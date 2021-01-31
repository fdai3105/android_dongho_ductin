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
import com.example.doan_android_2021.models.OrderDetail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private final Context context;
    private final List<OrderDetail> orderDetails;
    private final AdapterClickListener clickListener;

    public OrderDetailAdapter(Context context, List<OrderDetail> orderDetails, AdapterClickListener clickListener) {
        this.context = context;
        this.orderDetails = orderDetails;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetails.get(position);
        NumberFormat format = new DecimalFormat("#,###");
        Glide.with(context).load(orderDetail.getProduct().getImages().get(0).getImage()).into(holder.img);
        holder.name.setText(orderDetail.getProduct().getName());
        holder.price.setText(format.format(orderDetail.getProduct().getPrice()) + " vnd");
        holder.quantity.setText("Số lượng: " + orderDetail.getQuantity());
        holder.total.setText("Tổng cộng: " + format.format(orderDetail.getTotal()) + " vnd");
    }

    @Override
    public int getItemCount() {
        if (orderDetails == null) return 0;
        return orderDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout view;
        private final ImageView img;
        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private final TextView total;
        private final AdapterClickListener clickListener;

        public ViewHolder(@NonNull View itemView, AdapterClickListener clickListener) {
            super(itemView);
            view = itemView.findViewById(R.id.item_order_detail_view);
            img = itemView.findViewById(R.id.item_order_detail_img);
            name = itemView.findViewById(R.id.item_order_detail_name);
            price = itemView.findViewById(R.id.item_order_detail_price);
            quantity = itemView.findViewById(R.id.item_order_detail_quantity);
            total = itemView.findViewById(R.id.item_order_detail_total);
            view.setOnClickListener(this);
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClickListener(orderDetails.get(getAdapterPosition()).getProduct().getId());
        }
    }
}
