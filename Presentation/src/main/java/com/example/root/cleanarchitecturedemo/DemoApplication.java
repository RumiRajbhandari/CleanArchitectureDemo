package com.example.root.cleanarchitecturedemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.root.cleanarchitecturedemo.di.component.ApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.modules.ApplicaitonModule;


/**
 * Created by root on 11/6/17.
 */

public class DemoApplication extends Application{
    protected ApplicationComponent applicationComponent;
    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent= DaggerApplicationComponent.builder().applicaitonModule(new ApplicaitonModule(this)).build();
        applicationComponent.inject(this);


    }
    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
