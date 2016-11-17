package ru.bizapp.kuponandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bizapp.kuponandroid.Application;

/**
 * Created by Majo on 05.10.2016.
 */

@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application mApp) {
        this.mApp = mApp;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return mApp.getApplicationContext();
    }


}
