package com.example.exp9;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView tvCurrentTime, tvTotalTime, tvStatus;
    private Button btnPlay, btnPause, btnStop;
    private final Handler handler = new Handler();
    private Runnable updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        TextView tvSongName = findViewById(R.id.tvSongName);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        tvStatus = findViewById(R.id.tvStatus);
        seekBar = findViewById(R.id.seekBar);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        // Set song name from resources or default
        tvSongName.setText("song.mp3");

        setupMediaPlayer();
    }

    private void setupMediaPlayer() {
        // NOTE: You need to create a folder 'res/raw' and add 'song.mp3' to it.
        int resId = getResources().getIdentifier("song", "raw", getPackageName());
        
        if (resId != 0) {
            mediaPlayer = MediaPlayer.create(this, resId);
            
            if (mediaPlayer != null) {
                int duration = mediaPlayer.getDuration();
                tvTotalTime.setText(formatTime(duration));
                seekBar.setMax(duration);

                btnPlay.setOnClickListener(v -> {
                    mediaPlayer.start();
                    tvStatus.setText("Status: Playing");
                    updateSeekBarTask();
                });

                btnPause.setOnClickListener(v -> {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        tvStatus.setText("Status: Paused");
                    }
                });

                btnStop.setOnClickListener(v -> {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        tvStatus.setText("Status: Stopped");
                        try {
                            mediaPlayer.prepare();
                            mediaPlayer.seekTo(0);
                            seekBar.setProgress(0);
                            tvCurrentTime.setText("0:00");
                        } catch (Exception e) {
                            Log.e(TAG, "Error stopping mediaPlayer", e);
                        }
                    }
                });

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser && mediaPlayer != null) {
                            mediaPlayer.seekTo(progress);
                            tvCurrentTime.setText(formatTime(progress));
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                mediaPlayer.setOnCompletionListener(mp -> {
                    tvStatus.setText("Status: Completed");
                    seekBar.setProgress(0);
                    tvCurrentTime.setText("0:00");
                });
            } else {
                tvStatus.setText("Status: Error creating MediaPlayer");
            }
        } else {
            tvStatus.setText("Status: Missing res/raw/song.mp3");
            btnPlay.setEnabled(false);
            btnPause.setEnabled(false);
            btnStop.setEnabled(false);
        }
    }

    private void updateSeekBarTask() {
        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                    tvCurrentTime.setText(formatTime(currentPosition));
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(updateSeekBar);
    }

    private String formatTime(int millis) {
        return String.format(Locale.getDefault(), "%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateSeekBar);
    }
}
