package com.kingagroot.kingdraw.ui.fileTag;

import java.util.Iterator;
import java.io.Serializable;
import android.content.Intent;
import java.util.Map$Entry;
import android.view.Menu;
import android.view.View;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.AbsListView$OnScrollListener;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.pressenter.impl.SelectFilePresenterImpl;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import com.kingagroot.kingdraw.pressenter.SelectFilePresenterI;
import android.view.MenuItem;
import android.widget.GridView;
import java.util.List;
import android.widget.EditText;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import com.kingagroot.kingdraw.pressenter.view.SelectFileView;
import com.goodsrc.ui.library.ToolBarActivity;

public class SelectedFileActivity extends ToolBarActivity implements SelectFileView
{
    public static final String INTENT_DATA_DELETE_MODELS = "intent_data_delete_models";
    public static final String INTENT_DATA_MODELS = "intent_data_models";
    public static final String RESULT_DATAS = "result_datas";
    private CommonAdapter<FolderFileModel> adapter;
    private EditText etName;
    private List<FolderFileModel> folderFileModels;
    private GridView gridFile;
    MenuItem saveItem;
    SelectFilePresenterI selectFilePresenterI;
    private final HashMap<String, FolderFileModel> selectedModels;
    
    public SelectedFileActivity() {
        this.selectedModels = (HashMap<String, FolderFileModel>)new HashMap();
    }
    
    private void initData() {
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        final Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            final ArrayList list3 = (ArrayList)extras.getSerializable("intent_data_models");
            if (list3 != null) {
                list2.addAll((Collection)list3);
            }
            final ArrayList list4 = (ArrayList)extras.getSerializable("intent_data_delete_models");
            if (list4 != null) {
                ((List)list).addAll((Collection)list4);
            }
        }
        final SelectFilePresenterImpl selectFilePresenterI = new SelectFilePresenterImpl((SelectFileView)this, (List)list2, (List)list);
        this.selectFilePresenterI = (SelectFilePresenterI)selectFilePresenterI;
        this.folderFileModels = (List<FolderFileModel>)((SelectFilePresenterI)selectFilePresenterI).getFolderFileModels();
        final SelectedFileActivity$1 selectedFileActivity$1 = new SelectedFileActivity$1(this, (Context)this, (List)this.folderFileModels, 2131492955);
        this.adapter = (CommonAdapter<FolderFileModel>)selectedFileActivity$1;
        this.gridFile.setAdapter((ListAdapter)selectedFileActivity$1);
        this.gridFile.setOnScrollListener((AbsListView$OnScrollListener)new SelectedFileActivity$2(this));
        this.etName.addTextChangedListener((TextWatcher)new SelectedFileActivity$3(this));
        this.selectFilePresenterI.onRefresh("");
    }
    
    private void initView() {
        this.etName = (EditText)this.findViewById(2131296643);
        this.gridFile = (GridView)this.findViewById(2131296766);
        this.toolbar.setNavigationOnClickListener((View$OnClickListener)new _$$Lambda$SelectedFileActivity$_wBAKXecPyYRH9rAmLtejSluA0I(this));
    }
    
    private void onChangeCheckNum() {
        final int size = this.selectedModels.size();
        String title = this.getString(2131820588);
        if (size > 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(title);
            sb.append("(");
            sb.append(size);
            sb.append(")");
            title = sb.toString();
        }
        this.saveItem.setTitle((CharSequence)title);
    }
    
    public void notifyListView() {
        this.adapter.notifyDataSetChanged();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131821446);
        this.setContentView(2131492931);
        this.initView();
        this.initData();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        (this.saveItem = menu.add(0, 0, 0, (CharSequence)this.getString(2131820588))).setShowAsAction(2);
        this.onChangeCheckNum();
        return true;
    }
    
    public void onLoadFinish() {
        this.setRefreshing(false, true);
    }
    
    public void onLoading() {
        this.setRefreshing(true, true);
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            final ArrayList list = new ArrayList();
            final Iterator iterator = this.selectedModels.entrySet().iterator();
            while (iterator.hasNext()) {
                list.add((Object)((Map$Entry)iterator.next()).getValue());
            }
            final Intent intent = new Intent();
            intent.putExtra("result_datas", (Serializable)list);
            this.setResult(-1, intent);
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
