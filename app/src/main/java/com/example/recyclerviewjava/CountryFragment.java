package com.example.recyclerviewjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recyclerviewjava.databinding.FragmentCountryBinding;

import java.util.ArrayList;
import java.util.List;

public class CountryFragment extends Fragment {

    private FragmentCountryBinding binding;

    private static final String ARG_CONTINENT_NAME = "continent_name";

    public static CountryFragment newInstance(String continentName) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTINENT_NAME, continentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCountryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем название континента
        String continentName = getArguments() != null ? getArguments().getString(ARG_CONTINENT_NAME) : "";

        // Генерация списка стран
        List<GeoItem> countries = new ArrayList<>();
        countries.add(new GeoItem("Country 1", "https://example.com/country1.jpg", null));
        countries.add(new GeoItem("Country 2", "https://example.com/country2.jpg", null));
        // ... Добавьте больше стран

        // Настройка адаптера
        GeoAdapter adapter = new GeoAdapter(countries, item -> {
            // Переход к CityFragment
            CityFragment cityFragment = CityFragment.newInstance(item.getName());
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, cityFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Настройка RecyclerView
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
