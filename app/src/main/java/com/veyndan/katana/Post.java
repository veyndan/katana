package com.veyndan.katana;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineHeightSpan;
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
    AbsoluteSizeSpan absoluteSizeSpan12;
    int dateLineSpacing;

    public Post(Context context, String profileUrl, String name, @Type String type, String date,
                String imageUrl) {
        boldSpan = new StyleSpan(Typeface.BOLD);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(android.R.attr.textColorSecondary, typedValue, true);
        secondaryColorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, typedValue.resourceId));
        absoluteSizeSpan12 = new AbsoluteSizeSpan((int) UIUtils.spToPx(context, 12));
        dateLineSpacing = UIUtils.dpToPx(context, 4);

        this.profileUrl = profileUrl;
        this.name = name;
        this.date = date;
        this.type = initializeType(type);
        this.imageUrl = imageUrl;
        this.liked = false;
    }

    /**
     * Make spannable for the name view section.
     */
    private Spannable initializeType(@Type String type) {
        SpannableStringBuilder builder = new SpannableStringBuilder(name);
        final int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

        builder.setSpan(boldSpan, 0, name.length(), flag);
        switch (type) {
            case NONE:
                break;
            case UPDATE_PROFILE_PICTURE:
                builder.append(" updated her profile picture.");
                break;
            default:
                throw new IllegalStateException("Must be of type @Type");
        }
        builder.append("\n");
        builder.append(date);
        int dateEnd = builder.length();
        int dateStart = dateEnd - date.length();
        builder.setSpan(secondaryColorSpan, dateStart, dateEnd, flag);
        builder.setSpan(absoluteSizeSpan12, dateStart, dateEnd, flag);

        builder.setSpan(new LineHeightSpan() {
            @Override
            public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v,
                                     Paint.FontMetricsInt fm) {
                fm.ascent -= dateLineSpacing;
            }
        }, dateStart, dateEnd, flag);

        return builder;
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
