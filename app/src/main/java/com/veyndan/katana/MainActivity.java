package com.veyndan.katana;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        posts.add(new Post("https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/12923224_2012562415634" +
                "637_6490425404654077477_n.jpg?oh=8b2c57c4d2ec59ba0605d28fe68a1e5c&oe=57B59F67",
                "Veyndan Stuart", "1 Apr at 15:17",
                "https://scontent-lhr3-1.xx.fbcdn.net/hphotos-xaf1/t31.0-8/12916955_201" +
                        "6582558565956_3030515090458717633_o.jpg"));
        posts.add(new Post("https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/12923224_2012562415634" +
                "637_6490425404654077477_n.jpg?oh=8b2c57c4d2ec59ba0605d28fe68a1e5c&oe=57B59F67",
                "Veyndan Stuart", "1 Apr at 15:17",
                "https://scontent-lhr3-1.xx.fbcdn.net/hphotos-xaf1/t31.0-8/12916955_201" +
                        "6582558565956_3030515090458717633_o.jpg"));
        return posts;
    }
}
