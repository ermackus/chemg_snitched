package com.luck.picture.lib;

import com.luck.picture.lib.loader.LocalMediaLoader;
import com.luck.picture.lib.loader.LocalMediaPageLoader;
import com.luck.picture.lib.interfaces.OnRequestPermissionListener;
import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import com.luck.picture.lib.utils.ToastUtils;
import com.luck.picture.lib.config.InjectResourceSource;
import com.luck.picture.lib.utils.ValueOf;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.permissions.PermissionResultCallback;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.luck.picture.lib.permissions.PermissionChecker;
import java.util.Iterator;
import android.content.Context;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.basic.FragmentInjectManager;
import android.view.ViewGroup;
import com.luck.picture.lib.magical.BuildRecycleItemViewParams;
import android.os.Bundle;
import com.luck.picture.lib.config.SelectMimeType;
import android.text.TextUtils;
import com.luck.picture.lib.widget.TitleBar$OnTitleBarListener;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.animators.AlphaInAnimationAdapter;
import com.luck.picture.lib.animators.SlideInBottomAnimationAdapter;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.RecyclerView$ItemAnimator;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.utils.StyleUtils;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.RelativeLayout$LayoutParams;
import com.luck.picture.lib.utils.DensityUtil;
import androidx.constraintlayout.widget.ConstraintLayout$LayoutParams;
import com.luck.picture.lib.widget.BottomNavBar$OnBottomNavBarListener;
import com.luck.picture.lib.dialog.AlbumListPopWindow$OnPopupWindowStatusListener;
import java.util.Collection;
import java.io.File;
import android.app.Activity;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.luck.picture.lib.config.PictureMimeType;
import androidx.recyclerview.widget.RecyclerView$OnItemTouchListener;
import com.luck.picture.lib.widget.SlideSelectTouchListener$OnSlideSelectListener;
import com.luck.picture.lib.widget.SlideSelectionHandler;
import java.util.HashSet;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollListener;
import com.luck.picture.lib.interfaces.OnRecyclerViewScrollStateListener;
import com.luck.picture.lib.adapter.PictureImageGridAdapter$OnItemClickListener;
import com.luck.picture.lib.interfaces.OnAlbumItemClickListener;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.List;
import com.luck.picture.lib.loader.IBridgeMediaLoader;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import com.luck.picture.lib.config.SelectorConfig;
import android.widget.TextView;
import com.luck.picture.lib.widget.TitleBar;
import com.luck.picture.lib.widget.RecyclerPreloadView;
import com.luck.picture.lib.widget.SlideSelectTouchListener;
import com.luck.picture.lib.adapter.PictureImageGridAdapter;
import com.luck.picture.lib.widget.CompleteSelectView;
import com.luck.picture.lib.widget.BottomNavBar;
import com.luck.picture.lib.dialog.AlbumListPopWindow;
import com.luck.picture.lib.basic.IPictureSelectorEvent;
import com.luck.picture.lib.interfaces.OnRecyclerViewPreloadMoreListener;
import com.luck.picture.lib.basic.PictureCommonFragment;

public class PictureSelectorFragment extends PictureCommonFragment implements OnRecyclerViewPreloadMoreListener, IPictureSelectorEvent
{
    private static final Object LOCK;
    private static int SELECT_ANIM_DURATION;
    public static final String TAG;
    private AlbumListPopWindow albumListPopWindow;
    private int allFolderSize;
    private BottomNavBar bottomNarBar;
    private CompleteSelectView completeSelectView;
    private int currentPosition;
    private long intervalClickTime;
    private boolean isCameraCallback;
    private boolean isDisplayCamera;
    private boolean isMemoryRecycling;
    private PictureImageGridAdapter mAdapter;
    private SlideSelectTouchListener mDragSelectTouchListener;
    private RecyclerPreloadView mRecycler;
    private TitleBar titleBar;
    private TextView tvCurrentDataTime;
    private TextView tvDataEmpty;
    
    static {
        TAG = PictureSelectorFragment.class.getSimpleName();
        LOCK = new Object();
        PictureSelectorFragment.SELECT_ANIM_DURATION = 135;
    }
    
    public PictureSelectorFragment() {
        this.intervalClickTime = 0L;
        this.currentPosition = -1;
    }
    
    private void addAlbumPopWindowAction() {
        this.albumListPopWindow.setOnIBridgeAlbumWidget((OnAlbumItemClickListener)new PictureSelectorFragment$7(this));
    }
    
    private void addRecyclerAction() {
        this.mAdapter.setOnItemClickListener((PictureImageGridAdapter$OnItemClickListener)new PictureSelectorFragment$16(this));
        this.mRecycler.setOnRecyclerViewScrollStateListener((OnRecyclerViewScrollStateListener)new PictureSelectorFragment$17(this));
        this.mRecycler.setOnRecyclerViewScrollListener((OnRecyclerViewScrollListener)new PictureSelectorFragment$18(this));
        if (this.selectorConfig.isFastSlidingSelect) {
            final SlideSelectTouchListener withSelectListener = new SlideSelectTouchListener().setRecyclerViewHeaderCount((int)(this.mAdapter.isDisplayCamera() ? 1 : 0)).withSelectListener((SlideSelectTouchListener$OnSlideSelectListener)new SlideSelectionHandler((SlideSelectionHandler.SlideSelectionHandler$ISelectionHandler)new PictureSelectorFragment$19(this, new HashSet())));
            this.mDragSelectTouchListener = withSelectListener;
            this.mRecycler.addOnItemTouchListener((RecyclerView$OnItemTouchListener)withSelectListener);
        }
    }
    
    private void beginLoadData() {
        this.onPermissionExplainEvent(false, null);
        if (this.selectorConfig.isOnlySandboxDir) {
            this.loadOnlyInAppDirectoryAllMediaData();
        }
        else {
            this.loadAllAlbumData();
        }
    }
    
    private boolean checkNotifyStrategy(final boolean b) {
        final boolean isMaxSelectEnabledMask = this.selectorConfig.isMaxSelectEnabledMask;
        boolean b3;
        final boolean b2 = b3 = false;
        if (isMaxSelectEnabledMask) {
            if (this.selectorConfig.isWithVideoImage) {
                if (this.selectorConfig.selectionMode == 1) {
                    b3 = b2;
                    return b3;
                }
                if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum) {
                    b3 = b2;
                    if (b) {
                        return b3;
                    }
                    b3 = b2;
                    if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum - 1) {
                        return b3;
                    }
                }
            }
            else if (this.selectorConfig.getSelectCount() != 0) {
                if (!b || this.selectorConfig.getSelectCount() != 1) {
                    if (PictureMimeType.isHasVideo(this.selectorConfig.getResultFirstMimeType())) {
                        int n;
                        if (this.selectorConfig.maxVideoSelectNum > 0) {
                            n = this.selectorConfig.maxVideoSelectNum;
                        }
                        else {
                            n = this.selectorConfig.maxSelectNum;
                        }
                        if (this.selectorConfig.getSelectCount() != n) {
                            b3 = b2;
                            if (b) {
                                return b3;
                            }
                            b3 = b2;
                            if (this.selectorConfig.getSelectCount() != n - 1) {
                                return b3;
                            }
                        }
                    }
                    else if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum) {
                        b3 = b2;
                        if (b) {
                            return b3;
                        }
                        b3 = b2;
                        if (this.selectorConfig.getSelectCount() != this.selectorConfig.maxSelectNum - 1) {
                            return b3;
                        }
                    }
                }
            }
            b3 = true;
        }
        return b3;
    }
    
    private void handleAllAlbumData(final boolean b, final List<LocalMediaFolder> list) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        if (list.size() > 0) {
            LocalMediaFolder currentLocalMediaFolder;
            if (b) {
                currentLocalMediaFolder = (LocalMediaFolder)list.get(0);
                this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
            }
            else if (this.selectorConfig.currentLocalMediaFolder != null) {
                currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            }
            else {
                currentLocalMediaFolder = (LocalMediaFolder)list.get(0);
                this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
            }
            this.titleBar.setTitle(currentLocalMediaFolder.getFolderName());
            this.albumListPopWindow.bindAlbumData((List)list);
            if (this.selectorConfig.isPageStrategy) {
                if (this.selectorConfig.isPreloadFirst) {
                    this.mRecycler.setEnabledLoadMore(true);
                }
                else {
                    this.loadFirstPageMediaData(currentLocalMediaFolder.getBucketId());
                }
            }
            else {
                this.setAdapterData((ArrayList<LocalMedia>)currentLocalMediaFolder.getData());
            }
        }
        else {
            this.showDataNull();
        }
    }
    
    private void handleFirstPageMedia(final ArrayList<LocalMedia> adapterData, final boolean enabledLoadMore) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(enabledLoadMore);
        if (this.mRecycler.isEnabledLoadMore() && adapterData.size() == 0) {
            this.onRecyclerViewPreloadMore();
        }
        else {
            this.setAdapterData(adapterData);
        }
    }
    
    private void handleInAppDirAllMedia(final LocalMediaFolder currentLocalMediaFolder) {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            final String sandboxDir = this.selectorConfig.sandboxDir;
            final boolean b = currentLocalMediaFolder != null;
            String title;
            if (b) {
                title = currentLocalMediaFolder.getFolderName();
            }
            else {
                title = new File(sandboxDir).getName();
            }
            this.titleBar.setTitle(title);
            if (b) {
                this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
                this.setAdapterData((ArrayList<LocalMedia>)currentLocalMediaFolder.getData());
            }
            else {
                this.showDataNull();
            }
        }
    }
    
    private void handleMoreMediaData(final List<LocalMedia> list, final boolean enabledLoadMore) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(enabledLoadMore);
        if (this.mRecycler.isEnabledLoadMore()) {
            this.removePageCameraRepeatData(list);
            if (list.size() > 0) {
                final int size = this.mAdapter.getData().size();
                this.mAdapter.getData().addAll((Collection)list);
                final PictureImageGridAdapter mAdapter = this.mAdapter;
                mAdapter.notifyItemRangeChanged(size, mAdapter.getItemCount());
                this.hideDataNull();
            }
            else {
                this.onRecyclerViewPreloadMore();
            }
            if (list.size() < 10) {
                final RecyclerPreloadView mRecycler = this.mRecycler;
                mRecycler.onScrolled(mRecycler.getScrollX(), this.mRecycler.getScrollY());
            }
        }
    }
    
    private void handleRecoverAlbumData(final List<LocalMediaFolder> list) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        if (list.size() > 0) {
            LocalMediaFolder currentLocalMediaFolder;
            if (this.selectorConfig.currentLocalMediaFolder != null) {
                currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            }
            else {
                currentLocalMediaFolder = (LocalMediaFolder)list.get(0);
                this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
            }
            this.titleBar.setTitle(currentLocalMediaFolder.getFolderName());
            this.albumListPopWindow.bindAlbumData((List)list);
            if (this.selectorConfig.isPageStrategy) {
                this.handleFirstPageMedia((ArrayList<LocalMedia>)new ArrayList((Collection)this.selectorConfig.dataSource), true);
            }
            else {
                this.setAdapterData((ArrayList<LocalMedia>)currentLocalMediaFolder.getData());
            }
        }
        else {
            this.showDataNull();
        }
    }
    
    private void handleSwitchAlbum(final ArrayList<LocalMedia> adapterData, final boolean enabledLoadMore) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        this.mRecycler.setEnabledLoadMore(enabledLoadMore);
        if (adapterData.size() == 0) {
            this.mAdapter.getData().clear();
        }
        this.setAdapterData(adapterData);
        this.mRecycler.onScrolled(0, 0);
        this.mRecycler.smoothScrollToPosition(0);
    }
    
    private void hideCurrentMediaCreateTimeUI() {
        if (this.selectorConfig.isDisplayTimeAxis && this.mAdapter.getData().size() > 0) {
            this.tvCurrentDataTime.animate().setDuration(250L).alpha(0.0f).start();
        }
    }
    
    private void hideDataNull() {
        if (this.tvDataEmpty.getVisibility() == 0) {
            this.tvDataEmpty.setVisibility(8);
        }
    }
    
    private void initAlbumListPopWindow() {
        (this.albumListPopWindow = AlbumListPopWindow.buildPopWindow(this.getContext(), this.selectorConfig)).setOnPopupWindowStatusListener((AlbumListPopWindow$OnPopupWindowStatusListener)new PictureSelectorFragment$4(this));
        this.addAlbumPopWindowAction();
    }
    
    private void initBottomNavBar() {
        this.bottomNarBar.setBottomNavBarStyle();
        this.bottomNarBar.setOnBottomNavBarListener((BottomNavBar$OnBottomNavBarListener)new PictureSelectorFragment$8(this));
        this.bottomNarBar.setSelectedChange();
    }
    
    private void initComplete() {
        if (this.selectorConfig.selectionMode == 1 && this.selectorConfig.isDirectReturnSingle) {
            this.selectorConfig.selectorStyle.getTitleBarStyle().setHideCancelButton(false);
            this.titleBar.getTitleCancelView().setVisibility(0);
            this.completeSelectView.setVisibility(8);
        }
        else {
            this.completeSelectView.setCompleteSelectViewStyle();
            this.completeSelectView.setSelectedChange(false);
            if (this.selectorConfig.selectorStyle.getSelectMainStyle().isCompleteSelectRelativeTop()) {
                if (this.completeSelectView.getLayoutParams() instanceof ConstraintLayout$LayoutParams) {
                    ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topToTop = R$id.title_bar;
                    ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).bottomToBottom = R$id.title_bar;
                    if (this.selectorConfig.isPreviewFullScreenMode) {
                        ((ConstraintLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
                    }
                }
                else if (this.completeSelectView.getLayoutParams() instanceof RelativeLayout$LayoutParams && this.selectorConfig.isPreviewFullScreenMode) {
                    ((RelativeLayout$LayoutParams)this.completeSelectView.getLayoutParams()).topMargin = DensityUtil.getStatusBarHeight(this.getContext());
                }
            }
            this.completeSelectView.setOnClickListener((View$OnClickListener)new PictureSelectorFragment$2(this));
        }
    }
    
    private void initRecycler(final View view) {
        this.mRecycler = (RecyclerPreloadView)view.findViewById(R$id.recycler);
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        final int mainListBackgroundColor = selectMainStyle.getMainListBackgroundColor();
        if (StyleUtils.checkStyleValidity(mainListBackgroundColor)) {
            this.mRecycler.setBackgroundColor(mainListBackgroundColor);
        }
        else {
            this.mRecycler.setBackgroundColor(ContextCompat.getColor(this.getAppContext(), R$color.ps_color_black));
        }
        int imageSpanCount;
        if (this.selectorConfig.imageSpanCount <= 0) {
            imageSpanCount = 4;
        }
        else {
            imageSpanCount = this.selectorConfig.imageSpanCount;
        }
        if (this.mRecycler.getItemDecorationCount() == 0) {
            if (StyleUtils.checkSizeValidity(selectMainStyle.getAdapterItemSpacingSize())) {
                this.mRecycler.addItemDecoration((RecyclerView$ItemDecoration)new GridSpacingItemDecoration(imageSpanCount, selectMainStyle.getAdapterItemSpacingSize(), selectMainStyle.isAdapterItemIncludeEdge()));
            }
            else {
                this.mRecycler.addItemDecoration((RecyclerView$ItemDecoration)new GridSpacingItemDecoration(imageSpanCount, DensityUtil.dip2px(view.getContext(), 1.0f), selectMainStyle.isAdapterItemIncludeEdge()));
            }
        }
        this.mRecycler.setLayoutManager((RecyclerView$LayoutManager)new GridLayoutManager(this.getContext(), imageSpanCount));
        final RecyclerView$ItemAnimator itemAnimator = this.mRecycler.getItemAnimator();
        if (itemAnimator != null) {
            ((SimpleItemAnimator)itemAnimator).setSupportsChangeAnimations(false);
            this.mRecycler.setItemAnimator((RecyclerView$ItemAnimator)null);
        }
        if (this.selectorConfig.isPageStrategy) {
            this.mRecycler.setReachBottomRow(2);
            this.mRecycler.setOnRecyclerViewPreloadListener((OnRecyclerViewPreloadMoreListener)this);
        }
        else {
            this.mRecycler.setHasFixedSize(true);
        }
        (this.mAdapter = new PictureImageGridAdapter(this.getContext(), this.selectorConfig)).setDisplayCamera(this.isDisplayCamera);
        final int animationMode = this.selectorConfig.animationMode;
        if (animationMode != 1) {
            if (animationMode != 2) {
                this.mRecycler.setAdapter((RecyclerView$Adapter)this.mAdapter);
            }
            else {
                this.mRecycler.setAdapter((RecyclerView$Adapter)new SlideInBottomAnimationAdapter((RecyclerView$Adapter)this.mAdapter));
            }
        }
        else {
            this.mRecycler.setAdapter((RecyclerView$Adapter)new AlphaInAnimationAdapter((RecyclerView$Adapter)this.mAdapter));
        }
        this.addRecyclerAction();
    }
    
    private void initTitleBar() {
        if (this.selectorConfig.selectorStyle.getTitleBarStyle().isHideTitleBar()) {
            this.titleBar.setVisibility(8);
        }
        this.titleBar.setTitleBarStyle();
        this.titleBar.setOnTitleBarListener((TitleBar$OnTitleBarListener)new PictureSelectorFragment$3(this));
    }
    
    private boolean isAddSameImp(final int n) {
        final boolean b = false;
        if (n == 0) {
            return false;
        }
        final int allFolderSize = this.allFolderSize;
        boolean b2 = b;
        if (allFolderSize > 0) {
            b2 = b;
            if (allFolderSize < n) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private void mergeFolder(final LocalMedia localMedia) {
        final List albumList = this.albumListPopWindow.getAlbumList();
        LocalMediaFolder folder;
        if (this.albumListPopWindow.getFolderCount() == 0) {
            folder = new LocalMediaFolder();
            String folderName;
            if (TextUtils.isEmpty((CharSequence)this.selectorConfig.defaultAlbumName)) {
                int n;
                if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
                    n = R$string.ps_all_audio;
                }
                else {
                    n = R$string.ps_camera_roll;
                }
                folderName = this.getString(n);
            }
            else {
                folderName = this.selectorConfig.defaultAlbumName;
            }
            folder.setFolderName(folderName);
            folder.setFirstImagePath("");
            folder.setBucketId(-1L);
            albumList.add(0, (Object)folder);
        }
        else {
            folder = this.albumListPopWindow.getFolder(0);
        }
        folder.setFirstImagePath(localMedia.getPath());
        folder.setFirstMimeType(localMedia.getMimeType());
        folder.setData(this.mAdapter.getData());
        folder.setBucketId(-1L);
        int folderTotalNum;
        if (this.isAddSameImp(folder.getFolderTotalNum())) {
            folderTotalNum = folder.getFolderTotalNum();
        }
        else {
            folderTotalNum = folder.getFolderTotalNum() + 1;
        }
        folder.setFolderTotalNum(folderTotalNum);
        final LocalMediaFolder currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
        if (currentLocalMediaFolder == null || currentLocalMediaFolder.getFolderTotalNum() == 0) {
            this.selectorConfig.currentLocalMediaFolder = folder;
        }
        final LocalMediaFolder localMediaFolder = null;
        int n2 = 0;
        LocalMediaFolder localMediaFolder2;
        while (true) {
            localMediaFolder2 = localMediaFolder;
            if (n2 >= albumList.size()) {
                break;
            }
            localMediaFolder2 = (LocalMediaFolder)albumList.get(n2);
            if (TextUtils.equals((CharSequence)localMediaFolder2.getFolderName(), (CharSequence)localMedia.getParentFolderName())) {
                break;
            }
            ++n2;
        }
        LocalMediaFolder localMediaFolder3;
        if ((localMediaFolder3 = localMediaFolder2) == null) {
            localMediaFolder3 = new LocalMediaFolder();
            albumList.add((Object)localMediaFolder3);
        }
        localMediaFolder3.setFolderName(localMedia.getParentFolderName());
        if (localMediaFolder3.getBucketId() == -1L || localMediaFolder3.getBucketId() == 0L) {
            localMediaFolder3.setBucketId(localMedia.getBucketId());
        }
        if (this.selectorConfig.isPageStrategy) {
            localMediaFolder3.setHasMore(true);
        }
        else if (!this.isAddSameImp(folder.getFolderTotalNum()) || !TextUtils.isEmpty((CharSequence)this.selectorConfig.outPutCameraDir) || !TextUtils.isEmpty((CharSequence)this.selectorConfig.outPutAudioDir)) {
            localMediaFolder3.getData().add(0, (Object)localMedia);
        }
        int folderTotalNum2;
        if (this.isAddSameImp(folder.getFolderTotalNum())) {
            folderTotalNum2 = localMediaFolder3.getFolderTotalNum();
        }
        else {
            folderTotalNum2 = localMediaFolder3.getFolderTotalNum() + 1;
        }
        localMediaFolder3.setFolderTotalNum(folderTotalNum2);
        localMediaFolder3.setFirstImagePath(this.selectorConfig.cameraPath);
        localMediaFolder3.setFirstMimeType(localMedia.getMimeType());
        this.albumListPopWindow.bindAlbumData(albumList);
    }
    
    public static PictureSelectorFragment newInstance() {
        final PictureSelectorFragment pictureSelectorFragment = new PictureSelectorFragment();
        pictureSelectorFragment.setArguments(new Bundle());
        return pictureSelectorFragment;
    }
    
    private void onStartPreview(final int n, final boolean b) {
        if (ActivityCompatHelper.checkFragmentNonExits(this.getActivity(), PictureSelectorPreviewFragment.TAG)) {
            int statusBarHeight = 0;
            ArrayList list;
            int n2;
            long n3;
            if (b) {
                list = new ArrayList((Collection)this.selectorConfig.getSelectedResult());
                n2 = list.size();
                n3 = 0L;
            }
            else {
                list = new ArrayList((Collection)this.mAdapter.getData());
                final LocalMediaFolder currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
                if (currentLocalMediaFolder != null) {
                    n2 = currentLocalMediaFolder.getFolderTotalNum();
                    n3 = currentLocalMediaFolder.getBucketId();
                }
                else {
                    n2 = list.size();
                    if (list.size() > 0) {
                        n3 = ((LocalMedia)list.get(0)).getBucketId();
                    }
                    else {
                        n3 = -1L;
                    }
                }
            }
            if (!b && this.selectorConfig.isPreviewZoomEffect) {
                final RecyclerPreloadView mRecycler = this.mRecycler;
                if (!this.selectorConfig.isPreviewFullScreenMode) {
                    statusBarHeight = DensityUtil.getStatusBarHeight(this.getContext());
                }
                BuildRecycleItemViewParams.generateViewParams((ViewGroup)mRecycler, statusBarHeight);
            }
            if (this.selectorConfig.onPreviewInterceptListener != null) {
                this.selectorConfig.onPreviewInterceptListener.onPreview(this.getContext(), n, n2, this.mPage, n3, this.titleBar.getTitleText(), this.mAdapter.isDisplayCamera(), list, b);
            }
            else if (ActivityCompatHelper.checkFragmentNonExits(this.getActivity(), PictureSelectorPreviewFragment.TAG)) {
                final PictureSelectorPreviewFragment instance = PictureSelectorPreviewFragment.newInstance();
                instance.setInternalPreviewData(b, this.titleBar.getTitleText(), this.mAdapter.isDisplayCamera(), n, n2, this.mPage, n3, (ArrayList<LocalMedia>)list);
                FragmentInjectManager.injectFragment(this.getActivity(), PictureSelectorPreviewFragment.TAG, (Fragment)instance);
            }
        }
    }
    
    private boolean preloadPageFirstData() {
        boolean b;
        if (this.selectorConfig.isPageStrategy && this.selectorConfig.isPreloadFirst) {
            final LocalMediaFolder currentLocalMediaFolder = new LocalMediaFolder();
            currentLocalMediaFolder.setBucketId(-1L);
            if (TextUtils.isEmpty((CharSequence)this.selectorConfig.defaultAlbumName)) {
                final TitleBar titleBar = this.titleBar;
                Context context;
                int n;
                if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
                    context = this.requireContext();
                    n = R$string.ps_all_audio;
                }
                else {
                    context = this.requireContext();
                    n = R$string.ps_camera_roll;
                }
                titleBar.setTitle(context.getString(n));
            }
            else {
                this.titleBar.setTitle(this.selectorConfig.defaultAlbumName);
            }
            currentLocalMediaFolder.setFolderName(this.titleBar.getTitleText());
            this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
            this.loadFirstPageMediaData(currentLocalMediaFolder.getBucketId());
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    private void recoverSaveInstanceData() {
        this.mAdapter.setDisplayCamera(this.isDisplayCamera);
        this.setEnterAnimationDuration(0L);
        if (this.selectorConfig.isOnlySandboxDir) {
            this.handleInAppDirAllMedia(this.selectorConfig.currentLocalMediaFolder);
        }
        else {
            this.handleRecoverAlbumData((List<LocalMediaFolder>)new ArrayList((Collection)this.selectorConfig.albumDataSource));
        }
    }
    
    private void recoveryRecyclerPosition() {
        if (this.currentPosition > 0) {
            this.mRecycler.post((Runnable)new PictureSelectorFragment$15(this));
        }
    }
    
    private void removePageCameraRepeatData(final List<LocalMedia> list) {
        Label_0094: {
            try {
                if (this.selectorConfig.isPageStrategy && this.isCameraCallback) {
                    final Object lock = PictureSelectorFragment.LOCK;
                    synchronized (lock) {
                        final Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            if (this.mAdapter.getData().contains(iterator.next())) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
            finally {
                break Label_0094;
            }
            this.isCameraCallback = false;
            return;
        }
        this.isCameraCallback = false;
    }
    
    private void requestLoadData() {
        this.mAdapter.setDisplayCamera(this.isDisplayCamera);
        if (PermissionChecker.isCheckReadStorage(this.selectorConfig.chooseMode, this.getContext())) {
            this.beginLoadData();
        }
        else {
            final String[] readPermissionArray = PermissionConfig.getReadPermissionArray(this.getAppContext(), this.selectorConfig.chooseMode);
            this.onPermissionExplainEvent(true, readPermissionArray);
            if (this.selectorConfig.onPermissionsEventListener != null) {
                this.onApplyPermissionsEvent(-1, readPermissionArray);
            }
            else {
                PermissionChecker.getInstance().requestPermissions((Fragment)this, readPermissionArray, (PermissionResultCallback)new PictureSelectorFragment$5(this, readPermissionArray));
            }
        }
    }
    
    private void setAdapterData(final ArrayList<LocalMedia> adapterDataComplete) {
        final long enterAnimationDuration = this.getEnterAnimationDuration();
        if (enterAnimationDuration > 0L) {
            this.requireView().postDelayed((Runnable)new PictureSelectorFragment$20(this, (ArrayList)adapterDataComplete), enterAnimationDuration);
        }
        else {
            this.setAdapterDataComplete(adapterDataComplete);
        }
    }
    
    private void setAdapterDataComplete(final ArrayList<LocalMedia> dataAndDataSetChanged) {
        this.setEnterAnimationDuration(0L);
        this.sendChangeSubSelectPositionEvent(false);
        this.mAdapter.setDataAndDataSetChanged((ArrayList)dataAndDataSetChanged);
        this.selectorConfig.dataSource.clear();
        this.selectorConfig.albumDataSource.clear();
        this.recoveryRecyclerPosition();
        if (this.mAdapter.isDataEmpty()) {
            this.showDataNull();
        }
        else {
            this.hideDataNull();
        }
    }
    
    private void setCurrentMediaCreateTimeText() {
        if (this.selectorConfig.isDisplayTimeAxis) {
            final int firstVisiblePosition = this.mRecycler.getFirstVisiblePosition();
            if (firstVisiblePosition != -1) {
                final ArrayList data = this.mAdapter.getData();
                if (data.size() > firstVisiblePosition && ((LocalMedia)data.get(firstVisiblePosition)).getDateAddedTime() > 0L) {
                    this.tvCurrentDataTime.setText((CharSequence)DateUtils.getDataFormat(this.getContext(), ((LocalMedia)data.get(firstVisiblePosition)).getDateAddedTime()));
                }
            }
        }
    }
    
    private void showCurrentMediaCreateTimeUI() {
        if (this.selectorConfig.isDisplayTimeAxis && this.mAdapter.getData().size() > 0 && this.tvCurrentDataTime.getAlpha() == 0.0f) {
            this.tvCurrentDataTime.animate().setDuration(150L).alphaBy(1.0f).start();
        }
    }
    
    private void showDataNull() {
        if (this.selectorConfig.currentLocalMediaFolder == null || this.selectorConfig.currentLocalMediaFolder.getBucketId() == -1L) {
            if (this.tvDataEmpty.getVisibility() == 8) {
                this.tvDataEmpty.setVisibility(0);
            }
            this.tvDataEmpty.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R$drawable.ps_ic_no_data, 0, 0);
            int n;
            if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
                n = R$string.ps_audio_empty;
            }
            else {
                n = R$string.ps_empty;
            }
            this.tvDataEmpty.setText((CharSequence)this.getString(n));
        }
    }
    
    @Override
    public void dispatchCameraMediaResult(final LocalMedia localMedia) {
        if (!this.isAddSameImp(this.albumListPopWindow.getFirstAlbumImageCount())) {
            this.mAdapter.getData().add(0, (Object)localMedia);
            this.isCameraCallback = true;
        }
        if (this.selectorConfig.selectionMode == 1 && this.selectorConfig.isDirectReturnSingle) {
            this.selectorConfig.selectedResult.clear();
            if (this.confirmSelect(localMedia, false) == 0) {
                this.dispatchTransformResult();
            }
        }
        else {
            this.confirmSelect(localMedia, false);
        }
        this.mAdapter.notifyItemInserted((int)(this.selectorConfig.isDisplayCamera ? 1 : 0));
        this.mAdapter.notifyItemRangeChanged((int)(this.selectorConfig.isDisplayCamera ? 1 : 0), this.mAdapter.getData().size());
        if (this.selectorConfig.isOnlySandboxDir) {
            LocalMediaFolder currentLocalMediaFolder;
            if ((currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder) == null) {
                currentLocalMediaFolder = new LocalMediaFolder();
            }
            currentLocalMediaFolder.setBucketId(ValueOf.toLong((Object)localMedia.getParentFolderName().hashCode()));
            currentLocalMediaFolder.setFolderName(localMedia.getParentFolderName());
            currentLocalMediaFolder.setFirstMimeType(localMedia.getMimeType());
            currentLocalMediaFolder.setFirstImagePath(localMedia.getPath());
            currentLocalMediaFolder.setFolderTotalNum(this.mAdapter.getData().size());
            currentLocalMediaFolder.setCurrentDataPage(this.mPage);
            currentLocalMediaFolder.setHasMore(false);
            currentLocalMediaFolder.setData(this.mAdapter.getData());
            this.mRecycler.setEnabledLoadMore(false);
            this.selectorConfig.currentLocalMediaFolder = currentLocalMediaFolder;
        }
        else {
            this.mergeFolder(localMedia);
        }
        this.allFolderSize = 0;
        if (this.mAdapter.getData().size() <= 0 && !this.selectorConfig.isDirectReturnSingle) {
            this.showDataNull();
        }
        else {
            this.hideDataNull();
        }
    }
    
    @Override
    public String getFragmentTag() {
        return PictureSelectorFragment.TAG;
    }
    
    @Override
    public int getResourceId() {
        final int layoutResource = InjectResourceSource.getLayoutResource(this.getContext(), 1, this.selectorConfig);
        if (layoutResource != 0) {
            return layoutResource;
        }
        return R$layout.ps_fragment_selector;
    }
    
    @Override
    public void handlePermissionSettingResult(final String[] array) {
        if (array == null) {
            return;
        }
        this.onPermissionExplainEvent(false, null);
        final boolean b = array.length > 0 && TextUtils.equals((CharSequence)array[0], (CharSequence)PermissionConfig.CAMERA[0]);
        boolean b2;
        if (this.selectorConfig.onPermissionsEventListener != null) {
            b2 = this.selectorConfig.onPermissionsEventListener.hasPermissions((Fragment)this, array);
        }
        else {
            b2 = PermissionChecker.isCheckSelfPermission(this.getContext(), array);
        }
        if (b2) {
            if (b) {
                this.openSelectedCamera();
            }
            else {
                this.beginLoadData();
            }
        }
        else if (b) {
            ToastUtils.showToast(this.getContext(), this.getString(R$string.ps_camera));
        }
        else {
            ToastUtils.showToast(this.getContext(), this.getString(R$string.ps_jurisdiction));
            this.onKeyBackFragmentFinish();
        }
        PermissionConfig.CURRENT_REQUEST_PERMISSION = new String[0];
    }
    
    public void loadAllAlbumData() {
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadAllAlbumData(this.getContext(), (OnQueryAllAlbumListener)new PictureSelectorFragment$9(this));
        }
        else {
            this.mLoader.loadAllAlbum((OnQueryAllAlbumListener)new PictureSelectorFragment$10(this, this.preloadPageFirstData()));
        }
    }
    
    public void loadFirstPageMediaData(final long n) {
        this.mPage = 1;
        this.mRecycler.setEnabledLoadMore(true);
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadFirstPageMediaData(this.getContext(), n, this.mPage, this.mPage * this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorFragment$11(this));
        }
        else {
            this.mLoader.loadPageMediaData(n, this.mPage, this.mPage * this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorFragment$12(this));
        }
    }
    
    public void loadMoreMediaData() {
        if (this.mRecycler.isEnabledLoadMore()) {
            ++this.mPage;
            final LocalMediaFolder currentLocalMediaFolder = this.selectorConfig.currentLocalMediaFolder;
            long bucketId;
            if (currentLocalMediaFolder != null) {
                bucketId = currentLocalMediaFolder.getBucketId();
            }
            else {
                bucketId = 0L;
            }
            if (this.selectorConfig.loaderDataEngine != null) {
                this.selectorConfig.loaderDataEngine.loadMoreMediaData(this.getContext(), bucketId, this.mPage, this.selectorConfig.pageSize, this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorFragment$22(this));
            }
            else {
                this.mLoader.loadPageMediaData(bucketId, this.mPage, this.selectorConfig.pageSize, (OnQueryDataResultListener)new PictureSelectorFragment$23(this));
            }
        }
    }
    
    public void loadOnlyInAppDirectoryAllMediaData() {
        if (this.selectorConfig.loaderDataEngine != null) {
            this.selectorConfig.loaderDataEngine.loadOnlyInAppDirAllMediaData(this.getContext(), (OnQueryAlbumListener)new PictureSelectorFragment$13(this));
        }
        else {
            this.mLoader.loadOnlyInAppDirAllMedia((OnQueryAlbumListener)new PictureSelectorFragment$14(this));
        }
    }
    
    @Override
    public void onApplyPermissionsEvent(final int n, final String[] array) {
        if (n != -1) {
            super.onApplyPermissionsEvent(n, array);
        }
        else {
            this.selectorConfig.onPermissionsEventListener.requestPermission((Fragment)this, array, (OnRequestPermissionListener)new PictureSelectorFragment$6(this));
        }
    }
    
    @Override
    public void onCheckOriginalChange() {
        this.bottomNarBar.setOriginalCheck();
    }
    
    @Override
    public void onCreateLoader() {
        if (this.selectorConfig.loaderFactory != null) {
            this.mLoader = this.selectorConfig.loaderFactory.onCreateLoader();
            if (this.mLoader == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("No available ");
                sb.append((Object)IBridgeMediaLoader.class);
                sb.append(" loader found");
                throw new NullPointerException(sb.toString());
            }
        }
        else {
            Object mLoader;
            if (this.selectorConfig.isPageStrategy) {
                mLoader = new LocalMediaPageLoader(this.getAppContext(), this.selectorConfig);
            }
            else {
                mLoader = new LocalMediaLoader(this.getAppContext(), this.selectorConfig);
            }
            this.mLoader = (IBridgeMediaLoader)mLoader;
        }
    }
    
    public void onDestroyView() {
        super.onDestroyView();
        final SlideSelectTouchListener mDragSelectTouchListener = this.mDragSelectTouchListener;
        if (mDragSelectTouchListener != null) {
            mDragSelectTouchListener.stopAutoScroll();
        }
    }
    
    @Override
    public void onFixedSelectedChange(final LocalMedia localMedia) {
        this.mAdapter.notifyItemPositionChanged(localMedia.position);
    }
    
    @Override
    public void onFragmentResume() {
        this.setRootViewKeyListener(this.requireView());
    }
    
    public void onRecyclerViewPreloadMore() {
        if (this.isMemoryRecycling) {
            this.requireView().postDelayed((Runnable)new PictureSelectorFragment$21(this), 350L);
        }
        else {
            this.loadMoreMediaData();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("com.luck.picture.lib.all_folder_size", this.allFolderSize);
        bundle.putInt("com.luck.picture.lib.current_page", this.mPage);
        bundle.putInt("com.luck.picture.lib.current_preview_position", this.mRecycler.getLastVisiblePosition());
        bundle.putBoolean("com.luck.picture.lib.display_camera", this.mAdapter.isDisplayCamera());
        this.selectorConfig.addAlbumDataSource(this.albumListPopWindow.getAlbumList());
        this.selectorConfig.addDataSource(this.mAdapter.getData());
    }
    
    @Override
    public void onSelectedChange(final boolean b, final LocalMedia localMedia) {
        this.bottomNarBar.setSelectedChange();
        this.completeSelectView.setSelectedChange(false);
        if (this.checkNotifyStrategy(b)) {
            this.mAdapter.notifyItemPositionChanged(localMedia.position);
            this.mRecycler.postDelayed((Runnable)new PictureSelectorFragment$1(this), (long)PictureSelectorFragment.SELECT_ANIM_DURATION);
        }
        else {
            this.mAdapter.notifyItemPositionChanged(localMedia.position);
        }
        if (!b) {
            this.sendChangeSubSelectPositionEvent(true);
        }
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.reStartSavedInstance(bundle);
        this.isMemoryRecycling = (bundle != null);
        this.tvDataEmpty = (TextView)view.findViewById(R$id.tv_data_empty);
        this.completeSelectView = (CompleteSelectView)view.findViewById(R$id.ps_complete_select);
        this.titleBar = (TitleBar)view.findViewById(R$id.title_bar);
        this.bottomNarBar = (BottomNavBar)view.findViewById(R$id.bottom_nar_bar);
        this.tvCurrentDataTime = (TextView)view.findViewById(R$id.tv_current_data_time);
        this.onCreateLoader();
        this.initAlbumListPopWindow();
        this.initTitleBar();
        this.initComplete();
        this.initRecycler(view);
        this.initBottomNavBar();
        if (this.isMemoryRecycling) {
            this.recoverSaveInstanceData();
        }
        else {
            this.requestLoadData();
        }
    }
    
    @Override
    public void reStartSavedInstance(final Bundle bundle) {
        if (bundle != null) {
            this.allFolderSize = bundle.getInt("com.luck.picture.lib.all_folder_size");
            this.mPage = bundle.getInt("com.luck.picture.lib.current_page", this.mPage);
            this.currentPosition = bundle.getInt("com.luck.picture.lib.current_preview_position", this.currentPosition);
            this.isDisplayCamera = bundle.getBoolean("com.luck.picture.lib.display_camera", this.selectorConfig.isDisplayCamera);
        }
        else {
            this.isDisplayCamera = this.selectorConfig.isDisplayCamera;
        }
    }
    
    @Override
    public void sendChangeSubSelectPositionEvent(final boolean b) {
        if (this.selectorConfig.selectorStyle.getSelectMainStyle().isSelectNumberStyle()) {
            int num;
            for (int i = 0; i < this.selectorConfig.getSelectCount(); i = num) {
                final LocalMedia localMedia = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
                num = i + 1;
                localMedia.setNum(num);
                i = num;
                if (b) {
                    this.mAdapter.notifyItemPositionChanged(localMedia.position);
                }
            }
        }
    }
}
