package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.net.SocketTimeoutException;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.io.InputStream;

public class WebSocketReceiver implements Runnable
{
    private static final String CLASS_NAME;
    private InputStream input;
    private final Object lifecycle;
    private Logger log;
    private PipedOutputStream pipedOutputStream;
    private Thread receiverThread;
    private volatile boolean receiving;
    private boolean running;
    private boolean stopping;
    
    static {
        CLASS_NAME = WebSocketReceiver.class.getName();
    }
    
    public WebSocketReceiver(final InputStream input, final PipedInputStream pipedInputStream) throws IOException {
        this.log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", WebSocketReceiver.CLASS_NAME);
        this.running = false;
        this.stopping = false;
        this.lifecycle = new Object();
        this.receiverThread = null;
        this.input = input;
        pipedInputStream.connect(this.pipedOutputStream = new PipedOutputStream());
    }
    
    private void closeOutputStream() {
        try {
            this.pipedOutputStream.close();
        }
        catch (final IOException ex) {}
    }
    
    public boolean isReceiving() {
        return this.receiving;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void run() {
        while (this.running) {
            if (this.input == null) {
                break;
            }
            try {
                this.log.fine(WebSocketReceiver.CLASS_NAME, "run", "852");
                this.receiving = (this.input.available() > 0);
                final WebSocketFrame webSocketFrame = new WebSocketFrame(this.input);
                if (!webSocketFrame.isCloseFlag()) {
                    for (int i = 0; i < webSocketFrame.getPayload().length; ++i) {
                        this.pipedOutputStream.write((int)webSocketFrame.getPayload()[i]);
                    }
                    this.pipedOutputStream.flush();
                }
                else if (!this.stopping) {
                    throw new IOException("Server sent a WebSocket Frame with the Stop OpCode");
                }
                this.receiving = false;
                continue;
            }
            catch (final IOException ex) {
                this.stop();
                continue;
            }
            catch (final SocketTimeoutException ex2) {
                continue;
            }
            break;
        }
    }
    
    public void start(final String s) {
        this.log.fine(WebSocketReceiver.CLASS_NAME, "start", "855");
        final Object lifecycle = this.lifecycle;
        synchronized (lifecycle) {
            if (!this.running) {
                this.running = true;
                (this.receiverThread = new Thread((Runnable)this, s)).start();
            }
        }
    }
    
    public void stop() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: aload_0        
        //     3: iconst_1       
        //     4: putfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.stopping:Z
        //     7: aload_0        
        //     8: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.lifecycle:Ljava/lang/Object;
        //    11: astore_3       
        //    12: aload_3        
        //    13: dup            
        //    14: astore          4
        //    16: monitorenter   
        //    17: aload_0        
        //    18: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //    21: getstatic       org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.CLASS_NAME:Ljava/lang/String;
        //    24: ldc             "stop"
        //    26: ldc             "850"
        //    28: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    33: aload_0        
        //    34: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.running:Z
        //    37: ifeq            57
        //    40: aload_0        
        //    41: iconst_0       
        //    42: putfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.running:Z
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.receiving:Z
        //    50: aload_0        
        //    51: invokespecial   org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.closeOutputStream:()V
        //    54: goto            59
        //    57: iconst_0       
        //    58: istore_1       
        //    59: aload           4
        //    61: monitorexit    
        //    62: iload_1        
        //    63: ifeq            92
        //    66: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    69: aload_0        
        //    70: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.receiverThread:Ljava/lang/Thread;
        //    73: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    76: ifne            92
        //    79: aload_0        
        //    80: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.receiverThread:Ljava/lang/Thread;
        //    83: astore_2       
        //    84: aload_2        
        //    85: ifnull          92
        //    88: aload_2        
        //    89: invokevirtual   java/lang/Thread.join:()V
        //    92: aload_0        
        //    93: aconst_null    
        //    94: putfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.receiverThread:Ljava/lang/Thread;
        //    97: aload_0        
        //    98: getfield        org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.log:Lorg/eclipse/paho/client/mqttv3/logging/Logger;
        //   101: getstatic       org/eclipse/paho/client/mqttv3/internal/websocket/WebSocketReceiver.CLASS_NAME:Ljava/lang/String;
        //   104: ldc             "stop"
        //   106: ldc             "851"
        //   108: invokeinterface org/eclipse/paho/client/mqttv3/logging/Logger.fine:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   113: return         
        //   114: astore_2       
        //   115: aload           4
        //   117: monitorexit    
        //   118: aload_2        
        //   119: athrow         
        //   120: astore_2       
        //   121: goto            92
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  17     54     114    120    Any
        //  59     62     114    120    Any
        //  88     92     120    124    Ljava/lang/InterruptedException;
        //  115    118    114    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
