package com.fux.codexbruxellensis;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fux.codexbruxellensis.adapters.RecyclerViewAdapter;
import com.fux.codexbruxellensis.model.Song;
import com.fux.codexbruxellensis.services.SampleSongFinder;
import com.fux.codexbruxellensis.services.SongFinder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
// https://www.youtube.com/watch?v=Vyqz_-sJGFk
    private static final String TAG = "MainActivity";

    private ArrayList<Song> mSongs;

    @ViewById
    RecyclerView songRecyclerView;

    @AfterViews
    void bindAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mSongs);
        songRecyclerView.setAdapter(adapter);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started.");
        SongFinder songFinder = new SampleSongFinder();
        mSongs = songFinder.findAll();

    }
    
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
    }

    /*@ViewById
    ListView personList;
    @Bean
    PersonListAdapter adapter;

    @AfterViews
    void bindAdapter() {
        personList.setAdapter(adapter);
    }

    @ItemClick
    void personListItemClicked(Person person) {
        makeText(this, person.getFirstName() + " " + person.getLastName(), Toast.LENGTH_SHORT).show();
    }*/
}
