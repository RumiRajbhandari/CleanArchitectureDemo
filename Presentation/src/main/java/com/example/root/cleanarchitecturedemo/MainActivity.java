package com.example.root.cleanarchitecturedemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.data.UserDataRepository;
import com.example.domain.Repository.UserRepository;
import com.example.domain.User;
import com.example.domain.interactor.GetUserListUseCase;
import com.example.root.cleanarchitecturedemo.di.component.ActivityComponent;
import com.example.root.cleanarchitecturedemo.di.component.ApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerActivityComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.modules.ActivityModule;
import com.example.root.cleanarchitecturedemo.di.modules.ApplicaitonModule;
import com.example.root.cleanarchitecturedemo.presenter.UserListPresenter;
import com.example.root.cleanarchitecturedemo.view.UserListview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements UserListview {
    protected ApplicationComponent applicationComponent;
    List<User> users=new ArrayList<>();
    List<User> userList=new ArrayList<>();
    User user;
    String TAG="TAG";
    UserRepository userRepository;
    GetUserListUseCase getUserListUseCase;
    UserListview userListview;
    TextView id,name,address;
    @Inject
    UserDataRepository userDataRepository;
    @Inject
    UserListPresenter userListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*user=new User(1,1,"rumi","ktm");
        users.add(user);*/
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);

//        ActivityComponent activityComponent=DaggerActivityComponent.builder().
//        activityComponent.inject(this);
       ActivityComponent activityComponent= DaggerActivityComponent.builder()
               .applicationComponent(DemoApplication.get(this).getComponent())
               .build();
       activityComponent.inject(this);


//        userDataRepository=new UserDataRepository();
//        getUserListUseCase=new GetUserListUseCase(userDataRepository);

//      UserListPresenter userListPresenter=new UserListPresenter(getUserListUseCase);
        userListPresenter.getUserList();
        userListPresenter.setView(this);

    }

    @Override
    public void showLoading() {
        Log.e(TAG, "showLoading: " );

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
    public void renderUser(User user) {

    }

    @Override
    public void renderUserList(List<User> userCollection) {
        List<User> userList1=new ArrayList<>();
        userList1=(List<User>)userCollection;
        Log.e(TAG, "renderUserList: "+((List<User>) userCollection).get(0).getTitle() );
        id.setText(String.valueOf(userCollection.get(0).getUserId()));
        name.setText(userCollection.get(0).getTitle());
        address.setText(userCollection.get(0).getBody());

    }

    @Override
    public void viewUser(User user) {

    }
}
