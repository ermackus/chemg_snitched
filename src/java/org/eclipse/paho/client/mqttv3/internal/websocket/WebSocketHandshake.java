package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.UUID;
import java.security.MessageDigest;
import java.util.Iterator;
import java.net.URISyntaxException;
import java.io.PrintWriter;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Properties;

public class WebSocketHandshake
{
    private static final String ACCEPT_SALT = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final String EMPTY = "";
    private static final String HTTP_HEADER_CONNECTION = "connection";
    private static final String HTTP_HEADER_CONNECTION_VALUE = "upgrade";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_ACCEPT = "sec-websocket-accept";
    private static final String HTTP_HEADER_SEC_WEBSOCKET_PROTOCOL = "sec-websocket-protocol";
    private static final String HTTP_HEADER_UPGRADE = "upgrade";
    private static final String HTTP_HEADER_UPGRADE_WEBSOCKET = "websocket";
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String SHA1_PROTOCOL = "SHA1";
    Properties customWebSocketHeaders;
    String host;
    InputStream input;
    OutputStream output;
    int port;
    String uri;
    
    public WebSocketHandshake(final InputStream input, final OutputStream output, final String uri, final String host, final int port, final Properties customWebSocketHeaders) {
        this.input = input;
        this.output = output;
        this.uri = uri;
        this.host = host;
        this.port = port;
        this.customWebSocketHeaders = customWebSocketHeaders;
    }
    
    private Map<String, String> getHeaders(final ArrayList<String> list) {
        final HashMap hashMap = new HashMap();
        for (int i = 1; i < list.size(); ++i) {
            final String[] split = ((String)list.get(i)).split(":");
            ((Map)hashMap).put((Object)split[0].toLowerCase(), (Object)split[1]);
        }
        return (Map<String, String>)hashMap;
    }
    
    private void receiveHandshakeResponse(final String s) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(this.input));
        final ArrayList list = new ArrayList();
        String s2 = bufferedReader.readLine();
        if (s2 == null) {
            throw new IOException("WebSocket Response header: Invalid response from Server, It may not support WebSockets.");
        }
        while (!s2.equals((Object)"")) {
            list.add((Object)s2);
            s2 = bufferedReader.readLine();
        }
        final Map<String, String> headers = this.getHeaders((ArrayList<String>)list);
        final String s3 = (String)headers.get((Object)"connection");
        if (s3 == null || s3.equalsIgnoreCase("upgrade")) {
            throw new IOException("WebSocket Response header: Incorrect connection header");
        }
        final String s4 = (String)headers.get((Object)"upgrade");
        if (s4 == null || !s4.toLowerCase().contains((CharSequence)"websocket")) {
            throw new IOException("WebSocket Response header: Incorrect upgrade.");
        }
        if (headers.get((Object)"sec-websocket-protocol") == null) {
            throw new IOException("WebSocket Response header: empty sec-websocket-protocol");
        }
        if (headers.containsKey((Object)"sec-websocket-accept")) {
            try {
                this.verifyWebSocketKey(s, (String)headers.get((Object)"sec-websocket-accept"));
                return;
            }
            catch (final NoSuchAlgorithmException ex) {}
            catch (final HandshakeFailedException ex2) {
                throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
            }
            final NoSuchAlgorithmException ex;
            throw new IOException(ex.getMessage());
        }
        throw new IOException("WebSocket Response header: Missing Sec-WebSocket-Accept");
    }
    
    private void sendHandshakeRequest(String property) throws IOException {
        final String s = "/mqtt";
        try {
            final URI uri = new URI(this.uri);
            String s2 = s;
            if (uri.getRawPath() != null) {
                s2 = s;
                if (!uri.getRawPath().isEmpty()) {
                    final String s3 = s2 = uri.getRawPath();
                    if (uri.getRawQuery() != null) {
                        s2 = s3;
                        if (!uri.getRawQuery().isEmpty()) {
                            final StringBuilder sb = new StringBuilder(String.valueOf((Object)s3));
                            sb.append("?");
                            sb.append(uri.getRawQuery());
                            s2 = sb.toString();
                        }
                    }
                }
            }
            final PrintWriter printWriter = new PrintWriter(this.output);
            final StringBuilder sb2 = new StringBuilder("GET ");
            sb2.append(s2);
            sb2.append(" HTTP/1.1");
            sb2.append("\r\n");
            printWriter.print(sb2.toString());
            if (this.port != 80) {
                final StringBuilder sb3 = new StringBuilder("Host: ");
                sb3.append(this.host);
                sb3.append(":");
                sb3.append(this.port);
                sb3.append("\r\n");
                printWriter.print(sb3.toString());
            }
            else {
                final StringBuilder sb4 = new StringBuilder("Host: ");
                sb4.append(this.host);
                sb4.append("\r\n");
                printWriter.print(sb4.toString());
            }
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            final StringBuilder sb5 = new StringBuilder("Sec-WebSocket-Key: ");
            sb5.append(property);
            sb5.append("\r\n");
            printWriter.print(sb5.toString());
            printWriter.print("Sec-WebSocket-Protocol: mqtt\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            if (this.customWebSocketHeaders != null) {
                for (final String s4 : this.customWebSocketHeaders.keySet()) {
                    property = this.customWebSocketHeaders.getProperty(s4);
                    final StringBuilder sb6 = new StringBuilder(String.valueOf((Object)s4));
                    sb6.append(": ");
                    sb6.append(property);
                    sb6.append("\r\n");
                    printWriter.print(sb6.toString());
                }
            }
            final String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                final StringBuilder sb7 = new StringBuilder("Authorization: Basic ");
                sb7.append(Base64.encode(userInfo));
                sb7.append("\r\n");
                printWriter.print(sb7.toString());
            }
            printWriter.print("\r\n");
            printWriter.flush();
        }
        catch (final URISyntaxException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    private byte[] sha1(final String s) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA1").digest(s.getBytes());
    }
    
    private void verifyWebSocketKey(final String s, final String s2) throws NoSuchAlgorithmException, HandshakeFailedException {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
        sb.append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        if (Base64.encodeBytes(this.sha1(sb.toString())).trim().equals((Object)s2.trim())) {
            return;
        }
        throw new HandshakeFailedException();
    }
    
    public void execute() throws IOException {
        final byte[] array = new byte[16];
        System.arraycopy((Object)UUID.randomUUID().toString().getBytes(), 0, (Object)array, 0, 16);
        final String encodeBytes = Base64.encodeBytes(array);
        this.sendHandshakeRequest(encodeBytes);
        this.receiveHandshakeResponse(encodeBytes);
    }
}
