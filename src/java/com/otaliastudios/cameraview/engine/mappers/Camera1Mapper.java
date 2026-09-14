package com.otaliastudios.cameraview.engine.mappers;

import java.util.Iterator;
import com.otaliastudios.cameraview.controls.Control;
import android.os.Build$VERSION;
import java.util.HashMap;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Facing;
import java.util.Map;

public class Camera1Mapper
{
    private static final Map<Facing, Integer> FACING;
    private static final Map<Flash, String> FLASH;
    private static final Map<Hdr, String> HDR;
    private static final Map<WhiteBalance, String> WB;
    private static Camera1Mapper sInstance;
    
    static {
        FLASH = (Map)new HashMap();
        WB = (Map)new HashMap();
        FACING = (Map)new HashMap();
        HDR = (Map)new HashMap();
        Camera1Mapper.FLASH.put((Object)Flash.OFF, (Object)"off");
        Camera1Mapper.FLASH.put((Object)Flash.ON, (Object)"on");
        Camera1Mapper.FLASH.put((Object)Flash.AUTO, (Object)"auto");
        Camera1Mapper.FLASH.put((Object)Flash.TORCH, (Object)"torch");
        Camera1Mapper.FACING.put((Object)Facing.BACK, (Object)0);
        Camera1Mapper.FACING.put((Object)Facing.FRONT, (Object)1);
        Camera1Mapper.WB.put((Object)WhiteBalance.AUTO, (Object)"auto");
        Camera1Mapper.WB.put((Object)WhiteBalance.INCANDESCENT, (Object)"incandescent");
        Camera1Mapper.WB.put((Object)WhiteBalance.FLUORESCENT, (Object)"fluorescent");
        Camera1Mapper.WB.put((Object)WhiteBalance.DAYLIGHT, (Object)"daylight");
        Camera1Mapper.WB.put((Object)WhiteBalance.CLOUDY, (Object)"cloudy-daylight");
        Camera1Mapper.HDR.put((Object)Hdr.OFF, (Object)"auto");
        if (Build$VERSION.SDK_INT >= 17) {
            Camera1Mapper.HDR.put((Object)Hdr.ON, (Object)"hdr");
        }
        else {
            Camera1Mapper.HDR.put((Object)Hdr.ON, (Object)"hdr");
        }
    }
    
    private Camera1Mapper() {
    }
    
    public static Camera1Mapper get() {
        if (Camera1Mapper.sInstance == null) {
            Camera1Mapper.sInstance = new Camera1Mapper();
        }
        return Camera1Mapper.sInstance;
    }
    
    private <C extends Control, T> C reverseLookup(final Map<C, T> map, final T t) {
        for (final Control control : map.keySet()) {
            if (t.equals(map.get((Object)control))) {
                return (C)control;
            }
        }
        return null;
    }
    
    public int mapFacing(final Facing facing) {
        return (int)Camera1Mapper.FACING.get((Object)facing);
    }
    
    public String mapFlash(final Flash flash) {
        return (String)Camera1Mapper.FLASH.get((Object)flash);
    }
    
    public String mapHdr(final Hdr hdr) {
        return (String)Camera1Mapper.HDR.get((Object)hdr);
    }
    
    public String mapWhiteBalance(final WhiteBalance whiteBalance) {
        return (String)Camera1Mapper.WB.get((Object)whiteBalance);
    }
    
    public Facing unmapFacing(final int n) {
        return this.reverseLookup(Camera1Mapper.FACING, n);
    }
    
    public Flash unmapFlash(final String s) {
        return this.reverseLookup(Camera1Mapper.FLASH, s);
    }
    
    public Hdr unmapHdr(final String s) {
        return this.reverseLookup(Camera1Mapper.HDR, s);
    }
    
    public WhiteBalance unmapWhiteBalance(final String s) {
        return this.reverseLookup(Camera1Mapper.WB, s);
    }
}
