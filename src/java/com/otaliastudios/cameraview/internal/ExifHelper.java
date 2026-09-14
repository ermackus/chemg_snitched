package com.otaliastudios.cameraview.internal;

public class ExifHelper
{
    public static int getExifOrientation(final int n) {
        final int n2 = (n + 360) % 360;
        if (n2 == 0) {
            return 1;
        }
        if (n2 == 90) {
            return 6;
        }
        if (n2 == 180) {
            return 3;
        }
        if (n2 == 270) {
            return 8;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Invalid orientation: ");
        sb.append(n);
        throw new IllegalArgumentException(sb.toString());
    }
    
    public static int getOrientation(final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = n2;
                return n2;
            }
            case 1:
            case 2: {
                return n2;
            }
            case 7:
            case 8: {
                n2 = 270;
                return n2;
            }
            case 5:
            case 6: {
                n2 = 90;
                return n2;
            }
            case 3:
            case 4: {
                n2 = 180;
                return n2;
            }
        }
    }
}
