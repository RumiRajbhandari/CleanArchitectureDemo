package com.example.root.cleanarchitecturedemo.di.modules;

import android.content.Context;

import com.example.data.UserDataRepository;
import com.example.domain.Repository.UserRepository;
import com.example.root.cleanarchitecturedemo.DemoApplication;
import com.example.root.cleanarchitecturedemo.presenter.UserListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 11/6/17.
 */
@Module
public class ApplicaitonModule {
    private final DemoApplication application;

    public ApplicaitonModule(DemoApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
    @Provides
    UserRepository getUserRepository(UserDataRepository userDataRepository){
        return userDataRepository;
    }

}
