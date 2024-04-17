package com.bigoat.element.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;


public class ImageView2 extends AppCompatImageView {

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

    private String imageSrc;
    private boolean imageRound;
    private int imageRadius;
    private Drawable imageError;
    private Drawable imageLoading;

    @SuppressLint("ResourceAsColor")
    private void initAttrs(TypedArray typedArray) {

        imageSrc = typedArray.getString(R.styleable.ImageView2_imageSrc);
        imageRound = typedArray.getBoolean(R.styleable.ImageView2_imageRound, false);
        imageRadius = typedArray.getInt(R.styleable.ImageView2_imageRadius, -1);
        imageError = typedArray.getDrawable(R.styleable.ImageView2_imageError);
        imageLoading = typedArray.getDrawable(R.styleable.ImageView2_imageLoading);

        int index = R.styleable.ImageView2_imageSrc;
        String imageSrc = typedArray.getString(index);
        if (imageSrc != null) {
            if (imageSrc.startsWith("#")) {
                int colorValue = typedArray.getColor(index, -1);
                Log.d("TTT",  "colorValue " + colorValue);
                setBackgroundColor(colorValue);

            } else if (imageSrc.startsWith("res/")) {
                Drawable drawable = typedArray.getDrawable(index);
                Log.d("TTT",  "drawable " + drawable);
                setImageDrawable(drawable);

            } else {

                RequestOptions options = new RequestOptions();

                if (imageRadius>0) {
                    options.apply(RequestOptions.bitmapTransform(new RoundedCorners(imageRadius)));
                }

                if (imageRound) {
                    options.circleCrop();
                }

                Glide.with(this)
                        .load(imageSrc)
                        .apply(options)
                        .error(imageError)
                        .into(this);
                Log.d("TTT",  "other " + imageSrc);
            }
        }
    }

    @SuppressLint("CheckResult")
    private void processImageSrc(TypedArray typedArray, int index, boolean imageRound, int imageRadius) {

    }

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "imageSrc", // 源图片
            "imageHolder", // 占位图
            "imageError", // 错误图
            "imageRound", // 圆形图片
            "imageGray", // 图片黑白
            "imageBlur", // 图片模糊
            "imageCornersRadius", // 圆角大小
            "imageCornersTopLeftRadius", // 圆角左上角大小
            "imageCornersTopRightRadius", // 圆角右上角大小
            "imageCornersBottomRightRadius", // 圆角左下角大小
            "imageCornersBottomLeftRadius" // 圆角右下角大小
    }, requireAll = false)
    public static void load(@NotNull ImageView view,
                            Object src,
                            Drawable holder,
                            Drawable error,
                            boolean round,
                            boolean gray,
                            int blur,
                            int cornersRadius,
                            int cornersTopLeftRadius,
                            int cornersTopRightRadius,
                            int cornersBottomRightRadius,
                            int cornersBottomLeftRadius) {


        RequestOptions options = new RequestOptions();
        if (holder != null) options.placeholder(holder);
        if (error != null) options.error(error);

//        MultiTransformation<Bitmap> transformation = new MultiTransformation<Bitmap>(new RoundedCornersTransformation(0, 0, RoundedCornersTransformation.CornerType.ALL));
//
//        if (round) {
//            transformation = new MultiTransformation<Bitmap>(transformation, new CropCircleTransformation());
//        }
//
//        if (gray) {
//            transformation = new MultiTransformation<Bitmap>(transformation, new GrayscaleTransformation());
//        }
//
//        if (blur != 0) {
//            transformation = new MultiTransformation<Bitmap>(transformation, new BlurTransformation(blur));
//        }
//
//        if (!round) {
//            if (cornersRadius != 0) {
//                transformation = new MultiTransformation<Bitmap>(transformation, new RoundedCornersTransformation(cornersRadius, 0, RoundedCornersTransformation.CornerType.ALL));
//            } else {
//                if (cornersTopLeftRadius != 0) {
//                    transformation = new MultiTransformation<Bitmap>(transformation, new RoundedCornersTransformation(cornersTopLeftRadius, 0, RoundedCornersTransformation.CornerType.TOP_LEFT));
//                }
//
//                if (cornersTopRightRadius != 0) {
//                    transformation = new MultiTransformation<Bitmap>(transformation, new RoundedCornersTransformation(cornersTopRightRadius, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT));
//
//                }
//                if (cornersBottomRightRadius != 0) {
//                    transformation = new MultiTransformation<Bitmap>(transformation, new RoundedCornersTransformation(cornersBottomRightRadius, 0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT));
//
//                }
//                if (cornersBottomLeftRadius != 0) {
//                    transformation = new MultiTransformation<Bitmap>(transformation, new RoundedCornersTransformation(cornersBottomLeftRadius, 0, RoundedCornersTransformation.CornerType.BOTTOM_RIGHT));
//                }
//
//            }
//        }
//
//        options.transform(transformation);

        load(view, src, options);
    }

    public static void load(@NotNull ImageView view, Object src, Drawable holder, Drawable error) {
        load(view, src, holder, error, false, false, 0, 0, 0, 0, 0, 0);
    }

    public static void loadCornersRadius(@NotNull ImageView view, Object src, Drawable holder, Drawable error, int cornersRadius) {
        load(view, src, holder, error, false, false, 0, cornersRadius, 0, 0, 0, 0);
    }

    public static void loadCornersRadius(@NotNull ImageView view, Object src, Drawable holder, Drawable error, int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
        load(view, src, holder, error, false, false, 0, 0, topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius);
    }

    public static void loadBlur(@NotNull ImageView view, Object src, Drawable holder, Drawable error, int imageBlur) {
        load(view, src, holder, error, false, false, imageBlur, 0, 0, 0, 0, 0);
    }

    public static void loadRound(@NotNull ImageView view, Object src, Drawable holder, Drawable error) {
        load(view, src, holder, error, true, false, 0, 0, 0, 0, 0, 0);
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
            // ILog.d("加载图片失败 !");
            Glide.with(view).load(src).apply(options).into(view);
        }
    }
}
