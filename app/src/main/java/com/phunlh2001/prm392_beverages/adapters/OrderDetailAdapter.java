package com.phunlh2001.prm392_beverages.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.viewmodel.OrderInfo;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {

    List<OrderInfo> mListProduct;
    Context context;

    public OrderDetailAdapter(List<OrderInfo> mListProduct, Context context) {
        this.mListProduct = mListProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_item, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderInfo orderInfo = mListProduct.get(position);
        if(orderInfo == null){
            return;
        }

        int resourceId = context.getResources().getIdentifier(orderInfo.getThumbnail(), "drawable", context.getPackageName());
        holder.img.setImageResource(resourceId);
        holder.txtproductName.setText(orderInfo.getTitle());
        holder.txtpriceAndQuantity.setText(String.valueOf(orderInfo.getPrice()) + "đ" + " x" + String.valueOf(orderInfo.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtproductName, txtpriceAndQuantity;
        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.img_product);
            txtproductName = itemView.findViewById(R.id.product_name);
            txtpriceAndQuantity = itemView.findViewById(R.id.price_quantity);
        }
    }
}
