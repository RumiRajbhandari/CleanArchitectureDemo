package com.example.data;

import android.content.Context;
import android.util.Log;

import com.example.domain.Post;
import com.example.domain.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/9/17.
 */

public class UserCacheImpl implements UserCache {
    DatabaseHelper databaseHelper;
    List<User> userList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 100;

    private final Context context;
    private final FileManager fileManager;

    @Inject
    UserCacheImpl(Context context, FileManager fileManager, DatabaseHelper databaseHelper) {
        this.context = context;
        this.fileManager = fileManager;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Observable<List<User>> getAllUser() {
        userList = databaseHelper.getAllUser();
//        Log.e(TAG, "getAllUser: 0" + userList.get(0).toString());

        return Observable.just(userList);
    }

    @Override
    public Observable<List<Post>> getAllPost(int postId) {
        postList = databaseHelper.getAllPost(postId);
//        Log.e(TAG, "getAllPost: UCI"+postList.get(0).toString() );
        return Observable.just(postList);
    }

    @Override
    public Observable<List<User>> get(int userId) {

        return Observable.just(databaseHelper.getAllUser());
    }

    @Override
    public void put(List<User> users) {
        if (users != null) {
            Log.e(TAG, "put: add all user uci");
            //databaseHelper.addAllUser(users);
            setLastCacheUpdateTimeMillis();
            for (User user : users
                    ) {
                if (!isCachedUser(user.getId())) {
                    databaseHelper.addUser(user);
                    setLastCacheUpdateTimeMillis();
                }
                else {
                    //TODO db.update
                    databaseHelper.updateUser(user);
                    setLastCacheUpdateTimeMillis();
                }
            }
        }
    }

    @Override
    public void putPost(List<Post> postList) {
        if (postList != null) {
            Log.e(TAG, "put: add all post uci");
            //databaseHelper.addAllUser(users);
            setLastCacheUpdateTimeMillis();
            for (Post post : postList
                    ) {
                Log.e(TAG, "putPost: is cached?"+isCachedPost(post.getId()) );
                if (!isCachedPost(post.getId())) {
                    Log.e(TAG, "putPost: post not cached" );
                    databaseHelper.addPost(post);
                    setLastCacheUpdateTimeMillis();
                }
                else {
                    Log.e(TAG, "putPost: post cached" );
                    databaseHelper.updatePost(post);
                    setLastCacheUpdateTimeMillis();
                }
            }
        }
    }

    @Override
    public boolean isCachedUser(int id) {
        return databaseHelper.userAvailable(id);

    }

    @Override
    public boolean isCachedPost(int id) {
        int no=databaseHelper.postExist(id);
        if (no>0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);
        Log.e(TAG, "isExpired: date is" + expired);

        if (expired) {
            Log.e(TAG, "isExpired:2 ");
//            return true;
        }

        return expired;
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }


}
