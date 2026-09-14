package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;

public class SendMessageToWX
{
    private SendMessageToWX() {
    }
    
    public interface IWXMusicVipObject
    {
        boolean checkArgs();
        
        void serialize(final Bundle p0);
        
        void unserialize(final Bundle p0);
    }
    
    public interface IWXSceneDataObject
    {
        boolean checkArgs();
        
        int getJumpType();
        
        void serialize(final Bundle p0);
        
        void unserialize(final Bundle p0);
    }
}
