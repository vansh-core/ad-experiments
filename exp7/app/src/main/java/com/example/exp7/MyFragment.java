package com.example.exp7;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    // TAG used for Logcat filtering — search "FragmentDemo" in Logcat
    private static final String TAG = "FragmentDemo";
    Button btnShow;

    // 1. onAttach() — Fragment attached to Activity (FIRST)
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "1. onAttach() → Fragment attached to Activity");
    }

    // 2. onCreate() — Fragment initializing (data setup, NOT views)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "2. onCreate() → Fragment initializing");
    }

    // 3. onCreateView() — CREATE and RETURN Fragment's UI (MOST IMPORTANT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "3. onCreateView() → Fragment UI created");

        // Inflate XML layout — Fragment uses inflate() NOT setContentView()
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        btnShow = view.findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Fragment Active! Check Logcat for lifecycle!",
                        Toast.LENGTH_LONG).show();
            }
        });

        return view; // MUST return view
    }

    // 4. onActivityCreated() — Host Activity's onCreate() finished
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "4. onActivityCreated() → Activity fully created");
    }

    // 5. onStart() — Fragment VISIBLE to user
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "5. onStart() → Fragment is VISIBLE");
    }

    // 6. onResume() — Fragment ACTIVE and INTERACTIVE
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "6. onResume() → Fragment ACTIVE ✅ User can interact");
    }

    // 7. onPause() — Fragment loses focus
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "7. onPause() → Fragment PAUSED");
    }

    // 8. onStop() — Fragment not visible
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "8. onStop() → Fragment STOPPED");
    }

    // 9. onDestroyView() — Fragment's View destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "9. onDestroyView() → Fragment View DESTROYED");
    }

    // 10. onDestroy() — Fragment object destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "10. onDestroy() → Fragment DESTROYED");
    }

    // 11. onDetach() — Fragment detached from Activity (LAST)
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "11. onDetach() → Fragment DETACHED from Activity");
    }
}
