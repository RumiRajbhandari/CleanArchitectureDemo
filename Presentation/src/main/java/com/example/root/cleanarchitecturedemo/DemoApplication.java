package com.example.root.cleanarchitecturedemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.root.cleanarchitecturedemo.di.component.ApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.component.DaggerApplicationComponent;
import com.example.root.cleanarchitecturedemo.di.modules.ApplicaitonModule;
import com.facebook.stetho.Stetho;


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
        Stetho.InitializerBuilder builder= Stetho.newInitializerBuilder(this);
        builder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        builder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));
        Stetho.Initializer initializer=builder.build();
        Stetho.initialize(initializer);
        applicationComponent= DaggerApplicationComponent.builder().applicaitonModule(new ApplicaitonModule(this)).build();
        applicationComponent.inject(this);


    }
    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
