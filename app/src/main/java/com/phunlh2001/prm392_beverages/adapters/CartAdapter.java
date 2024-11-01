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
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private final Context context;

    private List<Product> productList;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setDataProducts(List<Product> products) {
        productList = products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = productList.get(position);

        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources()
                .getIdentifier(product.getThumbnail(), "drawable", context.getPackageName());

        holder.ivCartItemImg.setImageResource(resourceId);
        holder.tvCartTitle.setText(product.getTitle());
        holder.tvCartDesc.setText(product.getDesc());
        holder.tvCartPrice.setText(product.getPrice() + "");
        holder.tvCartQuantity.setText("1");
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvCartTitle, tvCartPrice, tvCartQuantity, tvCartDesc;
        ImageView ivCartItemImg, ivCartDelete, ivPlusItem, ivMinusItem;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCartDesc = itemView.findViewById(R.id.tvCartDesc);
            tvCartTitle = itemView.findViewById(R.id.tvCartTitle);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            ivCartItemImg = itemView.findViewById(R.id.ivCartItemImg);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);

            ivCartDelete = itemView.findViewById(R.id.ivCartDelete);
            ivPlusItem = itemView.findViewById(R.id.ivPlusItem);
            ivMinusItem = itemView.findViewById(R.id.ivMinusItem);
        }
    }
}
