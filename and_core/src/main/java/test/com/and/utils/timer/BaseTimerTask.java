package test.com.and.utils.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/7.
 */

public class BaseTimerTask extends TimerTask {

    private iTimerListener mtimerListener = null;

    public BaseTimerTask(iTimerListener mtimerListener) {
        this.mtimerListener = mtimerListener;
    }

    @Override
    public void run() {
        if (mtimerListener != null) {
            mtimerListener.onTimer();
        }
    }
}
