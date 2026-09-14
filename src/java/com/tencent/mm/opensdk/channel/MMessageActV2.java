package com.tencent.mm.opensdk.channel;

import android.os.Handler;
import android.app.PendingIntent$OnFinished;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Build$VERSION;
import com.tencent.mm.opensdk.channel.a.a;
import android.content.Intent;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;
import android.content.Context;

public class MMessageActV2
{
    public static final String DEFAULT_ENTRY_CLASS_NAME = ".wxapi.WXEntryActivity";
    public static final String MM_ENTRY_PACKAGE_NAME = "com.tencent.mm";
    public static final String MM_MSG_ENTRY_CLASS_NAME = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
    private static final String TAG = "MicroMsg.SDK.MMessageAct";
    
    public static boolean send(final Context context, final Args args) {
        while (true) {
            Label_0393: {
                if (context == null) {
                    break Label_0393;
                }
                if (args == null) {
                    break Label_0393;
                }
                if (!b.b(args.targetPkgName)) {
                    if (b.b(args.targetClassName)) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(args.targetPkgName);
                        sb.append(".wxapi.WXEntryActivity");
                        args.targetClassName = sb.toString();
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("send, targetPkgName = ");
                    sb2.append(args.targetPkgName);
                    sb2.append(", targetClassName = ");
                    sb2.append(args.targetClassName);
                    sb2.append(", launchMode = ");
                    sb2.append(args.launchMode);
                    Log.i("MicroMsg.SDK.MMessageAct", sb2.toString());
                    final Intent intent = new Intent();
                    intent.setClassName(args.targetPkgName, args.targetClassName);
                    final Bundle bundle = args.bundle;
                    if (bundle != null) {
                        intent.putExtras(bundle);
                    }
                    final String packageName = context.getPackageName();
                    intent.putExtra("_mmessage_sdkVersion", 638065664);
                    intent.putExtra("_mmessage_appPackage", packageName);
                    intent.putExtra("_mmessage_content", args.content);
                    intent.putExtra("_mmessage_checksum", a.a(args.content, 638065664, packageName));
                    intent.putExtra("_message_token", args.token);
                    final int flags = args.flags;
                    if (flags == -1) {
                        intent.addFlags(268435456).addFlags(134217728);
                    }
                    else {
                        intent.setFlags(flags);
                    }
                    try {
                        if (Build$VERSION.SDK_INT >= 29 && args.launchMode == 2) {
                            sendUsingPendingIntent(context, intent);
                        }
                        else {
                            context.startActivity(intent);
                        }
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("send mm message, intent=");
                        sb3.append((Object)intent);
                        Log.d("MicroMsg.SDK.MMessageAct", sb3.toString());
                        return true;
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append("send fail, ex = ");
                        sb4.append(android.util.Log.getStackTraceString((Throwable)ex));
                        final String s = sb4.toString();
                        break Label_0050;
                    }
                    break Label_0393;
                }
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("send fail, invalid targetPkgName, targetPkgName = ");
                sb5.append(args.targetPkgName);
                final String s = sb5.toString();
                Log.e("MicroMsg.SDK.MMessageAct", s);
                return false;
            }
            final String s = "send fail, invalid argument";
            continue;
        }
    }
    
    private static void sendUsingPendingIntent(final Context context, final Intent intent) {
        try {
            Log.i("MicroMsg.SDK.MMessageAct", "sendUsingPendingIntent");
            PendingIntent pendingIntent;
            if (Build$VERSION.SDK_INT >= 23) {
                pendingIntent = PendingIntent.getActivity(context, 3, intent, 201326592);
            }
            else {
                pendingIntent = PendingIntent.getActivity(context, 3, intent, 134217728);
            }
            pendingIntent.send(context, 4, (Intent)null, (PendingIntent$OnFinished)new PendingIntent$OnFinished() {
                public void onSendFinished(final PendingIntent pendingIntent, final Intent intent, final int n, final String s, final Bundle bundle) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("sendUsingPendingIntent onSendFinished resultCode: ");
                    sb.append(n);
                    sb.append(", resultData: ");
                    sb.append(s);
                    Log.i("MicroMsg.SDK.MMessageAct", sb.toString());
                }
            }, (Handler)null);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("sendUsingPendingIntent fail, ex = ");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.MMessageAct", sb.toString());
            context.startActivity(intent);
        }
    }
    
    public static class Args
    {
        public static final int INVALID_FLAGS = -1;
        public Bundle bundle;
        public String content;
        public int flags;
        public int launchMode;
        public String targetClassName;
        public String targetPkgName;
        public String token;
        
        public Args() {
            this.flags = -1;
            this.launchMode = 2;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("targetPkgName:");
            sb.append(this.targetPkgName);
            sb.append(", targetClassName:");
            sb.append(this.targetClassName);
            sb.append(", content:");
            sb.append(this.content);
            sb.append(", flags:");
            sb.append(this.flags);
            sb.append(", bundle:");
            sb.append((Object)this.bundle);
            return sb.toString();
        }
    }
}
