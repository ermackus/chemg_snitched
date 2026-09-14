package com.goodsrc.library.utils;

import android.graphics.Bitmap;

public class FastBlur
{
    public static Bitmap doBlur(final Bitmap bitmap, final int n, final boolean b) {
        Bitmap copy;
        if (b) {
            copy = bitmap;
        }
        else {
            copy = bitmap.copy(bitmap.getConfig(), true);
        }
        if (n < 1) {
            return null;
        }
        final int width = copy.getWidth();
        final int height = copy.getHeight();
        final int n2 = width * height;
        final int[] array = new int[n2];
        copy.getPixels(array, 0, width, 0, 0, width, height);
        final int n3 = width - 1;
        final int n4 = height - 1;
        final int n5 = n + n + 1;
        final int[] array2 = new int[n2];
        final int[] array3 = new int[n2];
        final int[] array4 = new int[n2];
        final int[] array5 = new int[Math.max(width, height)];
        final int n6 = n5 + 1 >> 1;
        final int n7 = n6 * n6;
        final int n8 = n7 * 256;
        final int[] array6 = new int[n8];
        for (int i = 0; i < n8; ++i) {
            array6[i] = i / n7;
        }
        final int[][] array7 = new int[n5][3];
        final int n9 = n + 1;
        int j = 0;
        int n10 = 0;
        int n11 = 0;
        final int n12 = n4;
        while (j < height) {
            int k = -n;
            int n13 = 0;
            int n14 = 0;
            int n15 = 0;
            int n16 = 0;
            int n17 = 0;
            int n18 = 0;
            int n19 = 0;
            int n20 = 0;
            int n21 = 0;
            while (k <= n) {
                final int n22 = array[n10 + Math.min(n3, Math.max(k, 0))];
                final int[] array8 = array7[k + n];
                array8[0] = (n22 & 0xFF0000) >> 16;
                array8[1] = (n22 & 0xFF00) >> 8;
                array8[2] = (n22 & 0xFF);
                final int n23 = n9 - Math.abs(k);
                n21 += array8[0] * n23;
                n13 += array8[1] * n23;
                n14 += array8[2] * n23;
                if (k > 0) {
                    n18 += array8[0];
                    n19 += array8[1];
                    n20 += array8[2];
                }
                else {
                    n15 += array8[0];
                    n16 += array8[1];
                    n17 += array8[2];
                }
                ++k;
            }
            final int n24 = n21;
            final int n25 = 0;
            int n26 = n20;
            int n27 = n19;
            int n28 = n18;
            int n29 = n;
            int n30 = n24;
            for (int l = n25; l < width; ++l) {
                array2[n10] = array6[n30];
                array3[n10] = array6[n13];
                array4[n10] = array6[n14];
                final int[] array9 = array7[(n29 - n + n5) % n5];
                final int n31 = array9[0];
                final int n32 = array9[1];
                final int n33 = array9[2];
                if (j == 0) {
                    array5[l] = Math.min(l + n + 1, n3);
                }
                final int n34 = array[n11 + array5[l]];
                array9[0] = (n34 & 0xFF0000) >> 16;
                array9[1] = (n34 & 0xFF00) >> 8;
                array9[2] = (n34 & 0xFF);
                final int n35 = n28 + array9[0];
                final int n36 = n27 + array9[1];
                final int n37 = n26 + array9[2];
                n30 = n30 - n15 + n35;
                n13 = n13 - n16 + n36;
                n14 = n14 - n17 + n37;
                n29 = (n29 + 1) % n5;
                final int[] array10 = array7[n29 % n5];
                n15 = n15 - n31 + array10[0];
                n16 = n16 - n32 + array10[1];
                n17 = n17 - n33 + array10[2];
                n28 = n35 - array10[0];
                n27 = n36 - array10[1];
                n26 = n37 - array10[2];
                ++n10;
            }
            n11 += width;
            ++j;
        }
        final int n38 = height;
        final int n39 = 0;
        final int n40 = n12;
        final int n41 = width;
        final int n42 = n5;
        for (int n43 = n39; n43 < n41; ++n43) {
            final int n44 = -n;
            int n45 = 0;
            int n46 = 0;
            int n47 = 0;
            int n48 = 0;
            int n49 = 0;
            int n50 = 0;
            int n51 = 0;
            int n52 = n44;
            int n53 = n44 * n41;
            int n54 = 0;
            int n55 = 0;
            while (n52 <= n) {
                final int n56 = Math.max(0, n53) + n43;
                final int[] array11 = array7[n52 + n];
                array11[0] = array2[n56];
                array11[1] = array3[n56];
                array11[2] = array4[n56];
                final int n57 = n9 - Math.abs(n52);
                n54 += array2[n56] * n57;
                n55 += array3[n56] * n57;
                n45 += array4[n56] * n57;
                if (n52 > 0) {
                    n49 += array11[0];
                    n50 += array11[1];
                    n51 += array11[2];
                }
                else {
                    n46 += array11[0];
                    n47 += array11[1];
                    n48 += array11[2];
                }
                int n58 = n53;
                if (n52 < n40) {
                    n58 = n53 + n41;
                }
                ++n52;
                n53 = n58;
            }
            final int n59 = n43;
            final int n60 = n55;
            final int n61 = n54;
            final int n62 = 0;
            int n63 = n;
            int n64 = n59;
            int n65 = n51;
            int n66 = n50;
            int n67 = n49;
            int n68 = n60;
            int n69 = n61;
            for (int n70 = n62; n70 < n38; ++n70) {
                array[n64] = ((array[n64] & 0xFF000000) | array6[n69] << 16 | array6[n68] << 8 | array6[n45]);
                final int[] array12 = array7[(n63 - n + n42) % n42];
                final int n71 = array12[0];
                final int n72 = array12[1];
                final int n73 = array12[2];
                if (n43 == 0) {
                    array5[n70] = Math.min(n70 + n9, n40) * n41;
                }
                final int n74 = array5[n70] + n43;
                array12[0] = array2[n74];
                array12[1] = array3[n74];
                array12[2] = array4[n74];
                final int n75 = n67 + array12[0];
                final int n76 = n66 + array12[1];
                final int n77 = n65 + array12[2];
                n69 = n69 - n46 + n75;
                n68 = n68 - n47 + n76;
                n45 = n45 - n48 + n77;
                n63 = (n63 + 1) % n42;
                final int[] array13 = array7[n63];
                n46 = n46 - n71 + array13[0];
                n47 = n47 - n72 + array13[1];
                n48 = n48 - n73 + array13[2];
                n67 = n75 - array13[0];
                n66 = n76 - array13[1];
                n65 = n77 - array13[2];
                n64 += n41;
            }
        }
        copy.setPixels(array, 0, n41, 0, 0, n41, n38);
        return copy;
    }
}
