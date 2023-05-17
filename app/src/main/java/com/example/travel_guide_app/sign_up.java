package com.example.travel_guide_app_1181390_1182126;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {

    Traveller traveller;
    dataBaseHelper dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        traveller = new Traveller();
        dataBase= new dataBaseHelper(sign_up.this, "database", null, 1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_sign_up);
        
        String[] options = { "Asia", "Africa", "North America", "South America","Antarctica","Europe","Australia" };
        final Spinner continentsSpinner =(Spinner) findViewById(R.id.preferred_travel_destinations_spinner);
        ArrayAdapter<String> objArr = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        continentsSpinner.setAdapter(objArr);

    }

    @Override
    protected void onResume() {
        super.onResume();

        FloatingActionButton signup = (FloatingActionButton) findViewById(R.id.signup_floating_button);
        final EditText email = (EditText) findViewById(R.id.email_address_sign_up);
        final EditText firstname = (EditText) findViewById(R.id.firstname_sign_up);
        final EditText lastname = (EditText) findViewById(R.id.Lastname_sign_up);
        final EditText password = (EditText) findViewById(R.id.password_sign_up);
        final EditText confirmpassword= (EditText) findViewById(R.id.confirm_password_sign_up);
        final Spinner continentsSpinner = (Spinner) findViewById(R.id.preferred_travel_destinations_spinner);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=true;
                //Check Continents
                if (continentsSpinner.getSelectedItem().equals("Asia") || continentsSpinner.getSelectedItem().equals("Africa") || continentsSpinner.getSelectedItem().equals("North America")|| continentsSpinner.getSelectedItem().equals("Antarctica")|| continentsSpinner.getSelectedItem().equals("Europe") || continentsSpinner.getSelectedItem().equals("Australia") ){
                    traveller.setPreferred(continentsSpinner.getSelectedItem().toString());
                }else{
                    Toast toast =Toast.makeText(sign_up.this, "Please Select Your Preferred Destination!",Toast.LENGTH_SHORT);
                    toast.show();
                    flag=false;
                }

                //Check first and last name
                if(firstname.getText().toString().length() < 3 ||firstname.getText().toString().length() > 20){
                    firstname.setText("");
                    firstname.setHint("First Name");
                    firstname.setHintTextColor(Color.parseColor("#c44f4f"));
                    if (lastname.getText().toString().length() < 3 || lastname.getText().toString().length() > 20){
                        lastname.setText("");
                        lastname.setHint("Last Name");
                        lastname.setHintTextColor(Color.parseColor("#c44f4f"));
                    }
                    Toast toast =Toast.makeText(sign_up.this, "First name must be longer than three characters and no more than twenty",Toast.LENGTH_SHORT);
                    toast.show();
                    flag=false;
                }else{
                    if (lastname.getText().toString().length() < 3 || lastname.getText().toString().length() > 20){
                        lastname.setText("");
                        lastname.setHint("Last Name");
                        lastname.setHintTextColor(Color.parseColor("#c44f4f"));
                        Toast toast =Toast.makeText(sign_up.this, "Last name must be longer than three characters and less than twenty",Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        traveller.setLname(firstname.getText().toString() +" "+ lastname.getText().toString());
                    }
                }


                if(!email.getText().toString().contains("@") || !email.getText().toString().endsWith(".com")){
                    email.setText("");
                    email.setHint("Email");
                    email.setHintTextColor(Color.parseColor("#c44f4f"));
                    Toast toast =Toast.makeText(sign_up.this, "Invalid Email Address",Toast.LENGTH_SHORT);
                    toast.show();
                    flag=false;
                }else {
                    if(dataBase.check_email(email.getText().toString())){
                        email.setText("");
                        email.setHint("Email");
                        email.setHintTextColor(Color.parseColor("#c44f4f"));
                        Toast toast =Toast.makeText(sign_up.this, "An account with this email already exists",Toast.LENGTH_SHORT);
                        toast.show();
                        flag=false;
                    }else{
                        traveller.setEmail(email.getText().toString());
                    }
                }
                //check password
                List<String> errorList = new ArrayList<String>();
                if(isValid(password.getText().toString(),confirmpassword.getText().toString(),errorList)){
                    traveller.setPassword(password.getText().toString());
                }else{
                    String errorline="";
                    for (String error : errorList) {
                        error=error+"\n";
                        errorline=errorline+error;
                    }
                    Toast toast =Toast.makeText(sign_up.this, errorline,Toast.LENGTH_SHORT);
                    toast.show();
                    flag=false;
                }

                if(flag==true){

                    Toast toast =Toast.makeText(sign_up.this, "Account Created Successfully!! ",Toast.LENGTH_SHORT);
                    toast.show();
                    Open_Sign_in();
                    dataBase.insertTraveller(traveller);
                }

            }
        });

    }
    public static boolean isValid(String passwordhere, String confirmhere, List<String> errorList) {

        Pattern numberCase = Pattern.compile("[0-9 ]");
        Pattern UpperCase = Pattern.compile("[A-Z ]");
        Pattern lowerCase = Pattern.compile("[a-z ]");

        errorList.clear();

        boolean flag=true;

        if (!passwordhere.equals(confirmhere)) {
            errorList.add("password and confirm password does not match");
            flag=false;
        }
        if (passwordhere.length() < 8) {
            errorList.add("Password length must have at least 8 character !!");
            flag=false;
        }
        if (passwordhere.length() > 15) {
            errorList.add("Password length must have at most 15 character !!");
            flag=false;
        }
        if (!UpperCase.matcher(passwordhere).find()) {
            errorList.add("Password must have at least one uppercase character !!");
            flag=false;
        }
        if (!lowerCase.matcher(passwordhere).find()) {
            errorList.add("Password must have at least one lowercase character !!");
            flag=false;
        }
        if (!numberCase.matcher(passwordhere).find()) {
            errorList.add("Password must have at least one number character !!");
            flag=false;
        }

        return flag;

    }

    public void Open_Sign_in() {

        startActivity(new Intent(sign_up.this,MainActivity.class));
    }

}






