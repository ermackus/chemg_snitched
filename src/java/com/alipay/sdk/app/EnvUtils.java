package com.alipay.sdk.app;

public class EnvUtils
{
    public static EnvEnum mEnv;
    
    static {
        EnvUtils.mEnv = EnvEnum.ONLINE;
    }
    
    public static EnvEnum geEnv() {
        return EnvUtils.mEnv;
    }
    
    public static boolean isNewSanBox() {
        return EnvUtils.mEnv == EnvEnum.SANDBOX;
    }
    
    public static boolean isPreSandBox() {
        return EnvUtils.mEnv == EnvEnum.PRE_SANDBOX;
    }
    
    public static boolean isSandBox() {
        return isPreSandBox() || isNewSanBox();
    }
    
    public static void setEnv(final EnvEnum mEnv) {
        EnvUtils.mEnv = mEnv;
    }
    
    public enum EnvEnum
    {
        public static final EnvEnum[] $VALUES;
        
        ONLINE, 
        PRE_SANDBOX, 
        SANDBOX;
    }
}
