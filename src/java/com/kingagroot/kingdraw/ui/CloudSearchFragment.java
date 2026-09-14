package com.kingagroot.kingdraw.ui;

import android.text.TextUtils;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import com.kingagroot.kingdraw.model.CloudFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import android.text.Html;
import com.goodsrc.library.utils.StringUtils;
import android.view.View;

public class CloudSearchFragment extends CloudBaseFragment
{
    String searchKey;
    
    public CloudSearchFragment() {
        this.searchKey = "";
    }
    
    @Override
    public void notifyListView() {
        super.notifyListView();
        this.listview.setEmptyView((View)this.llEmptySearchCloud);
        if (this.adapterList.getCount() <= 0) {
            if (this.searchKey.length() > 15) {
                final StringBuilder sb = new StringBuilder();
                sb.append(this.searchKey.substring(0, 11));
                sb.append("...");
                this.searchKey = sb.toString();
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("<font color='#000000'>");
            sb2.append(this.searchKey);
            sb2.append("</font>");
            this.tvEmptySearchCloud.setText((CharSequence)Html.fromHtml(StringUtils.format(this.getString(2131821352), new Object[] { sb2.toString() })));
        }
    }
    
    @Override
    protected void onAdapterConvert(final ViewHolder viewHolder, final CloudFileModel cloudFileModel) {
        super.onAdapterConvert(viewHolder, cloudFileModel);
        final CheckBox checkBox = (CheckBox)viewHolder.getView(2131296478);
        final View view = viewHolder.getView(2131296526);
        checkBox.setVisibility(8);
        view.setVisibility(8);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onSearchKey(final String searchKey) {
        this.searchKey = searchKey;
        this.refreshData();
    }
    
    @Override
    protected void refreshData() {
        super.refreshData();
        if (!TextUtils.isEmpty((CharSequence)this.searchKey)) {
            this.loading();
            this.cloudPresenterI.refreshSearchData(this.searchKey);
        }
        else {
            this.loadingFinish();
        }
    }
}
