package com.example.data;

import android.util.Log;

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
    @Inject
    public UserDataRepository(){
        userNetDataStore=new UserNetDataStore();

    }
    @Override
    public Observable<List<User>> users() {
        Log.e(TAG, "users: data repository" );
        return userNetDataStore.userList();
    }

    @Override
    public Observable<User> user(int userId) {
        return null;
    }
}
