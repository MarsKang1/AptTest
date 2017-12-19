package com.example.kangjiahang.neihanapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselibrary.ViewInjectUtil;
import com.example.models.OnClick;
import com.example.models.ViewBind;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }

    @ViewBind(R.id.sample_text)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjectUtil.inject(this);
        tv.setText(stringFromJNI());
    }

    public native String stringFromJNI();

    @OnClick({R.id.sample_text})
    public void onClick(View view) {
        Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
    }
}
