package com.example.baselibrary;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.models.OnClick;
import com.example.models.ViewBind;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
     * @param finder view的查找器
     * @param o      传入的对象
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

    /**
     * 注入方法
     *
     * @param finder view的查找器
     * @param o      传入的对象
     */
    private static void injectEvent(ViewFinder finder, Object o) {
        Class<?> clazz = o.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick == null) return;
            int[] values = onClick.value();
            for (int value : values) {
                View view = finder.findViewById(value);
                if (view == null) continue;
                view.setOnClickListener(new InjectOnClickListener(method, o));
            }
        }
    }

    private static class InjectOnClickListener implements View.OnClickListener {
        private Method method;
        private Object o;

        InjectOnClickListener(Method method, Object o) {
            this.method = method;
            this.o = o;
        }

        @Override
        public void onClick(View v) {
            try {
                method.invoke(o, v);
            } catch (Exception e) {
                Log.e("RuntimeException", " parameter view is null");
                e.printStackTrace();
            }
        }
    }

}
