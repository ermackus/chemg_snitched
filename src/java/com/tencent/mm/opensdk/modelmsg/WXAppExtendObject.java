package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXAppExtendObject implements WXMediaMessage$IMediaObject
{
    private static final int CONTENT_LENGTH_LIMIT = 10485760;
    private static final int EXTINFO_LENGTH_LIMIT = 2048;
    private static final int PATH_LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXAppExtendObject";
    public String extInfo;
    public byte[] fileData;
    public String filePath;
    
    public WXAppExtendObject() {
    }
    
    public WXAppExtendObject(final String extInfo, final String filePath) {
        this.extInfo = extInfo;
        this.filePath = filePath;
    }
    
    public WXAppExtendObject(final String extInfo, final byte[] fileData) {
        this.extInfo = extInfo;
        this.fileData = fileData;
    }
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        final String extInfo = this.extInfo;
        String s = null;
        Label_0071: {
            if (extInfo == null || extInfo.length() == 0) {
                final String filePath = this.filePath;
                if (filePath == null || filePath.length() == 0) {
                    final byte[] fileData = this.fileData;
                    if (fileData == null || fileData.length == 0) {
                        s = "checkArgs fail, all arguments is null";
                        break Label_0071;
                    }
                }
            }
            final String extInfo2 = this.extInfo;
            if (extInfo2 != null && extInfo2.length() > 2048) {
                s = "checkArgs fail, extInfo is invalid";
            }
            else {
                final String filePath2 = this.filePath;
                if (filePath2 != null && filePath2.length() > 10240) {
                    s = "checkArgs fail, filePath is invalid";
                }
                else {
                    final String filePath3 = this.filePath;
                    if (filePath3 != null && this.getFileSize(filePath3) > 10485760) {
                        s = "checkArgs fail, fileSize is too large";
                    }
                    else {
                        final byte[] fileData2 = this.fileData;
                        if (fileData2 == null || fileData2.length <= 10485760) {
                            return true;
                        }
                        s = "checkArgs fail, fileData is too large";
                    }
                }
            }
        }
        Log.e("MicroMsg.SDK.WXAppExtendObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxappextendobject_extInfo", this.extInfo);
        bundle.putByteArray("_wxappextendobject_fileData", this.fileData);
        bundle.putString("_wxappextendobject_filePath", this.filePath);
    }
    
    public int type() {
        return 7;
    }
    
    public void unserialize(final Bundle bundle) {
        this.extInfo = bundle.getString("_wxappextendobject_extInfo");
        this.fileData = bundle.getByteArray("_wxappextendobject_fileData");
        this.filePath = bundle.getString("_wxappextendobject_filePath");
    }
}
