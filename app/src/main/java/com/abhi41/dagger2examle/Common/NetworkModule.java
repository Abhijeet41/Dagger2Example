package com.abhi41.dagger2examle.Common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private String path;

    public NetworkModule(String path) {
        this.path = path;
    }

    @Singleton
    @Provides
    public Gson provideGson()
    {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    //this will create object at compiletime and give reference to retrofit

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

}
