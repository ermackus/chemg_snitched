package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.util.Strings;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;

public class MqttTopic
{
    private static final int MAX_TOPIC_LEN = 65535;
    private static final int MIN_TOPIC_LEN = 1;
    public static final String MULTI_LEVEL_WILDCARD = "#";
    public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
    private static final char NUL = '\0';
    public static final String SINGLE_LEVEL_WILDCARD = "+";
    public static final String TOPIC_LEVEL_SEPARATOR = "/";
    public static final String TOPIC_WILDCARDS = "#+";
    private ClientComms comms;
    private String name;
    
    public MqttTopic(final String name, final ClientComms comms) {
        this.comms = comms;
        this.name = name;
    }
    
    private MqttPublish createPublish(final MqttMessage mqttMessage) {
        return new MqttPublish(this.getName(), mqttMessage);
    }
    
    public static boolean isMatched(final String s, final String s2) throws IllegalArgumentException {
        final int length = s2.length();
        final int length2 = s.length();
        validate(s, true);
        validate(s2, false);
        if (s.equals((Object)s2)) {
            return true;
        }
        int n = 0;
        int n2 = 0;
        int n3;
        int n4;
        while (true) {
            n3 = n;
            n4 = n2;
            if (n >= length2) {
                break;
            }
            if (n2 >= length) {
                n3 = n;
                n4 = n2;
                break;
            }
            if (s.charAt(n) == '#') {
                n4 = length;
                n3 = length2;
                break;
            }
            if (s2.charAt(n2) == '/' && s.charAt(n) != '/') {
                n3 = n;
                n4 = n2;
                break;
            }
            if (s.charAt(n) != '+' && s.charAt(n) != '#' && s.charAt(n) != s2.charAt(n2)) {
                n3 = n;
                n4 = n2;
                break;
            }
            int n5 = n2;
            if (s.charAt(n) == '+') {
                while (true) {
                    final int n6 = n2 + 1;
                    n5 = n2;
                    if (n6 >= length) {
                        break;
                    }
                    if (s2.charAt(n6) == '/') {
                        n5 = n2;
                        break;
                    }
                    ++n2;
                }
            }
            ++n;
            n2 = n5 + 1;
        }
        if (n4 == length && n3 == length2) {
            return true;
        }
        if (s.length() - n3 > 0 && n4 == length) {
            if (s2.charAt(n4 - 1) == '/' && s.charAt(n3) == '#') {
                return true;
            }
            if (s.length() - n3 > 1 && s.substring(n3, n3 + 2).equals((Object)"/#")) {
                return true;
            }
        }
        return false;
    }
    
    public static void validate(final String s, final boolean b) throws IllegalArgumentException {
        try {
            final int length = s.getBytes("UTF-8").length;
            if (length < 1 || length > 65535) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", new Object[] { 1, 65535 }));
            }
            if (b) {
                if (Strings.equalsAny((CharSequence)s, (CharSequence[])new String[] { "#", "+" })) {
                    return;
                }
                if (Strings.countMatches((CharSequence)s, (CharSequence)"#") <= 1 && (!s.contains((CharSequence)"#") || s.endsWith("/#"))) {
                    validateSingleLevelWildcard(s);
                    return;
                }
                final StringBuilder sb = new StringBuilder("Invalid usage of multi-level wildcard in topic string: ");
                sb.append(s);
                throw new IllegalArgumentException(sb.toString());
            }
            else {
                if (!Strings.containsAny((CharSequence)s, (CharSequence)"#+")) {
                    return;
                }
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        }
        catch (final UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
    
    private static void validateSingleLevelWildcard(final String s) {
        final char char1 = "+".charAt(0);
        final char char2 = "/".charAt(0);
        final char[] charArray = s.toCharArray();
        int n2;
        for (int length = charArray.length, i = 0; i < length; i = n2) {
            final int n = i - 1;
            char c;
            if (n >= 0) {
                c = charArray[n];
            }
            else {
                c = '\0';
            }
            n2 = i + 1;
            char c2;
            if (n2 < length) {
                c2 = charArray[n2];
            }
            else {
                c2 = '\0';
            }
            if (charArray[i] == char1) {
                if (c == char2 || c == '\0') {
                    if (c2 == char2) {
                        continue;
                    }
                    if (c2 == '\0') {
                        continue;
                    }
                }
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", new Object[] { s }));
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public MqttDeliveryToken publish(final MqttMessage message) throws MqttException, MqttPersistenceException {
        final MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(this.comms.getClient().getClientId());
        mqttDeliveryToken.setMessage(message);
        this.comms.sendNoWait(this.createPublish(message), mqttDeliveryToken);
        mqttDeliveryToken.internalTok.waitUntilSent();
        return mqttDeliveryToken;
    }
    
    public MqttDeliveryToken publish(final byte[] array, final int qos, final boolean retained) throws MqttException, MqttPersistenceException {
        final MqttMessage mqttMessage = new MqttMessage(array);
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        return this.publish(mqttMessage);
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
