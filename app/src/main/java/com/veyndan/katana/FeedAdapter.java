package com.veyndan.katana;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<VH> {

    private final List<Post> posts;

    public FeedAdapter(Context context, Feed feed) {
        List<Post> posts = new ArrayList<>();

        String profileUrl, text;

        Feed.Data data = feed.data[0];

        profileUrl = FacebookAPI.get(context, FacebookAPI.RequestBuilder.picture(data.from.id), Picture.class).data.url;
        Post.Description description = new Post.Description("Couldn't be happier.", data.picture);
        posts.add(new Post(context, profileUrl, data.from.name, Post.NONE, data.created_time, description));

        profileUrl = "https://images.unsplash.com/photo-1456769355437-50afa71c8863?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=c9119822a43b8fbf134705964b90148c";
        description = new Post.Description(null, profileUrl);
        posts.add(new Post(context, profileUrl, "Roksolana Zasiadko", Post.UPDATE_PROFILE_PICTURE, "28 Mar at 12:54", description));

        profileUrl = "https://images.unsplash.com/photo-1460752652228-71887ef91aa4?ixlib=rb-0.3." +
                "5&q=80&fm=jpg&crop=entropy&s=091cb2288d1e101559437c487058edd1";
        text = "Calm down mechanic guy. Just here for an oil change. If I wanted to know about " +
                "all the other shit wrong with my car I'd turn the radio down.";
        description = new Post.Description(text, null);
        posts.add(new Post(context, profileUrl, "Sergey Svechnikov", Post.NONE, "27 Mar at 22:03", description));

        this.posts = posts;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        final Post post = posts.get(position);
        final Context context = holder.itemView.getContext();
        Glide.with(context).load(post.getProfileUrl()).into(holder.profile);
        holder.name.setText(post.getType());
        holder.like.setSelected(post.isLiked());

        Post.Description description = post.getDescription();
        String text = description.getText();
        String imageUrl = description.getImageUrl();

        if (text != null) {
            holder.text.setText(text);
            holder.text.setVisibility(View.VISIBLE);
        } else {
            holder.text.setVisibility(View.GONE);
        }
        if (imageUrl != null) {
            Glide.with(context).load(post.getDescription().getImageUrl()).into(holder.image);
            holder.image.setVisibility(View.VISIBLE);
        } else {
            holder.image.setVisibility(View.GONE);
        }

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.get(holder.getAdapterPosition()).setLiked(holder.like.isSelected());
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
