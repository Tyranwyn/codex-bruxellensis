package brussels.codex;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import brussels.codex.adapters.SongRecyclerAdapter;
import brussels.codex.model.Category;
import brussels.codex.model.Song;
import com.google.firebase.firestore.FirebaseFirestore;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    protected FirebaseFirestore database;
    protected SongRecyclerAdapter adapter;

    private static SharedPreferences sharedPreferences;
    private SearchView searchView;
    private List<Song> songList = new ArrayList<>();

    private static boolean cantusModus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(cantusModus ? R.style.Theme_AppCompat_NoActionBar : R.style.Theme_AppCompat_DayNight_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @AfterViews
    void getPreferences() {
        sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    @AfterViews
    void drawButtonIcon() {
        button.setImageResource(cantusModus ? R.drawable.ic_day_24px : R.drawable.ic_night_24px);
    }

    @AfterViews
    void databaseBinding() {
        if (null == database) {
            database = FirebaseFirestore.getInstance();
        }

        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setVerticalFadingEdgeEnabled(true);

        database.collection("songs")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        task.getResult()
                                .forEach(document -> songList.add(
                                        document.toObject(Song.class)
                                                .setId(document.getId())
                                                .setFavorite(sharedPreferences.getStringSet("favorites", new HashSet<>()).contains(document.getId()))
                                ));
                    else
                        Log.d(TAG, "Error getting documents: " + task.getException());
                    songList.sort(Comparator.comparing(Song::getPage));
                    renderRecyclerView(songList);
                });
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
                updateFavorites();
                renderRecyclerView(songList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_favorites))) {
                updateFavorites();
                renderRecyclerView(songList.stream().filter(Song::isFavorite)
                        .collect(Collectors.toList()));
            } else if (itemTitle.equals(getResources().getString(R.string.menu_association))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.ASSOCIATION.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_dutch))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.DUTCH.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_english))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.ENGLISH.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_french))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.FRENCH.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_foreign))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.FOREIGN.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_german))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.GERMAN.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
            } else if (itemTitle.equals(getResources().getString(R.string.menu_official))) {
                updateFavorites();
                List<Song> tempList = songList.stream()
                        .filter(song -> song.getCategory().toString().equals(Category.OFFICIAL.toString()))
                        .collect(Collectors.toList());
                renderRecyclerView(tempList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_view, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (null != adapter)
                    adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (null != adapter)
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

    private void renderRecyclerView(List<Song> songList) {
        adapter = new SongRecyclerAdapter(this, songList, sharedPreferences);
        songRecyclerView.setAdapter(adapter);
    }

    public static boolean isCantusModus() {
        return cantusModus;
    }

    private void updateFavorites() {
        songList = songList.stream()
                .map(song -> song.setFavorite(
                        sharedPreferences.getStringSet("favorites", new HashSet<>())
                                .contains(song.getId())))
                .collect(Collectors.toList());
    }

}
