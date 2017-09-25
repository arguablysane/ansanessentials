package com.arguablysane.aseplayground.router;

import android.app.Activity;

import com.arguablysane.aseplayground.activities.form.FormActivity;
import com.arguablysane.aseplayground.router.abs.AbsRouter;

/**
 * Created by administrator on 25/9/17.
 */

public class LegitRouter implements AbsRouter {

	@Override
	public void showFormActivity(Activity activity, long personId) {
		activity.startActivity(FormActivity.makeIntent(activity, personId));
	}
}
