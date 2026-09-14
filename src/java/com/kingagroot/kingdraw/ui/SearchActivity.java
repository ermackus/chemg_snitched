package com.kingagroot.kingdraw.ui;

import java.util.Iterator;
import com.kingagroot.kingdraw.model.GTagModel;
import java.util.Collection;
import android.widget.SearchView$OnQueryTextListener;
import android.os.Bundle;
import android.content.res.Configuration;
import java.io.Serializable;
import android.content.Intent;
import android.widget.CompoundButton;
import android.content.DialogInterface$OnDismissListener;
import com.kingagroot.kingdraw.widget.FileTag.OnTagFilterViewListener;
import android.content.Context;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.utils.ImageLoader;
import com.goodsrc.library.utils.StringUtils;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import java.util.List;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.kingagroot.kingdraw.widget.FileTag.OnTagClickListener;
import com.kingagroot.kingdraw.widget.FileTag.OnAddTagClickListener;
import android.widget.TextView;
import com.kingagroot.kingdraw.widget.FileTag.FileTagType;
import com.kingagroot.kingdraw.widget.FileTag.GTagSelectorDialog;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import com.kingagroot.kingdraw.widget.GSearchView;
import java.util.ArrayList;
import com.kingagroot.kingdraw.widget.FileTag.LocalScrollView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.ImageButton;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import com.kingagroot.kingdraw.widget.FileTag.FileTagGroup;
import android.widget.CheckBox;
import android.widget.Button;
import com.kingagroot.kingdraw.widget.FileTag.GBaseTagFilterView;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class SearchActivity extends BaseActivity implements View$OnClickListener, GBaseTagFilterView
{
    Button btn_draw;
    CheckBox check_search;
    private FileTagGroup fileTags;
    GFileSearchModel gSearchModel;
    private ImageButton imbtn_back;
    ImageButton imbtn_ben;
    ImageButton imbtn_clean_frags;
    ImageView img_clean;
    ImageView img_editor;
    String key;
    LinearLayout ll_result_data;
    LinearLayout ll_search;
    LinearLayout ll_shadow;
    private LocalSearchFragment localSearchFragment;
    RelativeLayout rl_draw_data;
    private LocalScrollView scrollView;
    private ArrayList<String> searchTags;
    private GSearchView searchView;
    private final ArrayList<String> selectedTags;
    String smiles;
    private TagModelDBI tagModelDBI;
    private GTagSelectorDialog tagSelectorDialog;
    private final FileTagType tagType;
    TextView tv_draw_name;
    TextView tv_draw_smiles;
    TextView tv_formula_name;
    TextView tv_formula_smiles;
    private TextView tv_frags;
    TextView tv_frags_num;
    
    public SearchActivity() {
        this.tagType = FileTagType.delete;
        this.selectedTags = (ArrayList<String>)new ArrayList();
        this.searchTags = (ArrayList<String>)new ArrayList();
    }
    
    private void initView() {
        this.searchView = (GSearchView)this.findViewById(2131297361);
        this.imbtn_back = (ImageButton)this.findViewById(2131296862);
        this.imbtn_ben = (ImageButton)this.findViewById(2131296863);
        this.btn_draw = (Button)this.findViewById(2131296425);
        this.rl_draw_data = (RelativeLayout)this.findViewById(2131297290);
        this.img_editor = (ImageView)this.findViewById(2131296875);
        this.img_clean = (ImageView)this.findViewById(2131296873);
        this.tv_draw_name = (TextView)this.findViewById(2131297565);
        this.tv_draw_smiles = (TextView)this.findViewById(2131297566);
        this.tv_formula_name = (TextView)this.findViewById(2131297600);
        this.tv_formula_smiles = (TextView)this.findViewById(2131297601);
        this.tv_frags_num = (TextView)this.findViewById(2131297603);
        this.ll_result_data = (LinearLayout)this.findViewById(2131297024);
        this.ll_search = (LinearLayout)this.findViewById(2131297026);
        this.check_search = (CheckBox)this.findViewById(2131296513);
        this.ll_shadow = (LinearLayout)this.findViewById(2131297030);
        this.tv_frags = (TextView)this.findViewById(2131297602);
        this.fileTags = (FileTagGroup)this.findViewById(2131296670);
        this.imbtn_clean_frags = (ImageButton)this.findViewById(2131296866);
        this.fileTags.setAddTagViewRes(2131230939, 70, 28, (String)null);
        this.fileTags.appendAddTag((OnAddTagClickListener)new _$$Lambda$SearchActivity$GF99TVVKM4C92e8aCHA8oKygFFo(this));
        this.fileTags.setOnTagClickListener((OnTagClickListener)new _$$Lambda$SearchActivity$oy2g4FyyRm8LzlcVCK_G0AmQe4A(this));
        this.tagModelDBI = (TagModelDBI)new TagModelDBImpl();
        this.imbtn_ben.setOnClickListener((View$OnClickListener)this);
        this.btn_draw.setOnClickListener((View$OnClickListener)this);
        this.img_clean.setOnClickListener((View$OnClickListener)this);
        this.rl_draw_data.setOnClickListener((View$OnClickListener)this);
        this.imbtn_clean_frags.setOnClickListener((View$OnClickListener)this);
        this.check_search.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new _$$Lambda$SearchActivity$1gVtNj4Dn22vwI49sgtwhIOvr6U(this));
        this.scrollView = (LocalScrollView)this.findViewById(2131297348);
        this.searchView.setQueryHint(this.getString(2131821347));
    }
    
    private void onSearch(final String searchKey) {
        this.setSearchKey(searchKey);
        final InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 2);
        inputMethodManager.showSoftInput((View)this.searchView, 2);
    }
    
    private void onSearchRefreshData() {
        this.localSearchFragment.onSearchKey(this.key, (List<String>)this.searchTags, this.smiles);
        if (TextUtils.isEmpty((CharSequence)this.key) && TextUtils.isEmpty((CharSequence)this.smiles)) {
            final ArrayList<String> searchTags = this.searchTags;
            if (searchTags == null || searchTags.size() <= 0) {
                this.ll_shadow.setVisibility(8);
            }
        }
    }
    
    private void setDefaultFragment() {
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(2131296754, (Fragment)(this.localSearchFragment = new LocalSearchFragment()));
        beginTransaction.commit();
    }
    
    private void setSearchSturct() {
        this.btn_draw.setVisibility(8);
        this.rl_draw_data.setVisibility(0);
        this.setText(this.gSearchModel);
        this.ll_shadow.setVisibility(0);
    }
    
    private void setSelectedTagNum() {
        final int n = this.fileTags.getChildCount() - 1;
        if (n > 0) {
            this.tv_frags.setText((CharSequence)StringUtils.format(this.getString(2131820889), new Object[] { n }));
        }
        else {
            this.tv_frags.setText((CharSequence)this.getString(2131820888));
        }
    }
    
    private void setShouText() {
        this.tv_formula_name.setText((CharSequence)this.tv_draw_name.getText().toString());
        this.tv_formula_smiles.setText((CharSequence)this.tv_draw_smiles.getText().toString());
        this.tv_frags_num.setText((CharSequence)this.tv_frags.getText().toString());
    }
    
    private void setText(final GFileSearchModel gFileSearchModel) {
        if (gFileSearchModel != null) {
            ImageLoader.bind(this.img_editor, gFileSearchModel.getPicPath());
            this.tv_draw_name.setText((CharSequence)gFileSearchModel.getFormola());
            this.tv_draw_smiles.setText((CharSequence)gFileSearchModel.getSmiles());
        }
    }
    
    public List<String> getTags() {
        return (List<String>)this.selectedTags;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1 && intent != null) {
            final GFileSearchModel gSearchModel = (GFileSearchModel)intent.getSerializableExtra("reslult_key_GSearchModel");
            this.gSearchModel = gSearchModel;
            if (!TextUtils.isEmpty((CharSequence)gSearchModel.getSmiles()) && this.gSearchModel.getSmiles().equals((Object)"error")) {
                this.btn_draw.setVisibility(0);
                this.rl_draw_data.setVisibility(8);
            }
            else {
                this.setSearchSturct();
                this.setSearchSmiles(this.gSearchModel.getSmiles());
            }
        }
        else if (this.gSearchModel == null) {
            this.btn_draw.setVisibility(0);
            this.rl_draw_data.setVisibility(8);
            this.setSearchSmiles("");
        }
    }
    
    public void onClick(final View view) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        if (view != this.imbtn_ben) {
            final Button btn_draw = this.btn_draw;
            if (view != btn_draw) {
                if (view == this.rl_draw_data) {
                    final Intent intent = new Intent((Context)this, (Class)NewSearchPaletteActivity.class);
                    final GFileSearchModel gSearchModel = this.gSearchModel;
                    if (gSearchModel != null) {
                        intent.putExtra("intent_key_gfilesearchmodel", (Serializable)gSearchModel);
                    }
                    this.startActivityForResult(intent, 1);
                    return;
                }
                if (view == this.img_clean) {
                    btn_draw.setVisibility(0);
                    this.rl_draw_data.setVisibility(8);
                    this.gSearchModel = null;
                    this.setSearchSmiles("");
                    return;
                }
                if (view == this.imbtn_clean_frags) {
                    this.setTags((List<String>)new ArrayList());
                    this.fileTags.clearTags();
                    this.setSelectedTagNum();
                }
                return;
            }
        }
        this.startActivityForResult(new Intent((Context)this, (Class)NewSearchPaletteActivity.class), 1);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final int orientation = configuration.orientation;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492929);
        this.initView();
        this.imbtn_back.setOnClickListener((View$OnClickListener)new _$$Lambda$SearchActivity$St_ICsc5ijfm67q9tr7FcgtKTPo(this));
        this.searchView.onSearch(true);
        this.searchView.setOnQueryTextListener((SearchView$OnQueryTextListener)new SearchActivity$1(this));
        this.setDefaultFragment();
    }
    
    protected void onRestoreInstanceState(final Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.gSearchModel = (GFileSearchModel)bundle.getSerializable("gsearchModel");
        this.smiles = bundle.getString("smiles", "");
        this.key = bundle.getString("key", "");
        final ArrayList list = (ArrayList)bundle.getSerializable("tags");
        if (list != null) {
            this.searchTags.clear();
            this.searchTags.addAll((Collection)list);
        }
        this.searchView.setQuery(this.key, false);
        this.fileTags.appendTags((List)this.searchTags, FileTagType.delete);
        this.setSearchSturct();
        this.onSearchRefreshData();
    }
    
    protected void onResume() {
        super.onResume();
        final GTagSelectorDialog tagSelectorDialog = this.tagSelectorDialog;
        if (tagSelectorDialog != null && tagSelectorDialog.isShowing()) {
            this.tagSelectorDialog.initAllTags();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("gsearchModel", (Serializable)this.gSearchModel);
        bundle.putString("smiles", this.smiles);
        bundle.putString("key", this.key);
        bundle.putSerializable("tags", (Serializable)this.searchTags);
    }
    
    public void refreshData() {
        this.selectedTags.clear();
        final List selectTagModels = this.tagModelDBI.getSelectTagModels();
        if (selectTagModels != null) {
            for (final GTagModel gTagModel : selectTagModels) {
                this.fileTags.appendTag((CharSequence)gTagModel.getName(), FileTagType.delete);
                this.selectedTags.add((Object)gTagModel.getName());
            }
        }
        this.scrollView.post((Runnable)new _$$Lambda$SearchActivity$2LryqYPaOJoXoUlVzE601efppOU(this));
    }
    
    public void removeTag(final String s) {
        this.fileTags.removeTag(s);
        this.selectedTags.remove((Object)s);
        this.tagModelDBI.removeTag(s);
        this.setSearchTags(this.selectedTags);
    }
    
    public void setSearchKey(final String key) {
        this.key = key;
        this.onSearchRefreshData();
    }
    
    public void setSearchSmiles(final String smiles) {
        this.smiles = smiles;
        this.onSearchRefreshData();
    }
    
    public void setSearchTags(final ArrayList<String> searchTags) {
        this.searchTags = searchTags;
        this.onSearchRefreshData();
    }
    
    public void setTags(final List<String> list) {
        this.fileTags.clearTags();
        this.selectedTags.clear();
        this.selectedTags.addAll((Collection)list);
        this.tagModelDBI.addSelectTagModels((List)list);
        this.fileTags.appendTags((List)list, this.tagType);
        this.setSelectedTagNum();
        this.ll_shadow.setVisibility(0);
        this.setSearchTags(this.selectedTags);
        if (this.selectedTags.size() > 0) {
            this.imbtn_clean_frags.setVisibility(0);
        }
        else {
            this.imbtn_clean_frags.setVisibility(8);
        }
        this.scrollView.post((Runnable)new _$$Lambda$SearchActivity$bKZKS43h9yei7dRok8j7rM8HgzI(this));
    }
}
