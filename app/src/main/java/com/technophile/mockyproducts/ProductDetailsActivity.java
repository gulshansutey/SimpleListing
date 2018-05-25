package com.technophile.mockyproducts;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technophile.mockyproducts.adapter.ProductSizesListAdapter;
import com.technophile.mockyproducts.models.Product;

public class ProductDetailsActivity extends AppCompatActivity implements ProductSizesListAdapter.ProductsSizeAdapterListener {

    private ProductSizesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Product product = getIntent().getParcelableExtra("data");
        RecyclerView rv_product_sizes = findViewById(R.id.rv_product_sizes);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_price = findViewById(R.id.tv_price);
        TextView tv_sale_label = findViewById(R.id.tv_sale_label);
        ImageView iv_product_img = findViewById(R.id.iv_product_img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_product_sizes.setLayoutManager(linearLayoutManager);
        adapter = new ProductSizesListAdapter(this);
        adapter.setItems(product.getSizes());
        rv_product_sizes.setAdapter(adapter);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(product.getName());
        }
        tv_name.setText(product.getName());
        tv_price.setText(product.getActualPrice());

        if (product.getOnSale()) {
            tv_sale_label.setVisibility(View.VISIBLE);
            String label = "Sale " + product.getDiscountPercentage() + " off";
            tv_sale_label.setText(label);
        } else {
            tv_sale_label.setVisibility(View.GONE);
        }
        Glide.with(this).load(product.getImage()).placeholder(R.drawable.ic_launcher_background).into(iv_product_img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    @Override
    public void onProductSizeSelected(int pos) {
        adapter.setSizeSelected(pos);
    }
}
