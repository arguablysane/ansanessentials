package com.arguablysane.aseplayground.widgets.listitems.personlistitem;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.arguablysane.aseplayground.data.models.PersonModel;

/**
 * Created by administrator on 14/9/17.
 */

public class PersonListitemContract {

	public static abstract class ViewModel extends BaseObservable {

		@Bindable
		public abstract CharSequence getName();

		@Bindable
		public abstract CharSequence getAge();

		@Bindable
		public abstract CharSequence getLocation();

		public abstract PersonModel getPerson();
	}

	public interface View {

		void onPersonClicked(ViewModel viewModel);
	}
}
