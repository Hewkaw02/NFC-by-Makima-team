package com.team.makimainu;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.team.makimainu.Model.POJO_Admin_Key_Detail;
import com.team.makimainu.Model.POJO_Key_Update;
import com.team.makimainu.Retrofit.NetworkConnectManager;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Admin_Get_Key;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_key_update;

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
        list_key = findViewById(R.id.list_view_key);

        new NetworkConnectManager().callServer_admin_get_key(admin_get_key,getIntent().getStringExtra("ID_user"));

        list_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedItem = KeyArray[position];
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminListActivity.this);
                builder.setTitle("Edit Key");

                final EditText input = new EditText(AdminListActivity.this);
                input.setText(selectedItem);
                builder.setView(input);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the item in the ListView
                        KeyArray[position] = input.getText().toString();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminListActivity.this, android.R.layout.simple_list_item_1, KeyArray);
                        list_key.setAdapter(adapter);

                        // Send the updated information to the database
                        new NetworkConnectManager().callServer_update_key(key_update,input.getText().toString(),getIntent().getStringExtra("ID_user"));
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
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
    NetCallback_key_update key_update = new NetCallback_key_update() {
        @Override
        public void onResponse(POJO_Key_Update key_update) {

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