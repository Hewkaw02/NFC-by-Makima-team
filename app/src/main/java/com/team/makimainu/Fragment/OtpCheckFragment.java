package com.team.makimainu.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.material.textfield.TextInputEditText;
import com.team.makimainu.LoginActivity;
import com.team.makimainu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtpCheckFragment extends Fragment{

    Button bt_send_otps;
    Context mContext;

    private static final int REQ_USER_CONSENT = 200;

    SmsBroadcastReceiver smsBroadcastReceiver;

    TextInputEditText etOTP;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext =  context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_check, container, false);
        // Inflate the layout for this fragment

        etOTP = view.findViewById(R.id.etOTP);
        startSmartUserConsent();
        bt_send_otps = view.findViewById(R.id.bt_send_otp);
        bt_send_otps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send data to register if otp correct
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);

            }
        });

        return view;
    }

    private void startSmartUserConsent() {

        SmsRetrieverClient client = SmsRetriever.getClient(mContext);
        client.startSmsUserConsent(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_USER_CONSENT){

            if((resultCode == RESULT_OK) && (data != null)){
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOtpFromMessaga(message);
            }
        }
    }

    private void getOtpFromMessaga(String message) {
        Pattern otpPattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = otpPattern.matcher(message);
        if(matcher.find()){
            etOTP.setText(matcher.group(0));
        }
    }

    private void registerBroadcastReceiver(){
        smsBroadcastReceiver = new SmsBroadcastReceiver();

        smsBroadcastReceiver.smsBroadcastReceiverListener = new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {

                startActivityForResult(intent,REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {
            }
        };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        mContext.registerReceiver(smsBroadcastReceiver,intentFilter);

    }

    @Override
    public void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
        mContext.unregisterReceiver(smsBroadcastReceiver);
    }
}