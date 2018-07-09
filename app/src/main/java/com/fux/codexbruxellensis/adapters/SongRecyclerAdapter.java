package com.fux.codexbruxellensis.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.fux.codexbruxellensis.R;
import com.fux.codexbruxellensis.SongDetailActivity_;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SongRecyclerAdapter extends RecyclerView.Adapter<SongHolder> implements Filterable {

    private Context context;
    private List<Song> songList;
    private List<Song> songListFiltered;
    private SharedPreferences preferences;

    public SongRecyclerAdapter(Context context, List<Song> songList, SharedPreferences preferences) {
        this.context = context;
        this.songList = songList;
        this.songListFiltered = songList;
        this.preferences = preferences;
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        final Song currentSong = songListFiltered.get(position);
        holder.getTitle()
                .setText(currentSong.getAssociationName().isEmpty()
                        ? currentSong.getTitle()
                        : currentSong.getAssociationName());
        holder.getPageNumber()
                .setText(currentSong.getPage().toString());

        holder.getFavoriteToggleButton().setChecked(currentSong.isFavorite());
        SharedPreferences.Editor editor = preferences.edit();
        holder.getFavoriteToggleButton()
                .setOnClickListener(v -> {
                    Set<String> tempFavorites = new HashSet<>(preferences.getStringSet("favorites", new HashSet<>()));
                    if (holder.getFavoriteToggleButton().isChecked()) {
                        tempFavorites.add(currentSong.getId());
                    }
                    else {
                        tempFavorites.remove(currentSong.getId());
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
    public int getItemCount() {
        return songListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty())
                    songListFiltered = songList;
                else {
                    List<Song> filteredList = new ArrayList<>();
                    songList.forEach(song -> {
                        if (filterCondition(song, charString))
                            filteredList.add(song);
                    });

                    songListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = songListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                songListFiltered = (ArrayList<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private boolean filterCondition(Song model, String filterPattern) {
        return model.getTitle().toLowerCase().contains(filterPattern) ||
                model.getAssociationName().toLowerCase().contains(filterPattern) ||
                model.getPage().toString().contains(filterPattern);
    }
}
