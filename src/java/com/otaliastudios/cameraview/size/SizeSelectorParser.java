package com.otaliastudios.cameraview.size;

import java.util.List;
import com.otaliastudios.cameraview.R;
import java.util.ArrayList;
import android.content.res.TypedArray;

public class SizeSelectorParser
{
    private SizeSelector pictureSizeSelector;
    private SizeSelector videoSizeSelector;
    
    public SizeSelectorParser(final TypedArray typedArray) {
        final ArrayList list = new ArrayList(3);
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMinWidth)) {
            ((List)list).add((Object)SizeSelectors.minWidth(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMinWidth, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMaxWidth)) {
            ((List)list).add((Object)SizeSelectors.maxWidth(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMaxWidth, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMinHeight)) {
            ((List)list).add((Object)SizeSelectors.minHeight(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMinHeight, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMaxHeight)) {
            ((List)list).add((Object)SizeSelectors.maxHeight(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMaxHeight, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMinArea)) {
            ((List)list).add((Object)SizeSelectors.minArea(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMinArea, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeMaxArea)) {
            ((List)list).add((Object)SizeSelectors.maxArea(typedArray.getInteger(R.styleable.CameraView_cameraPictureSizeMaxArea, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraPictureSizeAspectRatio)) {
            ((List)list).add((Object)SizeSelectors.aspectRatio(AspectRatio.parse(typedArray.getString(R.styleable.CameraView_cameraPictureSizeAspectRatio)), 0.0f));
        }
        if (typedArray.getBoolean(R.styleable.CameraView_cameraPictureSizeSmallest, false)) {
            ((List)list).add((Object)SizeSelectors.smallest());
        }
        if (typedArray.getBoolean(R.styleable.CameraView_cameraPictureSizeBiggest, false)) {
            ((List)list).add((Object)SizeSelectors.biggest());
        }
        SizeSelector pictureSizeSelector;
        if (!((List)list).isEmpty()) {
            pictureSizeSelector = SizeSelectors.and((SizeSelector[])((List)list).toArray((Object[])new SizeSelector[0]));
        }
        else {
            pictureSizeSelector = SizeSelectors.biggest();
        }
        this.pictureSizeSelector = pictureSizeSelector;
        final ArrayList list2 = new ArrayList(3);
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMinWidth)) {
            ((List)list2).add((Object)SizeSelectors.minWidth(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMinWidth, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMaxWidth)) {
            ((List)list2).add((Object)SizeSelectors.maxWidth(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMaxWidth, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMinHeight)) {
            ((List)list2).add((Object)SizeSelectors.minHeight(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMinHeight, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMaxHeight)) {
            ((List)list2).add((Object)SizeSelectors.maxHeight(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMaxHeight, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMinArea)) {
            ((List)list2).add((Object)SizeSelectors.minArea(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMinArea, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeMaxArea)) {
            ((List)list2).add((Object)SizeSelectors.maxArea(typedArray.getInteger(R.styleable.CameraView_cameraVideoSizeMaxArea, 0)));
        }
        if (typedArray.hasValue(R.styleable.CameraView_cameraVideoSizeAspectRatio)) {
            ((List)list2).add((Object)SizeSelectors.aspectRatio(AspectRatio.parse(typedArray.getString(R.styleable.CameraView_cameraVideoSizeAspectRatio)), 0.0f));
        }
        if (typedArray.getBoolean(R.styleable.CameraView_cameraVideoSizeSmallest, false)) {
            ((List)list2).add((Object)SizeSelectors.smallest());
        }
        if (typedArray.getBoolean(R.styleable.CameraView_cameraVideoSizeBiggest, false)) {
            ((List)list2).add((Object)SizeSelectors.biggest());
        }
        SizeSelector videoSizeSelector;
        if (!((List)list2).isEmpty()) {
            videoSizeSelector = SizeSelectors.and((SizeSelector[])((List)list2).toArray((Object[])new SizeSelector[0]));
        }
        else {
            videoSizeSelector = SizeSelectors.biggest();
        }
        this.videoSizeSelector = videoSizeSelector;
    }
    
    public SizeSelector getPictureSizeSelector() {
        return this.pictureSizeSelector;
    }
    
    public SizeSelector getVideoSizeSelector() {
        return this.videoSizeSelector;
    }
}
