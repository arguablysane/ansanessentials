package com.arguablysane.androidsanityessentials.recyclerview.adapters.abs;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by administrator on 13/6/17.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

	//region States
	public final static int STATE_NULL = -65535, STATE_LOADING = -1, STATE_OK = 0;
	public int state = STATE_NULL;
	//endregion

	public int totalItemCount = 0;
	private List<T> mObjects;

	public BaseAdapter() {
		mObjects = new ArrayList<>();
	}

	public BaseAdapter(final List<T> objects) {
		mObjects = objects;
	}

	/**
	 * Adds the specified object at the end of the array.
	 *
	 * @param object The object to add at the end of the array.
	 */
	public void add(final T object) {
		mObjects.add(object);
		notifyItemInserted(getItemCount() - 1);
	}

	public void set(final T object) {
		clear();
		add(object);
	}

	public void add(final List<T> objects) {
		if(objects != null) {
			int count = getItemCount();
			mObjects.addAll(objects);
			notifyItemRangeInserted(count, objects.size());
		}
	}

	public void set(final List<T> mObjects) {
		clear();
		add(mObjects);
	}

	public void add(final T... objects) {
		if(objects != null) {
			int count = getItemCount();
			Collections.addAll(mObjects, objects);
			notifyItemRangeInserted(count, objects.length);
		}
	}

	public void set(final T... objects) {
		clear();
		add(objects);
	}

	/**
	 * Remove all elements from the list.
	 */
	public void clear() {
		final int size = getItemCount();
		mObjects.clear();
		notifyItemRangeRemoved(0, size);
	}

	@Override
	public int getItemCount() {
		return mObjects.size();
	}

	public T getItem(final int position) {
		return mObjects.get(position);
	}

	public long getItemId(final int position) {
		return position;
	}

	/**
	 * Returns the position of the specified item in the array.
	 *
	 * @param item The item to retrieve the position of.
	 * @return The position of the specified item.
	 */
	public int getPosition(final T item) {
		return mObjects.indexOf(item);
	}

	/**
	 * Inserts the specified object at the specified index in the array.
	 *
	 * @param object The object to insert into the array.
	 * @param index  The index at which the object must be inserted.
	 */
	public void insert(final T object, int index) {
		mObjects.add(index, object);
		notifyItemInserted(index);
	}

	/**
	 * Removes the specified object from the array.
	 *
	 * @param object The object to remove.
	 */
	public void remove(T object) {
		final int position = getPosition(object);
		mObjects.remove(object);
		notifyItemRemoved(position);
	}

	/**
	 * Sorts the content of this adapter using the specified comparator.
	 *
	 * @param comparator The comparator used to sort the objects contained in this adapter.
	 */
	public void sort(Comparator<? super T> comparator) {
		Collections.sort(mObjects, comparator);
		notifyItemRangeChanged(0, getItemCount());
	}
}
