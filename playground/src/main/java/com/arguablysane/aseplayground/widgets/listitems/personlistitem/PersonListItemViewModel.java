package com.arguablysane.aseplayground.widgets.listitems.personlistitem;

import com.arguablysane.aseplayground.data.models.PersonModel;

/**
 * Created by administrator on 14/9/17.
 */

public class PersonListItemViewModel extends PersonListitemContract.ViewModel {

	private PersonModel personModel;

	public PersonListItemViewModel(PersonModel personModel) {
		this.personModel = personModel;
	}

	@Override
	public CharSequence getName() {
		return personModel.getName();
	}

	@Override
	public CharSequence getAge() {
		return String.valueOf(personModel.getAge());
	}

	@Override
	public CharSequence getLocation() {
		return personModel.getLocation();
	}

	@Override
	public PersonModel getPerson() {
		return personModel;
	}
}
