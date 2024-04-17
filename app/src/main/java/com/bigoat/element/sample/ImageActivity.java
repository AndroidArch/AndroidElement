package com.bigoat.element.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigoat.element.button.ButtonView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

//        ImageView imageView = findViewById(R.id.image);
//        Glide.with(this)
//                .load("https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg")
//                .apply(RequestOptions.circleCropTransform())
//                .into(imageView);

    }

    public void clickButton(View view) {
    }
}