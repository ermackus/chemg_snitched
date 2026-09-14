package com.kingagroot.kingdraw.dialog;

import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import java.util.Objects;
import android.view.Window;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.content.Context;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;

public class AppScoreDialog extends AlertDialog
{
    private Button btnLater;
    private Button btnScore;
    
    public AppScoreDialog(final Context context) {
        super(context, 2131886327);
    }
    
    private void initEvent() {
        this.btnScore.setOnClickListener((View$OnClickListener)new AppScoreDialog$1(this));
        this.btnLater.setOnClickListener((View$OnClickListener)new AppScoreDialog$2(this));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493017);
        this.setCanceledOnTouchOutside(false);
        ((Window)Objects.requireNonNull((Object)this.getWindow())).setLayout(-1, -2);
        this.btnScore = (Button)this.findViewById(2131296450);
        this.btnLater = (Button)this.findViewById(2131296434);
        final WordBreakTextView wordBreakTextView = (WordBreakTextView)this.findViewById(2131297675);
        ((WordBreakTextView)Objects.requireNonNull((Object)wordBreakTextView)).setText(this.getContext().getString(2131821076), "");
        wordBreakTextView.setTextColor(-570425344);
        final WordBreakTextView wordBreakTextView2 = (WordBreakTextView)this.findViewById(2131297551);
        ((WordBreakTextView)Objects.requireNonNull((Object)wordBreakTextView2)).setText(this.getContext().getString(2131821127), "");
        wordBreakTextView2.setTextColor(-1979711488);
        this.initEvent();
    }
    
    public void show() {
        super.show();
        ShareData.saveScoredAppFlag();
    }
}
