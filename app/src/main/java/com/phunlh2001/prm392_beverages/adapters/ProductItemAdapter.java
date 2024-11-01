package com.phunlh2001.prm392_beverages.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.phunlh2001.prm392_beverages.R;
import com.phunlh2001.prm392_beverages.data.entities.ProductItem;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder> {
    private List<ProductItem> productItemList;
    private ProductClickedListeners productClickedListeners;
    public ProductItemAdapter(ProductClickedListeners productClickedListeners){
        this.productClickedListeners = productClickedListeners;
    }
    public void setProductItemList(List<ProductItem> productItemList){
        this.productItemList = productItemList;
    }
    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item , parent , false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
        ProductItem productItem = productItemList.get(position);
        holder.tvProductTitle.setText(productItem.getTitle());
        holder.tvProductPrice.setText(String.valueOf(productItem.getPrice()));
        holder.ivProductImg.setImageDrawable(Drawable.createFromPath(productItem.getThumbnail()));
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListeners.onCardClicked(productItem);
            }
        });
        holder.btnProductAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListeners.onAddToCartBtnClicked(productItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productItemList == null){
            return 0;
        }else{
            return productItemList.size();
        }
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProductImg , btnProductAddToCart;
        private TextView tvProductTitle, tvProductPrice;
        private CardView cvProduct;
        public ProductItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cvProduct = itemView.findViewById(R.id.cvProduct);
            ivProductImg = itemView.findViewById(R.id.ivProductImg);
            btnProductAddToCart = itemView.findViewById(R.id.btnProductAddToCart);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }

    public interface ProductClickedListeners{
        void onCardClicked(ProductItem product);
        void onAddToCartBtnClicked(ProductItem productItem);
    }
}
