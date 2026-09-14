package com.otaliastudios.cameraview.engine.mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import android.util.Pair;
import java.util.List;
import com.otaliastudios.cameraview.controls.Flash;
import java.util.Iterator;
import com.otaliastudios.cameraview.controls.Control;
import java.util.HashMap;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.Facing;
import java.util.Map;

public class Camera2Mapper
{
    private static final Map<Facing, Integer> FACING;
    private static final Map<Hdr, Integer> HDR;
    private static final Map<WhiteBalance, Integer> WB;
    private static Camera2Mapper sInstance;
    
    static {
        FACING = (Map)new HashMap();
        WB = (Map)new HashMap();
        HDR = (Map)new HashMap();
        final Map<Facing, Integer> facing = Camera2Mapper.FACING;
        final Facing back = Facing.BACK;
        final Integer value = 1;
        facing.put((Object)back, (Object)value);
        final Map<Facing, Integer> facing2 = Camera2Mapper.FACING;
        final Facing front = Facing.FRONT;
        final Integer value2 = 0;
        facing2.put((Object)front, (Object)value2);
        Camera2Mapper.WB.put((Object)WhiteBalance.AUTO, (Object)value);
        Camera2Mapper.WB.put((Object)WhiteBalance.CLOUDY, (Object)6);
        Camera2Mapper.WB.put((Object)WhiteBalance.DAYLIGHT, (Object)5);
        Camera2Mapper.WB.put((Object)WhiteBalance.FLUORESCENT, (Object)3);
        Camera2Mapper.WB.put((Object)WhiteBalance.INCANDESCENT, (Object)2);
        Camera2Mapper.HDR.put((Object)Hdr.OFF, (Object)value2);
        Camera2Mapper.HDR.put((Object)Hdr.ON, (Object)18);
    }
    
    private Camera2Mapper() {
    }
    
    public static Camera2Mapper get() {
        if (Camera2Mapper.sInstance == null) {
            Camera2Mapper.sInstance = new Camera2Mapper();
        }
        return Camera2Mapper.sInstance;
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
        return (int)Camera2Mapper.FACING.get((Object)facing);
    }
    
    public List<Pair<Integer, Integer>> mapFlash(final Flash flash) {
        final ArrayList list = new ArrayList();
        final int n = Camera2Mapper$1.$SwitchMap$com$otaliastudios$cameraview$controls$Flash[flash.ordinal()];
        if (n != 1) {
            if (n != 2) {
                if (n != 3) {
                    if (n == 4) {
                        ((List)list).add((Object)new Pair((Object)1, (Object)2));
                        ((List)list).add((Object)new Pair((Object)0, (Object)2));
                    }
                }
                else {
                    ((List)list).add((Object)new Pair((Object)1, (Object)0));
                    ((List)list).add((Object)new Pair((Object)0, (Object)0));
                }
            }
            else {
                ((List)list).add((Object)new Pair((Object)2, (Object)0));
                ((List)list).add((Object)new Pair((Object)4, (Object)0));
            }
        }
        else {
            ((List)list).add((Object)new Pair((Object)3, (Object)0));
        }
        return (List<Pair<Integer, Integer>>)list;
    }
    
    public int mapHdr(final Hdr hdr) {
        return (int)Camera2Mapper.HDR.get((Object)hdr);
    }
    
    public int mapWhiteBalance(final WhiteBalance whiteBalance) {
        return (int)Camera2Mapper.WB.get((Object)whiteBalance);
    }
    
    public Facing unmapFacing(final int n) {
        return this.reverseLookup(Camera2Mapper.FACING, n);
    }
    
    public Set<Flash> unmapFlash(final int n) {
        final HashSet set = new HashSet();
        if (n != 0 && n != 1) {
            if (n != 2) {
                if (n == 3) {
                    ((Set)set).add((Object)Flash.ON);
                    return (Set<Flash>)set;
                }
                if (n != 4) {
                    return (Set<Flash>)set;
                }
            }
            ((Set)set).add((Object)Flash.AUTO);
        }
        else {
            ((Set)set).add((Object)Flash.OFF);
            ((Set)set).add((Object)Flash.TORCH);
        }
        return (Set<Flash>)set;
    }
    
    public Hdr unmapHdr(final int n) {
        return this.reverseLookup(Camera2Mapper.HDR, n);
    }
    
    public WhiteBalance unmapWhiteBalance(final int n) {
        return this.reverseLookup(Camera2Mapper.WB, n);
    }
}
