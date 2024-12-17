package com.example.recyclerviewjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.recyclerviewjava.databinding.FragmentCityBinding;
import com.example.recyclerviewjava.databinding.FragmentCountryBinding;

import java.util.ArrayList;
import java.util.List;

public class CityFragment extends Fragment {

    private FragmentCityBinding binding;

    private static final String ARG_COUNTRY_NAME = "country_name";

    public static CityFragment newInstance(String countryName) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNTRY_NAME, countryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем название страны
        String countryName = getArguments() != null ? getArguments().getString(ARG_COUNTRY_NAME) : "";

        // Генерация списка городов
        List<GeoItem> cities = new ArrayList<>();
        cities.add(new GeoItem("City 1", "https://example.com/city1.jpg", null));
        cities.add(new GeoItem("City 2", "https://example.com/city2.jpg", null));
        // ... Добавьте больше городов

        // Настройка адаптера
        GeoAdapter adapter = new GeoAdapter(cities, item -> {
            // Переход к CityDetailFragment
            CityDetailFragment detailFragment = CityDetailFragment.newInstance(item.getName(), item.getImageUrl(), "Описание города");
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, detailFragment)
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
