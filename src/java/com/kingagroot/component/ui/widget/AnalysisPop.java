package com.kingagroot.component.ui.widget;

import android.app.Activity;
import android.content.Intent;
import com.kingagroot.kingdraw.core.model.ChemPropertyConfig;
import com.kingagroot.kingdraw.core.utils.GNumberUtil;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.goodsrc.library.utils.SystemUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.RelativeLayout;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.widget.ImageButton;
import android.content.Context;
import android.widget.CheckBox;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.PopupWindow;

public class AnalysisPop extends PopupWindow implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    private static final int DECIMAL_NUMBER_MAX = 5;
    private static final int DECIMAL_NUMBER_MIN = 0;
    private View anchor;
    private CheckBox cbElemAnal;
    private CheckBox cbExactMass;
    private CheckBox cbFormula;
    private CheckBox cbMolWt;
    private CheckBox cbMz;
    private final Context context;
    private int decimalCount;
    private ImageButton ibtClose;
    private ImageButton ibtPaste;
    boolean isRegisterReceiver;
    private final KingDrawView kingDrawView;
    private int mHeight;
    private int mWidth;
    private final ChemicalAnalysisModel model;
    private Double mzMax;
    private Double mzMin;
    private OrientationChangedReceive orientationChangedReceive;
    private RelativeLayout rlMzPic;
    private TextView tvAdd;
    private TextView tvElemAnal;
    private TextView tvExactMass;
    private TextView tvFormula;
    private TextView tvMolWt;
    private TextView tvMz;
    private TextView tvNum;
    private TextView tvSub;
    private View viewLine;
    
    public AnalysisPop(final Context context, final KingDrawView kingDrawView, final ChemicalAnalysisModel model) {
        super(context);
        this.decimalCount = 2;
        final Double value = 0.0;
        this.mzMax = value;
        this.mzMin = value;
        this.isRegisterReceiver = false;
        this.context = context;
        this.kingDrawView = kingDrawView;
        this.model = model;
        this.setWidth(-2);
        this.setHeight(-2);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.component_pop_analysis, (ViewGroup)null);
        inflate.measure(0, 0);
        this.setContentView(inflate);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView(inflate);
        this.setText();
        this.setMzImage();
    }
    
    private void addDecimalNumber() {
        ++this.decimalCount;
        this.setEnableAdd();
    }
    
    private void calWidthAndHeight(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (SystemUtils.getScreenOrientation(context) == 2) {
            this.mWidth = (int)(displayMetrics.widthPixels * 0.5);
            this.mHeight = (int)(displayMetrics.heightPixels * 0.8);
        }
        else if (SystemUtils.getScreenOrientation(context) == 1) {
            this.mWidth = (int)(displayMetrics.widthPixels * 0.8);
            this.mHeight = (int)(displayMetrics.heightPixels * 0.6);
        }
    }
    
    private void initView(final View view) {
        this.ibtClose = (ImageButton)view.findViewById(R.id.ibt_close);
        this.tvSub = (TextView)view.findViewById(R.id.tv_sub);
        this.tvNum = (TextView)view.findViewById(R.id.tv_num);
        this.tvAdd = (TextView)view.findViewById(R.id.tv_add);
        this.tvFormula = (TextView)view.findViewById(R.id.tv_formula);
        this.tvExactMass = (TextView)view.findViewById(R.id.tv_exact_mass);
        this.tvMolWt = (TextView)view.findViewById(R.id.tv_mol_wt);
        this.tvElemAnal = (TextView)view.findViewById(R.id.tv_elem_anal);
        this.rlMzPic = (RelativeLayout)view.findViewById(R.id.rl_mz_pic);
        this.tvMz = (TextView)view.findViewById(R.id.tv_mz);
        this.viewLine = view.findViewById(R.id.view_line);
        this.tvSub.setOnClickListener((View$OnClickListener)this);
        this.tvAdd.setOnClickListener((View$OnClickListener)this);
        this.ibtClose.setOnClickListener((View$OnClickListener)this);
        this.setEnableAdd();
        this.setEnableSub();
        this.ibtPaste = (ImageButton)view.findViewById(R.id.ibt_paste);
        this.cbFormula = (CheckBox)view.findViewById(R.id.cb_formula);
        this.cbExactMass = (CheckBox)view.findViewById(R.id.cb_exact_mass);
        this.cbMolWt = (CheckBox)view.findViewById(R.id.cb_mol_wt);
        this.cbElemAnal = (CheckBox)view.findViewById(R.id.cb_elem_anal);
        this.cbMz = (CheckBox)view.findViewById(R.id.cb_mz);
        this.ibtPaste.setOnClickListener((View$OnClickListener)this);
    }
    
    private void registerReceiver() {
        if (this.isRegisterReceiver) {
            return;
        }
        this.orientationChangedReceive = new OrientationChangedReceive();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        this.context.registerReceiver((BroadcastReceiver)this.orientationChangedReceive, intentFilter);
        this.isRegisterReceiver = true;
    }
    
    private void setEnableAdd() {
        if (this.decimalCount >= 5) {
            this.decimalCount = 5;
            this.tvAdd.setEnabled(false);
        }
    }
    
    private void setEnableSub() {
        if (this.decimalCount <= 0) {
            this.decimalCount = 0;
            this.tvSub.setEnabled(false);
        }
    }
    
    private void setMzImage() {
        if (this.model.getMzPercent() != null) {
            final List<ChemicalAnalysisModel.MzPercent> mzPercent = this.model.getMzPercent();
            final int size = mzPercent.size();
            final int measuredWidth = this.viewLine.getMeasuredWidth();
            final int dp2px = GDensityUtil.dp2px(10.0f);
            int i = 0;
            int n;
            if (size > 1) {
                n = (measuredWidth - dp2px - 10) / (size - 1);
            }
            else {
                n = 0;
            }
            this.rlMzPic.removeAllViews();
            while (i < size) {
                final TextView textView = new TextView(this.context);
                textView.setBackgroundColor(-16777216);
                final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(GDensityUtil.dp2px(1.0f), (int)Math.round(((ChemicalAnalysisModel.MzPercent)mzPercent.get(i)).getPercent()));
                layoutParams.addRule(12);
                textView.setId(i);
                if (i == 0) {
                    layoutParams.leftMargin = 5;
                }
                else {
                    layoutParams.leftMargin = i * n + 5;
                }
                textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                this.rlMzPic.addView((View)textView);
                ++i;
            }
        }
    }
    
    private void setText() {
        this.tvNum.setText((CharSequence)String.valueOf(this.decimalCount));
        if (this.model != null) {
            final StringBuilder sb = new StringBuilder("#0");
            final int n = 0;
            for (int i = 0; i < this.decimalCount; ++i) {
                if (i == 0) {
                    sb.append(".0");
                }
                else {
                    sb.append("0");
                }
            }
            final DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            this.tvFormula.setText((CharSequence)this.model.getFormula());
            this.tvExactMass.setText((CharSequence)GNumberUtil.format(decimalFormat, this.model.getExactMass()));
            this.tvMolWt.setText((CharSequence)GNumberUtil.format(decimalFormat, this.model.getMolWt()));
            final List<ChemicalAnalysisModel.EleAnal> eleAnal = this.model.getEleAnal();
            final StringBuilder sb2 = new StringBuilder();
            if (eleAnal != null) {
                for (int size = eleAnal.size(), j = 0; j < size; ++j) {
                    final ChemicalAnalysisModel.EleAnal eleAnal2 = (ChemicalAnalysisModel.EleAnal)eleAnal.get(j);
                    final String format = GNumberUtil.format(decimalFormat, eleAnal2.getPercent());
                    sb2.append(eleAnal2.getEleName());
                    sb2.append(",");
                    sb2.append(format);
                    sb2.append(";");
                    if (j != size - 1) {
                        sb2.append("\n");
                    }
                }
            }
            this.tvElemAnal.setText((CharSequence)sb2.toString());
            final DecimalFormat decimalFormat2 = new DecimalFormat("#0.0");
            decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
            if (this.model.getMzPercent() != null) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(GNumberUtil.format(decimalFormat, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(0)).getMz()));
                sb3.append("(");
                sb3.append(GNumberUtil.format(decimalFormat2, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(0)).getPercent()));
                sb3.append("%)");
                final StringBuilder sb4 = new StringBuilder(sb3.toString());
                for (int size2 = this.model.getMzPercent().size(), k = n; k < size2; ++k) {
                    double n2;
                    if (k == 0) {
                        n2 = ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getMz();
                    }
                    else {
                        n2 = Math.max((double)this.mzMax, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getMz());
                    }
                    this.mzMax = n2;
                    double n3;
                    if (k == 0) {
                        n3 = ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getMz();
                    }
                    else {
                        n3 = Math.max((double)this.mzMin, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getMz());
                    }
                    this.mzMin = n3;
                    if (k > 0) {
                        sb4.append("\n");
                        sb4.append(GNumberUtil.format(decimalFormat, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getMz()));
                        sb4.append("(");
                        sb4.append(GNumberUtil.format(decimalFormat2, ((ChemicalAnalysisModel.MzPercent)this.model.getMzPercent().get(k)).getPercent()));
                        sb4.append("%)");
                    }
                }
                this.tvMz.setText((CharSequence)sb4.toString());
            }
        }
    }
    
    private void subDecimalNumber() {
        --this.decimalCount;
        this.setEnableSub();
    }
    
    private void unregisterReceiver() {
        final OrientationChangedReceive orientationChangedReceive = this.orientationChangedReceive;
        if (orientationChangedReceive != null && this.isRegisterReceiver) {
            this.context.unregisterReceiver((BroadcastReceiver)orientationChangedReceive);
            this.isRegisterReceiver = false;
        }
    }
    
    public void dismiss() {
        super.dismiss();
        this.unregisterReceiver();
    }
    
    public void onClick(final View view) {
        if (view == this.tvAdd) {
            this.addDecimalNumber();
            if (!this.tvSub.isEnabled()) {
                this.tvSub.setEnabled(true);
            }
            this.setText();
        }
        else if (view == this.tvSub) {
            this.subDecimalNumber();
            if (!this.tvAdd.isEnabled()) {
                this.tvAdd.setEnabled(true);
            }
            this.setText();
        }
        else if (view == this.ibtClose) {
            this.dismiss();
        }
        else if (view == this.ibtPaste) {
            this.dismiss();
            final ChemPropertyConfig chemPropertyConfig = new ChemPropertyConfig();
            chemPropertyConfig.enableFormula = this.cbFormula.isChecked();
            chemPropertyConfig.enableExactMass = this.cbExactMass.isChecked();
            chemPropertyConfig.enableMolWt = this.cbMolWt.isChecked();
            chemPropertyConfig.enableElemAnal = this.cbElemAnal.isChecked();
            chemPropertyConfig.enableMz = this.cbMz.isChecked();
            chemPropertyConfig.decimalCount = this.decimalCount;
            this.kingDrawView.addChemPropertyText(chemPropertyConfig);
        }
    }
    
    public void show(final View anchor) {
        this.anchor = anchor;
        this.registerReceiver();
        this.showAtLocation(anchor, 17, 0, 0);
    }
    
    private class OrientationChangedReceive extends BroadcastReceiver
    {
        final AnalysisPop this$0;
        
        private OrientationChangedReceive(final AnalysisPop this$0) {
            this.this$0 = this$0;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            final Activity activity = (Activity)this.this$0.context;
            if (activity != null && !activity.isFinishing()) {
                this.this$0.update(0, 0, -2, -2);
            }
        }
    }
}
