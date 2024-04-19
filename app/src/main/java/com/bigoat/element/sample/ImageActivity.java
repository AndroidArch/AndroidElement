package com.bigoat.element.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.bigoat.element.button.ButtonView;
import com.bigoat.element.sample.databinding.ImageActivityBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageActivityBinding bind = ImageActivityBinding.inflate(getLayoutInflater());
        bind.setLifecycleOwner(this);
        setContentView(bind.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ImageSrc imageSrc = new ImageSrc("https://www.baidu.com/img/flexible/logo/pc/result.png");

        bind.setImage(imageSrc);

    }

    public void clickButton(View view) {
    }
}