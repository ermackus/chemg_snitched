package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.utils.ImageLoader;
import com.google.gson.Gson;
import com.kingagroot.kingdraw.model.CloudFileModel;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import com.kingagroot.kingdraw.model.SynStatusEnum;
import android.view.View;
import com.kingagroot.kingdraw.widget.DoneView;
import android.os.Message;
import android.os.Handler;
import com.kingagroot.kingdraw.model.SynFileModel;
import java.util.List;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class SynchornizeAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private static final int MSG_NEXT = 31;
    private static final int MSG_REMOVEITEM = 30;
    private final Context context;
    private final List<SynFileModel> folderFileModels;
    Handler handler;
    private boolean isDowloadSyn;
    private SynchornizeAdapter.SynchornizeAdapter$OnSynChronizeAdapterListner onSynChronizeAdapterListner;
    
    public SynchornizeAdapter(final Context context, final List<SynFileModel> folderFileModels, final boolean isDowloadSyn) {
        this.isDowloadSyn = false;
        this.handler = (Handler)new SynchornizeAdapter$1(this);
        this.context = context;
        this.folderFileModels = folderFileModels;
        this.isDowloadSyn = isDowloadSyn;
    }
    
    private void delaySendRemoveMsg(final int arg1) {
        final Message message = new Message();
        message.what = 30;
        message.arg1 = arg1;
        this.handler.sendMessageDelayed(message, DoneView.ANIMTIME);
    }
    
    private void sendRemoveMsg(final int arg1) {
        final Message message = new Message();
        message.what = 30;
        message.arg1 = arg1;
        this.handler.sendMessage(message);
    }
    
    private void sendStartNextMsg() {
        final Message message = new Message();
        message.what = 31;
        this.handler.sendMessageDelayed(message, 500L);
    }
    
    public SynFileModel getItem(final int n) {
        return (SynFileModel)this.folderFileModels.get(n);
    }
    
    public int getItemCount() {
        final List<SynFileModel> folderFileModels = this.folderFileModels;
        if (folderFileModels == null) {
            return 0;
        }
        return folderFileModels.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final SynFileModel synFileModel = (SynFileModel)this.folderFileModels.get(n);
        if (this.isDowloadSyn) {
            final CloudFileModel cloudFileModel = (CloudFileModel)new Gson().fromJson(synFileModel.getFolderFileModelStr(), (Class)CloudFileModel.class);
            viewHolder.tvFilename.setText((CharSequence)cloudFileModel.getFileName());
            ImageLoader.bind(viewHolder.img, cloudFileModel.getPicPath());
        }
        else {
            final FolderFileModel folderFileModel = (FolderFileModel)new Gson().fromJson(synFileModel.getFolderFileModelStr(), (Class)FolderFileModel.class);
            viewHolder.tvFilename.setText((CharSequence)folderFileModel.getFileName());
            ImageLoader.bind(viewHolder.img, folderFileModel.getPicPath());
        }
        viewHolder.viewStatus.setTag((Object)n);
        if (synFileModel.getStatus() == SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()) {
            viewHolder.tvWait.setVisibility(0);
            viewHolder.llProgress.setVisibility(8);
            viewHolder.viewStatus.setVisibility(4);
        }
        else {
            viewHolder.tvWait.setVisibility(8);
            viewHolder.llProgress.setVisibility(0);
            viewHolder.viewStatus.setVisibility(0);
            if (synFileModel.getStatus() == SynStatusEnum.\u5931\u8d25.getCode()) {
                viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231660));
                viewHolder.viewStatus.fail();
                viewHolder.progressBar.setProgress(100);
            }
            else if (synFileModel.getStatus() == SynStatusEnum.\u6210\u529f.getCode()) {
                viewHolder.viewStatus.success();
                viewHolder.progressBar.setProgress(100);
                viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231661));
                this.sendRemoveMsg(n);
            }
            else if (synFileModel.getStatus() == SynStatusEnum.\u4f20\u8f93\u4e2d.getCode()) {
                viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231661));
                viewHolder.viewStatus.normal();
            }
        }
        viewHolder.itemView.setOnClickListener((View$OnClickListener)new _$$Lambda$SynchornizeAdapter$jgMwy9NZG_c7J09PCt3UlsQiNx8(this, viewHolder));
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131492952, viewGroup, false));
    }
    
    public void setOnSynChronizeAdapterListner(final SynchornizeAdapter.SynchornizeAdapter$OnSynChronizeAdapterListner onSynChronizeAdapterListner) {
        this.onSynChronizeAdapterListner = onSynChronizeAdapterListner;
    }
    
    public void updataView(final View view, int progress, final SynFileModel synFileModel) {
        final ViewHolder viewHolder = new ViewHolder(view);
        final int status = synFileModel.getStatus();
        if (status == SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()) {
            viewHolder.tvWait.setVisibility(0);
            viewHolder.llProgress.setVisibility(8);
            viewHolder.viewStatus.setVisibility(4);
        }
        else {
            viewHolder.tvWait.setVisibility(8);
            viewHolder.llProgress.setVisibility(0);
            viewHolder.viewStatus.setVisibility(0);
        }
        if (status == SynStatusEnum.\u4f20\u8f93\u4e2d.getCode()) {
            viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231661));
            progress = (int)(synFileModel.getSynProgress() * 100L / synFileModel.getFileSize());
            viewHolder.progressBar.setProgress(progress);
            viewHolder.viewStatus.normal();
        }
        else if (status == SynStatusEnum.\u6210\u529f.getCode()) {
            viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231661));
            viewHolder.progressBar.setProgress(100);
            viewHolder.viewStatus.startSuccessAnim();
            this.delaySendRemoveMsg(progress);
        }
        else if (status == SynStatusEnum.\u5931\u8d25.getCode()) {
            viewHolder.progressBar.setProgressDrawable(this.context.getResources().getDrawable(2131231660));
            viewHolder.progressBar.setProgress(100);
            viewHolder.viewStatus.startErrorAnim();
            this.sendStartNextMsg();
        }
    }
    
    class ViewHolder extends RecyclerView$ViewHolder
    {
        ImageView img;
        LinearLayout llProgress;
        ProgressBar progressBar;
        final SynchornizeAdapter this$0;
        TextView tvFilename;
        TextView tvWait;
        DoneView viewStatus;
        
        public ViewHolder(final SynchornizeAdapter this$0, final View view) {
            this.this$0 = this$0;
            super(view);
            this.img = (ImageView)view.findViewById(2131296867);
            this.tvFilename = (TextView)view.findViewById(2131297592);
            this.progressBar = (ProgressBar)view.findViewById(2131297206);
            this.viewStatus = (DoneView)view.findViewById(2131297738);
            this.tvWait = (TextView)view.findViewById(2131297703);
            this.llProgress = (LinearLayout)view.findViewById(2131297022);
        }
    }
}
