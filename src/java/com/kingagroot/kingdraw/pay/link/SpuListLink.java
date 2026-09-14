package com.kingagroot.kingdraw.pay.link;

import com.kingagroot.kingdraw.pay.model.SpuModel;
import java.util.List;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$UserOrder;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class SpuListLink
{
    public static final String TAG = "SpuListLink";
    private OnSpuListListener onSpuListListener;
    
    public SpuListLink(final OnSpuListListener onSpuListListener) {
        this.onSpuListListener = onSpuListListener;
    }
    
    public void getSpuList() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$UserOrder.spu()), (RequestCallBack)new SpuListLink$1(this));
    }
    
    public class DataModel
    {
        private List<SpuModel> list;
        final SpuListLink this$0;
        private int total;
        
        public DataModel(final SpuListLink this$0) {
            this.this$0 = this$0;
        }
        
        public List<SpuModel> getList() {
            return this.list;
        }
        
        public int getTotal() {
            return this.total;
        }
        
        public void setList(final List<SpuModel> list) {
            this.list = list;
        }
        
        public void setTotal(final int total) {
            this.total = total;
        }
    }
    
    public interface OnSpuListListener
    {
        void onFinish();
        
        void onSuccess(final DataModel p0);
    }
}
