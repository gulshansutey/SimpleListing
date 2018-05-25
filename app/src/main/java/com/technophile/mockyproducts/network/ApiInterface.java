package com.technophile.mockyproducts.network;


import com.technophile.mockyproducts.models.Products;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiInterface {

    @GET
    Observable<Products> fetchProducts(@Url String url);

}
