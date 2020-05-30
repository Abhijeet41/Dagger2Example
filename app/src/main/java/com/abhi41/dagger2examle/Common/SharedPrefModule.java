package com.abhi41.dagger2examle.Common;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;


import java.util.prefs.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {
    private Context context;

    public SharedPrefModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext()
    {
        return context;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferances(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
