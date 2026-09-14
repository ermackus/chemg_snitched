package com.kingagroot.kingdraw.ui.fileTag;

import com.kingagroot.kingdraw.interfaces.KingDrawFileDataI;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Bundle;
import com.kingagroot.kingdraw.interfaces.impl.KingDrawFileDataImpl;
import java.util.Collection;
import java.io.Serializable;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.AdapterView$OnItemClickListener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.widget.ListAdapter;
import android.content.Context;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import com.goodsrc.ui.library.widget.RefreshLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Handler;
import com.kingagroot.kingdraw.model.GTagModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import java.util.List;
import com.goodsrc.ui.library.ToolBarActivity;

public class FileTagListActivity extends ToolBarActivity
{
    private static final int MSG_WHAT_REFRESH_LIST = 10;
    private static final int REQUEST_CODE_ADD_TAG = 1003;
    private static final int REQUEST_CODE_EDIT_TAG = 1003;
    private final List<Long> TagFileCount;
    private CommonAdapter<GTagModel> adapter;
    private final List<GTagModel> gTagModels;
    private final Handler handler;
    private ListView listView;
    private LinearLayout llEmptyView;
    private RefreshLayout refresh;
    private TagModelDBI tagmodeldbi;
    
    public FileTagListActivity() {
        this.gTagModels = (List<GTagModel>)new ArrayList();
        this.TagFileCount = (List<Long>)new ArrayList();
        this.handler = (Handler)new FileTagListActivity$1(this);
    }
    
    private void initData() {
        this.tagmodeldbi = (TagModelDBI)new TagModelDBImpl();
        final FileTagListActivity$2 fileTagListActivity$2 = new FileTagListActivity$2(this, (Context)this, (List)this.gTagModels, 2131492953);
        this.adapter = (CommonAdapter<GTagModel>)fileTagListActivity$2;
        this.listView.setAdapter((ListAdapter)fileTagListActivity$2);
        this.refreshData();
    }
    
    private void initView() {
        this.listView = (ListView)this.findViewById(2131296980);
        (this.refresh = (RefreshLayout)this.findViewById(2131297261)).setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$FileTagListActivity$IFEnOWz50euPX6hvpMfvrJUhhYk(this));
        this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$FileTagListActivity$M7HHZk1FgdA8lI7LT4pFIbb_Fic(this));
        this.llEmptyView = (LinearLayout)this.findViewById(2131296995);
        ((Button)this.findViewById(2131296410)).setOnClickListener((View$OnClickListener)new _$$Lambda$FileTagListActivity$kKAX3medmLeWbW5bEFMEKLY_F10(this));
    }
    
    private void refreshData() {
        new Thread((Runnable)new _$$Lambda$FileTagListActivity$MfWAWmeTmL_vXW_KQGQC7Qy2_mk(this)).start();
    }
    
    private void toAddTag() {
        this.startActivityForResult(new Intent((Context)this, (Class)AddFileTagActivity.class), 1003);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1003) {
            if (intent != null) {
                this.refreshData();
            }
            this.listView.smoothScrollToPosition(0);
        }
        else if (n == 1003 && intent != null) {
            this.refreshData();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820582);
        this.setContentView(2131492906);
        this.initView();
        this.initData();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        final MenuItem add = menu.add(0, 0, 0, (CharSequence)"");
        add.setIcon(2131231542);
        add.setShowAsAction(2);
        return true;
    }
    
    protected void onEmptyRefresh() {
        super.onEmptyRefresh();
        this.refreshData();
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.toAddTag();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
