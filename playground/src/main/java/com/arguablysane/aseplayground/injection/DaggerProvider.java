package com.arguablysane.aseplayground.injection;

import android.app.Application;

import com.arguablysane.aseplayground.data.PlaygroundEmptyViewManager;
import com.arguablysane.aseplayground.data.sources.InMemoryDataSource;
import com.arguablysane.aseplayground.data.sources.abs.AbsDataSource;
import com.arguablysane.aseplayground.injection.components.AppComponent;
import com.arguablysane.aseplayground.injection.components.DaggerAppComponent;
import com.arguablysane.aseplayground.injection.modules.ApplicationModule;
import com.arguablysane.aseplayground.injection.modules.DataSourceModule;
import com.arguablysane.aseplayground.injection.modules.EmptyViewManagerModule;
import com.arguablysane.aseplayground.injection.modules.RouterModule;
import com.arguablysane.aseplayground.router.LegitRouter;

/**
 * Created by administrator on 14/9/17.
 */

public class DaggerProvider {

	private static AppComponent appComponent;

	public DaggerProvider(Application application) {
		AbsDataSource dataSource = new InMemoryDataSource();

		appComponent = DaggerAppComponent.builder()
				.applicationModule(new ApplicationModule(application))
				.routerModule(new RouterModule(new LegitRouter()))
				.emptyViewManagerModule(new EmptyViewManagerModule(new PlaygroundEmptyViewManager()))
				.dataSourceModule(new DataSourceModule(dataSource))
				.build();
	}

	public static AppComponent getAppComponent() {
		return appComponent;
	}
}
