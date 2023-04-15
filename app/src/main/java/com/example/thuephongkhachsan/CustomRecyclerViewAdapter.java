package com.example.thuephongkhachsan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<PostsViewHolder> {


    private List<Posts> posts;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public CustomRecyclerViewAdapter(Context context, List<Posts> datas ) {
        this.context = context;
        this.posts = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PostsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View recyclerViewItem = mLayoutInflater.inflate(R.layout.recyclerview_item_layout, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new PostsViewHolder(recyclerViewItem);
    }


    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {

        Posts baiviets = this.posts.get(position);

        int imageResId = this.getDrawableResIdByName(baiviets.getAnhbaiviet());
        holder.anhbaiviet.setImageResource(imageResId);
        holder.baiviet.setText(baiviets.getBaiviet() );
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i(MainActivity.LOG_TAG, "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }


    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Posts baiviets  = this.posts.get(itemPosition);

        Toast.makeText(this.context, baiviets.getBaiviet(), Toast.LENGTH_LONG).show();
    }
}
