package com.bigoat.element.sample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bigoat.element.sample.databinding.SidebarActivityBinding;

public class SidebarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SidebarActivityBinding binding = SidebarActivityBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    public void clickButton(View view) {
    }
}