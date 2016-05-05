package com.veyndan.katana;

public class Picture {

    Data data;

    public static class Data {

        boolean is_silhouette;
        String url;

        @Override
        public String toString() {
            return "Data{" +
                    "is_silhouette=" + is_silhouette +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Picture{" +
                "data=" + data +
                '}';
    }
}
