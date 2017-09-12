package com.arguablysane.androidsanityessentials.databinding.abs;

import android.databinding.BaseObservable;

/**
 * Created by administrator on 2/7/17.
 */

public class BaseViewModel<DataModel> extends BaseObservable {

	protected DataModel model;

	public BaseViewModel(DataModel dataModel) {
		setModel(dataModel);
	}

	public void setModel(DataModel model) {
		this.model = model;
		notifyChange();
	}

	public DataModel getModel() {
		return model;
	}
}
