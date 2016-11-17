package ru.bizapp.kuponandroid;

import android.content.Context;

import ru.bizapp.kuponandroid.di.component.AppComponent;
import ru.bizapp.kuponandroid.di.component.DaggerAppComponent;
import ru.bizapp.kuponandroid.di.module.AppModule;

/**
 * Created by Majo on 17.11.2016.
 */

public class Application extends android.app.Application{

    private AppComponent mApplicationComponent;

    @Override
    public void onCreate() {
        mApplicationComponent = createComponent();

    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((Application)context.getApplicationContext()).mApplicationComponent;
    }
}
