package com.example.domain.interactor;

import android.support.v4.util.Preconditions;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by root on 11/2/17.
 */

public abstract class   UseCase<T,Params>{
    private final CompositeDisposable disposables;

    UseCase() {
        this.disposables = new CompositeDisposable();
    }
    abstract Observable<T> buildUseCaseObservable(Params params );

    public void execute(DisposableObserver<T> observer,Params params){
        final  Observable<T> observable=this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(observer));
    }
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }


}
