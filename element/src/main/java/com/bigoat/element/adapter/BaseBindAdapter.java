//package com.bigoat.element.adapter;
//
//import android.content.Context;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.ViewDataBinding;
//
//import com.chad.library.adapter4.BaseQuickAdapter;
//import com.chad.library.adapter4.viewholder.QuickViewHolder;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//
///**
// * 数据绑定适配器
// *
// * @param <Item>
// * @param <ItemBind>
// */
//public abstract class BaseBindAdapter<Item, ItemBind extends ViewDataBinding> extends BaseQuickAdapter<Item, QuickViewHolder>  {
//    private final int layoutResId;
//    private List<Item> data;
//
//    public BaseBindAdapter(int layoutResId, @Nullable List<Item> data) {
//        this.layoutResId = layoutResId;
//        this.data = data;
//    }
//
//    public BaseBindAdapter(int layoutResId) {
//        this(layoutResId, null);
//    }
//
//    @NonNull
//    @Override
//    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup parent, int viewType) {
//        return new QuickViewHolder(layoutResId, parent);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int position, @androidx.annotation.Nullable Item item) {
//        if (item == null) {
//            return;
//        }
//
//        ItemBind binding = DataBindingUtil.getBinding(holder.itemView);
//        if (binding != null) {
//            convert(holder, item, binding);
//            binding.executePendingBindings();
//        }
//
//        DataBindingUtil.bind(holder.itemView);
//    }
//
//    public abstract void convert(@NotNull QuickViewHolder holder, Item item, ItemBind itemBind);
//}
