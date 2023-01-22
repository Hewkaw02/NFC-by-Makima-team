package com.team.makimainu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

//    public static final String MyPer = "myPer";
//    public static final String baseURL = "http://localhost/makima/";

    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = (ImageView) findViewById(R.id.Logoview);
        Picasso.get().load(R.drawable.png_who_are_we).resize(800, 800).into(imageView);
    }
}