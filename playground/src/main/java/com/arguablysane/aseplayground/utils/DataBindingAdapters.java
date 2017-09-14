package com.arguablysane.aseplayground.utils;

import android.databinding.BindingAdapter;

import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;
import com.arguablysane.androidsanityessentials.emptyview.EmptyViewWidget;

/**
 * Created by administrator on 14/9/17.
 */

public class DataBindingAdapters {

	@BindingAdapter("emptyViewState")
	public static void setEmptyViewState(EmptyViewWidget emptyViewWidget, EmptyViewState emptyViewState) {
		emptyViewWidget.setState(emptyViewState);
	}
}
