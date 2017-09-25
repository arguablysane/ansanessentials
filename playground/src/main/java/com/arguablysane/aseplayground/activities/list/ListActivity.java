package com.arguablysane.aseplayground.activities.list;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;
import com.arguablysane.androidsanityessentials.observables.abs.BaseRxObserver;
import com.arguablysane.aseplayground.BR;
import com.arguablysane.aseplayground.activities.abs.BaseActivity;
import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.data.sources.abs.AbsDataSource;
import com.arguablysane.aseplayground.databinding.ListActivityBinding;
import com.arguablysane.aseplayground.eventbus.PersonAddedEvent;
import com.arguablysane.aseplayground.eventbus.PersonDeletedEvent;
import com.arguablysane.aseplayground.eventbus.PersonUpdatedEvent;
import com.arguablysane.aseplayground.injection.DaggerProvider;
import com.arguablysane.aseplayground.router.abs.AbsRouter;
import com.arguablysane.aseplayground.widgets.listitems.personlistitem.PersonListitemContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by administrator on 25/9/17.
 */

public class ListActivity extends BaseActivity implements ListActivityContract.View, PersonListitemContract.View {

	private ListActivityBinding binding;
	private ListActivityContract.ViewModel viewModel;
	private PersonListAdapter listAdapter;

	@Inject
	AbsRouter router;

	@Inject
	AbsDataSource dataSource;

	@Inject
	EmptyViewManager emptyViewManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerProvider.getAppComponent().inject(this);
		EventBus.getDefault().register(this);

		listAdapter = new PersonListAdapter(this);

		binding = ListActivityBinding.inflate(getLayoutInflater());
		binding.rvList.setAdapter(listAdapter);
		binding.setView(this);
		setContentView(binding.getRoot());

		setViewModel(new ListActivityViewModel(emptyViewManager));

		getPersonList();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	private void getPersonList() {
		dataSource.getPersonList()
				.subscribe(new BaseRxObserver<List<PersonModel>>() {
					@Override
					public void onSubscribe(Disposable d) {
						// Add this to the composite disposable in BaseActivity
						// The composite disposable is cleared when the activity is destroyed, preventing memory leaks
						addDisposable(d);
						viewModel.setDataLoading();
					}

					@Override
					public void onNext(List<PersonModel> personModels) {
						// Add the list of people to the page view model
						// The view model updates the adapter via the code in {@link #setViewModel(ViewModel)}
						viewModel.setList(personModels);

						// Inform the viewmodel that the data loading was successful
						// This will take care of the emptyview state if there is no data
						viewModel.setDataOK();
					}

					@Override
					public void doOnError(Throwable e) {
						// Inform the viewModel that an error has occured.
						// This will take care of the emptyview state
						viewModel.setDataError(e);
					}

					@Override
					public void onComplete() {

					}
				});
	}

	@Override
	public void setViewModel(final ListActivityContract.ViewModel viewModel) {
		this.viewModel = viewModel;
		this.viewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				switch (i) {
					case BR.list:
						listAdapter.set(viewModel.getList());
						break;
				}
			}
		});

		if(binding != null) {
			binding.setViewModel(viewModel);
		}
	}

	@Override
	public void onAddButtonClicked() {
		router.showFormActivity(this, -1);
	}

	@Override
	public void onPersonClicked(PersonListitemContract.ViewModel viewModel) {
		router.showFormActivity(this, viewModel.getPerson().getTimestamp());
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onPersonAdded(PersonAddedEvent event) {
		getPersonList();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onPersonUpdated(PersonUpdatedEvent event) {
		getPersonList();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onPersonDeleted(PersonDeletedEvent event) {
		getPersonList();
	}
}
