package com.kingagroot.kingdraw.ui.baike.bridgeHandler;

import org.json.JSONException;
import org.json.JSONObject;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.TitleAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.TencentAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.CallAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.DialogAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.SearchPaletteAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.MoreInfoAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.FeedbackAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.PicPreviewAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.SupplierInfoAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.InputAction;
import com.kingagroot.kingdraw.ui.baike.nativeaction.KingDrawAction;
import java.util.HashMap;
import android.content.Context;
import com.kingagroot.kingdraw.ui.baike.nativeaction.BaseAction;
import java.util.Map;
import com.github.lzyzsd.jsbridge.BridgeHandler;

public class NativeOpenHandler implements BridgeHandler
{
    private final Map<String, BaseAction> actionMap;
    
    public NativeOpenHandler(final Context context) {
        this.actionMap = (Map<String, BaseAction>)new HashMap();
        this.initAction(context);
    }
    
    private void initAction(final Context context) {
        this.actionMap.put((Object)"kingdraw", (Object)new KingDrawAction(context));
        this.actionMap.put((Object)"input", (Object)new InputAction(context));
        this.actionMap.put((Object)"supplier", (Object)new SupplierInfoAction(context));
        this.actionMap.put((Object)"pic", (Object)new PicPreviewAction(context));
        this.actionMap.put((Object)"feedback", (Object)new FeedbackAction(context));
        this.actionMap.put((Object)"more", (Object)new MoreInfoAction(context));
        this.actionMap.put((Object)"searchKingdraw", (Object)new SearchPaletteAction(context));
        this.actionMap.put((Object)"dialog", (Object)new DialogAction(context));
        this.actionMap.put((Object)"call", (Object)new CallAction(context));
        this.actionMap.put((Object)"qq", (Object)new TencentAction(context));
        this.actionMap.put((Object)"detailtitle", (Object)new TitleAction(context));
    }
    
    public void handler(String string, final CallBackFunction callBackFunction) {
        try {
            final JSONObject jsonObject = new JSONObject(string);
            final String string2 = jsonObject.getString("type");
            string = "";
            if (jsonObject.has("data")) {
                string = jsonObject.getString("data");
            }
            final BaseAction baseAction = (BaseAction)this.actionMap.get((Object)string2);
            if (baseAction != null) {
                baseAction.action(string, callBackFunction);
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
    }
}
