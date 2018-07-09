package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.SongDetailActivity_;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;

import java.util.HashSet;
import java.util.Set;

public class SongFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Song, SongHolder> {

    private static final String TAG = "SongFirebaseRecyclerAdapter";
    private Context context;
    private SharedPreferences preferences;
    private Set<String> initFavorites;

    public SongFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Song> options,
                                       SharedPreferences preferences, Set<String> initFavorites) {
        super(options, true);
        this.context = context;
        this.preferences = preferences;
        this.initFavorites = initFavorites;
    }

    @Override
    protected void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull Song currentSong) {
        Log.d(TAG, "onBindViewHolder: itemcount: " + getItemCount());
        holder.getTitle()
                .setText(currentSong.getAssociationName().isEmpty() ? currentSong.getTitle() : currentSong.getAssociationName());
        holder.getPageNumber()
                .setText(currentSong.getPage().toString());

        holder.getFavoriteToggleButton().setChecked(initFavorites.contains(currentSong.getTitle()));

        SharedPreferences.Editor editor = preferences.edit();

        holder.getFavoriteToggleButton()
                .setOnClickListener(v -> {
                    Set<String> tempFavorites = new HashSet<>(preferences.getStringSet("favorites", new HashSet<>()));
                    if (holder.getFavoriteToggleButton().isChecked()) {
                        tempFavorites.add(currentSong.getTitle());
                        currentSong.setFavorite(true);
                    }
                    else {
                        tempFavorites.remove(currentSong.getTitle());
                        currentSong.setFavorite(false);
                    }
                    editor.putStringSet("favorites", tempFavorites);
                    editor.apply();
                });

        holder.getParentLayout()
                .setOnClickListener(view ->
                    SongDetailActivity_.intent(context)
                            .currentSong(currentSong)
                            .start()
                );
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_song_item, parent, false);
        return new SongHolder(view);
    }

    @Override
    protected boolean filterCondition(Song model, String filterPattern) {
        return model.getTitle().toLowerCase().contains(filterPattern) ||
                model.getAssociationName().toLowerCase().contains(filterPattern) ||
                model.getPage().toString().contains(filterPattern);
    }
}
