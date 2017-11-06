package com.example.root.cleanarchitecturedemo.view.acitvity;

import com.example.domain.User;
import com.example.domain.interactor.GetUserListUseCase;
import com.example.root.cleanarchitecturedemo.presenter.UserListPresenter;
import com.example.root.cleanarchitecturedemo.view.UserListview;

import java.util.Collection;
import java.util.List;

/**
 * Created by root on 11/5/17.
 */

public class UserListActivity implements UserListview {
    UserListPresenter userListPresenter;
    GetUserListUseCase getUserListUseCase;
    public UserListActivity(){}

    public void initComponent( ){
//        getUserListUseCase=new GetUserListUseCase();
//        userListPresenter=new UserListPresenter();

    }
    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void renderUser(User user) {}

    @Override
    public void renderUserList(List<User> userCollection) {

    }

    @Override
    public void viewUser(User user) {

    }
}
