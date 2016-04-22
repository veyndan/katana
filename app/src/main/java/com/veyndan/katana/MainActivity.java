package com.veyndan.katana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.profile) ImageView profile;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.image) ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String profileUrl = "https://scontent-lhr3-1.xx.fbcdn.net/v/t1.0-9/12923224_2012562415634" +
                "637_6490425404654077477_n.jpg?oh=8b2c57c4d2ec59ba0605d28fe68a1e5c&oe=57B59F67";
        Glide.with(this).load(profileUrl).into(profile);
        name.setText("Veyndan Stuart");
        date.setText("1 Apr at 15:17");
        String imageUrl = "https://scontent-lhr3-1.xx.fbcdn.net/hphotos-xaf1/t31.0-8/12916955_201" +
                "6582558565956_3030515090458717633_o.jpg";
        Glide.with(this).load(imageUrl).into(image);
    }
}
