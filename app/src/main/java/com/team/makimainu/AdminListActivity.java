package com.team.makimainu;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.team.makimainu.Model.POJO_Admin_Key_Detail;
import com.team.makimainu.Retrofit.NetworkConnectManager;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Admin_Get_Key;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class AdminListActivity extends AppCompatActivity {

    ListView list_key;

    String[] KeyArray ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
//        Toast.makeText(this, ""+ getIntent().getStringExtra("ID_user"), Toast.LENGTH_SHORT).show();

        list_key = findViewById(R.id.list_view_key);

        new NetworkConnectManager().callServer_admin_get_key(admin_get_key,getIntent().getStringExtra("ID_user"));

    }

    NetCallback_Admin_Get_Key admin_get_key = new NetCallback_Admin_Get_Key() {
        @Override
        public void onResponse(POJO_Admin_Key_Detail key_detail) {

            KeyArray = key_detail.getKeyData().toArray(new String[0]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String> (AdminListActivity.this,android.R.layout.simple_list_item_1,KeyArray);
            list_key.setAdapter(adapter);

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