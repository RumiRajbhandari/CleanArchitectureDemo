package com.example.data;

import android.util.Log;

import com.example.domain.Post;
import com.example.domain.Repository.UserRepository;
import com.example.domain.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/6/17.
 */

public class UserDataRepository implements UserRepository {
    UserNetDataStore userNetDataStore;
    UserDataStorageFactory userDataStorageFactory;
    @Inject
    public UserDataRepository(UserDataStorageFactory userDataStorageFactory){
        this.userDataStorageFactory=userDataStorageFactory;
//        userNetDataStore=new UserNetDataStore();

    }
    @Override
    public Observable<List<User>> users() {
        Log.e(TAG, "users: data repository" );
        UserDataStore userDataStore=userDataStorageFactory.check();
//        return userNetDataStore.userList();
        return userDataStore.userList();
    }

    @Override
    public Observable<List<User>> getNetData() {
        UserNetDataStore userNetDataStore=new UserNetDataStore();
        return userNetDataStore.userList();
    }

    @Override
    public Observable<List<User>> user(int userId) {
        final UserDataStore userDataStore=userDataStorageFactory.create(userId);
        return userDataStore.userEntityDetail(userId);
    }


    @Override
    public Observable<List<Post>> posts(int id)
    {
        UserDataStore userDataStore=userDataStorageFactory.create(id);
        Log.e(TAG, "user data repository for post" );
        return userDataStore.postEntityDetail(id);
    }

    @Override
    public Observable<List<Post>> getNetDataForPost(int id) {
        UserNetDataStore userNetDataStore=new UserNetDataStore();
        return  userNetDataStore.postList(id);
    }


}
