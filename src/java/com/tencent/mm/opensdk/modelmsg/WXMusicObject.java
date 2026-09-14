package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXMusicObject implements WXMediaMessage$IMediaObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final int LYRIC_LENGTH_LIMIT = 32768;
    private static final String TAG = "MicroMsg.SDK.WXMusicObject";
    public String musicDataUrl;
    public String musicLowBandDataUrl;
    public String musicLowBandUrl;
    public String musicUrl;
    public String songAlbumUrl;
    public String songLyric;
    
    public boolean checkArgs() {
        final String musicUrl = this.musicUrl;
        String s = null;
        Label_0057: {
            if (musicUrl == null || musicUrl.length() == 0) {
                final String musicLowBandUrl = this.musicLowBandUrl;
                if (musicLowBandUrl == null || musicLowBandUrl.length() == 0) {
                    s = "both arguments are null";
                    break Label_0057;
                }
            }
            final String musicUrl2 = this.musicUrl;
            if (musicUrl2 != null && musicUrl2.length() > 10240) {
                s = "checkArgs fail, musicUrl is too long";
            }
            else {
                final String musicLowBandUrl2 = this.musicLowBandUrl;
                if (musicLowBandUrl2 != null && musicLowBandUrl2.length() > 10240) {
                    s = "checkArgs fail, musicLowBandUrl is too long";
                }
                else {
                    final String songAlbumUrl = this.songAlbumUrl;
                    if (songAlbumUrl != null && songAlbumUrl.length() > 10240) {
                        s = "checkArgs fail, songAlbumUrl is too long";
                    }
                    else {
                        final String songLyric = this.songLyric;
                        if (songLyric == null || songLyric.length() <= 32768) {
                            return true;
                        }
                        s = "checkArgs fail, songLyric is too long";
                    }
                }
            }
        }
        Log.e("MicroMsg.SDK.WXMusicObject", s);
        return false;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
        bundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
        bundle.putString("_wxmusicobject_musicDataUrl", this.musicDataUrl);
        bundle.putString("_wxmusicobject_musicLowBandDataUrl", this.musicLowBandDataUrl);
        bundle.putString("_wxmusicobject_musicAlbumUrl", this.songAlbumUrl);
        bundle.putString("_wxmusicobject_musicLyric", this.songLyric);
    }
    
    public int type() {
        return 3;
    }
    
    public void unserialize(final Bundle bundle) {
        this.musicUrl = bundle.getString("_wxmusicobject_musicUrl");
        this.musicLowBandUrl = bundle.getString("_wxmusicobject_musicLowBandUrl");
        this.musicDataUrl = bundle.getString("_wxmusicobject_musicDataUrl");
        this.musicLowBandDataUrl = bundle.getString("_wxmusicobject_musicLowBandDataUrl");
        this.songAlbumUrl = bundle.getString("_wxmusicobject_musicAlbumUrl");
        this.songLyric = bundle.getString("_wxmusicobject_musicLyric");
    }
}
