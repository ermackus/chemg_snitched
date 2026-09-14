package com.kingagroot.kingdraw.widget.ViewSearch;

import java.util.Collection;
import java.util.Iterator;
import com.kingagroot.kingdraw.model.GTagModel;
import com.goodsrc.library.utils.StringUtils;
import com.kingagroot.kingdraw.interfaces.impl.TagModelDBImpl;
import com.kingagroot.kingdraw.widget.FileTag.OnTagClickListener;
import com.kingagroot.kingdraw.widget.FileTag.OnTagFilterViewListener;
import com.kingagroot.kingdraw.widget.FileTag.GTagSelectorDialog;
import com.kingagroot.kingdraw.widget.FileTag.OnAddTagClickListener;
import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.widget.TextView;
import com.kingagroot.kingdraw.widget.FileTag.FileTagType;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;
import java.util.List;
import com.kingagroot.kingdraw.widget.FileTag.FileTagGroup;
import android.content.Context;
import com.kingagroot.kingdraw.widget.FileTag.GBaseTagFilterView;
import android.widget.LinearLayout;

public class FragSearchView extends LinearLayout implements GBaseTagFilterView
{
    Context contextview;
    private FileTagGroup fileTags;
    private final List<String> selectedTags;
    private TagModelDBI tagModelDBI;
    private final FileTagType tagType;
    private TextView tv_frags;
    
    public FragSearchView(final Context context) {
        super(context);
        this.tagType = FileTagType.delete;
        this.selectedTags = (List<String>)new ArrayList();
    }
    
    public FragSearchView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FragSearchView(final Context contextview, final AttributeSet set, final int n) {
        super(contextview, set, n);
        this.tagType = FileTagType.delete;
        this.selectedTags = (List<String>)new ArrayList();
        this.contextview = contextview;
        final View inflate = View.inflate(contextview, 2131493195, (ViewGroup)this);
        this.tv_frags = (TextView)inflate.findViewById(2131297602);
        (this.fileTags = (FileTagGroup)inflate.findViewById(2131296670)).appendAddTag((OnAddTagClickListener)new OnAddTagClickListener(this) {
            final FragSearchView this$0;
            
            public void onAdd(final View view) {
                final GTagSelectorDialog gTagSelectorDialog = new GTagSelectorDialog(this.this$0.contextview, (List)this.this$0.getTags(), this.this$0.getContext().getString(2131820586));
                gTagSelectorDialog.setOnTagFilterViewListener((OnTagFilterViewListener)new OnTagFilterViewListener(this) {
                    final FragSearchView$1 this$1;
                    
                    public void onSelectTags(final List<String> tags) {
                        this.this$1.this$0.setTags(tags);
                    }
                });
                gTagSelectorDialog.show();
            }
        });
        this.fileTags.setOnTagClickListener((OnTagClickListener)new OnTagClickListener(this) {
            final FragSearchView this$0;
            
            public void onClick(final View view, final String s) {
                this.this$0.removeTag(s);
                this.this$0.setSelectedTagNum();
            }
        });
        this.tagModelDBI = (TagModelDBI)new TagModelDBImpl();
    }
    
    private void setSelectedTagNum() {
        final int n = this.fileTags.getChildCount() - 1;
        if (n > 0) {
            this.tv_frags.setText((CharSequence)StringUtils.format(this.contextview.getString(2131820889), new Object[] { n }));
        }
        else {
            this.tv_frags.setText((CharSequence)this.contextview.getString(2131820888));
        }
    }
    
    public List<String> getTags() {
        return this.selectedTags;
    }
    
    public String getText() {
        return this.tv_frags.getText().toString();
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
    }
    
    public void removeTag(final String s) {
        this.fileTags.removeTag(s);
        this.selectedTags.remove((Object)s);
        this.tagModelDBI.removeTag(s);
    }
    
    public void setTags(final List<String> list) {
        this.fileTags.clearTags();
        this.selectedTags.clear();
        this.selectedTags.addAll((Collection)list);
        this.tagModelDBI.addSelectTagModels((List)list);
        this.fileTags.appendTags((List)list, this.tagType);
        this.setSelectedTagNum();
    }
}
