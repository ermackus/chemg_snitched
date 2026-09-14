package com.kingagroot.kingdraw.ui;

import java.io.Serializable;
import com.kingagroot.kingdraw.utils.FileTagUtils;
import java.util.Collection;
import java.util.Iterator;
import com.kingagroot.kingdraw.model.GTagModel;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import android.os.Bundle;
import com.kingagroot.kingdraw.widget.FileTag.OnTagFilterViewListener;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import com.goodsrc.library.utils.DateTimeUtils;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.kingdraw.utils.ImageLoader;
import android.view.View$OnClickListener;
import java.util.Objects;
import java.util.ArrayList;
import android.widget.TextView;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import com.kingagroot.kingdraw.widget.FileTag.FileTagType;
import java.util.List;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.widget.FileTag.FileTagGroup;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.widget.FileTag.GTagSelectorDialog;
import com.kingagroot.kingdraw.widget.FileTag.GBaseTagFilterView;
import com.goodsrc.ui.library.ToolBarActivity;

public class InfoFileActivity extends ToolBarActivity implements GBaseTagFilterView
{
    public static String KEY_MODEL = "KEY_MODEL";
    private GTagSelectorDialog dialog;
    DrawFileDataI drawFileDataI;
    FileTagGroup fileTagGroup;
    FolderFileModel folderFileModel;
    String id;
    ImageView imgEditor;
    RelativeLayout rlNoFrags;
    private List<String> selectedTags;
    private final FileTagType tagType;
    TagModelDBI tagmodeldbi;
    TextView tvEdit;
    TextView tvFileFormat;
    TextView tvFileName;
    TextView tvFileRevise;
    TextView tvFileSize;
    TextView tvFileTime;
    
    public InfoFileActivity() {
        this.tagType = FileTagType.normal;
        this.selectedTags = (List<String>)new ArrayList();
    }
    
    private void init() {
        this.imgEditor = (ImageView)this.findViewById(2131296875);
        this.tvFileName = (TextView)this.findViewById(2131297588);
        this.tvFileSize = (TextView)this.findViewById(2131297590);
        this.tvFileFormat = (TextView)this.findViewById(2131297587);
        this.tvFileTime = (TextView)this.findViewById(2131297591);
        this.tvFileRevise = (TextView)this.findViewById(2131297589);
        this.tvEdit = (TextView)this.findViewById(2131297568);
        this.fileTagGroup = (FileTagGroup)this.findViewById(2131296670);
        this.rlNoFrags = (RelativeLayout)this.findViewById(2131297301);
        final FolderFileModel folderFileModel = (FolderFileModel)this.getIntent().getSerializableExtra(InfoFileActivity.KEY_MODEL);
        this.folderFileModel = folderFileModel;
        final FolderFileModel drawFile = this.drawFileDataI.findDrawFile(((FolderFileModel)Objects.requireNonNull((Object)folderFileModel)).getId());
        this.folderFileModel = drawFile;
        if (drawFile != null) {
            this.id = drawFile.getId();
            if (this.folderFileModel.getTags() != null && this.folderFileModel.getTagList().size() > 0) {
                this.selectedTags = (List<String>)this.folderFileModel.getTagList();
            }
            this.setFragsShow(this.selectedTags);
            this.setText(this.folderFileModel);
        }
        this.imgEditor.setOnClickListener((View$OnClickListener)new _$$Lambda$InfoFileActivity$4XcMO0Ye_HbGNXLD5DXWRfUwnVI(this));
        this.tvEdit.setOnClickListener((View$OnClickListener)new _$$Lambda$InfoFileActivity$t_EmdtwitHIB5YOymh_LjcQZhls(this));
    }
    
    private void setFragsShow(final List<String> list) {
        if (list != null && list.size() > 0) {
            this.rlNoFrags.setVisibility(8);
            this.fileTagGroup.setVisibility(0);
        }
        else {
            this.rlNoFrags.setVisibility(0);
            this.fileTagGroup.setVisibility(8);
        }
    }
    
    private void setText(final FolderFileModel folderFileModel) {
        ImageLoader.bind(this.imgEditor, folderFileModel.getPicPath());
        this.tvFileName.setText((CharSequence)folderFileModel.getFullFileName());
        final long fileSize = folderFileModel.getFileSize();
        if (fileSize <= 1000L) {
            this.tvFileSize.setText((CharSequence)StringUtils.format("%dB", new Object[] { folderFileModel.getFileSize() }));
        }
        else if (fileSize <= 10000L) {
            this.tvFileSize.setText((CharSequence)StringUtils.format("%dKB", new Object[] { folderFileModel.getFileSize() / 1024L }));
        }
        else {
            this.tvFileSize.setText((CharSequence)StringUtils.format("%sMB", new Object[] { StringUtils.format("%.2f", new Object[] { folderFileModel.getFileSize() / 1048576.0f }) }));
        }
        this.tvFileFormat.setText((CharSequence)folderFileModel.getFileExtension().replace((CharSequence)".", (CharSequence)""));
        this.tvFileTime.setText((CharSequence)DateTimeUtils.format(folderFileModel.getCreateTime(), "yyyy-MM-dd HH:mm"));
        this.tvFileRevise.setText((CharSequence)DateTimeUtils.format(folderFileModel.getModifyTime(), "yyyy-MM-dd HH:mm"));
        this.fileTagGroup.appendTags((List)this.selectedTags, this.tagType);
    }
    
    private void showPicDialog(final FolderFileModel folderFileModel) {
        final Intent intent = new Intent((Context)this, (Class)ImageDetailActivity.class);
        intent.putExtra("intent_path_url", folderFileModel.getPicPath());
        this.startActivity(intent);
    }
    
    public List<String> getTags() {
        return this.selectedTags;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820842);
        this.setContentView(2131492914);
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.tagmodeldbi = (TagModelDBI)new TagModelDBImpl();
        this.init();
    }
    
    protected void onResume() {
        super.onResume();
        final GTagSelectorDialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            this.dialog.initAllTags();
        }
    }
    
    public void refreshData() {
        this.selectedTags.clear();
        final List selectTagModels = this.tagmodeldbi.getSelectTagModels();
        if (selectTagModels != null) {
            for (final GTagModel gTagModel : selectTagModels) {
                this.fileTagGroup.appendTag((CharSequence)gTagModel.getName(), FileTagType.normal);
                this.selectedTags.add((Object)gTagModel.getName());
            }
        }
    }
    
    public void removeTag(final String s) {
        this.fileTagGroup.removeTag(s);
    }
    
    public void setTags(final List<String> list) {
        this.fileTagGroup.clearTags();
        this.selectedTags.clear();
        this.selectedTags.addAll((Collection)list);
        this.tagmodeldbi.addSelectTagModels((List)list);
        this.fileTagGroup.appendTags((List)list, this.tagType);
        if (list.size() > 0) {
            this.rlNoFrags.setVisibility(8);
            this.fileTagGroup.setVisibility(0);
        }
        else {
            this.rlNoFrags.setVisibility(0);
            this.fileTagGroup.setVisibility(8);
        }
        this.drawFileDataI.setTags(this.id, (List)list);
        this.folderFileModel.setTags(FileTagUtils.tagsFormat((List)list));
        final Intent intent = new Intent();
        intent.putExtra(InfoFileActivity.KEY_MODEL, (Serializable)this.folderFileModel);
        this.setResult(-1, intent);
    }
}
