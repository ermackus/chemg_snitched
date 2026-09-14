package com.kingagroot.kingdraw.widget.paletteMenu;

import com.goodsrc.ui.library.QyPermissions$RequestPermissionsCallBack;
import com.goodsrc.ui.library.QyPermissions$EasyParam;
import android.view.View;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.PhotoActivity;
import java.util.List;
import com.hjq.permissions.OnPermissionCallback;
import android.os.Build$VERSION;
import com.hjq.permissions.XXPermissions;
import android.view.View$OnClickListener;
import android.content.Context;
import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.component.ui.menu.menuitem.AiItemView;

public class KdAiItemView extends AiItemView
{
    public static int REQUEST_CODE_AI = 10021;
    BaseActivity activity;
    
    public KdAiItemView(final Context context) {
        super(context);
        this.activity = (BaseActivity)this.context;
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    private void checkFilePermission() {
        final XXPermissions permission = XXPermissions.with(this.context).permission(new String[] { "android.permission.CAMERA" });
        if (Build$VERSION.SDK_INT >= 33) {
            permission.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            permission.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        permission.request((OnPermissionCallback)new _$$Lambda$KdAiItemView$dqAuTwfIL7cJx64R4QrBPqo2Bb4(this));
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        final QyPermissions$EasyParam qyPermissions$EasyParam = new QyPermissions$EasyParam();
        qyPermissions$EasyParam.showSetting = true;
        qyPermissions$EasyParam.onResumeCheck = false;
        qyPermissions$EasyParam.permission = "android.permission.CAMERA";
        this.activity.checkPermission(qyPermissions$EasyParam, (QyPermissions$RequestPermissionsCallBack)new _$$Lambda$KdAiItemView$9oznVl84Swyr_c1C1x9o_WOyiMg(this));
    }
}
