package com.abhi41.dagger2examle.Interfaces;


import android.content.SharedPreferences;

import com.abhi41.dagger2examle.Common.NetworkModule;
import com.abhi41.dagger2examle.Common.SharedPrefModule;
import com.abhi41.dagger2examle.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class, NetworkModule.class})
public interface MyComponent {
    void inject(MainActivity activity);

}
