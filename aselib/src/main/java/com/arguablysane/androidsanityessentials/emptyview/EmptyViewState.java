package com.arguablysane.androidsanityessentials.emptyview;

/**
 * Created by Shannon Rodricks on 17/11/16.
 */

public class EmptyViewState {

    public final static int NULL_INT = -1;

    private int iconBackgroundId = NULL_INT, iconResId = NULL_INT, iconTintColor = NULL_INT;
    private CharSequence textTitle, textMessage;
    private int colorTitle = NULL_INT, colorMessage = NULL_INT;

    public EmptyViewState() {}

	public EmptyViewState(EmptyViewState loadingState) {
		this.iconBackgroundId = loadingState.iconBackgroundId;
		this.iconResId = loadingState.iconResId;
		this.iconTintColor = loadingState.iconTintColor;
		this.textTitle = loadingState.textTitle;
		this.textMessage = loadingState.textMessage;
		this.colorTitle = loadingState.colorTitle;
		this.colorMessage = loadingState.colorMessage;
	}

	public EmptyViewState withIcon(int iconResId) {
    	this.iconResId = iconResId;
    	return this;
	}

	public EmptyViewState withIconBackground(int background) {
    	this.iconBackgroundId = background;
    	return this;
	}

	public EmptyViewState withIconTint(int color) {
    	this.iconTintColor = color;
    	return this;
	}

	public EmptyViewState withTitle(CharSequence title) {
    	this.textTitle = title;
    	return this;
	}

	public EmptyViewState withTitleColor(int color) {
    	this.colorTitle = color;
    	return this;
	}

	public EmptyViewState withMessage(CharSequence message) {
    	this.textMessage = message;
    	return this;
	}

	public EmptyViewState withMessageColor(int color) {
    	this.colorMessage = color;
    	return this;
	}

	public int getIconBackgroundId() {
		return iconBackgroundId;
	}

	public void setIconBackgroundId(int iconBackgroundId) {
		this.iconBackgroundId = iconBackgroundId;
	}

	public int getIconResId() {
		return iconResId;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public int getIconTintColor() {
		return iconTintColor;
	}

	public void setIconTintColor(int iconTintColor) {
		this.iconTintColor = iconTintColor;
	}

	public CharSequence getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(CharSequence textTitle) {
		this.textTitle = textTitle;
	}

	public CharSequence getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(CharSequence textMessage) {
		this.textMessage = textMessage;
	}

	public int getColorTitle() {
		return colorTitle;
	}

	public void setColorTitle(int colorTitle) {
		this.colorTitle = colorTitle;
	}

	public int getColorMessage() {
		return colorMessage;
	}

	public void setColorMessage(int colorMessage) {
		this.colorMessage = colorMessage;
	}
}
