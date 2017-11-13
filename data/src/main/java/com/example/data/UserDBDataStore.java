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
    public Observable<List<Post>> postList(int postId) {
        return userCache.getAllPost(postId);
    }

    @Override
    public Observable<List<User>> userEntityDetail(int userId) {
        return this.userCache.get(userId);
    }

    @Override
    public Observable<List<Post>> postEntityDetail(int postId) {
        return this.userCache.getAllPost(postId);
    }
}
