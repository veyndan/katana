package com.veyndan.katana;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

        String profileUrl, imageUrl, text;

        profileUrl = "https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/12923224_201256241563" +
                "4637_6490425404654077477_n.jpg?oh=8b2c57c4d2ec59ba0605d28fe68a1e5c&oe=57B59F67";
        imageUrl = "https://scontent-lhr3-1.xx.fbcdn.net/hphotos-xaf1/t31.0-8/12916955_20" +
                "16582558565956_3030515090458717633_o.jpg";
        Post.Description description = new Post.Description("Couldn't be happier.", imageUrl);
        posts.add(new Post(this, profileUrl, "Veyndan Stuart", Post.NONE, "1 Apr at 15:17", description));

        profileUrl = "https://images.unsplash.com/photo-1456769355437-50afa71c8863?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=c9119822a43b8fbf134705964b90148c";
        imageUrl = profileUrl;
        description = new Post.Description(null, imageUrl);
        posts.add(new Post(this, profileUrl, "Roksolana Zasiadko", Post.UPDATE_PROFILE_PICTURE, "28 Mar at 12:54", description));

        profileUrl = "https://images.unsplash.com/photo-1460752652228-71887ef91aa4?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=091cb2288d1e101559437c487058edd1";
        text = "Calm down mechanic guy. Just here for an oil change. If I wanted to know about " +
                "all the other shit wrong with my car I'd turn the radio down.";
        description = new Post.Description(text, null);
        posts.add(new Post(this, profileUrl, "Sergey Svechnikov", Post.NONE, "27 Mar at 22:03", description));

        return posts;
    }
}
