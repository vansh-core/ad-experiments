package com.example.exp3; // Change this to your actual package name

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<ItemModel> itemList;

    // Constructor
    public MyAdapter(List<ItemModel> itemList) {
        this.itemList = itemList;
    }

    // ViewHolder class to hold the view references
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvItemName);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemModel item = itemList.get(position);
        holder.tvName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}