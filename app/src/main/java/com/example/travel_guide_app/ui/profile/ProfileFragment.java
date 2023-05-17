package com.example.travel_guide_app_1181390_1182126.ui.profile;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travel_guide_app_1181390_1182126.dataBaseHelper;
import com.example.travel_guide_app_1181390_1182126.databinding.FragmentProfileBinding;
import com.example.travel_guide_app_1181390_1182126.R;
import com.example.travel_guide_app_1181390_1182126.sign_up;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private dataBaseHelper dataBase;
    String email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        final String[] editeddata = new String[4];


        final ImageView editimage = getActivity().findViewById(R.id.edit);
        final Button updatebutton = getActivity().findViewById(R.id.update_profile);

        final EditText firstnamefield = (EditText) getActivity().findViewById(R.id.firstname_profile);
        final EditText lastnamefield = (EditText) getActivity().findViewById(R.id.lastname_profile);
        final EditText emailfield = (EditText) getActivity().findViewById(R.id.email_profile);
        final EditText passwordfield = (EditText) getActivity().findViewById(R.id.password_profile);
        final EditText confirmpasswordfield = (EditText) getActivity().findViewById(R.id.confirm_profile);
        final Spinner preferedfield = (Spinner) getActivity().findViewById(R.id.preferred_travel_destinations_spinner_profile);

//        dataBase = new dataBaseHelper(getActivity(), "database", null, 1);
//        String fname =" hhhhhhhhhhh", lname= " mmmmmmmmm", mail=" @@@@@@@@";
//        Cursor data = dataBase.getTraveller("hedayaa@gmail.com");
//        System.out.println("dataaa"+data);
//
//        try {
//            while (data.moveToNext()) {
//                fname = data.getString(1);
//                lname = data.getString(2);
//                mail = data.getString(3);
//            }
//        } finally {
//
//            if (data != null && data.isClosed())
//                data.close();
//            dataBase.close();
//        }
//        System.out.println(" f "+fname + " l "+ lname + " m " + mail);
        passwordfield.setVisibility(View.INVISIBLE);
        confirmpasswordfield.setVisibility(View.INVISIBLE);
        updatebutton.setVisibility(View.INVISIBLE);

        editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstnamefield.setEnabled(true);
                lastnamefield.setEnabled(true);
                emailfield.setEnabled(true);
                preferedfield.setEnabled(true);
                passwordfield.setVisibility(View.VISIBLE);
                confirmpasswordfield.setVisibility(View.VISIBLE);
                updatebutton.setVisibility(View.VISIBLE);

            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = true;
                //Check first and last name
                if ((firstnamefield.getText().toString().length() < 3) || (firstnamefield.getText().toString().length() > 20)) {
                    firstnamefield.setText("");
                    firstnamefield.setHint("First Name");
                    firstnamefield.setHintTextColor(Color.parseColor("#c44f4f"));
                    Toast toast = Toast.makeText(getActivity(), "First name must be longer than three characters and Less than twenty", Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                } else {
                    editeddata[0] = firstnamefield.getText().toString();
                }
                if ((lastnamefield.getText().toString().length() < 3) || (lastnamefield.getText().toString().length() > 20)) {
                    lastnamefield.setText("");
                    lastnamefield.setHint("Last Name");
                    lastnamefield.setHintTextColor(Color.parseColor("#c44f4f"));
                    Toast toast = Toast.makeText(getActivity(), "Last name must be longer than three characters and Less than twenty", Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                } else {
                    editeddata[1] = lastnamefield.getText().toString();
                }

                //email
                if (!emailfield.getText().toString().contains("@") || !emailfield.getText().toString().endsWith(".com")) {
                    emailfield.setText("");
                    emailfield.setHint("Email");
                    emailfield.setHintTextColor(Color.parseColor("#c44f4f"));
                    flag = false;
                } else {
                    if (dataBase.check_email(emailfield.getText().toString())) {
                        emailfield.setText("");
                        emailfield.setHint("Email");
                        emailfield.setHintTextColor(Color.parseColor("#c44f4f"));
                        flag = false;
                    } else {
                        editeddata[2] = emailfield.getText().toString();
                    }
                }


                List<String> errorList = new ArrayList<String>();

                if (isvalid(passwordfield.getText().toString(), confirmpasswordfield.getText().toString(), errorList)) {
                    editeddata[3] = passwordfield.getText().toString();
                } else {
                    String errorline = "";
                    for (String error : errorList) {
                        error = error + "\n";
                        errorline = errorline + error;
                    }
                    Toast toast = Toast.makeText(getActivity(), errorline, Toast.LENGTH_SHORT);
                    toast.show();
                    flag = false;
                }

                if (flag == true) {


//                    String name = editeddata[0] + " " + editeddata[1];
//                    dataBase.updateTraveller(email, name, editeddata[2], editeddata[3]);


                    firstnamefield.setEnabled(false);
                    firstnamefield.setText(editeddata[0]);

                    lastnamefield.setEnabled(false);
                    lastnamefield.setText(editeddata[1]);

                    emailfield.setEnabled(false);
                    emailfield.setText(editeddata[2]);

                    preferedfield.setEnabled(false);


                    passwordfield.setVisibility(View.INVISIBLE);
                    confirmpasswordfield.setVisibility(View.INVISIBLE);
                    updatebutton.setVisibility(View.INVISIBLE);

                    Toast toast = Toast.makeText(getActivity(), "Data Changed Successfully!! ", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }

        });

    }
    public static boolean isvalid(String passwordhere, String confirmhere, List<String> errorList) {

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
}

