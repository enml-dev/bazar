package com.enml.bazar.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.enml.bazar.R;

public class ViewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Light);
        setContentView(R.layout.activity_view_item);
    }
}