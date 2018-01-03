package test.com.and.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import test.com.and.app.shopping;

/**
 * Created by ${lh} on 2018/1/3.
 */

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = shopping.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = shopping.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
