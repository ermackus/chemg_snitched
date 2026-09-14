package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import org.xutils.common.util.DensityUtil;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.kingdraw.model.FileTypeModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class FileTypeChooseAdapter extends BaseAdapter
{
    Context context;
    List<FileTypeModel> fileTypeModelList;
    
    public FileTypeChooseAdapter(final Context context, final FileType fileType) {
        this.context = context;
        this.fileTypeModelList = (List<FileTypeModel>)new ArrayList();
        final FileTypeModel fileTypeModel = new FileTypeModel(FileType.KDX, "");
        fileTypeModel.setInfo(context.getResources().getString(2131820820));
        final FileTypeModel fileTypeModel2 = new FileTypeModel(FileType.MOL_V2000, "");
        fileTypeModel2.setInfo(context.getResources().getString(2131820821));
        final FileTypeModel fileTypeModel3 = new FileTypeModel(FileType.MOL_V3000, "V3000");
        fileTypeModel3.setInfo(context.getResources().getString(2131820822));
        final FileTypeModel fileTypeModel4 = new FileTypeModel(FileType.CDX, "");
        fileTypeModel4.setInfo(context.getResources().getString(2131820817));
        final FileTypeModel fileTypeModel5 = new FileTypeModel(FileType.PNG, "");
        fileTypeModel5.setInfo(context.getResources().getString(2131820823));
        final FileTypeModel fileTypeModel6 = new FileTypeModel(FileType.JPG, "");
        fileTypeModel6.setInfo(context.getResources().getString(2131820819));
        this.fileTypeModelList.add((Object)fileTypeModel);
        this.fileTypeModelList.add((Object)fileTypeModel2);
        this.fileTypeModelList.add((Object)fileTypeModel3);
        this.fileTypeModelList.add((Object)fileTypeModel4);
        this.fileTypeModelList.add((Object)fileTypeModel5);
        this.fileTypeModelList.add((Object)fileTypeModel6);
        if (fileType == FileType.CDX) {
            this.fileTypeModelList.remove((Object)fileTypeModel4);
            this.fileTypeModelList.add(0, (Object)fileTypeModel4);
        }
        else if (fileType == FileType.MOL_V3000) {
            this.fileTypeModelList.remove((Object)fileTypeModel3);
            this.fileTypeModelList.add(0, (Object)fileTypeModel3);
        }
        else if (fileType == FileType.MOL_V2000) {
            this.fileTypeModelList.remove((Object)fileTypeModel2);
            this.fileTypeModelList.add(0, (Object)fileTypeModel2);
        }
    }
    
    public static FileTypeModel getFileTypeModel(final Context context, final FileType fileType) {
        FileTypeModel fileTypeModel2;
        if (fileType == FileType.CDX) {
            final FileTypeModel fileTypeModel = new FileTypeModel(FileType.CDX, "");
            fileTypeModel.setInfo(context.getResources().getString(2131820817));
            fileTypeModel2 = fileTypeModel;
        }
        else if (fileType == FileType.MOL_V3000) {
            final FileTypeModel fileTypeModel3 = new FileTypeModel(FileType.MOL_V3000, "V3000");
            fileTypeModel3.setInfo(context.getResources().getString(2131820822));
            fileTypeModel2 = fileTypeModel3;
        }
        else if (fileType == FileType.MOL_V2000) {
            final FileTypeModel fileTypeModel4 = new FileTypeModel(FileType.MOL_V2000, "");
            fileTypeModel4.setInfo(context.getResources().getString(2131820821));
            fileTypeModel2 = fileTypeModel4;
        }
        else if (fileType == FileType.PNG) {
            final FileTypeModel fileTypeModel5 = new FileTypeModel(FileType.PNG, "");
            fileTypeModel5.setInfo(context.getResources().getString(2131820823));
            fileTypeModel2 = fileTypeModel5;
        }
        else if (fileType == FileType.JPG) {
            final FileTypeModel fileTypeModel6 = new FileTypeModel(FileType.JPG, "");
            fileTypeModel6.setInfo(context.getResources().getString(2131820819));
            fileTypeModel2 = fileTypeModel6;
        }
        else {
            final FileTypeModel fileTypeModel7 = new FileTypeModel(FileType.KDX, "");
            fileTypeModel7.setInfo(context.getResources().getString(2131820820));
            fileTypeModel2 = fileTypeModel7;
        }
        return fileTypeModel2;
    }
    
    public int getCount() {
        return this.fileTypeModelList.size();
    }
    
    public int getHeight() {
        return DensityUtil.dip2px(45.0f) + 0 + (this.getCount() - 1) * DensityUtil.dip2px(38.0f);
    }
    
    public FileTypeModel getItem(final int n) {
        return (FileTypeModel)this.fileTypeModelList.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getItemViewType(final int n) {
        if (n == 0) {
            return 1;
        }
        return 2;
    }
    
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        final int itemViewType = this.getItemViewType(n);
        Object tag;
        if (view == null) {
            if (itemViewType == 1) {
                view = from.inflate(2131492945, (ViewGroup)null);
            }
            else {
                view = from.inflate(2131492944, (ViewGroup)null);
            }
            tag = new ViewHolder(view);
            view.setTag(tag);
        }
        else {
            tag = view.getTag();
        }
        final FileTypeModel item = this.getItem(n);
        ((ViewHolder)tag).tv_format.setText((CharSequence)item.getType().extension.replace((CharSequence)".", (CharSequence)""));
        ((ViewHolder)tag).tv_versionName.setText((CharSequence)String.format("%s", new Object[] { item.getVersionName() }));
        return view;
    }
    
    class ViewHolder
    {
        final FileTypeChooseAdapter this$0;
        TextView tv_format;
        TextView tv_versionName;
        
        public ViewHolder(final FileTypeChooseAdapter this$0, final View view) {
            this.this$0 = this$0;
            this.tv_format = (TextView)view.findViewById(2131297597);
            this.tv_versionName = (TextView)view.findViewById(2131297688);
        }
    }
}
