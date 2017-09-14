package com.arguablysane.aseplayground.injection.components;

import com.arguablysane.aseplayground.activities.form.FormActivity;
import com.arguablysane.aseplayground.data.sources.SharedPrefDataSource;
import com.arguablysane.aseplayground.injection.modules.ApplicationModule;
import com.arguablysane.aseplayground.injection.modules.DataSourceModule;
import com.arguablysane.aseplayground.injection.modules.EmptyViewManagerModule;
import com.arguablysane.aseplayground.injection.modules.RouterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by administrator on 14/9/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataSourceModule.class, RouterModule.class, EmptyViewManagerModule.class})
public interface AppComponent {
	void inject(FormActivity formActivity);

	void inject(SharedPrefDataSource sharedPrefDataSource);
}
