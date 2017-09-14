package com.arguablysane.androidsanityessentials.databinding.abs;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.arguablysane.androidsanityessentials.BR;
import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManagerCallback;

/**
 * Created by administrator on 7/7/17.
 */

public abstract class BasePageViewModel extends BaseObservable implements BasePageContract.ViewModel {

	protected EmptyViewManager emptyViewManager;
	protected int dataState = EmptyViewManager.STATE_LOADING;
	private Throwable dataException = null;
	private boolean isLoading = false;
	private EmptyViewManagerCallback emptyViewManagerCallback;

	public BasePageViewModel(EmptyViewManager emptyViewManager) {
		this.emptyViewManager = emptyViewManager;
	}

	public void setEmptyViewManager(EmptyViewManager emptyViewManager) {
		this.emptyViewManager = emptyViewManager;
	}

	@Override
	public void setEmptyViewManagerCallback(EmptyViewManagerCallback emptyViewManagerCallback) {
		this.emptyViewManagerCallback = emptyViewManagerCallback;
	}

	@Bindable
	public int getDataState() {
		return dataState;
	}

	public void setDataLoading() {
		isLoading = true;
		dataException = null;
		dataState = EmptyViewManager.STATE_LOADING;
		notifyPropertyChanges();
	}

	public void setDataOK() {
		isLoading = false;
		dataException = null;
		dataState = EmptyViewManager.STATE_OK;
		notifyPropertyChanges();
	}

	public void setDataError(Throwable dataException) {
		isLoading = false;
		dataState = EmptyViewManager.STATE_ERROR;
		this.dataException = dataException;
		notifyPropertyChanges();
	}

	private void notifyPropertyChanges() {
		notifyPropertyChanged(BR.dataState);
		notifyPropertyChanged(BR.emptyViewState);
		notifyPropertyChanged(BR.emptyViewVisibility);
		notifyPropertyChanged(BR.contentLayoutVisibility);
	}

	public Throwable getDataException() {
		return this.dataException;
	}

	public boolean isLoading() {
		return isLoading;
	}

	/**
	 * Abstract method that informs this class whether to show the emptyview/contentLayout or not
	 * @return true if data is empty
	 */
	public abstract boolean isDataEmpty();

	/**
	 * Returns the visibility value for the emptyview
	 * @return one of {@link View#VISIBLE}, {@link View#INVISIBLE}, {@link View#GONE}
	 */
	@Bindable
	public int getEmptyViewVisibility() {
		return isDataEmpty() ? View.VISIBLE : View.GONE;
	}

	/**
	 * Returns the visibility value for the content layout
	 * @return one of {@link View#VISIBLE}, {@link View#INVISIBLE}, {@link View#GONE}
	 */
	@Bindable
	public int getContentLayoutVisibility() {
		return isDataEmpty() ? View.GONE : View.VISIBLE;
	}

	/**
	 * Returns the configuration for the emptyview
	 * @return EmptyView configuration from the emptyViewManager or any custom state
	 */
	@Bindable
	public EmptyViewState getEmptyViewState() {
		EmptyViewState emptyViewState = null;
		if(emptyViewManagerCallback != null) {
			emptyViewState = emptyViewManagerCallback.getEmptyViewState(dataState);
		}

		if(emptyViewState == null) {
			emptyViewState =emptyViewManager.getEmptyViewState(dataState, dataException);
		}

		if(emptyViewState != null) {
			switch (dataState) {
				case EmptyViewManager.STATE_LOADING:
					if(getLoadingTitle() != null) {
						emptyViewState.withTitle(getLoadingTitle());
					}
					if(getLoadingMessage() != null) {
						emptyViewState.withMessage(getLoadingMessage());
					}
					break;

				case EmptyViewManager.STATE_OK:
					if(isDataEmpty()) {
						if(getNoDataTitle() != null) {
							emptyViewState.withTitle(getNoDataTitle());
						}
						if(getNoDataMessage() != null) {
							emptyViewState.withMessage(getNoDataMessage());
						}
					}
					break;
			}
		}

		return emptyViewState;
	}

	@Override
	public CharSequence getLoadingTitle() {
		return null;
	}

	@Override
	public CharSequence getLoadingMessage() {
		return null;
	}

	@Override
	public CharSequence getNoDataTitle() {
		return null;
	}

	@Override
	public CharSequence getNoDataMessage() {
		return null;
	}
}
