package com.example.doan_android_2021.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.ProductDatum;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class HotProductAdapter extends RecyclerView.Adapter<HotProductAdapter.ViewHolder> {
    private final Context context;
    private final List<ProductDatum> hotProducts;
    private final AdapterClickListener clickListener;

    public HotProductAdapter(Context context, List<ProductDatum> hotProducts, AdapterClickListener clickListener) {
        this.context = context;
        this.hotProducts = hotProducts;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hot_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat format = new DecimalFormat("#,###");
        ProductDatum product = hotProducts.get(position);
        Glide.with(context).load(product.getImages().get(0).getImage()).into(holder.productImg);
        holder.productSize.setText(product.getSize() + " mm");
        holder.productWaterproof.setText(product.getWaterproof() + " ATM");
        holder.productName.setText(product.getName());
        holder.productPrice.setText(format.format(product.getPrice()) + " vnd");
    }

    @Override
    public int getItemCount() {
        if (hotProducts == null) return 0;
        return hotProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout productView;
        private final TextView productSize;
        private final TextView productWaterproof;
        private final ImageView productImg;
        private final TextView productName;
        private final TextView productPrice;
        private AdapterClickListener vhClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productView = itemView.findViewById(R.id.item_hot_product_view);
            productSize = itemView.findViewById(R.id.item_hot_product_size);
            productWaterproof = itemView.findViewById(R.id.item_hot_product_waterproof);
            productImg = itemView.findViewById(R.id.item_hot_product_img);
            productName = itemView.findViewById(R.id.item_hot_product_name);
            productPrice = itemView.findViewById(R.id.item_hot_product_price);
            vhClickListener = clickListener;
            productView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.vhClickListener.onItemClickListener(hotProducts.get(getAdapterPosition()).getId());
        }
    }
}
