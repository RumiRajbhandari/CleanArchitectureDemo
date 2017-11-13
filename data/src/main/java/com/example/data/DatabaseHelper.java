package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.domain.Post;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "evolve";
    private static final String TABLE_USERS = "user";
    private static final String KEY_USERID="userId";
    private static final String KEY_ID="_id";
    private static final String KEY_TITLE="title";
    private static final String KEY_BODY="body";

    private static final String TABLE_POST = "post";
    private static final String KEY_POSTID="postId";
    private static final String KEY_NAME="name";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PBODY="body";

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
        String CREATE_POSTS_TABLE= "CREATE TABLE " + TABLE_POST + "("
                + KEY_POSTID + " INTEGER," + KEY_ID + " INTEGER,"+ KEY_NAME
                + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_PBODY + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_POSTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);

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
    public boolean userAvailable(int userId){
        String userCount="SELECT count(*) FROM "+TABLE_USERS+" WHERE _id = "+userId +";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(userCount,null);
        cursor.moveToFirst();
        if (cursor.getInt(0)>0){
            return true;
        }
       else {
            return false;
        }

    }
    public void addPost(Post post){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_POSTID,post.getPostId());
        values.put(KEY_ID,post.getId());
        values.put(KEY_NAME,post.getName());
        values.put(KEY_EMAIL,post.getEmail());
        values.put(KEY_BODY,post.getBody());

        db.insert(TABLE_POST,null,values);
        db.close();
    }
    public List<Post> getAllPost(int i){
        List<Post> postList=new ArrayList<Post>();
        String selectQuery="SELECT * FROM "+TABLE_POST+" WHERE postId = "+i +";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do{
                Post post=new Post();
                post.setPostId(Integer.parseInt(cursor.getString(0)));
                post.setId(Integer.parseInt(cursor.getString(1)));
                post.setName(cursor.getString(2));
                post.setEmail(cursor.getString(3));
                post.setBody(cursor.getString(4));
                postList.add(post);
            }while (cursor.moveToNext());

        }
        cursor.close();
        return postList;
    }
    public int postExist(int postId){
        String no="SELECT count(*) FROM "+TABLE_POST+" WHERE _id = "+postId +";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(no,null);
        cursor.moveToFirst();
        return cursor.getInt(0) ;

    }

    public void updateUser(User user){
        /*String updateQuery="UPDATE "+TABLE_USERS +"SET "+ KEY_USERID +"= "+user.getUserId()+
                KEY_TITLE +" ="+user.getTitle()+ KEY_BODY +" ="+user.getBody() +" WHERE "+ KEY_ID+" ="+user.getId();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(updateQuery,null)*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USERID,user.getUserId());
        values.put(KEY_BODY,user.getBody());
        values.put(KEY_TITLE,user.getTitle());
        db.update(TABLE_USERS,values,KEY_ID+"="+user.getId(),null);
    }
    public void updatePost(Post post){
        /*String updateQuery="UPDATE "+TABLE_USERS +"SET "+ KEY_USERID +"= "+user.getUserId()+
                KEY_TITLE +" ="+user.getTitle()+ KEY_BODY +" ="+user.getBody() +" WHERE "+ KEY_ID+" ="+user.getId();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(updateQuery,null)*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,post.getName());
        values.put(KEY_EMAIL,post.getEmail());
        values.put(KEY_BODY,post.getBody());
        db.update(TABLE_POST,values,KEY_ID+"="+post.getId(),null);
    }
}
