package com.example.baselibrary;

import android.app.Activity;
import android.view.View;

import com.example.models.ViewBind;

import java.lang.reflect.Field;

/**
 * Created by kangjiahang on 17/12/18.
 */

public class ViewInjectUtil {
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    public static void inject(ViewFinder finder, Object o) {
        injectFild(finder, o);
        injectEvent(finder, o);
    }

    /**
     * 注入属性
     *
     * @param finder
     * @param o
     */
    private static void injectFild(ViewFinder finder, Object o) {
        Class<?> clazz = o.getClass();
        Field[] filelds = clazz.getDeclaredFields();
        for (Field fileld : filelds) {
            fileld.setAccessible(true);
            ViewBind viewBind = fileld.getAnnotation(ViewBind.class);
            if (viewBind == null) continue;
            View view = finder.findViewById(viewBind.value());
            if (view == null) continue;
            try {
                fileld.set(o, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    private static void injectEvent(ViewFinder finder, Object o) {

    }


}
