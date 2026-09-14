package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.kingdraw.core.data.SmilesMatch;
import android.os.Message;
import java.util.Iterator;
import org.xutils.ex.DbException;
import java.util.Collection;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.utils.FileTagUtils;
import android.text.TextUtils;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import android.os.Looper;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.OnSearchResultListener;
import android.os.Handler;
import com.kingagroot.kingdraw.model.GSearchModel;
import java.util.concurrent.ExecutorService;
import com.kingagroot.kingdraw.interfaces.FolderFileModelSearchDBI;

public class FolderFileModelSearchImpl extends BaseDBImpl implements FolderFileModelSearchDBI
{
    private static final int MSG_WHAT_DB = 1002;
    private static final int MSG_WHAT_INIT = 1003;
    int count;
    ExecutorService executorService;
    GSearchModel gSearchModel;
    private final Handler handler;
    String ids;
    private boolean isInitDataFinish;
    boolean isSearchFragement;
    boolean isSearching;
    private OnSearchResultListener onSearchResultListener;
    int pageIndex;
    private final List<FolderFileModelSearchImpl.FolderFileModelSearchImpl$SmilesModel> smilesModels;
    
    public FolderFileModelSearchImpl(final boolean isSearchFragement) {
        this.count = 0;
        this.handler = (Handler)new FolderFileModelSearchImpl$2(this, Looper.getMainLooper());
        this.gSearchModel = new GSearchModel();
        this.smilesModels = (List<FolderFileModelSearchImpl.FolderFileModelSearchImpl$SmilesModel>)new ArrayList();
        int n;
        if ((n = Runtime.getRuntime().availableProcessors() / 2) <= 0) {
            n = 1;
        }
        this.executorService = Executors.newFixedThreadPool(n);
        this.isSearchFragement = isSearchFragement;
        if (isSearchFragement) {
            this.initData();
        }
    }
    
    private void findDrawFiles(final GSearchModel gSearchModel, final int n, final String s) {
        final ArrayList list = new ArrayList();
        String key;
        if (TextUtils.isEmpty((CharSequence)gSearchModel.getKey())) {
            key = "";
        }
        else {
            key = gSearchModel.getKey();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM FolderFileModel");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(" WHERE FileName LIKE '%");
        sb2.append(key);
        sb2.append("%'");
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(" AND Id IN (");
        sb3.append(s);
        sb3.append(")");
        sb.append(sb3.toString());
        if (gSearchModel.getTags() != null) {
            for (final String s2 : gSearchModel.getTags()) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(" AND Tags LIKE '%");
                sb4.append(FileTagUtils.tagFormat(s2));
                sb4.append("%'");
                sb.append(sb4.toString());
            }
        }
        if (this.isSearchFragement && TextUtils.isEmpty((CharSequence)s)) {
            sb.append(" AND 1!=1");
        }
        sb.append(" order by ModifyTime desc");
        final StringBuilder sb5 = new StringBuilder();
        sb5.append(" LIMIT 20 OFFSET ");
        sb5.append(n * 20);
        sb.append(sb5.toString());
        try {
            try {
                final List allBySql = this.findAllBySql((Class)FolderFileModel.class, sb.toString());
                if (allBySql != null) {
                    ((List)list).addAll((Collection)allBySql);
                }
            }
            finally {}
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        this.searchFinish((List<FolderFileModel>)list);
        return;
        this.searchFinish((List<FolderFileModel>)list);
    }
    
    private void getIds() {
        final int size = this.smilesModels.size();
        int i = 0;
        this.count = 0;
        this.ids = "";
        while (i < size) {
            final FolderFileModelSearchImpl.FolderFileModelSearchImpl$SmilesModel folderFileModelSearchImpl$SmilesModel = (FolderFileModelSearchImpl.FolderFileModelSearchImpl$SmilesModel)this.smilesModels.get(i);
            if (!TextUtils.isEmpty((CharSequence)folderFileModelSearchImpl$SmilesModel.getSmiles()) && !folderFileModelSearchImpl$SmilesModel.getSmiles().equals((Object)"error")) {
                new FolderFileModelSearchImpl.FolderFileModelSearchImpl$matchThread(this).start(folderFileModelSearchImpl$SmilesModel, this.executorService, (FolderFileModelSearchImpl.FolderFileModelSearchImpl$onMatchListner)new FolderFileModelSearchImpl$3(this, size));
            }
            else {
                ++this.count;
            }
            ++i;
        }
    }
    
    private void initData() {
        this.reloadData();
    }
    
    private void searchFinish(final List<FolderFileModel> list) {
        this.isSearching = false;
        final OnSearchResultListener onSearchResultListener = this.onSearchResultListener;
        if (onSearchResultListener != null) {
            onSearchResultListener.onResult((List)list);
        }
    }
    
    private void sendDBMsg() {
        final Message message = new Message();
        message.what = 1002;
        message.obj = this.gSearchModel;
        message.arg1 = this.pageIndex;
        this.handler.sendMessage(message);
    }
    
    private void sendInitMsg() {
        final Message message = new Message();
        message.what = 1003;
        this.handler.sendMessage(message);
    }
    
    public void findDrawFiles(GSearchModel clone, final int pageIndex, final OnSearchResultListener onSearchResultListener) {
        this.onSearchResultListener = onSearchResultListener;
        this.pageIndex = pageIndex;
        this.isSearching = true;
        if (clone == null) {
            this.searchFinish((List<FolderFileModel>)new ArrayList());
        }
        else if (this.isSearchFragement && clone.isEmpty()) {
            this.gSearchModel = clone.clone();
            this.searchFinish((List<FolderFileModel>)new ArrayList());
        }
        else if (!TextUtils.isEmpty((CharSequence)clone.getSmiles()) && !clone.getSmiles().equals((Object)"error")) {
            if (!this.isInitDataFinish) {
                this.gSearchModel = clone.clone();
                return;
            }
            if (!clone.getSmiles().equals((Object)this.gSearchModel.getSmiles())) {
                clone = clone.clone();
                this.gSearchModel = clone;
                SmilesMatch.setQuerySmiles(clone.getSmiles());
                this.getIds();
            }
            else {
                this.gSearchModel = clone.clone();
                this.sendDBMsg();
            }
        }
        else {
            this.gSearchModel = clone.clone();
            try {
                final WhereBuilder b = WhereBuilder.b();
                String key;
                if (TextUtils.isEmpty((CharSequence)this.gSearchModel.getKey())) {
                    key = "";
                }
                else {
                    key = this.gSearchModel.getKey();
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("%");
                sb.append(key);
                sb.append("%");
                b.and("FileName", "LIKE", (Object)sb.toString());
                if (this.gSearchModel.getTags() != null) {
                    for (final String s : this.gSearchModel.getTags()) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("%");
                        sb2.append(FileTagUtils.tagFormat(s));
                        sb2.append("%");
                        b.and("Tags", "LIKE", (Object)sb2.toString());
                    }
                }
                this.searchFinish((List<FolderFileModel>)this.db.selector((Class)FolderFileModel.class).where(b).offset(pageIndex * 20).limit(20).orderBy("ModifyTime", true).findAll());
            }
            catch (final DbException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void reloadData() {
        this.isInitDataFinish = false;
        this.smilesModels.clear();
        this.executorService.submit((Runnable)new FolderFileModelSearchImpl$1(this));
    }
}
