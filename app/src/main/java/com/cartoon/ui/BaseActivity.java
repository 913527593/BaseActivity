package com.cartoon.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.cartoon.constant.IConstant;

/**
 * Created by Administrator on 2017/7/26.
 */

public abstract class BaseActivity extends Activity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);


    }

    protected abstract int getLayout();


    public void toast(String text) {
        if (TextUtils.isEmpty(text)) {
            toast.setText(text);
            toast.show();
        }
    }

    public void log(String text) {
        if (IConstant.DEBUG) {
            Log.i("TAG", "从" + this.getClass().getSimpleName() + "打印日志：" + text);
        }
    }

    public void log(int code, String message) {
        String log = "错误信息：" + code + ":" + message;
        log(log);
    }

    public void toastAndLog(String text, String log) {
        toast(text);
        log(log);
    }

    public void toastAndLog(String text, int code, String message) {
        toast(text);
        log(code, message);
    }

    public void jumpTo(Class<?> clazz, boolean isAnimation, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        if (isAnimation && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
        if (isFinish) {
            finish();
        }
    }

    public void jumpTo(Intent intent, boolean isFinish, boolean isAnimation) {
        if (isAnimation && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }

        if (isFinish) {
            finish();
        }
    }
}
