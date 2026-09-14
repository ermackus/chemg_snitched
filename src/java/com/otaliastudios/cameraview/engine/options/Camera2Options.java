package com.otaliastudios.cameraview.engine.options;

import android.hardware.camera2.CameraAccessException;
import android.media.CamcorderProfile;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.controls.Facing;
import android.graphics.ImageFormat;
import com.otaliastudios.cameraview.controls.PictureFormat;
import android.media.MediaRecorder;
import com.otaliastudios.cameraview.internal.CamcorderProfiles;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.size.Size;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Rational;
import android.util.Range;
import com.otaliastudios.cameraview.controls.Hdr;
import java.util.Collection;
import com.otaliastudios.cameraview.controls.Flash;
import android.hardware.camera2.CameraCharacteristics;
import com.otaliastudios.cameraview.engine.mappers.Camera2Mapper;
import android.hardware.camera2.CameraManager;
import com.otaliastudios.cameraview.CameraOptions;

public class Camera2Options extends CameraOptions
{
    public Camera2Options(final CameraManager cameraManager, final String s, final boolean b, int i) throws CameraAccessException {
        final Camera2Mapper value = Camera2Mapper.get();
        final CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(s);
        final String[] cameraIdList = cameraManager.getCameraIdList();
        final int length = cameraIdList.length;
        final int n = 0;
        for (int j = 0; j < length; ++j) {
            final Integer n2 = (Integer)cameraManager.getCameraCharacteristics(cameraIdList[j]).get(CameraCharacteristics.LENS_FACING);
            if (n2 != null) {
                final Facing unmapFacing = value.unmapFacing((int)n2);
                if (unmapFacing != null) {
                    this.supportedFacing.add((Object)unmapFacing);
                }
            }
        }
        final int[] array = (int[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        for (int length2 = array.length, k = 0; k < length2; ++k) {
            final WhiteBalance unmapWhiteBalance = value.unmapWhiteBalance(array[k]);
            if (unmapWhiteBalance != null) {
                this.supportedWhiteBalance.add((Object)unmapWhiteBalance);
            }
        }
        this.supportedFlash.add((Object)Flash.OFF);
        final Boolean b2 = (Boolean)cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        if (b2 != null && b2) {
            final int[] array2 = (int[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
            for (int length3 = array2.length, l = 0; l < length3; ++l) {
                this.supportedFlash.addAll((Collection)value.unmapFlash(array2[l]));
            }
        }
        this.supportedHdr.add((Object)Hdr.OFF);
        final int[] array3 = (int[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES);
        for (int length4 = array3.length, n3 = 0; n3 < length4; ++n3) {
            final Hdr unmapHdr = value.unmapHdr(array3[n3]);
            if (unmapHdr != null) {
                this.supportedHdr.add((Object)unmapHdr);
            }
        }
        final Float n4 = (Float)cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        final int n5 = 1;
        if (n4 != null) {
            this.zoomSupported = (n4 > 1.0f);
        }
        final Integer n6 = (Integer)cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        final Integer n7 = (Integer)cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        final Integer n8 = (Integer)cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        this.autoFocusSupported = ((n6 != null && n6 > 0) || (n7 != null && n7 > 0) || (n8 != null && n8 > 0));
        final Range range = (Range)cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        final Rational rational = (Rational)cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
        if (range != null && rational != null && rational.floatValue() != 0.0f) {
            this.exposureCorrectionMinValue = (int)range.getLower() / rational.floatValue();
            this.exposureCorrectionMaxValue = (int)range.getUpper() / rational.floatValue();
        }
        this.exposureCorrectionSupported = (this.exposureCorrectionMinValue != 0.0f && this.exposureCorrectionMaxValue != 0.0f);
        final StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            final int[] outputFormats = streamConfigurationMap.getOutputFormats();
            final int length5 = outputFormats.length;
            int n9 = 0;
            while (true) {
                while (n9 < length5) {
                    if (outputFormats[n9] == i) {
                        final int n10 = n5;
                        if (n10 != 0) {
                            final android.util.Size[] outputSizes = streamConfigurationMap.getOutputSizes(i);
                            int length6;
                            android.util.Size size;
                            int n11;
                            int n12;
                            for (length6 = outputSizes.length, i = 0; i < length6; ++i) {
                                size = outputSizes[i];
                                if (b) {
                                    n11 = size.getHeight();
                                }
                                else {
                                    n11 = size.getWidth();
                                }
                                if (b) {
                                    n12 = size.getWidth();
                                }
                                else {
                                    n12 = size.getHeight();
                                }
                                this.supportedPictureSizes.add((Object)new Size(n11, n12));
                                this.supportedPictureAspectRatio.add((Object)AspectRatio.of(n11, n12));
                            }
                            final CamcorderProfile value2 = CamcorderProfiles.get(s, new Size(Integer.MAX_VALUE, Integer.MAX_VALUE));
                            final Size size2 = new Size(value2.videoFrameWidth, value2.videoFrameHeight);
                            final android.util.Size[] outputSizes2 = streamConfigurationMap.getOutputSizes((Class)MediaRecorder.class);
                            int length7;
                            android.util.Size size3;
                            int n13;
                            int n14;
                            for (length7 = outputSizes2.length, i = 0; i < length7; ++i) {
                                size3 = outputSizes2[i];
                                if (size3.getWidth() <= size2.getWidth() && size3.getHeight() <= size2.getHeight()) {
                                    if (b) {
                                        n13 = size3.getHeight();
                                    }
                                    else {
                                        n13 = size3.getWidth();
                                    }
                                    if (b) {
                                        n14 = size3.getWidth();
                                    }
                                    else {
                                        n14 = size3.getHeight();
                                    }
                                    this.supportedVideoSizes.add((Object)new Size(n13, n14));
                                    this.supportedVideoAspectRatio.add((Object)AspectRatio.of(n13, n14));
                                }
                            }
                            final Range[] array4 = (Range[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                            if (array4 != null) {
                                this.previewFrameRateMinValue = Float.MAX_VALUE;
                                this.previewFrameRateMaxValue = -3.4028235E38f;
                                int length8;
                                Range range2;
                                for (length8 = array4.length, i = 0; i < length8; ++i) {
                                    range2 = array4[i];
                                    this.previewFrameRateMinValue = Math.min(this.previewFrameRateMinValue, (float)(int)range2.getLower());
                                    this.previewFrameRateMaxValue = Math.max(this.previewFrameRateMaxValue, (float)(int)range2.getUpper());
                                }
                            }
                            else {
                                this.previewFrameRateMinValue = 0.0f;
                                this.previewFrameRateMaxValue = 0.0f;
                            }
                            this.supportedPictureFormats.add((Object)PictureFormat.JPEG);
                            final int[] array5 = (int[])cameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
                            if (array5 != null) {
                                int length9;
                                for (length9 = array5.length, i = 0; i < length9; ++i) {
                                    if (array5[i] == 3) {
                                        this.supportedPictureFormats.add((Object)PictureFormat.DNG);
                                    }
                                }
                            }
                            this.supportedFrameProcessingFormats.add((Object)35);
                            final int[] outputFormats2 = streamConfigurationMap.getOutputFormats();
                            int length10;
                            int n15;
                            for (length10 = outputFormats2.length, i = n; i < length10; ++i) {
                                n15 = outputFormats2[i];
                                if (ImageFormat.getBitsPerPixel(n15) > 0) {
                                    this.supportedFrameProcessingFormats.add((Object)n15);
                                }
                            }
                            return;
                        }
                        final StringBuilder sb = new StringBuilder();
                        sb.append("Picture format not supported: ");
                        sb.append(i);
                        throw new IllegalStateException(sb.toString());
                    }
                    else {
                        ++n9;
                    }
                }
                final int n10 = 0;
                continue;
            }
        }
        throw new RuntimeException("StreamConfigurationMap is null. Should not happen.");
    }
}
