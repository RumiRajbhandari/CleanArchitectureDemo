package com.example.root.cleanarchitecturedemo.presenter;

import android.app.job.JobScheduler;
import android.util.Log;

import com.example.domain.User;
import com.example.domain.interactor.GetUserListUseCase;
import com.example.root.cleanarchitecturedemo.view.UserListview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/5/17.
 */

public class UserListPresenter implements Presenter {
    private final GetUserListUseCase getUserListUseCase;
    private UserListview userListview;
    List<User> userList=new ArrayList<>();


    @Inject
    public UserListPresenter(GetUserListUseCase getUserListUseCase){
        this.getUserListUseCase=getUserListUseCase;
    }
    public void setView(UserListview view){
        this.userListview=view;
    }

    public void getUserList(){
        this.getUserListUseCase.execute(new DisposableObserver<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                Log.e(TAG, "onNext: >>>>>>>executBoeth" );
                showUserList(users);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );
            }
        },null);
    }

    public void showUserList(List<User> users){
        if (userListview!=null){
            userListview.renderUserList(users);
        }

    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

}
