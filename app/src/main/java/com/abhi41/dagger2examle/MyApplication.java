package com.abhi41.dagger2examle;

import android.app.Application;


import com.abhi41.dagger2examle.Common.NetworkModule;
import com.abhi41.dagger2examle.Common.SharedPrefModule;
import com.abhi41.dagger2examle.Interfaces.Api;
import com.abhi41.dagger2examle.Interfaces.DaggerMyComponent;
import com.abhi41.dagger2examle.Interfaces.MyComponent;


public class MyApplication extends Application {

    private MyComponent networkModule;


    @Override
    public void onCreate() {
        super.onCreate();

        networkModule = DaggerMyComponent.builder()
                .networkModule(new NetworkModule(Api.BASE_URL))
                .sharedPrefModule(new SharedPrefModule(this))
                .build();

    }

    public MyComponent getnetworkModule()
    {
        return networkModule;
    }
}
