package com.example.doan_android_2021.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Product;
import com.example.doan_android_2021.models.ProductDatum;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    List<ProductDatum> products;
    private final AdapterClickListener clickListener;

    public ProductAdapter(Context context, List<ProductDatum> products, AdapterClickListener clickListener) {
        this.context = context;
        this.products = products;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat formatter = new DecimalFormat("#,###");
        Glide.with(context).load(products.get(position).getImages().get(0).getImage()).placeholder(R.drawable.placeholder).into(holder.image);
        holder.name.setText(products.get(position).getName() + "");
        holder.price.setText(formatter.format(products.get(position).getPrice()) + "₫");
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position) == null ? 1 : 0;
    }

    public void addMore(Product product) {
        if (product == null) return;
        this.products.addAll(product.getData());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (products == null) return 0;
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView image;
        private final TextView name;
        private final TextView price;
        private final AdapterClickListener vhClickListener;

        public ViewHolder(@NonNull View itemView, AdapterClickListener vhClickListener) {
            super(itemView);
            image = itemView.findViewById(R.id.item_items_image);
            name = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_product_price);
            this.vhClickListener = vhClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.vhClickListener.onItemClickListener(products.get(getAdapterPosition()).getId());
        }
    }
}
