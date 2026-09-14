package com.otaliastudios.cameraview.internal;

import android.graphics.Rect;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.size.Size;

public class CropHelper
{
    public static Rect computeCrop(final Size size, final AspectRatio aspectRatio) {
        int width = size.getWidth();
        int height = size.getHeight();
        final boolean matches = aspectRatio.matches(size, 5.0E-4f);
        int round = 0;
        if (matches) {
            return new Rect(0, 0, width, height);
        }
        int round3;
        if (AspectRatio.of(width, height).toFloat() > aspectRatio.toFloat()) {
            final int round2 = Math.round(height * aspectRatio.toFloat());
            round = Math.round((width - round2) / 2.0f);
            width = round2;
            round3 = 0;
        }
        else {
            final int round4 = Math.round(width / aspectRatio.toFloat());
            round3 = Math.round((height - round4) / 2.0f);
            height = round4;
        }
        return new Rect(round, round3, width + round, height + round3);
    }
}
