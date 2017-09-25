package com.arguablysane.aseplayground.data;

import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

/**
 * Created by administrator on 25/9/17.
 */

public class PlaygroundEmptyViewManager implements EmptyViewManager {

	private EmptyViewState LOADING_STATE = new EmptyViewState()
			.withTitle("Loading")
			.withMessage("Please wait while we load the data");

	private EmptyViewState GENERIC_ERROR = new EmptyViewState()
			.withTitle("Error")
			.withMessage("An error occured");

	private EmptyViewState NO_DATA = new EmptyViewState()
			.withTitle("No Data")
			.withMessage("Sorry, we couldn't find any data that matched your query");

	@Override
	public EmptyViewState getLoadingState() {
		return new EmptyViewState(LOADING_STATE);
	}

	@Override
	public EmptyViewState getEmptyViewState(int state, Throwable throwable) {
		switch (state) {
			case EmptyViewManager.STATE_LOADING:
				return new EmptyViewState(LOADING_STATE);

			case EmptyViewManager.STATE_OK:
				return new EmptyViewState(NO_DATA);

			case EmptyViewManager.STATE_ERROR:
				return new EmptyViewState(GENERIC_ERROR);
				// Alternately messages can be retrieved from the throwable and displayed
		}

		return null;
	}

	@Override
	public String getMessageFromError(Throwable throwable) {
		return null;
	}
}
