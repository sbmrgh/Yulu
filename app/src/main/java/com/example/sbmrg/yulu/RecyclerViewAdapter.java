package com.example.sbmrg.yulu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> Mlocation = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mTitle, ArrayList<String> mImage, ArrayList<String> mlocation, Context mContext) {
        this.mTitle = mTitle;
        this.mImage = mImage;
        Mlocation = mlocation;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(i))
                .into(viewHolder.imageView);
        viewHolder.title.setText(mTitle.get(i));
        viewHolder.location.setText(Mlocation.get(i));
    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title,location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);

        }
    }
}
