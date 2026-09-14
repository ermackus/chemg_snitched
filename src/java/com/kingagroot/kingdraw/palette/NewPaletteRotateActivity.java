package com.kingagroot.kingdraw.palette;

import android.os.AsyncTask;
import com.kingagroot.kingdraw.ui.SUPTableActivity;
import com.hjq.permissions.OnPermissionCallback;
import android.os.Build$VERSION;
import com.hjq.permissions.XXPermissions;
import com.goodsrc.ui.library.MANServiceConfig;
import com.kingagroot.component.ui.widget.EditPop;
import com.kingagroot.component.ui.widget.EditPop$OnEditPopListener;
import com.kingagroot.component.ui.widget.EditPop$Build;
import android.view.KeyEvent;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import com.kingagroot.kingdraw.core.GestureRecognizeResultCallBack;
import com.kingagroot.kingdraw.core.GestureCallBack;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction$OnSildeFlingGestureActionListener;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;
import com.kingagroot.component.ui.menu.SildeFlingGestureAction;
import java.io.Serializable;
import com.kingagroot.kingdraw.ui.OtherSettingActivity;
import com.kingagroot.kingdraw.limit.LimitPedia$OnPediaListener;
import com.kingagroot.kingdraw.limit.LimitPedia;
import android.os.Bundle;
import com.kingagroot.kingdraw.thread.DrawViewUpdataFileThread$onDrawViewUpdataFileListener;
import com.kingagroot.kingdraw.thread.DrawViewUpdataFileThread;
import com.kingagroot.kingdraw.thread.ThreadPoolManager;
import com.kingagroot.kingdraw.thread.DrawViewSaveFileThread$onDrawViewSaveFileListener;
import com.kingagroot.kingdraw.thread.DrawViewSaveFileThread;
import android.view.View;
import com.kingagroot.component.ui.widget.AnalysisPop;
import com.kingagroot.kingdraw.core.tool.ChemisAnalysisTool;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import com.kingagroot.kingdraw.core.data.DataOutModel;
import com.kingagroot.kingdraw.core.data.IUPACConverter$IUPACConverterListener;
import com.kingagroot.kingdraw.core.data.IUPACConverter;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.limit.LimitPic$OnExportPicCheck;
import com.kingagroot.kingdraw.limit.LimitPic;
import com.kingagroot.kingdraw.limit.LimitSupExport$OnSupExportListener;
import com.kingagroot.kingdraw.limit.LimitSupExport;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.goodsrc.ui.library.BaseActivity;
import java.io.InputStream;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import com.kingagroot.kingdraw.ui.PhotoActivity;
import com.kingagroot.kingdraw.core.StructConvertListener;
import com.kingagroot.kingdraw.core.tool.Struct2NameTool;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.kingagroot.kingdraw.model.BaiKeModel;
import com.kingagroot.kingdraw.core.tool.CleanUpTool;
import com.kingagroot.kingdraw.core.view3d.DataBuilder;
import android.os.Handler;
import android.os.Looper;
import com.kingagroot.kingdraw.ui.OpenGlMainActivity;
import com.kingagroot.kingdraw.core.view3d.DataElements;
import com.kingagroot.kingdraw.NewMainActivity;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.limit.LimitStereoscopicConfiguration$OnStereoscopicListener;
import com.kingagroot.kingdraw.limit.LimitStereoscopicConfiguration;
import com.kingagroot.kingdraw.core.tool.ChirlityTool;
import com.kingagroot.component.ui.vertical.ToolDataManage;
import com.kingagroot.kingdraw.widget.paletteMenu.KdSettingItemView;
import com.kingagroot.kingdraw.widget.paletteMenu.KdHelpItemView;
import com.kingagroot.component.ui.menu.menuitem.GridItemView;
import com.kingagroot.kingdraw.widget.paletteMenu.KdTo3dItemView;
import com.kingagroot.component.ui.menu.menuitem.ChiralItemView$OnChiralClickListener;
import com.kingagroot.component.ui.menu.menuitem.ChiralItemView;
import com.kingagroot.component.ui.menu.menuitem.SyntheticPredictionItemView$OnPredictionClickListener;
import com.kingagroot.component.ui.menu.menuitem.SyntheticPredictionItemView;
import com.kingagroot.component.ui.menu.menuitem.StructToNameItemView;
import com.kingagroot.component.ui.menu.menuitem.NameToStructItemView;
import com.kingagroot.kingdraw.core.tool.ClipboardData;
import com.kingagroot.component.ui.menu.menuitem.ChemInfoItemView;
import com.kingagroot.kingdraw.widget.paletteMenu.KdAiItemView;
import com.kingagroot.component.ui.menu.menuitem.SelectItemView;
import com.kingagroot.component.ui.menu.menuitem.AlignItemView;
import com.kingagroot.component.ui.menu.menuitem.ColorItemView;
import com.kingagroot.component.ui.menu.menuitem.ClearItemView;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import com.kingagroot.kingdraw.dialog.FileExportDialog$OnItemClickListener;
import org.xutils.http.body.RequestBody;
import java.util.List;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import java.util.ArrayList;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import com.goodsrc.library.utils.StringUtils;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.base.MApplication;
import java.util.Objects;
import java.lang.reflect.Type;
import com.goodsrc.library.utils.GsonUtil;
import java.io.IOException;
import com.kingagroot.kingdraw.core.FileReader$Builder;
import com.kingagroot.kingdraw.core.FileReader;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import org.xutils.http.RequestParams;
import com.goodsrc.library.http.HttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.http.HttpManager$Builder;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.net.Uri;
import com.goodsrc.library.utils.UriUtil;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.model.BkFileModel;
import android.content.Intent;
import com.kingagroot.kingdraw.ocr.KingDrawOCRApi;
import com.kingagroot.kingdraw.utils.MemoryUtil;
import android.app.Dialog;
import android.app.Activity;
import com.kingagroot.kingdraw.dialog.DialogManager;
import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.utils.FileExport$OnFileExportListner;
import com.kingagroot.kingdraw.utils.FileExport;
import com.goodsrc.library.utils.ToastUtil;
import java.io.File;
import com.kingagroot.component.ui.model.GFormatValue;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.component.ui.menu.PaletteOtherView;
import com.kingagroot.component.ui.vertical.VerticalRightToolView;
import com.kingagroot.component.ui.menu.ElementMenuView;
import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.kingdraw.dialog.FileExportDialog;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import android.content.Context;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.widget.paletteMenu.KdShareItemView;
import com.kingagroot.kingdraw.utils.ShareFile;
import com.kingagroot.kingdraw.widget.loadingview.AiAnimDialog;
import com.kingagroot.kingdraw.widget.paletteMenu.KdShareItemView$OnShareClickListner;
import com.kingagroot.kingdraw.widget.guide.GuideManager;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.kingdraw.widget.paletteMenu.KdFormatItemView;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.component.ui.menu.menuitem.CopyItemView;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.component.ui.menu.PaletteOtherView$OnMenuClickListener;
import com.kingagroot.component.ui.vertical.PaletteRightInterface;
import com.kingagroot.component.ui.PaletteTopInterface;
import com.kingagroot.component.ui.vertical.PaletteBottomInterface;
import com.kingagroot.component.ui.vertical.NewPaletteActivity;

public class NewPaletteRotateActivity extends NewPaletteActivity implements PaletteBottomInterface, PaletteTopInterface, PaletteRightInterface, PaletteOtherView$OnMenuClickListener
{
    static final boolean $assertionsDisabled = false;
    public static final int DATA_TYPE_BAIKE = 3;
    public static final int DATA_TYPE_MODEL = 1;
    public static final String INTENT_DATA = "intent_data";
    public static final String INTENT_DATA_TYPE = "intent_data_type";
    private static final ProtocolTypeEnum defaultType;
    private CopyItemView copyItem;
    private final DrawFileDataI drawFileDataI;
    private FolderFileModel fileModel;
    private KdFormatItemView formatMenuItem;
    GestureDbi gestureDbi;
    protected GuideManager guideManager;
    private boolean isNewFile;
    private final KdShareItemView$OnShareClickListner onShareClickListener;
    AiAnimDialog progressDialog;
    private ProtocolTypeEnum protocolTypeEnum;
    private ShareFile shareFile;
    private KdShareItemView shareMenuItem;
    
    static {
        defaultType = ProtocolTypeEnum.KDX;
    }
    
    public NewPaletteRotateActivity() {
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.guideManager = new GuideManager((Context)this);
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
        this.isNewFile = false;
        this.protocolTypeEnum = ProtocolTypeEnum.KDX;
        this.onShareClickListener = (KdShareItemView$OnShareClickListner)new _$$Lambda$NewPaletteRotateActivity$17wuoVOYd7p0SZhNpyUOlcHNcy4(this);
    }
    
    private String checkName(final String s) {
        final DrawFileDataI drawFileDataI = this.drawFileDataI;
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(this.protocolTypeEnum.extension);
        if (drawFileDataI.checkFileNameAvailable(sb.toString()).isSuccess) {
            return s;
        }
        final DrawFileDataI drawFileDataI2 = this.drawFileDataI;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s);
        sb2.append(this.protocolTypeEnum.extension);
        return drawFileDataI2.getCopyFileNameNoExtension(sb2.toString());
    }
    
    private void createNewFile() {
        this.isNewFile = true;
        final GFormatValue normalFormat = new GFormatValueDBImpl().readNormalFormat();
        this.kingDraw.createPalette(normalFormat.formatValue(), this.getConfigModel());
        this.kingDraw.setFormatValue(normalFormat.formatValue());
        this.setFormatValue(normalFormat);
    }
    
    private void deletePath(final String s) {
        new File(s).delete();
    }
    
    private void exportAsFile(final FileExportDialog fileExportDialog, final String s, final FileType fileType) {
        final DrawFileDataI drawFileDataI = this.drawFileDataI;
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(fileType.extension);
        final OperationState checkFileNameAvailable = drawFileDataI.checkFileNameAvailable(sb.toString());
        if (!checkFileNameAvailable.isSuccess) {
            ToastUtil.showShort((CharSequence)checkFileNameAvailable.info);
            return;
        }
        if (this.isSaving()) {
            return;
        }
        this.startSave();
        new FileExport().exportAsFile(s, this.kingDraw, fileType, (FileExport$OnFileExportListner)new NewPaletteRotateActivity$4(this, fileType, fileExportDialog));
    }
    
    private String getClipData() {
        try {
            final ClipData primaryClip = ((ClipboardManager)this.getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip != null && primaryClip.getItemCount() > 0) {
                return primaryClip.getItemAt(0).getText().toString();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    private PaletteConfigModel getConfigModel() {
        final PaletteConfigModel paletteConfigModel = new PaletteConfigModel();
        paletteConfigModel.setCarbonShowType(ShareData.getCarbonState());
        paletteConfigModel.setNormalColor(ShareData.getColorState());
        return paletteConfigModel;
    }
    
    private void initFinish() {
        this.runOnUiThread((Runnable)new NewPaletteRotateActivity$2(this));
    }
    
    private void kingVersionWarringDialog(final NewPaletteRotateActivity.NewPaletteRotateActivity$WarringCallback newPaletteRotateActivity$WarringCallback) {
        this.runOnUiThread((Runnable)new _$$Lambda$NewPaletteRotateActivity$ixLqczqiFs_dmgpx4_IrHHRrtr0(this, newPaletteRotateActivity$WarringCallback));
    }
    
    private void loadDataModel(final String s) {
        DialogManager.getInstance().getDialogQueue((Activity)this).addDailog((Dialog)(this.progressDialog = new AiAnimDialog((Context)this)));
        final NewPaletteRotateActivity$10 newPaletteRotateActivity$10 = new NewPaletteRotateActivity$10(this, s);
        if (MemoryUtil.getMemoryInfo((Context)this) > 4000.0f) {
            if (KingDrawOCRApi.CheckAndLoadMode((Context)this)) {
                ((AsyncTask)newPaletteRotateActivity$10).execute((Object[])new String[] { s });
            }
            else {
                this.onDismissLoading();
            }
        }
        else {
            ToastUtil.showShort(2131820591);
            this.onDismissLoading();
        }
    }
    
    private void onDismissLoading() {
        final AiAnimDialog progressDialog = this.progressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
    
    private void openFileByEncyclopedia(final Intent intent) {
        final String stringExtra = intent.getStringExtra("intent_data");
        final BkFileModel bkFileModel = (BkFileModel)intent.getSerializableExtra("BkFileModel");
        String fileName;
        if (TextUtils.isEmpty((CharSequence)bkFileModel.getName())) {
            fileName = DrawFileUtil.getAutoName();
        }
        else {
            fileName = bkFileModel.getName();
        }
        this.setFileName(fileName);
        new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$bEUwNuwT1Ck7FztnF4W8dbMcqU8(this, stringExtra)).start();
    }
    
    private void openFileByModel() {
        final FolderFileModel fileModel = (FolderFileModel)this.getIntent().getSerializableExtra("intent_data");
        this.fileModel = fileModel;
        this.setFileName(fileModel.getFileNameNoExtension());
        new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$GwZlYpCR_4DzRslsnb9JvFXqUpM(this)).start();
    }
    
    private void openFileByPath(final Intent intent) {
        final Uri data = intent.getData();
        boolean b = false;
        Label_0102: {
            if (data != null) {
                final String uri2File = UriUtil.Uri2File(data);
                if (!TextUtils.isEmpty((CharSequence)uri2File)) {
                    b = true;
                    final String name = new File(uri2File).getName();
                    if (!DrawFileUtil.checkFileReadEnable(name)) {
                        ToastUtil.showLong((CharSequence)this.getString(2131820862));
                        this.createNewFile();
                        this.initFinish();
                        return;
                    }
                    this.setFileName(DrawFileUtil.getFileNameNoExtension(name));
                    new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$ovDLzBgizzkDUJ5UlstbNclzFYo(this, data, name)).start();
                    break Label_0102;
                }
                else {
                    this.createNewFile();
                    this.initFinish();
                }
            }
            b = false;
        }
        if (!b) {
            this.createNewFile();
            this.initFinish();
        }
    }
    
    private void pasteCas(final String s, final float n, final FormatValue formatValue, final float n2, final float n3) {
        this.showLoading(this.getString(2131820950));
        final HttpManager build = new HttpManager$Builder().build();
        final RequestParams params = build.params(APIConfig.CAS_SEARCH);
        params.addParameter("searchType", 9);
        params.addParameter("startId", 0);
        final Integer value = 1;
        params.addParameter("count", value);
        params.addParameter("keywords", s);
        params.addParameter("minSamePer", 0.8);
        params.addParameter("maxSamePer", value);
        build.request(params, (RequestCallBack)new NewPaletteRotateActivity$8(this, n, formatValue));
    }
    
    private void readAiKing(final String s) {
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        final FileReader$Builder builder = FileReader.getBuilder();
        builder.withView(this.kingDraw, readerFormatForType.formatValue());
        builder.readByString(s);
        this.protocolTypeEnum = builder.getProtocolType();
        this.initFinish();
    }
    
    private void readByPath(final String s) throws IOException {
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        final FileReader$Builder builder = FileReader.getBuilder();
        builder.withView(this.kingDraw, readerFormatForType.formatValue());
        builder.readByPath(s);
        final ProtocolTypeEnum protocolType = builder.getProtocolType();
        this.protocolTypeEnum = protocolType;
        if (protocolType == null) {
            this.protocolTypeEnum = ProtocolTypeEnum.KDX;
        }
        this.initFinish();
    }
    
    private void readFail() {
        this.runOnUiThread((Runnable)new _$$Lambda$NewPaletteRotateActivity$EB8_LNEoRoKEdGTxiskHYgzXnPo(this));
    }
    
    private void readFolderModel(final FolderFileModel folderFileModel) throws IOException {
        final GFormatValue readerFormatForType = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        final FileReader$Builder builder = FileReader.getBuilder();
        if (!TextUtils.isEmpty((CharSequence)folderFileModel.getFromatStr())) {
            builder.withFileFormatValue(((GFormatValue)Objects.requireNonNull((Object)GsonUtil.parseJson(folderFileModel.getFromatStr(), (Type)GFormatValue.class))).formatValue());
        }
        builder.withView(this.kingDraw, readerFormatForType.formatValue());
        builder.readByPath(folderFileModel.getFilePath());
        this.protocolTypeEnum = builder.getProtocolType();
        this.initFinish();
    }
    
    private void sendDataChangeMsg() {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.getInstance());
        final Intent intent = new Intent("local_data_changed");
        intent.putExtra("datachange_key", true);
        instance.sendBroadcast(intent);
    }
    
    private void showCopyMenu() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this, 2131886327);
        String[] array = { this.getString(2131820739), this.getString(2131820740) };
        String s;
        if (this.kingDraw.expandSupAvailability()) {
            array = new String[] { this.getString(2131820739), this.getString(2131820740), this.getString(2131820742) };
            s = this.getString(2131820978);
        }
        else if (this.kingDraw.contractSupAvailability()) {
            array = new String[] { this.getString(2131820739), this.getString(2131820740), this.getString(2131820741) };
            s = this.getString(2131820977);
        }
        else {
            s = null;
        }
        alertDialog$Builder.setItems((CharSequence[])array, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$pmZf35jC4jbBepeUKyGQCI0OAhs(this, s));
        alertDialog$Builder.show();
    }
    
    private void showFormatHint(final GFormatValue gFormatValue) {
        ToastUtil.showFormatInfo((CharSequence)StringUtils.format(this.getString(2131820779), new Object[] { GDocumentTypeEnum.valueOfCode(gFormatValue.getDocumentType()).getFullName() }));
    }
    
    private void showMultiBtnDialog(final GFormatValue gFormatValue, final boolean b) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524);
        alertDialog$Builder.setMessage(2131821023);
        alertDialog$Builder.setPositiveButton(2131821022, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$j8yhG4YTv6mwtUo5Y_fYik6Ladk(this, gFormatValue));
        alertDialog$Builder.setNeutralButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$cIRsbYmzQmCqfrdmrqY_RFqCmh4(this, b));
        alertDialog$Builder.setNegativeButton(2131820778, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$CNeRQ4BV8mgY0G_JPJrbuFaMhdg(this, gFormatValue));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }
    
    private void showPasteMenu(final float n, final float n2) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this, 2131886327);
        alertDialog$Builder.setTitle(2131821160);
        alertDialog$Builder.setItems((CharSequence[])new String[] { this.getString(2131821157), this.getString(2131821158), this.getString(2131821159), this.getString(2131821156) }, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$62LEZuPIWrn_44oCzlgqoesBifY(this, n, n2));
        alertDialog$Builder.show();
    }
    
    private void showShareMenu() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this, 2131886327);
        alertDialog$Builder.setItems((CharSequence[])new String[] { this.getString(2131820859), this.getString(2131821388) }, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$NNI5dVOciwPPTNsOWHjLAqbjoMM(this));
        alertDialog$Builder.show();
    }
    
    private void taskFinishOrCancel(final String s) {
        this.onDismissLoading();
        KingDrawOCRApi.deleteModel();
        this.deletePath(s);
    }
    
    private void upLoadDialog(final String s) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131820936).setMessage(2131820590).setNegativeButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$VVsUASL28jfWnXYv7Bj4_BCFOy8(this, s)).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$NewPaletteRotateActivity$qljrVCEmLB57H7hdoTQYhZlVrKg(this, s));
        final AlertDialog show = alertDialog$Builder.show();
        show.setCanceledOnTouchOutside(false);
        show.getButton(-1).setTextColor(ContextCompat.getColor((Context)this, 2131099698));
    }
    
    private void upLoadPic(final String s) {
        final HttpManager build = new HttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.getAiUpLoadImage());
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("pic", new File(s)));
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        build.request(params, (RequestCallBack)new NewPaletteRotateActivity$11(this, s));
    }
    
    public void applyFormatValue(final GFormatValue formatValue) {
        this.setFormatValue(formatValue);
        this.showLoading(this.getString(2131821523), 1000L);
        this.kingDraw.applyFormatValue();
        this.showFormatHint(formatValue);
        this.dismissLoading();
    }
    
    public void export(final Context context, final String filename) {
        final FileExportDialog fileExportDialog = new FileExportDialog(context, filename, FileType.valueOfCode(this.protocolTypeEnum.code));
        fileExportDialog.show();
        fileExportDialog.setFilename(filename);
        fileExportDialog.setOnItemClickListener((FileExportDialog$OnItemClickListener)new NewPaletteRotateActivity$3(this, context));
    }
    
    @Override
    protected List<ToolBaseItemView> initTopMenu() {
        final ArrayList list = new ArrayList();
        final ClearItemView clearItemView = new ClearItemView((Context)this);
        ((ToolBaseItemView)clearItemView).setKingDrawView(this.kingDraw);
        final ColorItemView colorItemView = new ColorItemView((Context)this);
        ((ToolBaseItemView)colorItemView).setKingDrawView(this.kingDraw);
        final AlignItemView alignItemView = new AlignItemView((Context)this);
        ((ToolBaseItemView)alignItemView).setKingDrawView(this.kingDraw);
        final SelectItemView selectItemView = new SelectItemView((Context)this);
        final KdAiItemView kdAiItemView = new KdAiItemView((Context)this);
        final ChemInfoItemView chemInfoItemView = new ChemInfoItemView((Context)this);
        ((ToolBaseItemView)chemInfoItemView).setKingDrawView(this.kingDraw);
        (this.copyItem = new CopyItemView((Context)this)).setKingDrawView(this.kingDraw);
        if (ClipboardData.getInstance().hasClipData()) {
            this.copyItem.enablePaste();
        }
        (this.formatMenuItem = new KdFormatItemView((Context)this)).setKingDrawView(this.kingDraw);
        this.formatMenuItem.setFormatMenuListner((KdFormatItemView.KdFormatItemView$OnFormatMenuListner)new _$$Lambda$NewPaletteRotateActivity$3HTQrpNcB4Poz0ZEb_CQ1UNBE6o(this));
        final NameToStructItemView nameToStructItemView = new NameToStructItemView((Context)this);
        ((ToolBaseItemView)nameToStructItemView).setKingDrawView(this.kingDraw);
        final StructToNameItemView structToNameItemView = new StructToNameItemView((Context)this);
        ((ToolBaseItemView)structToNameItemView).setKingDrawView(this.kingDraw);
        final SyntheticPredictionItemView syntheticPredictionItemView = new SyntheticPredictionItemView((Context)this);
        ((ToolBaseItemView)syntheticPredictionItemView).setKingDrawView(this.kingDraw);
        syntheticPredictionItemView.setOnPredictionClickListener((SyntheticPredictionItemView$OnPredictionClickListener)new NewPaletteRotateActivity$1(this));
        final ChiralItemView chiralItemView = new ChiralItemView((Context)this);
        chiralItemView.setKingDrawView(this.kingDraw);
        chiralItemView.setOnChiralClickListener((ChiralItemView$OnChiralClickListener)new _$$Lambda$NewPaletteRotateActivity$N4dplnKW_IXIbZNN0oJiR5AUWWU(this, chiralItemView));
        final KdTo3dItemView kdTo3dItemView = new KdTo3dItemView((Context)this);
        ((ToolBaseItemView)kdTo3dItemView).setKingDrawView(this.kingDraw);
        final GridItemView gridItemView = new GridItemView((Context)this);
        ((ToolBaseItemView)gridItemView).setKingDrawView(this.kingDraw);
        (this.shareMenuItem = new KdShareItemView((Context)this)).setOnShareClickListner(this.onShareClickListener);
        final KdHelpItemView kdHelpItemView = new KdHelpItemView((Context)this);
        final KdSettingItemView kdSettingItemView = new KdSettingItemView((Context)this);
        ((ToolBaseItemView)kdSettingItemView).setKingDrawView(this.kingDraw);
        ((List)list).add((Object)clearItemView);
        ((List)list).add((Object)colorItemView);
        ((List)list).add((Object)alignItemView);
        ((List)list).add((Object)selectItemView);
        if (ShareData.getPicAiStatus()) {
            ((List)list).add((Object)kdAiItemView);
        }
        else {
            ((List)list).remove((Object)kdAiItemView);
        }
        ((List)list).add((Object)chemInfoItemView);
        ((List)list).add((Object)this.copyItem);
        ((List)list).add((Object)this.formatMenuItem);
        ((List)list).add((Object)nameToStructItemView);
        ((List)list).add((Object)structToNameItemView);
        ((List)list).add((Object)chiralItemView);
        ((List)list).add((Object)kdTo3dItemView);
        ((List)list).add((Object)syntheticPredictionItemView);
        ((List)list).add((Object)gridItemView);
        ((List)list).add((Object)this.shareMenuItem);
        ((List)list).add((Object)kdHelpItemView);
        ((List)list).add((Object)kdSettingItemView);
        return (List<ToolBaseItemView>)list;
    }
    
    @Override
    protected void initView() {
        super.initView();
        this.setIsPad(false);
        this.isPortrait(ShareData.getScreenDirection(), true);
        if (ShareData.getZoomerStatus()) {
            this.kingDraw.contactMagnifierView(this.magnifier);
        }
        this.verticalRightTool.setPaletteType(ToolDataManage.TYPE_BASE);
        this.verticalTopTool.setTopViewType(ToolDataManage.TYPE_BASE);
        this.verticalBottomTool.setPaletteType(ToolDataManage.TYPE_BASE);
        this.verticalRightTool.setPicAiShow(ShareData.getPicAiStatus());
        this.verticalBottomTool.setBottomViewClickListener((PaletteBottomInterface)this);
        this.verticalTopTool.setOnTopToolClick((PaletteTopInterface)this);
        this.verticalRightTool.setOnRightMenuClickListener((PaletteRightInterface)this);
        this.landTopTool.setMainPaletteView((PaletteTopInterface)this);
        this.landRightTool.setOnMenuClickListener((PaletteOtherView$OnMenuClickListener)this);
        this.landRightTool.setDotShow(ShareData.getScreenDotShow());
    }
    
    @Override
    public void longPressBlankSpace(final float n, final float n2) {
        this.showPasteMenu(n, n2);
    }
    
    @Override
    public void longPressSelectSpace() {
        this.showCopyMenu();
    }
    
    public void on3dViewClick() {
        final String selectedMoleculeElementsJson = this.kingDraw.getSelectedMoleculeElementsJson();
        final String allElementsJson = this.kingDraw.getAllElementsJson();
        String s = selectedMoleculeElementsJson;
        if (TextUtils.isEmpty((CharSequence)selectedMoleculeElementsJson)) {
            s = allElementsJson;
        }
        DataElements.cleanElements();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.showLoading(this.getString(2131820953));
            new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$4XzWOlfJ5lvZj2_eCbyja0DnqBU(this, s)).start();
        }
        else {
            ToastUtil.showShort(2131821134);
        }
    }
    
    @Override
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == KdSettingItemView.REQUEST_CODE_SET_FORMAT) {
            if (n2 == 10011 && intent != null) {
                final GFormatValue formatValue = (GFormatValue)intent.getSerializableExtra("result_formatvalue_key");
                if (intent.getBooleanExtra("intent_set_type", true)) {
                    this.kingDraw.setFormatValue(((GFormatValue)Objects.requireNonNull((Object)formatValue)).formatValue());
                    this.applyFormatValue(formatValue);
                }
                else {
                    this.setFormatValue(formatValue);
                    this.showFormatHint((GFormatValue)Objects.requireNonNull((Object)formatValue));
                    this.kingDraw.setFormatValue(formatValue.formatValue());
                }
            }
        }
        else if (n == KdAiItemView.REQUEST_CODE_AI && n2 == -1 && intent != null) {
            this.loadDataModel(intent.getStringExtra(PhotoActivity.RESULT_DATA));
        }
    }
    
    public void onChemInfoClick() {
        if (this.kingDraw != null) {
            final ChemicalAnalysisModel chemicalAnalysisModel = (ChemicalAnalysisModel)GsonUtil.fromJson(new ChemisAnalysisTool(this.kingDraw.getPaletteId()).chemisAnalysisWithSelectElements(), (Class)ChemicalAnalysisModel.class);
            if (chemicalAnalysisModel != null) {
                new AnalysisPop((Context)this, this.kingDraw, chemicalAnalysisModel).show((View)this.kingDraw);
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131821142));
            }
        }
    }
    
    public void onChiralClick(final boolean b) {
        String s;
        if (b) {
            s = this.getString(2131820973);
        }
        else {
            s = this.getString(2131820974);
        }
        new LimitStereoscopicConfiguration((LimitStereoscopicConfiguration$OnStereoscopicListener)new _$$Lambda$NewPaletteRotateActivity$S_zS1nLVgyrHMCbbIUgq_inKGRY(this, b)).checkStereoscopic((Context)this, s);
    }
    
    public void onCleanUpClick() {
        this.showLoading(this.getString(2131821523), 1000L);
        new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$bBrO3zjh5WQW9qvqo2ugeKkK48A(this)).start();
    }
    
    public void onCleanUpOnClick() {
        this.showLoading(this.getString(2131821523), 1000L);
        new Thread((Runnable)new _$$Lambda$NewPaletteRotateActivity$HGyF4xFouCU3wZtTmB_4QVdIhQo(this)).start();
    }
    
    public void onClose() {
        String s;
        if (this.isVertical) {
            s = this.verticalTopTool.getFileName();
        }
        else {
            s = this.landTopTool.getFileName();
        }
        final ProtocolTypeEnum protocolTypeEnum = this.protocolTypeEnum;
        if (protocolTypeEnum == null) {
            this.protocolTypeEnum = NewPaletteRotateActivity.defaultType;
        }
        else if (protocolTypeEnum == ProtocolTypeEnum.KING) {
            this.protocolTypeEnum = ProtocolTypeEnum.KDX;
        }
        if (this.protocolTypeEnum == ProtocolTypeEnum.MOL_V2000 && ProtocolUtils.exceedMolV2000(this.kingDraw.getAllElementsJson())) {
            ToastUtil.showShort((CharSequence)this.getString(2131820843));
            return;
        }
        if (this.fileModel == null) {
            if (!this.kingDraw.isEmpty()) {
                String autoName = s;
                if (TextUtils.isEmpty((CharSequence)s)) {
                    autoName = DrawFileUtil.getAutoName();
                }
                final DrawViewSaveFileThread drawViewSaveFileThread = new DrawViewSaveFileThread(this.checkName(autoName), this.protocolTypeEnum, this.kingDraw);
                drawViewSaveFileThread.setDrawViewSaveFileListener((DrawViewSaveFileThread$onDrawViewSaveFileListener)new _$$Lambda$NewPaletteRotateActivity$pFNbazUOmxvc9SlTRn_60c_bLec(this));
                ThreadPoolManager.getInstance().getService().execute((Runnable)drawViewSaveFileThread);
                this.showLoading(this.getString(2131821523), 1000L);
            }
            else {
                this.finish();
            }
        }
        else if (this.isNeedUpdateFile()) {
            final DrawViewUpdataFileThread drawViewUpdataFileThread = new DrawViewUpdataFileThread(this.fileModel, this.protocolTypeEnum, this.kingDraw);
            drawViewUpdataFileThread.setDrawViewSaveFileListener((DrawViewUpdataFileThread$onDrawViewUpdataFileListener)new _$$Lambda$NewPaletteRotateActivity$0AAlzP08LEKEvOO2vrMOfk7lrNc(this));
            ThreadPoolManager.getInstance().getService().execute((Runnable)drawViewUpdataFileThread);
            this.showLoading(this.getString(2131821523), 1000L);
        }
        else {
            if (this.isDataChange()) {
                this.sendDataChangeMsg();
            }
            this.finish();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.kingDraw.onDestroy();
    }
    
    @Override
    public void onElementChanged(final boolean b) {
        super.onElementChanged(b);
        this.shareMenuItem.setClickEnable(b ^ true);
    }
    
    public void onEncyOnClick() {
        new LimitPedia((LimitPedia$OnPediaListener)new _$$Lambda$NewPaletteRotateActivity$MoC0kdSFZN3XaqmcTTgaXxoZIoU(this)).checkPedia((Context)this, this.getString(2131820975));
    }
    
    public void onFirstToolViewClick() {
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onFormatChangeClick(final GFormatValue gFormatValue) {
        this.showMultiBtnDialog(gFormatValue, false);
    }
    
    @Override
    public void onFormatChanged(final FormatValue formatValue) {
        super.onFormatChanged(formatValue);
        this.setFormatValue(GFormatValue.formatValue(formatValue));
    }
    
    public void onFormatSetting() {
        final Intent intent = new Intent((Context)this, (Class)OtherSettingActivity.class);
        intent.putExtra("intent_key_format", (Serializable)GFormatValue.formatValue(this.kingDraw.getFormatValue()));
        intent.putExtra("intent_key_color", this.kingDraw.getColorAtom());
        this.startActivityForResult(intent, KdSettingItemView.REQUEST_CODE_SET_FORMAT);
    }
    
    public void onFullScreen(final boolean b) {
        if (b) {
            this.paletteMenuElement.dismiss();
            this.landTopTool.dismiss();
            this.landBottomTool.dismiss();
            this.landRightTool.dismiss();
            this.clearLandTools();
            this.kingDraw.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
            this.landRightTool.getPaletteMove().setSelect(true);
        }
        else {
            this.paletteMenuElement.show();
            this.landTopTool.show();
            this.landBottomTool.show();
            this.landRightTool.show();
        }
    }
    
    public void onGestureOnClick() {
        if (this.sildeFlingGestureAction == null) {
            this.sildeFlingGestureAction = new SildeFlingGestureAction((Context)this, (float)this.kingDraw.getWidth());
            this.kingDraw.addGestureAction((BaseGestureAction)this.sildeFlingGestureAction);
            this.sildeFlingGestureAction.setOnSildeFlingGestureActionListener((SildeFlingGestureAction$OnSildeFlingGestureActionListener)new NewPaletteRotateActivity$9(this));
        }
        this.kingDraw.setTool(ToolNameEnum.GGESTURE_TOOL, (String)null);
        GestureCallBack.setResultCallBack((GestureRecognizeResultCallBack)new _$$Lambda$NewPaletteRotateActivity$XY1uhEOlR6C7d1HX2RyzjwPNugM(this));
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
        this.landBottomTool.clearCheck();
        this.landTopTool.clearCheck();
    }
    
    public void onGestureOnLongClick() {
        final List dataByKey = this.gestureDbi.getDataByKey(true);
        if (dataByKey != null && dataByKey.size() > 0) {
            this.guideManager.showGestureChartGuide((View)this.kingDraw);
        }
    }
    
    public void onHelp() {
        final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
        intent.putExtra("title_key", this.getString(2131820947));
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            intent.putExtra("url_key", APIConfig.HELP_ZH);
        }
        else {
            intent.putExtra("url_key", APIConfig.HELP_EN);
        }
        this.startActivity(intent);
    }
    
    public void onInputFileName(final String s) {
        final FolderFileModel fileModel = this.fileModel;
        if (fileModel == null) {
            final DrawFileDataI drawFileDataI = this.drawFileDataI;
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(this.protocolTypeEnum.extension);
            final OperationState checkFileNameAvailable = drawFileDataI.checkFileNameAvailable(sb.toString());
            if (checkFileNameAvailable.isSuccess) {
                this.setFileName(s);
            }
            else {
                ToastUtil.showShort((CharSequence)checkFileNameAvailable.info);
            }
            return;
        }
        final OperationState rename = this.drawFileDataI.rename(fileModel.getId(), s, this.fileModel.getFileTypeEnum());
        if (rename.isSuccess && rename.getData() != null) {
            this.fileModel = (FolderFileModel)rename.getData();
            this.notifyDataChange();
            this.setFileName(s);
        }
        ToastUtil.showShort((CharSequence)rename.info);
    }
    
    public void onInputSupName(final String s) {
    }
    
    public void onJumpToPredictionClick() {
        if (this.checkNetwork()) {
            final String selectedElementsJson = this.kingDraw.getSelectedElementsJson();
            String smiles = null;
            Label_0052: {
                if (!TextUtils.isEmpty((CharSequence)selectedElementsJson)) {
                    final BaiKeModel baiKeModel = (BaiKeModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(selectedElementsJson), (Class)BaiKeModel.class);
                    if (baiKeModel != null) {
                        smiles = baiKeModel.getSmiles();
                        break Label_0052;
                    }
                }
                smiles = "";
            }
            if (!TextUtils.isEmpty((CharSequence)smiles) && !smiles.equals((Object)"error")) {
                this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://askcos.mit.edu/")));
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131820622));
            }
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            if (keyEvent.getAction() == 0) {
                if (this.inputMenu.getVisibility() == 0) {
                    this.inputMenu.setVisibility(8);
                }
                else {
                    this.onClose();
                }
            }
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    @Override
    public void onKingDrawViewAvailable() {
        final Intent intent = this.getIntent();
        final String action = intent.getAction();
        final int intExtra = intent.getIntExtra("intent_data_type", -1);
        this.showLoading(this.getString(2131821522));
        if (intExtra == 1) {
            this.openFileByModel();
        }
        else if (intExtra == 3) {
            this.openFileByEncyclopedia(intent);
        }
        else if ("android.intent.action.VIEW".equals((Object)action)) {
            this.openFileByPath(intent);
        }
        else {
            this.createNewFile();
            this.initFinish();
        }
    }
    
    public void onMoveOnClick() {
        this.kingDraw.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
        this.landTopTool.clearCheck();
        this.landBottomTool.clearCheck();
    }
    
    public void onNameToStructClick() {
        final EditPop create = new EditPop$Build((Context)this).setContent("").setHint(this.getString(2131821141)).setMaxLength(200).setShowCount(false).setOnEditPopListener((EditPop$OnEditPopListener)new _$$Lambda$NewPaletteRotateActivity$F5GjjHI7aWZxM4yx_nJ2vHe3Y00(this)).create();
        if (this.isVertical) {
            create.show((View)this.verticalTopTool);
        }
        else {
            create.show();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        MANServiceConfig.pageDisAppear((Activity)this);
    }
    
    public void onRClick() {
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (ShareData.getZoomerStatus()) {
            this.kingDraw.contactMagnifierView(this.magnifier);
        }
        else {
            this.kingDraw.closeMagnifierView();
        }
        this.kingDraw.setColorAtom(true, ShareData.getColorState());
        this.kingDraw.setCShowType(OtherSettingActivity.getShowAll(), ShareData.getCarbonState());
        MANServiceConfig.pageAppear((Activity)this);
    }
    
    public void onSaveAsFile() {
        if (this.kingDraw.isEmpty()) {
            ToastUtil.showShort((CharSequence)this.getString(2131821143));
            return;
        }
        String s;
        if (this.isVertical) {
            s = this.verticalTopTool.getFileName();
        }
        else {
            s = this.landTopTool.getFileName();
        }
        final FolderFileModel fileModel = this.fileModel;
        if (fileModel == null) {
            if (TextUtils.isEmpty((CharSequence)this.verticalTopTool.getFileName())) {
                s = DrawFileUtil.getAutoName();
            }
        }
        else {
            s = this.drawFileDataI.getCopyFileNameNoExtension(fileModel.getFullFileName());
        }
        this.export((Context)this, s);
    }
    
    public void onSaveFile() {
        if (this.isSaving()) {
            return;
        }
        final ProtocolTypeEnum protocolTypeEnum = this.protocolTypeEnum;
        if (protocolTypeEnum == null) {
            this.protocolTypeEnum = NewPaletteRotateActivity.defaultType;
        }
        else if (protocolTypeEnum == ProtocolTypeEnum.KING) {
            this.protocolTypeEnum = ProtocolTypeEnum.KDX;
        }
        if (this.protocolTypeEnum == ProtocolTypeEnum.MOL_V2000 && ProtocolUtils.exceedMolV2000(this.kingDraw.getAllElementsJson())) {
            ToastUtil.showShort((CharSequence)this.getString(2131820843));
            return;
        }
        if (this.kingDraw.isEmpty()) {
            ToastUtil.showShort((CharSequence)this.getString(2131821143));
            return;
        }
        final FolderFileModel fileModel = this.fileModel;
        if (fileModel == null) {
            String s;
            if (this.isVertical) {
                s = this.verticalTopTool.getFileName();
            }
            else {
                s = this.landTopTool.getFileName();
            }
            final DrawViewSaveFileThread drawViewSaveFileThread = new DrawViewSaveFileThread(s, this.protocolTypeEnum, this.kingDraw);
            final OperationState checkFileNameAvailable = drawViewSaveFileThread.checkFileNameAvailable();
            if (checkFileNameAvailable.isSuccess) {
                drawViewSaveFileThread.setNeedDestroyPalete(false);
                drawViewSaveFileThread.setDrawViewSaveFileListener((DrawViewSaveFileThread$onDrawViewSaveFileListener)new _$$Lambda$NewPaletteRotateActivity$R11MaEbOeq2fLaZaCJQeoRXaGlA(this));
                ThreadPoolManager.getInstance().getService().execute((Runnable)drawViewSaveFileThread);
                this.showLoading(this.getString(2131821523), 1000L);
                this.startSave();
            }
            else {
                ToastUtil.showShort((CharSequence)checkFileNameAvailable.getInfo());
            }
        }
        else {
            final DrawViewUpdataFileThread drawViewUpdataFileThread = new DrawViewUpdataFileThread(fileModel, this.protocolTypeEnum, this.kingDraw);
            drawViewUpdataFileThread.setDrawViewSaveFileListener((DrawViewUpdataFileThread$onDrawViewUpdataFileListener)new _$$Lambda$NewPaletteRotateActivity$fKxeAId90sxZSUti0MELCknJLyE(this));
            ThreadPoolManager.getInstance().getService().execute((Runnable)drawViewUpdataFileThread);
            this.showLoading(this.getString(2131821523), 1000L);
            this.startSave();
        }
    }
    
    public void onScreenToHorizontal() {
        this.isPortrait(false, (NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener)new _$$Lambda$NewPaletteRotateActivity$tnR5f0hjod_UNHZr17PhUSdc2yg(this));
    }
    
    public void onScreenToVertical() {
        this.isPortrait(true, (NewPaletteActivity.NewPaletteActivity$RequestedOrientationListener)new _$$Lambda$NewPaletteRotateActivity$mtAa5hh3TQJNNBClqxiHyt2LEXg(this));
        if (ShareData.getScreenDotShow()) {
            ShareData.setScreenDotShow(false);
        }
    }
    
    public void onSearchDone() {
    }
    
    public void onSecondToolViewClick() {
        this.verticalRightTool.clearCheck();
        this.verticalRightTool.setOtherToolSelect();
        this.paletteMenuElement.clearCheck();
    }
    
    public void onSelectPic() {
        final XXPermissions permission = XXPermissions.with((Context)this).permission(new String[] { "android.permission.CAMERA" });
        if (Build$VERSION.SDK_INT >= 33) {
            permission.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            permission.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        permission.request((OnPermissionCallback)new _$$Lambda$NewPaletteRotateActivity$egC_TsVctQx7vVRtgND9oRnE9og(this));
    }
    
    public void onShare() {
        if (this.kingDraw.isEmpty()) {
            ToastUtil.showShort((CharSequence)this.getString(2131821143));
            return;
        }
        this.showShareMenu();
    }
    
    public void onStructToNameClick() {
        this.showLoading(this.getString(2131820950));
        new Struct2NameTool(this.kingDraw.getPaletteId()).structToName((StructConvertListener)new NewPaletteRotateActivity$6(this));
    }
    
    public void onSupButtOnClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onSupClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onSupR() {
        this.kingDraw.setTool(ToolNameEnum.GR_TOOL, (String)null);
        this.verticalBottomTool.clearCheck();
        this.paletteMenuElement.clearCheck();
        this.landTopTool.clearCheck();
        this.landBottomTool.clearCheck();
    }
    
    public void onSupSave() {
    }
    
    @Override
    protected void onSupTable(final boolean b) {
        super.onSupTable(b);
        this.startActivityForResult(new Intent((Context)this, (Class)SUPTableActivity.class), 1100);
    }
    
    public void onSupTextOnClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onTextSupClick() {
        this.dlPalette.openDrawer(5);
    }
    
    public void onToolDefaultSelectClick() {
        this.verticalRightTool.checkSelect();
    }
    
    public void onToolNameShow(final String s) {
        this.tvHint.show(s);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (!this.guideManager.isInit() && this.isNewFile) {
            this.guideManager.showPaletteGuide(this.kingDraw);
        }
    }
    
    protected void setFormatValue(final GFormatValue gFormatValue) {
        if (this.verticalBottomTool != null) {
            this.verticalBottomTool.setDefaultFormat(gFormatValue);
        }
        final KdFormatItemView formatMenuItem = this.formatMenuItem;
        if (formatMenuItem != null) {
            formatMenuItem.setFormatValue(gFormatValue);
        }
    }
}
