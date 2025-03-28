package com.xwray.groupie.viewbinding;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import com.xwray.groupie.Item;
import java.util.List;

/**
 * The base unit of content for a GroupAdapter.
 * <p>
 * Because an Item is a Group of size one, you don't need to use Groups directly if you don't want;
 * simply mix and match Items and add directly to the adapter.
 * <p>
 * If you want to use Groups, because Item extends Group, you can mix and match adding Items and
 * other Groups directly to the adapter.
 *
 * @param <T> The ViewBinding subclass associated with this Item.
 */
public abstract class BindableItem<T extends ViewBinding> extends Item<GroupieViewHolder<T>> {

    public BindableItem() {
        super();
    }

    protected BindableItem(long id) {
        super(id);
    }

    @NonNull
    protected abstract T initializeViewBinding(@NonNull View view);

    @NonNull
    @Override
    public GroupieViewHolder<T> createViewHolder(@NonNull View itemView) {
        T binding = initializeViewBinding(itemView);
        return new GroupieViewHolder<>(binding);
    }

    @Override
    public void bind(@NonNull GroupieViewHolder<T> viewHolder, int position) {
        throw new RuntimeException("Doesn't get called");
    }

    @Override
    public void bind(@NonNull GroupieViewHolder<T> viewHolder, int position, @NonNull List<Object> payloads) {
        bind(viewHolder.binding, position, payloads);
    }

    /**
     * Perform any actions required to set up the view for display.
     *
     * @param viewBinding The ViewBinding to bind
     * @param position    The adapter position
     */
    public abstract void bind(@NonNull T viewBinding, int position);

    /**
     * Perform any actions required to set up the view for display.
     * <p>
     * If you don't specify how to handle payloads in your implementation, they'll be ignored and
     * the adapter will do a full rebind.
     *
     * @param viewBinding The ViewBinding to bind
     * @param position    The adapter position
     * @param payloads    A list of payloads (may be empty)
     */
    public void bind(@NonNull T viewBinding, int position, @NonNull List<Object> payloads) {
        bind(viewBinding, position);
    }
}
