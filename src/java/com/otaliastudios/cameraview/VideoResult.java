package com.otaliastudios.cameraview;

import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.size.Size;
import android.location.Location;
import java.io.FileDescriptor;
import java.io.File;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.Audio;

public class VideoResult
{
    public static final int REASON_MAX_DURATION_REACHED = 2;
    public static final int REASON_MAX_SIZE_REACHED = 1;
    public static final int REASON_USER = 0;
    private final Audio audio;
    private final int audioBitRate;
    private final AudioCodec audioCodec;
    private final int endReason;
    private final Facing facing;
    private final File file;
    private final FileDescriptor fileDescriptor;
    private final boolean isSnapshot;
    private final Location location;
    private final int maxDuration;
    private final long maxSize;
    private final int rotation;
    private final Size size;
    private final int videoBitRate;
    private final VideoCodec videoCodec;
    private final int videoFrameRate;
    
    VideoResult(final Stub stub) {
        this.isSnapshot = stub.isSnapshot;
        this.location = stub.location;
        this.rotation = stub.rotation;
        this.size = stub.size;
        this.file = stub.file;
        this.fileDescriptor = stub.fileDescriptor;
        this.facing = stub.facing;
        this.videoCodec = stub.videoCodec;
        this.audioCodec = stub.audioCodec;
        this.audio = stub.audio;
        this.maxSize = stub.maxSize;
        this.maxDuration = stub.maxDuration;
        this.endReason = stub.endReason;
        this.videoBitRate = stub.videoBitRate;
        this.videoFrameRate = stub.videoFrameRate;
        this.audioBitRate = stub.audioBitRate;
    }
    
    public Audio getAudio() {
        return this.audio;
    }
    
    public int getAudioBitRate() {
        return this.audioBitRate;
    }
    
    public AudioCodec getAudioCodec() {
        return this.audioCodec;
    }
    
    public Facing getFacing() {
        return this.facing;
    }
    
    public File getFile() {
        final File file = this.file;
        if (file != null) {
            return file;
        }
        throw new RuntimeException("File is only available when takeVideo(File) is used.");
    }
    
    public FileDescriptor getFileDescriptor() {
        final FileDescriptor fileDescriptor = this.fileDescriptor;
        if (fileDescriptor != null) {
            return fileDescriptor;
        }
        throw new RuntimeException("FileDescriptor is only available when takeVideo(FileDescriptor) is used.");
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public int getMaxDuration() {
        return this.maxDuration;
    }
    
    public long getMaxSize() {
        return this.maxSize;
    }
    
    public int getRotation() {
        return this.rotation;
    }
    
    public Size getSize() {
        return this.size;
    }
    
    public int getTerminationReason() {
        return this.endReason;
    }
    
    public int getVideoBitRate() {
        return this.videoBitRate;
    }
    
    public VideoCodec getVideoCodec() {
        return this.videoCodec;
    }
    
    public int getVideoFrameRate() {
        return this.videoFrameRate;
    }
    
    public boolean isSnapshot() {
        return this.isSnapshot;
    }
    
    public static class Stub
    {
        public Audio audio;
        public int audioBitRate;
        public AudioCodec audioCodec;
        public int endReason;
        public Facing facing;
        public File file;
        public FileDescriptor fileDescriptor;
        public boolean isSnapshot;
        public Location location;
        public int maxDuration;
        public long maxSize;
        public int rotation;
        public Size size;
        public int videoBitRate;
        public VideoCodec videoCodec;
        public int videoFrameRate;
        
        Stub() {
        }
    }
}
