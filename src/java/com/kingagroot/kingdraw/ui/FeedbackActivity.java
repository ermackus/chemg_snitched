package com.kingagroot.kingdraw.ui;

import com.luck.picture.lib.interfaces.OnItemClickListener;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import android.os.Bundle;
import com.luck.picture.lib.entity.MediaExtraInfo;
import java.util.Iterator;
import com.luck.picture.lib.utils.MediaUtils;
import com.luck.picture.lib.config.PictureMimeType;
import java.util.Collection;
import android.content.Intent;
import android.view.View;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.MultipartBody;
import java.io.File;
import com.luck.picture.lib.entity.LocalMedia;
import org.xutils.common.util.KeyValue;
import java.util.ArrayList;
import com.kingagroot.kingdraw.config.NetConfig$Feedback;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.goodsrc.library.utils.ToastUtil;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.utils.DensityUtil;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import com.kingagroot.kingdraw.widget.photoPicker.FullyGridLayoutManager;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.content.Context;
import com.luck.picture.lib.manager.PictureCacheManager;
import java.util.List;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.appcompat.app.AppCompatActivity;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import androidx.recyclerview.widget.RecyclerView;
import com.kingagroot.kingdraw.widget.photoPicker.GridImageAdapter;
import android.widget.EditText;
import android.widget.Button;
import com.kingagroot.kingdraw.widget.photoPicker.GridImageAdapter$OnAddPicClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class FeedbackActivity extends ToolBarActivity implements GridImageAdapter$OnAddPicClickListener
{
    private Button btnSubmit;
    private EditText etContactInfo;
    private EditText etOpinion;
    private GridImageAdapter mAdapter;
    private final int maxSelectNum;
    private RecyclerView recycler;
    private String strOpinion;
    
    public FeedbackActivity() {
        this.maxSelectNum = 6;
    }
    
    private void choosePic() {
        int language;
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_EN)) {
            language = 2;
        }
        else {
            language = 0;
        }
        PictureSelector.create((AppCompatActivity)this).openGallery(SelectMimeType.ofImage()).setImageEngine((ImageEngine)GlideEngine.createGlideEngine()).setLanguage(language).setMaxSelectNum(6).setRequestedOrientation(1).setImageSpanCount(4).setSelectionMode(2).isMaxSelectEnabledMask(true).isDisplayCamera(true).isPageStrategy(true).isPreviewImage(true).setSelectedData((List)this.mAdapter.getData()).forResult(188);
    }
    
    private void clearCache() {
        PictureCacheManager.deleteAllCacheDirRefreshFile((Context)this);
    }
    
    private void initView() {
        this.etOpinion = (EditText)this.findViewById(2131296644);
        this.etContactInfo = (EditText)this.findViewById(2131296632);
        this.btnSubmit = (Button)this.findViewById(2131296455);
        this.recycler = (RecyclerView)this.findViewById(2131297257);
        final TextView textView = (TextView)this.findViewById(2131297549);
        textView.setText((CharSequence)this.getResources().getString(2131820734));
        this.btnSubmit.setOnClickListener((View$OnClickListener)new _$$Lambda$FeedbackActivity$XTu0_mZRwyNPNfO9_gsE0GnB2PM(this));
        textView.setOnClickListener((View$OnClickListener)new _$$Lambda$FeedbackActivity$J_g5AO9sf8WXs9fwnEuAQFUwH90(this));
        this.recycler.setLayoutManager((RecyclerView$LayoutManager)new FullyGridLayoutManager((Context)this, 6, 1, false));
        this.recycler.addItemDecoration((RecyclerView$ItemDecoration)new GridSpacingItemDecoration(6, DensityUtil.dip2px((Context)this, 8.0f), false));
    }
    
    private boolean isNull() {
        final String trim = this.etOpinion.getText().toString().trim();
        this.strOpinion = trim;
        boolean b;
        if (TextUtils.isEmpty((CharSequence)trim) && this.mAdapter.getData().isEmpty()) {
            ToastUtil.showShort((CharSequence)this.getResources().getString(2131820830));
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    private void submit(final String s, final String s2) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Feedback.submitFeedback());
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("contact", s2));
        ((List)list).add((Object)new KeyValue("content", s));
        final ArrayList data = this.mAdapter.getData();
        for (int i = 0; i < ((List)data).size(); ++i) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Pic_");
            sb.append(i);
            ((List)list).add((Object)new KeyValue(sb.toString(), new File(((LocalMedia)((List)data).get(i)).getRealPath())));
        }
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        build.request(params, (RequestCallBack)new FeedbackActivity$1(this));
    }
    
    private void submitSuccess() {
        this.etOpinion.setText((CharSequence)"");
        this.etContactInfo.setText((CharSequence)"");
        this.clearCache();
        this.finish();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == -1 && n == 188) {
            final ArrayList obtainSelectorList = PictureSelector.obtainSelectorList(intent);
            for (final LocalMedia localMedia : obtainSelectorList) {
                if (localMedia.getWidth() == 0 || localMedia.getHeight() == 0) {
                    if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                        final MediaExtraInfo imageSize = MediaUtils.getImageSize((Context)this, localMedia.getPath());
                        localMedia.setWidth(imageSize.getWidth());
                        localMedia.setHeight(imageSize.getHeight());
                    }
                    else {
                        if (!PictureMimeType.isHasVideo(localMedia.getMimeType())) {
                            continue;
                        }
                        final MediaExtraInfo videoSize = MediaUtils.getVideoSize((Context)this, localMedia.getPath());
                        localMedia.setWidth(videoSize.getWidth());
                        localMedia.setHeight(videoSize.getHeight());
                    }
                }
            }
            this.runOnUiThread((Runnable)new _$$Lambda$FeedbackActivity$9g8DR8PODxIYvgrVasPq2XWSZGc(this, (List)obtainSelectorList));
        }
    }
    
    public void onAddPicClick() {
        this.choosePic();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820833);
        this.setContentView(2131492904);
        this.initView();
        this.mAdapter = new GridImageAdapter((Context)this, (GridImageAdapter$OnAddPicClickListener)this);
        if (bundle != null && bundle.getParcelableArrayList("selectorList") != null) {
            this.mAdapter.setList(bundle.getParcelableArrayList("selectorList"));
        }
        this.mAdapter.setSelectMax(6);
        this.recycler.setAdapter((RecyclerView$Adapter)this.mAdapter);
        this.mAdapter.setOnItemClickListener((OnItemClickListener)new _$$Lambda$FeedbackActivity$vAktFqPqy6BwtpBkN9q6yUfaF40(this));
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final GridImageAdapter mAdapter = this.mAdapter;
        if (mAdapter != null && mAdapter.getData() != null && this.mAdapter.getData().size() > 0) {
            bundle.putParcelableArrayList("selectorList", this.mAdapter.getData());
        }
    }
}
