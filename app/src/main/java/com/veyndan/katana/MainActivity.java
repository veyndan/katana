package com.veyndan.katana;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_facebook_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerView.smoothScrollToPosition(0);
                }
            });
        }

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(null);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FeedAdapter(init()));
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
                // IMPORTANT: Allows animations in RecyclerView e.g. ImageView spring, to continue
                // on notifying data set change.
                return true;
            }
        });
    }

    private List<Post> init() {
        List<Post> posts = new ArrayList<>();

        String profileUrl, text;

        Feed feed = FacebookAPI.get(this, FacebookAPI.RequestBuilder.feed(FacebookAPI.ID_ME), Feed.class);

        Feed.Data data = feed.data[0];

        profileUrl = FacebookAPI.get(this, FacebookAPI.RequestBuilder.picture(data.from.id), Picture.class).data.url;
        Post.Description description = new Post.Description("Couldn't be happier.", data.picture);
        posts.add(new Post(this, profileUrl, data.from.name, Post.NONE, data.created_time, description));

        profileUrl = "https://images.unsplash.com/photo-1456769355437-50afa71c8863?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=c9119822a43b8fbf134705964b90148c";
        description = new Post.Description(null, profileUrl);
        posts.add(new Post(this, profileUrl, "Roksolana Zasiadko", Post.UPDATE_PROFILE_PICTURE, "28 Mar at 12:54", description));

        profileUrl = "https://images.unsplash.com/photo-1460752652228-71887ef91aa4?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=091cb2288d1e101559437c487058edd1";
        text = "Calm down mechanic guy. Just here for an oil change. If I wanted to know about " +
                "all the other shit wrong with my car I'd turn the radio down.";
        description = new Post.Description(text, null);
        posts.add(new Post(this, profileUrl, "Sergey Svechnikov", Post.NONE, "27 Mar at 22:03", description));


        return posts;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
