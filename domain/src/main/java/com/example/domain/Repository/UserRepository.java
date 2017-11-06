package com.example.domain.Repository;

import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by root on 11/2/17.
 */

public interface UserRepository {

    Observable<List<User>> users();
    Observable<User> user(final int userId);
}
