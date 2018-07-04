package com.fux.codexbruxellensis;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.RecyclerViewAdapter;
import com.fux.codexbruxellensis.model.AssociationSong;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.services.SampleSongFinder;
import com.fux.codexbruxellensis.services.SongFinder;
import com.fux.codexbruxellensis.viewholders.SongHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.makeText;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
// https://www.youtube.com/watch?v=Vyqz_-sJGFk
    private static final String TAG = "MainActivity";

    private ArrayList<Song> mSongs = new ArrayList<>();

    @ViewById
    RecyclerView songRecyclerView;
    FirebaseRecyclerAdapter adapter;

    @AfterViews
    void bindAdapter() {
        Query query = FirebaseDatabase.getInstance()
                .getReference("songs")
                .child("association");
        FirebaseRecyclerOptions<AssociationSong> options =
                new FirebaseRecyclerOptions.Builder<AssociationSong>()
                .setQuery(query, AssociationSong.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<AssociationSong, SongHolder>(options) {

            @NonNull
            @Override
            public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder: created viewholder");
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_song_item, parent, false);
                return new SongHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull AssociationSong model) {
                holder.getTitle().setText(model.getAssociationName());
//                holder.getParentLayout().setOnClickListener(view -> {
//                    Log.d(TAG, "onClick: clicked on: " + mSongs.get(position).getTitle());
//                    Toast.makeText(this , mSongs.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//                });
            }
        };
        songRecyclerView.setAdapter(adapter);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: stopping");
        adapter.stopListening();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
    }
}
