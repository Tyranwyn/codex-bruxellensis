package com.fux.codexbruxellensis;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.SongFirebaseRecyclerAdapter;
import com.fux.codexbruxellensis.model.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    NavigationView navigationView;
    @ViewById
    Toolbar toolbar;

    @ViewById
    RecyclerView songRecyclerView;

    protected static FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected static DatabaseReference databaseReference;
    protected SongFirebaseRecyclerAdapter adapter;

    @AfterViews
    void databaseBinding() {
        database.setPersistenceEnabled(true);
        databaseReference = database.getReference();
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setVerticalFadingEdgeEnabled(true);

        if (getTitle().equals("All") || getTitle().equals("Codex Bruxellensis"))
            attachRecyclerViewAdapter(databaseReference.child("songs"));
        else
            attachRecyclerViewAdapter(createCategoryQueryBasedOnTitle());
        adapter.startListening();
    }

    @AfterViews
    void navigationConfiguration() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setCustomView(R.menu.actionbar_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            drawerLayout.closeDrawers();
            String itemTitle = item.getTitle().toString();
            if (itemTitle.equals("All")) {
                adapter.stopListening();
                attachRecyclerViewAdapter(databaseReference.child("songs"));
                adapter.startListening();
            }
            else {
                adapter.stopListening();
                attachRecyclerViewAdapter(databaseReference.child("songs").orderByChild("category").equalTo(itemTitle.toUpperCase()));
                adapter.startListening();
            }
            setTitle(itemTitle);
            return true;
        });
    }

    @AfterViews
    void configureSearch() {
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println(query);
            adapter.getFilter().filter(query);
        }
    }

    @Click(R.id.button)
    void activateCantusModus() {
    }

    /*@AfterViews
    void runner() {
        final Handler handler = new Handler();
        class MyRunnable implements Runnable {
            private Handler handler;
            private RecyclerView songRecyclerView;
            public MyRunnable(Handler handler, RecyclerView songRecyclerView) {
                this.handler = handler;
                this.songRecyclerView = songRecyclerView;
            }
            @Override
            public void run() {
                this.handler.postDelayed(this, 500);
                System.out.println("offset: " + songRecyclerView.computeVerticalScrollOffset());
            }
        }
//        handler.post(new MyRunnable(handler, songRecyclerView));
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_view, menu);
        return true;
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
                        .build();
        adapter = new SongFirebaseRecyclerAdapter(this, songOptions);
        songRecyclerView.setAdapter(adapter);
    }

    private Query createCategoryQueryBasedOnTitle() {
        return databaseReference.child("songs").orderByChild("category").equalTo(getTitle().toString().toUpperCase());
    }
}
