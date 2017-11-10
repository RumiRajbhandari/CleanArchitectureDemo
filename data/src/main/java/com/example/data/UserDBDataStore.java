package com.example.data;

import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by root on 11/9/17.
 */

public class UserDBDataStore implements UserDataStore {
    private final UserCache userCache;

    UserDBDataStore(UserCache userCache) {
        this.userCache = userCache;
    }
    @Override
    public Observable<List<User>> userList() {
        return userCache.getAllUser();
    }

    @Override
    public Observable<List<Post>> post(int id) {
        return null;
    }
}
