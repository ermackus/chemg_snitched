package com.kingagroot.kingdraw.ui.baike;

import android.view.View;
import android.view.View$OnClickListener;
import android.os.Bundle;
import com.goodsrc.library.utils.GsonUtil;
import android.content.Context;
import android.webkit.WebView;
import android.widget.TextView;
import com.kingagroot.kingdraw.model.ChemInfoMoreModel;
import android.widget.ImageButton;
import android.app.Dialog;

public class ChemInfoMoreDialog extends Dialog
{
    private ImageButton ibtClose;
    private final ChemInfoMoreModel moreModel;
    private TextView tvTitle;
    private WebView webMoreInfo;
    
    public ChemInfoMoreDialog(final Context context, final String s) {
        super(context);
        this.moreModel = (ChemInfoMoreModel)GsonUtil.fromJson(s, (Class)ChemInfoMoreModel.class);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2131493018);
        this.setCanceledOnTouchOutside(false);
        this.getWindow().setLayout((int)(this.getContext().getResources().getDisplayMetrics().widthPixels * 0.7), -2);
        this.tvTitle = (TextView)this.findViewById(2131297675);
        this.ibtClose = (ImageButton)this.findViewById(2131296803);
        this.webMoreInfo = (WebView)this.findViewById(2131297759);
        this.ibtClose.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final ChemInfoMoreDialog this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
        this.webMoreInfo.getSettings().setJavaScriptEnabled(true);
        this.tvTitle.setText((CharSequence)this.moreModel.getTitle());
        final String replace = this.moreModel.getHtml().replace((CharSequence)"\\'", (CharSequence)"\\\"");
        final WebView webMoreInfo = this.webMoreInfo;
        final StringBuilder sb = new StringBuilder();
        sb.append("<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>");
        sb.append(replace);
        webMoreInfo.loadDataWithBaseURL((String)null, sb.toString(), "text/html", "UTF-8", (String)null);
    }
}
