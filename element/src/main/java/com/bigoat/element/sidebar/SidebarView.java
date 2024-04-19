package com.bigoat.element.sidebar;



import static com.bigoat.element.ViewUtils.darkenColor;
import static com.bigoat.element.ViewUtils.dp2px;
import static com.bigoat.element.ViewUtils.getTypedArrayColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.bigoat.element.R;

import java.util.ArrayList;
import java.util.List;


public class SidebarView extends LinearLayout {
    private static final float DARKENING_RATIO = 0.8f;
    private static final int ROUNDED_CORNERS = 4;


    public List<SidebarItem> items = new ArrayList<>();

    // 属性
    private int type;
    // 是否朴素
    private boolean plain;
    // 是否细边框
    private boolean hairline;
    // 是否禁用
    private boolean disabled;
    // 是否加载中
    private boolean loading;
    // 加载中文本
    private String loadingText;
    // 图标
    private Drawable icon;
    // 是否正方形
    private boolean square;
    // 是否圆形
    private boolean round;
    // 大小
    private int size;
    // 自定义颜色
    private int color;

    // 文本控件
    private TextView textView;
    // 正常背景
    private GradientDrawable normalDrawable;
    // 按下背景
    private GradientDrawable pressedDrawable;

    public SidebarView(Context context) {
        super(context);
        init(context, null);
    }

    public SidebarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SidebarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

//        if (getChildCount()>0) {
//            View view = getChildAt(0);
//            removeView(view);
//
//        }

    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SidebarView);

        try {
            initAttrs(typedArray);

        } finally {
            typedArray.recycle();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void initAttrs(TypedArray typedArray) {

        int layout = typedArray.getResourceId(R.styleable.SidebarView_sidebarItem, -1);

        if (layout != -1) {
            for (int i = 0; i < 20; i++) {
                View view = LayoutInflater.from(getContext()).inflate(layout, this, true);
//                addView(view);
            }

        }
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
