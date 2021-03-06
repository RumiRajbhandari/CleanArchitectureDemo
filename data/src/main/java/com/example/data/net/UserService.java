package com.example.data.net;

import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 11/6/17.
 */

public interface UserService {
@GET("posts")
Observable<List<User>> getList();

@GET("/posts/{id}/comments")
Observable<List<Post>> getPosts(@Path(value = "id", encoded = true) Integer id);
}
