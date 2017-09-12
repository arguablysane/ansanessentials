package com.arguablysane.androidsanityessentials.recyclerview.adapters.abs;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by administrator on 2/7/17.
 */

public class ViewDatabindingViewHolder extends RecyclerView.ViewHolder {

	public ViewDataBinding binding;

	public ViewDatabindingViewHolder(ViewDataBinding binding) {
		super(binding.getRoot());
		this.binding = binding;
	}
}
