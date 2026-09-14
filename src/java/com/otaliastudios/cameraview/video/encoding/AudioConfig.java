package com.otaliastudios.cameraview.video.encoding;

public class AudioConfig
{
    public int bitRate;
    final int byteRatePerChannel;
    public int channels;
    public String encoder;
    final int encoding;
    public String mimeType;
    final int sampleSizePerChannel;
    public int samplingFrequency;
    
    public AudioConfig() {
        this.channels = 1;
        this.mimeType = "audio/mp4a-latm";
        this.samplingFrequency = 44100;
        this.encoding = 2;
        this.sampleSizePerChannel = 2;
        this.byteRatePerChannel = 44100 * 2;
    }
    
    int audioFormatChannels() {
        final int channels = this.channels;
        if (channels == 1) {
            return 16;
        }
        if (channels == 2) {
            return 12;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Invalid number of channels: ");
        sb.append(this.channels);
        throw new RuntimeException(sb.toString());
    }
    
    int audioRecordBufferFrames() {
        return 50;
    }
    
    int bitRate() {
        return this.byteRate() * 8;
    }
    
    int bufferPoolMaxSize() {
        return 500;
    }
    
    int byteRate() {
        return this.byteRatePerChannel * this.channels;
    }
    
    AudioConfig copy() {
        final AudioConfig audioConfig = new AudioConfig();
        audioConfig.bitRate = this.bitRate;
        audioConfig.channels = this.channels;
        audioConfig.encoder = this.encoder;
        audioConfig.mimeType = this.mimeType;
        audioConfig.samplingFrequency = this.samplingFrequency;
        return audioConfig;
    }
    
    int frameSize() {
        return this.channels * 1024;
    }
}
