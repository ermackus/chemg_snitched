package com.otaliastudios.cameraview.video.encoding;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;

public class OutputBuffer
{
    public ByteBuffer data;
    public MediaCodec$BufferInfo info;
    public int trackIndex;
}
