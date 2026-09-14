package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import com.kingagroot.kingdraw.pressenter.impl.CloudPresenterImpl;
import android.os.Bundle;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import com.goodsrc.library.utils.StringUtils;
import android.text.TextUtils;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import android.widget.AdapterView;
import com.kingagroot.kingdraw.dialog.CurrencyDialog$onNoOnclickListener;
import com.kingagroot.kingdraw.dialog.CurrencyDialog$onYesOnclickListener;
import com.kingagroot.kingdraw.dialog.CurrencyDialog;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog$Builder;
import android.text.Html;
import com.kingagroot.kingdraw.widget.MListView$OnMListViewlistener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.view.View;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import android.widget.TextView;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.kingagroot.kingdraw.widget.MListView;
import com.kingagroot.kingdraw.widget.ListMenuPopwindow;
import android.widget.LinearLayout;
import java.util.List;
import com.kingagroot.kingdraw.pressenter.CloudPresenterI;
import com.kingagroot.kingdraw.widget.BatchOperateView;
import com.kingagroot.kingdraw.model.CloudFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import com.kingagroot.kingdraw.widget.ListMenuPopwindow$OnListMenuPopListener;
import com.kingagroot.kingdraw.pressenter.view.CloudView;

public class CloudBaseFragment extends CloudView implements ListMenuPopwindow$OnListMenuPopListener
{
    public CommonAdapter<CloudFileModel> adapterList;
    String[] alertItems;
    public BatchOperateView batchView;
    public CloudPresenterI cloudPresenterI;
    public List<CloudFileModel> drawFileModels;
    public LinearLayout emptyView;
    public int ipos;
    public boolean isBatchMode;
    public ListMenuPopwindow listMenuPopwindow;
    public MListView listview;
    public LinearLayout llEmptySearchCloud;
    public SwipeRefreshLayout swiperefreshlayout;
    public SynFileDBI synfiledbi;
    public TextView tvEmptySearchCloud;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public CloudBaseFragment() {
        this.alertItems = new String[3];
    }
    
    private void initData() {
        this.adapterList = (CommonAdapter<CloudFileModel>)new CloudBaseFragment$1(this, (Context)this.getActivity(), (List)this.drawFileModels, 2131493075);
        this.listview.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$CloudBaseFragment$4YwyCkaWzPSdffWKhJ8P7hdn6ls(this));
        this.listview.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new _$$Lambda$CloudBaseFragment$sAlkZVDkGCxHC8lz_VC78qs5jio(this));
        this.listview.setAdapter((ListAdapter)this.adapterList);
        (this.listMenuPopwindow = new ListMenuPopwindow(this.getContext(), this.alertItems, (ListView)this.listview)).setOnListMenuPopListener((ListMenuPopwindow$OnListMenuPopListener)this);
    }
    
    private void initView(final View view) {
        this.swiperefreshlayout = (SwipeRefreshLayout)view.findViewById(2131297437);
        this.listview = (MListView)view.findViewById(2131296981);
        this.emptyView = (LinearLayout)view.findViewById(2131296617);
        this.batchView = (BatchOperateView)view.findViewById(2131296362);
        final TextView textView = (TextView)view.findViewById(2131297575);
        this.llEmptySearchCloud = (LinearLayout)view.findViewById(2131296994);
        this.tvEmptySearchCloud = (TextView)view.findViewById(2131297577);
        this.swiperefreshlayout.setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$qzLZioPPZn5GFFZYTITTrmAl7Eg(this));
        this.listview.setOnMListViewlistener((MListView$OnMListViewlistener)new _$$Lambda$CloudBaseFragment$LVKV7qlaNWEz9o8p8br3yoyTug4(this));
        this.batchView.setVisibility(8);
        textView.setText((CharSequence)Html.fromHtml(this.getString(2131820702)));
    }
    
    private void showDeleteDialog() {
        if (this.ipos >= this.adapterList.getCount()) {
            return;
        }
        final CloudFileModel cloudFileModel = (CloudFileModel)this.adapterList.getItem(this.ipos);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.requireActivity(), 2131886327);
        alertDialog$Builder.setTitle(2131821524);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.getString(2131820773), new Object[] { cloudFileModel.getFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setPositiveButton((CharSequence)this.getString(2131820766), (DialogInterface$OnClickListener)new _$$Lambda$CloudBaseFragment$xMfeaEAvfVyRH7WUFCDkEqT8yio(this, cloudFileModel));
        alertDialog$Builder.setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)null);
        alertDialog$Builder.show();
    }
    
    private void showEditNameDialog() {
        if (this.ipos >= this.adapterList.getCount()) {
            return;
        }
        final CloudFileModel cloudFileModel = (CloudFileModel)this.adapterList.getItem(this.ipos);
        final CurrencyDialog currencyDialog = new CurrencyDialog((Context)this.requireActivity(), 2131886327, 1);
        currencyDialog.setTitle(this.getString(2131821314));
        currencyDialog.setName(cloudFileModel.getFileNameNoExtension());
        currencyDialog.setYesOnclickListener(this.getString(2131820661), (CurrencyDialog$onYesOnclickListener)new _$$Lambda$dJxNDagy1OVnRJ8HrDwKti5gzjo(currencyDialog));
        currencyDialog.setNoOnclickListener(this.getString(2131820588), (CurrencyDialog$onNoOnclickListener)new _$$Lambda$CloudBaseFragment$BXAau9BEvdGG9f4mwEy0eLIy2xo(this, currencyDialog, cloudFileModel));
        currencyDialog.show();
    }
    
    public void loading() {
        super.loading();
        this.swiperefreshlayout.setRefreshing(true);
    }
    
    public void loadingFinish() {
        super.loadingFinish();
        this.swiperefreshlayout.setRefreshing(false);
    }
    
    public void notifyListView() {
        super.notifyListView();
        this.adapterList.notifyDataSetChanged();
    }
    
    protected void onAdapterConvert(final ViewHolder viewHolder, final CloudFileModel cloudFileModel) {
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.alertItems[0] = this.getString(2131820787);
        this.alertItems[1] = this.getString(2131821314);
        this.alertItems[2] = this.getString(2131820766);
        final CloudPresenterImpl cloudPresenterI = new CloudPresenterImpl((CloudView)this);
        this.cloudPresenterI = (CloudPresenterI)cloudPresenterI;
        this.drawFileModels = (List<CloudFileModel>)((CloudPresenterI)cloudPresenterI).getDates();
        this.synfiledbi = (SynFileDBI)new SynFileDBImpl();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493036, viewGroup, false);
        this.initView(inflate);
        this.initData();
        return inflate;
    }
    
    public void onMenuItemSelect(final int n) {
        if (n != 0) {
            if (n != 1) {
                if (n == 2) {
                    this.showDeleteDialog();
                }
            }
            else {
                this.showEditNameDialog();
            }
        }
        else {
            if (this.ipos >= this.adapterList.getCount()) {
                return;
            }
            this.cloudPresenterI.downloadCheck((CloudFileModel)((CloudFileModel)this.adapterList.getItem(this.ipos)).clone());
        }
    }
    
    public void onResume() {
        super.onResume();
        this.synfiledbi = (SynFileDBI)new SynFileDBImpl();
    }
    
    protected void refreshData() {
    }
}
