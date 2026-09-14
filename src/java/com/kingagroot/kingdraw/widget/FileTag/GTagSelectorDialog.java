package com.kingagroot.kingdraw.widget.FileTag;

import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.goodsrc.library.utils.ScreenUtils;
import android.os.Bundle;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.fileTag.AddFileTagActivity;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.view.View;
import java.util.Iterator;
import com.kingagroot.kingdraw.model.GTagModel;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.goodsrc.library.utils.StringUtils;
import android.view.View$OnClickListener;
import java.util.Objects;
import android.widget.Button;
import java.util.Collection;
import java.util.ArrayList;
import android.widget.TextView;
import java.util.List;
import android.widget.LinearLayout;
import android.content.Context;
import androidx.appcompat.app.AppCompatDialog;

public class GTagSelectorDialog extends AppCompatDialog
{
    private final Context context;
    private FileTagGroup fileAllTags;
    private FileTagGroup fileSelectedTags;
    private LinearLayout llEmptyView;
    private OnTagFilterViewListener onTagFilterViewListener;
    private final List<String> selectedTags;
    String title;
    private TextView tvTagMore;
    private TextView tvTagSelected;
    private TextView tvTitle;
    
    public GTagSelectorDialog(final Context context, final List<String> list, final String title) {
        super(context, 2131886326);
        final ArrayList selectedTags = new ArrayList();
        this.selectedTags = (List<String>)selectedTags;
        this.context = context;
        if (list != null) {
            ((List)selectedTags).addAll((Collection)list);
        }
        this.title = title;
    }
    
    private void initData() {
        this.initAllTags();
        this.setSelectTagModels();
        this.tvTitle.setText((CharSequence)this.title);
    }
    
    private void initView() {
        final Button button = (Button)this.findViewById(2131296413);
        this.tvTitle = (TextView)this.findViewById(2131297675);
        final Button button2 = (Button)this.findViewById(2131296418);
        this.tvTagSelected = (TextView)this.findViewById(2131297667);
        this.fileSelectedTags = (FileTagGroup)this.findViewById(2131296669);
        this.tvTagMore = (TextView)this.findViewById(2131297666);
        this.fileAllTags = (FileTagGroup)this.findViewById(2131296668);
        this.llEmptyView = (LinearLayout)this.findViewById(2131296995);
        final Button button3 = (Button)this.findViewById(2131296410);
        this.fileSelectedTags.setOnTagClickListener((OnTagClickListener)new _$$Lambda$GTagSelectorDialog$YgWauY_YTwG5FyLdAj9MXCHd4wI(this));
        this.fileAllTags.setOnTagClickListener((OnTagClickListener)new _$$Lambda$GTagSelectorDialog$ANUDn_q6hXqpEMd_SglDcFH0NE4(this));
        ((Button)Objects.requireNonNull((Object)button2)).setOnClickListener((View$OnClickListener)new _$$Lambda$GTagSelectorDialog$19ImT4x2IPVZo0J7jj_AAT6Hffk(this));
        this.fileAllTags.setAddTagViewRes(2131231109, 70, 28, this.context.getString(2131821447));
        this.fileAllTags.appendAddTag((OnAddTagClickListener)new _$$Lambda$GTagSelectorDialog$ETOWbz1VQ1sBbaKRyHlwXV2RAlE(this));
        ((Button)Objects.requireNonNull((Object)button)).setOnClickListener((View$OnClickListener)new _$$Lambda$GTagSelectorDialog$3m_8SEay6R7EnGrXE5ZImruzQTo(this));
        ((Button)Objects.requireNonNull((Object)button3)).setOnClickListener((View$OnClickListener)new _$$Lambda$GTagSelectorDialog$oTV28lBOhWAFdDyrE4lCmUpam54(this));
    }
    
    private void setMoreTagNum() {
        this.tvTagMore.setText((CharSequence)StringUtils.format(this.context.getString(2131821457), new Object[] { this.fileAllTags.getChildCount() - 1 }));
    }
    
    private void setSelectTagModels() {
        this.fileSelectedTags.appendTags((List)this.selectedTags, FileTagType.delete);
        this.setSelectedTagNum();
    }
    
    private void setSelectedTagNum() {
        this.tvTagSelected.setText((CharSequence)StringUtils.format(this.context.getString(2131821458), new Object[] { this.fileSelectedTags.getChildCount() }));
        this.selectedTags.clear();
        this.selectedTags.addAll((Collection)this.fileSelectedTags.getTags());
    }
    
    public void initAllTags() {
        final TagModelDBImpl tagModelDBImpl = new TagModelDBImpl();
        final List<String> selectedTags = this.selectedTags;
        if (selectedTags != null && selectedTags.size() > 0) {
            this.llEmptyView.setVisibility(8);
            final List unSelectTagModels = ((TagModelDBI)tagModelDBImpl).getUnSelectTagModels((List)this.selectedTags);
            this.fileAllTags.clearTags();
            if (unSelectTagModels != null && unSelectTagModels.size() > 0) {
                final Iterator iterator = unSelectTagModels.iterator();
                while (iterator.hasNext()) {
                    this.fileAllTags.appendTag((CharSequence)((GTagModel)iterator.next()).getName(), FileTagType.add);
                }
            }
        }
        else {
            final List unSelectTagModels2 = ((TagModelDBI)tagModelDBImpl).getUnSelectTagModels((List)this.selectedTags);
            this.fileAllTags.clearTags();
            if (unSelectTagModels2 != null && unSelectTagModels2.size() > 0) {
                final Iterator iterator2 = unSelectTagModels2.iterator();
                while (iterator2.hasNext()) {
                    this.fileAllTags.appendTag((CharSequence)((GTagModel)iterator2.next()).getName(), FileTagType.add);
                }
                this.llEmptyView.setVisibility(8);
            }
            else {
                this.llEmptyView.setVisibility(0);
            }
        }
        this.setMoreTagNum();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493020);
        this.getWindow().setLayout(-1, ScreenUtils.getScreenHeight(this.context) - GDensityUtil.dp2px(60.0f));
        this.getWindow().setGravity(80);
        this.getWindow().setWindowAnimations(2131886852);
        this.initView();
        this.initData();
        this.setCanceledOnTouchOutside(true);
    }
    
    public void setOnTagFilterViewListener(final OnTagFilterViewListener onTagFilterViewListener) {
        this.onTagFilterViewListener = onTagFilterViewListener;
    }
    
    public void updateSelectedTags(final List<String> list) {
        this.selectedTags.clear();
        if (list != null) {
            this.selectedTags.addAll((Collection)list);
        }
        this.fileSelectedTags.clearTags();
        this.setSelectTagModels();
    }
}
