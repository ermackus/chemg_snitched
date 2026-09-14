package com.goodsrc.ui.library;

import android.os.VibrationEffect;
import android.os.Build$VERSION;
import com.goodsrc.ui.library.widget.AppManager;
import android.os.Bundle;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.os.IBinder;
import android.view.MotionEvent;
import com.goodsrc.library.utils.LocalLanguageManageUtil;
import android.content.Context;
import android.view.View;
import com.goodsrc.library.utils.FastBlur;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.app.Activity;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity
{
    protected QyPermissions qyPermissions;
    private boolean vibrating;
    private Vibrator vibrator;
    
    public BaseActivity() {
        this.vibrating = false;
        this.qyPermissions = new QyPermissions(this);
    }
    
    public static Bitmap takeScreenShot(final Activity activity) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        final Bitmap drawingCache = decorView.getDrawingCache();
        final int width = decorView.getWidth();
        final int height = decorView.getHeight();
        final Matrix matrix = new Matrix();
        matrix.postScale(0.2f, 0.2f);
        final Bitmap doBlur = FastBlur.doBlur(Bitmap.createBitmap(drawingCache, 0, 0, width, height, matrix, true), 6, true);
        decorView.destroyDrawingCache();
        return doBlur;
    }
    
    protected void attachBaseContext(final Context local) {
        super.attachBaseContext(LocalLanguageManageUtil.setLocal(local));
    }
    
    public void checkPermission(final QyPermissions.EasyParam easyParam, final QyPermissions.RequestPermissionsCallBack requestPermissionsCallBack) {
        final QyPermissions qyPermissions = this.qyPermissions;
        if (qyPermissions != null) {
            qyPermissions.checkPermission(easyParam, requestPermissionsCallBack);
        }
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        return this.keyBoardEvent(motionEvent) || super.dispatchTouchEvent(motionEvent);
    }
    
    protected boolean hideKeyboard(final IBinder binder) {
        return binder != null && ((InputMethodManager)this.getSystemService("input_method")).hideSoftInputFromWindow(binder, 2);
    }
    
    protected boolean isShouldHideKeyboard(final View view, final MotionEvent motionEvent) {
        boolean b2;
        final boolean b = b2 = false;
        if (view != null) {
            b2 = b;
            if (view instanceof EditText) {
                final int[] array2;
                final int[] array = array2 = new int[2];
                array2[1] = (array2[0] = 0);
                view.getLocationInWindow(array);
                final int n = array[0];
                final int n2 = array[1];
                final int height = view.getHeight();
                final int width = view.getWidth();
                if (motionEvent.getX() > n && motionEvent.getX() < width + n && motionEvent.getY() > n2) {
                    b2 = b;
                    if (motionEvent.getY() < height + n2) {
                        return b2;
                    }
                }
                b2 = true;
            }
        }
        return b2;
    }
    
    protected boolean keyBoardEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            final View currentFocus = this.getCurrentFocus();
            if (this.isShouldHideKeyboard(currentFocus, motionEvent)) {
                return this.hideKeyboard(currentFocus.getWindowToken());
            }
        }
        return false;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.vibrator = (Vibrator)this.getSystemService("vibrator");
        AppManager.getInstance().addActivity((Activity)this);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity((Activity)this);
    }
    
    protected void onPause() {
        super.onPause();
        ActivityStack.getInstance().remove((Activity)this);
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        super.onRequestPermissionsResult(n, array, array2);
        final QyPermissions qyPermissions = this.qyPermissions;
        if (qyPermissions != null) {
            qyPermissions.onRequestPermissionsResult(n, array, array2);
        }
    }
    
    protected void onResume() {
        super.onResume();
        ActivityStack.getInstance().add((Activity)this);
        final QyPermissions qyPermissions = this.qyPermissions;
        if (qyPermissions != null) {
            qyPermissions.onResume();
        }
    }
    
    public void setVibrator(final boolean vibrating) {
        if (this.vibrator.hasVibrator()) {
            if (!vibrating || !this.vibrating) {
                if (this.vibrating = vibrating) {
                    if (Build$VERSION.SDK_INT >= 26) {
                        this.vibrator.vibrate(VibrationEffect.createOneShot(100L, -1));
                    }
                    else {
                        this.vibrator.vibrate(100L);
                    }
                }
                else {
                    this.vibrator.cancel();
                }
            }
        }
    }
}
