package com.arguablysane.aseplayground.injection.modules;

import com.arguablysane.aseplayground.data.sources.abs.DataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by administrator on 14/9/17.
 */
@Module
public class DataSourceModule {

	private DataSource dataSource;

	public DataSourceModule(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Singleton
	@Provides
	public DataSource providesDataSource() {
		return dataSource;
	}
}
