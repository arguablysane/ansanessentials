package com.arguablysane.aseplayground.activities.form;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.widget.Toast;

import com.arguablysane.aseplayground.activities.abs.BaseActivity;
import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.data.sources.abs.AbsDataSource;
import com.arguablysane.aseplayground.databinding.FormActivityBinding;
import com.arguablysane.aseplayground.injection.DaggerProvider;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FormActivity extends BaseActivity implements FormActivityContract.View {

	private static final String KEY_ARG_PERSON_ID = "ARG_PERSON_ID";

	private FormActivityContract.ViewModel viewModel;
	private FormActivityBinding binding;

	@Inject
	Application context;

	@Inject
	AbsDataSource dataSource;

	@Inject
	EmptyViewManager emptyViewManager;

	public static Intent makeIntent(Context context, long personId) {
		Intent intent = new Intent(context, FormActivity.class);
		intent.putExtra(KEY_ARG_PERSON_ID, personId);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerProvider.getAppComponent().inject(this);

		binding = FormActivityBinding.inflate(getLayoutInflater());
		binding.setView(this);
		setContentView(binding.getRoot());

		setViewModel(new FormActivityViewModel(emptyViewManager));
		long personId = getIntent().getLongExtra(KEY_ARG_PERSON_ID, -1);
		if(personId != -1) {
			getPersonDetails(personId);
		}
		else {
			viewModel.setPerson(new PersonModel());
		}
	}

	private void getPersonDetails(long personId) {
		dataSource.getPersonDetails(personId)
				.subscribe(new Observer<PersonModel>() {
					@Override
					public void onSubscribe(Disposable d) {
						addDisposable(d);
					}

					@Override
					public void onNext(PersonModel personModel) {
						viewModel.setPerson(personModel);
					}

					@Override
					public void onError(Throwable e) {
						viewModel.setDataError(e);
					}

					@Override
					public void onComplete() {

					}
				});

	}

	@Override
	public void setViewModel(final FormActivityContract.ViewModel viewModel) {
		this.viewModel = viewModel;
		if(this.binding != null) {
			this.binding.setViewModel(viewModel);
		}
		this.viewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {

			}
		});
	}

	@Override
	public void onSubmitClicked() {
		if(viewModel.isFormValid()) {
			savePerson();
		}
	}

	private void savePerson() {
		dataSource.savePerson(viewModel.getPerson())
				.subscribe(new Observer<Long>() {
					@Override
					public void onSubscribe(Disposable d) {
						addDisposable(d);
					}

					@Override
					public void onNext(Long aLong) {
						Toast.makeText(context, "Successfully Saved", Toast.LENGTH_LONG).show();
						finish();
					}

					@Override
					public void onError(Throwable e) {
						Toast.makeText(context, "An Error occured", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete() {

					}
				});
	}
}
