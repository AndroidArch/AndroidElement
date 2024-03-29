package com.bigoat.element.button;



import static com.bigoat.element.ViewUtils.darkenColor;
import static com.bigoat.element.ViewUtils.dp2px;
import static com.bigoat.element.ViewUtils.getTypedArrayColor;
import static com.bigoat.element.ViewUtils.px2dp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bigoat.element.R;

import java.util.ArrayList;
import java.util.List;


public class ButtonView extends CardView {
    private static final float DARKENING_RATIO = 0.8f;
    private static final int ROUNDED_CORNERS = 4;

    public enum Type {
        PRIMARY, SUCCESS, WARNING, DANGER
    }

    public enum Size {
        MINI, SMALL, NORMAL, LARGE
    }

    private TextView textView;
    private GradientDrawable normalDrawable;
    private GradientDrawable pressedDrawable;

    public OnClickListener onClickListener;

    public ButtonView(Context context) {
        super(context);
        init(context, null);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // TODO 插槽
        /*
        List<View> views = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
        }
         */
    }

    private void init(Context context, AttributeSet attrs) {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        addView(textView);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonView);

        try {
            initAttrs(typedArray);

        } finally {
            typedArray.recycle();
        }
    }

    private int type;
    private boolean plain;
    private boolean hairline;
    private boolean disabled;
    private boolean loading;
    private String loadingText;
    private Drawable icon;
    private boolean square;
    private boolean round;
    private int size;
    private int color;

    @SuppressLint("ResourceAsColor")
    private void initAttrs(TypedArray typedArray) {
        String text = typedArray.getString(R.styleable.ButtonView_buttonText);
        textView.setText(text);

        type = typedArray.getInt(R.styleable.ButtonView_buttonType, 0);
        plain = typedArray.getBoolean(R.styleable.ButtonView_buttonPlain, false);
        hairline = typedArray.getBoolean(R.styleable.ButtonView_buttonHairline, false);
        disabled = typedArray.getBoolean(R.styleable.ButtonView_buttonDisabled, false);
        loading = typedArray.getBoolean(R.styleable.ButtonView_buttonLoading, false);
        loadingText = typedArray.getString(R.styleable.ButtonView_buttonLoadingText);
        square = typedArray.getBoolean(R.styleable.ButtonView_buttonSquare, false);
        round = typedArray.getBoolean(R.styleable.ButtonView_buttonRound, false);
        icon = typedArray.getDrawable(R.styleable.ButtonView_buttonIcon);
        size = typedArray.getInt(R.styleable.ButtonView_buttonSize, 2);
        color = getTypedArrayColor(typedArray, R.styleable.ButtonView_buttonColor, -1);

        normalDrawable = new GradientDrawable();
        normalDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);

        setType(type, color, plain, hairline);

        setSize(size);

        setShape(square, round, ROUNDED_CORNERS);

        setEnabled(!disabled);

        setBackground(normalDrawable);
    }

    // 设置形状
    public void setShape(boolean square, boolean round, int radius) {
        this.square = square;
        this.round = round;

        if (!square) {
            radius = dp2px(radius);
            normalDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
            pressedDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        }

        if (round) {
            normalDrawable.setShape(GradientDrawable.OVAL);
            pressedDrawable.setShape(GradientDrawable.OVAL);
        }
    }

    public void setType(Type type) {
        setType(type.ordinal(), color, plain, hairline);
    }

    public void setPlain(boolean plain) {
        setType(type, color, plain, hairline);
    }

    public void setColor(int color) {
        setType(type, color, plain, hairline);
    }

    public void setHairline(boolean hairline) {
        setType(type, color, plain, hairline);
    }

    @SuppressLint("ResourceAsColor")
    private void setType(int type, int color, boolean plain, boolean hairline) {

        if (color != -1) {
            this.color = color;
        } else {
            switch (type) {
                case 1:
                    this.color = getResources().getColor(R.color.element_success_color);
                    break;
                case 2:
                    this.color = getResources().getColor(R.color.element_warning_color);
                    break;
                case 3:
                    this.color = getResources().getColor(R.color.element_danger_color);
                    break;
                default:
                    this.color = getResources().getColor(R.color.element_primary_color);
            }
        }

        if (plain) {
            int width = hairline?2:4;
            normalDrawable.setStroke(width, this.color);
            normalDrawable.setColor(Color.TRANSPARENT);
            textView.setTextColor(this.color);
            pressedDrawable.setColor(this.color);
        } else {
            normalDrawable.setColor(this.color);
            pressedDrawable.setColor(darkenColor(this.color, DARKENING_RATIO));
        }
    }

    private void setSize(Size size) {
        setSize(size.ordinal());
    }

    private void setSize(int size) {
        this.size = size;

        switch (size) {
            case 0:
                textView.setHeight(getResources().getDimensionPixelSize(R.dimen.element_button_size_mini));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.element_font_size_xs));
                break;
            case 1:
                textView.setHeight(getResources().getDimensionPixelSize(R.dimen.element_button_size_small));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.element_font_size_sm));
                break;
            case 3:
                textView.setHeight(getResources().getDimensionPixelSize(R.dimen.element_button_size_large));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.element_font_size_lg));
                break;
            default:
                textView.setHeight(getResources().getDimensionPixelSize(R.dimen.element_button_size_normal));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.element_font_size_md));
                break;
        }
    }

    public String getText() {
        return textView.getText().toString();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setAlpha(enabled?1f:0.5f);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressed(true);
                    return true;
                case MotionEvent.ACTION_UP:
                    pressed(false);
                    return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void pressed(boolean pressed) {
        if (pressed) {
            setBackground(pressedDrawable);
            if (plain || hairline) {
                textView.setTextColor(Color.WHITE);
            }
        } else {
            setBackground(normalDrawable);
            callOnClick();
            if (plain || hairline) {
                textView.setTextColor(color);
            }
        }
    }
}
