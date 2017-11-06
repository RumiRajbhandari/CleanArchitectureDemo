package com.example.root.cleanarchitecturedemo.view;


import com.example.domain.User;

/**
 * Created by root on 11/5/17.
 */

public interface UserDetailsView extends LoadDataView{
    void renderUser(User user);
}
