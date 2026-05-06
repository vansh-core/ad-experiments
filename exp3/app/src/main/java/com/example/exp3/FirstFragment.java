package com.example.exp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    EditText etMessage;
    Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate = load the XML layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        etMessage = view.findViewById(R.id.etMessage);
        btnSend   = view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get text typed by user
                String message = etMessage.getText().toString();

                // Create SecondFragment instance
                SecondFragment secondFragment = new SecondFragment();

                // Bundle = key-value data packet to pass data between fragments
                Bundle bundle = new Bundle();
                bundle.putString("message_key", message); // put data with key

                // Attach bundle to SecondFragment
                secondFragment.setArguments(bundle);

                // Replace FirstFragment with SecondFragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, secondFragment)
                        .addToBackStack(null) // allows Back button to return
                        .commit();
            }
        });

        return view;
    }
}
