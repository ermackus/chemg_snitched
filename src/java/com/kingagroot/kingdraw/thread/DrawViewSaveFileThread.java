package com.kingagroot.kingdraw.thread;

import android.os.Handler;
import android.os.Looper;
import com.kingagroot.kingdraw.core.PaletteManager;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.core.image.DrawOption;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.MThumbImageDrawOption;
import java.util.UUID;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.component.ui.view.OperationState;
import android.content.Intent;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.core.FileWriter;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.core.FileWriter$Builder;

public class DrawViewSaveFileThread implements Runnable
{
    FileWriter$Builder builder;
    boolean destroyPalete;
    DrawFileDataI drawFileDataI;
    private onDrawViewSaveFileListener drawViewSaveFileListener;
    String fileName;
    ProtocolTypeEnum fileType;
    private FolderFileModel folderFileModel;
    GFormatValue formatValue;
    private final KingDrawView kingDrawView;
    boolean writeFileState;
    
    public DrawViewSaveFileThread(final String fileName, final ProtocolTypeEnum fileType, final KingDrawView kingDrawView) {
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.destroyPalete = false;
        this.writeFileState = false;
        this.kingDrawView = kingDrawView;
        this.fileName = fileName;
        this.fileType = fileType;
        String autoName = fileName;
        if (TextUtils.isEmpty((CharSequence)fileName)) {
            autoName = DrawFileUtil.getAutoName();
        }
        this.fileName = autoName;
        this.formatValue = GFormatValue.formatValue(kingDrawView.getFormatValue());
        this.builder = FileWriter.getBuilder().setNeedSmiles(true).setNeedProtocolContent(true).setNeedKDJson(true).withView(kingDrawView);
    }
    
    private void sendDataChangeMsg(final boolean b) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.getInstance());
        final Intent intent = new Intent("local_data_changed");
        intent.putExtra("datachange_key", b);
        instance.sendBroadcast(intent);
    }
    
    public OperationState checkFileNameAvailable() {
        final DrawFileDataI drawFileDataI = this.drawFileDataI;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.fileName);
        sb.append(this.fileType.extension);
        return drawFileDataI.checkFileNameAvailable(sb.toString());
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        String fileName;
        if (TextUtils.isEmpty((CharSequence)this.fileName)) {
            fileName = DrawFileUtil.getAutoName();
        }
        else {
            fileName = this.fileName;
        }
        this.fileName = fileName;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.fileName);
        sb.append(System.currentTimeMillis());
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(FileConfig.DRAW_FILE_PIC_PATH);
        sb2.append(string);
        sb2.append(".");
        sb2.append((Object)Bitmap$CompressFormat.PNG);
        final String string2 = sb2.toString();
        (this.folderFileModel = new FolderFileModel()).setId(UUID.randomUUID().toString());
        this.folderFileModel.setFileType(this.fileType.toString());
        final FolderFileModel folderFileModel = this.folderFileModel;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(this.fileName);
        sb3.append(this.fileType.extension);
        folderFileModel.setFileName(sb3.toString());
        this.folderFileModel.setFileExtension(this.fileType.extension);
        final FolderFileModel folderFileModel2 = this.folderFileModel;
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(FileConfig.DRAW_FILE_PATH);
        sb4.append(this.fileName);
        sb4.append(this.fileType.extension);
        folderFileModel2.setFilePath(sb4.toString());
        this.folderFileModel.setPicPath(string2);
        this.folderFileModel.setCreateTime(currentTimeMillis);
        this.folderFileModel.setModifyTime(currentTimeMillis);
        if (this.fileType == ProtocolTypeEnum.KDX) {
            final String allElementsJson = this.kingDrawView.getAllElementsJson();
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("thumb_");
            sb5.append(string);
            final String string3 = sb5.toString();
            final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
            drawOption.setMark(false);
            final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
            final boolean saveToFile = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.formatValue.formatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)drawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string3);
            imageDrawBuilder.onDestroy();
            String filePath = null;
            String filePath2;
            if (saveToFile) {
                filePath2 = imageDrawBuilder.getFilePath();
                FileUtil.copyFile(filePath2, string2);
            }
            else {
                filePath2 = null;
            }
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("png_");
            sb6.append(string);
            final String string4 = sb6.toString();
            final ImageFileDrawOption imageFileDrawOption = new ImageFileDrawOption();
            new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(imageFileDrawOption)).checkExportPic(this.kingDrawView.getContext());
            final boolean saveToFile2 = imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.formatValue.formatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string4);
            imageDrawBuilder.onDestroy();
            String filePath3;
            if (saveToFile2) {
                filePath3 = imageDrawBuilder.getFilePath();
            }
            else {
                filePath3 = null;
            }
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("jpg_");
            sb7.append(string);
            final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(this.formatValue.formatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, sb7.toString());
            imageDrawBuilder.onDestroy();
            if (saveToFile3) {
                filePath = imageDrawBuilder.getFilePath();
            }
            this.builder.setKDXImageSource(filePath2, filePath3, filePath);
        }
        this.writeFileState = this.builder.writeToFile(FileConfig.DRAW_FILE_PATH, this.fileName, this.fileType);
        final String smiles = this.builder.getSmiles();
        this.builder.getJson();
        this.folderFileModel.setFileSmiles(smiles);
        if (this.fileType != ProtocolTypeEnum.KDX) {
            final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
            final MThumbImageDrawOption drawOption2 = new MThumbImageDrawOption();
            drawOption2.setMark(false);
            final ImageDrawBuilder imageDrawBuilder2 = new ImageDrawBuilder();
            imageDrawBuilder2.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(readerFormatForType.formatValue()).setDrawWidthFilePath(this.folderFileModel.getFilePath()).setDrawOption((DrawOption)drawOption2).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string);
            imageDrawBuilder2.onDestroy();
        }
        if (this.destroyPalete) {
            PaletteManager.deletePalette(this.kingDrawView.getPaletteId());
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
            final DrawViewSaveFileThread this$0;
            
            public void run() {
                OperationState saveFolderFileModel = new OperationState(false, MApplication.getInstance().getString(2131820857));
                if (this.this$0.writeFileState) {
                    final OperationState operationState = saveFolderFileModel = this.this$0.drawFileDataI.saveFolderFileModel(this.this$0.folderFileModel);
                    if (operationState.isSuccess) {
                        operationState.data = this.this$0.folderFileModel;
                        saveFolderFileModel = operationState;
                    }
                }
                if (this.this$0.drawViewSaveFileListener != null) {
                    this.this$0.drawViewSaveFileListener.onFinish(saveFolderFileModel);
                }
                if (this.this$0.destroyPalete && saveFolderFileModel.isSuccess) {
                    this.this$0.sendDataChangeMsg(true);
                }
            }
        });
    }
    
    public void setDrawViewSaveFileListener(final onDrawViewSaveFileListener drawViewSaveFileListener) {
        this.drawViewSaveFileListener = drawViewSaveFileListener;
    }
    
    public void setNeedDestroyPalete(final boolean destroyPalete) {
        this.destroyPalete = destroyPalete;
    }
    
    public interface onDrawViewSaveFileListener
    {
        void onFinish(final OperationState p0);
    }
}
