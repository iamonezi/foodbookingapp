package com.phunlh2001.prm392_beverages.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phunlh2001.prm392_beverages.Interface.RecyclerViewInterface;
import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.entities.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MenuViewHolder> {

    private List<Product> _products;
    private final Context context;

    private RecyclerViewInterface iRecyclerViewInterface;


    public ProductAdapter(Context context, RecyclerViewInterface listener) {
        this.context = context;
        this.iRecyclerViewInterface = listener;
    }

    public void setData(List<Product> list) {
        _products = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_search_item, parent, false);
        return new MenuViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Product prod = _products.get(position);
        if (prod == null) return;

        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources()
                .getIdentifier(prod.getThumbnail(), "drawable", context.getPackageName());

        holder.thumbnail.setImageResource(resourceId);
        holder.title.setText(prod.getTitle());
        holder.price.setText("" + prod.getPrice());

        //dang ky bo su kien xu ly viec click phan tu RecyclerView, va lay du lieu da click
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRecyclerViewInterface.onItemClick(prod);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (_products != null) return _products.size();
        return 0;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView thumbnail;
        RelativeLayout layoutItem;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            thumbnail = itemView.findViewById(R.id.img_item_menu);
            layoutItem = itemView.findViewById(R.id.layoutRCVItem);
        }
    }
}
