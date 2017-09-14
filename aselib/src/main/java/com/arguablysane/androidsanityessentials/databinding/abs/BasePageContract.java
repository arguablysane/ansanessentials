package com.arguablysane.androidsanityessentials.databinding.abs;


import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManagerCallback;

/**
 * This class is just to maintain sanity of the code.
 * The implementation is scary!
 */
public class BasePageContract {
	
	public interface ViewModel {

		void setEmptyViewManager(EmptyViewManager emptyViewManager);

		void setEmptyViewManagerCallback(EmptyViewManagerCallback emptyViewManagerCallback);

		int getDataState();

		//region Data State Setters
		void setDataLoading();
		void setDataOK();
		void setDataError(Throwable dataException);
		Throwable getDataException();
		//endregion

		boolean isLoading();

		boolean isDataEmpty();

		int getEmptyViewVisibility();
		int getContentLayoutVisibility();
		EmptyViewState getEmptyViewState();

		CharSequence getLoadingTitle();
		CharSequence getLoadingMessage();

		CharSequence getNoDataTitle();
		CharSequence getNoDataMessage();
	}
}
