package com.example.root.cleanarchitecturedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.data.UserDataRepository;
import com.example.domain.Post;
import com.example.root.cleanarchitecturedemo.adapter.PostAdapter;
import com.example.root.cleanarchitecturedemo.di.component.ActivityComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerActivityComponent;
import com.example.root.cleanarchitecturedemo.presenter.PostListPresenter;
import com.example.root.cleanarchitecturedemo.view.PostListView;

import java.util.List;

import javax.inject.Inject;

public class UserDetails extends AppCompatActivity implements PostListView {
    String TAG="TAG";
    PostAdapter postAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Inject
    UserDataRepository userDataRepository;
    @Inject
    PostListPresenter postListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        recyclerView=findViewById(R.id.recyclerView2);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int id=Integer.parseInt(getIntent().getStringExtra("userId"));
        Log.e(TAG, "onCreate: "+getIntent().getStringExtra("userId") );

        ActivityComponent activityComponent= DaggerActivityComponent.builder()
                .applicationComponent(DemoApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);
        postListPresenter.getPostList(id);
        postListPresenter.setView(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void renderUser(List<Post> postList) {
        postAdapter=new PostAdapter(postList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

    }
}
