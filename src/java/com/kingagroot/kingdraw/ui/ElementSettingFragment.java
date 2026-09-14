package com.kingagroot.kingdraw.ui;

import android.view.WindowManager$LayoutParams;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.MotionEvent;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.kingdraw.widget.WheelPop$OnClickButtonSureListener;
import android.view.ViewGroup;
import com.kingagroot.kingdraw.widget.WheelPop;
import android.graphics.Color;
import com.kingagroot.component.ui.widget.richinput.GFontStyleEnum;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.StringUtils;
import android.text.TextUtils;
import android.view.View;
import android.view.View$OnTouchListener;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import java.util.Objects;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import android.widget.TextView;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.os.Handler;
import com.kingagroot.kingdraw.widget.guide.GuideManager;
import com.kingagroot.component.ui.db.GFormatValueDBI;
import com.kingagroot.component.ui.model.GFormatValue;
import android.text.TextWatcher;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.EditText;
import com.kingagroot.kingdraw.widget.RegionNumberEditText;
import android.view.View$OnClickListener;

public class ElementSettingFragment extends BaseFragment implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    private final Runnable delayRun;
    private RegionNumberEditText etAngle;
    private RegionNumberEditText etBoldWidth;
    private EditText etFixedLength;
    private RegionNumberEditText etHashSpacing;
    private RegionNumberEditText etLineWidth;
    private RegionNumberEditText etMarginWidth;
    private RegionNumberEditText etSpacing;
    private FloatingActionButton fabSave;
    private final TextWatcher fixedLengthWatch;
    GFormatValue gFormatValueCurrent;
    GFormatValue gFormatValuePrevious;
    GFormatValueDBI gformatvaluedbi;
    private GuideManager guideManager;
    private final Handler handler;
    private ImageButton imbtnBondsHelp;
    private ImageButton imbtnChainsHelp;
    private boolean isSaving;
    private LinearLayout llAtomFontSelect;
    private LinearLayout llTextFontSelect;
    Context mContext;
    private ElementSettingFragment.ElementSettingFragment$OnSaveSuccessFormatListener onSaveSuccessFormatListener;
    private String strAngle;
    private String strAtomFontName;
    private String strAtomFontSize;
    private String strAtomFontStyle;
    private String strBoldWidth;
    private String strFixedLength;
    private String strHashSpacing;
    String[] strItems;
    private String strLineWidth;
    private String strMarginWidth;
    private String strSpacing;
    private String strTextFontName;
    private String strTextFontSize;
    private String strTextFontStyle;
    private TextView tvAtomFontName;
    private TextView tvAtomFontSize;
    private TextView tvAtomFontStyle;
    private TextView tvTemplate;
    private TextView tvTextFontName;
    private TextView tvTextFontSize;
    private TextView tvTextFontStyle;
    
    public ElementSettingFragment() {
        this.handler = new Handler();
        this.strItems = new String[3];
        this.fixedLengthWatch = (TextWatcher)new ElementSettingFragment$1(this);
        this.delayRun = (Runnable)new ElementSettingFragment$2(this);
    }
    
    private void getDateFromDocument(final String s) {
        if (s.equals((Object)GDocumentTypeEnum.ACS96\u683c\u5f0f.getFullName())) {
            this.gFormatValueCurrent = this.gformatvaluedbi.readerFormatForType(GDocumentTypeEnum.ACS96\u683c\u5f0f);
        }
        else if (s.equals((Object)GDocumentTypeEnum.KingDraw\u683c\u5f0f.getFullName())) {
            this.gFormatValueCurrent = this.gformatvaluedbi.readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f);
        }
        else {
            this.gFormatValueCurrent = this.gformatvaluedbi.readerFormatForType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f);
        }
        this.setDefault(this.gFormatValueCurrent);
    }
    
    private void getEtString() {
        this.strFixedLength = this.etFixedLength.getText().toString();
        this.strSpacing = Objects.requireNonNull((Object)this.etSpacing.getText()).toString();
        this.strLineWidth = Objects.requireNonNull((Object)this.etLineWidth.getText()).toString();
        this.strBoldWidth = Objects.requireNonNull((Object)this.etBoldWidth.getText()).toString();
        this.strMarginWidth = Objects.requireNonNull((Object)this.etMarginWidth.getText()).toString();
        this.strHashSpacing = Objects.requireNonNull((Object)this.etHashSpacing.getText()).toString();
        this.strAngle = Objects.requireNonNull((Object)this.etAngle.getText()).toString();
        this.strAtomFontName = this.tvAtomFontName.getText().toString();
        this.strAtomFontStyle = this.tvAtomFontStyle.getText().toString();
        this.strAtomFontSize = this.tvAtomFontSize.getText().toString();
        this.strTextFontName = this.tvTextFontName.getText().toString();
        this.strTextFontStyle = this.tvTextFontStyle.getText().toString();
        this.strTextFontSize = this.tvTextFontSize.getText().toString();
    }
    
    private void initData() {
        this.strItems = GFormatValue.getAllDocumentType();
        this.gformatvaluedbi = (GFormatValueDBI)new GFormatValueDBImpl();
        final GFormatValue gFormatValuePrevious = (GFormatValue)this.getArguments().getSerializable("intent_key_format");
        this.gFormatValuePrevious = gFormatValuePrevious;
        if (gFormatValuePrevious == null) {
            this.gFormatValuePrevious = ((GFormatValueDBImpl)this.gformatvaluedbi).getKingDoc();
        }
        this.setDefault(this.gFormatValueCurrent = this.gFormatValuePrevious);
        this.initEvent();
    }
    
    private void initEvent() {
        this.etFixedLength.addTextChangedListener(this.fixedLengthWatch);
        this.etSpacing.setRegion(100.0f, 0.0f, 0);
        this.etSpacing.setTextType(0);
        this.etSpacing.setTextWatcher();
        this.etLineWidth.setRegion(0.5f, 0.01f, 6);
        this.etLineWidth.setTextType(1);
        this.etLineWidth.setTextWatcher();
        this.etBoldWidth.setRegion(0.5f, 0.01f, 6);
        this.etBoldWidth.setTextType(1);
        this.etBoldWidth.setTextWatcher();
        if (this.etFixedLength.getText() != null && !this.strFixedLength.isEmpty()) {
            final float float1 = Float.parseFloat(this.strFixedLength);
            this.etMarginWidth.setRegion((float)(float1 * 0.25), 0.0f, 6);
            this.etMarginWidth.setTextType(1);
            this.etMarginWidth.setTextWatcher();
            this.etHashSpacing.setRegion(float1, 0.0f, 6);
            this.etHashSpacing.setTextType(1);
            this.etHashSpacing.setTextWatcher();
        }
        this.etAngle.setRegion(180.0f, 0.0f, 0);
        this.etAngle.setTextType(0);
        this.etAngle.setTextWatcher();
        this.etFixedLength.setOnTouchListener((View$OnTouchListener)new _$$Lambda$ElementSettingFragment$3puXubCIphiVOjlNcfFy_hZfzf4(this));
    }
    
    private void initView(final View view) {
        this.tvTemplate = (TextView)view.findViewById(2131297669);
        this.etFixedLength = (EditText)view.findViewById(2131296635);
        this.etSpacing = (RegionNumberEditText)view.findViewById(2131296657);
        this.etLineWidth = (RegionNumberEditText)view.findViewById(2131296639);
        this.etBoldWidth = (RegionNumberEditText)view.findViewById(2131296627);
        this.etMarginWidth = (RegionNumberEditText)view.findViewById(2131296642);
        this.etHashSpacing = (RegionNumberEditText)view.findViewById(2131296638);
        this.etAngle = (RegionNumberEditText)view.findViewById(2131296625);
        this.llAtomFontSelect = (LinearLayout)view.findViewById(2131296983);
        this.tvAtomFontName = (TextView)view.findViewById(2131297535);
        this.tvAtomFontStyle = (TextView)view.findViewById(2131297537);
        this.tvAtomFontSize = (TextView)view.findViewById(2131297536);
        this.llTextFontSelect = (LinearLayout)view.findViewById(2131297033);
        this.tvTextFontName = (TextView)view.findViewById(2131297671);
        this.tvTextFontStyle = (TextView)view.findViewById(2131297673);
        this.tvTextFontSize = (TextView)view.findViewById(2131297672);
        this.imbtnBondsHelp = (ImageButton)view.findViewById(2131296864);
        this.imbtnChainsHelp = (ImageButton)view.findViewById(2131296865);
        this.fabSave = (FloatingActionButton)view.findViewById(2131296666);
        this.tvTemplate.setOnClickListener((View$OnClickListener)this);
        this.llAtomFontSelect.setOnClickListener((View$OnClickListener)this);
        this.llTextFontSelect.setOnClickListener((View$OnClickListener)this);
        this.imbtnBondsHelp.setOnClickListener((View$OnClickListener)this);
        this.imbtnChainsHelp.setOnClickListener((View$OnClickListener)this);
        this.fabSave.setOnClickListener((View$OnClickListener)this);
    }
    
    private boolean isNull() {
        this.getEtString();
        final String string = this.getString(2131820886);
        if (TextUtils.isEmpty((CharSequence)this.strFixedLength)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Fixed Length" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strSpacing)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Spacing" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strLineWidth)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Line Width" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strBoldWidth)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Bold Width" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strMarginWidth)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Margin Width" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strHashSpacing)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Hash Spacing" }));
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)this.strAngle)) {
            ToastUtil.showShort((CharSequence)StringUtils.format(string, new Object[] { "Angle" }));
            return false;
        }
        return !TextUtils.isEmpty((CharSequence)this.strAtomFontName) && !TextUtils.isEmpty((CharSequence)this.strAtomFontStyle) && !TextUtils.isEmpty((CharSequence)this.strAtomFontSize) && !TextUtils.isEmpty((CharSequence)this.strTextFontName) && !TextUtils.isEmpty((CharSequence)this.strTextFontStyle) && (TextUtils.isEmpty((CharSequence)this.strTextFontSize) ^ true);
    }
    
    private String removeZero(final String s) {
        String replaceAll = s;
        if (s.indexOf(".") > 0) {
            replaceAll = s.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return replaceAll;
    }
    
    private void saveFormat() {
        this.showMultiBtnDialog();
    }
    
    private void saveFormatState(final boolean b) {
        this.isSaving = true;
        new Handler().postDelayed((Runnable)new _$$Lambda$ElementSettingFragment$8hhOcvppfjzYiXP_h2WvuUItT6Y(this, b), 500L);
    }
    
    private void setAtomFont(final String text, final String text2, final String text3) {
        this.tvAtomFontName.setText((CharSequence)text);
        this.tvAtomFontStyle.setText((CharSequence)text2);
        this.tvAtomFontSize.setText((CharSequence)text3);
    }
    
    private void setDataFromDialog(final String documentEnable) {
        this.getDateFromDocument(documentEnable);
        this.setDocumentEnable(documentEnable);
    }
    
    private void setDefault(final GFormatValue gFormatValue) {
        this.tvTemplate.setText((CharSequence)GDocumentTypeEnum.valueOfCode(gFormatValue.getDocumentType()).getFullName());
        this.etFixedLength.setText((CharSequence)String.valueOf((Object)gFormatValue.getFixedLength()));
        this.etSpacing.setText((CharSequence)String.valueOf(gFormatValue.getSpacing()));
        this.etLineWidth.setText((CharSequence)String.valueOf((Object)gFormatValue.getLineWidth()));
        this.etBoldWidth.setText((CharSequence)String.valueOf((Object)gFormatValue.getBoldWidth()));
        this.etMarginWidth.setText((CharSequence)String.valueOf((Object)gFormatValue.getMarginWidth()));
        this.etHashSpacing.setText((CharSequence)String.valueOf((Object)gFormatValue.getHashSpacing()));
        this.etAngle.setText((CharSequence)String.valueOf(gFormatValue.getChainsAngle()));
        this.tvAtomFontName.setText((CharSequence)gFormatValue.getAtomFontName());
        this.tvAtomFontStyle.setText((CharSequence)GFontStyleEnum.valueOfCode(gFormatValue.getAtomFontStyle()).getFullName());
        this.tvAtomFontSize.setText((CharSequence)String.valueOf(gFormatValue.getAtomFontSize()));
        this.tvTextFontName.setText((CharSequence)gFormatValue.getTextFontName());
        this.tvTextFontStyle.setText((CharSequence)GFontStyleEnum.valueOfCode(gFormatValue.getTextFontStyle()).getFullName());
        this.tvTextFontSize.setText((CharSequence)String.valueOf(gFormatValue.getTextFontSize()));
        this.strFixedLength = gFormatValue.getFixedLength();
        this.setDocumentEnable(GDocumentTypeEnum.valueOfCode(gFormatValue.getDocumentType()).getFullName());
    }
    
    private void setDismissCur() {
        this.etFixedLength.setCursorVisible(false);
        this.etSpacing.setCursorVisible(false);
        this.etLineWidth.setCursorVisible(false);
        this.etBoldWidth.setCursorVisible(false);
        this.etMarginWidth.setCursorVisible(false);
        this.etHashSpacing.setCursorVisible(false);
        this.etAngle.setCursorVisible(false);
    }
    
    private void setDocumentEnable(final String s) {
        if (s.equals((Object)GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getFullName())) {
            this.setTextEnable(true);
            this.setTextColor("#333333");
        }
        else {
            this.setTextEnable(false);
            this.setTextColor("#999999");
        }
    }
    
    private GFormatValue setModel() {
        GFormatValue gFormatValue = new GFormatValue();
        this.getEtString();
        if (this.tvTemplate.getText().toString().equals((Object)GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getFullName())) {
            try {
                gFormatValue.setDocumentType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode());
                gFormatValue.setId(this.gFormatValueCurrent.getId());
                gFormatValue.setFixedLength(this.strFixedLength);
                gFormatValue.setSpacing(Integer.parseInt(this.strSpacing));
                gFormatValue.setLineWidth(this.strLineWidth);
                gFormatValue.setBoldWidth(this.strBoldWidth);
                gFormatValue.setMarginWidth(this.strMarginWidth);
                gFormatValue.setHashSpacing(this.strHashSpacing);
                gFormatValue.setChainsAngle(Integer.parseInt(this.strAngle));
                gFormatValue.setAtomFontName(this.strAtomFontName);
                gFormatValue.setAtomFontStyle(GFontStyleEnum.valueOfStyle(this.strAtomFontStyle).getCode());
                gFormatValue.setAtomFontSize(Integer.parseInt(this.strAtomFontSize));
                gFormatValue.setTextFontName(this.strTextFontName);
                gFormatValue.setTextFontStyle(GFontStyleEnum.valueOfStyle(this.strTextFontStyle).getCode());
                gFormatValue.setTextFontSize(Integer.parseInt(this.strTextFontSize));
            }
            catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        else if (this.tvTemplate.getText().toString().equals((Object)GDocumentTypeEnum.ACS96\u683c\u5f0f.getFullName())) {
            gFormatValue = this.gFormatValueCurrent.clone();
        }
        else if (this.tvTemplate.getText().toString().equals((Object)GDocumentTypeEnum.KingDraw\u683c\u5f0f.getFullName())) {
            gFormatValue = this.gFormatValueCurrent.clone();
        }
        return gFormatValue;
    }
    
    private void setTextColor(final String s) {
        this.etFixedLength.setTextColor(Color.parseColor(s));
        this.etSpacing.setTextColor(Color.parseColor(s));
        this.etLineWidth.setTextColor(Color.parseColor(s));
        this.etBoldWidth.setTextColor(Color.parseColor(s));
        this.etMarginWidth.setTextColor(Color.parseColor(s));
        this.etHashSpacing.setTextColor(Color.parseColor(s));
        this.etAngle.setTextColor(Color.parseColor(s));
        this.tvAtomFontName.setTextColor(Color.parseColor(s));
        this.tvAtomFontStyle.setTextColor(Color.parseColor(s));
        this.tvAtomFontSize.setTextColor(Color.parseColor(s));
        this.tvTextFontName.setTextColor(Color.parseColor(s));
        this.tvTextFontStyle.setTextColor(Color.parseColor(s));
        this.tvTextFontSize.setTextColor(Color.parseColor(s));
    }
    
    private void setTextEnable(final boolean enabled) {
        this.etFixedLength.setEnabled(enabled);
        this.etSpacing.setEnabled(enabled);
        this.etLineWidth.setEnabled(enabled);
        this.etBoldWidth.setEnabled(enabled);
        this.etMarginWidth.setEnabled(enabled);
        this.etHashSpacing.setEnabled(enabled);
        this.etAngle.setEnabled(enabled);
        this.llAtomFontSelect.setEnabled(enabled);
        this.llTextFontSelect.setEnabled(enabled);
    }
    
    private void setTextFont(final String text, final String text2, final String text3) {
        this.tvTextFontName.setText((CharSequence)text);
        this.tvTextFontStyle.setText((CharSequence)text2);
        this.tvTextFontSize.setText((CharSequence)text3);
    }
    
    private void showFontPicker(final int n) {
        String s = null;
        String s2;
        String s3;
        if (n != 0) {
            if (n != 1) {
                s2 = null;
                s3 = null;
            }
            else {
                s = this.tvTextFontName.getText().toString();
                s2 = this.tvTextFontStyle.getText().toString();
                s3 = this.tvTextFontSize.getText().toString();
            }
        }
        else {
            s = this.tvAtomFontName.getText().toString();
            s2 = this.tvAtomFontStyle.getText().toString();
            s3 = this.tvAtomFontSize.getText().toString();
        }
        final WheelPop wheelPop = new WheelPop(this.mContext, s, s2, s3);
        this.setBackgroundAlpha(0.5f);
        wheelPop.setAnimationStyle(2131886852);
        wheelPop.showAtLocation(((ViewGroup)this.requireActivity().findViewById(16908290)).getChildAt(0), 80, 0, 0);
        wheelPop.setSure((WheelPop$OnClickButtonSureListener)new _$$Lambda$ElementSettingFragment$ezsttpMP_X8x_ft5hGVCQ3I2l0A(this, n));
        wheelPop.setOnDismissListener((PopupWindow$OnDismissListener)new _$$Lambda$ElementSettingFragment$fdjzf0Ah3bErTIhe6fnSXApTmzc(this));
    }
    
    private void showMultiBtnDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setTitle(2131821524).setMessage(2131821023).setPositiveButton(2131821022, (DialogInterface$OnClickListener)new _$$Lambda$ElementSettingFragment$lO_BDqQqzivrRGHiIEtJDXvZc7U(this)).setNeutralButton(2131820661, (DialogInterface$OnClickListener)_$$Lambda$ElementSettingFragment$aBuZlj07jlqWye_IRBr4fMX7p18.INSTANCE).setNegativeButton(2131820778, (DialogInterface$OnClickListener)new _$$Lambda$ElementSettingFragment$8t_yG6_ODWCLTFJvJZ37Pq05YTA(this));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }
    
    private void showSaveDialog() {
        new AlertDialog$Builder(this.getContext()).setTitle(2131821524).setMessage((CharSequence)StringUtils.format(this.getString(2131820887), new Object[] { GDocumentTypeEnum.valueOfCode(this.gFormatValueCurrent.getDocumentType()).getFullName() })).setPositiveButton(2131821338, (DialogInterface$OnClickListener)new _$$Lambda$ElementSettingFragment$pThOkJZ49iHHm69bwud4n1YfN8E(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)_$$Lambda$ElementSettingFragment$B5IhsqinOqTm5_4wTHz00gzFEgw.INSTANCE).show();
    }
    
    private void showTemplateDialog() {
        final String[] strItems = this.strItems;
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
        alertDialog$Builder.setItems((CharSequence[])strItems, (DialogInterface$OnClickListener)new _$$Lambda$ElementSettingFragment$MGA_vLk_papFcgGIKF3U_tW_P8c(this, strItems));
        alertDialog$Builder.show();
    }
    
    public void backCheck() {
        if (this.gFormatValueCurrent.sameToFormat(this.setModel())) {
            if (!this.isSaving) {
                OtherSettingActivity.getOtherSettingActivity().finish();
            }
        }
        else {
            this.showSaveDialog();
        }
    }
    
    public void onClick(final View view) {
        if (view == this.tvTemplate) {
            this.showTemplateDialog();
        }
        else if (view == this.llAtomFontSelect) {
            this.showFontPicker(0);
        }
        else if (view == this.llTextFontSelect) {
            this.showFontPicker(1);
        }
        else if (view == this.imbtnBondsHelp) {
            if (this.guideManager == null) {
                this.guideManager = new GuideManager(this.mContext);
            }
            this.guideManager.showFormatBondsGuide((View)this.imbtnBondsHelp);
        }
        else if (view == this.imbtnChainsHelp) {
            if (this.guideManager == null) {
                this.guideManager = new GuideManager(this.mContext);
            }
            this.guideManager.showFormatChainsGuide((View)this.imbtnChainsHelp);
        }
        else if (view == this.fabSave && this.isNull()) {
            this.saveFormat();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = (Context)this.getActivity();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final ViewGroup viewGroup2 = (ViewGroup)layoutInflater.inflate(2131493039, viewGroup, false);
        this.initView((View)viewGroup2);
        this.initData();
        return (View)viewGroup2;
    }
    
    public void setBackgroundAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = this.requireActivity().getWindow().getAttributes();
        attributes.alpha = alpha;
        this.requireActivity().getWindow().setAttributes(attributes);
    }
    
    public void setSaveStateListener(final ElementSettingFragment.ElementSettingFragment$OnSaveSuccessFormatListener onSaveSuccessFormatListener) {
        this.onSaveSuccessFormatListener = onSaveSuccessFormatListener;
    }
}
