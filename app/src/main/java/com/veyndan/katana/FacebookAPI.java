package com.veyndan.katana;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FacebookAPI {

    public static final String ID_001 = "1690301937860688";
    public static final String ID_ME = ID_001;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ID_001, ID_ME})
    public @interface ID {}

    public static <T> T get(Context context, Uri request, Class<T> clazz) {
        int res = context.getResources().getIdentifier(
                resourceName(request), "raw", context.getPackageName());

        InputStream input = context.getResources().openRawResource(res);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        Gson gson = new GsonBuilder().create();
        T result = gson.fromJson(reader, clazz);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Gets the resource name of the request, where the resource name is always prepended
     * with "k_".
     *
     * @param request The request to get the resource name from.
     * @return The resource name associated with a resource in res/raw.
     */
    private static String resourceName(Uri request) {
        return TextUtils.join("_", request.getPathSegments());
    }

    public static class RequestBuilder {

        private static final String SCHEME = "https";
        private static final String AUTHORITY = "graph.facebook.com";
        private static final String VERSION = "v26"; // v2.6

        private static Uri.Builder base() {
            return new Uri.Builder().scheme(SCHEME).authority(AUTHORITY).appendPath(VERSION);
        }

        public static Uri feed(@ID String id) {
            return base().appendPath(id).appendPath("feed").build();
        }

        public static Uri picture(@ID String id) {
            return base().appendPath(id).appendPath("picture").build();
        }

    }
}
