package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;

public class WXMusicVideoObject implements WXMediaMessage$IMediaObject
{
    private static final int HD_ALBUM_FILE_LENGTH = 10485760;
    private static final int LYRIC_LENGTH_LIMIT = 32768;
    private static final int STRING_LIMIT = 1024;
    private static final String TAG = "MicroMsg.SDK.WXMusicVideoObject";
    private static final int URL_LENGTH_LIMIT = 10240;
    public String albumName;
    public int duration;
    public String hdAlbumThumbFileHash;
    public String hdAlbumThumbFilePath;
    public String identification;
    public long issueDate;
    public String musicDataUrl;
    public String musicGenre;
    public String musicOperationUrl;
    public String musicUrl;
    public WXMusicVipInfo musicVipInfo;
    public String singerName;
    public String songLyric;
    
    private int getFileSize(final String s) {
        return b.a(s);
    }
    
    public boolean checkArgs() {
        String s;
        if (!b.b(this.musicUrl) && this.musicUrl.length() <= 10240) {
            if (!b.b(this.musicDataUrl) && this.musicDataUrl.length() <= 10240) {
                if (!b.b(this.singerName) && this.singerName.length() <= 1024) {
                    if (!b.b(this.songLyric) && this.songLyric.length() > 32768) {
                        s = "songLyric.length exceeds the limit";
                    }
                    else if (!b.b(this.hdAlbumThumbFilePath) && this.hdAlbumThumbFilePath.length() > 1024) {
                        s = "hdAlbumThumbFilePath.length exceeds the limit";
                    }
                    else if (!b.b(this.hdAlbumThumbFilePath) && this.getFileSize(this.hdAlbumThumbFilePath) > 10485760) {
                        s = "hdAlbumThumbFilePath file length exceeds the limit";
                    }
                    else if (!b.b(this.musicGenre) && this.musicGenre.length() > 1024) {
                        s = "musicGenre.length exceeds the limit";
                    }
                    else if (!b.b(this.identification) && this.identification.length() > 1024) {
                        s = "identification.length exceeds the limit";
                    }
                    else {
                        if (b.b(this.musicOperationUrl) || this.musicOperationUrl.length() <= 10240) {
                            return true;
                        }
                        s = "musicOperationUrl.length exceeds the limit";
                    }
                }
                else {
                    s = "singerName.length exceeds the limit";
                }
            }
            else {
                s = "musicDataUrl.length exceeds the limit";
            }
        }
        else {
            s = "musicUrl.length exceeds the limit";
        }
        Log.e("MicroMsg.SDK.WXMusicVideoObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxmusicvideoobject_musicUrl", this.musicUrl);
        bundle.putString("_wxmusicvideoobject_musicDataUrl", this.musicDataUrl);
        bundle.putString("_wxmusicvideoobject_singerName", this.singerName);
        bundle.putString("_wxmusicvideoobject_songLyric", this.songLyric);
        bundle.putString("_wxmusicvideoobject_hdAlbumThumbFilePath", this.hdAlbumThumbFilePath);
        bundle.putString("_wxmusicvideoobject_albumName", this.albumName);
        bundle.putString("_wxmusicvideoobject_musicGenre", this.musicGenre);
        bundle.putLong("_wxmusicvideoobject_issueDate", this.issueDate);
        bundle.putString("_wxmusicvideoobject_identification", this.identification);
        bundle.putInt("_wxmusicvideoobject_duration", this.duration);
        bundle.putString("_wxmusicvideoobject_musicOperationUrl", this.musicOperationUrl);
        final WXMusicVipInfo musicVipInfo = this.musicVipInfo;
        if (musicVipInfo != null) {
            bundle.putString("_wxmusicvideoobject_musicVipInfo", musicVipInfo.getClass().getName());
            this.musicVipInfo.serialize(bundle);
        }
        bundle.putString("_wxmusicvideoobject_hdAlbumThumbFileHash", this.hdAlbumThumbFileHash);
    }
    
    public int type() {
        return 76;
    }
    
    public void unserialize(final Bundle bundle) {
        this.musicUrl = bundle.getString("_wxmusicvideoobject_musicUrl");
        this.musicDataUrl = bundle.getString("_wxmusicvideoobject_musicDataUrl");
        this.singerName = bundle.getString("_wxmusicvideoobject_singerName");
        this.songLyric = bundle.getString("_wxmusicvideoobject_songLyric");
        this.hdAlbumThumbFilePath = bundle.getString("_wxmusicvideoobject_hdAlbumThumbFilePath");
        this.albumName = bundle.getString("_wxmusicvideoobject_albumName");
        this.musicGenre = bundle.getString("_wxmusicvideoobject_musicGenre");
        this.issueDate = bundle.getLong("_wxmusicvideoobject_issueDate", 0L);
        this.identification = bundle.getString("_wxmusicvideoobject_identification");
        this.duration = bundle.getInt("_wxmusicvideoobject_duration", 0);
        this.musicOperationUrl = bundle.getString("_wxmusicvideoobject_musicOperationUrl");
        final String string = bundle.getString("_wxmusicvideoobject_musicVipInfo");
        if (string != null) {
            try {
                (this.musicVipInfo = (WXMusicVipInfo)Class.forName(string).newInstance()).unserialize(bundle);
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("get WXSceneDataObject from bundle failed: unknown vipInfoObjectStr ");
                sb.append(string);
                sb.append(", ex = ");
                sb.append(ex.getMessage());
                Log.e("MicroMsg.SDK.WXMusicVideoObject", sb.toString());
            }
        }
        this.hdAlbumThumbFileHash = bundle.getString("_wxmusicvideoobject_hdAlbumThumbFileHash");
    }
}
