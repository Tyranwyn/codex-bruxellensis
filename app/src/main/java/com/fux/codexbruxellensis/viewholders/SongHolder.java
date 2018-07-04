package com.fux.codexbruxellensis.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fux.codexbruxellensis.R;

public class SongHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private RelativeLayout parentLayout;

    public SongHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        parentLayout = itemView.findViewById(R.id.parentLayout);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public RelativeLayout getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(RelativeLayout parentLayout) {
        this.parentLayout = parentLayout;
    }
}
