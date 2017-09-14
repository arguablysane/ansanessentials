package com.arguablysane.aseplayground.data.sources;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.data.sources.abs.DataSource;
import com.arguablysane.aseplayground.injection.DaggerProvider;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by administrator on 14/9/17.
 */

public class SharedPrefDataSource implements DataSource {

	private SharedPreferences sharedPreferences;

	@Inject
	Application application;

	private SharedPreferences getSharedPreferences() {
		if(sharedPreferences == null) {
			DaggerProvider.getAppComponent().inject(this);
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
		}

		return sharedPreferences;
	}

	@Override
	public Observable<List<PersonModel>> getPersonList() {
		return Observable.fromCallable(new Callable<List<PersonModel>>() {
			@Override
			public List<PersonModel> call() throws Exception {
				return null;
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Observable<Long> savePerson(PersonModel person) {
		return Observable.fromCallable(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				if()
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Observable<PersonModel> getPersonDetails(long personId) {
		return Observable.fromCallable(new Callable<PersonModel>() {
			@Override
			public PersonModel call() throws Exception {
				return null;
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}
}
