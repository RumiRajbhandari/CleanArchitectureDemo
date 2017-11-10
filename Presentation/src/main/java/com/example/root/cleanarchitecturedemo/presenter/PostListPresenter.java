package com.example.root.cleanarchitecturedemo.presenter;

import android.util.Log;

import com.example.domain.Post;
import com.example.domain.interactor.GetPostUseCase;
import com.example.root.cleanarchitecturedemo.view.PostListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/7/17.
 */

public class PostListPresenter {
    private final GetPostUseCase getPostUseCase;
    private PostListView postListView;

    @Inject
    public PostListPresenter(GetPostUseCase getPostUseCase){
        this.getPostUseCase=getPostUseCase;
    }

    public void setView(@NonNull PostListView postListView){
        this.postListView=postListView;
    }

    public void getPostList(int id){
        this.getPostUseCase.execute(new DisposableObserver<List<Post>>() {
            @Override
            public void onNext(List<Post> postList) {
                Log.e(TAG, "onNext: "+postList.get(0).getBody() );
                showPostList(postList);

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e );

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );

            }
        }, GetPostUseCase.Params.forPost(id));
    }

    public void showPostList(List<Post> postList){
        if (postListView!=null){
            postListView.renderUser(postList);
        }
    }

}
