package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXFileObject implements WXMediaMessage$IMediaObject
{
    private static final int CONTENT_LENGTH_LIMIT = 1920991232;
    private static final String TAG = "MicroMsg.SDK.WXFileObject";
    private int contentLengthLimit;
    public byte[] fileData;
    public String filePath;
    
    public WXFileObject() {
        this.contentLengthLimit = 1920991232;
        this.fileData = null;
        this.filePath = null;
    }
    
    public WXFileObject(final String filePath) {
        this.contentLengthLimit = 1920991232;
        this.filePath = filePath;
    }
    
    public WXFileObject(final byte[] fileData) {
        this.contentLengthLimit = 1920991232;
        this.fileData = fileData;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final byte[] fileData = this.fileData;
        String s = null;
        Label_0054: {
            if (fileData == null || fileData.length == 0) {
                final String filePath = this.filePath;
                if (filePath == null || filePath.length() == 0) {
                    s = "checkArgs fail, both arguments is null";
                    break Label_0054;
                }
            }
            final byte[] fileData2 = this.fileData;
            if (fileData2 != null && fileData2.length > this.contentLengthLimit) {
                s = "checkArgs fail, fileData is too large";
            }
            else {
                final String filePath2 = this.filePath;
                if (filePath2 == null || this.getFileSize(filePath2) <= this.contentLengthLimit) {
                    return true;
                }
                s = "checkArgs fail, fileSize is too large";
            }
        }
        Log.e("MicroMsg.SDK.WXFileObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putByteArray("_wxfileobject_fileData", this.fileData);
        bundle.putString("_wxfileobject_filePath", this.filePath);
    }
    
    public void setContentLengthLimit(final int contentLengthLimit) {
        this.contentLengthLimit = contentLengthLimit;
    }
    
    public void setFileData(final byte[] fileData) {
        this.fileData = fileData;
    }
    
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
    
    public int type() {
        return 6;
    }
    
    public void unserialize(final Bundle bundle) {
        this.fileData = bundle.getByteArray("_wxfileobject_fileData");
        this.filePath = bundle.getString("_wxfileobject_filePath");
    }
}
