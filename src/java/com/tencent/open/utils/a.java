package com.tencent.open.utils;

import javax.crypto.spec.IvParameterSpec;
import java.security.PrivateKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import javax.security.auth.x500.X500Principal;
import android.security.KeyPairGeneratorSpec$Builder;
import java.util.Calendar;
import java.security.spec.AlgorithmParameterSpec;
import android.security.keystore.KeyGenParameterSpec$Builder;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.MessageDigest;
import java.security.Key;
import javax.crypto.Cipher;
import android.os.Build$VERSION;
import android.util.Base64;
import java.security.SecureRandom;
import com.tencent.open.log.SLog;
import java.security.KeyStore$LoadStoreParameter;
import android.content.Context;
import android.content.SharedPreferences;
import java.security.KeyStore;

public class a
{
    private KeyStore a;
    private SharedPreferences b;
    
    public a(final Context context) {
        try {
            this.b = context.getSharedPreferences("KEYSTORE_SETTING", 0);
            (this.a = KeyStore.getInstance("AndroidKeyStore")).load((KeyStore$LoadStoreParameter)null);
            if (!this.a.containsAlias("KEYSTORE_AES")) {
                this.c("");
                this.a(context);
                this.a();
            }
        }
        catch (final Exception ex) {
            SLog.d("KEYSTORE", "Exception", (Throwable)ex);
        }
    }
    
    private void a() throws Exception {
        final byte[] array = new byte[16];
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(array);
        this.c(Base64.encodeToString(secureRandom.generateSeed(12), 0));
        if (Build$VERSION.SDK_INT >= 18) {
            final PublicKey publicKey = this.a.getCertificate("KEYSTORE_AES").getPublicKey();
            final Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, (Key)publicKey);
            this.d(Base64.encodeToString(instance.doFinal(array), 0));
        }
        else {
            final MessageDigest instance2 = MessageDigest.getInstance("SHA-256");
            instance2.update(array);
            this.d(Base64.encodeToString(instance2.digest(), 0));
        }
    }
    
    private void a(final Context context) throws Exception {
        final StringBuilder sb = new StringBuilder();
        sb.append("Build.VERSION.SDK_INT=");
        sb.append(Build$VERSION.SDK_INT);
        SLog.d("KEYSTORE", sb.toString());
        if (Build$VERSION.SDK_INT >= 23) {
            final KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            instance.initialize((AlgorithmParameterSpec)new KeyGenParameterSpec$Builder("KEYSTORE_AES", 3).setDigests(new String[] { "SHA-256", "SHA-512" }).setEncryptionPaddings(new String[] { "PKCS1Padding" }).build());
            instance.generateKeyPair();
        }
        else if (Build$VERSION.SDK_INT >= 18) {
            final KeyPairGenerator instance2 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            final Calendar instance3 = Calendar.getInstance();
            final Calendar instance4 = Calendar.getInstance();
            instance4.add(1, 30);
            instance2.initialize((AlgorithmParameterSpec)new KeyPairGeneratorSpec$Builder(context).setAlias("KEYSTORE_AES").setSubject(new X500Principal("CN=KEYSTORE_AES")).setSerialNumber(BigInteger.TEN).setStartDate(instance3.getTime()).setEndDate(instance4.getTime()).build());
            instance2.generateKeyPair();
        }
    }
    
    private byte[] b() {
        return Base64.decode(this.b.getString("PREF_KEY_IV", ""), 0);
    }
    
    private SecretKeySpec c() throws Exception {
        final String string = this.b.getString("PREF_KEY_AES", "");
        if (Build$VERSION.SDK_INT >= 18) {
            final PrivateKey privateKey = (PrivateKey)this.a.getKey("KEYSTORE_AES", (char[])null);
            final Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, (Key)privateKey);
            return new SecretKeySpec(instance.doFinal(Base64.decode(string, 0)), "AES/GCM/NoPadding");
        }
        return new SecretKeySpec(Base64.decode(string, 0), "AES/GCM/NoPadding");
    }
    
    private void c(final String s) {
        this.b.edit().putString("PREF_KEY_IV", s).apply();
    }
    
    private void d(final String s) {
        this.b.edit().putString("PREF_KEY_AES", s).apply();
    }
    
    public String a(String encodeToString) {
        try {
            final Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(1, (Key)this.c(), (AlgorithmParameterSpec)new IvParameterSpec(this.b()));
            encodeToString = Base64.encodeToString(instance.doFinal(encodeToString.getBytes()), 0);
            return encodeToString;
        }
        catch (final Exception ex) {
            SLog.e("KEYSTORE", "Exception", (Throwable)ex);
            return "";
        }
    }
    
    public String b(String s) {
        try {
            final byte[] decode = Base64.decode(s.getBytes(), 0);
            final Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(2, (Key)this.c(), (AlgorithmParameterSpec)new IvParameterSpec(this.b()));
            s = new String(instance.doFinal(decode));
            return s;
        }
        catch (final Exception ex) {
            SLog.e("KEYSTORE", "Exception", (Throwable)ex);
            return "";
        }
    }
}
