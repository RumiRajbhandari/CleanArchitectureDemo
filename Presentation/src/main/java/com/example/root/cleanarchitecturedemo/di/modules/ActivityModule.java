package com.example.root.cleanarchitecturedemo.di.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 11/6/17.
 */
@Module
public class ActivityModule {
    private Activity activity;
    @Inject
    public ActivityModule(Activity activity){this.activity=activity;}

    @Provides
    Activity provideContext(){return activity;}
}
