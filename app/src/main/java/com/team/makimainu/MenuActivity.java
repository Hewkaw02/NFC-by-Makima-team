package com.team.makimainu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {


    CardView bt_use_nfc , bt_regis_nfc , bt_setting,bt_for_future ;
    TextView name_user;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        try {this.getSupportActionBar().hide();}
        catch (NullPointerException e){}

        context = getBaseContext();
        sharedPreferences = getSharedPreferences(LoginActivity.MyPer,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        bt_use_nfc = (CardView) findViewById(R.id.bt_Use_NFC);
        bt_regis_nfc = (CardView) findViewById(R.id.bt_Regis_NFC);
        bt_setting = (CardView) findViewById(R.id.bt_Setting);
        bt_for_future = (CardView) findViewById(R.id.bt_for_future);
        name_user = (TextView) findViewById(R.id.tag_name);

        String Name_user = sharedPreferences.getString(LoginActivity.Name_User,"");
        name_user.setText("Hello , "+Name_user);

        bt_for_future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_use_nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,NFC_UseActivity.class);
                startActivity(intent);

            }
        });

        bt_regis_nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,NFC_RegisterActivity.class);
                startActivity(intent);

            }
        });

        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,SettingUserActivity.class);
                startActivity(intent);

            }
        });

    }
}