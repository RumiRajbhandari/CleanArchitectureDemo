package com.example.root.cleanarchitecturedemo.view;


import com.example.domain.Post;
import com.example.domain.User;

import java.util.List;

/**
 * Created by root on 11/5/17.
 */

public interface PostListView extends LoadDataView{
    void renderUser(List<Post> postList);
}
