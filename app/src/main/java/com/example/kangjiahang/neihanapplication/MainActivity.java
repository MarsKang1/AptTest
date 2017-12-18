package com.example.kangjiahang.neihanapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baselibrary.ViewInjectUtil;
import com.example.models.ViewBind;

public class MainActivity extends AppCompatActivity {
    @ViewBind(R.id.sample_text)
    TextView tv;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjectUtil.inject(this);
        tv.setText(stringFromJNI());
        // Example of a call to a native method
//        = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
