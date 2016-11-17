package ru.bizapp.kuponandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.bizapp.kuponandroid.di.module.AppModule;

/**
 * Created by Majo on 05.10.2016.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

}
