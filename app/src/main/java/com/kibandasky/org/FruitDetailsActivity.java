package com.kibandasky.org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class FruitDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView = findViewById(R.id.imageView);
        textViewTitle = findViewById(R.id.title_dt);
        textViewDescription = findViewById(R.id.description_dt);


        Intent intent = getIntent();
        String mtitile = intent.getStringExtra("title");
        String mDescription = intent.getStringExtra("description");
        final String mImage = intent.getStringExtra("image");

        textViewTitle.setText(mtitile);
        textViewDescription.setText(mDescription);
        Picasso.get().load(mImage).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

                Picasso.get().load(mImage).into(imageView);
            }
        });

    }
}