package com.example.travel_guide_app_1181390_1182126.ui.sorted;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.travel_guide_app_1181390_1182126.databinding.FragmentSortedBinding;
import com.example.travel_guide_app_1181390_1182126.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SortedFragment extends Fragment {

    private FragmentSortedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSortedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

}