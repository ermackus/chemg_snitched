package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import com.kingagroot.component.ui.model.CountryModel;
import java.util.List;
import android.widget.BaseAdapter;

public class ChoiceCountryAdapter extends BaseAdapter
{
    private final List<CountryModel> countryModels;
    private final Context mContext;
    
    public ChoiceCountryAdapter(final Context mContext, final List<CountryModel> countryModels) {
        this.mContext = mContext;
        this.countryModels = countryModels;
    }
    
    public int getCount() {
        final List<CountryModel> countryModels = this.countryModels;
        if (countryModels == null) {
            return 0;
        }
        return countryModels.size();
    }
    
    public CountryModel getItem(final int n) {
        return (CountryModel)this.countryModels.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public String getSortLetters(final int n) {
        final List<CountryModel> countryModels = this.countryModels;
        if (countryModels != null && !countryModels.isEmpty()) {
            return ((CountryModel)this.countryModels.get(n)).getPinYinFirstCode();
        }
        return null;
    }
    
    public int getSortLettersFirstPosition(final String s) {
        final List<CountryModel> countryModels = this.countryModels;
        int n2;
        final int n = n2 = -1;
        if (countryModels != null) {
            if (countryModels.isEmpty()) {
                n2 = n;
            }
            else {
                int n3 = 0;
                while (true) {
                    n2 = n;
                    if (n3 >= this.countryModels.size()) {
                        break;
                    }
                    if (((CountryModel)this.countryModels.get(n3)).getPinYinFirstCode().equals((Object)s)) {
                        n2 = n3;
                        break;
                    }
                    ++n3;
                }
            }
        }
        return n2;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131492943, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final CountryModel countryModel = (CountryModel)this.countryModels.get(n);
        if (n == this.getSortLettersFirstPosition(this.getSortLetters(n))) {
            tag.tvLetter.setText((CharSequence)countryModel.getPinYinFirstCode());
            tag.tvLetter.setVisibility(0);
        }
        else {
            tag.tvLetter.setVisibility(8);
        }
        tag.tvAreaName.setText((CharSequence)countryModel.getKey());
        tag.tvAreaCode.setText((CharSequence)countryModel.getValue());
        return inflate;
    }
    
    public static class ViewHolder
    {
        private final TextView tvAreaCode;
        private final TextView tvAreaName;
        private final TextView tvLetter;
        
        public ViewHolder(final View view) {
            this.tvLetter = (TextView)view.findViewById(2131297611);
            this.tvAreaName = (TextView)view.findViewById(2131297534);
            this.tvAreaCode = (TextView)view.findViewById(2131297533);
        }
    }
}
