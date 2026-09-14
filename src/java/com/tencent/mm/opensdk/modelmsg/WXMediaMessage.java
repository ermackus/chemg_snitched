package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import com.tencent.mm.opensdk.utils.b;
import com.tencent.mm.opensdk.utils.Log;

public final class WXMediaMessage
{
    public static final String ACTION_WXAPPMESSAGE = "com.tencent.mm.sdk.openapi.Intent.ACTION_WXAPPMESSAGE";
    public static final int DESCRIPTION_LENGTH_LIMIT = 1024;
    public static final int MEDIA_TAG_NAME_LENGTH_LIMIT = 64;
    public static final int MESSAGE_ACTION_LENGTH_LIMIT = 2048;
    public static final int MESSAGE_EXT_LENGTH_LIMIT = 2048;
    public static final int MINI_PROGRAM__THUMB_LENGHT = 131072;
    public static final int NATIVE_GAME__THUMB_LIMIT = 262144;
    private static final String TAG = "MicroMsg.SDK.WXMediaMessage";
    public static final int THUMB_LENGTH_LIMIT = 65536;
    public static final int TITLE_LENGTH_LIMIT = 512;
    public String description;
    public IMediaObject mediaObject;
    public String mediaTagName;
    public String messageAction;
    public String messageExt;
    public String msgSignature;
    public int sdkVer;
    public byte[] thumbData;
    public String thumbDataHash;
    public String title;
    
    public WXMediaMessage() {
        this(null);
    }
    
    public WXMediaMessage(final IMediaObject mediaObject) {
        this.mediaObject = mediaObject;
    }
    
    boolean checkArgs() {
        while (true) {
            Label_0034: {
                if (this.getType() != 8) {
                    break Label_0034;
                }
                final byte[] thumbData = this.thumbData;
                if (thumbData != null && thumbData.length != 0) {
                    break Label_0034;
                }
                final String s = "checkArgs fail, thumbData should not be null when send emoji";
                Log.e("MicroMsg.SDK.WXMediaMessage", s);
                return false;
            }
            if (this.getType() == 76 && b.b(this.title)) {
                final String s = "checkArgs fail, Type = Music Video, but title == null";
                continue;
            }
            if (b.a(this.getType())) {
                final byte[] thumbData2 = this.thumbData;
                if (thumbData2 == null || thumbData2.length > 131072) {
                    final String s = "checkArgs fail, thumbData should not be null or exceed 128kb";
                    continue;
                }
            }
            final int type = this.getType();
            final boolean b = true;
            if (type == 101) {
                final byte[] thumbData3 = this.thumbData;
                if (thumbData3 == null || thumbData3.length > 262144) {
                    final String s = "checkArgs fail, thumbData should not be null or exceed 256KB";
                    continue;
                }
            }
            int n = b ? 1 : 0;
            if (!com.tencent.mm.opensdk.utils.b.a(this.getType())) {
                if (this.getType() == 101) {
                    n = (b ? 1 : 0);
                }
                else {
                    n = 0;
                }
            }
            if (n == 0) {
                final byte[] thumbData4 = this.thumbData;
                if (thumbData4 != null && thumbData4.length > 65536) {
                    final String s = "checkArgs fail, thumbData is invalid";
                    continue;
                }
            }
            final String title = this.title;
            if (title != null && title.length() > 512) {
                final String s = "checkArgs fail, title is invalid";
                continue;
            }
            final String description = this.description;
            if (description != null && description.length() > 1024) {
                final String s = "checkArgs fail, description is invalid";
                continue;
            }
            if (this.mediaObject == null) {
                final String s = "checkArgs fail, mediaObject is null";
                continue;
            }
            final String mediaTagName = this.mediaTagName;
            if (mediaTagName != null && mediaTagName.length() > 64) {
                final String s = "checkArgs fail, mediaTagName is too long";
                continue;
            }
            final String messageAction = this.messageAction;
            if (messageAction != null && messageAction.length() > 2048) {
                final String s = "checkArgs fail, messageAction is too long";
                continue;
            }
            final String messageExt = this.messageExt;
            if (messageExt != null && messageExt.length() > 2048) {
                final String s = "checkArgs fail, messageExt is too long";
                continue;
            }
            break;
        }
        return this.mediaObject.checkArgs();
    }
    
    public int getType() {
        final IMediaObject mediaObject = this.mediaObject;
        if (mediaObject == null) {
            return 0;
        }
        return mediaObject.type();
    }
    
    public void setThumbImage(final Bitmap bitmap) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap$CompressFormat.JPEG, 85, (OutputStream)byteArrayOutputStream);
            this.thumbData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("setThumbImage exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXMediaMessage", sb.toString());
        }
    }
    
    public static class Builder
    {
        public static final String KEY_IDENTIFIER = "_wxobject_identifier_";
        
        public static WXMediaMessage fromBundle(final Bundle bundle) {
            final WXMediaMessage wxMediaMessage = new WXMediaMessage();
            wxMediaMessage.sdkVer = bundle.getInt("_wxobject_sdkVer");
            wxMediaMessage.title = bundle.getString("_wxobject_title");
            wxMediaMessage.description = bundle.getString("_wxobject_description");
            wxMediaMessage.thumbData = bundle.getByteArray("_wxobject_thumbdata");
            wxMediaMessage.mediaTagName = bundle.getString("_wxobject_mediatagname");
            wxMediaMessage.messageAction = bundle.getString("_wxobject_message_action");
            wxMediaMessage.messageExt = bundle.getString("_wxobject_message_ext");
            wxMediaMessage.msgSignature = bundle.getString("_wxobject_msgsignature");
            wxMediaMessage.thumbDataHash = bundle.getString("_wxobject_thumbdatadash");
            final String pathOldToNew = pathOldToNew(bundle.getString("_wxobject_identifier_"));
            if (pathOldToNew != null) {
                if (pathOldToNew.length() > 0) {
                    try {
                        (wxMediaMessage.mediaObject = (IMediaObject)Class.forName(pathOldToNew).newInstance()).unserialize(bundle);
                        return wxMediaMessage;
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("get media object from bundle failed: unknown ident ");
                        sb.append(pathOldToNew);
                        sb.append(", ex = ");
                        sb.append(ex.getMessage());
                        Log.e("MicroMsg.SDK.WXMediaMessage", sb.toString());
                    }
                }
            }
            return wxMediaMessage;
        }
        
        private static String pathNewToOld(final String s) {
            if (s != null && s.length() != 0) {
                return s.replace((CharSequence)"com.tencent.mm.opensdk.modelmsg", (CharSequence)"com.tencent.mm.sdk.openapi");
            }
            Log.e("MicroMsg.SDK.WXMediaMessage", "pathNewToOld fail, newPath is null");
            return s;
        }
        
        private static String pathOldToNew(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("pathOldToNew, oldPath = ");
            sb.append(s);
            Log.i("MicroMsg.SDK.WXMediaMessage", sb.toString());
            if (s == null || s.length() == 0) {
                Log.e("MicroMsg.SDK.WXMediaMessage", "pathOldToNew fail, oldPath is null");
                return s;
            }
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex == -1) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("pathOldToNew fail, invalid pos, oldPath = ");
                sb2.append(s);
                Log.e("MicroMsg.SDK.WXMediaMessage", sb2.toString());
                return s;
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("com.tencent.mm.opensdk.modelmsg");
            sb3.append(s.substring(lastIndex));
            return sb3.toString();
        }
        
        public static Bundle toBundle(final WXMediaMessage wxMediaMessage) {
            final Bundle bundle = new Bundle();
            bundle.putInt("_wxobject_sdkVer", wxMediaMessage.sdkVer);
            bundle.putString("_wxobject_title", wxMediaMessage.title);
            bundle.putString("_wxobject_description", wxMediaMessage.description);
            bundle.putByteArray("_wxobject_thumbdata", wxMediaMessage.thumbData);
            final IMediaObject mediaObject = wxMediaMessage.mediaObject;
            if (mediaObject != null) {
                bundle.putString("_wxobject_identifier_", pathNewToOld(mediaObject.getClass().getName()));
                wxMediaMessage.mediaObject.serialize(bundle);
            }
            bundle.putString("_wxobject_mediatagname", wxMediaMessage.mediaTagName);
            bundle.putString("_wxobject_message_action", wxMediaMessage.messageAction);
            bundle.putString("_wxobject_message_ext", wxMediaMessage.messageExt);
            bundle.putString("_wxobject_msgsignature", wxMediaMessage.msgSignature);
            bundle.putString("_wxobject_thumbdatadash", wxMediaMessage.thumbDataHash);
            return bundle;
        }
    }
    
    public interface IMediaObject
    {
        public static final int TYPE_APPBRAND = 33;
        public static final int TYPE_APPDATA = 7;
        public static final int TYPE_BUSINESS_CARD = 45;
        public static final int TYPE_CARD_SHARE = 16;
        public static final int TYPE_DESIGNER_SHARED = 25;
        public static final int TYPE_DEVICE_ACCESS = 12;
        public static final int TYPE_EMOJI = 8;
        public static final int TYPE_EMOJILIST_SHARED = 27;
        public static final int TYPE_EMOTICON_GIFT = 11;
        public static final int TYPE_EMOTICON_SHARED = 15;
        public static final int TYPE_EMOTIONLIST_SHARED = 26;
        public static final int TYPE_FILE = 6;
        public static final int TYPE_GAME_LIVE = 70;
        public static final int TYPE_GAME_VIDEO_FILE = 39;
        public static final int TYPE_GIFTCARD = 34;
        public static final int TYPE_IMAGE = 2;
        public static final int TYPE_LOCATION = 30;
        public static final int TYPE_LOCATION_SHARE = 17;
        public static final int TYPE_MALL_PRODUCT = 13;
        public static final int TYPE_MUSIC = 3;
        public static final int TYPE_MUSIC_VIDEO = 76;
        public static final int TYPE_NATIVE_GAME_PAGE = 101;
        public static final int TYPE_NOTE = 24;
        public static final int TYPE_OLD_TV = 14;
        public static final int TYPE_OPENSDK_APPBRAND = 36;
        public static final int TYPE_OPENSDK_APPBRAND_WEISHIVIDEO = 46;
        public static final int TYPE_OPENSDK_LITEAPP = 68;
        public static final int TYPE_OPENSDK_WEWORK_OBJECT = 49;
        public static final int TYPE_PRODUCT = 10;
        public static final int TYPE_RECORD = 19;
        public static final int TYPE_TEXT = 1;
        public static final int TYPE_TV = 20;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_URL = 5;
        public static final int TYPE_VIDEO = 4;
        public static final int TYPE_VIDEO_FILE = 38;
        
        boolean checkArgs();
        
        void serialize(final Bundle p0);
        
        int type();
        
        void unserialize(final Bundle p0);
    }
}
