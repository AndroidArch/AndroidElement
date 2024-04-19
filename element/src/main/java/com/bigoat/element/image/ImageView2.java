package com.bigoat.element.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bigoat.element.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;


public class ImageView2 extends AppCompatImageView {

    private String imageSrc;
    private Drawable imageHolder;
    private Drawable imageError;
    private boolean imageRound;
    private int imageCorner;
    private int imageCornerTopLeft;
    private int imageCornerTopRight;
    private int imageCornerBottomRight;
    private int imageCornerBottomLeft;

    public ImageView2(Context context) {
        super(context);
        init(context, null);
    }

    public ImageView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageView2);
        try {
            initAttrs(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    private void initAttrs(TypedArray typedArray) {

        imageSrc = typedArray.getString(R.styleable.ImageView2_imageSrc);
        imageHolder = typedArray.getDrawable(R.styleable.ImageView2_imageHolder);
        imageError = typedArray.getDrawable(R.styleable.ImageView2_imageError);
        imageRound = typedArray.getBoolean(R.styleable.ImageView2_imageRound, false);
        imageCorner = typedArray.getInt(R.styleable.ImageView2_imageCorner, -1);
        imageCornerTopLeft = typedArray.getInt(R.styleable.ImageView2_imageCornerTopLeft, -1);
        imageCornerTopRight = typedArray.getInt(R.styleable.ImageView2_imageCornerTopRight, -1);
        imageCornerBottomRight = typedArray.getInt(R.styleable.ImageView2_imageCornerBottomRight, -1);
        imageCornerBottomLeft = typedArray.getInt(R.styleable.ImageView2_imageCornerBottomLeft, -1);

        int index = R.styleable.ImageView2_imageSrc;
        String imageSrc = typedArray.getString(index);
        if (imageSrc != null) {
            if (imageSrc.startsWith("#")) {
                int colorValue = typedArray.getColor(index, -1);
                if (colorValue != -1) {
                    Drawable drawable = new ColorDrawable(colorValue);
                    load(this, drawable, imageHolder, imageError, imageRound, imageCorner, imageCornerTopLeft, imageCornerTopRight, imageCornerBottomRight, imageCornerBottomLeft);
                }

            } else if (imageSrc.startsWith("res/")) {
                Drawable drawable = typedArray.getDrawable(index);
                load(this, drawable, imageHolder, imageError, imageRound, imageCorner, imageCornerTopLeft, imageCornerTopRight, imageCornerBottomRight, imageCornerBottomLeft);
            } else {
                load(this, imageSrc, imageHolder, imageError, imageRound, imageCorner, imageCornerTopLeft, imageCornerTopRight, imageCornerBottomRight, imageCornerBottomLeft);
            }
        }
    }

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "imageSrc", // 源图片
            "imageHolder", // 占位图
            "imageError", // 错误图
            "imageRound", // 圆形图片
            "imageCorner", // 圆角大小
            "imageCornerTopLeft", // 圆角左上角大小
            "imageCornerTopRight", // 圆角右上角大小
            "imageCornerBottomRight", // 圆角左下角大小
            "imageCornerBottomLeft" // 圆角右下角大小
    }, requireAll = false)
    public static void load(@NotNull ImageView view,
                            Object src,
                            Drawable holder,
                            Drawable error,
                            boolean round,
                            int imageCorner,
                            int imageCornerTopLeft,
                            int imageCornerTopRight,
                            int imageCornerBottomRight,
                            int imageCornerBottomLeft) {
        RequestOptions options = new RequestOptions();
        if (holder != null) options.placeholder(holder);
        if (error != null) options.error(error);

        if (imageCornerBottomLeft > 0 || imageCornerBottomRight > 0 || imageCornerTopLeft > 0 || imageCornerTopRight > 0) {
            options.transform(new GranularRoundedCorners(imageCornerTopLeft, imageCornerTopRight, imageCornerBottomRight, imageCornerBottomLeft));
        }

        if (imageCorner > 0) {
            options.transform(new RoundedCorners(imageCorner));
        }

        if (round) {
            options.circleCrop();
        }

        load(view, src, options);
    }

    public static void load(@NotNull ImageView view, Object src) {
        load(view, src, null, null, false, 0, 0, 0, 0, 0);
    }

    public static void load(@NotNull ImageView view, Object src, Drawable error) {
        load(view, src, null, error, false, 0, 0, 0, 0, 0);
    }
    public static void load(@NotNull ImageView view, Object src, Drawable holder, Drawable error) {
        load(view, src, holder, error, false, 0, 0, 0, 0, 0);
    }

    private static void load(ImageView view, Object src, RequestOptions options) {
        if (src instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) src;
            Glide.with(view).load(bitmap).apply(options).into(view);
        } else if (src instanceof Drawable) {
            Drawable drawable = (Drawable) src;
            Glide.with(view).load(drawable).apply(options).into(view);
        } else if (src instanceof String) {
            String string = (String) src;
            Glide.with(view).load(string).apply(options).into(view);
        } else if (src instanceof Uri) {
            Uri uri = (Uri) src;
            Glide.with(view).load(uri).apply(options).into(view);
        } else if (src instanceof File) {
            File file = (File) src;
            Glide.with(view).load(file).apply(options).into(view);
        } else if (src instanceof URL) {
            URL url = (URL) src;
            Glide.with(view).load(url).apply(options).into(view);
        } else if (src instanceof Integer) {
            Integer integer = (Integer) src;
            Glide.with(view).load(integer).apply(options).into(view);
        } else {
            Glide.with(view).load(src).apply(options).into(view);
        }
    }
}
