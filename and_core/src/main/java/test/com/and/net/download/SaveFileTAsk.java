package test.com.and.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import test.com.and.app.shopping;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.utils.FileUtil;

/**
 * Created by Administrator on 2018/1/7.
 */

public class SaveFileTAsk extends AsyncTask<Object,Void,File> {

    private final IRequset Requset;
    private final ISuccess success;

    public SaveFileTAsk(IRequset requset, ISuccess success) {
        Requset = requset;
        this.success = success;
    }

    @Override
    protected File doInBackground(Object... parms) {
      String downloadrir = (String) parms[0];
      String extension = (String) parms[1];
      final ResponseBody body = (ResponseBody) parms[2];
      final String name = (String) parms[3];
      final InputStream is = body.byteStream();
        if (downloadrir == null || downloadrir.equals("")) {
            downloadrir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadrir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is,downloadrir,name);
        }
    }

    // 执行完异步回到主线程的操作
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (success != null) {
            success.onSuccess(file.getPath());
        }
        if (Requset != null) {
            Requset.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     * 默认安装
     * @param file
     */
    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            // 新启一个栈 有可能在后台运行
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            shopping.getApplication().startActivity(install);
        }
    }

}
