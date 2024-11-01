package com.phunlh2001.prm392_beverages.adapters;

import static com.phunlh2001.prm392_beverages.utils.Constant.KEY_CART;
import static com.phunlh2001.prm392_beverages.utils.Constant.PREF_CART;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phunlh2001.prm392_beverages.ProductDetailActivity;
import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.viewmodel.AddressViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductMenuAdapter extends RecyclerView.Adapter<ProductMenuAdapter.MenuViewHolder> {
    private List<Product> _products;
    private final Context context;

    public ProductMenuAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Product> list) {
        _products = list;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_menu_item, parent, false);
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
        
        holder.btnDetail.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_product", prod);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
        
        holder.btnAddToCart.setOnClickListener(view -> {
            try {
                _products = getList();
                _products.add(prod);
                setList(_products);
                Toast.makeText(context, "Add to cart successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Add to cart failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return _products != null ? _products.size() : 0;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView thumbnail;
        Button btnAddToCart, btnDetail;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            thumbnail = itemView.findViewById(R.id.img_item_menu);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }

    private void setList(List<Product> list) {
        SharedPreferences pref = context.getSharedPreferences(PREF_CART, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor.putString(KEY_CART, json);
        editor.apply();
    }

    private List<Product> getList() {
        SharedPreferences pref = context.getSharedPreferences(PREF_CART, Context.MODE_PRIVATE);
        List<Product> arrItems = new ArrayList<>();
        String json = pref.getString(KEY_CART, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>(){}.getType();
            arrItems = gson.fromJson(json, type);
        }
        return arrItems;
    }
}
