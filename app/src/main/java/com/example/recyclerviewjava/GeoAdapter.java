package com.example.recyclerviewjava;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recyclerviewjava.databinding.ItemGeoBinding;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.GeoViewHolder> {

    private List<GeoItem> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(GeoItem item);
    }

    public GeoAdapter(List<GeoItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGeoBinding binding = ItemGeoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GeoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoViewHolder holder, int position) {
        GeoItem item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class GeoViewHolder extends RecyclerView.ViewHolder {
        private ItemGeoBinding binding;

        public GeoViewHolder(@NonNull ItemGeoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GeoItem item, OnItemClickListener listener) {
            binding.itemName.setText(item.getName());
            Glide.with(binding.getRoot().getContext()).load(item.getImageUrl()).into(binding.itemImage);
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
