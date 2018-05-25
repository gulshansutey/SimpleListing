package com.technophile.mockyproducts.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Products implements Parcelable
{

    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<>();
    public final static Creator<Products> CREATOR = new Creator<Products>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        public Products[] newArray(int size) {
            return (new Products[size]);
        }

    }
            ;

    protected Products(Parcel in) {
        in.readList(this.products, ( Product.class.getClassLoader()));
    }

    public Products() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(products);
    }

    public int describeContents() {
        return 0;
    }

}


