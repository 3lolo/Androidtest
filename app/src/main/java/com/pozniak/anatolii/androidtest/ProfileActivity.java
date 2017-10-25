package com.pozniak.anatolii.androidtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pozniak.anatolii.androidtest.models.AnimalModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Владелец on 24.10.2017.
 */

public class ProfileActivity extends AppCompatActivity {

    public static final String BUNDLE_ANIMAL = "animal";
    public static final int TAB_CAT = 0;
    public static final int TAB_DOG = 1;
    private ImageView ivPic;
    private TextView tvTxt;
    private TextView tvNumber;


    public static Intent getLaunchIntent(Context context, AnimalModel animal) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(BUNDLE_ANIMAL, animal);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (savedInstanceState == null) {
            ivPic = (ImageView) findViewById(R.id.iv_pic);
            tvTxt = (TextView) findViewById(R.id.tv_text);
            tvNumber = (TextView) findViewById(R.id.tv_number);

            AnimalModel animal = getIntent().getParcelableExtra(BUNDLE_ANIMAL);

            tvTxt.setText(animal.getTitle());
            Picasso.with(this).load(animal.getUrl()).into(ivPic);
        }
    }


}
