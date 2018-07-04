package com.fux.codexbruxellensis;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.AssociationSongFirebaseRecyclerAdapter;
import com.fux.codexbruxellensis.model.AssociationSong;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.viewholders.SongHolder;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

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
        adapter = new AssociationSongFirebaseRecyclerAdapter(this, options);
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
