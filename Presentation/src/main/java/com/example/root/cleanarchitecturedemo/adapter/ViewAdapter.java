package com.example.root.cleanarchitecturedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.User;
import com.example.root.cleanarchitecturedemo.R;
import com.example.root.cleanarchitecturedemo.UserDetails;

import java.util.List;

/**
 * Created by root on 11/7/17.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    List<User> userList;
    Context context;
    public ViewAdapter(Context context,List<User> userList){
        this.context=context;
        this.userList=userList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user=userList.get(position);
        holder.id.setText(String.valueOf(user.getId()));
        holder.userId.setText(String.valueOf(user.getUserId()));
        holder.title.setText(user.getTitle());
        holder.body.setText(user.getBody());


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id,userId,title,body;

        public MyViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            userId=itemView.findViewById(R.id.userid);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,UserDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("userId",id.getText());
                    context.startActivity(intent);
                }
            });
        }
    }
}
