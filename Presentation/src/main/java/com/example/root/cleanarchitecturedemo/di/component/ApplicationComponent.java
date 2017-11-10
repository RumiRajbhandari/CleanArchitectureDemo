package com.example.root.cleanarchitecturedemo.di.component;

import android.content.Context;

import com.example.data.UserCache;
import com.example.domain.Repository.UserRepository;
import com.example.root.cleanarchitecturedemo.DemoApplication;
import com.example.root.cleanarchitecturedemo.MainActivity;
import com.example.root.cleanarchitecturedemo.di.modules.ApplicaitonModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by root on 11/6/17.
 */
@Singleton
@Component(modules = ApplicaitonModule.class)
public interface ApplicationComponent {
    Context context();

    void inject(DemoApplication demoApplication);

    UserRepository userRepository();
    UserCache userCache();
}
