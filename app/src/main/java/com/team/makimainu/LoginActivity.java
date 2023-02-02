package com.team.makimainu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.team.makimainu.Fragment.SignUpFragment;
import com.team.makimainu.Model.POJO_Login;
import com.team.makimainu.Retrofit.NetworkConnectManager;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Login;

import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity  {

    public static final String MyPer = "myPer";
    public static final String BASEURL = "http://10.0.2.2/makima/";
    public static  String Name_User = "Name";
    public static  String Email_User = "Email";
    public static  String Phone_number_User = "Phone_number";
    public static  String Type_User = "User";


    ImageView imageView ;
    EditText et_user , et_pass;
    Button bt_signup , bt_signin;
    LinearLayout layout_login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {this.getSupportActionBar().hide();}
        catch (NullPointerException e){}

        sharedPreferences = getSharedPreferences(MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        imageView = (ImageView) findViewById(R.id.Logoview);
        et_user = (EditText) findViewById(R.id.et_Username);
        et_pass = (EditText) findViewById(R.id.et_Password);
        bt_signin = (Button) findViewById(R.id.bt_Login);
        bt_signup = (Button) findViewById(R.id.bt_register);
        layout_login = (LinearLayout) findViewById(R.id.Layout_Login);
        Picasso.get().load(R.drawable.png_who_are_we).resize(800, 800).into(imageView);

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);

//            layout_login.setVisibility(View.GONE);
//            getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container, new SignUpFragment()).commit();


            }
        });
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_user.getText().toString().equals("")){
                    et_user.setError("ดูเหมือนยังไม่ได้ใส่ username นะ");
                } else if (et_pass.getText().toString().equals("")) {
                    et_pass.setError("ดูเหมือนยังไม่ได้ใส่ password นะ");
                }else {

                    String user = et_user.getText().toString();
                    String pass = et_pass.getText().toString();

                    new NetworkConnectManager().callServer_Login(sign_in,user,pass);
                }

            }
        });

    }

    NetCallback_Login sign_in = new NetCallback_Login() {
        @Override
        public void onResponse(POJO_Login Sign_In) {

            if (Sign_In.getStatus().equals("failed")){
                Toast.makeText(LoginActivity.this, "ดูเหมือน username หรือ password ยังผิดนะ", Toast.LENGTH_SHORT).show();
            }else {

                Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();

                editor.putString(Name_User , Sign_In.getName().toString());
                editor.putString(Email_User , Sign_In.getEmail().toString());
                editor.putString(Phone_number_User , Sign_In.getPhoneNumber().toString());
                editor.putString(Type_User, Sign_In.getTypeUser().toString());
                editor.commit();

                if (Sign_In.getTypeUser().equals("User")){

                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intent);
                    finish();

                }else if (Sign_In.getTypeUser().equals("Admin")){

                    Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                    startActivity(intent);
                    finish();

                }else {

                    Toast.makeText(LoginActivity.this, "Who are you !?", Toast.LENGTH_SHORT).show();

                }



            }


        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

}