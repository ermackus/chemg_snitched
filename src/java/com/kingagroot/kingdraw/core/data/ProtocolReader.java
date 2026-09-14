package com.kingagroot.kingdraw.core.data;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import android.text.TextUtils;
import java.io.IOException;

public class ProtocolReader
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static Builer builder() {
        return new Builer();
    }
    
    public static String getExtensionByFileName(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return s.substring(lastIndex);
    }
    
    private static String readByPath(String line, final DataParam dataParam) throws IOException {
        final String extensionByFileName = getExtensionByFileName(line);
        if (!TextUtils.isEmpty((CharSequence)extensionByFileName) && extensionByFileName.equals((Object)ProtocolTypeEnum.CDX.extension)) {
            return readCDXFile(line, dataParam);
        }
        if (!TextUtils.isEmpty((CharSequence)extensionByFileName) && extensionByFileName.equals((Object)ProtocolTypeEnum.KDX.extension)) {
            return readKDXFile(line, dataParam);
        }
        final StringBuilder sb = new StringBuilder();
        final File file = new File(line);
        if (file.exists()) {
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file)));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\n");
            }
        }
        return readByString(sb.toString(), dataParam);
    }
    
    private static native String readByString(final String p0, final int p1, final DataParam p2);
    
    private static String readByString(final String s, final DataParam dataParam) {
        return readByString(s, 0, dataParam);
    }
    
    private static native String readCDXFile(final String p0, final DataParam p1);
    
    private static native String readKDXFile(final String p0, final DataParam p1);
    
    public static class Builer
    {
        private DataParam param;
        
        public Builer() {
            this.param = new DataParam();
        }
        
        private DataOutModel parse(final String s) {
            final DataOutModel dataOutModel = new DataOutModel();
            dataOutModel.parse(s);
            return dataOutModel;
        }
        
        public DataOutModel readByPath(final String s) throws IOException {
            this.param.setJson(true);
            return this.parse(readByPath(s, this.param));
        }
        
        public DataOutModel readByString(final String s) {
            this.param.setJson(true);
            return this.parse(readByString(s, this.param));
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
        
        public Builer setNeedType(final boolean type) {
            this.param.setType(type);
            return this;
        }
    }
}
