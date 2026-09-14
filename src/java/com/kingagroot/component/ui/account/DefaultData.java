package com.kingagroot.component.ui.account;

import com.goodsrc.library.utils.LanguageTool;
import com.kingagroot.component.ui.model.CountryModel;
import android.content.Context;

public class DefaultData
{
    public static CountryModel getDefaultArea(final Context context) {
        final CountryModel countryModel = new CountryModel();
        if (LanguageTool.getLanguageType(context).equals((Object)LanguageTool.SER_ZH)) {
            countryModel.setKey("\u4e2d\u56fd");
            countryModel.setRootKey("\u4e2d\u56fd");
            countryModel.setCountryCode("CN");
            countryModel.setValue("+86");
        }
        else {
            countryModel.setKey("United States of America");
            countryModel.setRootKey("\u7f8e\u56fd");
            countryModel.setCountryCode("US");
            countryModel.setValue("+1");
        }
        return countryModel;
    }
}
