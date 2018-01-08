package test.com.and.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2018/1/8.
 */

public class LauncherHolder implements Holder<Integer> {
    // 每一个子元素都是一张图片，所以
    private AppCompatImageView mimageView = null;

    @Override
    public View createView(Context context) {
        mimageView = new AppCompatImageView(context);
        return mimageView;
    }

    // 每次滑动时，索要跟新的东西
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mimageView.setBackgroundResource(data);
    }
}
