package com.team.makimainu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team.makimainu.LoginActivity;
import com.team.makimainu.MenuActivity;
import com.team.makimainu.Model.POJO_Sign_Up;
import com.team.makimainu.R;
import com.team.makimainu.Retrofit.NetworkConnectManager;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Sign_Up;

import okhttp3.ResponseBody;

public class SignUpFragment extends Fragment {

    EditText et_name , et_username , et_password , et_email , et_phone_number ;
    Button bt_send;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        context = getContext();
        et_name = view.findViewById(R.id.et_Name);
        et_username = view.findViewById(R.id.et_Username);
        et_password = view.findViewById(R.id.et_Password);
        et_phone_number = view.findViewById(R.id.et_Phone_number);
        et_email = view.findViewById(R.id.et_Email);
        bt_send = view.findViewById(R.id.bt_Send);




        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name_send = et_name.getText().toString();
                String username_send = et_username.getText().toString();
                String password_send = et_password.getText().toString();
                String email_send = et_email.getText().toString();
                String phone_number_send = et_phone_number.getText().toString();

                if (et_name.getText().toString().equals("")) {
                    et_name.setError("ดูเหมือนยังไม่ได้ใส่ ชื่อ นะ");
                } else if (et_username.getText().toString().equals("")) {
                    et_username.setError("ดูเหมือนยังไม่ได้ใส่ username นะ");
                } else if (et_password.getText().toString().equals("")) {
                    et_password.setError("ดูเหมือนยังไม่ได้ใส่ password นะ");
                } else if (et_email.getText().toString().equals("")) {
                    et_email.setError("ดูเหมือนยังไม่ได้ใส่ email นะ");
                } else if (et_phone_number.getText().toString().equals("")) {
                    et_phone_number.setError("ดูเหมือนยังไม่ได้ใส่ เบอร์ นะ");
                } else {



                    new NetworkConnectManager().callServer_Sign_Up(
                            callback_sign_up,
                            name_send,
                            username_send,
                            password_send,
                            email_send,
                            phone_number_send
                    );
                }
            }
        });



        return view;
}


    NetCallback_Sign_Up callback_sign_up = new NetCallback_Sign_Up() {
        @Override
        public void onResponse(POJO_Sign_Up create_acc) {

//            if (create_acc.getStaus().equals("Success")) {
//                Toast.makeText(context, "สมัครสมาชิกสำเร็จ", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(context, LoginActivity.class));
//            } else {
//                Toast.makeText(context, "สมัครสมาชิกไม่สำเร็จ", Toast.LENGTH_SHORT).show();
//            }

            if (create_acc.getStaus().equals("Unsuccess")){
                Toast.makeText(context, "ดูเหมือนจะมีปัญหาบางทีน้าา", Toast.LENGTH_SHORT).show();
            }else {

                Toast.makeText(context, "ลงทะเบียนสำเรํจ", Toast.LENGTH_SHORT).show();
// Go to OTP Fragment

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                OtpCheckFragment otherFragment = new OtpCheckFragment();
                fragmentTransaction.replace(R.id.container, otherFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Toast.makeText(context, "onBodyError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBodyErrorIsNull() {
            Toast.makeText(context, "onBodyErrorIsNull", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show();
        }
    };
}