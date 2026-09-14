package com.king.zxing.analyze;

import java.nio.ByteBuffer;
import com.king.zxing.util.LogUtils;
import com.google.zxing.Result;
import androidx.camera.core.ImageProxy;

public abstract class ImageAnalyzer implements Analyzer
{
    public Result analyze(final ImageProxy imageProxy, int i) {
        if (imageProxy.getFormat() != 35) {
            final StringBuilder sb = new StringBuilder();
            sb.append("imageFormat: ");
            sb.append(imageProxy.getFormat());
            LogUtils.w(sb.toString());
            return null;
        }
        final ByteBuffer buffer = imageProxy.getPlanes()[0].getBuffer();
        final int remaining = buffer.remaining();
        final byte[] array = new byte[remaining];
        buffer.get(array);
        final int width = imageProxy.getWidth();
        final int height = imageProxy.getHeight();
        if (i == 1) {
            final byte[] array2 = new byte[remaining];
            int j;
            for (i = 0; i < height; ++i) {
                for (j = 0; j < width; ++j) {
                    array2[j * height + height - i - 1] = array[i * width + j];
                }
            }
            return this.analyze(array2, height, width);
        }
        return this.analyze(array, width, height);
    }
    
    public abstract Result analyze(final byte[] p0, final int p1, final int p2);
}
