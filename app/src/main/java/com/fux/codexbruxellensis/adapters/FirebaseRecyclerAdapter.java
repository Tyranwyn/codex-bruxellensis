package com.fux.codexbruxellensis.adapters;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is a generic way of backing a {@link RecyclerView} with a Firebase location. It
 * handles all of the child events at the given Firebase location and marshals received data into
 * the given class type.
 * <p>
 * See the <a href="https://github.com/firebase/FirebaseUI-Android/blob/master/database/README.md">README</a>
 * for an in-depth tutorial on how to set up the FirebaseRecyclerAdapter.
 *
 * @param <T>  The Java class that maps to the type of objects stored in the Firebase location.
 * @param <VH> The {@link RecyclerView.ViewHolder} class that contains the Views in the layout that
 *             is shown for each object.
 */
public abstract class FirebaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements FirebaseAdapter<T>, Filterable {
    private static final String TAG = "FirebaseRecyclerAdapter";

    private final ObservableSnapshotArray<T> mSnapshots;
    private final List<T> list, backupList;
    private CustomFilter mCustomFilter;
    private boolean isFiltarable;

    // https://gist.github.com/Kishanjvaghela/67c42f8f32efaa2fadb682bc980e9280

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     */
    public FirebaseRecyclerAdapter(FirebaseRecyclerOptions<T> options, boolean isFiltarable) {
        mSnapshots = options.getSnapshots();
        list = new ArrayList<>();
        backupList = new ArrayList<>();
        if (options.getOwner() != null) {
            options.getOwner().getLifecycle().addObserver(this);
        }
        this.isFiltarable = isFiltarable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListening() {
        if (!mSnapshots.isListening(this)) {
            mSnapshots.addChangeEventListener(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopListening() {
        mSnapshots.removeChangeEventListener(this);
        notifyDataSetChanged();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void cleanup(LifecycleOwner source) {
        source.getLifecycle().removeObserver(this);
    }

    @Override
    public void onChildChanged(ChangeEventType type,
                               DataSnapshot snapshot,
                               int newIndex,
                               int oldIndex) {
        T model = mSnapshots.get(newIndex);
        onChildUpdate(model, type, snapshot, newIndex, oldIndex);
    }

    protected void onChildUpdate(T model, ChangeEventType type,
                                 DataSnapshot snapshot,
                                 int newIndex,
                                 int oldIndex) {
        switch (type) {
            case ADDED:
                addItem(snapshot.getKey(), model);
                notifyItemInserted(newIndex);
                break;
            case CHANGED:
                addItem(snapshot.getKey(), model, newIndex);
                notifyItemChanged(newIndex);
                break;
            case REMOVED:
                removeItem(newIndex);
                notifyItemRemoved(newIndex);
                break;
            case MOVED:
                moveItem(snapshot.getKey(), model, newIndex, oldIndex);
                notifyItemMoved(oldIndex, newIndex);
                break;
            default:
                throw new IllegalStateException("Incomplete case statement");
        }
    }

    private void moveItem(String key, T t, int newIndex, int oldIndex) {
        list.remove(oldIndex);
        list.add(newIndex, t);
        if (isFiltarable) {
            backupList.remove(oldIndex);
            backupList.add(newIndex, t);
        }
    }

    private void removeItem(int newIndex) {
        list.remove(newIndex);
        if (isFiltarable)
            backupList.remove(newIndex);
    }

    private void addItem(String key, T t, int newIndex) {
        list.remove(newIndex);
        list.add(newIndex, t);
        if (isFiltarable) {
            backupList.remove(newIndex);
            backupList.add(newIndex, t);
        }
    }

    private void addItem(String id, T t) {
        list.add(t);
        if (isFiltarable)
            backupList.add(t);
    }

    @Override
    public void onDataChanged() {
    }

    @Override
    public void onError(DatabaseError error) {
        Log.w(TAG, error.toException());
    }

    @Override
    public ObservableSnapshotArray<T> getSnapshots() {
        return mSnapshots;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, position, getItem(position));
    }

    /**
     * @param model the model object containing the data that should be used to populate the view.
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    protected abstract void onBindViewHolder(VH holder, int position, T model);

    /**
     * filter condition for Filter
     *
     * @param model         model T
     * @param filterPattern filter pattern with Lower Case
     */
    protected boolean filterCondition(T model, String filterPattern) {
        return true;
    }

    @Override
    public Filter getFilter() {
        if (mCustomFilter == null) {
            mCustomFilter = new CustomFilter();
        }
        return mCustomFilter;
    }

    public class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                results.values = backupList;
                results.count = backupList.size();
            } else {
                List<T> filteredList = new ArrayList<>();
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (T t : backupList) {
                    if (filterCondition(t, filterPattern)) {
                        filteredList.add(t);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends T>) results.values);
            notifyDataSetChanged();
        }
    }
}