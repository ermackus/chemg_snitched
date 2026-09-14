package com.kingagroot.component.ui.model;

import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import com.goodsrc.library.utils.StringUtils;
import android.text.Spannable;
import java.util.List;

public class ChemicalAnalysisModel
{
    private int charge;
    private List<EleAnal> eleAnal;
    private double exactMass;
    private String formula;
    private double molWt;
    private List<MzPercent> mzPercent;
    private int radicalCount;
    
    public int getCharge() {
        return this.charge;
    }
    
    public List<EleAnal> getEleAnal() {
        return this.eleAnal;
    }
    
    public double getExactMass() {
        return this.exactMass;
    }
    
    public Spannable getFormula() {
        final StringBuilder sb = new StringBuilder();
        final List separateCLable = StringUtils.separateCLable(this.formula);
        for (int i = 0; i < separateCLable.size(); ++i) {
            final String[] separateTextWithSubCount = StringUtils.separateTextWithSubCount((String)separateCLable.get(i));
            sb.append("<span font=\"Arial\" size=\"6\" fstyle=\"0\" drawtype=\"0\">");
            sb.append(separateTextWithSubCount[0]);
            sb.append("</span>");
            sb.append("<span font=\"Arial\" size=\"6\" fstyle=\"0\" drawtype=\"4\">");
            sb.append(separateTextWithSubCount[1]);
            sb.append("</span>");
        }
        final String s = "";
        final int radicalCount = this.radicalCount;
        String string = s;
        if (radicalCount > 0) {
            String string2 = s;
            if (radicalCount != 1) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(String.valueOf(this.radicalCount));
                string2 = sb2.toString();
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append("\u2022");
            string = sb3.toString();
        }
        final int charge = this.charge;
        String s2 = string;
        if (charge != 0) {
            String string3 = string;
            if (1 != Math.abs(charge)) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(string);
                sb4.append(String.valueOf(Math.abs(this.charge)));
                string3 = sb4.toString();
            }
            if (this.charge > 0) {
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(string3);
                sb5.append("+");
                s2 = sb5.toString();
            }
            else {
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(string3);
                sb6.append("\uff0d");
                s2 = sb6.toString();
            }
        }
        sb.append("<span font=\"Arial\" size=\"6\" fstyle=\"0\" drawtype=\"2\">");
        sb.append(s2);
        sb.append("</span>");
        return (Spannable)SpanUtil.htmlToSpan(sb.toString());
    }
    
    public double getMolWt() {
        return this.molWt;
    }
    
    public List<MzPercent> getMzPercent() {
        return this.mzPercent;
    }
    
    public int getRadicalCount() {
        return this.radicalCount;
    }
    
    public void setCharge(final int charge) {
        this.charge = charge;
    }
    
    public void setEleAnal(final List<EleAnal> eleAnal) {
        this.eleAnal = eleAnal;
    }
    
    public void setExactMass(final double exactMass) {
        this.exactMass = exactMass;
    }
    
    public void setFormula(final String formula) {
        this.formula = formula;
    }
    
    public void setMolWt(final double molWt) {
        this.molWt = molWt;
    }
    
    public void setMzPercent(final List<MzPercent> mzPercent) {
        this.mzPercent = mzPercent;
    }
    
    public void setRadicalCount(final int radicalCount) {
        this.radicalCount = radicalCount;
    }
    
    public class EleAnal
    {
        private String eleName;
        private double percent;
        final ChemicalAnalysisModel this$0;
        
        public EleAnal(final ChemicalAnalysisModel this$0) {
            this.this$0 = this$0;
        }
        
        public String getEleName() {
            return this.eleName;
        }
        
        public double getPercent() {
            return this.percent;
        }
        
        public void setEleName(final String eleName) {
            this.eleName = eleName;
        }
        
        public void setPercent(final double percent) {
            this.percent = percent;
        }
    }
    
    public class MzPercent
    {
        private double mz;
        private double percent;
        final ChemicalAnalysisModel this$0;
        
        public MzPercent(final ChemicalAnalysisModel this$0) {
            this.this$0 = this$0;
        }
        
        public double getMz() {
            return this.mz;
        }
        
        public double getPercent() {
            return this.percent;
        }
        
        public void setMz(final double mz) {
            this.mz = mz;
        }
        
        public void setPercent(final int n) {
            this.percent = n;
        }
    }
}
