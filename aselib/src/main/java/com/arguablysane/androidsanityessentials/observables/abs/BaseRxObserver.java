package com.arguablysane.androidsanityessentials.observables.abs;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by administrator on 12/9/17.
 */

public abstract class BaseRxObserver<T> implements Observer<T> {
	@Override
	public void onSubscribe(Disposable d) {

	}

	@Override
	public void onNext(T t) {

	}

	@Override
	public final void onError(Throwable e) {

	}

	public abstract void doOnError(Throwable e);

	@Override
	public void onComplete() {

	}
}
