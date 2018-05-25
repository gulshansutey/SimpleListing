package com.technophile.mockyproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technophile.mockyproducts.R;
import com.technophile.mockyproducts.models.Size;

import java.util.ArrayList;
import java.util.List;

public class ProductSizesListAdapter extends RecyclerView.Adapter<ProductSizesListAdapter.VM> {

    private ProductsSizeAdapterListener listener;
    private List<Size> sizeList;
    private Context context;
    private int seletedItemposition = 0;

    public ProductSizesListAdapter(ProductsSizeAdapterListener listener) {
        sizeList = new ArrayList<>();

        this.listener = listener;
    }

    @NonNull
    @Override
    public VM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = View.inflate(context, R.layout.adapter_product_size_list, null);
        return new VM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VM holder, int position) {
        holder.tv_size.setText(sizeList.get(position).getSize());

        if (seletedItemposition == position) {
            holder.tv_size.setBackground(context.getResources().getDrawable(R.drawable.item_size_selected));
            holder.tv_size.setTextColor(context.getResources().getColor(R.color.colorSaleText));
        } else {
            holder.tv_size.setTextColor(context.getResources().getColor(R.color.colorTitle));
            if (sizeList.get(position).getAvailable()) {
                holder.tv_size.setBackground(context.getResources().getDrawable(R.drawable.item_size_normal));
            } else {
                holder.tv_size.setBackground(context.getResources().getDrawable(R.drawable.item_size_disable));

            }
        }

    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    public void setSizeSelected(int pos) {
        seletedItemposition = pos;
        notifyDataSetChanged();
    }

    public void setItems(List<Size> items) {
        sizeList = items;
        for (int i = 0; i < sizeList.size(); i++) {
            if (sizeList.get(i).getAvailable()) {
                seletedItemposition = i;
                break;
            }
        }
    }

    public interface ProductsSizeAdapterListener {
        void onProductSizeSelected(int pos);
    }

    public class VM extends RecyclerView.ViewHolder {
        TextView tv_size;

        public VM(View itemView) {
            super(itemView);
            tv_size = itemView.findViewById(R.id.tv_size);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sizeList.get(getAdapterPosition()).getAvailable())
                        listener.onProductSizeSelected(getAdapterPosition());
                }
            });
        }
    }
}
