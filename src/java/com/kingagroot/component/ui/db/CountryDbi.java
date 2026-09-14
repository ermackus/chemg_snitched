package com.kingagroot.component.ui.db;

import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.component.ui.model.CountryModel;
import java.util.List;

public interface CountryDbi
{
    long CountryCount();
    
    OperationState addCountryList(final List<CountryModel> p0);
    
    void deleteAllData();
    
    List<CountryModel> getAllData();
    
    CountryModel getCountryByCountryCode(final String p0);
    
    CountryModel getCountryByName(final String p0);
    
    List<CountryModel> getCountryModels(final String p0);
}
