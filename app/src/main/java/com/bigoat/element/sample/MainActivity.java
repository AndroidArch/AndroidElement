package com.bigoat.element.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigoat.element.button.ButtonView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SidebarActivity.class));

    }

    public void jumpToButtonActivity(View view) {
        startActivity(new Intent(this, ButtonActivity.class));
    }

    public void jumpToImageActivity(View view) {
        startActivity(new Intent(this, ImageActivity.class));
    }

}