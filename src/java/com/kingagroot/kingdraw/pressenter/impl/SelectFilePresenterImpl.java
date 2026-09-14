package com.kingagroot.kingdraw.pressenter.impl;

import android.text.TextUtils;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.base.MApplication;
import java.util.Collection;
import java.util.Iterator;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import java.util.ArrayList;
import com.kingagroot.kingdraw.pressenter.view.SelectFileView;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.pressenter.SelectFilePresenterI;

public class SelectFilePresenterImpl implements SelectFilePresenterI
{
    private final DrawFileDataI drawFileDataI;
    private final List<FolderFileModel> folderFileModels;
    private final List<FolderFileModel> historicalModel;
    private String ids;
    private int page;
    private String searchKey;
    SelectFileView selectFileView;
    
    public SelectFilePresenterImpl(final SelectFileView selectFileView, final List<FolderFileModel> list, final List<FolderFileModel> historicalModel) {
        this.searchKey = "";
        this.ids = "";
        this.folderFileModels = (List<FolderFileModel>)new ArrayList();
        this.selectFileView = selectFileView;
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.historicalModel = historicalModel;
        final StringBuilder sb = new StringBuilder();
        for (final FolderFileModel folderFileModel : historicalModel) {
            sb.append(",");
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("\"");
            sb2.append(folderFileModel.getId());
            sb2.append("\"");
            sb.append(sb2.toString());
        }
        for (final FolderFileModel folderFileModel2 : list) {
            sb.append(",");
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("\"");
            sb3.append(folderFileModel2.getId());
            sb3.append("\"");
            sb.append(sb3.toString());
        }
        if (sb.length() > 0) {
            sb.delete(0, 1);
        }
        this.ids = sb.toString();
    }
    
    public List<FolderFileModel> getFolderFileModels() {
        return this.folderFileModels;
    }
    
    public void onLoadMore() {
        this.selectFileView.onLoading();
        final int page = this.page + 1;
        this.page = page;
        final List drawFilesWithOutIds = this.drawFileDataI.findDrawFilesWithOutIds(this.searchKey, page, this.ids);
        if (drawFilesWithOutIds != null) {
            this.folderFileModels.addAll((Collection)drawFilesWithOutIds);
        }
        if (drawFilesWithOutIds != null && drawFilesWithOutIds.size() > 0) {
            this.selectFileView.notifyListView();
        }
        else {
            --this.page;
            ToastUtil.showShort((CharSequence)MApplication.getInstance().getString(2131821091));
        }
        this.selectFileView.onLoadFinish();
    }
    
    public void onRefresh(final String searchKey) {
        this.selectFileView.onLoading();
        this.page = 0;
        this.searchKey = searchKey;
        this.folderFileModels.clear();
        if (this.historicalModel != null) {
            if (TextUtils.isEmpty((CharSequence)this.searchKey)) {
                this.folderFileModels.addAll((Collection)this.historicalModel);
            }
            else {
                for (final FolderFileModel folderFileModel : this.historicalModel) {
                    if (folderFileModel.getFileNameNoExtension().contains((CharSequence)this.searchKey) || this.searchKey.contains((CharSequence)folderFileModel.getFileNameNoExtension())) {
                        this.folderFileModels.add((Object)folderFileModel);
                    }
                }
            }
        }
        final List drawFilesWithOutIds = this.drawFileDataI.findDrawFilesWithOutIds(this.searchKey, this.page, this.ids);
        if (drawFilesWithOutIds != null) {
            this.folderFileModels.addAll((Collection)drawFilesWithOutIds);
        }
        this.selectFileView.notifyListView();
        this.selectFileView.onLoadFinish();
    }
}
