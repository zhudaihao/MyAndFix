package cn.wqgallery.myandfix;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/16.
 */

public class MyApp extends Application {
    private static final String TAG = " andrew";
    private static final String APATCH_PATH = "/out.apatch";
    private static final String DIR = "apatch";//补丁文件夹
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setAndFix();

    }

    private void setAndFix() {
        // initialize
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        try {
            // .apatch file path
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");

            //复制且加载补丁成功后，删除下载的补丁
            File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
            if (f.exists()) {
                boolean result = new File(patchFileString).delete();
                if (!result){
//                    Log.e(TAG, patchFileString + " delete fail");
                }

            }
        } catch (IOException e) {
//            Log.e(TAG, "", e);
        }
    }


}
