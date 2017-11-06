package com.example.data.net;

/**
 * Created by root on 11/6/17.
 */

public class ServiceGenerator {
    private static UserService userService;
    public static UserService getUserService(){
        if (userService==null){
            userService=RetrofitHelper.getRetrofit().create(UserService.class);

        }
        return userService;
    }
}
