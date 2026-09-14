package com.otaliastudios.cameraview;

import java.io.File;
import android.os.Build$VERSION;
import android.graphics.BitmapFactory$Options;
import com.otaliastudios.cameraview.size.Size;
import android.location.Location;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.controls.Facing;

public class PictureResult
{
    private final byte[] data;
    private final Facing facing;
    private final PictureFormat format;
    private final boolean isSnapshot;
    private final Location location;
    private final int rotation;
    private final Size size;
    
    PictureResult(final Stub stub) {
        this.isSnapshot = stub.isSnapshot;
        this.location = stub.location;
        this.rotation = stub.rotation;
        this.size = stub.size;
        this.facing = stub.facing;
        this.data = stub.data;
        this.format = stub.format;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public Facing getFacing() {
        return this.facing;
    }
    
    public PictureFormat getFormat() {
        return this.format;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public int getRotation() {
        return this.rotation;
    }
    
    public Size getSize() {
        return this.size;
    }
    
    public boolean isSnapshot() {
        return this.isSnapshot;
    }
    
    public void toBitmap(final int n, final int n2, final BitmapCallback bitmapCallback) {
        if (this.format == PictureFormat.JPEG) {
            CameraUtils.decodeBitmap(this.getData(), n, n2, new BitmapFactory$Options(), this.rotation, bitmapCallback);
        }
        else {
            if (this.format != PictureFormat.DNG || Build$VERSION.SDK_INT < 24) {
                final StringBuilder sb = new StringBuilder();
                sb.append("PictureResult.toBitmap() does not support this picture format: ");
                sb.append((Object)this.format);
                throw new UnsupportedOperationException(sb.toString());
            }
            CameraUtils.decodeBitmap(this.getData(), n, n2, new BitmapFactory$Options(), this.rotation, bitmapCallback);
        }
    }
    
    public void toBitmap(final BitmapCallback bitmapCallback) {
        this.toBitmap(-1, -1, bitmapCallback);
    }
    
    public void toFile(final File file, final FileCallback fileCallback) {
        CameraUtils.writeToFile(this.getData(), file, fileCallback);
    }
    
    public static class Stub
    {
        public byte[] data;
        public Facing facing;
        public PictureFormat format;
        public boolean isSnapshot;
        public Location location;
        public int rotation;
        public Size size;
        
        Stub() {
        }
    }
}
