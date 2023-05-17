package com.example.travel_guide_app_1181390_1182126;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // define database helper and SharedPrefManager
    SharedPrefManager sharedPrefManager;
    dataBaseHelper dataBase;
    public static String EXTRA_EMAIL = "package com.example.travel_guide_app_1181390_1182126";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new dataBaseHelper(MainActivity.this, "database", null, 1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

//        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
//        connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");
    }

    //================================================================
    public void Open_Sign_up(View view) {
        startActivity(new Intent(MainActivity.this, sign_up.class));
    }

    protected void onResume() {

        super.onResume();
        FloatingActionButton login = (FloatingActionButton) findViewById(R.id.sign_in_button);
        final EditText email = (EditText) findViewById(R.id.email_address);
        final EditText password = (EditText) findViewById(R.id.password);
        final CheckBox rememberme = (CheckBox) findViewById(R.id.login_rememberme);
//        final TextView signup = (TextView) findViewById(R.id.signup1) ;

        sharedPrefManager = SharedPrefManager.getInstance(this);
        if (!sharedPrefManager.readString("email", "noValue").equals("noValue")) {
            email.setText(sharedPrefManager.readString("email", "noValue"));
            password.setText(sharedPrefManager.readString("password", "noValue"));
            rememberme.setChecked(true);
        }
        //-----------------------------------------------------------------
        //remember me check box
        //When you click on it, it will take the value in the email and password (in the EditText)
        // and save it inside the EditText itself,
        // so that if we open the application again, we will find the EditText is full
        rememberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rememberme.isChecked()){
                    sharedPrefManager=SharedPrefManager.getInstance(MainActivity.this);
                    sharedPrefManager.writeString("email",email.getText().toString());
                    sharedPrefManager.writeString("password",password.getText().toString());
                }else if(!rememberme.isChecked()){
                    sharedPrefManager=SharedPrefManager.getInstance(MainActivity.this);
                    sharedPrefManager.writeString("email","noValue");
                    sharedPrefManager.writeString("password","noValue");
                }
            }
        });
        //-------------------------------------------------------------------------
        //login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataBase.check_User_Exist(email.getText().toString(),password.getText().toString())){
                    Toast toast =Toast.makeText(MainActivity.this, "login successfully",Toast.LENGTH_SHORT);
                    toast.show();
                    OpenHomePage(email.getText().toString());
                    List<Destination> destinations = new ArrayList<Destination>();
                    store_Destination(destinations);

                }else{
                    email.setHint("Email");
                    email.setHintTextColor(Color.RED);
                    password.setHint("password");
                    password.setHintTextColor(Color.RED);

                    Toast toast =Toast.makeText(MainActivity.this, "Wrong Email or password",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        //-----------------------------------------------------------------------------------------
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Open_Sign_up(view);
//            }
//        });

    }

    public void OpenHomePage(String email) {
        startActivity(new Intent(MainActivity.this,NavHome.class).putExtra(EXTRA_EMAIL,email));
    }

    public void store_Destination(List<Destination> destinations) {
        dataBaseHelper destination_data = new dataBaseHelper(MainActivity.this, "database", null, 1);

        for (int i = 0; i < destinations.size(); i++) {
            System.out.println ("destination("+i+")"+destinations.get(i).toString() +"\n");
           destination_data.insertDestination(destinations.get(i));
        }
    }

}