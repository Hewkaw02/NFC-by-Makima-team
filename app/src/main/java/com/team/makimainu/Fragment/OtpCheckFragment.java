package com.team.makimainu.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team.makimainu.R;

public class OtpCheckFragment extends Fragment {


    Button bt_send_otps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_check, container, false);
        // Inflate the layout for this fragment

        bt_send_otps = view.findViewById(R.id.bt_send_otp);
        bt_send_otps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send data to register if otp correct


                getActivity().finish();

            }
        });

        return view;
    }
}