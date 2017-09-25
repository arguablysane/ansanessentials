package com.arguablysane.aseplayground.activities.form;

import android.databinding.Bindable;

import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.androidsanityessentials.databinding.abs.BasePageViewModel;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

/**
 * Created by administrator on 14/9/17.
 */

public class FormActivityContract {

	public interface ResourceProvider {

	}

	public static abstract class ViewModel extends BasePageViewModel {

		public final static int STEP_NAME_ENTRY = 0, STEP_AGE_ENTRY = 1, STEP_LOCATION_ENTRY = 2, STEP_FORM_SUBMISSION = 3;

		public ViewModel(EmptyViewManager emptyViewManager) {
			super(emptyViewManager);
		}

		public abstract void setResourceProvider(ResourceProvider resourceProvider);

		//region Person
		public abstract void setPerson(PersonModel person);

		public abstract PersonModel getPerson();
		//endregion

		//region Name
		@Bindable
		public abstract CharSequence getName();
		
		public abstract void setName(CharSequence name);
		
		public abstract boolean isNameValid();
		
		@Bindable
		public abstract CharSequence getNameError();
		
		public abstract void setNameError(CharSequence error);
		//endregion
		
		//region Age
		@Bindable
		public abstract CharSequence getAge();

		public abstract void setAge(CharSequence name);

		public abstract boolean isAgeValid();

		@Bindable
		public abstract CharSequence getAgeError();

		public abstract void setAgeError(CharSequence error);
		//endregion

		//region Location
		@Bindable
		public abstract CharSequence getLocation();

		public abstract void setLocation(CharSequence name);

		public abstract boolean isLocationValid();

		@Bindable
		public abstract CharSequence getLocationError();

		public abstract void setLocationError(CharSequence error);
		//endregion

		public abstract boolean isFormValid();
		
	}

	public interface View {

		void setViewModel(ViewModel viewModel);

		void onSubmitClicked();
	}
}
