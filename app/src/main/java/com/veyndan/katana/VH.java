package com.veyndan.katana;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VH extends RecyclerView.ViewHolder {

    @Bind(R.id.profile) ImageView profile;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.like) ToggleButton like;

    public VH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
