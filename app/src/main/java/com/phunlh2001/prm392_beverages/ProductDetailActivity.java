package com.phunlh2001.prm392_beverages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phunlh2001.prm392_beverages.data.AppDatabase;
import com.phunlh2001.prm392_beverages.data.dao.ProductDao;
import com.phunlh2001.prm392_beverages.data.entities.Product;

@SuppressWarnings("unchecked")
public class ProductDetailActivity extends AppCompatActivity {
    private Product productQuery;
    private Product product;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvDes;
    private ImageView ivProduct;
    private ProductDao productDao;

    private void init() {
        tvName = findViewById(R.id.menu_item_title);
        tvPrice = findViewById(R.id.menu_item_price);
        tvDes = findViewById(R.id.menu_item_des);
        ivProduct = findViewById(R.id.img_item_menu);

        //khoi tao dao
        productDao = AppDatabase.getInstance(this).productDao();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();

        //get recived product
        product = (Product) getIntent().getExtras().get("object_product");
        if (product == null) {
            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
        } else {
            //fill data to UI
            try {
                //Truy xuat ID tu bundle lay duoc tu RecylerView
                int sId = product.getId();
                //Truy xuat CSDL =sId de lay full thong tin
                retrieveProductFromDBtoUI(sId);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    //lay du lieu thong qua id
    @SuppressLint("SetTextI18n")
    private void retrieveProductFromDBtoUI(int ID) {
        try {
             productQuery = productDao.getById(ID);
            if (productQuery != null) {
                // fill data to UI
                @SuppressLint("DiscouragedApi")
                int resourceId = getResources()
                        .getIdentifier(productQuery.getThumbnail(), "drawable", getPackageName());

                    tvName.setText(productQuery.getTitle());
                    tvPrice.setText("" + productQuery.getPrice());
                    tvDes.setText(productQuery.getDesc());
                    ivProduct.setImageResource(resourceId);

            } else Toast.makeText(this, "Error: Data is NULL", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}