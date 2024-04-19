package com.bigoat.element.sidebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigoat.element.R;

public class SidebarItem extends FrameLayout {
    public SidebarItem(Context context) {
        super(context);
        init(context, null);
    }

    public SidebarItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SidebarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonView);

        TextView textView = new TextView(getContext());
        textView.setText("XXXXXX");
        addView(textView);

        try {
//            initAttrs(typedArray);

        } finally {
            typedArray.recycle();
        }
    }


}
