package com.veyndan.katana;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VH extends RecyclerView.ViewHolder {

    @BindView(R.id.profile) ImageView profile;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.like) ToggleButton like;

    @BindView(R.id.text) TextView text;
    @BindView(R.id.image) ImageView image;

    public VH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
