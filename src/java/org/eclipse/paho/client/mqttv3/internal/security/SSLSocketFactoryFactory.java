package org.eclipse.paho.client.mqttv3.internal.security;

import java.util.Map;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocketFactory;
import java.util.Vector;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.UnrecoverableKeyException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import java.util.Properties;
import java.util.Hashtable;

public class SSLSocketFactoryFactory
{
    public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
    public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
    public static final String KEYSTORE = "com.ibm.ssl.keyStore";
    public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
    public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
    public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
    public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
    public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
    public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
    public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
    public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
    public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
    public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
    public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
    public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
    public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
    public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
    public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
    public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
    public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
    private static final byte[] key;
    private static final String[] propertyKeys;
    private static final String xorTag = "{xor}";
    private Hashtable configs;
    private Properties defaultProperties;
    private Logger logger;
    
    static {
        propertyKeys = new String[] { "com.ibm.ssl.protocol", "com.ibm.ssl.contextProvider", "com.ibm.ssl.keyStore", "com.ibm.ssl.keyStorePassword", "com.ibm.ssl.keyStoreType", "com.ibm.ssl.keyStoreProvider", "com.ibm.ssl.keyManager", "com.ibm.ssl.trustStore", "com.ibm.ssl.trustStorePassword", "com.ibm.ssl.trustStoreType", "com.ibm.ssl.trustStoreProvider", "com.ibm.ssl.trustManager", "com.ibm.ssl.enabledCipherSuites", "com.ibm.ssl.clientAuthentication" };
        key = new byte[] { -99, -89, -39, -128, 5, -72, -119, -100 };
    }
    
    public SSLSocketFactoryFactory() {
        this.logger = null;
        this.configs = new Hashtable();
    }
    
    public SSLSocketFactoryFactory(final Logger logger) {
        this();
        this.logger = logger;
    }
    
    private void checkPropertyKeys(final Properties properties) throws IllegalArgumentException {
        for (final String s : properties.keySet()) {
            if (this.keyValid(s)) {
                continue;
            }
            final StringBuilder sb = new StringBuilder(String.valueOf((Object)s));
            sb.append(" is not a valid IBM SSL property key.");
            throw new IllegalArgumentException(sb.toString());
        }
    }
    
    private void convertPassword(final Properties properties) {
        final String property = properties.getProperty("com.ibm.ssl.keyStorePassword");
        if (property != null && !property.startsWith("{xor}")) {
            properties.put((Object)"com.ibm.ssl.keyStorePassword", (Object)obfuscate(property.toCharArray()));
        }
        final String property2 = properties.getProperty("com.ibm.ssl.trustStorePassword");
        if (property2 != null && !property2.startsWith("{xor}")) {
            properties.put((Object)"com.ibm.ssl.trustStorePassword", (Object)obfuscate(property2.toCharArray()));
        }
    }
    
    public static char[] deObfuscate(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final byte[] decode = SimpleBase64Encoder.decode(s.substring(5));
            for (int i = 0; i < decode.length; ++i) {
                final byte b = decode[i];
                final byte[] key = SSLSocketFactoryFactory.key;
                decode[i] = (byte)((b ^ key[i % key.length]) & 0xFF);
            }
            return toChar(decode);
        }
        catch (final Exception ex) {
            return null;
        }
    }
    
    private String getProperty(String s, final String s2, final String s3) {
        s = this.getPropertyFromConfig(s, s2);
        if (s != null) {
            return s;
        }
        if (s3 != null) {
            s = System.getProperty(s3);
        }
        return s;
    }
    
    private String getPropertyFromConfig(String s, String property) {
        final String s2 = null;
        Properties properties;
        if (s != null) {
            properties = (Properties)this.configs.get((Object)s);
        }
        else {
            properties = null;
        }
        s = s2;
        if (properties != null) {
            final String property2 = properties.getProperty(property);
            if ((s = property2) != null) {
                return property2;
            }
        }
        final Properties defaultProperties = this.defaultProperties;
        if (defaultProperties != null) {
            property = defaultProperties.getProperty(property);
            if ((s = property) != null) {}
        }
        return s;
    }
    
    private SSLContext getSSLContext(String s) throws MqttSecurityException {
        String s2 = s;
        String sslProtocol;
        if ((sslProtocol = this.getSSLProtocol(s)) == null) {
            sslProtocol = "TLS";
        }
        final Logger logger = this.logger;
        if (logger != null) {
            String s3;
            if (s2 != null) {
                s3 = s2;
            }
            else {
                s3 = "null (broker defaults)";
            }
            logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12000", new Object[] { s3, sslProtocol });
        }
        final String jsseProvider = this.getJSSEProvider(s);
        Label_0092: {
            if (jsseProvider != null) {
                break Label_0092;
            }
            try {
                SSLContext sslContext = SSLContext.getInstance(sslProtocol);
                while (true) {
                    if (this.logger != null) {
                        final Logger logger2 = this.logger;
                        String s4;
                        if (s2 != null) {
                            s4 = s2;
                        }
                        else {
                            s4 = "null (broker defaults)";
                        }
                        logger2.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12001", new Object[] { s4, sslContext.getProvider().getName() });
                    }
                    String s5;
                    if ((s5 = this.getProperty(s2, "com.ibm.ssl.keyStore", null)) == null) {
                        s5 = this.getProperty(s2, "com.ibm.ssl.keyStore", "javax.net.ssl.keyStore");
                    }
                    final Logger logger3 = this.logger;
                    final String s6 = "null";
                    if (logger3 != null) {
                        final Logger logger4 = this.logger;
                        String s7;
                        if (s2 != null) {
                            s7 = s2;
                        }
                        else {
                            s7 = "null (broker defaults)";
                        }
                        String s8;
                        if (s5 != null) {
                            s8 = s5;
                        }
                        else {
                            s8 = "null";
                        }
                        logger4.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12004", new Object[] { s7, s8 });
                    }
                    final char[] keyStorePassword = this.getKeyStorePassword(s);
                    if (this.logger != null) {
                        final Logger logger5 = this.logger;
                        String s9;
                        if (s2 != null) {
                            s9 = s2;
                        }
                        else {
                            s9 = "null (broker defaults)";
                        }
                        String obfuscate;
                        if (keyStorePassword != null) {
                            obfuscate = obfuscate(keyStorePassword);
                        }
                        else {
                            obfuscate = "null";
                        }
                        logger5.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12005", new Object[] { s9, obfuscate });
                    }
                    String s10;
                    if ((s10 = this.getKeyStoreType(s)) == null) {
                        s10 = KeyStore.getDefaultType();
                    }
                    if (this.logger != null) {
                        final Logger logger6 = this.logger;
                        String s11;
                        if (s2 != null) {
                            s11 = s2;
                        }
                        else {
                            s11 = "null (broker defaults)";
                        }
                        String s12;
                        if (s10 != null) {
                            s12 = s10;
                        }
                        else {
                            s12 = "null";
                        }
                        logger6.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12006", new Object[] { s11, s12 });
                    }
                    String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
                    final String keyStoreProvider = this.getKeyStoreProvider(s);
                    final String keyManager = this.getKeyManager(s);
                    if (keyManager != null) {
                        defaultAlgorithm = keyManager;
                    }
                    Label_0729: {
                        if (s5 == null || s10 == null || defaultAlgorithm == null) {
                            break Label_0729;
                        }
                        Label_0732: {
                            try {
                                final KeyStore instance = KeyStore.getInstance(s10);
                                instance.load((InputStream)new FileInputStream(s5), keyStorePassword);
                                KeyManagerFactory keyManagerFactory;
                                if (keyStoreProvider != null) {
                                    keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm, keyStoreProvider);
                                }
                                else {
                                    keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm);
                                }
                                if (this.logger != null) {
                                    final Logger logger7 = this.logger;
                                    String s13;
                                    if (s2 != null) {
                                        s13 = s2;
                                    }
                                    else {
                                        s13 = "null (broker defaults)";
                                    }
                                    if (defaultAlgorithm == null) {
                                        defaultAlgorithm = "null";
                                    }
                                    logger7.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12010", new Object[] { s13, defaultAlgorithm });
                                    final Logger logger8 = this.logger;
                                    String s14;
                                    if (s2 != null) {
                                        s14 = s2;
                                    }
                                    else {
                                        s14 = "null (broker defaults)";
                                    }
                                    logger8.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12009", new Object[] { s14, keyManagerFactory.getProvider().getName() });
                                }
                                keyManagerFactory.init(instance, keyStorePassword);
                                final KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
                                break Label_0732;
                            }
                            catch (final UnrecoverableKeyException ex) {
                                throw new MqttSecurityException((Throwable)ex);
                            }
                            catch (final IOException ex2) {
                                final MqttSecurityException ex3 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                final MqttSecurityException ex5;
                                final MqttSecurityException ex4 = ex5 = ex3;
                                final Throwable t = (Throwable)ex2;
                                new MqttSecurityException(t);
                                throw ex4;
                            }
                            catch (final FileNotFoundException ex7) {
                                final MqttSecurityException ex8 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                final MqttSecurityException ex10;
                                final MqttSecurityException ex9 = ex10 = ex8;
                                final Throwable t2 = (Throwable)ex7;
                                new MqttSecurityException(t2);
                                throw ex9;
                            }
                            catch (final CertificateException ex12) {
                                final MqttSecurityException ex13 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                final MqttSecurityException ex15;
                                final MqttSecurityException ex14 = ex15 = ex13;
                                final Throwable t3 = (Throwable)ex12;
                                new MqttSecurityException(t3);
                                throw ex14;
                            }
                            catch (final KeyStoreException ex17) {
                                throw new MqttSecurityException((Throwable)ex17);
                            }
                            try {
                                final MqttSecurityException ex3 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                final MqttSecurityException ex5;
                                final MqttSecurityException ex4 = ex5 = ex3;
                                final IOException ex2;
                                final Throwable t = (Throwable)ex2;
                                new MqttSecurityException(t);
                                throw ex4;
                                try {
                                    final MqttSecurityException ex8 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                    final MqttSecurityException ex10;
                                    final MqttSecurityException ex9 = ex10 = ex8;
                                    final FileNotFoundException ex7;
                                    final Throwable t2 = (Throwable)ex7;
                                    new MqttSecurityException(t2);
                                    throw ex9;
                                    try {
                                        final MqttSecurityException ex13 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                        final MqttSecurityException ex15;
                                        final MqttSecurityException ex14 = ex15 = ex13;
                                        final CertificateException ex12;
                                        final Throwable t3 = (Throwable)ex12;
                                        new MqttSecurityException(t3);
                                        throw ex14;
                                        try {
                                            final KeyManager[] keyManagers = null;
                                            final String trustStore = this.getTrustStore(s);
                                            if (this.logger != null) {
                                                final Logger logger9 = this.logger;
                                                String s15;
                                                if (s2 != null) {
                                                    s15 = s2;
                                                }
                                                else {
                                                    s15 = "null (broker defaults)";
                                                }
                                                String s16;
                                                if (trustStore != null) {
                                                    s16 = trustStore;
                                                }
                                                else {
                                                    s16 = "null";
                                                }
                                                logger9.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12011", new Object[] { s15, s16 });
                                            }
                                            final char[] trustStorePassword = this.getTrustStorePassword(s);
                                            if (this.logger != null) {
                                                final Logger logger10 = this.logger;
                                                String s17;
                                                if (s2 != null) {
                                                    s17 = s2;
                                                }
                                                else {
                                                    s17 = "null (broker defaults)";
                                                }
                                                String obfuscate2;
                                                if (trustStorePassword != null) {
                                                    obfuscate2 = obfuscate(trustStorePassword);
                                                }
                                                else {
                                                    obfuscate2 = "null";
                                                }
                                                logger10.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12012", new Object[] { s17, obfuscate2 });
                                            }
                                            String s18;
                                            if ((s18 = this.getTrustStoreType(s)) == null) {
                                                s18 = KeyStore.getDefaultType();
                                            }
                                            if (this.logger != null) {
                                                final Logger logger11 = this.logger;
                                                String s19;
                                                if (s2 != null) {
                                                    s19 = s2;
                                                }
                                                else {
                                                    s19 = "null (broker defaults)";
                                                }
                                                String s20;
                                                if (s18 != null) {
                                                    s20 = s18;
                                                }
                                                else {
                                                    s20 = "null";
                                                }
                                                logger11.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12013", new Object[] { s19, s20 });
                                            }
                                            final String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
                                            final String trustStoreProvider = this.getTrustStoreProvider(s);
                                            final String trustManager = this.getTrustManager(s);
                                            s = defaultAlgorithm2;
                                            if (trustManager != null) {
                                                s = trustManager;
                                            }
                                            Label_1237: {
                                                if (trustStore == null || s18 == null || s == null) {
                                                    break Label_1237;
                                                }
                                                Label_1239: {
                                                    try {
                                                        final KeyStore instance2 = KeyStore.getInstance(s18);
                                                        instance2.load((InputStream)new FileInputStream(trustStore), trustStorePassword);
                                                        TrustManagerFactory trustManagerFactory;
                                                        if (trustStoreProvider != null) {
                                                            trustManagerFactory = TrustManagerFactory.getInstance(s, trustStoreProvider);
                                                        }
                                                        else {
                                                            trustManagerFactory = TrustManagerFactory.getInstance(s);
                                                        }
                                                        if (this.logger != null) {
                                                            final Logger logger12 = this.logger;
                                                            String s21;
                                                            if (s2 != null) {
                                                                s21 = s2;
                                                            }
                                                            else {
                                                                s21 = "null (broker defaults)";
                                                            }
                                                            String s22 = s6;
                                                            if (s != null) {
                                                                s22 = s;
                                                            }
                                                            logger12.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12017", new Object[] { s21, s22 });
                                                            final Logger logger13 = this.logger;
                                                            if (s2 == null) {
                                                                s2 = "null (broker defaults)";
                                                            }
                                                            logger13.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12016", new Object[] { s2, trustManagerFactory.getProvider().getName() });
                                                        }
                                                        trustManagerFactory.init(instance2);
                                                        final TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                                                        break Label_1239;
                                                    }
                                                    catch (final IOException ex18) {
                                                        throw new MqttSecurityException((Throwable)ex18);
                                                    }
                                                    catch (final FileNotFoundException ex19) {
                                                        final MqttSecurityException ex20 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                                        final MqttSecurityException ex22;
                                                        final MqttSecurityException ex21 = ex22 = ex20;
                                                        final Throwable t4 = (Throwable)ex19;
                                                        new MqttSecurityException(t4);
                                                        throw ex21;
                                                    }
                                                    catch (final CertificateException ex24) {
                                                        final MqttSecurityException ex25 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                                        final MqttSecurityException ex27;
                                                        final MqttSecurityException ex26 = ex27 = ex25;
                                                        final Throwable t5 = (Throwable)ex24;
                                                        new MqttSecurityException(t5);
                                                        throw ex26;
                                                    }
                                                    catch (final KeyStoreException ex29) {
                                                        throw new MqttSecurityException((Throwable)ex29);
                                                    }
                                                    try {
                                                        final MqttSecurityException ex20 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                                        final MqttSecurityException ex22;
                                                        final MqttSecurityException ex21 = ex22 = ex20;
                                                        final FileNotFoundException ex19;
                                                        final Throwable t4 = (Throwable)ex19;
                                                        new MqttSecurityException(t4);
                                                        throw ex21;
                                                        try {
                                                            final MqttSecurityException ex25 = new(org.eclipse.paho.client.mqttv3.MqttSecurityException.class)();
                                                            final MqttSecurityException ex27;
                                                            final MqttSecurityException ex26 = ex27 = ex25;
                                                            final CertificateException ex24;
                                                            final Throwable t5 = (Throwable)ex24;
                                                            new MqttSecurityException(t5);
                                                            throw ex26;
                                                            try {
                                                                final TrustManager[] trustManagers = null;
                                                                sslContext.init(keyManagers, trustManagers, (SecureRandom)null);
                                                                return sslContext;
                                                            }
                                                            catch (final KeyManagementException ex30) {
                                                                throw new MqttSecurityException((Throwable)ex30);
                                                            }
                                                            catch (final NoSuchProviderException ex31) {
                                                                throw new MqttSecurityException((Throwable)ex31);
                                                            }
                                                            catch (final NoSuchAlgorithmException ex32) {
                                                                throw new MqttSecurityException((Throwable)ex32);
                                                            }
                                                        }
                                                        catch (final KeyManagementException ex33) {}
                                                        catch (final NoSuchProviderException ex34) {}
                                                        catch (final NoSuchAlgorithmException ex35) {}
                                                    }
                                                    catch (final KeyManagementException ex36) {}
                                                    catch (final NoSuchProviderException ex37) {}
                                                    catch (final NoSuchAlgorithmException ex38) {}
                                                }
                                            }
                                        }
                                        catch (final KeyManagementException ex39) {}
                                        catch (final NoSuchProviderException ex40) {}
                                        catch (final NoSuchAlgorithmException ex41) {}
                                    }
                                    catch (final KeyManagementException ex42) {}
                                    catch (final NoSuchProviderException ex43) {}
                                    catch (final NoSuchAlgorithmException ex44) {}
                                }
                                catch (final KeyManagementException ex45) {}
                                catch (final NoSuchProviderException ex46) {}
                                catch (final NoSuchAlgorithmException ex47) {}
                            }
                            catch (final KeyManagementException ex48) {}
                            catch (final NoSuchProviderException ex49) {}
                            catch (final NoSuchAlgorithmException ex50) {}
                        }
                    }
                    sslContext = SSLContext.getInstance(sslProtocol, jsseProvider);
                    continue;
                }
            }
            catch (final KeyManagementException ex51) {}
            catch (final NoSuchProviderException ex52) {}
            catch (final NoSuchAlgorithmException ex53) {}
        }
    }
    
    public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
        try {
            Class.forName("javax.net.ssl.SSLServerSocketFactory");
            return true;
        }
        catch (final ClassNotFoundException ex) {
            return false;
        }
    }
    
    private boolean keyValid(final String s) {
        int n = 0;
        while (true) {
            final String[] propertyKeys = SSLSocketFactoryFactory.propertyKeys;
            if (n >= propertyKeys.length) {
                break;
            }
            if (propertyKeys[n].equals((Object)s)) {
                break;
            }
            ++n;
        }
        return n < SSLSocketFactoryFactory.propertyKeys.length;
    }
    
    public static String obfuscate(final char[] array) {
        if (array == null) {
            return null;
        }
        final byte[] byte1 = toByte(array);
        for (int i = 0; i < byte1.length; ++i) {
            final byte b = byte1[i];
            final byte[] key = SSLSocketFactoryFactory.key;
            byte1[i] = (byte)((b ^ key[i % key.length]) & 0xFF);
        }
        final StringBuilder sb = new StringBuilder("{xor}");
        sb.append(new String(SimpleBase64Encoder.encode(byte1)));
        return sb.toString();
    }
    
    public static String packCipherSuites(final String[] array) {
        String string;
        if (array != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(array[i]);
                if (i < array.length - 1) {
                    sb.append(',');
                }
            }
            string = sb.toString();
        }
        else {
            string = null;
        }
        return string;
    }
    
    public static byte[] toByte(final char[] array) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[array.length * 2];
        int i = 0;
        int n = 0;
        while (i < array.length) {
            final int n2 = n + 1;
            array2[n] = (byte)(array[i] & '\u00ff');
            n = n2 + 1;
            array2[n2] = (byte)(array[i] >> 8 & 0xFF);
            ++i;
        }
        return array2;
    }
    
    public static char[] toChar(final byte[] array) {
        if (array == null) {
            return null;
        }
        final char[] array2 = new char[array.length / 2];
        int i = 0;
        int n = 0;
        while (i < array.length) {
            final int n2 = i + 1;
            array2[n] = (char)((array[i] & 0xFF) + ((array[n2] & 0xFF) << 8));
            ++n;
            i = n2 + 1;
        }
        return array2;
    }
    
    public static String[] unpackCipherSuites(final String s) {
        if (s == null) {
            return null;
        }
        final Vector vector = new Vector();
        int i;
        int n;
        for (i = s.indexOf(44), n = 0; i > -1; i = s.indexOf(44, n)) {
            vector.add((Object)s.substring(n, i));
            n = i + 1;
        }
        vector.add((Object)s.substring(n));
        final String[] array = new String[vector.size()];
        vector.toArray((Object[])array);
        return array;
    }
    
    public SSLSocketFactory createSocketFactory(String property) throws MqttSecurityException {
        final SSLContext sslContext = this.getSSLContext(property);
        final Logger logger = this.logger;
        if (logger != null) {
            String s;
            if (property != null) {
                s = property;
            }
            else {
                s = "null (broker defaults)";
            }
            if (this.getEnabledCipherSuites(property) != null) {
                property = this.getProperty(property, "com.ibm.ssl.enabledCipherSuites", null);
            }
            else {
                property = "null (using platform-enabled cipher suites)";
            }
            logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "createSocketFactory", "12020", new Object[] { s, property });
        }
        return sslContext.getSocketFactory();
    }
    
    public boolean getClientAuthentication(String property) {
        property = this.getProperty(property, "com.ibm.ssl.clientAuthentication", null);
        return property != null && Boolean.valueOf(property);
    }
    
    public Properties getConfiguration(final String s) {
        Object o;
        if (s == null) {
            o = this.defaultProperties;
        }
        else {
            o = this.configs.get((Object)s);
        }
        return (Properties)o;
    }
    
    public String[] getEnabledCipherSuites(final String s) {
        return unpackCipherSuites(this.getProperty(s, "com.ibm.ssl.enabledCipherSuites", null));
    }
    
    public String getJSSEProvider(final String s) {
        return this.getProperty(s, "com.ibm.ssl.contextProvider", null);
    }
    
    public String getKeyManager(final String s) {
        return this.getProperty(s, "com.ibm.ssl.keyManager", "ssl.KeyManagerFactory.algorithm");
    }
    
    public String getKeyStore(String propertyFromConfig) {
        propertyFromConfig = this.getPropertyFromConfig(propertyFromConfig, "com.ibm.ssl.keyStore");
        if (propertyFromConfig != null) {
            return propertyFromConfig;
        }
        return System.getProperty("javax.net.ssl.keyStore");
    }
    
    public char[] getKeyStorePassword(String property) {
        property = this.getProperty(property, "com.ibm.ssl.keyStorePassword", "javax.net.ssl.keyStorePassword");
        char[] array;
        if (property != null) {
            if (property.startsWith("{xor}")) {
                array = deObfuscate(property);
            }
            else {
                array = property.toCharArray();
            }
        }
        else {
            array = null;
        }
        return array;
    }
    
    public String getKeyStoreProvider(final String s) {
        return this.getProperty(s, "com.ibm.ssl.keyStoreProvider", null);
    }
    
    public String getKeyStoreType(final String s) {
        return this.getProperty(s, "com.ibm.ssl.keyStoreType", "javax.net.ssl.keyStoreType");
    }
    
    public String getSSLProtocol(final String s) {
        return this.getProperty(s, "com.ibm.ssl.protocol", null);
    }
    
    public String getTrustManager(final String s) {
        return this.getProperty(s, "com.ibm.ssl.trustManager", "ssl.TrustManagerFactory.algorithm");
    }
    
    public String getTrustStore(String s) {
        s = this.getProperty(s, "com.ibm.ssl.trustStore", "javax.net.ssl.trustStore");
        try {
            s = URLDecoder.decode(s, StandardCharsets.UTF_8.name());
            return s;
        }
        catch (final Exception ex) {
            return s;
        }
    }
    
    public char[] getTrustStorePassword(String property) {
        property = this.getProperty(property, "com.ibm.ssl.trustStorePassword", "javax.net.ssl.trustStorePassword");
        char[] array;
        if (property != null) {
            if (property.startsWith("{xor}")) {
                array = deObfuscate(property);
            }
            else {
                array = property.toCharArray();
            }
        }
        else {
            array = null;
        }
        return array;
    }
    
    public String getTrustStoreProvider(final String s) {
        return this.getProperty(s, "com.ibm.ssl.trustStoreProvider", null);
    }
    
    public String getTrustStoreType(final String s) {
        return this.getProperty(s, "com.ibm.ssl.trustStoreType", null);
    }
    
    public void initialize(final Properties properties, final String s) throws IllegalArgumentException {
        this.checkPropertyKeys(properties);
        final Properties defaultProperties = new Properties();
        defaultProperties.putAll((Map)properties);
        this.convertPassword(defaultProperties);
        if (s != null) {
            this.configs.put((Object)s, (Object)defaultProperties);
        }
        else {
            this.defaultProperties = defaultProperties;
        }
    }
    
    public void merge(final Properties properties, final String s) throws IllegalArgumentException {
        this.checkPropertyKeys(properties);
        Properties defaultProperties = this.defaultProperties;
        if (s != null) {
            defaultProperties = (Properties)this.configs.get((Object)s);
        }
        Properties defaultProperties2;
        if ((defaultProperties2 = defaultProperties) == null) {
            defaultProperties2 = new Properties();
        }
        this.convertPassword(properties);
        defaultProperties2.putAll((Map)properties);
        if (s != null) {
            this.configs.put((Object)s, (Object)defaultProperties2);
        }
        else {
            this.defaultProperties = defaultProperties2;
        }
    }
    
    public boolean remove(final String s) {
        boolean b = true;
        if (s != null) {
            if (this.configs.remove((Object)s) != null) {
                return b;
            }
        }
        else if (this.defaultProperties != null) {
            this.defaultProperties = null;
            return b;
        }
        b = false;
        return b;
    }
}
