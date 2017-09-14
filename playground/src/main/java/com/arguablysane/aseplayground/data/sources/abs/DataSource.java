package com.arguablysane.aseplayground.data.sources.abs;

import com.arguablysane.aseplayground.data.models.PersonModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by administrator on 14/9/17.
 */

public interface DataSource {

	Observable<List<PersonModel>> getPersonList();

	Observable<Long> savePerson(PersonModel person);

	Observable<PersonModel> getPersonDetails(long personId);
}
