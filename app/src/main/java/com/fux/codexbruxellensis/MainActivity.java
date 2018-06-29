package com.fux.codexbruxellensis;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fux.codexbruxellensis.adapters.RecyclerViewAdapter;
import com.fux.codexbruxellensis.model.Song;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

import static android.widget.Toast.makeText;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
// https://www.youtube.com/watch?v=Vyqz_-sJGFk
    private static final String TAG = "MainActivity";

    private ArrayList<Song> mSongs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started.");

        initRecyclerView();
    }
    
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.songRecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mSongs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
