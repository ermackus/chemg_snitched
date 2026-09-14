package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.kingagroot.kingdraw.limit.LimitPalette;
import com.kingagroot.kingdraw.limit.LimitDialog;
import android.app.Activity;
import java.io.Serializable;
import android.os.Bundle;
import android.content.Intent;
import com.kingagroot.kingdraw.palette.NewPaletteRotateActivity;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.model.BkFileModel;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class KingDrawAction extends BaseAction
{
    public KingDrawAction(final Context context) {
        super(context);
    }
    
    public void action(String smilesToMol, final CallBackFunction callBackFunction) {
        final BkFileModel bkFileModel = (BkFileModel)GsonUtil.fromJson(smilesToMol, (Class)BkFileModel.class);
        smilesToMol = ProtocolConverter.smilesToMol(bkFileModel.getSmiles());
        if (!TextUtils.isEmpty((CharSequence)smilesToMol)) {
            new LimitPalette((LimitPalette$OnJumpPalette)new LimitPalette$OnJumpPalette(this, bkFileModel, smilesToMol) {
                final KingDrawAction this$0;
                final BkFileModel val$model;
                final String val$molStr;
                
                public void onJumpPalette() {
                    final Intent intent = new Intent(this.this$0.context, (Class)NewPaletteRotateActivity.class);
                    intent.putExtra("intent_data_type", 3);
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable("BkFileModel", (Serializable)this.val$model);
                    bundle.putString("intent_data", this.val$molStr);
                    intent.putExtras(bundle);
                    ((Activity)this.this$0.context).startActivityForResult(intent, 258);
                }
                
                public void onShowVipDialog() {
                    LimitDialog.showVipDialog(this.this$0.context, this.this$0.context.getString(2131820979));
                }
            }).jumpPaletteCheck(this.context);
        }
    }
}
