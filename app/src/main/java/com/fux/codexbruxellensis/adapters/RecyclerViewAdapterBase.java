package com.fux.codexbruxellensis.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fux.codexbruxellensis.views.ViewWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewWrapper<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

}
