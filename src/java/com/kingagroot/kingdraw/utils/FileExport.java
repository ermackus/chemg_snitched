package com.kingagroot.kingdraw.utils;

import com.kingagroot.kingdraw.core.data.ProtocolWriter$Builer;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.data.DataOutModel;
import java.io.IOException;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.core.data.ProtocolWriter;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.core.FileWriter$Builder;
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
import com.kingagroot.kingdraw.core.FileWriter;
import java.util.UUID;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.model.FileResultStatusEnum;
import com.kingagroot.kingdraw.model.FileResultModel;
import android.os.Handler;
import android.os.Looper;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.concurrent.Executors;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import java.util.concurrent.Executor;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;

public class FileExport
{
    DrawFileDataI drawFileDataI;
    Executor executor;
    OnFileExportListner onFileExportListner;
    
    public FileExport() {
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.executor = (Executor)Executors.newFixedThreadPool(1);
    }
    
    private void error() {
        if (this.onFileExportListner != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                final FileExport this$0;
                
                public void run() {
                    final FileResultModel fileResultModel = new FileResultModel();
                    fileResultModel.status = FileResultStatusEnum.\u5931\u8d25.code;
                    this.this$0.onFileExportListner.onFinish(fileResultModel);
                }
            });
        }
    }
    
    private void error(final int n) {
        if (this.onFileExportListner != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, n) {
                final FileExport this$0;
                final int val$errorCode;
                
                public void run() {
                    final FileResultModel fileResultModel = new FileResultModel();
                    fileResultModel.status = this.val$errorCode;
                    this.this$0.onFileExportListner.onFinish(fileResultModel);
                }
            });
        }
    }
    
    private void start() {
        if (this.onFileExportListner != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                final FileExport this$0;
                
                public void run() {
                    this.this$0.onFileExportListner.onStart();
                }
            });
        }
    }
    
    private void sucess(final FolderFileModel folderFileModel) {
        if (this.onFileExportListner != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, folderFileModel) {
                final FileExport this$0;
                final FolderFileModel val$folderFileModel;
                
                public void run() {
                    final FileResultModel fileResultModel = new FileResultModel();
                    fileResultModel.status = FileResultStatusEnum.\u6210\u529f.code;
                    fileResultModel.object = this.val$folderFileModel;
                    this.this$0.onFileExportListner.onFinish(fileResultModel);
                }
            });
        }
    }
    
    public void exportAsFile(final String s, final KingDrawView kingDrawView, final FileType fileType, final OnFileExportListner onFileExportListner) {
        this.onFileExportListner = onFileExportListner;
        this.start();
        this.executor.execute((Runnable)new Runnable(this, fileType, kingDrawView, s) {
            final FileExport this$0;
            final FileType val$fileType;
            final KingDrawView val$kingDrawView;
            final String val$newFileName;
            
            public void run() {
                final FileType val$fileType = this.val$fileType;
                final FileType png = FileType.PNG;
                final boolean b = false;
                if (val$fileType != png && this.val$fileType != FileType.JPG) {
                    if (this.val$fileType == FileType.MOL_V2000 && ProtocolUtils.exceedMolV2000(this.val$kingDrawView.getAllElementsJson())) {
                        this.this$0.error(FileResultStatusEnum.molv2000\u8d85\u8fc7\u9650\u5236.code);
                    }
                    else {
                        final ProtocolTypeEnum valueOfCode = ProtocolTypeEnum.valueOfCode(this.val$fileType.code);
                        int n = b ? 1 : 0;
                        if (valueOfCode != null) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            final StringBuilder sb = new StringBuilder();
                            sb.append(this.val$newFileName);
                            sb.append(this.val$fileType.extension);
                            final String string = sb.toString();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.val$newFileName);
                            sb2.append(System.currentTimeMillis());
                            final String string2 = sb2.toString();
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(FileConfig.DRAW_FILE_PIC_PATH);
                            sb3.append(string2);
                            sb3.append(".");
                            sb3.append((Object)Bitmap$CompressFormat.PNG);
                            final String string3 = sb3.toString();
                            final FolderFileModel folderFileModel = new FolderFileModel();
                            folderFileModel.setId(UUID.randomUUID().toString());
                            folderFileModel.setFileType(this.val$fileType.toString());
                            folderFileModel.setFileName(string);
                            folderFileModel.setFileExtension(this.val$fileType.extension);
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append(FileConfig.DRAW_FILE_PATH);
                            sb4.append(string);
                            folderFileModel.setFilePath(sb4.toString());
                            folderFileModel.setPicPath(string3);
                            folderFileModel.setCreateTime(currentTimeMillis);
                            folderFileModel.setModifyTime(currentTimeMillis);
                            final FileWriter$Builder withView = FileWriter.getBuilder().setNeedSmiles(true).setNeedKDJson(true).withView(this.val$kingDrawView);
                            if (valueOfCode == ProtocolTypeEnum.KDX) {
                                final String allElementsJson = this.val$kingDrawView.getAllElementsJson();
                                final StringBuilder sb5 = new StringBuilder();
                                sb5.append("thumb_");
                                sb5.append(string2);
                                final String string4 = sb5.toString();
                                final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
                                drawOption.setMark(false);
                                final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
                                final boolean saveToFile = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.val$kingDrawView.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)drawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string4);
                                imageDrawBuilder.onDestroy();
                                String filePath;
                                if (saveToFile) {
                                    filePath = imageDrawBuilder.getFilePath();
                                    FileUtil.copyFile(filePath, string3);
                                }
                                else {
                                    filePath = null;
                                }
                                final StringBuilder sb6 = new StringBuilder();
                                sb6.append("png_");
                                sb6.append(string2);
                                final String string5 = sb6.toString();
                                final ImageFileDrawOption imageFileDrawOption = new ImageFileDrawOption();
                                new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(imageFileDrawOption)).checkExportPic(this.val$kingDrawView.getContext());
                                final boolean saveToFile2 = imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.val$kingDrawView.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string5);
                                imageDrawBuilder.onDestroy();
                                String filePath2;
                                if (saveToFile2) {
                                    filePath2 = imageDrawBuilder.getFilePath();
                                }
                                else {
                                    filePath2 = null;
                                }
                                final StringBuilder sb7 = new StringBuilder();
                                sb7.append("jpg_");
                                sb7.append(string2);
                                final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(this.val$kingDrawView.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, sb7.toString());
                                imageDrawBuilder.onDestroy();
                                String filePath3;
                                if (saveToFile3) {
                                    filePath3 = imageDrawBuilder.getFilePath();
                                }
                                else {
                                    filePath3 = null;
                                }
                                withView.setKDXImageSource(filePath, filePath2, filePath3);
                            }
                            final boolean writeToFile = withView.writeToFile(FileConfig.DRAW_FILE_PATH, this.val$newFileName, valueOfCode);
                            final String smiles = withView.getSmiles();
                            final String json = withView.getJson();
                            folderFileModel.setFileSmiles(smiles);
                            n = (b ? 1 : 0);
                            if (writeToFile) {
                                if (valueOfCode != ProtocolTypeEnum.KDX) {
                                    final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
                                    final ImageDrawBuilder imageDrawBuilder2 = new ImageDrawBuilder();
                                    final MThumbImageDrawOption drawOption2 = new MThumbImageDrawOption();
                                    drawOption2.setMark(false);
                                    imageDrawBuilder2.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setDrawOption((DrawOption)drawOption2).setFormatValue(readerFormatForType.formatValue()).setDrawWithJson(json).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string2);
                                    imageDrawBuilder2.onDestroy();
                                }
                                n = (b ? 1 : 0);
                                if (this.this$0.drawFileDataI.saveFolderFileModel(folderFileModel).isSuccess) {
                                    this.this$0.sucess(folderFileModel);
                                    n = 1;
                                }
                            }
                        }
                        if (n == 0) {
                            this.this$0.error();
                        }
                    }
                }
                else {
                    Bitmap$CompressFormat bitmap$CompressFormat;
                    if (this.val$fileType == FileType.PNG) {
                        bitmap$CompressFormat = Bitmap$CompressFormat.PNG;
                    }
                    else {
                        bitmap$CompressFormat = Bitmap$CompressFormat.JPEG;
                    }
                    final ImageDrawBuilder imageDrawBuilder3 = new ImageDrawBuilder();
                    if (this.val$fileType == FileType.JPG) {
                        imageDrawBuilder3.setBackgroundColor(-1);
                    }
                    else {
                        imageDrawBuilder3.setBackgroundColor(0);
                    }
                    final ImageFileDrawOption drawOption3 = new ImageFileDrawOption();
                    new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(drawOption3)).checkExportPic(this.val$kingDrawView.getContext());
                    imageDrawBuilder3.setProofStatus(false).setCompressFormat(bitmap$CompressFormat, 100).setFormatValue(this.val$kingDrawView.getFormatValue()).setDrawWithView(this.val$kingDrawView).setDrawOption((DrawOption)drawOption3);
                    final boolean saveBitmapToPicture = DrawFileUtil.saveBitmapToPicture(imageDrawBuilder3.getDrawBitmap(), this.val$newFileName, 100, bitmap$CompressFormat);
                    imageDrawBuilder3.onDestroy();
                    if (saveBitmapToPicture) {
                        this.this$0.sucess(null);
                    }
                    else {
                        this.this$0.error();
                    }
                }
            }
        });
    }
    
    public void exportAsFile(final String s, final FolderFileModel folderFileModel, final FileType fileType, final OnFileExportListner onFileExportListner) {
        this.onFileExportListner = onFileExportListner;
        this.start();
        this.executor.execute((Runnable)new Runnable(this, fileType, folderFileModel, s) {
            final FileExport this$0;
            final FileType val$fileType;
            final FolderFileModel val$model;
            final String val$newFileName;
            
            public void run() {
                if (this.val$fileType != FileType.PNG && this.val$fileType != FileType.JPG) {
                    try {
                        final DataOutModel byPath = ProtocolReader.builder().setNeedKDJson(true).readByPath(this.val$model.getFilePath());
                        if (this.val$fileType == FileType.MOL_V2000 && ProtocolUtils.exceedMolV2000(byPath.josn)) {
                            this.this$0.error(FileResultStatusEnum.molv2000\u8d85\u8fc7\u9650\u5236.code);
                        }
                        else {
                            final ProtocolTypeEnum valueOfCode = ProtocolTypeEnum.valueOfCode(this.val$fileType.code);
                            final long currentTimeMillis = System.currentTimeMillis();
                            final StringBuilder sb = new StringBuilder();
                            sb.append(this.val$newFileName);
                            sb.append(valueOfCode.extension);
                            final String string = sb.toString();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.val$newFileName);
                            sb2.append(System.currentTimeMillis());
                            final String string2 = sb2.toString();
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(FileConfig.DRAW_FILE_PIC_PATH);
                            sb3.append(string2);
                            sb3.append(".");
                            sb3.append((Object)Bitmap$CompressFormat.PNG);
                            final String string3 = sb3.toString();
                            final GFormatValue fromatValue = this.val$model.getFromatValue();
                            FormatValue formatValue;
                            if (fromatValue != null) {
                                formatValue = fromatValue.formatValue();
                            }
                            else {
                                formatValue = null;
                            }
                            boolean b = false;
                            Label_0960: {
                                if (valueOfCode != null) {
                                    final ProtocolWriter$Builer setNeedKDJson = ProtocolWriter.builder().setNeedKDJson(true);
                                    if (valueOfCode == ProtocolTypeEnum.KDX) {
                                        final String josn = byPath.josn;
                                        final StringBuilder sb4 = new StringBuilder();
                                        sb4.append("thumb_");
                                        sb4.append(string2);
                                        final String string4 = sb4.toString();
                                        final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
                                        drawOption.setMark(false);
                                        final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
                                        final boolean saveToFile = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(josn).setDrawOption((DrawOption)drawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string4);
                                        imageDrawBuilder.onDestroy();
                                        String filePath;
                                        if (saveToFile) {
                                            filePath = imageDrawBuilder.getFilePath();
                                            FileUtil.copyFile(filePath, string3);
                                        }
                                        else {
                                            filePath = null;
                                        }
                                        final StringBuilder sb5 = new StringBuilder();
                                        sb5.append("png_");
                                        sb5.append(string2);
                                        final String string5 = sb5.toString();
                                        final ImageFileDrawOption imageFileDrawOption = new ImageFileDrawOption();
                                        new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(imageFileDrawOption)).checkExportPic(MApplication.getInstance().getApplicationContext());
                                        final boolean saveToFile2 = imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(josn).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string5);
                                        imageDrawBuilder.onDestroy();
                                        String filePath2;
                                        if (saveToFile2) {
                                            filePath2 = imageDrawBuilder.getFilePath();
                                        }
                                        else {
                                            filePath2 = null;
                                        }
                                        final StringBuilder sb6 = new StringBuilder();
                                        sb6.append("jpg_");
                                        sb6.append(string2);
                                        final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(josn).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, sb6.toString());
                                        imageDrawBuilder.onDestroy();
                                        String filePath3;
                                        if (saveToFile3) {
                                            filePath3 = imageDrawBuilder.getFilePath();
                                        }
                                        else {
                                            filePath3 = null;
                                        }
                                        setNeedKDJson.setKDXImageSource(filePath, filePath2, filePath3);
                                    }
                                    if (setNeedKDJson.writeToFile(byPath.josn, FileConfig.DRAW_FILE_PATH, this.val$newFileName, valueOfCode)) {
                                        final String json = setNeedKDJson.getJson();
                                        final FolderFileModel folderFileModel = new FolderFileModel();
                                        folderFileModel.setId(UUID.randomUUID().toString());
                                        folderFileModel.setFileType(this.val$fileType.toString());
                                        folderFileModel.setFileName(string);
                                        folderFileModel.setFileExtension(this.val$fileType.extension);
                                        final StringBuilder sb7 = new StringBuilder();
                                        sb7.append(FileConfig.DRAW_FILE_PATH);
                                        sb7.append(string);
                                        folderFileModel.setFilePath(sb7.toString());
                                        folderFileModel.setPicPath(string3);
                                        folderFileModel.setCreateTime(currentTimeMillis);
                                        folderFileModel.setModifyTime(currentTimeMillis);
                                        folderFileModel.setFileSmiles(this.val$model.getFileSmiles());
                                        if (valueOfCode != ProtocolTypeEnum.KDX) {
                                            final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
                                            final ImageDrawBuilder imageDrawBuilder2 = new ImageDrawBuilder();
                                            final MThumbImageDrawOption drawOption2 = new MThumbImageDrawOption();
                                            drawOption2.setMark(false);
                                            imageDrawBuilder2.setBackgroundColor(-1).setProofStatus(false).setFormatValue(readerFormatForType.formatValue()).setDrawWithJson(json).setDrawOption((DrawOption)drawOption2).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string2);
                                            imageDrawBuilder2.onDestroy();
                                        }
                                        if (this.this$0.drawFileDataI.saveFolderFileModel(folderFileModel).isSuccess) {
                                            this.this$0.sucess(folderFileModel);
                                            b = true;
                                            break Label_0960;
                                        }
                                    }
                                }
                                b = false;
                            }
                            if (!b) {
                                this.this$0.error();
                            }
                        }
                    }
                    catch (final IOException ex) {
                        this.this$0.error();
                    }
                }
                else {
                    Bitmap$CompressFormat bitmap$CompressFormat;
                    if (this.val$fileType == FileType.PNG) {
                        bitmap$CompressFormat = Bitmap$CompressFormat.PNG;
                    }
                    else {
                        bitmap$CompressFormat = Bitmap$CompressFormat.JPEG;
                    }
                    final ImageDrawBuilder imageDrawBuilder3 = new ImageDrawBuilder();
                    final GFormatValue fromatValue2 = this.val$model.getFromatValue();
                    FormatValue formatValue2;
                    if (fromatValue2 != null) {
                        formatValue2 = fromatValue2.formatValue();
                    }
                    else {
                        formatValue2 = null;
                    }
                    if (this.val$fileType == FileType.JPG) {
                        imageDrawBuilder3.setBackgroundColor(-1);
                    }
                    else {
                        imageDrawBuilder3.setBackgroundColor(0);
                    }
                    final ImageFileDrawOption drawOption3 = new ImageFileDrawOption();
                    new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(drawOption3)).checkExportPic(MApplication.getInstance().getApplicationContext());
                    imageDrawBuilder3.setProofStatus(false).setCompressFormat(bitmap$CompressFormat, 100).setDrawOption((DrawOption)drawOption3).setFormatValue(formatValue2).setDrawWidthFilePath(this.val$model.getFilePath());
                    final boolean saveBitmapToPicture = DrawFileUtil.saveBitmapToPicture(imageDrawBuilder3.getDrawBitmap(), this.val$newFileName, 100, bitmap$CompressFormat);
                    imageDrawBuilder3.onDestroy();
                    if (saveBitmapToPicture) {
                        this.this$0.sucess(null);
                    }
                    else {
                        this.this$0.error();
                    }
                }
            }
        });
    }
    
    public interface OnFileExportListner
    {
        void onFinish(final FileResultModel p0);
        
        void onStart();
    }
}
