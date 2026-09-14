package com.otaliastudios.cameraview.filter;

import com.otaliastudios.cameraview.filters.VignetteFilter;
import com.otaliastudios.cameraview.filters.TintFilter;
import com.otaliastudios.cameraview.filters.TemperatureFilter;
import com.otaliastudios.cameraview.filters.SharpnessFilter;
import com.otaliastudios.cameraview.filters.SepiaFilter;
import com.otaliastudios.cameraview.filters.SaturationFilter;
import com.otaliastudios.cameraview.filters.PosterizeFilter;
import com.otaliastudios.cameraview.filters.LomoishFilter;
import com.otaliastudios.cameraview.filters.InvertColorsFilter;
import com.otaliastudios.cameraview.filters.HueFilter;
import com.otaliastudios.cameraview.filters.GrayscaleFilter;
import com.otaliastudios.cameraview.filters.GrainFilter;
import com.otaliastudios.cameraview.filters.GammaFilter;
import com.otaliastudios.cameraview.filters.FillLightFilter;
import com.otaliastudios.cameraview.filters.DuotoneFilter;
import com.otaliastudios.cameraview.filters.DocumentaryFilter;
import com.otaliastudios.cameraview.filters.CrossProcessFilter;
import com.otaliastudios.cameraview.filters.ContrastFilter;
import com.otaliastudios.cameraview.filters.BrightnessFilter;
import com.otaliastudios.cameraview.filters.BlackAndWhiteFilter;
import com.otaliastudios.cameraview.filters.AutoFixFilter;

public enum Filters
{
    private static final Filters[] $VALUES;
    
    AUTO_FIX((Class<? extends Filter>)AutoFixFilter.class), 
    BLACK_AND_WHITE((Class<? extends Filter>)BlackAndWhiteFilter.class), 
    BRIGHTNESS((Class<? extends Filter>)BrightnessFilter.class), 
    CONTRAST((Class<? extends Filter>)ContrastFilter.class), 
    CROSS_PROCESS((Class<? extends Filter>)CrossProcessFilter.class), 
    DOCUMENTARY((Class<? extends Filter>)DocumentaryFilter.class), 
    DUOTONE((Class<? extends Filter>)DuotoneFilter.class), 
    FILL_LIGHT((Class<? extends Filter>)FillLightFilter.class), 
    GAMMA((Class<? extends Filter>)GammaFilter.class), 
    GRAIN((Class<? extends Filter>)GrainFilter.class), 
    GRAYSCALE((Class<? extends Filter>)GrayscaleFilter.class), 
    HUE((Class<? extends Filter>)HueFilter.class), 
    INVERT_COLORS((Class<? extends Filter>)InvertColorsFilter.class), 
    LOMOISH((Class<? extends Filter>)LomoishFilter.class), 
    NONE((Class<? extends Filter>)NoFilter.class), 
    POSTERIZE((Class<? extends Filter>)PosterizeFilter.class), 
    SATURATION((Class<? extends Filter>)SaturationFilter.class), 
    SEPIA((Class<? extends Filter>)SepiaFilter.class), 
    SHARPNESS((Class<? extends Filter>)SharpnessFilter.class), 
    TEMPERATURE((Class<? extends Filter>)TemperatureFilter.class), 
    TINT((Class<? extends Filter>)TintFilter.class), 
    VIGNETTE((Class<? extends Filter>)VignetteFilter.class);
    
    private Class<? extends Filter> filterClass;
    
    private Filters(final Class<? extends Filter> filterClass) {
        this.filterClass = filterClass;
    }
    
    public Filter newInstance() {
        try {
            return (Filter)this.filterClass.newInstance();
        }
        catch (final InstantiationException ex) {
            return (Filter)new NoFilter();
        }
        catch (final IllegalAccessException ex2) {
            return (Filter)new NoFilter();
        }
    }
}
