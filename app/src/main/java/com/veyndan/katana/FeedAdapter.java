package com.veyndan.katana;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<VH> {

    private final List<Post> posts;

    public FeedAdapter(List<Post> posts) {
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
        holder.date.setText(post.getDate());
        Glide.with(context).load(post.getImageUrl()).into(holder.image);
        holder.like.setSelected(post.isLiked());

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
