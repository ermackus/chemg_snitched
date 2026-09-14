package com.luck.picture.lib.basic;

import android.text.TextUtils;
import com.luck.picture.lib.interfaces.OnQueryFilterListener;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnQueryDataSourceListener;
import android.app.Activity;
import com.luck.picture.lib.loader.LocalMediaLoader;
import android.content.Context;
import com.luck.picture.lib.loader.LocalMediaPageLoader;
import com.luck.picture.lib.loader.IBridgeMediaLoader;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;

public class PictureSelectionQueryModel
{
    private final SelectorConfig selectionConfig;
    private final PictureSelector selector;
    
    public PictureSelectionQueryModel(final PictureSelector selector, final int chooseMode) {
        this.selector = selector;
        this.selectionConfig = new SelectorConfig();
        SelectorProviders.getInstance().addSelectorConfigQueue(this.selectionConfig);
        this.selectionConfig.chooseMode = chooseMode;
    }
    
    public IBridgeMediaLoader buildMediaLoader() {
        final Activity activity = this.selector.getActivity();
        if (activity != null) {
            Object o;
            if (this.selectionConfig.isPageStrategy) {
                o = new LocalMediaPageLoader((Context)activity, this.selectionConfig);
            }
            else {
                o = new LocalMediaLoader((Context)activity, this.selectionConfig);
            }
            return (IBridgeMediaLoader)o;
        }
        throw new NullPointerException("Activity cannot be null");
    }
    
    public PictureSelectionQueryModel isBmp(final boolean isBmp) {
        this.selectionConfig.isBmp = isBmp;
        return this;
    }
    
    public PictureSelectionQueryModel isGif(final boolean isGif) {
        this.selectionConfig.isGif = isGif;
        return this;
    }
    
    public PictureSelectionQueryModel isPageStrategy(final boolean isPageStrategy) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        return this;
    }
    
    public PictureSelectionQueryModel isPageStrategy(final boolean isPageStrategy, final int n) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        final SelectorConfig selectionConfig = this.selectionConfig;
        int pageSize = n;
        if (n < 10) {
            pageSize = 60;
        }
        selectionConfig.pageSize = pageSize;
        return this;
    }
    
    public PictureSelectionQueryModel isPageStrategy(final boolean isPageStrategy, final int n, final boolean isFilterInvalidFile) {
        this.selectionConfig.isPageStrategy = isPageStrategy;
        final SelectorConfig selectionConfig = this.selectionConfig;
        int pageSize = n;
        if (n < 10) {
            pageSize = 60;
        }
        selectionConfig.pageSize = pageSize;
        this.selectionConfig.isFilterInvalidFile = isFilterInvalidFile;
        return this;
    }
    
    public PictureSelectionQueryModel isWebp(final boolean isWebp) {
        this.selectionConfig.isWebp = isWebp;
        return this;
    }
    
    public void obtainAlbumData(final OnQueryDataSourceListener<LocalMediaFolder> onQueryDataSourceListener) {
        final Activity activity = this.selector.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity cannot be null");
        }
        if (onQueryDataSourceListener != null) {
            Object o;
            if (this.selectionConfig.isPageStrategy) {
                o = new LocalMediaPageLoader((Context)activity, this.selectionConfig);
            }
            else {
                o = new LocalMediaLoader((Context)activity, this.selectionConfig);
            }
            ((IBridgeMediaLoader)o).loadAllAlbum((OnQueryAllAlbumListener<LocalMediaFolder>)new PictureSelectionQueryModel$1(this, (OnQueryDataSourceListener)onQueryDataSourceListener));
            return;
        }
        throw new NullPointerException("OnQueryDataSourceListener cannot be null");
    }
    
    public void obtainMediaData(final OnQueryDataSourceListener<LocalMedia> onQueryDataSourceListener) {
        final Activity activity = this.selector.getActivity();
        if (activity == null) {
            throw new NullPointerException("Activity cannot be null");
        }
        if (onQueryDataSourceListener != null) {
            Object o;
            if (this.selectionConfig.isPageStrategy) {
                o = new LocalMediaPageLoader((Context)activity, this.selectionConfig);
            }
            else {
                o = new LocalMediaLoader((Context)activity, this.selectionConfig);
            }
            ((IBridgeMediaLoader)o).loadAllAlbum((OnQueryAllAlbumListener<LocalMediaFolder>)new PictureSelectionQueryModel$2(this, (IBridgeMediaLoader)o, (OnQueryDataSourceListener)onQueryDataSourceListener));
            return;
        }
        throw new NullPointerException("OnQueryDataSourceListener cannot be null");
    }
    
    public PictureSelectionQueryModel setFilterMaxFileSize(final long filterMaxFileSize) {
        if (filterMaxFileSize >= 1048576L) {
            this.selectionConfig.filterMaxFileSize = filterMaxFileSize;
        }
        else {
            this.selectionConfig.filterMaxFileSize = filterMaxFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionQueryModel setFilterMinFileSize(final long filterMinFileSize) {
        if (filterMinFileSize >= 1048576L) {
            this.selectionConfig.filterMinFileSize = filterMinFileSize;
        }
        else {
            this.selectionConfig.filterMinFileSize = filterMinFileSize * 1024L;
        }
        return this;
    }
    
    public PictureSelectionQueryModel setFilterVideoMaxSecond(final int n) {
        this.selectionConfig.filterVideoMaxSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionQueryModel setFilterVideoMinSecond(final int n) {
        this.selectionConfig.filterVideoMinSecond = n * 1000;
        return this;
    }
    
    public PictureSelectionQueryModel setQueryFilterListener(final OnQueryFilterListener onQueryFilterListener) {
        this.selectionConfig.onQueryFilterListener = onQueryFilterListener;
        return this;
    }
    
    public PictureSelectionQueryModel setQuerySortOrder(final String sortOrder) {
        if (!TextUtils.isEmpty((CharSequence)sortOrder)) {
            this.selectionConfig.sortOrder = sortOrder;
        }
        return this;
    }
}
