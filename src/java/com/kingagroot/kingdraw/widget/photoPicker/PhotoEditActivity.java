package com.kingagroot.kingdraw.widget.photoPicker;

import android.os.Bundle;
import com.bumptech.glide.request.target.Target;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.kingagroot.kingdraw.widget.photoPicker.editer.ImageEditView;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoEditActivity extends AppCompatActivity
{
    public static final String PATH_URL = "intent_path_url";
    private ImageEditView editView;
    private String path;
    
    private void initData() {
        this.path = this.getIntent().getExtras().getString("intent_path_url");
    }
    
    private void initView() {
        this.editView = (ImageEditView)this.findViewById(2131296613);
        Glide.with((FragmentActivity)this).asBitmap().load(this.path).into((Target)new PhotoEditActivity$1(this));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492925);
        this.initData();
        this.initView();
    }
    
    protected void onPause() {
        super.onPause();
        this.editView.onPause();
    }
    
    protected void onResume() {
        super.onResume();
    }
}
