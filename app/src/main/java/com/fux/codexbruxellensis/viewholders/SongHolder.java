package com.fux.codexbruxellensis.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fux.codexbruxellensis.R;

public class SongHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView pageNumber;
    private LinearLayout parentLayout;

    public SongHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.listTitle);
        pageNumber = itemView.findViewById(R.id.listPageNumber);
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

    public LinearLayout getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(LinearLayout parentLayout) {
        this.parentLayout = parentLayout;
    }
}
