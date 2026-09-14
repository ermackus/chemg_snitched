package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;

public class WXStateSceneDataObject implements SendMessageToWX$IWXSceneDataObject
{
    private static final int LENGTH_LIMIT = 10240;
    private static final String TAG = "MicroMsg.SDK.WXStateSceneDataObject";
    private static final String WX_STATE_JUMP_INFO_KEY_IDENTIFIER = "_wxapi_scene_data_state_jump_info_identifier";
    public String stateId;
    public WXStateSceneDataObject.WXStateSceneDataObject$IWXStateJumpInfo stateJumpInfo;
    public String stateTitle;
    public String token;
    
    public boolean checkArgs() {
        final String stateId = this.stateId;
        if (stateId != null && stateId.length() > 10240) {
            Log.e("MicroMsg.SDK.WXStateSceneDataObject", "checkArgs fail, stateId is invalid");
            return false;
        }
        final String stateTitle = this.stateTitle;
        if (stateTitle != null && stateTitle.length() > 10240) {
            Log.e("MicroMsg.SDK.WXStateSceneDataObject", "checkArgs fail, stateId is invalid");
            return false;
        }
        final String token = this.token;
        if (token != null && token.length() > 10240) {
            Log.e("MicroMsg.SDK.WXStateSceneDataObject", "checkArgs fail, stateId is invalid");
            return false;
        }
        final WXStateSceneDataObject.WXStateSceneDataObject$IWXStateJumpInfo stateJumpInfo = this.stateJumpInfo;
        if (stateJumpInfo == null) {
            Log.e("MicroMsg.SDK.WXStateSceneDataObject", "checkArgs fail, statsJumpInfo is null");
            return false;
        }
        return stateJumpInfo.checkArgs();
    }
    
    public int getJumpType() {
        final WXStateSceneDataObject.WXStateSceneDataObject$IWXStateJumpInfo stateJumpInfo = this.stateJumpInfo;
        int type;
        if (stateJumpInfo != null) {
            type = stateJumpInfo.type();
        }
        else {
            type = 0;
        }
        return type;
    }
    
    public void serialize(final Bundle bundle) {
        bundle.putString("_wxapi_scene_data_state_id", this.stateId);
        bundle.putString("_wxapi_scene_data_state_title", this.stateTitle);
        bundle.putString("_wxapi_scene_data_state_token", this.token);
        final WXStateSceneDataObject.WXStateSceneDataObject$IWXStateJumpInfo stateJumpInfo = this.stateJumpInfo;
        if (stateJumpInfo != null) {
            bundle.putString("_wxapi_scene_data_state_jump_info_identifier", stateJumpInfo.getClass().getName());
            this.stateJumpInfo.serialize(bundle);
        }
    }
    
    public void unserialize(final Bundle bundle) {
        this.stateId = bundle.getString("_wxapi_scene_data_state_id");
        this.stateTitle = bundle.getString("_wxapi_scene_data_state_title");
        this.token = bundle.getString("_wxapi_scene_data_state_token");
        final String string = bundle.getString("_wxapi_scene_data_state_jump_info_identifier");
        if (string != null) {
            try {
                (this.stateJumpInfo = (WXStateSceneDataObject.WXStateSceneDataObject$IWXStateJumpInfo)Class.forName(string).newInstance()).unserialize(bundle);
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("get WXSceneDataObject from bundle failed: unknown ident ");
                sb.append(string);
                sb.append(", ex = ");
                sb.append(ex.getMessage());
                Log.e("MicroMsg.SDK.WXStateSceneDataObject", sb.toString());
            }
        }
    }
}
