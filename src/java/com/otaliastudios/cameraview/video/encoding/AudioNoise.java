package com.otaliastudios.cameraview.video.encoding;

import java.nio.ByteOrder;
import java.util.Objects;
import java.nio.ByteBuffer;
import java.util.Random;

class AudioNoise
{
    private static final int FRAMES = 1;
    private static final Random RANDOM;
    private final ByteBuffer mNoiseBuffer;
    
    static {
        RANDOM = new Random();
    }
    
    AudioNoise(final AudioConfig audioConfig) {
        Objects.requireNonNull((Object)audioConfig);
        this.mNoiseBuffer = ByteBuffer.allocateDirect(audioConfig.frameSize() * 1).order(ByteOrder.nativeOrder());
        double n = 0.0;
        final double n2 = 3.141592653589793 / (audioConfig.frameSize() / 2.0);
        while (this.mNoiseBuffer.hasRemaining()) {
            ++n;
            final short n3 = (short)(Math.sin(n * n2) * 10.0);
            this.mNoiseBuffer.put((byte)n3);
            this.mNoiseBuffer.put((byte)(n3 >> 8));
        }
        this.mNoiseBuffer.rewind();
    }
    
    void fill(final ByteBuffer byteBuffer) {
        this.mNoiseBuffer.clear();
        if (this.mNoiseBuffer.capacity() == byteBuffer.remaining()) {
            this.mNoiseBuffer.position(0);
        }
        else {
            final ByteBuffer mNoiseBuffer = this.mNoiseBuffer;
            mNoiseBuffer.position(AudioNoise.RANDOM.nextInt(mNoiseBuffer.capacity() - byteBuffer.remaining()));
        }
        final ByteBuffer mNoiseBuffer2 = this.mNoiseBuffer;
        mNoiseBuffer2.limit(mNoiseBuffer2.position() + byteBuffer.remaining());
        byteBuffer.put(this.mNoiseBuffer);
    }
}
