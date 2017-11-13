package com.example.data;

import com.example.data.net.ServiceGenerator;
import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by root on 11/6/17.
 */

public class UserNetDataStore implements UserDataStore {
    private  UserCache userCache;

    UserNetDataStore(UserCache userCache) {
        this.userCache = userCache;
    }
    UserNetDataStore(){}

    @Override
    public Observable<List<User>> userList() {

        return ServiceGenerator.getUserService().getList();
    }

    @Override
    public Observable<List<Post>> postList(int postId) {
        return ServiceGenerator.getUserService().getPosts(postId);
    }

    @Override
    public Observable<List<User>> userEntityDetail(int userId) {
        return this.userList().doOnNext(UserNetDataStore.this.userCache::put);
    }

    @Override
    public Observable<List<Post>> postEntityDetail(int postId) {
        return this.postList(postId).doOnNext(UserNetDataStore.this.userCache::putPost);
    }

/*
    public Observable<List<User>> getUser() {
        return this.userList().doOnNext(UserNetDataStore.this.userCache::put);
    }*/

}
