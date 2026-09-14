package com.luck.picture.lib.dialog;

import com.luck.picture.lib.interfaces.OnAlbumItemClickListener;
import android.text.TextUtils;
import com.luck.picture.lib.entity.LocalMedia;
import android.view.ViewGroup$LayoutParams;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.List;
import com.luck.picture.lib.utils.SdkVersionUtils;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import com.luck.picture.lib.decoration.WrapContentLinearLayoutManager;
import com.luck.picture.lib.utils.DensityUtil;
import android.view.ViewGroup;
import com.luck.picture.lib.R;
import android.view.LayoutInflater;
import android.view.View;
import com.luck.picture.lib.config.SelectorConfig;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.luck.picture.lib.adapter.PictureAlbumAdapter;
import android.widget.PopupWindow;

public class AlbumListPopWindow extends PopupWindow
{
    private static final int ALBUM_MAX_COUNT = 8;
    private boolean isDismiss;
    private PictureAlbumAdapter mAdapter;
    private final Context mContext;
    private RecyclerView mRecyclerView;
    private SelectorConfig selectorConfig;
    private View windMask;
    private int windowMaxHeight;
    private OnPopupWindowStatusListener windowStatusListener;
    
    public AlbumListPopWindow(final Context mContext, final SelectorConfig selectorConfig) {
        this.isDismiss = false;
        this.mContext = mContext;
        this.selectorConfig = selectorConfig;
        this.setContentView(LayoutInflater.from(mContext).inflate(R.layout.ps_window_folder, (ViewGroup)null));
        this.setWidth(-1);
        this.setHeight(-2);
        this.setAnimationStyle(R.style.PictureThemeWindowStyle);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.initViews();
    }
    
    static /* synthetic */ void access$001(final AlbumListPopWindow albumListPopWindow) {
        albumListPopWindow.dismiss();
    }
    
    public static AlbumListPopWindow buildPopWindow(final Context context, final SelectorConfig selectorConfig) {
        return new AlbumListPopWindow(context, selectorConfig);
    }
    
    private void initViews() {
        this.windowMaxHeight = (int)(DensityUtil.getScreenHeight(this.mContext) * 0.6);
        this.mRecyclerView = (RecyclerView)this.getContentView().findViewById(R.id.folder_list);
        this.windMask = this.getContentView().findViewById(R.id.rootViewBg);
        this.mRecyclerView.setLayoutManager((RecyclerView$LayoutManager)new WrapContentLinearLayoutManager(this.mContext));
        final PictureAlbumAdapter pictureAlbumAdapter = new PictureAlbumAdapter(this.selectorConfig);
        this.mAdapter = pictureAlbumAdapter;
        this.mRecyclerView.setAdapter((RecyclerView$Adapter)pictureAlbumAdapter);
        this.windMask.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final AlbumListPopWindow this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
        this.getContentView().findViewById(R.id.rootView).setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final AlbumListPopWindow this$0;
            
            public void onClick(final View view) {
                if (SdkVersionUtils.isMinM()) {
                    this.this$0.dismiss();
                }
            }
        });
    }
    
    public void bindAlbumData(final List<LocalMediaFolder> list) {
        this.mAdapter.bindAlbumData((List)list);
        this.mAdapter.notifyDataSetChanged();
        final ViewGroup$LayoutParams layoutParams = this.mRecyclerView.getLayoutParams();
        int windowMaxHeight;
        if (list.size() > 8) {
            windowMaxHeight = this.windowMaxHeight;
        }
        else {
            windowMaxHeight = -2;
        }
        layoutParams.height = windowMaxHeight;
    }
    
    public void changeSelectedAlbumStyle() {
        final List albumList = this.mAdapter.getAlbumList();
        for (int i = 0; i < albumList.size(); ++i) {
            final LocalMediaFolder localMediaFolder = (LocalMediaFolder)albumList.get(i);
            localMediaFolder.setSelectTag(false);
            this.mAdapter.notifyItemChanged(i);
            for (int j = 0; j < this.selectorConfig.getSelectCount(); ++j) {
                if (TextUtils.equals((CharSequence)localMediaFolder.getFolderName(), (CharSequence)((LocalMedia)this.selectorConfig.getSelectedResult().get(j)).getParentFolderName()) || localMediaFolder.getBucketId() == -1L) {
                    localMediaFolder.setSelectTag(true);
                    this.mAdapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }
    
    public void dismiss() {
        if (this.isDismiss) {
            return;
        }
        this.windMask.setAlpha(0.0f);
        final OnPopupWindowStatusListener windowStatusListener = this.windowStatusListener;
        if (windowStatusListener != null) {
            windowStatusListener.onDismissPopupWindow();
        }
        this.isDismiss = true;
        this.windMask.post((Runnable)new Runnable(this) {
            final AlbumListPopWindow this$0;
            
            public void run() {
                AlbumListPopWindow.access$001(this.this$0);
                this.this$0.isDismiss = false;
            }
        });
    }
    
    public List<LocalMediaFolder> getAlbumList() {
        return (List<LocalMediaFolder>)this.mAdapter.getAlbumList();
    }
    
    public int getFirstAlbumImageCount() {
        final int folderCount = this.getFolderCount();
        int folderTotalNum = 0;
        if (folderCount > 0) {
            folderTotalNum = this.getFolder(0).getFolderTotalNum();
        }
        return folderTotalNum;
    }
    
    public LocalMediaFolder getFolder(final int n) {
        LocalMediaFolder localMediaFolder;
        if (this.mAdapter.getAlbumList().size() > 0 && n < this.mAdapter.getAlbumList().size()) {
            localMediaFolder = (LocalMediaFolder)this.mAdapter.getAlbumList().get(n);
        }
        else {
            localMediaFolder = null;
        }
        return localMediaFolder;
    }
    
    public int getFolderCount() {
        return this.mAdapter.getAlbumList().size();
    }
    
    public void setOnIBridgeAlbumWidget(final OnAlbumItemClickListener onIBridgeAlbumWidget) {
        this.mAdapter.setOnIBridgeAlbumWidget(onIBridgeAlbumWidget);
    }
    
    public void setOnPopupWindowStatusListener(final OnPopupWindowStatusListener windowStatusListener) {
        this.windowStatusListener = windowStatusListener;
    }
    
    public void showAsDropDown(final View view) {
        if (this.getAlbumList() != null) {
            if (this.getAlbumList().size() != 0) {
                if (SdkVersionUtils.isN()) {
                    final int[] array = new int[2];
                    view.getLocationInWindow(array);
                    this.showAtLocation(view, 0, 0, array[1] + view.getHeight());
                }
                else {
                    super.showAsDropDown(view);
                }
                this.isDismiss = false;
                final OnPopupWindowStatusListener windowStatusListener = this.windowStatusListener;
                if (windowStatusListener != null) {
                    windowStatusListener.onShowPopupWindow();
                }
                this.windMask.animate().alpha(1.0f).setDuration(250L).setStartDelay(250L).start();
                this.changeSelectedAlbumStyle();
            }
        }
    }
    
    public interface OnPopupWindowStatusListener
    {
        void onDismissPopupWindow();
        
        void onShowPopupWindow();
    }
}
