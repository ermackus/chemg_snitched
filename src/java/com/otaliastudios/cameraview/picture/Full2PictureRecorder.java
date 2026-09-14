package com.otaliastudios.cameraview.picture;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.InputStream;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import android.media.Image;
import android.hardware.camera2.CameraAccessException;
import com.otaliastudios.cameraview.internal.ExifHelper;
import android.hardware.camera2.CaptureResult;
import com.otaliastudios.cameraview.controls.PictureFormat;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import com.otaliastudios.cameraview.engine.Camera2Engine;
import com.otaliastudios.cameraview.PictureResult$Stub;
import android.media.ImageReader;
import android.hardware.camera2.CaptureRequest$Builder;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import android.hardware.camera2.DngCreator;
import com.otaliastudios.cameraview.engine.action.Action;
import android.media.ImageReader$OnImageAvailableListener;

public class Full2PictureRecorder extends FullPictureRecorder implements ImageReader$OnImageAvailableListener
{
    private final Action mAction;
    private DngCreator mDngCreator;
    private final ActionHolder mHolder;
    private final CaptureRequest$Builder mPictureBuilder;
    private final ImageReader mPictureReader;
    
    public Full2PictureRecorder(final PictureResult$Stub pictureResult$Stub, final Camera2Engine mHolder, final CaptureRequest$Builder mPictureBuilder, final ImageReader mPictureReader) {
        super(pictureResult$Stub, (PictureRecorder$PictureResultListener)mHolder);
        this.mHolder = (ActionHolder)mHolder;
        this.mPictureBuilder = mPictureBuilder;
        (this.mPictureReader = mPictureReader).setOnImageAvailableListener((ImageReader$OnImageAvailableListener)this, WorkerHandler.get().getHandler());
        this.mAction = (Action)new BaseAction(this) {
            final Full2PictureRecorder this$0;
            
            public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
                try {
                    super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
                }
                catch (final Exception mError) {
                    this.this$0.mError = mError;
                    this.this$0.dispatchResult();
                }
                if (this.this$0.mResult.format == PictureFormat.DNG) {
                    this.this$0.mDngCreator = new DngCreator(actionHolder.getCharacteristics((Action)this), (CaptureResult)totalCaptureResult);
                    this.this$0.mDngCreator.setOrientation(ExifHelper.getExifOrientation(this.this$0.mResult.rotation));
                    if (this.this$0.mResult.location != null) {
                        this.this$0.mDngCreator.setLocation(this.this$0.mResult.location);
                    }
                }
            }
            
            public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
                super.onCaptureStarted(actionHolder, captureRequest);
                if (captureRequest.getTag() == Integer.valueOf(2)) {
                    FullPictureRecorder.LOG.i(new Object[] { "onCaptureStarted:", "Dispatching picture shutter." });
                    this.this$0.dispatchOnShutter(false);
                    this.setState(Integer.MAX_VALUE);
                }
            }
            
            protected void onStart(final ActionHolder actionHolder) {
                super.onStart(actionHolder);
                this.this$0.mPictureBuilder.addTarget(this.this$0.mPictureReader.getSurface());
                if (this.this$0.mResult.format == PictureFormat.JPEG) {
                    this.this$0.mPictureBuilder.set(CaptureRequest.JPEG_ORIENTATION, (Object)this.this$0.mResult.rotation);
                }
                this.this$0.mPictureBuilder.setTag((Object)2);
                try {
                    actionHolder.applyBuilder((Action)this, this.this$0.mPictureBuilder);
                }
                catch (final CameraAccessException mError) {
                    this.this$0.mResult = null;
                    this.this$0.mError = (Exception)mError;
                    this.this$0.dispatchResult();
                    this.setState(Integer.MAX_VALUE);
                }
            }
        };
    }
    
    private void readJpegImage(final Image image) {
        final ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        final byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        this.mResult.data = data;
        this.mResult.rotation = 0;
        try {
            this.mResult.rotation = ExifHelper.getOrientation(new ExifInterface((InputStream)new ByteArrayInputStream(this.mResult.data)).getAttributeInt("Orientation", 1));
        }
        catch (final IOException ex) {}
    }
    
    private void readRawImage(final Image ex) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)byteArrayOutputStream);
        try {
            this.mDngCreator.writeImage((OutputStream)bufferedOutputStream, (Image)ex);
            bufferedOutputStream.flush();
            this.mResult.data = byteArrayOutputStream.toByteArray();
        }
        catch (final IOException ex) {
            this.mDngCreator.close();
            try {
                bufferedOutputStream.close();
                throw new RuntimeException((Throwable)ex);
            }
            catch (final IOException ex2) {}
        }
    }
    
    public void onImageAvailable(final ImageReader imageReader) {
        Full2PictureRecorder.LOG.i(new Object[] { "onImageAvailable started." });
        Image image2 = null;
        Label_0206: {
            Image image;
            try {
                final Image acquireNextImage = imageReader.acquireNextImage();
                try {
                    try {
                        final int n = Full2PictureRecorder$2.$SwitchMap$com$otaliastudios$cameraview$controls$PictureFormat[this.mResult.format.ordinal()];
                        if (n != 1) {
                            if (n != 2) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append("Unknown format: ");
                                sb.append((Object)this.mResult.format);
                                throw new IllegalStateException(sb.toString());
                            }
                            this.readRawImage(acquireNextImage);
                        }
                        else {
                            this.readJpegImage(acquireNextImage);
                        }
                        if (acquireNextImage != null) {
                            acquireNextImage.close();
                        }
                        Full2PictureRecorder.LOG.i(new Object[] { "onImageAvailable ended." });
                        this.dispatchResult();
                        return;
                    }
                    finally {}
                }
                catch (final Exception mError) {}
            }
            catch (final Exception mError) {
                image = null;
            }
            finally {
                image2 = null;
                break Label_0206;
            }
            this.mResult = null;
            final Exception mError;
            this.mError = mError;
            this.dispatchResult();
            if (image != null) {
                image.close();
            }
            return;
        }
        if (image2 != null) {
            image2.close();
        }
    }
    
    public void take() {
        this.mAction.start(this.mHolder);
    }
}
