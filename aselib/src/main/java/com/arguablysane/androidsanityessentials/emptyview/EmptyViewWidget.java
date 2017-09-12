package com.arguablysane.androidsanityessentials.emptyview;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.arguablysane.androidsanityessentials.R;

/**
 * Created by Shannon Rodricks on 17/11/16.
 */

public class EmptyViewWidget extends FrameLayout {

    private ImageView imgIcon;
    private TextView tvTitle;
    private TextView tvMessage;

    private OnClickListener iconClickListener, titleClickListener, messageClickListener;

    public EmptyViewWidget(Context context) {
        this(context, null);
    }

    public EmptyViewWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyViewWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater.from(context).inflate(R.layout.widget_empty_view, this, true);
        this.tvMessage = (TextView) findViewById(R.id.tvMessage);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.imgIcon = (ImageView) findViewById(R.id.imgIcon);

        imgIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iconClickListener != null) {
                    iconClickListener.onClick(view);
                }
            }
        });

        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleClickListener != null) {
                    titleClickListener.onClick(view);
                }
            }
        });

        tvMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(messageClickListener != null) {
                    messageClickListener.onClick(view);
                }
            }
        });
    }

    public void setState(EmptyViewState state) {
        setIcon(state.getIconBackgroundId(), state.getIconResId(), state.getIconTintColor());
        setTitle(state.getColorTitle(), state.getTextTitle());
        setMessage(state.getColorMessage(), state.getTextMessage());
    }

    public void setIcon(@DrawableRes int backgroundRes, @DrawableRes int iconRes, @ColorInt int tintColor) {
        if(backgroundRes != EmptyViewState.NULL_INT){
            this.imgIcon.setBackgroundResource(backgroundRes);
        }

        if(iconRes != EmptyViewState.NULL_INT) {
			this.imgIcon.setImageResource(iconRes);
		}

        if(tintColor != EmptyViewState.NULL_INT) {
			this.imgIcon.setColorFilter(tintColor);
		}

        Drawable icon = imgIcon.getDrawable();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if(icon instanceof AnimatedVectorDrawableCompat) {
                ((AnimatedVectorDrawableCompat) icon).start();
            }
        }
        else {
            if(icon instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) icon).start();
            }
        }
    }

    public void setTitle(@ColorInt int titleColor, CharSequence title) {
        if(titleColor != EmptyViewState.NULL_INT) {
        	tvTitle.setTextColor(titleColor);
		}

        tvTitle.setText(title);
    }

    public void setMessage(@ColorInt int messageColor, CharSequence message) {
        if(messageColor != EmptyViewState.NULL_INT) {
        	tvMessage.setTextColor(messageColor);
		}

        tvMessage.setText(message);
    }

    public void setIconClickListener(OnClickListener listener) {
        this.iconClickListener = listener;
    }

    public void setTitleClickListener(OnClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    public void setMessageClickListener(OnClickListener messageClickListener) {
        this.messageClickListener = messageClickListener;
    }
}
