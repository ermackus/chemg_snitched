package com.kingagroot.kingdraw.dialog;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.goodsrc.library.utils.NetworkUtil;
import android.os.Handler;
import android.widget.AdapterView;
import android.util.DisplayMetrics;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.content.res.Configuration;
import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import android.app.Activity;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import com.goodsrc.library.utils.StringUtils;
import com.goodsrc.library.utils.KeyBoardUtils;
import android.text.Html;
import com.kingagroot.kingdraw.model.FileTypeModel;
import android.widget.EditText;
import android.view.MotionEvent;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.BaseAdapter;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.View;
import android.widget.ListAdapter;
import com.kingagroot.kingdraw.adapter.FileTypeChooseAdapter;
import androidx.appcompat.widget.ListPopupWindow;
import android.text.TextUtils;
import android.graphics.Bitmap;
import android.widget.Toast;
import android.text.Editable;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextWatcher;
import com.goodsrc.ui.library.widget.RefreshLayout;
import android.widget.LinearLayout;
import com.kingagroot.kingdraw.model.FileType;
import com.goodsrc.ui.library.widget.ClearEditText;
import android.content.Context;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class FileExportDialog extends Dialog implements View$OnClickListener
{
    private final int MAX_LENGTH;
    int SpinnerHeight;
    int SpinnerWidth;
    Button btn_cancel;
    Button btn_sure;
    Button btn_word_format;
    private final Context context;
    private ClearEditText et_edit_name;
    FileType fileType;
    private String filename;
    private String format;
    private boolean isShowDescription;
    private LinearLayout llNotch;
    private LinearLayout llSpinner;
    private OnItemClickListener mListener;
    private RefreshLayout refreshView;
    private final TextWatcher textWatcher;
    Toolbar toolbar;
    private TextView tvFormat;
    private TextView tvVersionName;
    private TextView tv_format_explain;
    private WebView webView;
    private final WebViewClient webViewClient;
    
    public FileExportDialog(final Context context, final String filename, final FileType fileType) {
        super(context);
        this.MAX_LENGTH = 50;
        this.fileType = FileType.KDX;
        this.textWatcher = (TextWatcher)new TextWatcher() {
            private CharSequence temp;
            final FileExportDialog this$0;
            
            public void afterTextChanged(final Editable editable) {
                if (this.temp.length() > 50) {
                    Toast.makeText(this.this$0.getContext(), (CharSequence)this.this$0.getContext().getResources().getString(2131820848), 1).show();
                }
            }
            
            public void beforeTextChanged(final CharSequence temp, final int n, final int n2, final int n3) {
                this.temp = temp;
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
            }
        };
        this.webViewClient = new WebViewClient() {
            final FileExportDialog this$0;
            
            public void onPageFinished(final WebView webView, final String s) {
                super.onPageFinished(webView, s);
                this.this$0.refreshView.setRefreshing(false);
            }
            
            public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                this.this$0.refreshView.setRefreshing(true);
            }
        };
        this.context = context;
        this.filename = filename;
        if (fileType != null) {
            this.fileType = fileType;
        }
    }
    
    private void initData() {
        final String filename = this.filename;
        if (filename != null) {
            this.et_edit_name.setText((CharSequence)filename);
            if (!TextUtils.isEmpty((CharSequence)this.filename)) {
                this.et_edit_name.setSelection(this.et_edit_name.getText().toString().length());
            }
        }
    }
    
    private void initDropdownPopup(final FileType fileType) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(this.context);
        final FileTypeChooseAdapter adapter = new FileTypeChooseAdapter(this.context, this.fileType);
        listPopupWindow.setAdapter((ListAdapter)adapter);
        listPopupWindow.setWidth(this.SpinnerWidth);
        listPopupWindow.setHeight(adapter.getHeight());
        listPopupWindow.setAnchorView((View)this.llSpinner);
        listPopupWindow.setModal(true);
        listPopupWindow.setVerticalOffset(-(this.SpinnerHeight + GDensityUtil.dp2px(1.0f)));
        listPopupWindow.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$FileExportDialog$XMmD8mgLVrOJPULxH9KYP7GrqIg(this, (BaseAdapter)adapter, listPopupWindow));
        listPopupWindow.show();
    }
    
    private boolean isShouldHideKeyboard(final View view, final MotionEvent motionEvent) {
        boolean b2;
        final boolean b = b2 = false;
        if (view != null) {
            b2 = b;
            if (view instanceof EditText) {
                final int[] array2;
                final int[] array = array2 = new int[2];
                array2[1] = (array2[0] = 0);
                view.getLocationInWindow(array);
                final int n = array[0];
                final int n2 = array[1];
                final int height = view.getHeight();
                final int width = view.getWidth();
                if (motionEvent.getX() > n && motionEvent.getX() < width + n && motionEvent.getY() > n2) {
                    b2 = b;
                    if (motionEvent.getY() < height + n2) {
                        return b2;
                    }
                }
                b2 = true;
            }
        }
        return b2;
    }
    
    private void setFileType(final FileTypeModel fileTypeModel) {
        this.tv_format_explain.setText((CharSequence)Html.fromHtml(fileTypeModel.getInfo()));
        this.tvFormat.setText((CharSequence)fileTypeModel.getType().extension.replace((CharSequence)".", (CharSequence)""));
        this.tvVersionName.setText((CharSequence)fileTypeModel.getVersionName());
        this.fileType = fileTypeModel.getType();
    }
    
    public void dismiss() {
        super.dismiss();
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            final View currentFocus = this.getCurrentFocus();
            if (currentFocus != null && this.isShouldHideKeyboard(currentFocus, motionEvent)) {
                KeyBoardUtils.hidInput(currentFocus);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public String getFileName() {
        return StringUtils.reFileName(this.et_edit_name.getText().toString());
    }
    
    public FileType getFileType() {
        return this.fileType;
    }
    
    protected void initView(final View view) {
        (this.toolbar = (Toolbar)this.findViewById(2131297503)).setTitle((CharSequence)this.getContext().getResources().getString(2131820855));
        this.toolbar.setNavigationOnClickListener((View$OnClickListener)new _$$Lambda$FileExportDialog$DB_ncQ3An7puZOccBlzPzc_kAcc(this));
        final Configuration configuration = this.context.getResources().getConfiguration();
        this.btn_sure = (Button)view.findViewById(2131296460);
        this.btn_cancel = (Button)view.findViewById(2131296413);
        this.btn_word_format = (Button)view.findViewById(2131296465);
        this.tv_format_explain = (TextView)view.findViewById(2131297598);
        this.et_edit_name = (ClearEditText)view.findViewById(2131296633);
        (this.llSpinner = (LinearLayout)this.findViewById(2131297031)).setOnClickListener((View$OnClickListener)new _$$Lambda$FileExportDialog$UK_KCL9lv__tImuXkDMPjZrCbUQ(this));
        this.btn_sure.setOnClickListener((View$OnClickListener)this);
        this.btn_cancel.setOnClickListener((View$OnClickListener)this);
        this.btn_word_format.setOnClickListener((View$OnClickListener)this);
        this.et_edit_name.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(50) });
        this.et_edit_name.addTextChangedListener(this.textWatcher);
        final String string = this.getContext().getResources().getString(2131820820);
        this.format = string;
        this.tv_format_explain.setText((CharSequence)Html.fromHtml(string));
        this.et_edit_name.setText((CharSequence)this.filename);
        final int orientation = configuration.orientation;
        final Window window = this.getWindow();
        final WindowManager$LayoutParams attributes = window.getAttributes();
        final DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        if (orientation == 2) {
            this.toolbar.setVisibility(8);
            this.btn_cancel.setVisibility(0);
        }
        else if (orientation == 1) {
            this.toolbar.setVisibility(0);
            this.btn_cancel.setVisibility(8);
        }
        attributes.height = displayMetrics.heightPixels;
        attributes.width = displayMetrics.widthPixels;
        this.getWindow().setWindowAnimations(2131886852);
        window.setAttributes(attributes);
        window.setGravity(48);
        KeyBoardUtils.showInput((View)this.et_edit_name);
        final ViewTreeObserver viewTreeObserver = this.llSpinner.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener(this, viewTreeObserver) {
            final FileExportDialog this$0;
            final ViewTreeObserver val$observer;
            
            public void onGlobalLayout() {
                if (this.val$observer.isAlive()) {
                    this.val$observer.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                }
                if (this.this$0.SpinnerWidth == 0) {
                    final FileExportDialog this$0 = this.this$0;
                    this$0.SpinnerWidth = this$0.llSpinner.getMeasuredWidth();
                }
                if (this.this$0.SpinnerHeight == 0) {
                    final FileExportDialog this$2 = this.this$0;
                    this$2.SpinnerHeight = this$2.llSpinner.getMeasuredHeight();
                }
            }
        });
        this.tvFormat = (TextView)this.findViewById(2131297597);
        this.tvVersionName = (TextView)this.findViewById(2131297688);
        this.webView = (WebView)this.findViewById(2131297764);
        this.refreshView = (RefreshLayout)this.findViewById(2131297262);
        this.webView.setWebViewClient(this.webViewClient);
        this.refreshView.setRefresh(false);
        final LinearLayout llNotch = (LinearLayout)this.findViewById(2131297012);
        this.llNotch = llNotch;
        if (orientation == 2) {
            final NotchContext notchContext = new NotchContext((Activity)this.context, (View)llNotch);
            notchContext.checkNotchInScreen((NotchCallBack)new _$$Lambda$FileExportDialog$EfdRWBNTWMgsGdWuQhIlr8_NuiE(this, notchContext));
        }
    }
    
    public void onClick(final View view) {
        if (this.mListener != null) {
            if (view == this.btn_word_format) {
                final boolean isShowDescription = this.isShowDescription ^ true;
                this.isShowDescription = isShowDescription;
                if (isShowDescription) {
                    this.webView.setVisibility(0);
                    if (NetworkUtil.isNetworkConnected(this.context)) {
                        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                            this.webView.loadUrl(APIConfig.FORMAT_ZH);
                        }
                        else {
                            this.webView.loadUrl(APIConfig.FORMAT_EN);
                        }
                    }
                    else {
                        ToastUtil.showShort((CharSequence)this.context.getString(2131821065));
                    }
                }
                else {
                    this.webView.setVisibility(4);
                }
            }
            else if (view == this.btn_cancel) {
                this.dismiss();
                this.mListener.OnCancelClickListener(this);
            }
            else if (view == this.btn_sure) {
                final String fileName = this.getFileName();
                if (TextUtils.isEmpty((CharSequence)fileName)) {
                    ToastUtil.showShort((CharSequence)this.context.getResources().getString(2131821138));
                }
                else {
                    this.mListener.OnSureClickListener(this, this.getFileType(), fileName);
                }
            }
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final View inflate = LayoutInflater.from(this.context).inflate(2131493144, (ViewGroup)null);
        this.requestWindowFeature(1);
        this.setContentView(inflate);
        this.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.getWindow().setLayout(-1, -1);
        this.initView(inflate);
        this.setFileType(FileTypeChooseAdapter.getFileTypeModel(this.getContext(), this.fileType));
    }
    
    public void setFilename(final String filename) {
        this.filename = filename;
        this.initData();
    }
    
    public void setOnItemClickListener(final OnItemClickListener mListener) {
        this.mListener = mListener;
    }
    
    public interface OnItemClickListener
    {
        void OnCancelClickListener(final FileExportDialog p0);
        
        void OnSureClickListener(final FileExportDialog p0, final FileType p1, final String... p2);
    }
}
