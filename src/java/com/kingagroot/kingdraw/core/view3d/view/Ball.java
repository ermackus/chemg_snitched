package com.kingagroot.kingdraw.core.view3d.view;

import java.util.ArrayList;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import com.kingagroot.kingdraw.core.view3d.opengl.GlBuffer;
import java.nio.FloatBuffer;

public class Ball
{
    private static float[] ballCoords;
    private FloatBuffer colorBuffer;
    private GlBuffer colorsGLBuffer;
    private FloatBuffer fbNormal;
    public Map<String, GlBuffer> glBufferMap;
    public GlBuffer normalGLBuffer;
    public GlBuffer positionGLBuffer;
    private FloatBuffer vertexBuffer;
    public final int vertexCount;
    
    public Ball() {
        this.glBufferMap = (Map<String, GlBuffer>)new HashMap();
        final float[] array = Ball.ballCoords = this.createBallPos(1.0f);
        this.vertexCount = array.length / 3;
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(array.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        (this.vertexBuffer = allocateDirect.asFloatBuffer()).put(Ball.ballCoords);
        this.vertexBuffer.position(0);
        final ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(Ball.ballCoords.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        (this.fbNormal = allocateDirect2.asFloatBuffer()).put(Ball.ballCoords);
        this.fbNormal.position(0);
        (this.positionGLBuffer = new GlBuffer()).initBuffer(4, this.vertexBuffer.limit(), this.vertexBuffer);
        (this.normalGLBuffer = new GlBuffer()).initBuffer(4, this.fbNormal.limit(), this.fbNormal);
    }
    
    public float[] createBallPos(final float n) {
        final ArrayList list = new ArrayList();
        int n2 = -90;
        int n3;
        while (true) {
            n3 = 0;
            final int n4 = 0;
            if (n2 >= 90) {
                break;
            }
            int i = n4;
            while (i <= 360) {
                final double n5 = n;
                final double n6 = n2;
                final double cos = Math.cos(Math.toRadians(n6));
                final double n7 = i;
                final float n8 = (float)(cos * n5 * Math.cos(Math.toRadians(n7)));
                final float n9 = (float)(Math.cos(Math.toRadians(n6)) * n5 * Math.sin(Math.toRadians(n7)));
                final float n10 = (float)(Math.sin(Math.toRadians(n6)) * n5);
                final double cos2 = Math.cos(Math.toRadians(n6));
                i += 10;
                final double n11 = i;
                final float n12 = (float)(cos2 * n5 * Math.cos(Math.toRadians(n11)));
                final float n13 = (float)(Math.cos(Math.toRadians(n6)) * n5 * Math.sin(Math.toRadians(n11)));
                final float n14 = (float)(Math.sin(Math.toRadians(n6)) * n5);
                final double n15 = n2 + 10;
                final float n16 = (float)(Math.cos(Math.toRadians(n15)) * n5 * Math.cos(Math.toRadians(n11)));
                final float n17 = (float)(Math.sin(Math.toRadians(n11)) * (Math.cos(Math.toRadians(n15)) * n5));
                final float n18 = (float)(n5 * Math.sin(Math.toRadians(n15)));
                final float n19 = (float)(Math.cos(Math.toRadians(n15)) * n5 * Math.cos(Math.toRadians(n7)));
                final float n20 = (float)(Math.sin(Math.toRadians(n7)) * (Math.cos(Math.toRadians(n15)) * n5));
                final float n21 = (float)(n5 * Math.sin(Math.toRadians(n15)));
                list.add((Object)n12);
                list.add((Object)n13);
                list.add((Object)n14);
                list.add((Object)n19);
                list.add((Object)n20);
                list.add((Object)n21);
                list.add((Object)n8);
                list.add((Object)n9);
                list.add((Object)n10);
                list.add((Object)n12);
                list.add((Object)n13);
                list.add((Object)n14);
                list.add((Object)n16);
                list.add((Object)n17);
                list.add((Object)n18);
                list.add((Object)n19);
                list.add((Object)n20);
                list.add((Object)n21);
            }
            n2 += 10;
        }
        final int size = list.size();
        final float[] array = new float[size];
        for (int j = n3; j < size; ++j) {
            array[j] = (float)list.get(j);
        }
        return array;
    }
    
    public void setColor(final float[] array, final String s) {
        final int n = this.vertexCount * 4;
        final float[] array2 = new float[n];
        for (int i = 0; i < this.vertexCount * 4; i += 4) {
            array2[i] = array[0];
            array2[i + 1] = array[1];
            array2[i + 2] = array[2];
            array2[i + 3] = array[3];
        }
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(n * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        (this.colorBuffer = allocateDirect.asFloatBuffer()).put(array2);
        this.colorBuffer.position(0);
        (this.colorsGLBuffer = new GlBuffer()).initBuffer(4, this.colorBuffer.limit(), this.colorBuffer);
        this.glBufferMap.put((Object)s, (Object)this.colorsGLBuffer);
    }
}
