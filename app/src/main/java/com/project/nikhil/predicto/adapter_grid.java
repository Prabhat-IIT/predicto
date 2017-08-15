package com.project.nikhil.predicto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nikhil on 9/8/17.
 */

public class adapter_grid extends RecyclerView.Adapter<adapter_grid.ViewHolder> {
    public ArrayList<grid_object> mdatas;
    ViewGroup x;
    Context context,activity;

    public adapter_grid(ArrayList<grid_object> myDataset,Context cxt) {
        mdatas = myDataset;
        activity=cxt;
    }
    @Override
    public adapter_grid.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        x=parent;
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_object,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(adapter_grid.ViewHolder holder, int position) {
       final grid_object o1=mdatas.get(position);
        holder.title.setText(o1.getName());
        holder.images.setImageResource(o1.getImage());

        holder.setOnItemClickListener(new MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i=new Intent(activity,o1.getAclass());
                activity.startActivity(i);
           }
        });
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView images;
        private MyClickListener myClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.grid_object_title);
            images=(ImageView)itemView.findViewById(R.id.grid_object_image);
            itemView.setOnClickListener(this);

            context=itemView.getContext();

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
        }
        public void setOnItemClickListener(MyClickListener myClickListener) {
            this.myClickListener = myClickListener;
        }
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
