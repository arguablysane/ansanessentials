package com.arguablysane.aseplayground.eventbus;

import com.arguablysane.aseplayground.data.models.PersonModel;

/**
 * Created by administrator on 25/9/17.
 */

public class PersonAddedEvent {

	private PersonModel personModel;

	public PersonAddedEvent(PersonModel personModel) {
		this.personModel = personModel;
	}

	public PersonModel getPersonModel() {
		return personModel;
	}
}
