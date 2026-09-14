package com.kingagroot.kingdraw.ui.fileTag;

import android.view.MenuItem;
import android.view.Menu;
import android.os.Bundle;
import android.content.DialogInterface;
import java.io.Serializable;
import android.view.View;
import android.content.Intent;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.kingagroot.component.ui.view.OperationState;
import com.goodsrc.library.utils.ToastUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.widget.GTagInputFilter;
import android.text.InputFilter;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;
import java.util.List;
import android.content.Context;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import java.util.Iterator;
import java.util.Collection;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import android.widget.GridView;
import android.widget.EditText;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import java.util.ArrayList;
import android.widget.Button;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import com.goodsrc.ui.library.ToolBarActivity;

public class AddFileTagActivity extends ToolBarActivity
{
    private final int REQUEST_CODE_SELECT_FILE;
    private CommonAdapter<FolderFileModel> adapter;
    private Button btnDeleteFile;
    ArrayList<FolderFileModel> deleteFolderModels;
    private DrawFileDataI drawFileDataI;
    private EditText etTagName;
    private final ArrayList<FolderFileModel> folderFileModels;
    private GridView gridFile;
    boolean isEdit;
    private TagModelDBI tagmodeldbi;
    
    public AddFileTagActivity() {
        this.REQUEST_CODE_SELECT_FILE = 1001;
        this.folderFileModels = (ArrayList<FolderFileModel>)new ArrayList();
        this.deleteFolderModels = (ArrayList<FolderFileModel>)new ArrayList();
    }
    
    private void addFileModels(final ArrayList<FolderFileModel> list) {
        if (list != null) {
            this.folderFileModels.addAll((Collection)list);
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
    
    private void deleteFileModels(final FolderFileModel folderFileModel) {
        final Iterator iterator = this.deleteFolderModels.iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (((FolderFileModel)iterator.next()).getId().equals((Object)folderFileModel.getId())) {
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
    
    private void initData() {
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.tagmodeldbi = (TagModelDBI)new TagModelDBImpl();
        this.adapter = (CommonAdapter<FolderFileModel>)new AddFileTagActivity$1(this, (Context)this, (List)this.folderFileModels, 2131492954);
        this.setEmptyView(2131821453);
        this.gridFile.setAdapter((ListAdapter)this.adapter);
        this.adapter.notifyDataSetChanged();
    }
    
    private void initView() {
        this.etTagName = (EditText)this.findViewById(2131296658);
        this.gridFile = (GridView)this.findViewById(2131296766);
        this.btnDeleteFile = (Button)this.findViewById(2131296420);
        final Button button = (Button)this.findViewById(2131296409);
        this.btnDeleteFile.setOnClickListener((View$OnClickListener)new _$$Lambda$AddFileTagActivity$IE72X_4qVVRVj5H85WvZ4sjtk24(this));
        button.setOnClickListener((View$OnClickListener)new _$$Lambda$AddFileTagActivity$9_yaUmKqHjC77n6DO5HA_UOEJ7k(this));
        this.btnDeleteFile.setVisibility(8);
        this.etTagName.setFilters(new InputFilter[] { (InputFilter)new GTagInputFilter() });
    }
    
    private boolean isNull() {
        if (TextUtils.isEmpty((CharSequence)this.etTagName.getText().toString())) {
            ToastUtil.showShort((CharSequence)this.getString(2131820813));
            return true;
        }
        return false;
    }
    
    private boolean onBackCheck() {
        return !TextUtils.isEmpty((CharSequence)this.etTagName.getText().toString()) || this.folderFileModels.size() > 0;
    }
    
    private void onSaveData() {
        if (!this.isNull()) {
            final String string = this.etTagName.getText().toString();
            final OperationState addTag = this.tagmodeldbi.addTag(string);
            if (addTag.isSuccess) {
                this.saveFileTag(string);
                this.hidInput();
                this.setFinishData();
                this.finish();
            }
            ToastUtil.showShort((CharSequence)addTag.getInfo());
        }
    }
    
    private void onSaveDialogWarring() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524).setMessage(2131820753).setPositiveButton(2131821338, (DialogInterface$OnClickListener)new _$$Lambda$AddFileTagActivity$AxxmOO9fx4OFUALr_EtU2sLjWtg(this)).setNegativeButton(2131821340, (DialogInterface$OnClickListener)new _$$Lambda$AddFileTagActivity$sV_ms4iXOz0_8pSjUp0Ug4GQHg8(this));
        alertDialog$Builder.show().getButton(-2).setTextColor(-4408132);
    }
    
    private void saveFileTag(final String s) {
        for (int i = 0; i < this.folderFileModels.size(); ++i) {
            this.drawFileDataI.addTag(((FolderFileModel)this.folderFileModels.get(i)).getId(), s);
        }
    }
    
    private void setEditStatus(final boolean isEdit) {
        this.isEdit = isEdit;
        if (isEdit) {
            this.btnDeleteFile.setText(2131820781);
        }
        else {
            this.btnDeleteFile.setText(2131820766);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    private void setFinishData() {
        this.setResult(-1, new Intent());
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
        this.setTitle(2131821447);
        this.setContentView(2131492896);
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
            this.onSaveData();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
