package com.example.exp5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Adapter connects data to RecyclerView
// Must extend RecyclerView.Adapter with our ViewHolder type
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<String> nameList;

    // Constructor
    public MyAdapter(Context context, List<String> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    // STEP A: onCreateViewHolder — inflate item layout, create ViewHolder
    // Called when RecyclerView needs a NEW ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    // STEP B: onBindViewHolder — fill data into ViewHolder
    // Called for EVERY visible item on screen
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = nameList.get(position);
        holder.tvName.setText(name);
        holder.tvRollNo.setText(String.valueOf(position + 1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked: " + name,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // STEP C: getItemCount — return total number of items
    @Override
    public int getItemCount() {
        return nameList.size();
    }

    // ViewHolder — stores references to views of ONE list item
    // Avoids calling findViewById() repeatedly = performance boost
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRollNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName   = itemView.findViewById(R.id.tvName);
            tvRollNo = itemView.findViewById(R.id.tvRollNo);
        }
    }
}
