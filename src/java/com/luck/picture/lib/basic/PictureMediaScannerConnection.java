package com.luck.picture.lib.basic;

import android.net.Uri;
import android.text.TextUtils;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection$MediaScannerConnectionClient;

public class PictureMediaScannerConnection implements MediaScannerConnection$MediaScannerConnectionClient
{
    private ScanListener mListener;
    private final MediaScannerConnection mMs;
    private final String mPath;
    
    public PictureMediaScannerConnection(final Context context, final String mPath) {
        this.mPath = mPath;
        (this.mMs = new MediaScannerConnection(context.getApplicationContext(), (MediaScannerConnection$MediaScannerConnectionClient)this)).connect();
    }
    
    public PictureMediaScannerConnection(final Context context, final String mPath, final ScanListener mListener) {
        this.mListener = mListener;
        this.mPath = mPath;
        (this.mMs = new MediaScannerConnection(context.getApplicationContext(), (MediaScannerConnection$MediaScannerConnectionClient)this)).connect();
    }
    
    public void onMediaScannerConnected() {
        if (!TextUtils.isEmpty((CharSequence)this.mPath)) {
            this.mMs.scanFile(this.mPath, (String)null);
        }
    }
    
    public void onScanCompleted(final String s, final Uri uri) {
        this.mMs.disconnect();
        final ScanListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onScanFinish();
        }
    }
    
    public interface ScanListener
    {
        void onScanFinish();
    }
}
