package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.interfaces.LimitConfigHintDbi;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.interfaces.impl.LimitConfigHintDbiMpl;
import com.goodsrc.library.utils.SPUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.model.LimitHintModel;
import com.kingagroot.kingdraw.model.LimitResultModel;
import com.kingagroot.kdlimitconfig.ResultModel;
import com.kingagroot.kingdraw.model.LimitModel;

public class LimitLink
{
    public static final String TAG = "LimitLink";
    private LimitModel limitModel;
    private final int loginState;
    private final OnLimitConfigInfoListener onLimitConfigInfoListener;
    
    public LimitLink(final int loginState, final OnLimitConfigInfoListener onLimitConfigInfoListener) {
        this.loginState = loginState;
        this.onLimitConfigInfoListener = onLimitConfigInfoListener;
    }
    
    private LimitResultModel getLimitResultModel(final ResultModel resultModel) {
        final LimitResultModel limitResultModel = new LimitResultModel();
        limitResultModel.setLoginState(this.loginState);
        limitResultModel.setData(resultModel.data);
        limitResultModel.setKey(resultModel.key);
        return limitResultModel;
    }
    
    private com.kingagroot.kdlimitconfig.LimitModel getSaveLimitModel(final LimitModel limitModel) {
        final com.kingagroot.kdlimitconfig.LimitModel limitModel2 = new com.kingagroot.kdlimitconfig.LimitModel();
        limitModel2.setLocalFileStoreNum(limitModel.getLocalFileStoreNum());
        limitModel2.setCloudFileStoreNum(limitModel.getCloudFileStoreNum());
        limitModel2.setExportImageSetting(limitModel.getExportImageSetting());
        limitModel2.setAllowImagesExported(limitModel.getAllowImagesExported());
        limitModel2.setFileRetrievalService(limitModel.getFileRetrievalService());
        limitModel2.setPrintSetting(limitModel.getPrintSetting());
        limitModel2.setCustomGroups(limitModel.getCustomGroups());
        limitModel2.setCustomizeTemplate(limitModel.getCustomizeTemplate());
        limitModel2.setGroupUnfolds(limitModel.getGroupUnfolds());
        limitModel2.setDimensionalSetting(limitModel.getDimensionalSetting());
        limitModel2.setEncyclopediaAvailable(limitModel.getEncyclopediaAvailable());
        limitModel2.setCurrentTime(System.currentTimeMillis());
        return limitModel2;
    }
    
    private void saveHintAndUrl(final LimitHintModel limitHintModel, final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            SPUtil.setStringDefault("LIMIT_URL", s);
        }
        if (limitHintModel != null) {
            ((LimitConfigHintDbi)new LimitConfigHintDbiMpl()).saveLimitHintData(limitHintModel);
        }
    }
    
    public void getLimitConfig() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getVipLimitConfig()), (RequestCallBack)new LimitLink$1(this));
    }
    
    public interface OnLimitConfigInfoListener
    {
        void onFinish();
    }
}
