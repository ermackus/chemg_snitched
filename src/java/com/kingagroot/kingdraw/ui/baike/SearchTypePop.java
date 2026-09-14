package com.kingagroot.kingdraw.ui.baike;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RadioGroup$OnCheckedChangeListener;
import android.widget.PopupWindow;

public class SearchTypePop extends PopupWindow implements RadioGroup$OnCheckedChangeListener
{
    View conentView;
    OnSearchTypeChangeListen onSearchTypeChangeListen;
    private RadioButton rbtCid;
    private RadioButton rbtMf;
    private RadioButton rbtSimilarityDegree;
    private RadioButton rbtSubstructure;
    private RadioButton rbtTxt;
    private RadioGroup rgSearch;
    private SearchDataRightType rightType;
    private final SearchDataType type;
    
    public SearchTypePop(final Context context, final SearchDataType type, final SearchDataRightType rightType) {
        super(context);
        this.setContentView(this.conentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493150, (ViewGroup)null));
        this.setWidth(-2);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView();
        this.uiChangeByType(this.type = type);
        this.rightType = rightType;
        final int n = SearchTypePop$1.$SwitchMap$com$kingagroot$kingdraw$ui$baike$SearchDataRightType[this.rightType.ordinal()];
        if (n != 1) {
            if (n != 2) {
                if (n != 3) {
                    if (n != 4) {
                        if (n == 5) {
                            this.rbtCid.setChecked(true);
                        }
                    }
                    else {
                        this.rbtMf.setChecked(true);
                    }
                }
                else {
                    this.rbtTxt.setChecked(true);
                }
            }
            else {
                this.rbtSimilarityDegree.setChecked(true);
            }
        }
        else {
            this.rbtSubstructure.setChecked(true);
        }
        this.rgSearch.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)this);
    }
    
    private void initView() {
        this.rgSearch = (RadioGroup)this.conentView.findViewById(2131297274);
        this.rbtSubstructure = (RadioButton)this.conentView.findViewById(2131297253);
        this.rbtSimilarityDegree = (RadioButton)this.conentView.findViewById(2131297252);
        this.rbtTxt = (RadioButton)this.conentView.findViewById(2131297254);
        this.rbtCid = (RadioButton)this.conentView.findViewById(2131297238);
        this.rbtMf = (RadioButton)this.conentView.findViewById(2131297245);
    }
    
    private void uiChangeByType(final SearchDataType searchDataType) {
        final int n = SearchTypePop$1.$SwitchMap$com$kingagroot$kingdraw$ui$baike$SearchDataType[searchDataType.ordinal()];
        if (n != 1 && n != 2) {
            if (n != 3) {
                if (n != 4) {
                    if (n == 5) {
                        this.rbtSubstructure.setVisibility(0);
                        this.rbtSimilarityDegree.setVisibility(0);
                        this.rbtTxt.setVisibility(0);
                        this.rbtCid.setVisibility(0);
                        this.rbtMf.setVisibility(0);
                        this.rbtSubstructure.setChecked(true);
                    }
                }
                else {
                    this.rbtSubstructure.setVisibility(8);
                    this.rbtSimilarityDegree.setVisibility(8);
                    this.rbtTxt.setVisibility(0);
                    this.rbtCid.setVisibility(8);
                    this.rbtMf.setVisibility(0);
                    this.rbtMf.setChecked(true);
                }
            }
            else {
                this.rbtSubstructure.setVisibility(8);
                this.rbtSimilarityDegree.setVisibility(8);
                this.rbtTxt.setVisibility(0);
                this.rbtCid.setVisibility(0);
                this.rbtMf.setVisibility(8);
                this.rbtCid.setChecked(true);
            }
        }
        else {
            this.rbtSubstructure.setVisibility(0);
            this.rbtSimilarityDegree.setVisibility(0);
            this.rbtTxt.setVisibility(0);
            this.rbtCid.setVisibility(8);
            this.rbtMf.setVisibility(8);
            this.rbtSubstructure.setChecked(true);
        }
    }
    
    public void onCheckedChanged(final RadioGroup radioGroup, final int n) {
        if (n == this.rbtSubstructure.getId()) {
            this.onSearchTypeChangeListen.onSubstructure();
            this.rightType = SearchDataRightType.Substructure;
        }
        else if (n == this.rbtSimilarityDegree.getId()) {
            this.onSearchTypeChangeListen.onSimilarityDegree();
            this.rightType = SearchDataRightType.SimilarityDegree;
        }
        else if (n == this.rbtTxt.getId()) {
            this.onSearchTypeChangeListen.onTxt();
            this.rightType = SearchDataRightType.Txt;
        }
        else if (n == this.rbtCid.getId()) {
            this.onSearchTypeChangeListen.onCid();
            this.rightType = SearchDataRightType.Cid;
        }
        else if (n == this.rbtMf.getId()) {
            this.onSearchTypeChangeListen.onMF();
            this.rightType = SearchDataRightType.Mf;
        }
        this.dismiss();
    }
    
    void setOnSearchTypeChangeListen(final OnSearchTypeChangeListen onSearchTypeChangeListen) {
        this.onSearchTypeChangeListen = onSearchTypeChangeListen;
    }
    
    public interface OnSearchTypeChangeListen
    {
        void onCid();
        
        void onMF();
        
        void onSimilarityDegree();
        
        void onSubstructure();
        
        void onTxt();
    }
}
