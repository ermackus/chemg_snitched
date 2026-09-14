package com.otaliastudios.cameraview.video.encoding;

class AudioTimestamp
{
    private long mBaseTimeUs;
    private int mByteRate;
    private long mBytesSinceBaseTime;
    private long mGapUs;
    
    AudioTimestamp(final int mByteRate) {
        this.mByteRate = mByteRate;
    }
    
    static long bytesToMillis(final long n, final int n2) {
        return n * 1000L / n2;
    }
    
    static long bytesToUs(final long n, final int n2) {
        return n * 1000000L / n2;
    }
    
    int getGapCount(final int n) {
        if (this.mGapUs == 0L) {
            return 0;
        }
        return (int)(this.mGapUs / bytesToUs(n, this.mByteRate));
    }
    
    long getGapStartUs(final long n) {
        return n - this.mGapUs;
    }
    
    long increaseUs(final int n) {
        final long mBytesSinceBaseTime = n;
        final long bytesToUs = bytesToUs(mBytesSinceBaseTime, this.mByteRate);
        final long n2 = System.nanoTime() / 1000L - bytesToUs;
        if (this.mBytesSinceBaseTime == 0L) {
            this.mBaseTimeUs = n2;
        }
        final long n3 = this.mBaseTimeUs + bytesToUs(this.mBytesSinceBaseTime, this.mByteRate);
        final long mGapUs = n2 - n3;
        if (mGapUs >= bytesToUs * 2L) {
            this.mBaseTimeUs = n2;
            this.mBytesSinceBaseTime = mBytesSinceBaseTime;
            this.mGapUs = mGapUs;
            return n2;
        }
        this.mGapUs = 0L;
        this.mBytesSinceBaseTime += mBytesSinceBaseTime;
        return n3;
    }
}
