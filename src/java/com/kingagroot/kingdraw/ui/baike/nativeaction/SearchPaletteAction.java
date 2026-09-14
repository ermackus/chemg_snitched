package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.kingagroot.component.ui.model.GFormatValue;
import android.app.Activity;
import java.io.Serializable;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.NewSearchPaletteActivity;
import com.kingagroot.kingdraw.ui.baike.SearchType;
import android.text.TextUtils;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class SearchPaletteAction extends BaseAction
{
    public static final int REQUEST_CODE_SEARCH = 1002;
    
    public SearchPaletteAction(final Context context) {
        super(context);
    }
    
    public void action(String kingContent, final CallBackFunction callBackFunction) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)kingContent) || !SearchType.isSmiles(kingContent)) {
            kingContent = "";
        }
        final Intent intent = new Intent(this.context, (Class)NewSearchPaletteActivity.class);
        if (!TextUtils.isEmpty((CharSequence)kingContent)) {
            final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
            if (readerFormatForType != null) {
                kingContent = ProtocolConverter.smilesToMol(kingContent, (double)(float)GDensityUtil.dp2px(Float.parseFloat(readerFormatForType.getFixedLength())), Math.toRadians((double)readerFormatForType.getChainsAngle()));
            }
            else {
                kingContent = ProtocolConverter.smilesToMol(kingContent);
            }
            final GFileSearchModel gFileSearchModel = new GFileSearchModel();
            gFileSearchModel.setKingContent(kingContent);
            intent.putExtra("intent_key_gfilesearchmodel", (Serializable)gFileSearchModel);
        }
        ((Activity)this.context).startActivityForResult(intent, 1002);
    }
}
