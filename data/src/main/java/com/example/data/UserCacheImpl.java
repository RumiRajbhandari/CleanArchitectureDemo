package com.example.data;

import android.content.Context;
import android.util.Log;

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
    List<User> userList=new ArrayList<>();
    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final FileManager fileManager;

    @Inject
    UserCacheImpl(Context context,FileManager fileManager,DatabaseHelper databaseHelper){
        this.context=context;
        this.fileManager=fileManager;
        this.databaseHelper=databaseHelper;
    }
    @Override
    public Observable<List<User>> getAllUser() {
        userList=databaseHelper.getAllUser();

        return Observable.just(userList);
    }
    @Override
    public Observable<User> get(int userId) {
        return null;
    }

    @Override
    public void put(List<User> user) {
        if (user != null) {
            databaseHelper.addAllUser(user);
            setLastCacheUpdateTimeMillis();
            /*if (!isCached(user.getUserId())) {
                databaseHelper.addUser(user);
                setLastCacheUpdateTimeMillis();
            }*/
        }
    }



    @Override
    public boolean isCached(int id) {
        return false;
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            Log.e(TAG, "isExpired: " );
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
