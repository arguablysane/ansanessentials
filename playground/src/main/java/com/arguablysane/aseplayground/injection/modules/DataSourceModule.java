package com.arguablysane.aseplayground.injection.modules;

import com.arguablysane.aseplayground.data.sources.abs.AbsDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by administrator on 14/9/17.
 */
@Module
public class DataSourceModule {

	private AbsDataSource dataSource;

	public DataSourceModule(AbsDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Singleton
	@Provides
	public AbsDataSource providesDataSource() {
		return dataSource;
	}
}
