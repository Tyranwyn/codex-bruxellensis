package com.fux.codexbruxellensis;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.SongFirebaseRecyclerAdapter;
import com.fux.codexbruxellensis.model.Category;
import com.fux.codexbruxellensis.model.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
// https://www.youtube.com/watch?v=Vyqz_-sJGFk

    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    NavigationView navigationView;
    @ViewById
    Toolbar toolbar;

    @ViewById
    RecyclerView songRecyclerView;
    @ViewById
    FloatingActionButton button;

    protected static FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected static DatabaseReference databaseReference;

    @AfterViews
    void databaseBinding() {
        database.setPersistenceEnabled(true);
        databaseReference = database.getReference();
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @AfterViews
    void navigationConfiguration() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            drawerLayout.closeDrawers();
            String itemTitle = item.getTitle().toString();
            if (itemTitle.equals("All"))
                attachRecyclerViewAdapter(databaseReference.child("songs"));
            else
                attachRecyclerViewAdapter(databaseReference.child("songs").orderByChild("category").equalTo(itemTitle.toUpperCase()));
            setTitle(itemTitle);
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getTitle().equals("All") || getTitle().equals("Codex Bruxellensis"))
            attachRecyclerViewAdapter(databaseReference.child("songs"));
        else
            attachRecyclerViewAdapter(databaseReference.child("songs").orderByChild("category").equalTo(getTitle().toString().toUpperCase()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attachRecyclerViewAdapter(Query songsQuery) {
        FirebaseRecyclerOptions<Song> songOptions =
                new FirebaseRecyclerOptions.Builder<Song>()
                        .setQuery(songsQuery, Song.class)
                        .setLifecycleOwner(this)
                        .build();
        RecyclerView.Adapter adapter = new SongFirebaseRecyclerAdapter(this, songOptions);
        songRecyclerView.setAdapter(adapter);
    }
}
