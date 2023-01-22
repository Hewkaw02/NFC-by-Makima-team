package com.team.makimainu;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    String[] titleArray = new String[]{"Slide 1", "Slide 2", "Slide 3", "Slide 4"};
    String prevStarted = "yes";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {this.getSupportActionBar().hide();}
        catch (NullPointerException e){}

        sliderAdapter = new SliderAdapter(this);


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


