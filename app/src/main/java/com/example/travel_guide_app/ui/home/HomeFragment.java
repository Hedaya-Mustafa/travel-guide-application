package com.example.travel_guide_app_1181390_1182126.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.travel_guide_app_1181390_1182126.Destination;
import com.example.travel_guide_app_1181390_1182126.MainActivity;
import com.example.travel_guide_app_1181390_1182126.R;
import com.example.travel_guide_app_1181390_1182126.dataBaseHelper;
import com.example.travel_guide_app_1181390_1182126.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

//import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    Destination destination;
    dataBaseHelper dataBase;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView city= root.findViewById(R.id.city_textView2);
        final TextView country= root.findViewById(R.id.country_textView2);
        final TextView continent= root.findViewById(R.id.continent_textView2);
        final TextView longitude= root.findViewById(R.id.longitude_textView2);
        final TextView latitude= root.findViewById(R.id.latitude_textView2);
        final TextView cost= root.findViewById(R.id.cost_textView2);
        final ImageView img= root.findViewById(R.id.city_imageView);
        final TextView description= root.findViewById(R.id.description_textView2);

        dataBase = new dataBaseHelper(root.getContext(), "database", null, 1);
//        favsList = new ArrayList<>();

        //================================================================
        List<String> pref_cites = new ArrayList<>();
        Cursor data = dataBase.getCitesByGivenContinent("Africa");
        while (data.moveToNext()) {
            pref_cites.add(data.getString(0));
        }
        // choose random city from list
        Random r = new Random();
        int randomitem = r.nextInt(pref_cites.size());
        String random_city = pref_cites.get(randomitem);

        System.out.println("pref = " + random_city);
        //===============================================================

        Cursor random_destination = dataBase.getAllInformation_ofOne_preferredContinent(random_city);
//        boolean em = dataBase.check_email("enas@gmail.com");
//        System.out.println("rand = " + random_destination);
        while (random_destination.moveToNext()) {
            System.out.println("cursor = " + random_destination.getString(0));
            city.setText(random_destination.getString(0));
            country.setText(random_destination.getString(1));
            continent.setText(random_destination.getString(2));
            longitude.setText(random_destination.getString(3));
            latitude.setText(random_destination.getString(4));
            cost.setText(random_destination.getString(5));
            Picasso.with(getActivity()).load(random_destination.getString(6)).into(img);
            description.setText(random_destination.getString(7));
        }
        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
    }






}