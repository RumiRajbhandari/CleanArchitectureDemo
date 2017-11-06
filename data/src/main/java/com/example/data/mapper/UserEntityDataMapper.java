package com.example.data.mapper;

import com.example.data.UserEntity;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/6/17.
 */

public class UserEntityDataMapper {
    public User transform(UserEntity userEntity){
        User user=null;
        if (userEntity!=null){
            user.setId(userEntity.getId());
            user.setUserId(userEntity.getUserId());
            user.setTitle(userEntity.getTitle());
            user.setBody(userEntity.getBody());
        }
        return user;
    }

    public List<User> transform(List<UserEntity> userEntities){
        List<User> userList=new ArrayList<>();
        for (UserEntity userEntity:userEntities
             ) {
            userList.add(transform(userEntity));
        }
        return userList;
    }


}
