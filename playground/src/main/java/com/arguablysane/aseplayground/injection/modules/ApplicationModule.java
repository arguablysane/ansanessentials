package com.arguablysane.aseplayground.injection.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by administrator on 14/9/17.
 */
@Module
public class ApplicationModule {

	private Application application;

	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Singleton
	@Provides
	public Application providesApplication() {
		return application;
	}
}
