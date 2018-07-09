package com.fux.codexbruxellensis;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.fux.codexbruxellensis.adapters.SongFirebaseRecyclerAdapter;
import com.fux.codexbruxellensis.model.Preferences_;
import com.fux.codexbruxellensis.model.Song;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

    @Pref
    static Preferences_ preferences;

    protected static FirebaseDatabase database;
    protected static DatabaseReference databaseReference;
    protected SongFirebaseRecyclerAdapter adapter;
    private SearchView searchView;

    private static boolean cantusModus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(cantusModus ? R.style.Theme_AppCompat_NoActionBar : R.style.Theme_AppCompat_DayNight_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @AfterViews
    void drawButtonIcon() {
        button.setImageResource(cantusModus ? R.drawable.ic_day_24px :  R.drawable.ic_night_24px);
    }

    @AfterViews
    void databaseBinding() {
        if (null == database) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }

        databaseReference = database.getReference();
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setVerticalFadingEdgeEnabled(true);

        if (getTitle().equals(getResources().getString(R.string.menu_all)) || getTitle().equals(getResources().getString(R.string.app_name)))
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

        navigationView.setNavigationItemSelectedListener(item -> {
            clearSearchBar();

            item.setChecked(true);
            drawerLayout.closeDrawers();
            String itemTitle = item.getTitle().toString();
            if (itemTitle.equals(getResources().getString(R.string.menu_all))) {
                adapter.stopListening();
                attachRecyclerViewAdapter(databaseReference.child("songs"));
                adapter.startListening();
            } else {
                adapter.stopListening();
                attachRecyclerViewAdapter(databaseReference.child("songs").orderByChild("category").equalTo(itemTitle.toUpperCase()));
                adapter.startListening();
            }
            setTitle(itemTitle);
            return true;
        });
    }

    private void clearSearchBar() {
        searchView.setQuery("", false);
        searchView.clearFocus();
        searchView.setIconified(true);
    }

    @Click(R.id.button)
    void activateCantusModus() {
        cantusModus = !cantusModus;
        recreate();
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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
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
        adapter = new SongFirebaseRecyclerAdapter(this, songOptions, preferences);
        songRecyclerView.setAdapter(adapter);
    }

    private Query createCategoryQueryBasedOnTitle() {
        return databaseReference.child("songs").orderByChild("category").equalTo(getTitle().toString().toUpperCase());
    }

    public static boolean isCantusModus() {
        return cantusModus;
    }

}
