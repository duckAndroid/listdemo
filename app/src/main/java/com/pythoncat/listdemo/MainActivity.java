package com.pythoncat.listdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ThreadLocal<TextView> tv = new ThreadLocal<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvJump = (TextView) findViewById(R.id.jump);
        tv.set(tvJump);
        assert tvJump != null;
        tvJump.setText("我来啦。。。。");
        tvJump.setOnClickListener(v -> {
            startActivity(new Intent(this, DemoListActivity.class));
        });

        findViewById(R.id.button_linearlayout)
                .setOnClickListener(v -> {
                    startActivity(new Intent(this, LayoutActivity.class));
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        tv.get().setText("我死了@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
