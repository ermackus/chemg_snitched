package com.kingagroot.kingdraw.widget;

import android.view.View$OnClickListener;
import android.view.KeyEvent;
import android.widget.TextView$OnEditorActionListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.View;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.SearchView$OnQueryTextListener;
import android.widget.ImageButton;
import android.widget.EditText;
import android.content.Context;
import android.widget.LinearLayout;

public class GSearchView extends LinearLayout
{
    private final Context context;
    private EditText etSearch;
    private ImageButton imgbtnSearch;
    private LinearLayout llTitle;
    private SearchView$OnQueryTextListener onQueryTextListener;
    private TextView tvTitle;
    
    public GSearchView(final Context context) {
        this(context, null);
    }
    
    public GSearchView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public GSearchView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.context = context;
        this.initView(View.inflate(context, 2131493090, (ViewGroup)this));
    }
    
    private void hidInput() {
        new Handler().postDelayed((Runnable)new Runnable(this) {
            final GSearchView this$0;
            
            public void run() {
                try {
                    ((InputMethodManager)this.this$0.context.getSystemService("input_method")).hideSoftInputFromWindow(((Activity)this.this$0.context).getCurrentFocus().getWindowToken(), 2);
                }
                catch (final Exception ex) {}
            }
        }, 300L);
    }
    
    private void initView(final View view) {
        this.etSearch = (EditText)view.findViewById(2131296656);
        this.tvTitle = (TextView)view.findViewById(2131297675);
        this.imgbtnSearch = (ImageButton)view.findViewById(2131296890);
        this.etSearch.setImeOptions(3);
        this.etSearch.setInputType(1);
        this.etSearch.setSingleLine(true);
        this.etSearch.addTextChangedListener((TextWatcher)new TextWatcher(this) {
            final GSearchView this$0;
            
            public void afterTextChanged(final Editable editable) {
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                this.this$0.onQueryTextListener.onQueryTextChange(charSequence.toString());
            }
        });
        this.etSearch.setOnEditorActionListener((TextView$OnEditorActionListener)new TextView$OnEditorActionListener(this) {
            final GSearchView this$0;
            
            public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
                if (n == 3) {
                    final GSearchView this$0 = this.this$0;
                    this$0.setListener(this$0.etSearch.getText().toString());
                    this.this$0.hidInput();
                    return true;
                }
                return false;
            }
        });
        this.imgbtnSearch.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final GSearchView this$0;
            
            public void onClick(final View view) {
                this.this$0.onSearch(true);
            }
        });
        this.llTitle = (LinearLayout)this.findViewById(2131297034);
    }
    
    private void showInput() {
        new Handler().postDelayed((Runnable)new Runnable(this) {
            final GSearchView this$0;
            
            public void run() {
                ((InputMethodManager)this.this$0.etSearch.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
            }
        }, 300L);
    }
    
    public String getQuery() {
        return this.etSearch.getText().toString().trim();
    }
    
    public void onSearch(final boolean b) {
        if (b) {
            this.llTitle.setVisibility(8);
            this.etSearch.setVisibility(0);
            this.etSearch.requestFocus();
            this.showInput();
        }
        else {
            this.llTitle.setVisibility(0);
            this.etSearch.setVisibility(8);
        }
    }
    
    public void setListener(final String s) {
        this.onQueryTextListener.onQueryTextSubmit(s);
    }
    
    public void setOnQueryTextListener(final SearchView$OnQueryTextListener onQueryTextListener) {
        this.onQueryTextListener = onQueryTextListener;
    }
    
    public void setQuery(final String s, final boolean b) {
        if (b) {
            this.setListener(s);
        }
        this.etSearch.setText((CharSequence)s);
        this.etSearch.setSelection(this.etSearch.getText().length());
    }
    
    public void setQueryHint(final String hint) {
        this.etSearch.setHint((CharSequence)hint);
    }
    
    public void setTitle(final String text) {
        this.tvTitle.setText((CharSequence)text);
    }
}
