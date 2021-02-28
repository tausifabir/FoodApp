package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Model.StaticRVModel;
import com.example.foodapp.R;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticViewHolder> {

    private Context context;
    private ArrayList<StaticRVModel> items;


    public StaticRvAdapter(Context context, ArrayList<StaticRVModel> items) {
        this.context = context;
        this.items = items;
    }

    int index_row = -1;

   /* public StaticRvAdapter(ArrayList<StaticRVModel> items) {
        this.items = items;
    }*/

    @NonNull
    @Override
    public StaticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staticrecycler_row,parent,false);
        StaticViewHolder staticViewHolder = new StaticViewHolder(view);
        return staticViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticViewHolder holder, final int position) {


        holder.image.setImageResource(items.get(position).getImage());
        holder.text.setText(items.get(position).getText());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index_row = position;
                notifyDataSetChanged();
            }
        });

        if (index_row == position){
            holder.linearLayout.setBackgroundResource(R.drawable.rv_background);
        }else {
            holder.linearLayout.setBackgroundResource(R.drawable.selected_recyclerview);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class StaticViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView text;
        private LinearLayout linearLayout;

        public StaticViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.staticRvImage);
            text = itemView.findViewById(R.id.staticRvText);
            linearLayout = itemView.findViewById(R.id.linearRV);
        }
    }
}
