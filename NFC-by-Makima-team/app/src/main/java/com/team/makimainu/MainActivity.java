package com.team.makimainu;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    String[] titleArray = new String[]{"Slide 1", "Slide 2", "Slide 3", "Slide 4"};
    String prevStarted = "yes";
    private Context context = this;

    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    TextInputEditText etOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {this.getSupportActionBar().hide();}
        catch (NullPointerException e){}

        sliderAdapter = new SliderAdapter(this);

        //ยังไม่สร้าง layout otp
        //etOTP = findViewById();
        startSmartUserConsent();
        viewPager = (ViewPager) findViewById(R.id.viewpaper);
        
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == titleArray.length -1) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }else {
                    Log.e(TAG, "onPageSelected: position "+position +" title "+ titleArray.length);
//                    Toast.makeText(context, "posi  " + position +"     title  " + titleArray.length, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        viewPager.setAdapter(sliderAdapter);


        }

    private void startSmartUserConsent() {

        SmsRetrieverClient client = SmsRetriever.getClient(this);
        client.startSmsUserConsent(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_USER_CONSENT){

            if(resultCode == RESULT_OK && (data != null)){
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
        registerReceiver(smsBroadcastReceiver,intentFilter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
//        if (!sharedpreferences.getBoolean(prevStarted, false)) {
//            SharedPreferences.Editor editor = sharedpreferences.edit();
//            editor.putBoolean(prevStarted, Boolean.TRUE);
//            editor.apply();
//        } else {
//            finish();
//            moveToSecondary();
//        }
//    }
//
//    public void moveToSecondary(){
//        // use an intent to travel from one activity to another.
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
//    }
}


