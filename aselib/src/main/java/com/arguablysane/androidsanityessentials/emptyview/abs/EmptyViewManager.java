package com.arguablysane.androidsanityessentials.emptyview.abs;


import com.arguablysane.androidsanityessentials.emptyview.EmptyViewState;

/**
 * Created by administrator on 16/8/17.
 */

public interface EmptyViewManager {

	int STATE_NULL = 0, STATE_LOADING = 1, STATE_OK = 2, STATE_ERROR = 3;

	EmptyViewState getLoadingState();

	EmptyViewState getEmptyViewState(int state, Throwable throwable);

}
