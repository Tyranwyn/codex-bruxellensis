package com.fux.codexbruxellensis;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fux.codexbruxellensis.model.Song;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_song_detail)
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
