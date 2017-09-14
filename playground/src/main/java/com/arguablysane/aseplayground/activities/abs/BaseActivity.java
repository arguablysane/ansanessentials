package com.arguablysane.aseplayground.activities.abs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by administrator on 14/9/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

	protected CompositeDisposable compositeDisposable;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		compositeDisposable = new CompositeDisposable();
	}

	protected void addDisposable(Disposable disposable) {
		compositeDisposable.add(disposable);
	}

	@Override
	protected void onDestroy() {
		compositeDisposable.clear();
		super.onDestroy();
	}
}
