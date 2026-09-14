package org.eclipse.paho.client.mqttv3.internal;

import java.lang.reflect.Field;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.Iterator;
import java.net.URISyntaxException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.util.regex.Matcher;
import java.net.URI;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;
import java.util.ServiceLoader;
import java.util.regex.Pattern;

public class NetworkModuleService
{
    private static final Pattern AUTHORITY_PATTERN;
    private static final int AUTH_GROUP_HOST = 3;
    private static final int AUTH_GROUP_PORT = 5;
    private static final int AUTH_GROUP_USERINFO = 2;
    private static final ServiceLoader<NetworkModuleFactory> FACTORY_SERVICE_LOADER;
    private static Logger LOG;
    
    static {
        NetworkModuleService.LOG = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", NetworkModuleService.class.getSimpleName());
        FACTORY_SERVICE_LOADER = ServiceLoader.load((Class)NetworkModuleFactory.class, NetworkModuleService.class.getClassLoader());
        AUTHORITY_PATTERN = Pattern.compile("((.+)@)?([^:]*)(:(\\d+))?");
    }
    
    private NetworkModuleService() {
    }
    
    public static void applyRFC3986AuthorityPatch(final URI uri) {
        if (uri != null && uri.getHost() == null && uri.getAuthority() != null) {
            if (!uri.getAuthority().isEmpty()) {
                final Matcher matcher = NetworkModuleService.AUTHORITY_PATTERN.matcher((CharSequence)uri.getAuthority());
                if (matcher.find()) {
                    setURIField(uri, "userInfo", matcher.group(2));
                    setURIField(uri, "host", matcher.group(3));
                    final String group = matcher.group(5);
                    int int1;
                    if (group != null) {
                        int1 = Integer.parseInt(group);
                    }
                    else {
                        int1 = -1;
                    }
                    setURIField(uri, "port", int1);
                }
            }
        }
    }
    
    public static NetworkModule createInstance(final String s, final MqttConnectOptions mqttConnectOptions, final String s2) throws MqttException, IllegalArgumentException {
        try {
            final URI uri = new URI(s);
            applyRFC3986AuthorityPatch(uri);
            final String lowerCase = uri.getScheme().toLowerCase();
            final ServiceLoader<NetworkModuleFactory> factory_SERVICE_LOADER = NetworkModuleService.FACTORY_SERVICE_LOADER;
            synchronized (factory_SERVICE_LOADER) {
                for (final NetworkModuleFactory networkModuleFactory : NetworkModuleService.FACTORY_SERVICE_LOADER) {
                    if (networkModuleFactory.getSupportedUriSchemes().contains((Object)lowerCase)) {
                        return networkModuleFactory.createNetworkModule(uri, mqttConnectOptions, s2);
                    }
                }
                monitorexit(factory_SERVICE_LOADER);
                throw new IllegalArgumentException(uri.toString());
            }
        }
        catch (final URISyntaxException ex) {
            throw new IllegalArgumentException(s, (Throwable)ex);
        }
    }
    
    private static void setURIField(final URI uri, String declaredField, final Object o) {
        try {
            declaredField = (IllegalAccessException)URI.class.getDeclaredField((String)declaredField);
            ((Field)declaredField).setAccessible(true);
            ((Field)declaredField).set((Object)uri, o);
            return;
        }
        catch (final IllegalAccessException declaredField) {}
        catch (final IllegalArgumentException declaredField) {}
        catch (final SecurityException declaredField) {}
        catch (final NoSuchFieldException ex) {}
        NetworkModuleService.LOG.warning(NetworkModuleService.class.getName(), "setURIField", "115", new Object[] { uri.toString() }, (Throwable)declaredField);
    }
    
    public static void validateURI(final String s) throws IllegalArgumentException {
        try {
            final URI uri = new URI(s);
            final String scheme = uri.getScheme();
            if (scheme != null && !scheme.isEmpty()) {
                final String lowerCase = scheme.toLowerCase();
                final ServiceLoader<NetworkModuleFactory> factory_SERVICE_LOADER = NetworkModuleService.FACTORY_SERVICE_LOADER;
                synchronized (factory_SERVICE_LOADER) {
                    for (final NetworkModuleFactory networkModuleFactory : NetworkModuleService.FACTORY_SERVICE_LOADER) {
                        if (networkModuleFactory.getSupportedUriSchemes().contains((Object)lowerCase)) {
                            networkModuleFactory.validateURI(uri);
                            return;
                        }
                    }
                    monitorexit(factory_SERVICE_LOADER);
                    final StringBuilder sb = new StringBuilder("no NetworkModule installed for scheme \"");
                    sb.append(lowerCase);
                    sb.append("\" of URI \"");
                    sb.append(s);
                    sb.append("\"");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            final StringBuilder sb2 = new StringBuilder("missing scheme in broker URI: ");
            sb2.append(s);
            throw new IllegalArgumentException(sb2.toString());
        }
        catch (final URISyntaxException ex) {
            final StringBuilder sb3 = new StringBuilder("Can't parse string to URI \"");
            sb3.append(s);
            sb3.append("\"");
            throw new IllegalArgumentException(sb3.toString(), (Throwable)ex);
        }
    }
}
