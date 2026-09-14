package com.kingagroot.kingdraw.widget.FileTag;

import java.util.Collection;
import java.util.Iterator;
import com.kingagroot.kingdraw.model.GTagModel;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.view.View$OnClickListener;
import android.content.IntentFilter;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.widget.TextView;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import java.util.List;
import android.content.BroadcastReceiver;
import android.widget.ImageButton;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;

public class GTagLocalFilterView extends LinearLayout implements GBaseTagFilterView
{
    private RelativeLayout RlTop;
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private FileTagGroup fileTags;
    private ImageButton imgbtnClear;
    private GTagLocalFilterView.GTagLocalFilterView$OnLocalFilterListner onLocalFilterListner;
    private OnLocalFilterViewAnimListener onLocalFilterViewAnimListener;
    BroadcastReceiver receiver;
    private LocalScrollView scrollView;
    private final List<String> selectedTags;
    private final TagModelDBI tagModelDBI;
    private GTagSelectorDialog tagSelectorDialog;
    private final FileTagType tagType;
    private TextView tvTagManage;
    
    public GTagLocalFilterView(final Context context) {
        this(context, null);
    }
    
    public GTagLocalFilterView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public GTagLocalFilterView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.tagType = FileTagType.delete;
        this.selectedTags = (List<String>)new ArrayList();
        this.receiver = (BroadcastReceiver)new GTagLocalFilterView$1(this);
        this.context = context;
        this.initView(View.inflate(context, 2131493091, (ViewGroup)this));
        this.tagModelDBI = (TagModelDBI)new TagModelDBImpl();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("file_tag_change");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        this.refreshData();
    }
    
    private void initView(final View view) {
        this.tvTagManage = (TextView)view.findViewById(2131297665);
        this.fileTags = (FileTagGroup)view.findViewById(2131296670);
        this.scrollView = (LocalScrollView)view.findViewById(2131297348);
        this.imgbtnClear = (ImageButton)view.findViewById(2131296885);
        this.fileTags.setAddTagViewRes(2131231148, 26, 26, (String)null);
        this.fileTags.appendAddTag((OnAddTagClickListener)new OnAddTagClickListener(this) {
            final GTagLocalFilterView this$0;
            
            public void onAdd(final View view) {
                final ArrayList tags = new ArrayList();
                this.this$0.setTags((List<String>)tags);
                if (this.this$0.onLocalFilterListner != null) {
                    this.this$0.onLocalFilterListner.onChangle((List)tags);
                }
            }
        });
        this.imgbtnClear.setOnClickListener((View$OnClickListener)new GTagLocalFilterView$3(this));
        this.fileTags.setOnTagClickListener((OnTagClickListener)new OnTagClickListener(this) {
            final GTagLocalFilterView this$0;
            
            public void onClick(final View view, final String s) {
                this.this$0.removeTag(s);
                if (this.this$0.onLocalFilterListner != null) {
                    this.this$0.onLocalFilterListner.onChangle(this.this$0.selectedTags);
                }
            }
        });
        this.tvTagManage.setOnClickListener((View$OnClickListener)new _$$Lambda$GTagLocalFilterView$pUzYM066wPR0Z_Z2ZaOPT_UUaBc(this));
    }
    
    private void setImagBtnClearVisibility() {
        if (this.selectedTags.size() <= 0) {
            this.hide();
        }
        else {
            this.show();
        }
    }
    
    public List<String> getTags() {
        return this.selectedTags;
    }
    
    public void hide() {
        if (!this.scrollView.isShrink()) {
            this.scrollView.setAnim(true);
            this.scrollView.clearAnimation();
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.scrollView, "height", new float[] { 1.0f, 0.0f });
            ofFloat.addListener((Animator$AnimatorListener)new GTagLocalFilterView$7(this));
            ofFloat.setDuration(150L);
            ofFloat.start();
        }
    }
    
    public void onDestroy() {
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public void onResume() {
        final GTagSelectorDialog tagSelectorDialog = this.tagSelectorDialog;
        if (tagSelectorDialog != null && tagSelectorDialog.isShowing()) {
            this.tagSelectorDialog.initAllTags();
        }
    }
    
    public void refreshData() {
        this.selectedTags.clear();
        this.fileTags.clearTags();
        final List selectTagModels = this.tagModelDBI.getSelectTagModels();
        if (selectTagModels != null) {
            for (final GTagModel gTagModel : selectTagModels) {
                this.fileTags.appendTag((CharSequence)gTagModel.getName(), FileTagType.delete);
                this.selectedTags.add((Object)gTagModel.getName());
            }
        }
        this.scrollView.post((Runnable)new GTagLocalFilterView$5(this));
        this.setImagBtnClearVisibility();
    }
    
    public void removeTag(final String s) {
        this.fileTags.removeTag(s);
        this.selectedTags.remove((Object)s);
        this.tagModelDBI.removeTag(s);
        this.setImagBtnClearVisibility();
    }
    
    public void setOnLocalFilterListner(final GTagLocalFilterView.GTagLocalFilterView$OnLocalFilterListner onLocalFilterListner) {
        this.onLocalFilterListner = onLocalFilterListner;
    }
    
    public void setOnLocalFilterViewAnimListener(final OnLocalFilterViewAnimListener onLocalFilterViewAnimListener) {
        this.onLocalFilterViewAnimListener = onLocalFilterViewAnimListener;
    }
    
    public void setTags(final List<String> list) {
        this.fileTags.clearTags();
        this.selectedTags.clear();
        this.selectedTags.addAll((Collection)list);
        this.tagModelDBI.addSelectTagModels((List)list);
        this.fileTags.appendTags((List)list, this.tagType);
        this.scrollView.post((Runnable)new GTagLocalFilterView$6(this));
        this.setImagBtnClearVisibility();
    }
    
    public void show() {
        if (this.scrollView.isShrink()) {
            this.scrollView.setAnim(true);
            this.scrollView.clearAnimation();
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.scrollView, "height", new float[] { 0.0f, 1.0f });
            ofFloat.addListener((Animator$AnimatorListener)new GTagLocalFilterView$8(this));
            ofFloat.setDuration(150L);
            ofFloat.start();
        }
    }
}
