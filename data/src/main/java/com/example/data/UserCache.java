package com.example.data;

import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by root on 11/9/17.
 */

public interface UserCache
{
    Observable<User> get(final  int userId);
    void put(List<User> user);
    boolean isCached(int id);
    boolean isExpired();
    Observable<List<User>> getAllUser();

}
