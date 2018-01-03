package test.com.and.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by ${lh} on 2018/1/3.
 * 处理一下不用每次都反射 已缓存的形式处理load
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

//    放在Indicator包下
    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        // stringBuilder 线程不安全
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
          final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
          drawableClassName.append(defaultPackageName)
                  .append(".indicators")
                  .append(".");
        }
        drawableClassName.append(name);
        try {
           final Class<?> drawableClass = Class.forName(drawableClassName.toString());
           return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


