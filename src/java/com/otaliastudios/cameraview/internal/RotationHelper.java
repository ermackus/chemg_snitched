package com.otaliastudios.cameraview.internal;

import com.otaliastudios.cameraview.size.Size;

@Deprecated
public class RotationHelper
{
    public static byte[] rotate(final byte[] array, final Size size, int i) {
        if (i == 0) {
            return array;
        }
        if (i % 90 == 0 && i >= 0 && i <= 270) {
            final int width = size.getWidth();
            final int height = size.getHeight();
            final byte[] array2 = new byte[array.length];
            final int n = width * height;
            final boolean b = i % 180 != 0;
            final boolean b2 = i % 270 != 0;
            final boolean b3 = i >= 180;
            int j;
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            int n9;
            for (i = 0; i < height; ++i) {
                for (j = 0; j < width; ++j) {
                    n2 = (i >> 1) * width + n + (j & 0xFFFFFFFE);
                    if (b) {
                        n3 = height;
                    }
                    else {
                        n3 = width;
                    }
                    if (b) {
                        n4 = width;
                    }
                    else {
                        n4 = height;
                    }
                    if (b) {
                        n5 = i;
                    }
                    else {
                        n5 = j;
                    }
                    if (b) {
                        n6 = j;
                    }
                    else {
                        n6 = i;
                    }
                    n7 = n5;
                    if (b2) {
                        n7 = n3 - n5 - 1;
                    }
                    n8 = n6;
                    if (b3) {
                        n8 = n4 - n6 - 1;
                    }
                    n9 = n + (n8 >> 1) * n3 + (n7 & 0xFFFFFFFE);
                    array2[n8 * n3 + n7] = (byte)(array[i * width + j] & 0xFF);
                    array2[n9] = (byte)(array[n2] & 0xFF);
                    array2[n9 + 1] = (byte)(array[n2 + 1] & 0xFF);
                }
            }
            return array2;
        }
        throw new IllegalArgumentException("0 <= rotation < 360, rotation % 90 == 0");
    }
}
