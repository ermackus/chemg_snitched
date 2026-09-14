package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.core.data.ProtocolWriter;
import com.kingagroot.kingdraw.core.data.ProtocolWriter$Builer;

public class FileWriter
{
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    private static native String getAllElementsJson(final String p0);
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder
    {
        private ProtocolWriter$Builer builer;
        private String paletteId;
        
        public Builder() {
            this.builer = ProtocolWriter.builder();
        }
        
        public String getFormula() {
            return this.builer.getFormula();
        }
        
        public String getJson() {
            return this.builer.getJson();
        }
        
        public String getProtocolContent() {
            return this.builer.getProtocolContent();
        }
        
        public String getSmiles() {
            return this.builer.getSmiles();
        }
        
        public Builder setKDXImageSource(final String s, final String s2, final String s3) {
            this.builer.setKDXImageSource(s, s2, s3);
            return this;
        }
        
        public Builder setNeedFormula(final boolean needFormula) {
            this.builer.setNeedFormula(needFormula);
            return this;
        }
        
        public Builder setNeedKDJson(final boolean needKDJson) {
            this.builer.setNeedKDJson(needKDJson);
            return this;
        }
        
        public Builder setNeedProtocolContent(final boolean needProtocolContent) {
            this.builer.setNeedProtocolContent(needProtocolContent);
            return this;
        }
        
        public Builder setNeedSmiles(final boolean needSmiles) {
            this.builer.setNeedSmiles(needSmiles);
            return this;
        }
        
        public Builder withView(final KingDrawView kingDrawView) {
            this.paletteId = kingDrawView.getPaletteId();
            return this;
        }
        
        public boolean writeToFile(final String s, final String s2, final ProtocolTypeEnum protocolTypeEnum) {
            return this.builer.writeToFile(getAllElementsJson(this.paletteId), s, s2, protocolTypeEnum);
        }
        
        public String writeToString(final ProtocolTypeEnum protocolTypeEnum) {
            if (protocolTypeEnum == ProtocolTypeEnum.CDX) {
                return "";
            }
            return this.builer.writeToString(getAllElementsJson(this.paletteId), protocolTypeEnum);
        }
    }
}
