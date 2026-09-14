package com.otaliastudios.cameraview.frame;

import android.media.Image;

public class ImageFrameManager extends FrameManager<Image>
{
    public ImageFrameManager(final int n) {
        super(n, (Class)Image.class);
    }
    
    protected Image onCloneFrameData(final Image image) {
        throw new RuntimeException("Cannot freeze() an Image Frame. Please consider using the frame synchronously in your process() method, which also gives better performance.");
    }
    
    protected void onFrameDataReleased(final Image image, final boolean b) {
        try {
            image.close();
        }
        catch (final Exception ex) {}
    }
}
