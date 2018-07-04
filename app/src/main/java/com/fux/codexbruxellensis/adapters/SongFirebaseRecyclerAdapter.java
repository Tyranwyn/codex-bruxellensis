package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;

public class SongFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Song, SongHolder> {

    private Context context;

    public SongFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Song> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull Song model) {
        holder.getTitle().setText(model.getAssociationName());
        holder.getParentLayout()
                .setOnClickListener(view -> Toast.makeText(context, model.getAssociationInfo(), Toast.LENGTH_LONG).show());
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_song_item, parent, false);
        return new SongHolder(view);
    }
}
