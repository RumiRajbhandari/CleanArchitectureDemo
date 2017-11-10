package com.example.root.cleanarchitecturedemo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.UserDataRepository;
import com.example.domain.User;
import com.example.root.cleanarchitecturedemo.adapter.ViewAdapter;
import com.example.root.cleanarchitecturedemo.di.component.ActivityComponent;
import com.example.root.cleanarchitecturedemo.di.component.ApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerActivityComponent;
import com.example.root.cleanarchitecturedemo.presenter.UserListPresenter;
import com.example.root.cleanarchitecturedemo.view.UserListview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements UserListview, SwipeRefreshLayout.OnRefreshListener {
    protected ApplicationComponent applicationComponent;
    List<User> users=new ArrayList<>();
    List<User> userList=new ArrayList<>();
    String TAG="TAG";
    TextView id,name,address;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    @Inject
    UserDataRepository userDataRepository;
    @Inject
    UserListPresenter userListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        toolbar=findViewById(R.id.toolbar);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
*/
       ActivityComponent activityComponent= DaggerActivityComponent.builder()
               .applicationComponent(DemoApplication.get(this).getComponent())
               .build();
       activityComponent.inject(this);
        userListPresenter.getUserList();
        userListPresenter.setView(this);

    }




    @Override
    public void renderUserList(List<User> userCollection) {
        List<User> userList1=new ArrayList<>();
        userList1=(List<User>)userCollection;


        viewAdapter=new ViewAdapter(getApplicationContext(),userList1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),R.drawable.divider));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.setAdapter(viewAdapter);

    }

    @Override
    public void viewUser(User user) {

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
    public void onRefresh() {
        Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
        userListPresenter.getUserList();
        userListPresenter.setView(this);

    }
}
