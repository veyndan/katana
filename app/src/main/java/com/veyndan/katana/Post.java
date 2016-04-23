package com.veyndan.katana;

public class Post {

    private final String profileUrl;
    private final String name;
    private final String date;
    private final String imageUrl;
    private boolean liked;

    public Post(String profileUrl, String name, String date, String imageUrl) {
        this.profileUrl = profileUrl;
        this.name = name;
        this.date = date;
        this.imageUrl = imageUrl;
        this.liked = false;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
