package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.SongDetailActivity_;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;

public class SongFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Song, SongHolder> {

    private Context context;

    public SongFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Song> options) {
        super(options, true);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull Song currentSong) {
        holder.getTitle()
                .setText(currentSong.getAssociationName().isEmpty() ? currentSong.getTitle() : currentSong.getAssociationName());
        holder.getPageNumber()
                .setText(currentSong.getPage().toString());
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
