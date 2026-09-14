package com.kingagroot.kingdraw.core.view3d.view;

import java.util.ArrayList;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import com.kingagroot.kingdraw.core.view3d.opengl.GlBuffer;
import java.nio.FloatBuffer;

public class Cylinder
{
    private static float[] cylinderCoords;
    private FloatBuffer colorBuffer;
    private GlBuffer colorsGLBuffer;
    private FloatBuffer fbNormal;
    public Map<String, GlBuffer> glBufferMap;
    public GlBuffer normalGLBuffer;
    public GlBuffer positionGLBuffer;
    private FloatBuffer vertexBuffer;
    public final int vertexCount;
    
    public Cylinder() {
        this.glBufferMap = (Map<String, GlBuffer>)new HashMap();
        final float[] array = Cylinder.cylinderCoords = this.createCylinderPos(1.0f);
        this.vertexCount = array.length / 3;
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(array.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        (this.vertexBuffer = allocateDirect.asFloatBuffer()).put(Cylinder.cylinderCoords);
        this.vertexBuffer.position(0);
        final ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(Cylinder.cylinderCoords.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        (this.fbNormal = allocateDirect2.asFloatBuffer()).put(Cylinder.cylinderCoords);
        this.fbNormal.position(0);
        (this.positionGLBuffer = new GlBuffer()).initBuffer(4, this.vertexBuffer.limit(), this.vertexBuffer);
        (this.normalGLBuffer = new GlBuffer()).initBuffer(4, this.fbNormal.limit(), this.fbNormal);
    }
    
    public float[] createCylinderPos(final float n) {
        final ArrayList list = new ArrayList();
        final float n2 = 90;
        float n3 = 360.0f;
        float n4 = 360.0f / n2;
        final float n5 = n / 2.0f;
        int i;
        while (true) {
            i = 0;
            final int n6 = 0;
            if (n3 <= 0.0f) {
                break;
            }
            int j = n6;
            final float n7 = n3;
            final float n8 = n4;
            while (j < 2) {
                final float n9 = -n / 2.0f;
                final float n10 = j * n5 + n9;
                final double n11 = 0.06f;
                final double n12 = n7 * 3.141592653589793 / 180.0;
                final float n13 = (float)(n11 * Math.sin(n12));
                final float n14 = (float)(n11 * Math.cos(n12));
                final double n15 = (n7 - n8) * 3.141592653589793 / 180.0;
                final float n16 = (float)(n11 * Math.sin(n15));
                final float n17 = (float)(n11 * Math.cos(n15));
                ++j;
                final float n18 = n9 + j * n5;
                final float n19 = (float)(n11 * Math.sin(n15));
                final float n20 = (float)(Math.cos(n15) * n11);
                final float n21 = (float)(Math.sin(n12) * n11);
                final float n22 = (float)(n11 * Math.cos(n12));
                list.add((Object)n10);
                list.add((Object)n13);
                list.add((Object)n14);
                list.add((Object)n10);
                list.add((Object)n16);
                list.add((Object)n17);
                list.add((Object)n18);
                list.add((Object)n21);
                list.add((Object)n22);
                list.add((Object)n10);
                list.add((Object)n16);
                list.add((Object)n17);
                list.add((Object)n18);
                list.add((Object)n19);
                list.add((Object)n20);
                list.add((Object)n18);
                list.add((Object)n21);
                list.add((Object)n22);
            }
            final float n23 = n7 - n8;
            n4 = n8;
            n3 = n23;
        }
        final int size = list.size();
        final float[] array = new float[size];
        while (i < size) {
            array[i] = (float)list.get(i);
            ++i;
        }
        return array;
    }
    
    public void setColor(final String s, final float[] array, final float[] array2) {
        final int n = this.vertexCount * 4;
        final float[] array3 = new float[n];
        int i = 0;
        int n2 = 0;
        while (i < n) {
            final int n3 = n2 % 12;
            if (n3 != 0 && n3 != 1 && n3 != 2 && n3 != 3 && n3 != 4 && n3 != 5) {
                array3[i] = array2[0];
                array3[i + 1] = array2[1];
                array3[i + 2] = array2[2];
                array3[i + 3] = array2[3];
            }
            else {
                array3[i] = array[0];
                array3[i + 1] = array[1];
                array3[i + 2] = array[2];
                array3[i + 3] = array[3];
            }
            ++n2;
            i += 4;
        }
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(n * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        (this.colorBuffer = allocateDirect.asFloatBuffer()).put(array3);
        this.colorBuffer.position(0);
        (this.colorsGLBuffer = new GlBuffer()).initBuffer(4, this.colorBuffer.limit(), this.colorBuffer);
        this.glBufferMap.put((Object)s, (Object)this.colorsGLBuffer);
    }
}
