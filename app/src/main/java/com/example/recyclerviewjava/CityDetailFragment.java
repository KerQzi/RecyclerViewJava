package com.example.recyclerviewjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.recyclerviewjava.databinding.FragmentCityDetailBinding;

public class CityDetailFragment extends Fragment {

    private FragmentCityDetailBinding binding;

    private static final String ARG_CITY_NAME = "city_name";
    private static final String ARG_CITY_IMAGE = "city_image";
    private static final String ARG_CITY_DESCRIPTION = "city_description";

    public static CityDetailFragment newInstance(String cityName, String cityImage, String cityDescription) {
        CityDetailFragment fragment = new CityDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY_NAME, cityName);
        args.putString(ARG_CITY_IMAGE, cityImage);
        args.putString(ARG_CITY_DESCRIPTION, cityDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем данные о городе
        Bundle args = getArguments();
        if (args != null) {
            binding.cityName.setText(args.getString(ARG_CITY_NAME));
            binding.cityDescription.setText(args.getString(ARG_CITY_DESCRIPTION));
            Glide.with(this).load(args.getString(ARG_CITY_IMAGE)).into(binding.cityImage);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
