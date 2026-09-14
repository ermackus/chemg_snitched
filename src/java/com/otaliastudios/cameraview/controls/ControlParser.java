package com.otaliastudios.cameraview.controls;

import com.otaliastudios.cameraview.R;
import android.content.res.TypedArray;
import android.content.Context;

public class ControlParser
{
    private int audio;
    private int audioCodec;
    private int engine;
    private int facing;
    private int flash;
    private int grid;
    private int hdr;
    private int mode;
    private int pictureFormat;
    private int preview;
    private int videoCodec;
    private int whiteBalance;
    
    public ControlParser(final Context context, final TypedArray typedArray) {
        this.preview = typedArray.getInteger(R.styleable.CameraView_cameraPreview, Preview.DEFAULT.value());
        this.facing = typedArray.getInteger(R.styleable.CameraView_cameraFacing, Facing.DEFAULT(context).value());
        this.flash = typedArray.getInteger(R.styleable.CameraView_cameraFlash, Flash.DEFAULT.value());
        this.grid = typedArray.getInteger(R.styleable.CameraView_cameraGrid, Grid.DEFAULT.value());
        this.whiteBalance = typedArray.getInteger(R.styleable.CameraView_cameraWhiteBalance, WhiteBalance.DEFAULT.value());
        this.mode = typedArray.getInteger(R.styleable.CameraView_cameraMode, Mode.DEFAULT.value());
        this.hdr = typedArray.getInteger(R.styleable.CameraView_cameraHdr, Hdr.DEFAULT.value());
        this.audio = typedArray.getInteger(R.styleable.CameraView_cameraAudio, Audio.DEFAULT.value());
        this.videoCodec = typedArray.getInteger(R.styleable.CameraView_cameraVideoCodec, VideoCodec.DEFAULT.value());
        this.audioCodec = typedArray.getInteger(R.styleable.CameraView_cameraAudioCodec, AudioCodec.DEFAULT.value());
        this.engine = typedArray.getInteger(R.styleable.CameraView_cameraEngine, Engine.DEFAULT.value());
        this.pictureFormat = typedArray.getInteger(R.styleable.CameraView_cameraPictureFormat, PictureFormat.DEFAULT.value());
    }
    
    public Audio getAudio() {
        return Audio.fromValue(this.audio);
    }
    
    public AudioCodec getAudioCodec() {
        return AudioCodec.fromValue(this.audioCodec);
    }
    
    public Engine getEngine() {
        return Engine.fromValue(this.engine);
    }
    
    public Facing getFacing() {
        return Facing.fromValue(this.facing);
    }
    
    public Flash getFlash() {
        return Flash.fromValue(this.flash);
    }
    
    public Grid getGrid() {
        return Grid.fromValue(this.grid);
    }
    
    public Hdr getHdr() {
        return Hdr.fromValue(this.hdr);
    }
    
    public Mode getMode() {
        return Mode.fromValue(this.mode);
    }
    
    public PictureFormat getPictureFormat() {
        return PictureFormat.fromValue(this.pictureFormat);
    }
    
    public Preview getPreview() {
        return Preview.fromValue(this.preview);
    }
    
    public VideoCodec getVideoCodec() {
        return VideoCodec.fromValue(this.videoCodec);
    }
    
    public WhiteBalance getWhiteBalance() {
        return WhiteBalance.fromValue(this.whiteBalance);
    }
}
