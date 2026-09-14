package com.kingagroot.kingdraw.ui;

import android.os.Bundle;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.kingagroot.kingdraw.NewMainActivity;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.core.tool.ClipboardData;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.core.FileWriter$Builder;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.Intent;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.SearchPaletteResultModel;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.core.image.DrawOption;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.MThumbImageDrawOption;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import java.io.File;
import java.io.IOException;
import com.kingagroot.kingdraw.core.FileReader$Builder;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.kingdraw.core.FileReader;
import com.kingagroot.kingdraw.palette.NewPaletteRotateActivity$WarringCallback;
import com.kingagroot.kingdraw.core.FileWriter;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.NativeHandlerParam;
import android.os.CountDownTimer;

public class NativeSearchPaletteActivity extends NewSearchPaletteActivity
{
    public static final String INTENT_KEY_NATIVEHANDLERPARAM = "intent_key_nativehandlerparam";
    private CountDownTimer countDownTimer;
    private NativeHandlerParam nativeHandlerParam;
    private ProtocolTypeEnum protocolTypeEnum;
    
    private void autoSave() {
        if (this.isSaving()) {
            return;
        }
        this.startSave();
        new Thread((Runnable)new NativeSearchPaletteActivity$2(this)).start();
    }
    
    private void createPalette() {
        this.kingDraw.createPalette(new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f).formatValue(), new PaletteConfigModel());
    }
    
    private String getMolV3000() {
        return FileWriter.getBuilder().setNeedSmiles(false).setNeedProtocolContent(true).setNeedKDJson(false).withView(this.kingDraw).writeToString(ProtocolTypeEnum.MOL_V3000);
    }
    
    private void initFinish() {
        this.runOnUiThread((Runnable)new _$$Lambda$NativeSearchPaletteActivity$tzDnsaxCs6xx01L8Pwu96vwyOjk(this));
    }
    
    private void kingVersionWarringDialog(final NewPaletteRotateActivity$WarringCallback newPaletteRotateActivity$WarringCallback) {
        this.runOnUiThread((Runnable)new _$$Lambda$NativeSearchPaletteActivity$9ULfCUYhNZnR_ezmbXES9t5fOME(this, newPaletteRotateActivity$WarringCallback));
    }
    
    private void openFileByModel(final String s) {
        new Thread((Runnable)new _$$Lambda$NativeSearchPaletteActivity$9BZiFQO38adyPxFewy6bD1fRu00(this, s)).start();
    }
    
    private void readFail() {
        this.runOnUiThread((Runnable)new _$$Lambda$NativeSearchPaletteActivity$w1CW_4SAS_f9pS2IblcvH8catGE(this));
    }
    
    private void readFolderModel(final String s) throws IOException {
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        final FileReader$Builder builder = FileReader.getBuilder();
        builder.withView(this.kingDraw, readerFormatForType.formatValue());
        builder.readByPath(s);
        this.initFinish();
    }
    
    private void saveFile() {
        final FileWriter$Builder withView = FileWriter.getBuilder().setNeedSmiles(true).setNeedProtocolContent(true).setNeedKDJson(true).withView(this.kingDraw);
        final String parent = new File(this.nativeHandlerParam.filePath).getParent();
        final String fileNameNoExtension = DrawFileUtil.getFileNameNoExtension(this.nativeHandlerParam.fileName);
        final StringBuilder sb = new StringBuilder();
        sb.append(fileNameNoExtension);
        sb.append(System.currentTimeMillis());
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(parent);
        sb2.append(string);
        sb2.append(".");
        sb2.append((Object)Bitmap$CompressFormat.PNG);
        final String string2 = sb2.toString();
        final String allElementsJson = this.kingDraw.getAllElementsJson();
        if (this.protocolTypeEnum == ProtocolTypeEnum.KDX) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("thumb_");
            sb3.append(string);
            final String string3 = sb3.toString();
            final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
            drawOption.setMark(false);
            final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
            final boolean saveToFile = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.kingDraw.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)drawOption).saveToFile(parent, string3);
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
            final boolean saveToFile2 = imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(this.kingDraw.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(parent, string4);
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
            final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(this.kingDraw.getFormatValue()).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(parent, sb5.toString());
            imageDrawBuilder.onDestroy();
            if (saveToFile3) {
                filePath = imageDrawBuilder.getFilePath();
            }
            withView.setKDXImageSource(filePath2, filePath3, filePath);
        }
        withView.writeToFile(parent, fileNameNoExtension, this.protocolTypeEnum);
        final String smiles = withView.getSmiles();
        final String molV3000 = this.getMolV3000();
        final SearchPaletteResultModel searchPaletteResultModel = new SearchPaletteResultModel();
        searchPaletteResultModel.taskId = this.nativeHandlerParam.taskId;
        searchPaletteResultModel.mol = molV3000;
        searchPaletteResultModel.smiles = smiles;
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(parent);
        sb6.append(File.separator);
        sb6.append(fileNameNoExtension);
        sb6.append(this.protocolTypeEnum.extension);
        searchPaletteResultModel.kdx = sb6.toString();
        searchPaletteResultModel.kdjson = allElementsJson;
        searchPaletteResultModel.material_data = this.kingDraw.getJsonMaterial();
        final Intent intent = new Intent();
        intent.setAction("intent_filter_opennetchemfilehandler");
        intent.putExtra("data", (Serializable)searchPaletteResultModel);
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
    }
    
    @Override
    public void onElementChanged(final boolean b) {
        super.onElementChanged(b);
        final CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer.start();
        }
    }
    
    @Override
    public void onKingDrawViewAvailable() {
        final Bundle extras = this.getIntent().getExtras();
        new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        final boolean b = false;
        final int n = 0;
        int n2 = b ? 1 : 0;
        if (extras != null) {
            final NativeHandlerParam nativeHandlerParam = (NativeHandlerParam)extras.getSerializable("intent_key_nativehandlerparam");
            this.nativeHandlerParam = nativeHandlerParam;
            n2 = (b ? 1 : 0);
            if (nativeHandlerParam != null) {
                this.protocolTypeEnum = ProtocolTypeEnum.KDX;
                int n3 = n;
                if (new File(this.nativeHandlerParam.filePath).exists()) {
                    n3 = 1;
                    this.openFileByModel(this.nativeHandlerParam.filePath);
                }
                n2 = n3;
                if (this.nativeHandlerParam.autoSave) {
                    n2 = n3;
                    if (this.nativeHandlerParam.saveInterval > 0L) {
                        (this.countDownTimer = (CountDownTimer)new NativeSearchPaletteActivity$1(this, this.nativeHandlerParam.saveInterval, this.nativeHandlerParam.saveInterval)).start();
                        n2 = n3;
                    }
                }
            }
        }
        if (n2 == 0) {
            this.createPalette();
            this.initFinish();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        final CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        final CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer.start();
        }
    }
    
    @Override
    public void onSearchDone() {
        final CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (this.nativeHandlerParam != null) {
            if (this.isSaving()) {
                return;
            }
            this.startSave();
            this.saveFile();
            this.endSave();
        }
        this.finish();
    }
}
