package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.MainActivity;
import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.SongDetailActivity_;
import com.fux.codexbruxellensis.model.Preferences_;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;

import org.androidannotations.annotations.EBean;

import java.util.Set;

public class SongFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Song, SongHolder> {

    private Context context;
    private Preferences_ preferences;

    public SongFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Song> options, Preferences_ preferences) {
        super(options, true);
        this.context = context;
        this.preferences = preferences;
    }

    @Override
    protected void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull Song currentSong) {
        holder.getTitle()
                .setText(currentSong.getAssociationName().isEmpty() ? currentSong.getTitle() : currentSong.getAssociationName());
        holder.getPageNumber()
                .setText(currentSong.getPage().toString());

        Set<String> favoredSongs = preferences.favorites().get();
        holder.getFavoriteToggleButton().setChecked(favoredSongs.contains(currentSong.getTitle()));

        holder.getFavoriteToggleButton()
                .setOnClickListener(v -> {
                    if (holder.getFavoriteToggleButton().isChecked())
                        favoredSongs.add(currentSong.getTitle());
                    else
                        favoredSongs.remove(currentSong.getTitle());
                    preferences.favorites().put(favoredSongs);
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
