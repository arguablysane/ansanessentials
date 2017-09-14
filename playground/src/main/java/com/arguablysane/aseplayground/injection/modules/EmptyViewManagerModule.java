package com.arguablysane.aseplayground.injection.modules;

import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by administrator on 14/9/17.
 */
@Module
public class EmptyViewManagerModule {

	private EmptyViewManager emptyViewManager;

	public EmptyViewManagerModule(EmptyViewManager emptyViewManager) {
		this.emptyViewManager = emptyViewManager;
	}

	@Singleton
	@Provides
	public EmptyViewManager providesEmptyViewManager() {
		return emptyViewManager;
	}
}
