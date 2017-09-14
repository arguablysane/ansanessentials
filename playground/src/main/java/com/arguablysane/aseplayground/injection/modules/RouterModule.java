package com.arguablysane.aseplayground.injection.modules;

import com.arguablysane.aseplayground.router.abs.AbsRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by administrator on 14/9/17.
 */
@Module
public class RouterModule {

	private AbsRouter router;

	public RouterModule(AbsRouter router) {
		this.router = router;
	}

	@Singleton
	@Provides
	public AbsRouter providesRouter() {
		return router;
	}
}
