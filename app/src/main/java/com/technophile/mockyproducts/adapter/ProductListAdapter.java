package com.technophile.mockyproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technophile.mockyproducts.R;
import com.technophile.mockyproducts.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.VM> implements Filterable {


    private List<Product> productList;
    private List<Product> productListFiltered;
    private ProductsAdapterListener productsAdapterListener;
    private Context context;

    public ProductListAdapter(ProductsAdapterListener productsAdapterListener) {
        productList = new ArrayList<>();
        this.productsAdapterListener = productsAdapterListener;
        productListFiltered = new ArrayList<>();
    }

    @NonNull
    @Override
    public VM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = View.inflate(context, R.layout.adapter_product_list, null);
        return new VM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VM holder, int position) {
        Glide.with(context).load(productListFiltered.get(position).getImage())
                .placeholder(R.drawable.ic_launcher_background).into(holder.iv_product_img);
        holder.tv_name.setText(productListFiltered.get(position).getName());
        holder.tv_price.setText(productListFiltered.get(position).getActualPrice());

        if (productListFiltered.get(position).getOnSale()) {
            holder.tv_sale_label.setVisibility(View.VISIBLE);
            String label = "Sale " + productListFiltered.get(position).getDiscountPercentage() + " off";
            holder.tv_sale_label.setText(label);
        } else {
            holder.tv_sale_label.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return productListFiltered.size();
    }

    public void setProducts(List<Product> products) {
        productListFiltered = products;
        productList = products;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    productListFiltered = productList;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product row : productList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    productListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = productListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productListFiltered = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface ProductsAdapterListener {
        void onItemSelected(Product product);
    }

    public class VM extends RecyclerView.ViewHolder {
        ImageView iv_product_img;
        TextView tv_name, tv_price, tv_sale_label;

        public VM(View itemView) {
            super(itemView);

            iv_product_img = itemView.findViewById(R.id.iv_product_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_sale_label = itemView.findViewById(R.id.tv_sale_label);
            tv_price = itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productsAdapterListener.onItemSelected(productListFiltered.get(getAdapterPosition()));
                }
            });
        }


    }


}
