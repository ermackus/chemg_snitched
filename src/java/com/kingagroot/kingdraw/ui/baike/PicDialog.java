package com.kingagroot.kingdraw.ui.baike;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONArray;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageButton;
import android.os.Bundle;
import com.bumptech.glide.request.target.Target;
import android.widget.ImageView;
import org.xutils.image.ImageOptions$Builder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import android.content.Context;
import android.widget.ProgressBar;
import com.github.chrisbanes.photoview.PhotoView;
import android.app.Dialog;

public class PicDialog extends Dialog
{
    private PhotoView ivPic;
    private ProgressBar loading;
    private final String picData;
    
    public PicDialog(final Context context, final String picData) {
        super(context, 2131886437);
        this.picData = picData;
    }
    
    private void loadImageUrl(final String s) {
        ((RequestBuilder)Glide.with(this.getContext()).load(s).error(new ImageOptions$Builder().setFailureDrawableId(2131231528).build().getFailureDrawable((ImageView)this.ivPic))).into((Target)new PicDialog$2(this, (ImageView)this.ivPic));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493149);
        this.setCanceledOnTouchOutside(false);
        this.getWindow().setLayout(-1, -1);
        this.ivPic = (PhotoView)this.findViewById(2131296934);
        ((ImageButton)this.findViewById(2131296803)).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final PicDialog this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
        this.loading = (ProgressBar)this.findViewById(2131297040);
        String s;
        try {
            s = (String)new JSONArray(this.picData).get(0);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
            s = null;
        }
        Log.e("url", s);
        this.loadImageUrl(s);
    }
    
    protected void onStart() {
        super.onStart();
        this.getWindow().getDecorView().setSystemUiVisibility(2822);
    }
}
