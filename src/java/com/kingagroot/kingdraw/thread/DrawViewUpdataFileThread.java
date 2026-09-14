package com.kingagroot.kingdraw.thread;

import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.base.MApplication;
import android.os.Handler;
import android.os.Looper;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import java.io.File;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.core.image.DrawOption;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.MThumbImageDrawOption;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.kingdraw.core.FileWriter;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.core.FileWriter$Builder;

public class DrawViewUpdataFileThread implements Runnable
{
    FileWriter$Builder builder;
    DrawFileDataI drawFileDataI;
    private onDrawViewUpdataFileListener drawViewUpdataFileListener;
    ProtocolTypeEnum fileType;
    private final FolderFileModel folderFileModel;
    GFormatValue formatValue;
    private final KingDrawView kingDrawView;
    boolean writeFileState;
    
    public DrawViewUpdataFileThread(final FolderFileModel folderFileModel, final ProtocolTypeEnum fileType, final KingDrawView kingDrawView) {
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.writeFileState = false;
        this.kingDrawView = kingDrawView;
        this.fileType = fileType;
        this.formatValue = GFormatValue.formatValue(kingDrawView.getFormatValue());
        this.folderFileModel = folderFileModel;
        this.builder = FileWriter.getBuilder().setNeedSmiles(true).setNeedProtocolContent(true).setNeedKDJson(true).withView(kingDrawView);
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        final String fileNameNoExtension = this.folderFileModel.getFileNameNoExtension();
        final StringBuilder sb = new StringBuilder();
        sb.append(fileNameNoExtension);
        sb.append(System.currentTimeMillis());
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(FileConfig.DRAW_FILE_PIC_PATH);
        sb2.append(string);
        sb2.append(".");
        sb2.append((Object)Bitmap$CompressFormat.PNG);
        final String string2 = sb2.toString();
        this.folderFileModel.setPicPath(string2);
        this.folderFileModel.setModifyTime(currentTimeMillis);
        if (this.fileType == ProtocolTypeEnum.KDX) {
            final String allElementsJson = this.kingDrawView.getAllElementsJson();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("thumb_");
            sb3.append(string);
            final String string3 = sb3.toString();
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
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("png_");
            sb4.append(string);
            final String string4 = sb4.toString();
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
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("jpg_");
            sb5.append(string);
            final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(this.formatValue.formatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, sb5.toString());
            imageDrawBuilder.onDestroy();
            if (saveToFile3) {
                filePath = imageDrawBuilder.getFilePath();
            }
            this.builder.setKDXImageSource(filePath2, filePath3, filePath);
        }
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(System.currentTimeMillis());
        sb6.append("");
        final String string5 = sb6.toString();
        final boolean writeToFile = this.builder.writeToFile(FileConfig.DRAW_FILE_PATH, string5, this.fileType);
        this.writeFileState = writeToFile;
        if (writeToFile) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append(FileConfig.DRAW_FILE_PATH);
            sb7.append(string5);
            sb7.append(this.fileType.extension);
            final String string6 = sb7.toString();
            if (new File(string6).exists()) {
                final File file = new File(this.folderFileModel.getFilePath());
                if (file.exists()) {
                    file.delete();
                }
                this.folderFileModel.setFileType(this.fileType.toString());
                final FolderFileModel folderFileModel = this.folderFileModel;
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(fileNameNoExtension);
                sb8.append(this.fileType.extension);
                folderFileModel.setFileName(sb8.toString());
                this.folderFileModel.setFileExtension(this.fileType.extension);
                this.folderFileModel.setFilePath(string6);
            }
        }
        final String smiles = this.builder.getSmiles();
        final String json = this.builder.getJson();
        this.folderFileModel.setFileSmiles(smiles);
        if (this.fileType != ProtocolTypeEnum.KDX) {
            final MThumbImageDrawOption drawOption2 = new MThumbImageDrawOption();
            drawOption2.setMark(false);
            final ImageDrawBuilder imageDrawBuilder2 = new ImageDrawBuilder();
            imageDrawBuilder2.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f).formatValue()).setDrawWithJson(json).setDrawOption((DrawOption)drawOption2).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string);
            imageDrawBuilder2.onDestroy();
        }
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
            final DrawViewUpdataFileThread this$0;
            
            public void run() {
                OperationState updataFolderFileModel = new OperationState(false, MApplication.getInstance().getString(2131820857));
                if (this.this$0.writeFileState) {
                    final OperationState operationState = updataFolderFileModel = this.this$0.drawFileDataI.updataFolderFileModel(this.this$0.folderFileModel);
                    if (operationState.isSuccess) {
                        operationState.data = this.this$0.folderFileModel;
                        updataFolderFileModel = operationState;
                    }
                }
                if (this.this$0.drawViewUpdataFileListener != null) {
                    this.this$0.drawViewUpdataFileListener.onFinish(updataFolderFileModel);
                }
            }
        });
    }
    
    public void setDrawViewSaveFileListener(final onDrawViewUpdataFileListener drawViewUpdataFileListener) {
        this.drawViewUpdataFileListener = drawViewUpdataFileListener;
    }
    
    public interface onDrawViewUpdataFileListener
    {
        void onFinish(final OperationState p0);
    }
}
