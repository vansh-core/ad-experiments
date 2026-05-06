package com.example.exp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    TextView tvReceivedMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        tvReceivedMessage = view.findViewById(R.id.tvReceivedMessage);

        // Retrieve the Bundle sent from FirstFragment
        Bundle bundle = getArguments();

        if (bundle != null) {
            // Get string using the same key used in FirstFragment
            String receivedMessage = bundle.getString("message_key");
            tvReceivedMessage.setText(getString(R.string.received_message, receivedMessage));
        }

        return view;
    }
}
