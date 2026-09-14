package com.kingagroot.kingdraw.widget;

import android.text.style.ForegroundColorSpan;
import android.text.SpannableString;
import com.goodsrc.library.utils.StringUtils;
import java.util.Iterator;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.kingdraw.model.CloudFileModel;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.ArrayList;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.LinearLayout;

public class BatchOperateView extends LinearLayout
{
    public Button btnDelete;
    public Button btnDownload;
    public CheckBox cbBatch;
    private final ArrayList<FolderFileModel> checkMap;
    private final ArrayList<CloudFileModel> cloudCheckMap;
    private final Context context;
    private OnBatchOperateViewListener onBatchOperateViewListener;
    
    public BatchOperateView(final Context context) {
        this(context, null);
    }
    
    public BatchOperateView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public BatchOperateView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.checkMap = (ArrayList<FolderFileModel>)new ArrayList();
        this.cloudCheckMap = (ArrayList<CloudFileModel>)new ArrayList();
        this.context = context;
        this.initView(View.inflate(context, 2131493086, (ViewGroup)this));
    }
    
    private void initView(final View view) {
        this.cbBatch = (CheckBox)view.findViewById(2131296477);
        this.btnDelete = (Button)view.findViewById(2131296419);
        this.btnDownload = (Button)view.findViewById(2131296424);
        this.btnDelete.setOnClickListener((View$OnClickListener)new _$$Lambda$BatchOperateView$ypOzUZniJwwtIKC4Lmcd6n5JJDQ(this));
        this.btnDownload.setOnClickListener((View$OnClickListener)new _$$Lambda$BatchOperateView$6PTdki6Xc0FqUbh5NXTm9FjxEoI(this));
        this.cbBatch.setOnClickListener((View$OnClickListener)new _$$Lambda$BatchOperateView$AQijhT276b_BGko0vyPoqb0hlXY(this));
    }
    
    public void addCheck(final String s, final FolderFileModel folderFileModel) {
        if (!this.isCheck(s)) {
            this.checkMap.add((Object)folderFileModel);
        }
    }
    
    public void addCloudCheck(final int n, final CloudFileModel cloudFileModel) {
        if (!this.isCloudCheck(n)) {
            this.cloudCheckMap.add((Object)cloudFileModel);
        }
    }
    
    public void clearCheck() {
        this.checkMap.clear();
    }
    
    public void clearCloudCheck() {
        this.cloudCheckMap.clear();
    }
    
    public ArrayList<FolderFileModel> getCheckMap() {
        return this.checkMap;
    }
    
    public ArrayList<CloudFileModel> getCloudCheckMap() {
        return this.cloudCheckMap;
    }
    
    public boolean isCheck(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Iterator iterator = this.checkMap.iterator();
            while (iterator.hasNext()) {
                if (((FolderFileModel)iterator.next()).getId().equals((Object)s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isCheckAll() {
        return this.cbBatch.isChecked();
    }
    
    public boolean isCloudCheck(final int n) {
        final Iterator iterator = this.cloudCheckMap.iterator();
        while (iterator.hasNext()) {
            if (((CloudFileModel)iterator.next()).getId() == n) {
                return true;
            }
        }
        return false;
    }
    
    public void removeCheck(final FolderFileModel folderFileModel) {
        for (int i = 0; i < this.checkMap.size(); ++i) {
            if (((FolderFileModel)this.checkMap.get(i)).getId().equals((Object)folderFileModel.getId())) {
                this.checkMap.remove(i);
                break;
            }
        }
    }
    
    public void removeCloudCheck(final CloudFileModel cloudFileModel) {
        for (int i = 0; i < this.cloudCheckMap.size(); ++i) {
            if (((CloudFileModel)this.cloudCheckMap.get(i)).getId() == cloudFileModel.getId()) {
                this.cloudCheckMap.remove(i);
                break;
            }
        }
    }
    
    public void setBtnDownloadText(final String text) {
        this.btnDownload.setText((CharSequence)text);
    }
    
    public void setCheck(final boolean checked) {
        this.cbBatch.setChecked(checked);
    }
    
    public void setCheckNum() {
        this.setCheckNum(this.checkMap.size());
    }
    
    public void setCheckNum(final int n) {
        if (n == 0) {
            this.cbBatch.setText(2131820680);
        }
        else {
            final String format = StringUtils.format(this.context.getResources().getString(2131820683), new Object[] { n });
            final int index = format.indexOf(String.valueOf(n));
            final int length = String.valueOf(n).length();
            final SpannableString text = new SpannableString((CharSequence)format);
            text.setSpan((Object)new ForegroundColorSpan(this.context.getResources().getColor(2131099773)), index, length + index, 33);
            this.cbBatch.setText((CharSequence)text);
        }
        if (n > 0) {
            this.btnDownload.setEnabled(true);
            this.btnDelete.setEnabled(true);
        }
        else {
            this.btnDownload.setEnabled(false);
            this.btnDelete.setEnabled(false);
        }
    }
    
    public void setCloudCheckNum() {
        this.setCheckNum(this.cloudCheckMap.size());
    }
    
    public void setOnBatchOperateViewListener(final OnBatchOperateViewListener onBatchOperateViewListener) {
        this.onBatchOperateViewListener = onBatchOperateViewListener;
    }
    
    public interface OnBatchOperateViewListener
    {
        void onCheckAll();
        
        void onCheckClear();
        
        void onDelete();
        
        void onSynchronise();
    }
}
