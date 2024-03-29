package com.bigoat.element.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigoat.element.button.ButtonView;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity);

        // 设置状态栏白色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public void clickButton(View view) {

        if (view instanceof ButtonView) {
            Toast.makeText(this, "点击按钮 " + ((ButtonView)view).getText(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "点击了按钮", Toast.LENGTH_SHORT).show();
        }
    }
}