package com.example.travel_guide_app_1181390_1182126;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.travel_guide_app_1181390_1182126.ui.home.HomeFragment;
public class Connection extends AppCompatActivity {
    private dataBaseHelper dataBase;
    Button button;
    LinearLayout linearLayout;
    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase= new dataBaseHelper(this, "database", null, 1);


        checkFirstRun();


        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(Connection.this);
        connectionAsyncTask.execute("https://firebasestorage.googleapis.com/v0/b/advance-proj1.appspot.com/o/data.json?alt=media&token=503ffc5e-9131-4572-ab9f-49910746c63f\n");

    }

    public void OpenSigninPage() {
        startActivity(new Intent(Connection.this, HomeFragment.class));
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            OpenSigninPage();
            finish();
            // This is just a normal run


        } else if (savedVersionCode == DOESNT_EXIST) {
            // Update the shared preferences with the current version code
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
            return;

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }


    }


}