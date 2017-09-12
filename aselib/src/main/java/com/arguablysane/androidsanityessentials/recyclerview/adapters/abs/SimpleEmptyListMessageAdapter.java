package com.arguablysane.androidsanityessentials.recyclerview.adapters.abs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arguablysane.androidsanityessentials.databinding.EmptyRVListItemBinding;

/**
 * Created by administrator on 2/7/17.
 */

public abstract class SimpleEmptyListMessageAdapter<ViewModelClass> extends BaseAdapter<ViewModelClass, ViewDatabindingViewHolder> {

	protected final static int TYPE_ITEM = 0, TYPE_EMPTY_LIST_MESSAGE = 1;

	private CharSequence emptyListMessage;

	public SimpleEmptyListMessageAdapter(CharSequence emptyListMessage) {
		this.emptyListMessage = emptyListMessage;
	}

	@Override
	public int getItemCount() {
		if(super.getItemCount() > 0) {
			return super.getItemCount();
		}
		else {
			return 1;
		}
	}

	@Override
	public int getItemViewType(int position) {
		if(super.getItemCount() > 0) {
			return TYPE_ITEM;
		}
		else {
			return TYPE_EMPTY_LIST_MESSAGE;
		}
	}

	@Override
	public ViewDatabindingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		if(getItemViewType(i) == TYPE_ITEM) {
			return selCreateItemViewHolder(viewGroup, i);
		}
		else {
			EmptyRVListItemBinding msgBinding = EmptyRVListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
			msgBinding.setMessage(emptyListMessage);
			return new ViewDatabindingViewHolder(msgBinding);
		}
	}

	protected abstract ViewDatabindingViewHolder selCreateItemViewHolder(ViewGroup viewGroup, int i);

	@Override
	public void onBindViewHolder(ViewDatabindingViewHolder viewHolderClass, int i) {
		if(getItemViewType(i) == TYPE_ITEM) {
			selBindItemViewHolder(viewHolderClass, i);
		}
	}

	public abstract void selBindItemViewHolder(ViewDatabindingViewHolder viewHolder, int i);

	public void setEmptyListMessage(CharSequence emptyListMessage) {
		this.emptyListMessage = emptyListMessage;
	}

	public CharSequence getEmptyListMessage() {
		return emptyListMessage;
	}
}
