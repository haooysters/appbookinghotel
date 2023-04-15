package com.example.thuephongkhachsan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsViewHolder extends   RecyclerView.ViewHolder {

    ImageView anhbaiviet;
    TextView baiviet;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.anhbaiviet = (ImageView) itemView.findViewById(R.id.img_baiviet);
        this.baiviet = (TextView) itemView.findViewById(R.id.txt_baiviet);
    }
}
