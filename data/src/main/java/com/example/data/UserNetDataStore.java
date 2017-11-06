package com.example.data;

import android.util.Log;

import com.example.data.net.ServiceGenerator;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/6/17.
 */

public class UserNetDataStore implements UserDataStore {
    @Override
    public Observable<List<User>> userList() {
        return ServiceGenerator.getUserService().getList();
    }

    public Observable<List<User>> getUser(){
        return this.userList();
    }
}
