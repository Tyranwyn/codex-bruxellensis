package com.fux.codex.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.fux.codex.R;

public class SongHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView pageNumber;
    private ToggleButton favoriteToggleButton;
    private LinearLayout parentLayout;

    public SongHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.listTitle);
        pageNumber = itemView.findViewById(R.id.listPageNumber);
        favoriteToggleButton = itemView.findViewById(R.id.favoriteToggleButton);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getPageNumber() {
        return pageNumber;
    }

    public SongHolder setPageNumber(TextView pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public ToggleButton getFavoriteToggleButton() {
        return favoriteToggleButton;
    }

    public SongHolder setFavoriteToggleButton(ToggleButton favoriteToggleButton) {
        this.favoriteToggleButton = favoriteToggleButton;
        return this;
    }

    public LinearLayout getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(LinearLayout parentLayout) {
        this.parentLayout = parentLayout;
    }
}
