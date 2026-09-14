package com.kingagroot.component.ui.widget.richinput;

import android.widget.LinearLayout$LayoutParams;
import android.widget.EditText;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.view.ViewGroup$LayoutParams;
import android.text.Spanned;
import com.kingagroot.component.ui.widget.richinput.html.HtmlParserResult;
import com.kingagroot.component.ui.widget.richinput.html.Html;
import android.widget.RadioGroup$OnCheckedChangeListener;
import android.os.Build$VERSION;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.widget.richinput.style.BaseStyle;
import com.kingagroot.component.ui.widget.richinput.style.GChemStyle;
import com.kingagroot.component.ui.widget.richinput.style.GFontFamilyStyle;
import com.kingagroot.component.ui.widget.richinput.style.GFontSizeStyle;
import com.kingagroot.component.ui.widget.richinput.style.GSubscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GSuperscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GItalicStyle;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.style.GBlodStyle;
import com.kingagroot.component.ui.widget.richinput.view.BaseRichEditor;
import com.kingagroot.component.ui.R$drawable;
import java.util.ArrayList;
import java.util.List;
import android.text.TextWatcher;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import com.kingagroot.component.ui.menu.SymbolItemView;
import com.kingagroot.component.ui.menu.menuitem.MenuBaseItem;
import android.content.Context;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.LinearLayout;
import com.kingagroot.component.ui.widget.richinput.view.GStyleButton;
import com.kingagroot.component.ui.widget.richinput.view.GBIStyleButton;
import android.widget.FrameLayout;
import com.kingagroot.component.ui.widget.richinput.view.RichEditor;
import android.view.View;
import android.widget.CheckBox;

public class LandscapeInputMenu extends InputBaseMenu
{
    private static final int MENU_HEIGHT_MIN;
    private CheckBox cbA;
    private CheckBox cbSymbol;
    View contentView;
    private RichEditor etRich;
    private FrameLayout flMenuContainer;
    private FrameLayout flRich;
    GStyleManager gStyleManager;
    private GAlignEnum gravityType;
    private GBIStyleButton imgbtnBlod;
    private GStyleButton imgbtnChem;
    private GBIStyleButton imgbtnItalic;
    private GStyleButton imgbtnSub;
    private GStyleButton imgbtnSup;
    private LinearLayout llFont;
    private FontExtendMenu menuFont;
    private ViewTreeObserver$OnGlobalLayoutListener onGlobalLayoutListener;
    private InputMenuContainer$OnInputListner onInputListner;
    private CompoundButton$OnCheckedChangeListener onSymbolCheckedChangeListener;
    private OnMenuClickListener onSymbolMenuClickListener;
    private RadioButton rbtAlignCenter;
    private RadioButton rbtAlignLeft;
    private RadioButton rbtAlignRight;
    private RadioGroup rgpAlign;
    private MoreMenuPop symbolMenuPop;
    private TextView tvCancel;
    private TextView tvConfirm;
    private TextView tvCount;
    private ViewTreeObserver viewTreeObserver;
    
    static {
        MENU_HEIGHT_MIN = GDensityUtil.dp2px(180.0f);
    }
    
    public LandscapeInputMenu(final Context context) {
        super(context);
        this.gravityType = GAlignEnum.left;
        this.onSymbolMenuClickListener = new OnMenuClickListener() {
            final LandscapeInputMenu this$0;
            
            public void onMenuItemClick(final MenuBaseItem menuBaseItem) {
                super.onMenuItemClick(menuBaseItem);
                this.this$0.etRich.paste(((SymbolItemView)menuBaseItem).getSymbol());
            }
        };
        this.onSymbolCheckedChangeListener = (CompoundButton$OnCheckedChangeListener)new LandscapeInputMenu$2(this);
        this.onGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new LandscapeInputMenu$3(this);
    }
    
    public LandscapeInputMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.gravityType = GAlignEnum.left;
        this.onSymbolMenuClickListener = new OnMenuClickListener() {
            final LandscapeInputMenu this$0;
            
            public void onMenuItemClick(final MenuBaseItem menuBaseItem) {
                super.onMenuItemClick(menuBaseItem);
                this.this$0.etRich.paste(((SymbolItemView)menuBaseItem).getSymbol());
            }
        };
        this.onSymbolCheckedChangeListener = (CompoundButton$OnCheckedChangeListener)new LandscapeInputMenu$2(this);
        this.onGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new LandscapeInputMenu$3(this);
        this.contentView = View.inflate(context, R$layout.component_rich_input_menu_container_landscape, (ViewGroup)this);
        this.initView();
        this.initData();
        this.etRich.setImeOptions(268435456);
        this.cbA.setOnClickListener((View$OnClickListener)new LandscapeInputMenu$4(this));
        this.etRich.addTextChangedListener((TextWatcher)new LandscapeInputMenu$5(this));
    }
    
    private void addKeyBoardHeightLisener() {
        final ViewTreeObserver viewTreeObserver = this.contentView.getViewTreeObserver();
        this.viewTreeObserver = viewTreeObserver;
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
    }
    
    private List<MenuBaseItem> getSymbolItems(final MoreMenuPop moreMenuPop) {
        final ArrayList list = new ArrayList();
        final SymbolItemView symbolItemView = new SymbolItemView(this.context, "\u2191", R$drawable.ic_symbol_arrow_up);
        ((MenuBaseItem)symbolItemView).setOnMenuClickListener(this.onSymbolMenuClickListener);
        ((MenuBaseItem)symbolItemView).contactPopupWindow(moreMenuPop);
        ((List)list).add((Object)symbolItemView);
        final SymbolItemView symbolItemView2 = new SymbolItemView(this.context, "\u2193", R$drawable.ic_symbol_arrow_down);
        ((MenuBaseItem)symbolItemView2).setOnMenuClickListener(this.onSymbolMenuClickListener);
        ((MenuBaseItem)symbolItemView2).contactPopupWindow(moreMenuPop);
        ((List)list).add((Object)symbolItemView2);
        final SymbolItemView symbolItemView3 = new SymbolItemView(this.context, "\u25b3", R$drawable.ic_symbol_heating);
        ((MenuBaseItem)symbolItemView3).setOnMenuClickListener(this.onSymbolMenuClickListener);
        ((MenuBaseItem)symbolItemView3).contactPopupWindow(moreMenuPop);
        ((List)list).add((Object)symbolItemView3);
        final SymbolItemView symbolItemView4 = new SymbolItemView(this.context, "\u2103", R$drawable.ic_symbol_temp);
        ((MenuBaseItem)symbolItemView4).setOnMenuClickListener(this.onSymbolMenuClickListener);
        ((MenuBaseItem)symbolItemView4).contactPopupWindow(moreMenuPop);
        ((List)list).add((Object)symbolItemView4);
        return (List<MenuBaseItem>)list;
    }
    
    private void initData() {
        this.imgbtnBlod.setCheckResId(R$drawable.ic_rich_bold_select_selector);
        this.imgbtnBlod.setUnChecnkResId(R$drawable.ic_rich_bold_nor_selector);
        this.imgbtnItalic.setCheckResId(R$drawable.ic_rich_ita_sel_selector);
        this.imgbtnItalic.setUnChecnkResId(R$drawable.ic_rich_ita_nor_selector);
        this.imgbtnSub.setCheckResId(R$drawable.ic_rich_sub_sel_selector);
        this.imgbtnSub.setUnChecnkResId(R$drawable.ic_rich_sub_nor_selector);
        this.imgbtnSup.setCheckResId(R$drawable.ic_rich_sup_sel_selector);
        this.imgbtnSup.setUnChecnkResId(R$drawable.ic_rich_sup_nor_selector);
        this.imgbtnChem.setCheckResId(R$drawable.ic_rich_ch_num_sel_selector);
        this.imgbtnChem.setUnChecnkResId(R$drawable.ic_rich_ch_num_nor_selector);
        this.etRich.setRichEnable(true);
        this.menuFont.getFontFamilyView().setBaseRichEditor((BaseRichEditor)this.etRich);
        this.menuFont.getFontSizeView().setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnBlod.setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnItalic.setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnSub.setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnSup.setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnChem.setBaseRichEditor((BaseRichEditor)this.etRich);
        this.imgbtnBlod.setCheck(false);
        this.imgbtnItalic.setCheck(false);
        this.imgbtnSub.setCheck(false);
        this.imgbtnSup.setCheck(false);
        this.imgbtnChem.setCheck(false);
        this.gStyleManager = (GStyleManager)this.etRich.getStyleManager();
        final GBlodStyle gBlodStyle = new GBlodStyle((BaseStyleView)this.imgbtnBlod);
        final GItalicStyle gItalicStyle = new GItalicStyle((BaseStyleView)this.imgbtnItalic);
        final GSuperscriptStyle gSuperscriptStyle = new GSuperscriptStyle((BaseStyleView)this.imgbtnSup);
        final GSubscriptStyle gSubscriptStyle = new GSubscriptStyle((BaseStyleView)this.imgbtnSub);
        final GFontSizeStyle gFontSizeStyle = new GFontSizeStyle((BaseStyleView)this.menuFont.getFontSizeView());
        final GFontFamilyStyle gFontFamilyStyle = new GFontFamilyStyle((BaseStyleView)this.menuFont.getFontFamilyView());
        final GChemStyle gChemStyle = new GChemStyle((BaseStyleView)this.imgbtnChem);
        final StyleBaseManager styleManager = this.etRich.getStyleManager();
        if (styleManager instanceof GStyleManager) {
            final GStyleManager gStyleManager = (GStyleManager)styleManager;
            gStyleManager.setgFontFamilyStyle(gFontFamilyStyle);
            gStyleManager.setgFontSizeStyle(gFontSizeStyle);
        }
        styleManager.addSpan((BaseStyle)gBlodStyle);
        styleManager.addSpan((BaseStyle)gItalicStyle);
        styleManager.addSpan((BaseStyle)gSuperscriptStyle);
        styleManager.addSpan((BaseStyle)gSubscriptStyle);
        styleManager.addSpan((BaseStyle)gChemStyle);
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)this.imgbtnChem);
        this.imgbtnSub.setToggleViews((List)list);
        this.imgbtnSub.addTogglevView((BaseStyleView)this.imgbtnSup);
        this.imgbtnSup.setToggleViews((List)list);
        this.imgbtnSup.addTogglevView((BaseStyleView)this.imgbtnSub);
        final ArrayList toggleViews = new ArrayList();
        ((List)toggleViews).add((Object)this.imgbtnSub);
        ((List)toggleViews).add((Object)this.imgbtnSup);
        this.imgbtnChem.setToggleViews((List)toggleViews);
        this.tvConfirm.setOnClickListener((View$OnClickListener)new LandscapeInputMenu$6(this));
        this.tvCancel.setOnClickListener((View$OnClickListener)new LandscapeInputMenu$7(this));
        this.setAlignClick();
        this.menuFont.setRichEditor(this.etRich);
    }
    
    private void initView() {
        this.etRich = (RichEditor)this.findViewById(R$id.et_rich);
        this.cbA = (CheckBox)this.findViewById(R$id.cb_a);
        this.imgbtnBlod = (GBIStyleButton)this.findViewById(R$id.imgbtn_blod);
        this.imgbtnItalic = (GBIStyleButton)this.findViewById(R$id.imgbtn_italic);
        this.imgbtnSub = (GStyleButton)this.findViewById(R$id.imgbtn_sub);
        this.imgbtnSup = (GStyleButton)this.findViewById(R$id.imgbtn_sup);
        this.imgbtnChem = (GStyleButton)this.findViewById(R$id.imgbtn_chem);
        this.rgpAlign = (RadioGroup)this.findViewById(R$id.rgp_align);
        this.rbtAlignLeft = (RadioButton)this.findViewById(R$id.rbt_align_left);
        this.rbtAlignCenter = (RadioButton)this.findViewById(R$id.rbt_align_center);
        this.rbtAlignRight = (RadioButton)this.findViewById(R$id.rbt_align_right);
        this.flMenuContainer = (FrameLayout)this.findViewById(R$id.fl_menu_container);
        this.menuFont = (FontExtendMenu)this.findViewById(R$id.menu_font);
        this.tvConfirm = (TextView)this.findViewById(R$id.tv_confirm);
        this.tvCancel = (TextView)this.findViewById(R$id.tv_cancel);
        this.llFont = (LinearLayout)this.findViewById(R$id.ll_font);
        this.tvCount = (TextView)this.findViewById(R$id.tv_count);
        this.flRich = (FrameLayout)this.findViewById(R$id.fl_rich);
        (this.cbSymbol = (CheckBox)this.findViewById(R$id.cb_symbol)).setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.cbSymbol.setOnCheckedChangeListener(this.onSymbolCheckedChangeListener);
    }
    
    private boolean isShowKeyBoard(final int n) {
        return this.flMenuContainer.getLayoutParams().height == n;
    }
    
    private void removeKeyBoardHeightLisener() {
        final ViewTreeObserver viewTreeObserver = this.viewTreeObserver;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            if (Build$VERSION.SDK_INT >= 16) {
                this.viewTreeObserver.removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
            }
            else {
                this.viewTreeObserver.removeGlobalOnLayoutListener(this.onGlobalLayoutListener);
            }
        }
    }
    
    private void reset() {
        this.imgbtnBlod.setCheck(false);
        this.imgbtnItalic.setCheck(false);
        this.imgbtnSub.setCheck(false);
        this.imgbtnSup.setCheck(false);
        this.imgbtnChem.setCheck(false);
        this.menuFont.getFontFamilyView().setCheck(false);
        this.menuFont.getFontSizeView().setCheck(false);
    }
    
    private void setAlignClick() {
        this.rgpAlign.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)new LandscapeInputMenu$8(this));
    }
    
    private void setContent(final String s) {
        final HtmlParserResult fromHtml = Html.fromHtml(s);
        final Spanned spanned = fromHtml.getSpanned();
        this.gravityType = fromHtml.getgAlignEnum();
        this.setRgpAlignType();
        this.setEtRichContent((CharSequence)spanned);
    }
    
    private void setEtRichContent(final CharSequence text) {
        this.etRich.removeTextChangedListener();
        this.etRich.setText(text);
        this.etRich.setSelection(this.etRich.getText().length());
        this.etRich.addTextChangedListener();
    }
    
    private void setExtendMenuHeight(final int height) {
        if (height > LandscapeInputMenu.MENU_HEIGHT_MIN) {
            final ViewGroup$LayoutParams layoutParams = this.flMenuContainer.getLayoutParams();
            if (layoutParams.height != height) {
                layoutParams.height = height;
                this.flMenuContainer.setLayoutParams(layoutParams);
                final ViewTreeObserver viewTreeObserver = this.etRich.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new LandscapeInputMenu$10(this, viewTreeObserver));
                }
            }
        }
    }
    
    private void setRbtEnable(final boolean b) {
        if (b) {
            this.rbtAlignLeft.setEnabled(false);
            this.rbtAlignRight.setEnabled(false);
            this.rbtAlignCenter.setEnabled(false);
        }
        else {
            this.rbtAlignLeft.setEnabled(true);
            this.rbtAlignRight.setEnabled(true);
            this.rbtAlignCenter.setEnabled(true);
        }
    }
    
    private void setRgpAlignType() {
        if (this.gravityType == GAlignEnum.left) {
            this.rbtAlignLeft.setChecked(true);
        }
        else if (this.gravityType == GAlignEnum.center) {
            this.rbtAlignCenter.setChecked(true);
        }
        else if (this.gravityType == GAlignEnum.right) {
            this.rbtAlignRight.setChecked(true);
        }
    }
    
    private void setRichEnable(final boolean rbtEnable, final FormatValue formatValue) {
        if (formatValue != null) {
            int n;
            int size;
            String family;
            if (rbtEnable) {
                n = formatValue.atomFontStyle;
                final String atomFontName = formatValue.atomFontName;
                size = formatValue.atomFontSize;
                family = atomFontName;
            }
            else {
                n = formatValue.textFontStyle;
                final String textFontName = formatValue.textFontName;
                size = formatValue.textFontSize;
                family = textFontName;
            }
            this.setRbtEnable(rbtEnable);
            if (n == GFontStyleEnum.Bold.getCode()) {
                this.imgbtnBlod.setCheck(true);
            }
            else if (n == GFontStyleEnum.Italic.getCode()) {
                this.imgbtnItalic.setCheck(true);
            }
            else if (n == GFontStyleEnum.BoldItalic.getCode()) {
                this.imgbtnBlod.setCheck(true);
                this.imgbtnItalic.setCheck(true);
            }
            this.menuFont.getFontFamilyView().setFamily(family);
            this.menuFont.getFontSizeView().setSize(size);
            if (rbtEnable) {
                this.gStyleManager.setFontStyle(family, size, n, true);
            }
            else {
                this.gStyleManager.setFontStyle(family, size, n, false);
            }
        }
    }
    
    public boolean keyBoardEvent(final MotionEvent motionEvent) {
        if (this.getVisibility() == 0) {
            this.etRich.keyBoardEvent(motionEvent);
            return true;
        }
        return false;
    }
    
    public void onPause() {
        if (this.getVisibility() == 0) {
            this.removeKeyBoardHeightLisener();
        }
    }
    
    public void onResume() {
        this.getVisibility();
    }
    
    public void setContent(final boolean b, final String content, final FormatValue formatValue) {
        this.etRich.setSingleLine(false);
        this.setRichEnable(b, formatValue);
        if (b) {
            if (TextUtils.isEmpty((CharSequence)content)) {
                this.imgbtnChem.setCheck(true);
            }
            this.etRich.setSingleLine(true);
        }
        else {
            this.etRich.setSingleLine(false);
        }
        this.setContent(content);
    }
    
    public void setFontIsShow(final boolean b) {
        if (b) {
            this.flMenuContainer.setVisibility(4);
            this.llFont.setVisibility(8);
            this.etRich.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(50) });
            this.etRich.setMaxLength(50);
            this.etRich.addTextChangedListener((TextWatcher)new LandscapeInputMenu$9(this));
            this.tvCount.setVisibility(0);
        }
        else {
            this.etRich.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(Integer.MAX_VALUE) });
            this.etRich.setMaxLength(Integer.MAX_VALUE);
            this.flMenuContainer.setVisibility(0);
            this.llFont.setVisibility(0);
            this.tvCount.setVisibility(8);
        }
    }
    
    public void setOnInputListner(final InputMenuContainer$OnInputListner onInputListner) {
        this.onInputListner = onInputListner;
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        if (visibility == 0) {
            this.addKeyBoardHeightLisener();
            this.etRich.setFocusableInTouchMode(true);
            this.etRich.requestFocus();
            this.showKeyBoard((EditText)this.etRich);
            final LinearLayout$LayoutParams layoutParams = (LinearLayout$LayoutParams)this.flRich.getLayoutParams();
            layoutParams.height = 0;
            layoutParams.weight = 1.0f;
            this.flRich.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        else {
            this.etRich.SelectMenuDismiss();
            this.removeKeyBoardHeightLisener();
            this.reset();
        }
    }
}
