package com.pythoncat.listdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LayoutActivity extends AppCompatActivity {

    private CombLayout cLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        cLayout = (CombLayout) findViewById(R.id.my_layout);
    }
}
