package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.view.GCompoundButton;
import com.kingagroot.component.ui.widget.EditPop;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.util.AttributeSet;
import com.kingagroot.component.ui.menu.menuitem.UndoItemView;
import android.widget.TextView;
import com.kingagroot.component.ui.menu.menuitem.RedoItemView;
import com.kingagroot.component.ui.PaletteTopInterface;
import com.kingagroot.component.ui.view.GImageButton;
import android.widget.ImageButton;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.LinearLayout;

public class VerticalTopToolView extends LinearLayout implements View$OnClickListener
{
    private boolean SaveAsEnable;
    private Button btnSearchCancel;
    private Button btnSearchDone;
    private Button btnSupSave;
    private final View contentView;
    private final Context context;
    private ImageButton ibtClose;
    private GImageButton ibtMore;
    private LinearLayout llBase;
    private LinearLayout llSearch;
    private String nameHtml;
    private PaletteTopInterface onTopToolClickListener;
    private int paletteType;
    private RedoItemView redoItemView;
    private TextView tvFilename;
    private UndoItemView undoItemView;
    
    public VerticalTopToolView(final Context context, final AttributeSet set) {
        super(context, set);
        this.SaveAsEnable = true;
        this.context = context;
        this.contentView = View.inflate(context, R.layout.view_vertical_top, (ViewGroup)this);
        this.initView();
    }
    
    private EditPop.Build createEditPop() {
        final EditPop.Build build = new EditPop.Build(this.getContext());
        build.setHint(this.getContext().getString(R.string.palette_input_filename)).setMaxLines(1);
        return build;
    }
    
    private void initView() {
        this.ibtClose = (ImageButton)this.contentView.findViewById(R.id.ibt_close);
        this.tvFilename = (TextView)this.contentView.findViewById(R.id.tv_filename);
        this.undoItemView = (UndoItemView)this.contentView.findViewById(R.id.undo_item_view);
        this.redoItemView = (RedoItemView)this.contentView.findViewById(R.id.redo_item_view);
        this.ibtMore = (GImageButton)this.contentView.findViewById(R.id.ibt_more);
        this.llBase = (LinearLayout)this.contentView.findViewById(R.id.ll_base);
        this.llSearch = (LinearLayout)this.contentView.findViewById(R.id.ll_search);
        this.btnSearchDone = (Button)this.contentView.findViewById(R.id.btn_search_done);
        this.btnSearchCancel = (Button)this.contentView.findViewById(R.id.btn_search_cancel);
        this.btnSupSave = (Button)this.contentView.findViewById(R.id.btn_sup_save);
        this.tvFilename.setOnClickListener((View$OnClickListener)this);
        this.ibtMore.setOnClickListener((View$OnClickListener)this);
        this.ibtClose.setOnClickListener((View$OnClickListener)this);
        this.btnSearchDone.setOnClickListener((View$OnClickListener)this);
        this.btnSearchCancel.setOnClickListener((View$OnClickListener)this);
        this.btnSupSave.setOnClickListener((View$OnClickListener)this);
        this.initEnable(false);
    }
    
    private void setViewEnable(final GCompoundButton gCompoundButton, final boolean clickEnable) {
        gCompoundButton.setClickEnable(clickEnable);
        if (clickEnable) {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R.color.elementColor));
        }
        else {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R.color.line));
        }
    }
    
    public void contextKingDrawView(final KingDrawView kingDrawView) {
        this.undoItemView.setKingDrawView(kingDrawView);
        this.redoItemView.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new VerticalTopToolView$1(this));
    }
    
    public void enableSaveAs(final boolean saveAsEnable) {
        this.SaveAsEnable = saveAsEnable;
    }
    
    public String getFileName() {
        return this.tvFilename.getText().toString();
    }
    
    public String getSupName() {
        return this.nameHtml;
    }
    
    public void initEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.undoItemView, b);
        this.setViewEnable((GCompoundButton)this.redoItemView, b);
    }
    
    public void inputFileName() {
        if (this.paletteType == ToolDataManage.TYPE_SUP) {
            final PaletteTopInterface onTopToolClickListener = this.onTopToolClickListener;
            if (onTopToolClickListener != null) {
                onTopToolClickListener.onInputSupName(this.nameHtml);
            }
        }
        else {
            final EditPop.Build editPop = this.createEditPop();
            editPop.setContent(this.tvFilename.getText().toString().trim());
            editPop.setMaxLength(50);
            editPop.setOnEditPopListener((EditPop.OnEditPopListener)new VerticalTopToolView$2(this));
            editPop.create().show((View)this);
        }
    }
    
    public void onClick(final View view) {
        if (CheckDoubleClick.isFastDoubleClick(view)) {
            return;
        }
        if (view == this.ibtMore) {
            final TopMorePop topMorePop = new TopMorePop(this.context);
            topMorePop.enableSaveAs(this.SaveAsEnable);
            topMorePop.showAsDropDown((View)this.ibtMore);
            topMorePop.setOnTopMenuClickListener((TopMorePop.OnTopMoreMenuClickListener)new VerticalTopToolView$3(this));
        }
        else if (view == this.tvFilename) {
            this.inputFileName();
        }
        else if (view == this.ibtClose) {
            final PaletteTopInterface onTopToolClickListener = this.onTopToolClickListener;
            if (onTopToolClickListener != null) {
                onTopToolClickListener.onClose();
            }
        }
        else if (view == this.btnSearchDone) {
            final PaletteTopInterface onTopToolClickListener2 = this.onTopToolClickListener;
            if (onTopToolClickListener2 != null) {
                onTopToolClickListener2.onSearchDone();
            }
        }
        else if (view == this.btnSearchCancel) {
            final PaletteTopInterface onTopToolClickListener3 = this.onTopToolClickListener;
            if (onTopToolClickListener3 != null) {
                onTopToolClickListener3.onClose();
            }
        }
        else if (view == this.btnSupSave) {
            final PaletteTopInterface onTopToolClickListener4 = this.onTopToolClickListener;
            if (onTopToolClickListener4 != null) {
                onTopToolClickListener4.onSupSave();
            }
        }
    }
    
    public void setFileName(final String text) {
        this.tvFilename.setText((CharSequence)text);
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.undoItemView.setOnToolHintListener(onToolHintListener);
        this.redoItemView.setOnToolHintListener(onToolHintListener);
    }
    
    public void setOnTopToolClick(final PaletteTopInterface onTopToolClickListener) {
        this.onTopToolClickListener = onTopToolClickListener;
    }
    
    public void setRedoEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.redoItemView, b);
    }
    
    public void setSupName(final String nameHtml) {
        this.nameHtml = nameHtml;
        this.tvFilename.setText((CharSequence)SpanUtil.htmlToSpan(nameHtml));
    }
    
    public void setTopViewType(final int paletteType) {
        this.paletteType = paletteType;
        if (paletteType == ToolDataManage.TYPE_BASE) {
            this.llBase.setVisibility(0);
            this.llSearch.setVisibility(8);
            this.btnSupSave.setVisibility(8);
            this.ibtMore.setVisibility(0);
        }
        else if (paletteType == ToolDataManage.TYPE_SUP) {
            this.llBase.setVisibility(0);
            this.llSearch.setVisibility(8);
            this.btnSupSave.setVisibility(0);
            this.ibtMore.setVisibility(8);
        }
        else if (paletteType == ToolDataManage.TYPE_SEARCH) {
            this.llBase.setVisibility(8);
            this.llSearch.setVisibility(0);
            this.btnSupSave.setVisibility(8);
            this.ibtMore.setVisibility(8);
        }
    }
    
    public void setUndoEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.undoItemView, b);
    }
}
