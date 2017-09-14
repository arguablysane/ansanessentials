package com.arguablysane.androidsanityessentials.emptyview;


import com.arguablysane.androidsanityessentials.R;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

/**
 * Created by Shannon Rodricks on 17/11/16.
 */
public class ReallySimpleEmptyViewManager implements EmptyViewManager {

	private EmptyViewState loadingState = new EmptyViewState()
			.withIcon(R.drawable.colordrawable_transparent)
			.withTitle("Loading")
			.withMessage("Please wait while we load your data");

    public static EmptyViewState genericErrorState = new EmptyViewState()
				.withIcon(R.drawable.colordrawable_transparent)
                .withTitle("An Error Occured")
                .withMessage("An error has occured. We're looking into it");

    @Override
    public EmptyViewState getLoadingState() {
		return loadingState;
    }

    @Override
    public EmptyViewState getEmptyViewState(int state, Throwable throwable) {
		switch (state) {
			case STATE_LOADING:
				return getLoadingState();

			default:
				return genericErrorState;
		}
    }

	@Override
	public String getMessageFromError(Throwable throwable) {
		return throwable.getMessage();
	}
}
