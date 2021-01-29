package com.example.doan_android_2021.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Brand;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private final Context context;
    private final List<Brand> brands;
    private final CategoryClickListener clickListener;

    public BrandAdapter(Context context, List<Brand> brands, CategoryClickListener clickListener) {
        this.context = context;
        this.brands = brands;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.brandName.setText(brands.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (brands == null) return 0;
        return brands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView brandName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.item_category_text);

            brandName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(brands.get(getAdapterPosition()).getId());
        }
    }

    public interface CategoryClickListener {
        void onClick(int id);
    }
}
