package com.example.domain.Repository;

import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by root on 11/2/17.
 */

public interface UserRepository {

    Observable<List<User>> users();
    Observable<List<User>> getNetData();
    Observable<List<User>> user(int userId);
    Observable<List<Post>> posts(int id);
    Observable<List<Post>> getNetDataForPost(int id);

}
