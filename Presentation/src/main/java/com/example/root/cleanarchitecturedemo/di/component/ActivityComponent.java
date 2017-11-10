package com.example.root.cleanarchitecturedemo.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.root.cleanarchitecturedemo.MainActivity;
import com.example.root.cleanarchitecturedemo.UserDetails;
import com.example.root.cleanarchitecturedemo.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by root on 11/6/17.
 */
@PerActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {
//    Activity activity();
    void inject(MainActivity mainActivity);
    void inject(UserDetails userDetails);
}
