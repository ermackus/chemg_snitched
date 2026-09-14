package com.kingagroot.kingdraw.ui;

import android.view.MenuItem;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.WindowManager$LayoutParams;
import android.content.res.Configuration;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import android.widget.RelativeLayout;
import com.kingagroot.kingdraw.data.GestureData;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import java.util.Collection;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import android.widget.ListAdapter;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.View;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.SpannableString;
import java.util.ArrayList;
import android.widget.TextView;
import android.view.Menu;
import android.widget.ImageView;
import com.kingagroot.kingdraw.adapter.GestureItemAdapter;
import android.widget.GridView;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.kingdraw.adapter.GestureGesItemAdapter;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.goodsrc.ui.library.ToolBarActivity;

public class GestureEditActivity extends ToolBarActivity
{
    private final GestureGroupModel deleteGestureModel;
    private GestureGesItemAdapter gestureAdapter;
    private GestureDbi gestureDbi;
    private final List<GestureGroupModel> gestureGroupModelsNoBind;
    private final List<GestureGroupModel> gestureModels;
    private GridView gvGesture;
    private GridView gvValue;
    private GestureItemAdapter itemAdapter;
    private ImageView ivGesture;
    private ImageView ivValue;
    private Menu menuFinish;
    private TextView tvGesture;
    private TextView tvValue;
    private final GestureGroupModel updateModel;
    
    public GestureEditActivity() {
        this.gestureModels = (List<GestureGroupModel>)new ArrayList();
        this.gestureGroupModelsNoBind = (List<GestureGroupModel>)new ArrayList();
        this.updateModel = new GestureGroupModel();
        this.deleteGestureModel = new GestureGroupModel();
    }
    
    private void checkMenu() {
        if (this.menuFinish != null) {
            final SpannableString title = new SpannableString(this.menuFinish.getItem(0).getTitle());
            final GestureGroupModel updateModel = this.updateModel;
            if (updateModel != null && !TextUtils.isEmpty((CharSequence)updateModel.getBondRes()) && !TextUtils.isEmpty((CharSequence)this.updateModel.getGestureRes())) {
                this.menuFinish.getItem(0).setEnabled(true);
                title.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#E13E3F")), 0, title.length(), 0);
            }
            else {
                this.menuFinish.getItem(0).setEnabled(false);
                title.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#FF666666")), 0, title.length(), 0);
            }
            this.menuFinish.getItem(0).setTitle((CharSequence)title);
        }
    }
    
    public static void fadeIn(final View view) {
        fadeIn(view, 0.0f, 1.0f, 400L);
        view.setEnabled(true);
    }
    
    public static void fadeIn(final View view, final float n, final float n2, final long duration) {
        if (view.getVisibility() == 0) {
            return;
        }
        view.setVisibility(0);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        ((Animation)alphaAnimation).setDuration(duration);
        view.startAnimation((Animation)alphaAnimation);
    }
    
    public static void fadeOut(final View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        view.setEnabled(false);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ((Animation)alphaAnimation).setDuration(400L);
        view.startAnimation((Animation)alphaAnimation);
        view.setVisibility(8);
    }
    
    private void initData() {
        final GestureItemAdapter gestureItemAdapter = new GestureItemAdapter((Context)this, (List)this.gestureModels);
        this.itemAdapter = gestureItemAdapter;
        this.gvValue.setAdapter((ListAdapter)gestureItemAdapter);
        final GestureGesItemAdapter gestureGesItemAdapter = new GestureGesItemAdapter((Context)this, (List)this.gestureGroupModelsNoBind);
        this.gestureAdapter = gestureGesItemAdapter;
        this.gvGesture.setAdapter((ListAdapter)gestureGesItemAdapter);
        this.gestureDbi = (GestureDbi)new GestureDbiMpl();
        final GestureGroupModel chooseData = (GestureGroupModel)this.getIntent().getSerializableExtra("Data");
        final List dataByKey = this.gestureDbi.getDataByKey(false);
        if (dataByKey != null) {
            this.gestureModels.addAll((Collection)dataByKey);
        }
        this.gestureGroupModelsNoBind.addAll((Collection)this.gestureDbi.getGestureNoBindData());
        if (chooseData != null) {
            this.gestureModels.add((Object)chooseData);
            this.gestureGroupModelsNoBind.add((Object)chooseData);
            this.itemAdapter.setSelection(chooseData);
            this.gestureAdapter.setSelection(chooseData);
            this.ivValue.setVisibility(0);
            this.tvValue.setVisibility(8);
            this.ivValue.setImageResource(GBitmapUtils.getResId((Context)this, chooseData.getBondRes()));
            this.ivGesture.setVisibility(0);
            this.tvGesture.setVisibility(8);
            this.ivGesture.setImageResource(GBitmapUtils.getResId((Context)this, chooseData.getGestureRes()));
            this.setChooseData(chooseData);
        }
        GestureData.listSortByBondId((List)this.gestureModels);
        GestureData.listSortByGestureId((List)this.gestureGroupModelsNoBind);
        this.itemAdapter.notifyDataSetChanged();
        this.gestureAdapter.notifyDataSetChanged();
    }
    
    private void initView() {
        final RelativeLayout relativeLayout = (RelativeLayout)this.findViewById(2131297326);
        this.ivValue = (ImageView)this.findViewById(2131296944);
        this.tvValue = (TextView)this.findViewById(2131297686);
        final RelativeLayout relativeLayout2 = (RelativeLayout)this.findViewById(2131297292);
        this.ivGesture = (ImageView)this.findViewById(2131296920);
        this.tvGesture = (TextView)this.findViewById(2131297605);
        this.gvValue = (GridView)this.findViewById(2131296777);
        this.gvGesture = (GridView)this.findViewById(2131296774);
        relativeLayout.setOnClickListener((View$OnClickListener)new _$$Lambda$GestureEditActivity$Q2R__pOWq7pzZq_bSN__wBNnp5Y(this));
        relativeLayout2.setOnClickListener((View$OnClickListener)new _$$Lambda$GestureEditActivity$91jcjf91coNqK3lTPuyr7gQpQds(this));
        this.gvValue.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$GestureEditActivity$87i_bHH68SXQ8ntXCLJYGALAvp0(this));
        this.gvGesture.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$GestureEditActivity$eD7dMTriGDw5aRfnzquFeU2VONE(this));
    }
    
    private void onOrientationChanged(final Configuration configuration) {
        final int orientation = configuration.orientation;
        if (orientation == 2) {
            this.gvValue.setNumColumns(6);
            this.gvGesture.setNumColumns(6);
        }
        else if (orientation == 1) {
            this.gvValue.setNumColumns(3);
            this.gvGesture.setNumColumns(3);
        }
    }
    
    private void setActivityBg(final float alpha) {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getWindow().setAttributes(attributes);
        this.getWindow().addFlags(2);
    }
    
    private void setChooseData(final GestureGroupModel gestureGroupModel) {
        this.updateModel.setBondId(gestureGroupModel.getBondId());
        this.updateModel.setBondRes(gestureGroupModel.getBondRes());
        this.updateModel.setGestureId(gestureGroupModel.getGestureId());
        this.updateModel.setGestureCoreId(gestureGroupModel.getGestureCoreId());
        this.updateModel.setGestureRes(gestureGroupModel.getGestureRes());
        this.updateModel.setToolName(gestureGroupModel.getToolName());
        this.updateModel.setParam(gestureGroupModel.getParam());
        this.deleteGestureModel.setBondId(gestureGroupModel.getBondId());
        this.deleteGestureModel.setBondRes(gestureGroupModel.getBondRes());
        this.deleteGestureModel.setGestureId(gestureGroupModel.getGestureId());
        this.deleteGestureModel.setGestureCoreId(gestureGroupModel.getGestureCoreId());
        this.deleteGestureModel.setGestureRes(gestureGroupModel.getGestureRes());
        this.deleteGestureModel.setToolName(gestureGroupModel.getToolName());
        this.deleteGestureModel.setParam(gestureGroupModel.getParam());
    }
    
    private void setData() {
        this.updateModel.setBind(true);
        this.deleteGestureModel.setBind(false);
        this.gestureDbi.upData(this.deleteGestureModel);
    }
    
    private void showDialog() {
        final GestureCreateDialog gestureCreateDialog = new GestureCreateDialog();
        gestureCreateDialog.setDataModel(this.updateModel);
        gestureCreateDialog.setOnGestureListener((GestureCreateDialog$OnGestureListener)new GestureEditActivity$1(this, gestureCreateDialog));
        gestureCreateDialog.show(this.getSupportFragmentManager(), (String)null);
        gestureCreateDialog.setCancelable(false);
        this.setActivityBg(0.5f);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.onOrientationChanged(configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492909);
        this.setTitle(2131820904);
        this.initView();
        this.initData();
        this.onOrientationChanged(this.getResources().getConfiguration());
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuItem add = menu.add(0, 0, 0, (CharSequence)this.getString(2131820902));
        final SpannableString title = new SpannableString(add.getTitle());
        title.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#E13E3F")), 0, title.length(), 0);
        add.setTitle((CharSequence)title);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.showDialog();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public boolean onPrepareOptionsMenu(final Menu menuFinish) {
        this.menuFinish = menuFinish;
        this.checkMenu();
        return super.onPrepareOptionsMenu(menuFinish);
    }
}
