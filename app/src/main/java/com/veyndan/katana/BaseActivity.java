package com.veyndan.katana;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    @Nullable Toolbar toolbar;

    @Nullable
    protected Toolbar getToolbar() {
        if (toolbar == null) {
            toolbar = ButterKnife.findById(this, R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
    }
}
