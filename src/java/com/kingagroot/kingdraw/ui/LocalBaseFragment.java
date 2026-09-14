package com.kingagroot.kingdraw.ui;

import android.content.DialogInterface$OnDismissListener;
import com.kingagroot.kingdraw.dialog.FileExportDialog$OnItemClickListener;
import com.kingagroot.kingdraw.dialog.FileExportDialog;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import com.kingagroot.kingdraw.model.FileType;
import android.text.TextUtils;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import com.kingagroot.kingdraw.dialog.LogInHintDialog;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.limit.LimitPic$OnExportPicCheck;
import com.kingagroot.kingdraw.limit.LimitPic;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import com.kingagroot.kingdraw.pressenter.impl.LocalPresenterImpl;
import android.os.Bundle;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import com.kingagroot.kingdraw.utils.FileTagUtils;
import java.util.Objects;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import com.kingagroot.kingdraw.limit.LimitPalette;
import com.goodsrc.library.utils.ToastUtil;
import java.io.Serializable;
import android.content.Intent;
import com.kingagroot.kingdraw.palette.NewPaletteRotateActivity;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.widget.AdapterView;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.dialog.CurrencyDialog$onNoOnclickListener;
import com.kingagroot.kingdraw.dialog.CurrencyDialog$onYesOnclickListener;
import com.kingagroot.kingdraw.dialog.CurrencyDialog;
import android.view.View$OnClickListener;
import android.text.Html;
import com.kingagroot.kingdraw.config.ShareData;
import android.widget.ListView;
import com.kingagroot.kingdraw.widget.MListView$OnMListViewlistener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog$Builder;
import java.util.ArrayList;
import android.widget.TextView;
import com.kingagroot.kingdraw.widget.FileTag.GTagLocalFilterView;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import com.kingagroot.kingdraw.widget.GSwipeRefreshLayout;
import com.kingagroot.kingdraw.utils.ShareFile;
import com.kingagroot.kingdraw.pressenter.LocalPresenterI;
import android.view.View;
import com.kingagroot.kingdraw.widget.ListMenuPopwindow;
import com.kingagroot.kingdraw.widget.MListView;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.widget.BatchOperateView;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import com.kingagroot.kingdraw.widget.ListMenuPopwindow$OnListMenuPopListener;
import com.kingagroot.kingdraw.pressenter.view.LocalView;

public class LocalBaseFragment extends LocalView implements ListMenuPopwindow$OnListMenuPopListener
{
    private static final int REQUEST_CODE_DRAW_INFO = 1004;
    public CommonAdapter<FolderFileModel> adapterImg;
    public CommonAdapter<FolderFileModel> adapterList;
    public boolean adpaterViewRefresh;
    public String[] alertItems;
    public BatchOperateView batchView;
    public DrawFileDataI drawFileDataI;
    protected List<FolderFileModel> folderFileModels;
    public int ipos;
    public boolean isBatchMode;
    public MListView listData;
    public ListMenuPopwindow listMenuPopwindow;
    View llLocalEmpty;
    public LocalPresenterI localPresenterI;
    View rlSearchDataEmpty;
    private ShareFile shareFile;
    public GSwipeRefreshLayout swiperefreshlayout;
    public SynFileDBI synfiledbi;
    public GTagLocalFilterView tagFilterView;
    protected TextView tvNewBuild;
    
    public LocalBaseFragment() {
        this.adpaterViewRefresh = true;
        this.folderFileModels = (List<FolderFileModel>)new ArrayList();
    }
    
    private void DuplicateNameWarningDialog(final FolderFileModel folderFileModel, final String s) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.requireContext(), 2131886327);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.getString(2131821493), new Object[] { folderFileModel.getFullFileName(), folderFileModel.getFullFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setTitle((CharSequence)this.getString(2131821524)).setPositiveButton((CharSequence)this.getString(2131821316), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$ye1PtxRzOYTUPyXaWhJGCrITALA(this, folderFileModel)).setNegativeButton((CharSequence)this.getString(2131820960), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$PAhZC_zXOaXxwFcfWAh9dzIVTfo(this, folderFileModel, s)).setNeutralButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$fhm96Pg0rcmUW402DWGE7NkE85w(this));
        final AlertDialog show = alertDialog$Builder.show();
        show.setCanceledOnTouchOutside(false);
        show.setCancelable(false);
        show.getButton(-1).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
        show.getButton(-2).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
        show.getButton(-3).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
    }
    
    private void initSet() {
        final int width = this.requireActivity().getWindowManager().getDefaultDisplay().getWidth();
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.adapterImg = (CommonAdapter<FolderFileModel>)new LocalBaseFragment$1(this, (Context)this.getActivity(), (List)this.folderFileModels, 2131493074, width);
        final LocalBaseFragment$2 localBaseFragment$2 = new LocalBaseFragment$2(this, (Context)this.getActivity(), (List)this.folderFileModels, 2131493075);
        this.adapterList = (CommonAdapter<FolderFileModel>)localBaseFragment$2;
        this.listData.setAdapter((ListAdapter)localBaseFragment$2);
        this.listData.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$LocalBaseFragment$tbfNQhQIjtN5iANIA72WyXNS7uM(this));
        this.listData.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new _$$Lambda$LocalBaseFragment$nj8Xl9SZFa_mVCYG6ljiz71JG14(this));
        this.swiperefreshlayout.setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$oFFGtoq_Kgintjp_UjgfzzmBiQ8(this));
        this.listData.setOnMListViewlistener((MListView$OnMListViewlistener)new _$$Lambda$LocalBaseFragment$StsEmbw3DNsYbF7L6g_NlKCl_rc(this));
        (this.listMenuPopwindow = new ListMenuPopwindow(this.getContext(), this.alertItems, (ListView)this.listData)).setOnListMenuPopListener((ListMenuPopwindow$OnListMenuPopListener)this);
        this.setListMode(ShareData.getFolderMode());
        this.tvNewBuild.setText((CharSequence)Html.fromHtml(this.getString(2131821137)));
        this.tvNewBuild.setOnClickListener((View$OnClickListener)new _$$Lambda$LocalBaseFragment$e5879ocuuN73QrP2_7xpexCNa_A(this));
    }
    
    private void showDeleteDialog() {
        final FolderFileModel folderFileModel = (FolderFileModel)this.folderFileModels.get(this.ipos);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.requireActivity(), 2131886327);
        alertDialog$Builder.setTitle(2131821524);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.getString(2131820773), new Object[] { folderFileModel.getFullFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setPositiveButton((CharSequence)this.getString(2131820766), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$MU1nj7nGfuW2waXNdoxDWSzbmpk(this, folderFileModel));
        alertDialog$Builder.setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)null);
        alertDialog$Builder.show();
    }
    
    private void showEditNameDialog() {
        final FolderFileModel folderFileModel = (FolderFileModel)this.folderFileModels.get(this.ipos);
        final CurrencyDialog currencyDialog = new CurrencyDialog((Context)this.requireActivity(), 2131886327, 1);
        currencyDialog.setTitle(this.getString(2131821314));
        currencyDialog.setName(folderFileModel.getFileNameNoExtension());
        currencyDialog.setYesOnclickListener(this.getString(2131820661), (CurrencyDialog$onYesOnclickListener)new _$$Lambda$dJxNDagy1OVnRJ8HrDwKti5gzjo(currencyDialog));
        currencyDialog.setNoOnclickListener(this.getString(2131820588), (CurrencyDialog$onNoOnclickListener)new _$$Lambda$LocalBaseFragment$mAbDYMr6OG_VshG3HSEAjdd__VE(this, currencyDialog, folderFileModel));
        currencyDialog.show();
    }
    
    private void showUploadDialog(final FolderFileModel folderFileModel) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.requireContext(), 2131886327);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.getString(2131820776), new Object[] { folderFileModel.getFullFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setTitle(2131821524).setPositiveButton((CharSequence)this.getString(2131820588), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$hVkBJiAUlI_IuVU7IV24529eH3U(this, folderFileModel)).setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)new _$$Lambda$LocalBaseFragment$bWiwdH1UH_tMs17l4OU07dxK7nk(this));
        final AlertDialog show = alertDialog$Builder.show();
        show.setCanceledOnTouchOutside(false);
        show.setCancelable(false);
        show.getButton(-1).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
        show.getButton(-2).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
        show.getButton(-3).setTextColor(ContextCompat.getColor((Context)this.requireActivity(), 2131099773));
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
        this.adapterImg.notifyDataSetChanged();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1004 && n2 == -1 && intent != null) {
            final FolderFileModel folderFileModel = (FolderFileModel)intent.getSerializableExtra(InfoFileActivity.KEY_MODEL);
            if (!FileTagUtils.fileTagContainTags(((FolderFileModel)Objects.requireNonNull((Object)folderFileModel)).getTags(), this.tagFilterView.getTags())) {
                this.folderFileModels.remove(this.ipos);
            }
            else {
                ((FolderFileModel)this.folderFileModels.get(this.ipos)).setTags(folderFileModel.getTags());
            }
            this.notifyListView();
        }
    }
    
    protected void onAdapterConvert(final ViewHolder viewHolder, final FolderFileModel folderFileModel) {
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.alertItems = new String[8])[0] = this.getString(2131821496);
        this.alertItems[1] = this.getString(2131821339);
        this.alertItems[2] = this.getString(2131820824);
        this.alertItems[3] = this.getString(2131820860);
        this.alertItems[4] = this.getString(2131820842);
        this.alertItems[5] = this.getString(2131821314);
        this.alertItems[6] = this.getString(2131820736);
        this.alertItems[7] = this.getString(2131820766);
        this.setHasOptionsMenu(true);
        final LocalPresenterImpl localPresenterI = new LocalPresenterImpl((LocalView)this);
        this.localPresenterI = (LocalPresenterI)localPresenterI;
        this.folderFileModels = (List<FolderFileModel>)((LocalPresenterI)localPresenterI).getDatas();
        this.synfiledbi = (SynFileDBI)new SynFileDBImpl();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493038, viewGroup, false);
        this.rlSearchDataEmpty = inflate.findViewById(2131297310);
        this.llLocalEmpty = inflate.findViewById(2131297007);
        this.listData = (MListView)inflate.findViewById(2131296974);
        this.swiperefreshlayout = (GSwipeRefreshLayout)inflate.findViewById(2131297437);
        this.batchView = (BatchOperateView)inflate.findViewById(2131296362);
        this.listData.setVerticalScrollBarEnabled(false);
        this.tagFilterView = (GTagLocalFilterView)inflate.findViewById(2131296671);
        this.tvNewBuild = (TextView)inflate.findViewById(2131297628);
        this.tagFilterView.setVisibility(8);
        this.batchView.setVisibility(8);
        this.initSet();
        return inflate;
    }
    
    public void onDestroyView() {
        this.unregisterForContextMenu((View)this.listData);
        super.onDestroyView();
    }
    
    public void onMenuItemSelect(final int n) {
        switch (n) {
            case 7: {
                this.showDeleteDialog();
                break;
            }
            case 6: {
                new LimitPalette((LimitPalette$OnJumpPalette)new LocalBaseFragment$4(this)).jumpPaletteCheck((Context)this.getActivity());
                break;
            }
            case 5: {
                this.showEditNameDialog();
                break;
            }
            case 4: {
                final Intent intent = new Intent(this.getContext(), (Class)InfoFileActivity.class);
                intent.putExtra(InfoFileActivity.KEY_MODEL, (Serializable)this.folderFileModels.get(this.ipos));
                this.startActivityForResult(intent, 1004);
                break;
            }
            case 3: {
                if (this.shareFile == null) {
                    this.shareFile = new ShareFile(this.requireContext());
                }
                this.shareFile.shareFile((FolderFileModel)this.folderFileModels.get(this.ipos));
                break;
            }
            case 2: {
                this.showExportDialog(this.getContext(), (FolderFileModel)this.folderFileModels.get(this.ipos));
                break;
            }
            case 1: {
                new LimitPic((LimitPic$OnExportPicCheck)new _$$Lambda$LocalBaseFragment$ruYR3C0Ue5RF6laSq4iwQSJgASg(this)).checkExportPic((Context)this.getActivity(), this.getString(2131820976));
                break;
            }
            case 0: {
                if (MApplication.getInstance().isLogin()) {
                    this.loading();
                    this.localPresenterI.checkFile((FolderFileModel)this.folderFileModels.get(this.ipos));
                    break;
                }
                new LogInHintDialog(this.requireContext()).show();
                break;
            }
        }
    }
    
    public void onResume() {
        super.onResume();
        this.synfiledbi = (SynFileDBI)new SynFileDBImpl();
    }
    
    public void onStart() {
        this.requireActivity().invalidateOptionsMenu();
        super.onStart();
    }
    
    protected void refreshData() {
    }
    
    public void setListMode(final boolean b) {
        if (b) {
            this.listData.setAdapter((ListAdapter)this.adapterList);
            this.adapterList.notifyDataSetChanged();
        }
        else {
            this.listData.setAdapter((ListAdapter)this.adapterImg);
            this.adapterImg.notifyDataSetChanged();
        }
    }
    
    public void showExportDialog(final Context context, final FolderFileModel folderFileModel) {
        String filename;
        if (folderFileModel == null) {
            filename = DrawFileUtil.getAutoName();
        }
        else {
            filename = this.drawFileDataI.getCopyFileNameNoExtension(folderFileModel.getFullFileName());
        }
        final String fileExtension = ((FolderFileModel)Objects.requireNonNull((Object)folderFileModel)).getFileExtension();
        FileType fileType;
        if (!TextUtils.isEmpty((CharSequence)fileExtension) && fileExtension.equals((Object)FileType.MOL_V2000.extension)) {
            if (ProtocolUtils.isMolV2000File(folderFileModel.getFilePath())) {
                fileType = FileType.MOL_V2000;
            }
            else {
                fileType = FileType.MOL_V3000;
            }
        }
        else {
            fileType = FileType.valueByExt(folderFileModel.getFileExtension());
        }
        final FileExportDialog fileExportDialog = new FileExportDialog(context, filename, fileType);
        fileExportDialog.show();
        fileExportDialog.setFilename(filename);
        fileExportDialog.setOnItemClickListener((FileExportDialog$OnItemClickListener)new LocalBaseFragment$5(this, folderFileModel, context));
        fileExportDialog.setOnDismissListener((DialogInterface$OnDismissListener)new _$$Lambda$LocalBaseFragment$lUhaJZkKG7mjdpXUN0_Yo6MhMfA(this));
    }
    
    public void upFile(final boolean b, final String s) {
        final FolderFileModel folderFileModel = (FolderFileModel)((FolderFileModel)this.folderFileModels.get(this.ipos)).clone();
        if (b) {
            this.showUploadDialog(folderFileModel);
        }
        else {
            this.DuplicateNameWarningDialog(folderFileModel, s);
        }
    }
}
