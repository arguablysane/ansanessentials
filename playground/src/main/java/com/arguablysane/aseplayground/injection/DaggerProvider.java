package com.arguablysane.aseplayground.injection;

import com.arguablysane.aseplayground.data.sources.SharedPrefDataSource;
import com.arguablysane.aseplayground.injection.components.AppComponent;
import com.arguablysane.aseplayground.injection.components.DaggerAppComponent;
import com.arguablysane.aseplayground.injection.modules.DataSourceModule;

/**
 * Created by administrator on 14/9/17.
 */

public class DaggerProvider {

	private static AppComponent appComponent;

	public DaggerProvider() {
		appComponent = DaggerAppComponent.builder()
				.dataSourceModule(new DataSourceModule(new SharedPrefDataSource()))
				.build();
	}

	public static AppComponent getAppComponent() {
		return appComponent;
	}
}
