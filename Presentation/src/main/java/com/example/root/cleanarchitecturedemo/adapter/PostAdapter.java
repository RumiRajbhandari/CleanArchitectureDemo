package com.example.root.cleanarchitecturedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.Post;
import com.example.root.cleanarchitecturedemo.R;

import java.util.List;

/**
 * Created by root on 11/7/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    List<Post> postList;

    public PostAdapter(List<Post> postList){
        this.postList=postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_post,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post=postList.get(position);
        holder.id.setText(String.valueOf(post.getId()));
        holder.postId.setText(String.valueOf(post.getPostId()));
        holder.name.setText(post.getName());
        holder.email.setText(post.getEmail());
        holder.body.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id,postId, email, body, name;

        public MyViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            postId=itemView.findViewById(R.id.postId);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            body=itemView.findViewById(R.id.body);
        }
    }
}
