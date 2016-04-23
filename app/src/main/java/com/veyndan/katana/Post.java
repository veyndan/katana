package com.veyndan.katana;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Post {

    public static final String NONE = "%s";
    public static final String UPDATE_PROFILE_PICTURE = "%s updated her profile picture.";

    @StringDef({NONE, UPDATE_PROFILE_PICTURE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}

    private final String profileUrl;
    private final String name;
    private final Spannable type;
    private final String date;
    private final String imageUrl;
    private boolean liked;

    StyleSpan boldSpan;
    ForegroundColorSpan secondaryColorSpan;

    public Post(Context context, String profileUrl, String name, @Type String type, String date,
                String imageUrl) {
        boldSpan = new StyleSpan(Typeface.BOLD);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(android.R.attr.textColorSecondary, typedValue, true);
        secondaryColorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, typedValue.resourceId));

        this.profileUrl = profileUrl;
        this.name = name;
        this.type = initializeType(type);
        this.date = date;
        this.imageUrl = imageUrl;
        this.liked = false;
    }

    private Spannable initializeType(@Type String type) {
        switch (type) {
            case NONE:
                SpannableStringBuilder builder = new SpannableStringBuilder(name);
                builder.setSpan(boldSpan, 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return builder;
            case UPDATE_PROFILE_PICTURE:
                builder = new SpannableStringBuilder(name);
                builder.setSpan(boldSpan, 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(" updated her profile picture.");
                return builder;
        }
        return null;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getName() {
        return name;
    }

    public Spannable getType() {
        return type;
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
