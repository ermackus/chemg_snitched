package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;

public class WXLocationObject implements WXMediaMessage$IMediaObject
{
    private static final String TAG = "MicroMsg.SDK.WXLocationObject";
    public double lat;
    public double lng;
    
    public WXLocationObject() {
        this(0.0, 0.0);
    }
    
    public WXLocationObject(final double lat, final double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    
    public boolean checkArgs() {
        return true;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putDouble("_wxlocationobject_lat", this.lat);
        bundle.putDouble("_wxlocationobject_lng", this.lng);
    }
    
    public int type() {
        return 30;
    }
    
    public void unserialize(final Bundle bundle) {
        this.lat = bundle.getDouble("_wxlocationobject_lat");
        this.lng = bundle.getDouble("_wxlocationobject_lng");
    }
}
