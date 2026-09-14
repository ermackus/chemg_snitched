package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.MessageCatalog;

public class MqttException extends Exception
{
    public static final short REASON_CODE_BROKER_UNAVAILABLE = 3;
    public static final short REASON_CODE_CLIENT_ALREADY_DISCONNECTED = 32101;
    public static final short REASON_CODE_CLIENT_CLOSED = 32111;
    public static final short REASON_CODE_CLIENT_CONNECTED = 32100;
    public static final short REASON_CODE_CLIENT_DISCONNECTING = 32102;
    public static final short REASON_CODE_CLIENT_DISCONNECT_PROHIBITED = 32107;
    public static final short REASON_CODE_CLIENT_EXCEPTION = 0;
    public static final short REASON_CODE_CLIENT_NOT_CONNECTED = 32104;
    public static final short REASON_CODE_CLIENT_TIMEOUT = 32000;
    public static final short REASON_CODE_CONNECTION_LOST = 32109;
    public static final short REASON_CODE_CONNECT_IN_PROGRESS = 32110;
    public static final short REASON_CODE_DISCONNECTED_BUFFER_FULL = 32203;
    public static final short REASON_CODE_FAILED_AUTHENTICATION = 4;
    public static final short REASON_CODE_INVALID_CLIENT_ID = 2;
    public static final short REASON_CODE_INVALID_MESSAGE = 32108;
    public static final short REASON_CODE_INVALID_PROTOCOL_VERSION = 1;
    public static final short REASON_CODE_MAX_INFLIGHT = 32202;
    public static final short REASON_CODE_NOT_AUTHORIZED = 5;
    public static final short REASON_CODE_NO_MESSAGE_IDS_AVAILABLE = 32001;
    public static final short REASON_CODE_SERVER_CONNECT_ERROR = 32103;
    public static final short REASON_CODE_SOCKET_FACTORY_MISMATCH = 32105;
    public static final short REASON_CODE_SSL_CONFIG_ERROR = 32106;
    public static final short REASON_CODE_SUBSCRIBE_FAILED = 128;
    public static final short REASON_CODE_TOKEN_INUSE = 32201;
    public static final short REASON_CODE_UNEXPECTED_ERROR = 6;
    public static final short REASON_CODE_WRITE_TIMEOUT = 32002;
    private static final long serialVersionUID = 300L;
    private Throwable cause;
    private int reasonCode;
    
    public MqttException(final int reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public MqttException(final int reasonCode, final Throwable cause) {
        this.reasonCode = reasonCode;
        this.cause = cause;
    }
    
    public MqttException(final Throwable cause) {
        this.reasonCode = 0;
        this.cause = cause;
    }
    
    public Throwable getCause() {
        return this.cause;
    }
    
    public String getMessage() {
        return MessageCatalog.getMessage(this.reasonCode);
    }
    
    public int getReasonCode() {
        return this.reasonCode;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf((Object)this.getMessage()));
        sb.append(" (");
        sb.append(this.reasonCode);
        sb.append(")");
        String s = sb.toString();
        if (this.cause != null) {
            final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)s));
            sb2.append(" - ");
            sb2.append(this.cause.toString());
            s = sb2.toString();
        }
        return s;
    }
}
