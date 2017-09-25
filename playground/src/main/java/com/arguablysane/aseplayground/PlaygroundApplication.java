package com.arguablysane.aseplayground;

import android.app.Application;

import com.arguablysane.aseplayground.injection.DaggerProvider;

/**
 * Created by administrator on 14/9/17.
 */

public class PlaygroundApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Just initialising things here
		new DaggerProvider(this);
	}
}
