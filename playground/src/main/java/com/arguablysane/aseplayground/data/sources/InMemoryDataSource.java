package com.arguablysane.aseplayground.data.sources;

import android.app.Application;

import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.data.sources.abs.AbsDataSource;
import com.arguablysane.aseplayground.eventbus.PersonAddedEvent;
import com.arguablysane.aseplayground.eventbus.PersonDeletedEvent;
import com.arguablysane.aseplayground.eventbus.PersonUpdatedEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by administrator on 14/9/17.
 */

public class InMemoryDataSource implements AbsDataSource {

	private HashMap<Long, PersonModel> personStore;

	@Inject
	Application application;

	public InMemoryDataSource() {
		personStore = new HashMap<>();
	}

	@Override
	public Observable<List<PersonModel>> getPersonList() {
		return Observable.fromCallable(new Callable<List<PersonModel>>() {
			@Override
			public List<PersonModel> call() throws Exception {
				Thread.sleep(2000);			// Simulated  delay
				return new ArrayList<>(personStore.values());
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Observable<Long> savePerson(final PersonModel person) {
		return Observable.fromCallable(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				if(person.getTimestamp()==0) {
					person.setTimestamp(System.currentTimeMillis());
					personStore.put(person.getTimestamp(), person);
					EventBus.getDefault().post(new PersonAddedEvent(person));
				}
				else {
					personStore.put(person.getTimestamp(), person);
					EventBus.getDefault().post(new PersonUpdatedEvent(person));
				}

				return person.getTimestamp();
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Observable<Boolean> deletePerson(final long personId) {
		return Observable.fromCallable(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				personStore.remove(personId);
				EventBus.getDefault().post(new PersonDeletedEvent(personId));
				return true;
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}

	@Override
	public Observable<PersonModel> getPersonDetails(final long personId) {
		return Observable.fromCallable(new Callable<PersonModel>() {
			@Override
			public PersonModel call() throws Exception {
				Thread.sleep(3000);			// Simulated delay
				return personStore.get(personId);
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread());
	}
}
