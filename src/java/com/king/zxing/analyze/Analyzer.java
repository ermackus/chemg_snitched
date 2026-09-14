package com.king.zxing.analyze;

import com.google.zxing.Result;
import androidx.camera.core.ImageProxy;

public interface Analyzer
{
    Result analyze(final ImageProxy p0, final int p1);
}
