package com.veyndan.katana;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.StringDef;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class FacebookAPI {

    public static final String ID_001 = "1690301937860688";
    public static final String ID_ME = ID_001;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ID_001, ID_ME})
    public @interface ID {}

    public static <T> T get(Context context, Uri request, Class<T> clazz) {
        List<String> pathSegments = request.getPathSegments();

        StringBuilder stringBuilder = new StringBuilder("k");

        for (String pathSegment : pathSegments) {
            stringBuilder.append("_");
            stringBuilder.append(pathSegment);
        }

        int res = context.getResources().getIdentifier(
                stringBuilder.toString(), "raw", context.getPackageName());

        InputStream inputStream = context.getResources().openRawResource(res);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Gson gson = new GsonBuilder().create();
        T result = gson.fromJson(reader, clazz);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static class RequestBuilder {

        private static final String SCHEME = "https";
        private static final String AUTHORITY = "graph.facebook.com";

        private static Uri.Builder base() {
            return new Uri.Builder().scheme(SCHEME).authority(AUTHORITY);
        }

        public static Uri feed(@ID String id) {
            return base().appendPath(id).appendPath("feed").build();
        }

        public static Uri picture(@ID String id) {
            return base().appendPath(id).appendPath("picture").build();
        }

    }
}
