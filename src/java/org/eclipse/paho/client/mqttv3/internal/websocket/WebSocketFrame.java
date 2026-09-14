package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.security.SecureRandom;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.InputStream;

public class WebSocketFrame
{
    public static final int frameLengthOverhead = 6;
    private boolean closeFlag;
    private boolean fin;
    private byte opcode;
    private byte[] payload;
    
    public WebSocketFrame(final byte opcode, final boolean fin, final byte[] array) {
        this.closeFlag = false;
        this.opcode = opcode;
        this.fin = fin;
        if (array != null) {
            this.payload = array.clone();
        }
    }
    
    public WebSocketFrame(final InputStream inputStream) throws IOException {
        final int n = 0;
        this.closeFlag = false;
        this.setFinAndOpCode((byte)inputStream.read());
        final byte opcode = this.opcode;
        int n2 = 2;
        if (opcode == 2) {
            final byte b = (byte)inputStream.read();
            final boolean b2 = (b & 0x80) != 0x0;
            int n3 = (byte)(b & 0x7F);
            if (n3 == 127) {
                n2 = 8;
            }
            else if (n3 != 126) {
                n2 = 0;
            }
            int n4 = n2;
            if (n2 > 0) {
                n3 = 0;
                n4 = n2;
            }
            while (--n4 >= 0) {
                n3 |= ((byte)inputStream.read() & 0xFF) << n4 * 8;
            }
            byte[] array;
            if (b2) {
                array = new byte[4];
                inputStream.read(array, 0, 4);
            }
            else {
                array = null;
            }
            this.payload = new byte[n3];
            int read;
            for (int n5 = n3, i = 0; i != n3; i += read, n5 -= read) {
                read = inputStream.read(this.payload, i, n5);
            }
            if (b2) {
                int n6 = n;
                while (true) {
                    final byte[] payload = this.payload;
                    if (n6 >= payload.length) {
                        break;
                    }
                    payload[n6] ^= array[n6 % 4];
                    ++n6;
                }
            }
            return;
        }
        if (opcode == 8) {
            this.closeFlag = true;
            return;
        }
        final StringBuilder sb = new StringBuilder("Invalid Frame: Opcode: ");
        sb.append((int)this.opcode);
        throw new IOException(sb.toString());
    }
    
    public WebSocketFrame(byte[] array) {
        final int n = 0;
        this.closeFlag = false;
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        this.setFinAndOpCode(wrap.get());
        final byte value = wrap.get();
        final boolean b = (value & 0x80) != 0x0;
        int n2 = (byte)(value & 0x7F);
        int n3;
        if (n2 == 127) {
            n3 = 8;
        }
        else if (n2 == 126) {
            n3 = 2;
        }
        else {
            n3 = 0;
        }
        while (--n3 > 0) {
            n2 |= (wrap.get() & 0xFF) << n3 * 8;
        }
        array = null;
        if (b) {
            array = new byte[4];
            wrap.get(array, 0, 4);
        }
        wrap.get(this.payload = new byte[n2], 0, n2);
        if (b) {
            int n4 = n;
            while (true) {
                final byte[] payload = this.payload;
                if (n4 >= payload.length) {
                    break;
                }
                payload[n4] ^= array[n4 % 4];
                ++n4;
            }
        }
    }
    
    public static void appendFinAndOpCode(final ByteBuffer byteBuffer, final byte b, final boolean b2) {
        byte b3;
        if (b2) {
            b3 = (byte)128;
        }
        else {
            b3 = 0;
        }
        byteBuffer.put((byte)((b & 0xF) | b3));
    }
    
    private static void appendLength(final ByteBuffer byteBuffer, final int n, final boolean b) {
        if (n >= 0) {
            int n2;
            if (b) {
                n2 = -128;
            }
            else {
                n2 = 0;
            }
            if (n > 65535) {
                byteBuffer.put((byte)(n2 | 0x7F));
                byteBuffer.put((byte)0);
                byteBuffer.put((byte)0);
                byteBuffer.put((byte)0);
                byteBuffer.put((byte)0);
                byteBuffer.put((byte)(n >> 24 & 0xFF));
                byteBuffer.put((byte)(n >> 16 & 0xFF));
                byteBuffer.put((byte)(n >> 8 & 0xFF));
                byteBuffer.put((byte)(n & 0xFF));
            }
            else if (n >= 126) {
                byteBuffer.put((byte)(n2 | 0x7E));
                byteBuffer.put((byte)(n >> 8));
                byteBuffer.put((byte)(n & 0xFF));
            }
            else {
                byteBuffer.put((byte)(n | n2));
            }
            return;
        }
        throw new IllegalArgumentException("Length cannot be negative");
    }
    
    public static void appendLengthAndMask(final ByteBuffer byteBuffer, final int n, final byte[] array) {
        if (array != null) {
            appendLength(byteBuffer, n, true);
            byteBuffer.put(array);
        }
        else {
            appendLength(byteBuffer, n, false);
        }
    }
    
    public static byte[] generateMaskingKey() {
        final SecureRandom secureRandom = new SecureRandom();
        return new byte[] { (byte)secureRandom.nextInt(255), (byte)secureRandom.nextInt(255), (byte)secureRandom.nextInt(255), (byte)secureRandom.nextInt(255) };
    }
    
    private void setFinAndOpCode(final byte b) {
        this.fin = ((b & 0x80) != 0x0);
        this.opcode = (byte)(b & 0xF);
    }
    
    public byte[] encodeFrame() {
        final byte[] payload = this.payload;
        final int n = payload.length + 6;
        int n2;
        if (payload.length > 65535) {
            n2 = n + 8;
        }
        else {
            n2 = n;
            if (payload.length >= 126) {
                n2 = n + 2;
            }
        }
        final ByteBuffer allocate = ByteBuffer.allocate(n2);
        appendFinAndOpCode(allocate, this.opcode, this.fin);
        final byte[] generateMaskingKey = generateMaskingKey();
        appendLengthAndMask(allocate, this.payload.length, generateMaskingKey);
        int n3 = 0;
        while (true) {
            final byte[] payload2 = this.payload;
            if (n3 >= payload2.length) {
                break;
            }
            allocate.put(payload2[n3] ^= generateMaskingKey[n3 % 4]);
            ++n3;
        }
        allocate.flip();
        return allocate.array();
    }
    
    public byte getOpcode() {
        return this.opcode;
    }
    
    public byte[] getPayload() {
        return this.payload;
    }
    
    public boolean isCloseFlag() {
        return this.closeFlag;
    }
    
    public boolean isFin() {
        return this.fin;
    }
}
