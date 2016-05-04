package com.veyndan.katana;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public final class Home {

    Data[] data;

    public static class Data {

        String id;
        From from;
        String story;
        StoryTags story_tags;
        String picture;
        String link;
        String name;
        String icon;
        Actions[] actions;
        Privacy privacy;
        String type;
        String status_type;
        String object_id;
        String created_time;
        String updated_time;
        boolean is_hidden;
        boolean subscribed;
        boolean is_expired;

        public static class From {

            String name;
            String id;

            @Override
            public String toString() {
                return "From{" +
                        "name='" + name + '\'' +
                        ", id='" + id + '\'' +
                        '}';
            }
        }

        public static class StoryTags {

            @SerializedName("0") Something[] zero;

            public static class Something {

                String id;
                String name;
                String type;
                int offset;
                int length;

                @Override
                public String toString() {
                    return "StoryTags{" +
                            "id='" + id + '\'' +
                            ", name='" + name + '\'' +
                            ", type='" + type + '\'' +
                            ", offset=" + offset +
                            ", length=" + length +
                            '}';
                }

            }

            @Override
            public String toString() {
                return "StoryTags{" +
                        "zero=" + Arrays.toString(zero) +
                        '}';
            }
        }

        public static class Actions {

            String name;
            String link;

            @Override
            public String toString() {
                return "Actions{" +
                        "name='" + name + '\'' +
                        ", link='" + link + '\'' +
                        '}';
            }
        }

        public static class Privacy {

            String value;
            String description;
            String friends;
            String allow;
            String deny;

            @Override
            public String toString() {
                return "Privacy{" +
                        "value='" + value + '\'' +
                        ", description='" + description + '\'' +
                        ", friends='" + friends + '\'' +
                        ", allow='" + allow + '\'' +
                        ", deny='" + deny + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", from=" + from +
                    ", story='" + story + '\'' +
                    ", story_tags=" + story_tags +
                    ", picture='" + picture + '\'' +
                    ", link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    ", actions=" + Arrays.toString(actions) +
                    ", privacy=" + privacy +
                    ", type='" + type + '\'' +
                    ", status_type='" + status_type + '\'' +
                    ", object_id='" + object_id + '\'' +
                    ", created_time='" + created_time + '\'' +
                    ", updated_time='" + updated_time + '\'' +
                    ", is_hidden=" + is_hidden +
                    ", subscribed=" + subscribed +
                    ", is_expired=" + is_expired +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Home{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
