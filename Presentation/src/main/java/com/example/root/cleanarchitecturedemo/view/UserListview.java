package com.example.root.cleanarchitecturedemo.view;

import com.example.domain.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by root on 11/5/17.
 */

public interface UserListview extends UserDetailsView{
    void renderUserList(List<User> userCollection);
    void viewUser(User user);
}
