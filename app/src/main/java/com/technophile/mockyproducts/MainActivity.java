package com.technophile.mockyproducts;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.technophile.mockyproducts.adapter.ProductListAdapter;
import com.technophile.mockyproducts.models.Product;
import com.technophile.mockyproducts.models.Products;
import com.technophile.mockyproducts.network.ApiFactory;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ProductListAdapter.ProductsAdapterListener {
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv_products_list = findViewById(R.id.rv_products_list);
        rv_products_list.setLayoutManager(new LinearLayoutManager(this));
        productListAdapter = new ProductListAdapter(this);
        rv_products_list.setAdapter(productListAdapter);
        progressBar = findViewById(R.id.progressBar);
        fetchProducts();
    }


    private void fetchProducts() {
        progressBar.setVisibility(View.VISIBLE);
        ApiFactory.create().fetchProducts("59b6a65a0f0000e90471257d")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Products>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Products products) {
                        progressBar.setVisibility(View.GONE);
                        productListAdapter.setProducts(products.getProducts());

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (searchManager != null)
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                productListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                productListAdapter.getFilter().filter(query);
                return false;
            }

        });
        return true;
    }

    @Override
    public void onItemSelected(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("data", product);
        startActivity(intent);
    }
}
