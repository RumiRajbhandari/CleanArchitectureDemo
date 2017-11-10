package com.example.data;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by root on 11/9/17.
 */

public class UserDataStorageFactory {
    private final Context context;
    private final UserCache userCache;

    @Inject
    UserDataStorageFactory(@NonNull Context context, @NonNull UserCache userCache) {
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }
    public UserDataStore create(int userId) {
        UserDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
            userDataStore = new UserDBDataStore(this.userCache);
        } else {
            userDataStore=createCloudDataStore();
        }

        return userDataStore;
    }
    public UserDataStore check() {
        UserDataStore userDataStore;

        if (!this.userCache.isExpired()) {
            userDataStore = new UserDBDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    public UserDataStore createCloudDataStore() {
        final UserNetDataStore userNetDataStore = new UserNetDataStore(this.userCache);

        return userNetDataStore;
    }
}
