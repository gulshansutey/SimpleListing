package com.technophile.mockyproducts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    public final static Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("code_color")
    @Expose
    private String codeColor;
    @SerializedName("color_slug")
    @Expose
    private String colorSlug;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("actual_price")
    @Expose
    private String actualPrice;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;
    @SerializedName("installments")
    @Expose
    private String installments;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = new ArrayList<>();

    protected Product(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.style = ((String) in.readValue((String.class.getClassLoader())));
        this.codeColor = ((String) in.readValue((String.class.getClassLoader())));
        this.colorSlug = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.onSale = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.regularPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.actualPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.discountPercentage = ((String) in.readValue((String.class.getClassLoader())));
        this.installments = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.sizes, (Size.class.getClassLoader()));
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }

    public String getColorSlug() {
        return colorSlug;
    }

    public void setColorSlug(String colorSlug) {
        this.colorSlug = colorSlug;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(style);
        dest.writeValue(codeColor);
        dest.writeValue(colorSlug);
        dest.writeValue(color);
        dest.writeValue(onSale);
        dest.writeValue(regularPrice);
        dest.writeValue(actualPrice);
        dest.writeValue(discountPercentage);
        dest.writeValue(installments);
        dest.writeValue(image);
        dest.writeList(sizes);
    }

    public int describeContents() {
        return 0;
    }

}
