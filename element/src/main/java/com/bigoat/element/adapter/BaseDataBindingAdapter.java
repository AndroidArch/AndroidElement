//package com.bigoat.element.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.ViewDataBinding;
//
//import com.chad.library.adapter4.BaseQuickAdapter;
//import com.chad.library.adapter4.viewholder.QuickViewHolder;
//
///**
// * <pre>
// *     author : ylw
// *     e-mail : bigoatsm@gmail.com
// *     time   : 2020-04-29
// *     desc   : BaseDataBindingAdapter
// * </pre>
// */
//@Deprecated
//public abstract class BaseDataBindingAdapter<T> extends BaseQuickAdapter<T, QuickViewHolder> {
//
//    private final int layoutResId;
//
//    public BaseDataBindingAdapter(int layoutResId) {
//       this.layoutResId = layoutResId;
//    }
//
//    @NonNull
//    @Override
//    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
//        return new QuickViewHolder(layoutResId, parent);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int position, @Nullable T item) {
//        DataBindingUtil.bind(holder.itemView);
//
//    }
//
//    protected <K extends ViewDataBinding> K getBinding(View view) {
//        return DataBindingUtil.getBinding(view);
//    }
//
//}
