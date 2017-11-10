package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by root on 11/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "evolve";
    private static final String TABLE_USERS = "user";
    private static final String KEY_USERID="userId";
    private static final String KEY_ID="_id";
    private static final String KEY_TITLE="title";
    private static final String KEY_BODY="body";

    @Inject
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE= "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERID + " INTEGER," + KEY_ID + " INTEGER,"+ KEY_TITLE
                + " TEXT," + KEY_BODY + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    }
    public void addUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USERID,user.getUserId());
        values.put(KEY_ID,user.getId());
        values.put(KEY_TITLE,user.getTitle());
        values.put(KEY_BODY,user.getBody());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }
    public void addAllUser(List<User> userList){
        SQLiteDatabase db=this.getWritableDatabase();
        for (User user:userList
                ) {
            ContentValues values=new ContentValues();
            values.put(KEY_USERID,user.getUserId());
            values.put(KEY_ID,user.getId());
            values.put(KEY_TITLE,user.getTitle());
            values.put(KEY_BODY,user.getBody());
            db.insert(TABLE_USERS,null,values);

        }

        db.close();
    }
    public List<User> getAllUser(){
        List<User> userList=new ArrayList<User>();
        String selectQuery="SELECT * FROM "+TABLE_USERS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do{
                User user=new User();
                user.setUserId(Integer.parseInt(cursor.getString(0)));
                user.setId(Integer.parseInt(cursor.getString(1)));
                user.setTitle(cursor.getString(2));
                user.setBody(cursor.getString(3));
                userList.add(user);
            }while (cursor.moveToNext());

        }
        cursor.close();
        return userList;
    }
    public boolean userAvailable(){
        String userCount="SELECT count(*) FROM "+TABLE_USERS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(userCount,null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.moveToFirst();
//        Log.e(TAG, "userAvailable:cursor "+cursor.getLong(0) );
        if ((int)cursor.getLong(0)==0){
            return false;
        }
        else return true;

    }
}
