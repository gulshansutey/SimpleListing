package com.technophile.mockyproducts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Size implements Parcelable
{

    @SerializedName("available")
    @Expose
    private Boolean available;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    private Boolean isSelected;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("sku")
    @Expose
    private String sku;
    public final static Creator<Size> CREATOR = new Creator<Size>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Size createFromParcel(Parcel in) {
            return new Size(in);
        }

        public Size[] newArray(int size) {
            return (new Size[size]);
        }

    }
            ;

    protected Size(Parcel in) {
        this.available = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isSelected = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.size = ((String) in.readValue((String.class.getClassLoader())));
        this.sku = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Size() {
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(available);
        dest.writeValue(isSelected);
        dest.writeValue(size);
        dest.writeValue(sku);
    }

    public int describeContents() {
        return 0;
    }

}
