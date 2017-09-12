package com.arguablysane.androidsanityessentials.databinding.abs;


import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

/**
 * This class is just to maintain sanity of the code.
 * The implementation is scary!
 */
public class BasePageContract {
	
	public interface ViewModel {

		void setEmptyViewManager(EmptyViewManager emptyViewManager);

		int getDataState();

		void setDataLoading();
		void setDataOK();

		void setDataError(Throwable dataException);
		Throwable getDataException();

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
