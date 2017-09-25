package com.arguablysane.aseplayground.eventbus;

/**
 * Created by administrator on 25/9/17.
 */

public class PersonDeletedEvent {

	private long personId;

	public PersonDeletedEvent(long personId) {
		this.personId = personId;
	}
}
