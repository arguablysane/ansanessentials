package com.arguablysane.aseplayground.activities.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arguablysane.androidsanityessentials.recyclerview.adapters.abs.BaseAdapter;
import com.arguablysane.androidsanityessentials.recyclerview.adapters.abs.ViewDatabindingViewHolder;
import com.arguablysane.aseplayground.databinding.PersonListItemBinding;
import com.arguablysane.aseplayground.widgets.listitems.personlistitem.PersonListitemContract;

/**
 * Created by administrator on 25/9/17.
 */

public class PersonListAdapter extends BaseAdapter<PersonListitemContract.ViewModel, ViewDatabindingViewHolder> {

	private PersonListitemContract.View listItemCallback;

	public PersonListAdapter(PersonListitemContract.View listItemCallback) {
		this.listItemCallback = listItemCallback;
	}

	@Override
	public ViewDatabindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		PersonListItemBinding binding = PersonListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
		binding.setView(listItemCallback);
		return new ViewDatabindingViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(ViewDatabindingViewHolder holder, int position) {
		((PersonListItemBinding) holder.binding).setViewModel(getItem(position));
	}
}
