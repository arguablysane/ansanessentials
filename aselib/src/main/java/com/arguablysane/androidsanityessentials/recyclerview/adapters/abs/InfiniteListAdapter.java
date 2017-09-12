package com.arguablysane.androidsanityessentials.recyclerview.adapters.abs;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arguablysane.androidsanityessentials.databinding.LoadingListItemWidgetBinding;
import com.arguablysane.androidsanityessentials.emptyview.abs.EmptyViewManager;


/**
 * Created by Shannon on 6/7/17.
 * Base class for an infinite list adapter
 * Should be a straight-forward implementation
 * TODO Everything!!
 */
public abstract class InfiniteListAdapter<ViewModelClass> extends BaseAdapter<ViewModelClass, ViewDatabindingViewHolder> {

	private final static int TYPE_ITEM = 0, TYPE_LOADING = 65535;

	public interface InfiniteListAdapterCallback {
		void onLoadMore(int offset);
	}

	private int statusCode = EmptyViewManager.STATE_NULL;
	private int totalItemCount = -1;
	private boolean isLoading = false;
	private InfiniteListAdapterCallback callback;
	private Context context;

	private Handler handler;

	public InfiniteListAdapter(Context context, InfiniteListAdapterCallback callback) {
		this.callback = callback;
		setHasStableIds(false);
		handler = new Handler();
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		this.isLoading = statusCode == EmptyViewManager.STATE_LOADING;
		handler.post(new Runnable() {
			@Override
			public void run() {
				notifyItemChanged(getItemCount()-1);
			}
		});
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
		handler.post(new Runnable() {
			@Override
			public void run() {
				notifyItemChanged(getItemCount()-1);
			}
		});
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setLoading(boolean loading) {
		isLoading = loading;
		handler.post(new Runnable() {
			@Override
			public void run() {
				notifyItemChanged(getItemCount()-1);
			}
		});
	}

	public boolean isLoading() {
		return isLoading;
	}

	@Override
	public int getItemCount() {
		return super.getItemCount() == 0 ? 0 : super.getItemCount()+1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position == getItemCount()-1 ? TYPE_LOADING : TYPE_ITEM;
	}

	@Override
	public ViewDatabindingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		switch (i) {
			case TYPE_LOADING:
				LoadingListItemWidgetBinding binding = LoadingListItemWidgetBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
				return new ViewDatabindingViewHolder(binding);

			case TYPE_ITEM:
				return inflCreateViewHolder(viewGroup, i);
		}

		return null;
	}

	protected abstract ViewDatabindingViewHolder inflCreateViewHolder(ViewGroup viewGroup, int i);

	@Override
	public void onBindViewHolder(ViewDatabindingViewHolder viewDatabindingViewHolder, int i) {
		// Setting up the loader
		if(super.getItemCount() > 0 && i == super.getItemCount()) {
			if(!isLoading && getItemCount() < totalItemCount) {
				callback.onLoadMore(super.getItemCount());
				isLoading = true;
			}
		}

		switch (getItemViewType(i)) {
			case TYPE_ITEM:
				inflBindViewHolder(viewDatabindingViewHolder, i);
				break;

			case TYPE_LOADING:
				CharSequence msg = null;
				if(isLoading) {
					msg = getLoadingMessage(context);
				}
				else if(totalItemCount < getItemCount()) {
					msg = getNoMoreItemsMessage(context);
				}
				else {
					msg = getErrorMessage(context, statusCode);
				}
				((LoadingListItemWidgetBinding) viewDatabindingViewHolder.binding).setMessage(msg);
				break;
		}
	}

	protected abstract void inflBindViewHolder(ViewDatabindingViewHolder viewDatabindingViewHolder, int i);

	protected abstract CharSequence getLoadingMessage(Context context);

	protected abstract CharSequence getErrorMessage(Context context, int statusCode);

	protected abstract CharSequence getNoMoreItemsMessage(Context context);
}
