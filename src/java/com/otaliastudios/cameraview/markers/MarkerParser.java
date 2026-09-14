package com.otaliastudios.cameraview.markers;

import com.otaliastudios.cameraview.R;
import android.content.res.TypedArray;

public class MarkerParser
{
    private AutoFocusMarker autoFocusMarker;
    
    public MarkerParser(final TypedArray typedArray) {
        this.autoFocusMarker = null;
        final String string = typedArray.getString(R.styleable.CameraView_cameraAutoFocusMarker);
        if (string == null) {
            return;
        }
        try {
            this.autoFocusMarker = (AutoFocusMarker)Class.forName(string).newInstance();
        }
        catch (final Exception ex) {}
    }
    
    public AutoFocusMarker getAutoFocusMarker() {
        return this.autoFocusMarker;
    }
}
