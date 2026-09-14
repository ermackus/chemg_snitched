package com.kingagroot.kingdraw.core.data;

import android.text.TextUtils;
import java.io.File;

public class ProtocolWriter
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static Builer builder() {
        return new Builer();
    }
    
    private static native String wirteToFile(final String p0, final String p1, final int p2, final DataParam p3);
    
    private static native String writToKDXFile(final String p0, final String p1, final DataParam p2);
    
    private static native String writeToCDXFile(final String p0, final String p1, final DataParam p2);
    
    private static String writeToFile(final String s, final String s2, String s3, final ProtocolTypeEnum protocolTypeEnum, final DataParam dataParam) {
        final File file = new File(s2);
        if (!file.exists()) {
            file.mkdir();
        }
        if (s2.endsWith(File.separator)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s2);
            sb.append(s3);
            sb.append(protocolTypeEnum.extension);
            s3 = sb.toString();
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(s2);
            sb2.append(File.separator);
            sb2.append(s3);
            sb2.append(protocolTypeEnum.extension);
            s3 = sb2.toString();
        }
        dataParam.setContent(true);
        if (protocolTypeEnum == ProtocolTypeEnum.CDX) {
            return writeToCDXFile(s, s3, dataParam);
        }
        if (protocolTypeEnum == ProtocolTypeEnum.KDX) {
            return writToKDXFile(s, s3, dataParam);
        }
        final File file2 = new File(s2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return wirteToFile(s, s3, protocolTypeEnum.code, dataParam);
    }
    
    private static native String writeToString(final String p0, final int p1, final DataParam p2);
    
    private static String writeToString(final String s, final ProtocolTypeEnum protocolTypeEnum, final DataParam dataParam) {
        if (protocolTypeEnum == ProtocolTypeEnum.CDX) {
            return "";
        }
        dataParam.setContent(true);
        return writeToString(s, protocolTypeEnum.code, dataParam);
    }
    
    public static class Builer
    {
        private DataOutModel outModel;
        private DataParam param;
        
        public Builer() {
            this.param = new DataParam();
            this.outModel = new DataOutModel();
        }
        
        public String getFormula() {
            return this.outModel.formula;
        }
        
        public String getJson() {
            return this.outModel.josn;
        }
        
        public String getProtocolContent() {
            return this.outModel.content;
        }
        
        public String getSmiles() {
            return this.outModel.smiles;
        }
        
        public Builer setKDXImageSource(final String thumbnailPath, final String pngPath, final String jpgPath) {
            if (!TextUtils.isEmpty((CharSequence)thumbnailPath)) {
                this.param.setThumbnailPath(thumbnailPath);
            }
            if (!TextUtils.isEmpty((CharSequence)pngPath)) {
                this.param.setPngPath(pngPath);
            }
            if (!TextUtils.isEmpty((CharSequence)jpgPath)) {
                this.param.setJpgPath(jpgPath);
            }
            return this;
        }
        
        public Builer setNeedFormula(final boolean formula) {
            this.param.setFormula(formula);
            return this;
        }
        
        public Builer setNeedKDJson(final boolean json) {
            this.param.setJson(json);
            return this;
        }
        
        public Builer setNeedProtocolContent(final boolean content) {
            this.param.setContent(content);
            return this;
        }
        
        public Builer setNeedSmiles(final boolean smiles) {
            this.param.setSmiles(smiles);
            return this;
        }
        
        public boolean writeToFile(String access$100, final String s, final String s2, final ProtocolTypeEnum protocolTypeEnum) {
            this.param.setContent(true);
            access$100 = writeToFile(access$100, s, s2, protocolTypeEnum, this.param);
            this.outModel.parse(access$100);
            return true;
        }
        
        public String writeToString(String access$000, final ProtocolTypeEnum protocolTypeEnum) {
            if (protocolTypeEnum != ProtocolTypeEnum.CDX) {
                this.param.setContent(true);
                access$000 = writeToString(access$000, protocolTypeEnum, this.param);
                this.outModel.parse(access$000);
            }
            return this.getProtocolContent();
        }
    }
}
