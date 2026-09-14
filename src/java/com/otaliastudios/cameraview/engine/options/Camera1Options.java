package com.otaliastudios.cameraview.engine.options;

import android.media.CamcorderProfile;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import java.util.Iterator;
import java.util.List;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.internal.CamcorderProfiles;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.size.Size;
import android.hardware.Camera$Size;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.Flash;
import android.hardware.Camera;
import android.hardware.Camera$CameraInfo;
import com.otaliastudios.cameraview.engine.mappers.Camera1Mapper;
import android.hardware.Camera$Parameters;
import com.otaliastudios.cameraview.CameraOptions;

public class Camera1Options extends CameraOptions
{
    public Camera1Options(final Camera$Parameters camera$Parameters, int n, final boolean b) {
        final Camera1Mapper value = Camera1Mapper.get();
        final Camera$CameraInfo camera$CameraInfo = new Camera$CameraInfo();
        for (int numberOfCameras = Camera.getNumberOfCameras(), i = 0; i < numberOfCameras; ++i) {
            Camera.getCameraInfo(i, camera$CameraInfo);
            final Facing unmapFacing = value.unmapFacing(camera$CameraInfo.facing);
            if (unmapFacing != null) {
                this.supportedFacing.add((Object)unmapFacing);
            }
        }
        final List supportedWhiteBalance = camera$Parameters.getSupportedWhiteBalance();
        if (supportedWhiteBalance != null) {
            final Iterator iterator = supportedWhiteBalance.iterator();
            while (iterator.hasNext()) {
                final WhiteBalance unmapWhiteBalance = value.unmapWhiteBalance((String)iterator.next());
                if (unmapWhiteBalance != null) {
                    this.supportedWhiteBalance.add((Object)unmapWhiteBalance);
                }
            }
        }
        this.supportedFlash.add((Object)Flash.OFF);
        final List supportedFlashModes = camera$Parameters.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            final Iterator iterator2 = supportedFlashModes.iterator();
            while (iterator2.hasNext()) {
                final Flash unmapFlash = value.unmapFlash((String)iterator2.next());
                if (unmapFlash != null) {
                    this.supportedFlash.add((Object)unmapFlash);
                }
            }
        }
        this.supportedHdr.add((Object)Hdr.OFF);
        final List supportedSceneModes = camera$Parameters.getSupportedSceneModes();
        if (supportedSceneModes != null) {
            final Iterator iterator3 = supportedSceneModes.iterator();
            while (iterator3.hasNext()) {
                final Hdr unmapHdr = value.unmapHdr((String)iterator3.next());
                if (unmapHdr != null) {
                    this.supportedHdr.add((Object)unmapHdr);
                }
            }
        }
        this.zoomSupported = camera$Parameters.isZoomSupported();
        this.autoFocusSupported = camera$Parameters.getSupportedFocusModes().contains((Object)"auto");
        final float exposureCompensationStep = camera$Parameters.getExposureCompensationStep();
        this.exposureCorrectionMinValue = camera$Parameters.getMinExposureCompensation() * exposureCompensationStep;
        this.exposureCorrectionMaxValue = camera$Parameters.getMaxExposureCompensation() * exposureCompensationStep;
        this.exposureCorrectionSupported = (camera$Parameters.getMinExposureCompensation() != 0 || camera$Parameters.getMaxExposureCompensation() != 0);
        for (final Camera$Size camera$Size : camera$Parameters.getSupportedPictureSizes()) {
            int n2;
            if (b) {
                n2 = camera$Size.height;
            }
            else {
                n2 = camera$Size.width;
            }
            int n3;
            if (b) {
                n3 = camera$Size.width;
            }
            else {
                n3 = camera$Size.height;
            }
            this.supportedPictureSizes.add((Object)new Size(n2, n3));
            this.supportedPictureAspectRatio.add((Object)AspectRatio.of(n2, n3));
        }
        final CamcorderProfile value2 = CamcorderProfiles.get(n, new Size(Integer.MAX_VALUE, Integer.MAX_VALUE));
        final Size size = new Size(value2.videoFrameWidth, value2.videoFrameHeight);
        final List supportedVideoSizes = camera$Parameters.getSupportedVideoSizes();
        if (supportedVideoSizes != null) {
            for (final Camera$Size camera$Size2 : supportedVideoSizes) {
                if (camera$Size2.width <= size.getWidth() && camera$Size2.height <= size.getHeight()) {
                    if (b) {
                        n = camera$Size2.height;
                    }
                    else {
                        n = camera$Size2.width;
                    }
                    int n4;
                    if (b) {
                        n4 = camera$Size2.width;
                    }
                    else {
                        n4 = camera$Size2.height;
                    }
                    this.supportedVideoSizes.add((Object)new Size(n, n4));
                    this.supportedVideoAspectRatio.add((Object)AspectRatio.of(n, n4));
                }
            }
        }
        else {
            for (final Camera$Size camera$Size3 : camera$Parameters.getSupportedPreviewSizes()) {
                if (camera$Size3.width <= size.getWidth() && camera$Size3.height <= size.getHeight()) {
                    if (b) {
                        n = camera$Size3.height;
                    }
                    else {
                        n = camera$Size3.width;
                    }
                    int n5;
                    if (b) {
                        n5 = camera$Size3.width;
                    }
                    else {
                        n5 = camera$Size3.height;
                    }
                    this.supportedVideoSizes.add((Object)new Size(n, n5));
                    this.supportedVideoAspectRatio.add((Object)AspectRatio.of(n, n5));
                }
            }
        }
        this.previewFrameRateMinValue = Float.MAX_VALUE;
        this.previewFrameRateMaxValue = -3.4028235E38f;
        for (final int[] array : camera$Parameters.getSupportedPreviewFpsRange()) {
            final float n6 = array[0] / 1000.0f;
            final float n7 = array[1] / 1000.0f;
            this.previewFrameRateMinValue = Math.min(this.previewFrameRateMinValue, n6);
            this.previewFrameRateMaxValue = Math.max(this.previewFrameRateMaxValue, n7);
        }
        this.supportedPictureFormats.add((Object)PictureFormat.JPEG);
        this.supportedFrameProcessingFormats.add((Object)17);
    }
}
