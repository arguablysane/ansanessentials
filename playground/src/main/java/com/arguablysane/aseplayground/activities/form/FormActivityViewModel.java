package com.arguablysane.aseplayground.activities.form;

import com.arguablysane.aseplayground.BR;
import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

/**
 * Created by administrator on 14/9/17.
 */

public class FormActivityViewModel extends FormActivityContract.ViewModel {

	private PersonModel personModel;
	private FormActivityContract.ResourceProvider resourceProvider;
	private int currentStep = STEP_NAME_ENTRY;
	private CharSequence msgNameError, msgAgeError, msgLocationError;

	public FormActivityViewModel(EmptyViewManager emptyViewManager) {
		super(emptyViewManager);
	}

	@Override
	public boolean isDataEmpty() {
		return false;
	}

	@Override
	public void setResourceProvider(FormActivityContract.ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
	}

	@Override
	public void setPerson(PersonModel person) {
		this.personModel = person;
	}

	@Override
	public PersonModel getPerson() {
		return this.personModel;
	}

	@Override
	public int getCurrentStep() {
		return currentStep;
	}

	@Override
	public void setStep(int state) {
		this.currentStep = state;
		notifyPropertyChanged(BR.currentStep);
	}

	@Override
	public void setStepNameEntry() {
		setStep(STEP_NAME_ENTRY);
		notifyPropertyChanged(BR.nameLayoutVisible);
	}

	@Override
	public void setStepAgeEntry() {
		setStep(STEP_AGE_ENTRY);
		notifyPropertyChanged(BR.ageLayoutVisible);
	}

	@Override
	public void setStepLocationEntry() {
		setStep(STEP_LOCATION_ENTRY);
		notifyPropertyChanged(BR.locationLayoutVisible);
	}

	@Override
	public void setStepFormSubmission() {
		setStep(STEP_FORM_SUBMISSION);
	}

	@Override
	public void testAndMoveToNextStep() {
		switch (currentStep) {
			case STEP_NAME_ENTRY:
				if(isNameValid()) {
					setStepAgeEntry();
				}
				break;

			case STEP_AGE_ENTRY:
				if(isAgeValid()) {
					setStepLocationEntry();
				}
				break;

			case STEP_LOCATION_ENTRY:
				if(isLocationValid()) {
					setStepFormSubmission();
				}
				break;
		}
	}

	@Override
	public boolean isNameLayoutVisible() {
		return currentStep >= STEP_NAME_ENTRY;
	}

	@Override
	public CharSequence getName() {
		return personModel.getName();
	}

	@Override
	public void setName(CharSequence name) {
		this.personModel.setName(name.toString());
	}

	@Override
	public boolean isNameValid() {
		msgNameError = null;
		if(personModel.getName() != null && personModel.getName().length() > 0) {
			setStepAgeEntry();
			return true;
		}
		else {
			setNameError("Please enter a valid name");
			return false;
		}
	}

	@Override
	public CharSequence getNameError() {
		return msgNameError;
	}

	@Override
	public void setNameError(CharSequence error) {
		this.msgNameError = error;
		notifyPropertyChanged(BR.nameError);
	}

	@Override
	public boolean isAgeLayoutVisible() {
		return currentStep >= STEP_AGE_ENTRY;
	}

	@Override
	public CharSequence getAge() {
		return personModel.getAge() == 0 ? null : String.valueOf(personModel.getAge());
	}

	@Override
	public void setAge(CharSequence age) {
		try {
			personModel.setAge(Integer.parseInt(age.toString()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAgeValid() {
		if(personModel.getAge() <= 0) {
			setAgeError("You do not exist!");
			return false;
		}
		else if(personModel.getAge() > 200) {
			setAgeError("Dayum! Youre old!!");
			return false;
		}
		return true;
	}

	@Override
	public CharSequence getAgeError() {
		return msgAgeError;
	}

	@Override
	public void setAgeError(CharSequence error) {
		this.msgAgeError = error;
		notifyPropertyChanged(BR.ageError);
	}

	@Override
	public boolean isLocationLayoutVisible() {
		return currentStep >= STEP_LOCATION_ENTRY;
	}

	@Override
	public CharSequence getLocation() {
		return personModel.getLocation();
	}

	@Override
	public void setLocation(CharSequence location) {
		personModel.setLocation(location.toString());
	}

	@Override
	public boolean isLocationValid() {
		if(personModel.getLocation() != null || personModel.getLocation().length() < 3) {
			setLocationError("Where in Carmen Sandiego are you!!?!");
			return false;
		}
		return true;
	}

	@Override
	public CharSequence getLocationError() {
		return msgLocationError;
	}

	@Override
	public void setLocationError(CharSequence error) {
		this.msgLocationError = error;
		notifyPropertyChanged(BR.locationError);
	}


}
