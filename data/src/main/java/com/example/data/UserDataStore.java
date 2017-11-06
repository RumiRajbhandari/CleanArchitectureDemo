package com.example.data;

import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by root on 11/6/17.
 */

public interface UserDataStore {
    Observable<List<User>> userList();
}
