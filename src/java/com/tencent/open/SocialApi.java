package com.tencent.open;

import com.tencent.tauth.IUiListener;
import android.os.Bundle;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;

public class SocialApi
{
    private SocialApiIml a;
    
    public SocialApi(final QQToken qqToken) {
        this.a = new SocialApiIml(qqToken);
    }
    
    public void ask(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a.ask(activity, bundle, uiListener);
    }
    
    public void gift(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a.gift(activity, bundle, uiListener);
    }
    
    public void invite(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a.invite(activity, bundle, uiListener);
    }
    
    public void story(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a.story(activity, bundle, uiListener);
    }
}
