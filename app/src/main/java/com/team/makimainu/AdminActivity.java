package com.team.makimainu;


import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.team.makimainu.CustomListView.CustomAdapter;
import com.team.makimainu.CustomListView.DataModel;
import com.team.makimainu.Model.POJO_Admin_Status;
import com.team.makimainu.Retrofit.NetworkConnectManager;
import com.team.makimainu.Retrofit.OnNetworkCallback.NetCallback_Admin_Get_User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class AdminActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView=(ListView)findViewById(R.id.list_data_admin);
        context = getBaseContext();
        sharedPreferences = getSharedPreferences(LoginActivity.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        try {this.getSupportActionBar().hide();}
        catch (NullPointerException e){}

        String tp_user = sharedPreferences.getString(LoginActivity.Type_User,"");


        dataModels= new ArrayList<>();
        new NetworkConnectManager().callServer_admin_get_user(get_user,tp_user);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    NetCallback_Admin_Get_User get_user = new NetCallback_Admin_Get_User() {
        @Override
        public void onResponse(POJO_Admin_Status status) {

            for (int i = 0; i< status.getData().size() ;i++) {
                Log.e(TAG, "onResponse: "+" "+status.getData().get(i).getName()+" "+status.getData().get(i).getPhoneNumber()+" "+status.getData().get(i).getKeyUnit());
                dataModels.add(new DataModel(""+status.getData().get(i).getName(),""+status.getData().get(i).getPhoneNumber(),""+status.getData().get(i).getKeyUnit()));

            }

            adapter= new CustomAdapter(dataModels,getApplicationContext());

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent intent = new Intent(getApplicationContext(),AdminListActivity.class);
                    intent.putExtra("ID_user",status.getData().get(position).getId());
                    startActivity(intent);
                }
            });

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