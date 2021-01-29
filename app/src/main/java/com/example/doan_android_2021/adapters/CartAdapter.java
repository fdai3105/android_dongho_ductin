package com.example.doan_android_2021.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Cart;
import com.example.doan_android_2021.models.ProductDatum;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Context context;
    private final List<Cart> carts;
    private final CartClickListener cartClickListener;

    public CartAdapter(Context context, List<Cart> carts, CartClickListener cartClickListener) {
        this.context = context;
        this.carts = carts;
        this.cartClickListener = cartClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view, cartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        NumberFormat format = new DecimalFormat("#,###");
        Glide.with(context).load(cart.getProduct().getImages().get(0).getImage()).into(holder.itemImg);
        holder.itemName.setText(cart.getProduct().getName());
        holder.itemPrice.setText(format.format(cart.getProduct().getPrice()) + " vnd");
        holder.itemQuantity.setText(cart.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        if (carts == null) return 0;
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView itemImg;
        private final TextView itemName;
        private final TextView itemPrice;
        private final TextView itemQuantity;
        private final Button itemRemove;
        private final CartClickListener cartClickListener;

        public ViewHolder(@NonNull View itemView, CartClickListener cartClickListener) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_cart_img);
            itemName = itemView.findViewById(R.id.item_cart_name);
            itemPrice = itemView.findViewById(R.id.item_cart_price);
            itemQuantity = itemView.findViewById(R.id.item_cart_quantity);
            itemRemove = itemView.findViewById(R.id.item_cart_remove);
            this.cartClickListener = cartClickListener;
            itemRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_cart_remove:
                    this.cartClickListener.onRemoveClick(carts.get(getAdapterPosition()).getId());
                    break;
            }
        }
    }

    public interface CartClickListener {
        void onRemoveClick(int id);

        void ItemClick(int id);
    }
}
