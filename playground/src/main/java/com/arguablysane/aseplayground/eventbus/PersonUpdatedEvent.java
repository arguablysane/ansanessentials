package com.arguablysane.aseplayground.eventbus;

import com.arguablysane.aseplayground.data.models.PersonModel;

/**
 * Created by administrator on 25/9/17.
 */

public class PersonUpdatedEvent {

	private PersonModel personModel;

	public PersonUpdatedEvent(PersonModel personModel) {
		this.personModel = personModel;
	}

	public PersonModel getPersonModel() {
		return personModel;
	}
}
