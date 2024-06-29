package com.example.moviemania;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ImageView poster_image = findViewById(R.id.poster_image);
        TextView mTitle = findViewById(R.id.mTitle);
        TextView mRating = findViewById(R.id.mRating);
        TextView moverview = findViewById(R.id.movervie_tv);

        Bundle bundle= getIntent().getExtras();

        String mtitle = bundle.getString("title");
        String mOverview = bundle.getString("overview");
        String mposter = bundle.getString("poster");
        Double mrating = bundle.getDouble("rating");

        Glide.with(this).load(mposter).into(poster_image);
        mTitle.setText(mtitle);
        mRating.setText(mrating.toString());
        moverview.setText(mOverview);
    }
}