package com.example.baselibrary;

import android.app.Activity;
import android.view.View;

/**
 * Created by kangjiahang on 17/12/18.
 */

public class ViewFinder {
    private Activity activity;
    private View view;

    public ViewFinder(View view) {
        this.view = view;
    }

    public ViewFinder(Activity activity) {
        this.activity = activity;
    }

    public View findViewById(int id) {
        return activity == null ? view.findViewById(id) : activity.findViewById(id);
    }


}
