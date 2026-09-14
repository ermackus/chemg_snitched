package com.otaliastudios.cameraview.filter;

import com.otaliastudios.cameraview.R;
import android.content.res.TypedArray;

public class FilterParser
{
    private Filter filter;
    
    public FilterParser(final TypedArray typedArray) {
        this.filter = null;
        final String string = typedArray.getString(R.styleable.CameraView_cameraFilter);
        try {
            this.filter = (Filter)Class.forName(string).newInstance();
        }
        catch (final Exception ex) {
            this.filter = (Filter)new NoFilter();
        }
    }
    
    public Filter getFilter() {
        return this.filter;
    }
}
