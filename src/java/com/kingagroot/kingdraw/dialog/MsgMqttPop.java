package com.kingagroot.kingdraw.dialog;

import java.util.List;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import com.kingagroot.kingdraw.utils.RichTextUtils;
import android.view.View$OnClickListener;
import android.widget.ImageButton;
import android.content.Context;
import android.webkit.WebView;
import android.widget.TextView;
import com.kingagroot.kingdraw.model.MsgDialogModel;
import android.widget.Button;
import android.app.Dialog;

public class MsgMqttPop extends Dialog
{
    public static final String TAG = "MsgMqttPop";
    private Button btnPopMsgCenter;
    private Button btnPopMsgLeft;
    private Button btnPopMsgRight;
    private final MsgDialogModel msgDialogModel;
    private OnMsgPopClick onMsgPopClick;
    private TextView tvPopMsgContent;
    private TextView tvPopMsgTitle;
    private WebView webPopMsgContent;
    
    public MsgMqttPop(final Context context, final MsgDialogModel msgDialogModel) {
        super(context, 2131886327);
        this.msgDialogModel = msgDialogModel;
    }
    
    private void init() {
        this.tvPopMsgTitle = (TextView)this.findViewById(2131297638);
        final ImageButton imageButton = (ImageButton)this.findViewById(2131296818);
        this.tvPopMsgContent = (TextView)this.findViewById(2131297637);
        this.webPopMsgContent = (WebView)this.findViewById(2131297761);
        this.btnPopMsgLeft = (Button)this.findViewById(2131296445);
        this.btnPopMsgCenter = (Button)this.findViewById(2131296444);
        this.btnPopMsgRight = (Button)this.findViewById(2131296446);
        imageButton.setOnClickListener((View$OnClickListener)new _$$Lambda$MsgMqttPop$q4TXPi_e_FG2b78aX2T_DXNraR8(this));
    }
    
    private void setMsgInfo() {
        this.tvPopMsgTitle.setText((CharSequence)this.msgDialogModel.getTitle());
        if (this.msgDialogModel.getContentType() == 0) {
            this.webPopMsgContent.setVisibility(0);
            this.tvPopMsgContent.setVisibility(8);
            this.webPopMsgContent.loadUrl(this.msgDialogModel.getMsgContent());
        }
        else if (this.msgDialogModel.getContentType() == 1) {
            this.webPopMsgContent.setVisibility(8);
            this.tvPopMsgContent.setVisibility(0);
            RichTextUtils.showRichHtmlWithImageContent(this.tvPopMsgContent, this.msgDialogModel.getMsgContent());
        }
        this.setMsgAction(this.msgDialogModel.getMsgActions());
        if (this.msgDialogModel.getShowType() == 3) {
            new CountDownTimer(this, 5000L, 1000L) {
                final MsgMqttPop this$0;
                
                public void onFinish() {
                    this.this$0.dismiss();
                }
                
                public void onTick(final long n) {
                }
            }.start();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493147);
        this.setCanceledOnTouchOutside(false);
        final Window window = this.getWindow();
        if (this.msgDialogModel.getShowType() == 1) {
            window.setGravity(17);
        }
        else {
            window.setGravity(80);
        }
        final WindowManager$LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.init();
        this.setMsgInfo();
    }
    
    public void setMsgAction(final List<MsgDialogModel.MsgDialogActionModel> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                final MsgDialogModel.MsgDialogActionModel msgDialogActionModel = (MsgDialogModel.MsgDialogActionModel)list.get(i);
                if (msgDialogActionModel.getOrderID() == 1) {
                    this.btnPopMsgLeft.setVisibility(0);
                    this.btnPopMsgLeft.setText((CharSequence)msgDialogActionModel.getActionName());
                    this.btnPopMsgLeft.setOnClickListener((View$OnClickListener)new _$$Lambda$MsgMqttPop$H_xLlz4EeclJby5I_oHeKWAB8ms(this, msgDialogActionModel));
                }
                else if (msgDialogActionModel.getOrderID() == 2) {
                    this.btnPopMsgCenter.setVisibility(0);
                    this.btnPopMsgCenter.setText((CharSequence)msgDialogActionModel.getActionName());
                    this.btnPopMsgCenter.setOnClickListener((View$OnClickListener)new _$$Lambda$MsgMqttPop$bpM5T_bNzrdVb357gNG9V_xBU9c(this, msgDialogActionModel));
                }
                else if (msgDialogActionModel.getOrderID() == 3) {
                    this.btnPopMsgRight.setVisibility(0);
                    this.btnPopMsgRight.setText((CharSequence)msgDialogActionModel.getActionName());
                    this.btnPopMsgRight.setOnClickListener((View$OnClickListener)new _$$Lambda$MsgMqttPop$jY8TKwY3dqzgov5ibJtcm_Pn8TU(this, msgDialogActionModel));
                }
            }
        }
        else {
            this.btnPopMsgLeft.setVisibility(8);
            this.btnPopMsgCenter.setVisibility(8);
            this.btnPopMsgRight.setVisibility(8);
        }
    }
    
    public void setMsgPopClickListener(final OnMsgPopClick onMsgPopClick) {
        this.onMsgPopClick = onMsgPopClick;
    }
    
    public interface OnMsgPopClick
    {
        void onCenterAction(final String p0);
        
        void onLeftAction(final String p0);
        
        void onRightAction(final String p0);
    }
}
