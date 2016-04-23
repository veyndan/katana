package com.veyndan.katana;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        String profileUrl = "https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/12923224_201256241563" +
                "4637_6490425404654077477_n.jpg?oh=8b2c57c4d2ec59ba0605d28fe68a1e5c&oe=57B59F67";
        String imageUrl = "https://scontent-lhr3-1.xx.fbcdn.net/hphotos-xaf1/t31.0-8/12916955_20" +
                "16582558565956_3030515090458717633_o.jpg";
        posts.add(new Post(profileUrl, "Veyndan Stuart", "1 Apr at 15:17", imageUrl));

        profileUrl = "https://images.unsplash.com/photo-1456769355437-50afa71c8863?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=c9119822a43b8fbf134705964b90148c";
        imageUrl = profileUrl;
        posts.add(new Post(profileUrl, "Roksolana Zasiadko updated her profile picture.", "28 Mar at 12:54", imageUrl));
        return posts;
    }
}
