package com.otaliastudios.cameraview;

import com.otaliastudios.cameraview.gesture.GestureAction;
import java.util.Collections;
import com.otaliastudios.cameraview.controls.Preview;
import com.otaliastudios.cameraview.controls.Engine;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.controls.Grid;
import java.util.Arrays;
import com.otaliastudios.cameraview.controls.Audio;
import java.util.Collection;
import com.otaliastudios.cameraview.controls.Control;
import java.util.HashSet;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Facing;
import java.util.Set;

public abstract class CameraOptions
{
    protected boolean autoFocusSupported;
    protected float exposureCorrectionMaxValue;
    protected float exposureCorrectionMinValue;
    protected boolean exposureCorrectionSupported;
    protected float previewFrameRateMaxValue;
    protected float previewFrameRateMinValue;
    protected Set<Facing> supportedFacing;
    protected Set<Flash> supportedFlash;
    protected Set<Integer> supportedFrameProcessingFormats;
    protected Set<Hdr> supportedHdr;
    protected Set<AspectRatio> supportedPictureAspectRatio;
    protected Set<PictureFormat> supportedPictureFormats;
    protected Set<Size> supportedPictureSizes;
    protected Set<AspectRatio> supportedVideoAspectRatio;
    protected Set<Size> supportedVideoSizes;
    protected Set<WhiteBalance> supportedWhiteBalance;
    protected boolean zoomSupported;
    
    protected CameraOptions() {
        this.supportedWhiteBalance = (Set<WhiteBalance>)new HashSet(5);
        this.supportedFacing = (Set<Facing>)new HashSet(2);
        this.supportedFlash = (Set<Flash>)new HashSet(4);
        this.supportedHdr = (Set<Hdr>)new HashSet(2);
        this.supportedPictureSizes = (Set<Size>)new HashSet(15);
        this.supportedVideoSizes = (Set<Size>)new HashSet(5);
        this.supportedPictureAspectRatio = (Set<AspectRatio>)new HashSet(4);
        this.supportedVideoAspectRatio = (Set<AspectRatio>)new HashSet(3);
        this.supportedPictureFormats = (Set<PictureFormat>)new HashSet(2);
        this.supportedFrameProcessingFormats = (Set<Integer>)new HashSet(2);
    }
    
    public final float getExposureCorrectionMaxValue() {
        return this.exposureCorrectionMaxValue;
    }
    
    public final float getExposureCorrectionMinValue() {
        return this.exposureCorrectionMinValue;
    }
    
    public final float getPreviewFrameRateMaxValue() {
        return this.previewFrameRateMaxValue;
    }
    
    public final float getPreviewFrameRateMinValue() {
        return this.previewFrameRateMinValue;
    }
    
    public final <T extends Control> Collection<T> getSupportedControls(final Class<T> clazz) {
        if (clazz.equals(Audio.class)) {
            return (Collection<T>)Arrays.asList((Object[])Audio.values());
        }
        if (clazz.equals(Facing.class)) {
            return (Collection<T>)this.getSupportedFacing();
        }
        if (clazz.equals(Flash.class)) {
            return (Collection<T>)this.getSupportedFlash();
        }
        if (clazz.equals(Grid.class)) {
            return (Collection<T>)Arrays.asList((Object[])Grid.values());
        }
        if (clazz.equals(Hdr.class)) {
            return (Collection<T>)this.getSupportedHdr();
        }
        if (clazz.equals(Mode.class)) {
            return (Collection<T>)Arrays.asList((Object[])Mode.values());
        }
        if (clazz.equals(VideoCodec.class)) {
            return (Collection<T>)Arrays.asList((Object[])VideoCodec.values());
        }
        if (clazz.equals(AudioCodec.class)) {
            return (Collection<T>)Arrays.asList((Object[])AudioCodec.values());
        }
        if (clazz.equals(WhiteBalance.class)) {
            return (Collection<T>)this.getSupportedWhiteBalance();
        }
        if (clazz.equals(Engine.class)) {
            return (Collection<T>)Arrays.asList((Object[])Engine.values());
        }
        if (clazz.equals(Preview.class)) {
            return (Collection<T>)Arrays.asList((Object[])Preview.values());
        }
        if (clazz.equals(PictureFormat.class)) {
            return (Collection<T>)this.getSupportedPictureFormats();
        }
        return (Collection<T>)Collections.emptyList();
    }
    
    public final Collection<Facing> getSupportedFacing() {
        return (Collection<Facing>)Collections.unmodifiableSet((Set)this.supportedFacing);
    }
    
    public final Collection<Flash> getSupportedFlash() {
        return (Collection<Flash>)Collections.unmodifiableSet((Set)this.supportedFlash);
    }
    
    public final Collection<Integer> getSupportedFrameProcessingFormats() {
        return (Collection<Integer>)Collections.unmodifiableSet((Set)this.supportedFrameProcessingFormats);
    }
    
    public final Collection<Hdr> getSupportedHdr() {
        return (Collection<Hdr>)Collections.unmodifiableSet((Set)this.supportedHdr);
    }
    
    public final Collection<AspectRatio> getSupportedPictureAspectRatios() {
        return (Collection<AspectRatio>)Collections.unmodifiableSet((Set)this.supportedPictureAspectRatio);
    }
    
    public final Collection<PictureFormat> getSupportedPictureFormats() {
        return (Collection<PictureFormat>)Collections.unmodifiableSet((Set)this.supportedPictureFormats);
    }
    
    public final Collection<Size> getSupportedPictureSizes() {
        return (Collection<Size>)Collections.unmodifiableSet((Set)this.supportedPictureSizes);
    }
    
    public final Collection<AspectRatio> getSupportedVideoAspectRatios() {
        return (Collection<AspectRatio>)Collections.unmodifiableSet((Set)this.supportedVideoAspectRatio);
    }
    
    public final Collection<Size> getSupportedVideoSizes() {
        return (Collection<Size>)Collections.unmodifiableSet((Set)this.supportedVideoSizes);
    }
    
    public final Collection<WhiteBalance> getSupportedWhiteBalance() {
        return (Collection<WhiteBalance>)Collections.unmodifiableSet((Set)this.supportedWhiteBalance);
    }
    
    public final boolean isAutoFocusSupported() {
        return this.autoFocusSupported;
    }
    
    public final boolean isExposureCorrectionSupported() {
        return this.exposureCorrectionSupported;
    }
    
    public final boolean isZoomSupported() {
        return this.zoomSupported;
    }
    
    public final boolean supports(final Control control) {
        return this.getSupportedControls(control.getClass()).contains((Object)control);
    }
    
    public final boolean supports(final GestureAction gestureAction) {
        switch (CameraOptions$1.$SwitchMap$com$otaliastudios$cameraview$gesture$GestureAction[gestureAction.ordinal()]) {
            default: {
                return false;
            }
            case 7: {
                return this.isExposureCorrectionSupported();
            }
            case 6: {
                return this.isZoomSupported();
            }
            case 2:
            case 3:
            case 4:
            case 5: {
                return true;
            }
            case 1: {
                return this.isAutoFocusSupported();
            }
        }
    }
}
