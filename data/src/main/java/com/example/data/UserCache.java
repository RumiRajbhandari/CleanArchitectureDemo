package com.example.data;

import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by root on 11/9/17.
 */

public interface UserCache
{
    Observable<List<User>> get(int userId);
    void put(List<User> user);
    void putPost(List<Post> post);
    boolean isCachedUser(int id);
    boolean isCachedPost(int id);
    boolean isExpired();
    Observable<List<User>> getAllUser();
    Observable<List<Post>> getAllPost(int postId);

}
