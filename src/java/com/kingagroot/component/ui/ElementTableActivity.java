package com.kingagroot.component.ui;

import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.content.res.Configuration;
import java.io.Serializable;
import android.content.Intent;
import com.kingagroot.component.ui.widget.periodictable.NavPeriodicCollectView$OnNavPeriodicCollectViewListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import com.kingagroot.component.ui.utils.SVGDrawUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import com.kingagroot.component.ui.db.impl.PeriodicTableDBImpl;
import androidx.appcompat.app.AppCompatDelegate;
import com.kingagroot.component.ui.model.GAtom;
import java.util.List;
import com.kingagroot.component.ui.db.PeriodicTableDBI;
import com.goodsrc.ui.library.widget.notch.NotchView;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import com.kingagroot.component.ui.widget.periodictable.NavPeriodicCollectView;
import android.widget.ImageButton;
import com.kingagroot.component.ui.widget.periodictable.HVScrollView;
import android.widget.GridView;
import android.widget.FrameLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import com.kingagroot.component.ui.widget.periodictable.ChemAdapter;

public class ElementTableActivity extends ComponentBaseActivity
{
    private static final int BottomMaxNum = 35;
    private static final int TopMaxNum = 126;
    public static boolean isShow;
    private ChemAdapter bottomTableAdapter;
    private DrawerLayout drawerlayout;
    private FrameLayout frameLayout;
    private GridView gvBottom;
    private GridView gvTop;
    private HVScrollView hvScrollView;
    private ImageButton ibRight;
    private ImageButton imgbtnClose;
    private NavPeriodicCollectView navCollect;
    NotchContext notchContext;
    NotchView notchView;
    PeriodicTableDBI periodicTableDBI;
    List<GAtom> tableBottomModels;
    List<GAtom> tableTopModels;
    private ChemAdapter topTableAdapter;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ElementTableActivity.isShow = false;
    }
    
    public ElementTableActivity() {
        this.periodicTableDBI = (PeriodicTableDBI)new PeriodicTableDBImpl();
        this.tableTopModels = (List<GAtom>)new ArrayList();
        this.tableBottomModels = (List<GAtom>)new ArrayList();
    }
    
    private void addAnim(final GAtom gAtom, final View view) {
        final View inflate = View.inflate((Context)this, R$layout.component_adapter_chem_table, (ViewGroup)null);
        final int n = (int)this.getResources().getDimension(R$dimen.element_table_size);
        inflate.setLayoutParams(new ViewGroup$LayoutParams(n, n));
        final TextView textView = (TextView)inflate.findViewById(R$id.tv_weight);
        final TextView textView2 = (TextView)inflate.findViewById(R$id.tv_name);
        final LinearLayout linearLayout = (LinearLayout)inflate.findViewById(R$id.ll_content);
        final Drawable changeColor = SVGDrawUtils.changeColor((Context)this, R$drawable.bg_periodic_table_item, gAtom.getColorHex());
        textView2.setTextColor(gAtom.getColorHex());
        linearLayout.setBackgroundDrawable(changeColor);
        textView.setTextColor(gAtom.getIndex());
        textView2.setText((CharSequence)gAtom.getName());
        final int[] array = new int[2];
        view.getLocationInWindow(array);
        inflate.setX((float)array[0]);
        inflate.setY((float)array[1]);
        this.frameLayout.addView(inflate);
        final int[] array2 = new int[2];
        this.ibRight.getLocationInWindow(array2);
        final ValueAnimator ofObject = ValueAnimator.ofObject((TypeEvaluator)new ElementTableActivity.ElementTableActivity$BezierTypeEvaluator(this, new PointF((float)array[0], (float)array2[1])), new Object[] { new PointF((float)array[0], (float)array[1]), new PointF((float)array2[0], (float)array2[1]) });
        ofObject.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ElementTableActivity$9(this, inflate));
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)inflate, "scaleX", new float[] { 1.0f, 0.3f });
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object)inflate, "scaleY", new float[] { 1.0f, 0.3f });
        final AnimatorSet set = new AnimatorSet();
        set.setDuration(800L);
        set.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        set.addListener((Animator$AnimatorListener)new ElementTableActivity$10(this, inflate));
        set.play((Animator)ofObject).with((Animator)ofFloat).with((Animator)ofFloat2);
        set.start();
    }
    
    private void addCollect(final GAtom gAtom, final View view) {
        if (this.periodicTableDBI.setCollect(gAtom.getIndex(), true)) {
            gAtom.setCollect(true);
            this.addAnim(gAtom, view);
        }
        this.navCollect.refreshData();
    }
    
    private void deleteCollect(final GAtom gAtom) {
        if (this.periodicTableDBI.setCollect(gAtom.getIndex(), false)) {
            gAtom.setCollect(false);
        }
        this.navCollect.refreshData();
    }
    
    private GAtom getNullItem() {
        final GAtom gAtom = new GAtom();
        gAtom.index = -1;
        gAtom.name = "";
        gAtom.colorHex = -1;
        return gAtom;
    }
    
    private void initView() {
        this.imgbtnClose = (ImageButton)this.findViewById(R$id.ibt_close);
        this.gvTop = (GridView)this.findViewById(R$id.gv_top);
        this.gvBottom = (GridView)this.findViewById(R$id.gv_bottom);
        this.frameLayout = (FrameLayout)this.findViewById(R$id.frame);
        this.hvScrollView = (HVScrollView)this.findViewById(R$id.hvScrollView);
        (this.ibRight = (ImageButton)this.findViewById(R$id.ibt_right)).setOnClickListener((View$OnClickListener)new ElementTableActivity$2(this));
        this.topTableAdapter = new ChemAdapter((Context)this, (List)this.tableTopModels);
        this.bottomTableAdapter = new ChemAdapter((Context)this, (List)this.tableBottomModels);
        this.gvTop.setAdapter((ListAdapter)this.topTableAdapter);
        this.gvBottom.setAdapter((ListAdapter)this.bottomTableAdapter);
        this.gvTop.setOnItemClickListener((AdapterView$OnItemClickListener)new ElementTableActivity$3(this));
        this.gvTop.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new ElementTableActivity$4(this));
        this.gvBottom.setOnItemClickListener((AdapterView$OnItemClickListener)new ElementTableActivity$5(this));
        this.gvBottom.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new ElementTableActivity$6(this));
        this.imgbtnClose.setOnClickListener((View$OnClickListener)new ElementTableActivity$7(this));
        this.navCollect = (NavPeriodicCollectView)this.findViewById(R$id.nav_collect);
        this.drawerlayout = (DrawerLayout)this.findViewById(R$id.drawerlayout);
        this.navCollect.setOnNavPeriodicCollectViewListener((NavPeriodicCollectView$OnNavPeriodicCollectViewListener)new ElementTableActivity$8(this));
        this.drawerlayout.setScrimColor(0);
    }
    
    private void intData() {
        this.tableTopModels.clear();
        this.tableBottomModels.clear();
        final List periodicTable = this.periodicTableDBI.findPeriodicTable("");
        if (periodicTable != null) {
            if (periodicTable.size() != 0) {
                Object o = null;
                final int n = 0;
                for (int i = 0; i < 126; ++i) {
                    if (i == 0) {
                        o = periodicTable.get(i);
                    }
                    else if (i > 0 && i < 17) {
                        o = this.getNullItem();
                    }
                    else if (i >= 17 && i <= 19) {
                        o = periodicTable.get(i - 16);
                    }
                    else if (i > 19 && i < 30) {
                        o = this.getNullItem();
                    }
                    else if (i >= 30 && i <= 37) {
                        o = periodicTable.get(i - 26);
                    }
                    else if (i > 37 && i < 48) {
                        o = this.getNullItem();
                    }
                    else if (i >= 48 && i <= 91) {
                        o = periodicTable.get(i - 36);
                    }
                    else if (i == 92) {
                        o = new GAtom();
                        ((GAtom)o).index = -1;
                        ((GAtom)o).name = "";
                        ((GAtom)o).colorHex = -1;
                    }
                    else if (i >= 93 && i <= 109) {
                        o = periodicTable.get(i - 22);
                    }
                    else if (i == 110) {
                        o = new GAtom();
                        ((GAtom)o).index = -1;
                        ((GAtom)o).name = "";
                        ((GAtom)o).colorHex = -1;
                    }
                    else if (i >= 111) {
                        o = periodicTable.get(i - 8);
                    }
                    this.tableTopModels.add(o);
                }
                this.topTableAdapter.notifyDataSetChanged();
                GAtom gAtom2 = null;
                for (int j = n; j < 35; ++j, o = gAtom2) {
                    Label_0481: {
                        GAtom gAtom;
                        if (j < 2) {
                            gAtom = this.getNullItem();
                        }
                        else if (j >= 2 && j <= 16) {
                            gAtom = (GAtom)periodicTable.get(j + 54);
                        }
                        else if (j > 16 && j < 20) {
                            gAtom = this.getNullItem();
                        }
                        else {
                            gAtom2 = (GAtom)o;
                            if (j < 20) {
                                break Label_0481;
                            }
                            gAtom2 = (GAtom)o;
                            if (j > 34) {
                                break Label_0481;
                            }
                            gAtom = (GAtom)periodicTable.get(j + 68);
                        }
                        gAtom2 = gAtom;
                    }
                    this.tableBottomModels.add((Object)gAtom2);
                }
                this.bottomTableAdapter.notifyDataSetChanged();
            }
        }
    }
    
    private void resultData(final int n, final GAtom gAtom) {
        final Intent intent = new Intent();
        intent.putExtra("data", (Serializable)gAtom);
        this.setResult(n, intent);
        this.finish();
    }
    
    public void finish() {
        ElementTableActivity.isShow = false;
        super.finish();
        this.overridePendingTransition(R$anim.activity_stay, R$anim.push_bottom_out);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final NotchView notchView = this.notchView;
        if (notchView != null) {
            notchView.onConfigurationChanged(configuration);
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        ElementTableActivity.isShow = true;
        this.setContentView(R$layout.component_activity_element_table);
        this.initView();
        this.intData();
        this.notchView = new NotchView((View)this.drawerlayout);
        (this.notchContext = new NotchContext((Activity)this)).checkNotchInScreen((NotchCallBack)new ElementTableActivity$1(this));
        final Configuration configuration = this.getResources().getConfiguration();
        if (configuration.orientation == 2) {
            this.setRequestedOrientation(11);
        }
        else if (configuration.orientation == 1) {
            this.setRequestedOrientation(1);
        }
    }
}
