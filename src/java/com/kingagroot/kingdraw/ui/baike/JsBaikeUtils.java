package com.kingagroot.kingdraw.ui.baike;

import java.util.List;
import android.content.pm.PackageInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class JsBaikeUtils
{
    public static void copyToClipboard(final Context context, final CharSequence charSequence) {
        final ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence)null, charSequence));
            if (clipboardManager.hasPrimaryClip()) {
                clipboardManager.getPrimaryClip().getItemAt(0).getText();
            }
        }
    }
    
    public static boolean isqqclientavailable(final Context context) {
        final List installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); ++i) {
            final String packageName = ((PackageInfo)installedPackages.get(i)).packageName;
            if ("com.tencent.mobileqq".equals((Object)packageName) || "com.tencent.tim".equals((Object)packageName)) {
                return true;
            }
        }
        return false;
    }
}
