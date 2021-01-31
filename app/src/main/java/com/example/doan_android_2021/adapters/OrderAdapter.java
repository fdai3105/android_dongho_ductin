package com.example.doan_android_2021.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android_2021.R;
import com.example.doan_android_2021.models.Order;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private final Context context;
    private final List<Order> orders;
    private final AdapterClickListener clickListener;

    public OrderAdapter(Context context, List<Order> orders, AdapterClickListener clickListener) {
        this.context = context;
        this.orders = orders;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        NumberFormat format = new DecimalFormat("#,###");
        holder.id.setText("#" + order.getId());
        holder.name.setText("Đơn hàng #" + order.getId());
        holder.status.setText("Trạng thái: " + order.getStatus());
        holder.total.setText(format.format(order.getTotal()) + " vnd");
        holder.address.setText(order.getAddress().getCity() + ", "
                + order.getAddress().getDistrict() + ", "
                + order.getAddress().getWard() + ", "
                + order.getAddress().getAddress());
    }

    @Override
    public int getItemCount() {
        if (orders == null) return 0;
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout view;
        private final TextView id;
        private final TextView name;
        private final TextView status;
        private final TextView total;
        private final TextView address;
        private final AdapterClickListener clickListener;

        public ViewHolder(@NonNull View itemView, AdapterClickListener clickListener) {
            super(itemView);
            view = itemView.findViewById(R.id.item_order_view);
            id = itemView.findViewById(R.id.item_order_id);
            name = itemView.findViewById(R.id.item_order_name);
            status = itemView.findViewById(R.id.item_order_status);
            total = itemView.findViewById(R.id.item_order_total);
            address = itemView.findViewById(R.id.item_order_address);
            view.setOnClickListener(this);
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            this.clickListener.onItemClickListener(orders.get(getAdapterPosition()).getId());
        }
    }
}
