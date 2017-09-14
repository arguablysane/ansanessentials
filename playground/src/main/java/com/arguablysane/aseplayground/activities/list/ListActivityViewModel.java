package com.arguablysane.aseplayground.activities.list;

import com.arguablysane.aseplayground.BR;
import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.widgets.listitems.personlistitem.PersonListItemViewModel;
import com.arguablysane.aseplayground.widgets.listitems.personlistitem.PersonListitemContract;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 14/9/17.
 */

public class ListActivityViewModel extends ListActivityContract.ViewModel {

	private List<PersonListitemContract.ViewModel> personViewModelList;

	public ListActivityViewModel(EmptyViewManager emptyViewManager) {
		super(emptyViewManager);
		this.personViewModelList = new ArrayList<>();
	}

	@Override
	public List<PersonListitemContract.ViewModel> getList() {
		return personViewModelList;
	}

	@Override
	public void setList(List<PersonModel> people) {
		personViewModelList.clear();
		if(people != null) {
			for(PersonModel person : people) {
				personViewModelList.add(new PersonListItemViewModel(person));
			}
		}

		notifyPropertyChanged(BR.list);
	}

	@Override
	public boolean isDataEmpty() {
		return personViewModelList.size() == 0;
	}
}
