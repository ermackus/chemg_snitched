package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class CountryLink
{
    private final OnCountryFinishLister onCountryFinishLister;
    
    public CountryLink(final OnCountryFinishLister onCountryFinishLister) {
        this.onCountryFinishLister = onCountryFinishLister;
    }
    
    public void getCountryList() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getCountryCode()), (RequestCallBack)new CountryLink$1(this));
    }
    
    public interface OnCountryFinishLister
    {
        void onFinish();
    }
}
