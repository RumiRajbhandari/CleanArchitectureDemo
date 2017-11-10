package com.example.data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by root on 11/9/17.
 */

public class FileManager {

    @Inject
    FileManager(){}

    void writeToPreferences(Context context, String preferenceFileName, String key,
                            long value) {

        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }
    long getFromPreferences(Context context, String preferenceFileName, String key) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }
}
