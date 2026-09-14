package com.otaliastudios.cameraview.picture;

import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class SnapshotPictureRecorder extends PictureRecorder
{
    protected static final CameraLogger LOG;
    private static final String TAG;
    
    static {
        LOG = CameraLogger.create(TAG = SnapshotPictureRecorder.class.getSimpleName());
    }
    
    public SnapshotPictureRecorder(final PictureResult$Stub pictureResult$Stub, final PictureRecorder$PictureResultListener pictureRecorder$PictureResultListener) {
        super(pictureResult$Stub, pictureRecorder$PictureResultListener);
    }
}
