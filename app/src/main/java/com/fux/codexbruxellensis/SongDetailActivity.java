package com.fux.codexbruxellensis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fux.codexbruxellensis.model.Song;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class SongDetailActivity extends AppCompatActivity {

    @Extra
    Song currentSong;
    @ViewById
    TextView detailAssociationName;
    @ViewById
    TextView detailAssociationInfo;
    @ViewById
    TextView detailTitle;
    @ViewById
    TextView detailBgInfo;
    @ViewById
    TextView detailLyrics;
    @ViewById
    TextView detailBattleCryName;
    @ViewById
    TextView detailBattleCryInfo;
    @ViewById
    TextView detailBattleCry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            setTheme(MainActivity.isCantusModus() ? R.style.Theme_AppCompat_NoActionBar : R.style.Theme_AppCompat_DayNight_NoActionBar);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_song_detail);
    }

    @AfterViews
    public void bindFields() {
        setTitle(currentSong.getAssociationName().isEmpty() ? currentSong.getTitle() : currentSong.getAssociationName());
        setFields();
        removeUnusedFields();
    }

    private void removeUnusedFields() {
        if (currentSong.getAssociationName().isEmpty()) detailAssociationName.setVisibility(View.GONE);
        if (currentSong.getAssociationInfo().isEmpty()) detailAssociationInfo.setVisibility(View.GONE);

        if (currentSong.getTitle().isEmpty()) detailTitle.setVisibility(View.GONE);
        if (currentSong.getBgInfo().isEmpty()) detailBgInfo.setVisibility(View.GONE);
        if (currentSong.getLyrics().isEmpty()) detailLyrics.setVisibility(View.GONE);

        if (currentSong.getBattleCryName().isEmpty()) detailBattleCryName.setVisibility(View.GONE);
        if (currentSong.getBattleCryInfo().isEmpty()) detailBattleCryInfo.setVisibility(View.GONE);
        if (currentSong.getBattleCry().isEmpty()) detailBattleCry.setVisibility(View.GONE);
    }

    private void setFields() {
        detailAssociationName.setText(currentSong.getAssociationName());
        detailAssociationInfo.setText(currentSong.getAssociationInfo());

        detailTitle.setText(currentSong.getTitle());
        detailBgInfo.setText(currentSong.getBgInfo());
        detailLyrics.setText(currentSong.getLyrics());

        detailBattleCryName.setText(currentSong.getBattleCryName());
        detailBattleCryInfo.setText(currentSong.getBattleCryInfo());
        detailBattleCry.setText(currentSong.getBattleCry());
    }
}
