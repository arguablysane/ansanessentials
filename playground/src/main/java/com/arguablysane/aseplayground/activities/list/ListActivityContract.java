package com.arguablysane.aseplayground.activities.list;

import android.databinding.Bindable;

import com.arguablysane.aseplayground.data.models.PersonModel;
import com.arguablysane.aseplayground.widgets.listitems.personlistitem.PersonListitemContract;
import com.arguablysane.androidsanityessentials.databinding.abs.BasePageViewModel;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;

import java.util.List;

/**
 * Created by administrator on 14/9/17.
 */

public class ListActivityContract {

	public interface ResourceProvider {

	}

	public static abstract class ViewModel extends BasePageViewModel {

		public ViewModel(EmptyViewManager emptyViewManager) {
			super(emptyViewManager);
		}

		@Bindable
		public abstract List<PersonListitemContract.ViewModel> getList();

		public abstract void setList(List<PersonModel> people);
	}

	public interface View {
		void onAddButtonClicked();
	}
}
