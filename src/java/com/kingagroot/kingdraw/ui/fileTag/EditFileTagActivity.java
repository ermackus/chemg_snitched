package com.kingagroot.kingdraw.ui.fileTag;

import android.view.MenuItem;
import android.view.Menu;
import android.content.DialogInterface;
import java.io.Serializable;
import android.view.View;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.component.ui.view.OperationState;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.library.utils.ToastUtil;
import android.text.TextUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.kingagroot.kingdraw.widget.GTagInputFilter;
import android.text.InputFilter;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.model.GTagModel;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import android.widget.ScrollView;
import com.kingagroot.kingdraw.widget.EditTagGridView;
import com.kingagroot.kingdraw.model.GSearchModel;
import android.widget.EditText;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import android.widget.Button;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import com.goodsrc.ui.library.ToolBarActivity;

public class EditFileTagActivity extends ToolBarActivity
{
    public static final String INTENT_DATA_TAG = "intent_data_tag";
    int FileId;
    String FileTag;
    private final int REQUEST_CODE_SELECT_FILE;
    CommonAdapter<FolderFileModel> adapter;
    ArrayList<FolderFileModel> addFolderModels;
    private Button btnSaveFile;
    ArrayList<FolderFileModel> deleteFolderModels;
    DrawFileDataI drawFileDataI;
    private EditText etTagName;
    ArrayList<FolderFileModel> folderFileModels;
    GSearchModel gSearchModel;
    private EditTagGridView gridFile;
    boolean isEdit;
    boolean isSelect;
    private ScrollView scrollView;
    TagModelDBI tagModelDBI;
    
    public EditFileTagActivity() {
        this.REQUEST_CODE_SELECT_FILE = 1001;
        this.gSearchModel = new GSearchModel();
        this.folderFileModels = (ArrayList<FolderFileModel>)new ArrayList();
        this.deleteFolderModels = (ArrayList<FolderFileModel>)new ArrayList();
        this.addFolderModels = (ArrayList<FolderFileModel>)new ArrayList();
    }
    
    private void addFileModels(final ArrayList<FolderFileModel> list) {
        if (list != null) {
            this.folderFileModels.addAll((Collection)list);
            this.addFolderModels.addAll((Collection)list);
            for (final FolderFileModel folderFileModel : list) {
                for (final FolderFileModel folderFileModel2 : this.deleteFolderModels) {
                    if (folderFileModel.getId().equals((Object)folderFileModel2.getId())) {
                        this.deleteFolderModels.remove((Object)folderFileModel2);
                        break;
                    }
                }
            }
        }
    }
    
    private void deleteFolderModels(final FolderFileModel folderFileModel) {
        for (final FolderFileModel folderFileModel2 : this.addFolderModels) {
            if (folderFileModel.getId().equals((Object)folderFileModel2.getId())) {
                this.addFolderModels.remove((Object)folderFileModel2);
                break;
            }
        }
        final Iterator iterator2 = this.deleteFolderModels.iterator();
        while (true) {
            while (iterator2.hasNext()) {
                if (((FolderFileModel)iterator2.next()).getId().equals((Object)folderFileModel.getId())) {
                    final boolean b = true;
                    if (!b) {
                        this.deleteFolderModels.add((Object)folderFileModel);
                    }
                    return;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    private void fileAddTag() {
        for (int i = 0; i < this.folderFileModels.size(); ++i) {
            this.drawFileDataI.addTag(((FolderFileModel)this.folderFileModels.get(i)).getId(), this.FileTag);
        }
    }
    
    private void fileChangeTag(final String s, final String s2) {
        for (int i = 0; i < this.folderFileModels.size(); ++i) {
            this.drawFileDataI.modifyTagByOldTag(((FolderFileModel)this.folderFileModels.get(i)).getId(), s, s2);
        }
    }
    
    private void fileRemoveTag(final List<FolderFileModel> list) {
        if (list != null) {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                this.drawFileDataI.removeTag(((FolderFileModel)iterator.next()).getId(), this.FileTag);
            }
        }
    }
    
    private void initData() {
        final Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            final GTagModel gTagModel = (GTagModel)extras.getSerializable("intent_data_tag");
            this.FileTag = gTagModel.getName();
            this.FileId = gTagModel.getId();
            this.isSelect = gTagModel.isSelect();
        }
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.tagModelDBI = (TagModelDBI)new TagModelDBImpl();
        final EditFileTagActivity$2 editFileTagActivity$2 = new EditFileTagActivity$2(this, (Context)this, (List)this.folderFileModels, 2131492954);
        this.adapter = (CommonAdapter<FolderFileModel>)editFileTagActivity$2;
        this.gridFile.setAdapter((ListAdapter)editFileTagActivity$2);
        this.etTagName.setText((CharSequence)this.FileTag);
        this.refreshData();
        this.setEditStatus(false);
        this.setEmptyView(2131821453);
        this.adapter.notifyDataSetChanged();
    }
    
    private void initView() {
        this.etTagName = (EditText)this.findViewById(2131296658);
        this.gridFile = (EditTagGridView)this.findViewById(2131296766);
        this.btnSaveFile = (Button)this.findViewById(2131296449);
        final Button button = (Button)this.findViewById(2131296409);
        final Button button2 = (Button)this.findViewById(2131296421);
        this.scrollView = (ScrollView)this.findViewById(2131297348);
        button2.setOnClickListener((View$OnClickListener)new _$$Lambda$EditFileTagActivity$_ijNre_N_t7PSWfBCPPxXEdH4QQ(this));
        this.btnSaveFile.setOnClickListener((View$OnClickListener)new _$$Lambda$EditFileTagActivity$WApqhOxuXPSwDrG3w_xwHgSq1Ec(this));
        button.setOnClickListener((View$OnClickListener)new _$$Lambda$EditFileTagActivity$Y_NrrZF6YGxLhmh1lgavFV5gsLA(this));
        this.etTagName.setFilters(new InputFilter[] { (InputFilter)new GTagInputFilter() });
        this.scrollView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new EditFileTagActivity$1(this));
    }
    
    private boolean isNull() {
        if (TextUtils.isEmpty((CharSequence)this.etTagName.getText().toString())) {
            ToastUtil.showShort((CharSequence)this.getString(2131820813));
            return true;
        }
        return false;
    }
    
    private boolean onBackCheck() {
        return !this.FileTag.equals((Object)this.etTagName.getText().toString()) || this.deleteFolderModels.size() > 0 || this.addFolderModels.size() > 0;
    }
    
    private void onDeleteFileTag() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524).setMessage(2131820769).setPositiveButton(2131820766, (DialogInterface$OnClickListener)new _$$Lambda$EditFileTagActivity$FZoIWYA6niRQHunn05Lz9tyvwck(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null);
        alertDialog$Builder.show().getButton(-2).setTextColor(-4408132);
    }
    
    private void onSaveDialogWarring() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524).setMessage(2131820753).setPositiveButton(2131821338, (DialogInterface$OnClickListener)new _$$Lambda$EditFileTagActivity$wHsy9OtKxtfdstUWDG6EClr9FsI(this)).setNegativeButton(2131821340, (DialogInterface$OnClickListener)new _$$Lambda$EditFileTagActivity$jgeJxWs84IdxhmIzLhsVWoCGoRY(this));
        alertDialog$Builder.show().getButton(-2).setTextColor(-4408132);
    }
    
    private void onSaveFileTag() {
        if (!this.isNull()) {
            this.fileRemoveTag((List<FolderFileModel>)this.deleteFolderModels);
            final String string = this.etTagName.getText().toString();
            if (this.FileTag.equals((Object)string)) {
                this.fileAddTag();
                this.setFinishData();
            }
            else {
                final OperationState updataTag = this.tagModelDBI.updataTag(this.FileId, string);
                if (updataTag.isSuccess) {
                    final String string2 = this.etTagName.getText().toString();
                    this.fileChangeTag(string2, this.FileTag);
                    this.FileTag = string2;
                    this.setFinishData();
                }
                ToastUtil.showShort((CharSequence)updataTag.getInfo());
            }
            this.sendTagChangeMsg();
        }
    }
    
    private void refreshData() {
        final ArrayList tags = new ArrayList();
        ((List)tags).add((Object)this.FileTag);
        this.gSearchModel.setTags((List)tags);
        final List checkAll = this.drawFileDataI.checkAll(this.gSearchModel);
        this.folderFileModels.clear();
        if (checkAll != null) {
            this.folderFileModels.addAll((Collection)checkAll);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    private void sendTagChangeMsg() {
        if (this.isSelect) {
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)this.getApplication());
            final Intent intent = new Intent();
            intent.setAction("file_tag_change");
            instance.sendBroadcast(intent);
        }
    }
    
    private void setEditStatus(final boolean isEdit) {
        this.isEdit = isEdit;
        if (isEdit) {
            this.btnSaveFile.setText(2131820781);
        }
        else {
            this.btnSaveFile.setText(2131820766);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    private void setFinishData() {
        this.setResult(-1, new Intent());
        this.finish();
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1001 && n2 == -1 && intent != null) {
            this.addFileModels((ArrayList<FolderFileModel>)intent.getSerializableExtra("result_datas"));
            this.setEditStatus(false);
            this.adapter.notifyDataSetChanged();
        }
    }
    
    public void onBackPressed() {
        if (this.onBackCheck()) {
            this.onSaveDialogWarring();
        }
        else {
            super.onBackPressed();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131821448);
        this.setContentView(2131492902);
        this.initView();
        this.initData();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, (CharSequence)this.getString(2131821338)).setShowAsAction(2);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.onSaveFileTag();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
