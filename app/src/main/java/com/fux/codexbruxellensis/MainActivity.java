package com.fux.codexbruxellensis;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.SongFirebaseRecyclerAdapter;
import com.fux.codexbruxellensis.model.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
// https://www.youtube.com/watch?v=Vyqz_-sJGFk

    @ViewById
    RecyclerView songRecyclerView;
    FirebaseRecyclerAdapter adapter;

    @AfterViews
    void bindAdapter() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference();

        Query songsQuery = databaseReference.child("songs");

        FirebaseRecyclerOptions<Song> songOptions =
                new FirebaseRecyclerOptions.Builder<Song>()
                .setQuery(songsQuery, Song.class)
                .build();

        adapter = new SongFirebaseRecyclerAdapter(this, songOptions);
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
        adapter.stopListening();
    }

}
