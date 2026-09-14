package com.kingagroot.kingdraw.core;

import java.io.IOException;
import android.text.TextUtils;
import android.os.Handler;
import android.os.Looper;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import org.json.JSONException;
import com.kingagroot.kingdraw.core.model.ModelUtils;
import org.json.JSONObject;
import com.kingagroot.kingdraw.core.data.DataOutModel;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.core.model.FormatValue;

public class FileReader
{
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    private static native boolean setFileContent(final String p0, final String p1, final String p2);
    
    public static class Builder
    {
        private String fileFormat;
        private FormatValue fileFormatValue;
        private String fileJson;
        private KingDrawView kingDrawView;
        private PaletteConfigModel paletteConfigModel;
        private DataOutModel result;
        private FormatValue viewInitFormatValue;
        
        public FormatValue getFileFormatValue() {
            try {
                final JSONObject jsonObject = new JSONObject(this.result.josn);
                if (jsonObject.has("format")) {
                    return (FormatValue)ModelUtils.formJson(jsonObject.getString("format"), (Class)FormatValue.class);
                }
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
            }
            return null;
        }
        
        public ProtocolTypeEnum getProtocolType() {
            final DataOutModel result = this.result;
            if (result != null) {
                return ProtocolTypeEnum.valueOfCode(result.type);
            }
            return ProtocolTypeEnum.KDX;
        }
        
        public void readByPath(String json) throws IOException {
            final DataOutModel byPath = ProtocolReader.builder().setNeedType(true).setNeedKDJson(true).readByPath(json);
            this.result = byPath;
            final String josn = byPath.josn;
            final FormatValue fileFormatValue = this.getFileFormatValue();
            if (fileFormatValue != null) {
                this.fileFormatValue = fileFormatValue;
            }
            final FormatValue fileFormatValue2 = this.fileFormatValue;
            if (fileFormatValue2 != null) {
                json = ModelUtils.toJson((Object)fileFormatValue2);
            }
            else {
                json = "";
            }
            this.fileJson = josn;
            this.fileFormat = json;
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                final Builder this$0;
                
                public void run() {
                    if (this.this$0.kingDrawView != null) {
                        if (TextUtils.isEmpty((CharSequence)this.this$0.kingDrawView.getPaletteId())) {
                            FormatValue formatValue = this.this$0.viewInitFormatValue;
                            if (this.this$0.fileFormatValue != null) {
                                formatValue = this.this$0.fileFormatValue;
                            }
                            if (this.this$0.paletteConfigModel == null) {
                                this.this$0.paletteConfigModel = new PaletteConfigModel();
                            }
                            this.this$0.kingDrawView.createPalette(formatValue, this.this$0.paletteConfigModel);
                        }
                        setFileContent(this.this$0.fileJson, this.this$0.fileFormat, this.this$0.kingDrawView.getPaletteId());
                    }
                }
            });
        }
        
        public void readByString(String json) {
            final DataOutModel byString = ProtocolReader.builder().setNeedType(true).setNeedKDJson(true).readByString(json);
            this.result = byString;
            final String josn = byString.josn;
            final FormatValue fileFormatValue = this.getFileFormatValue();
            if (fileFormatValue != null) {
                this.fileFormatValue = fileFormatValue;
            }
            final FormatValue fileFormatValue2 = this.fileFormatValue;
            if (fileFormatValue2 != null) {
                json = ModelUtils.toJson((Object)fileFormatValue2);
            }
            else {
                json = "";
            }
            this.fileJson = josn;
            this.fileFormat = json;
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
                final Builder this$0;
                
                public void run() {
                    if (this.this$0.kingDrawView != null) {
                        if (TextUtils.isEmpty((CharSequence)this.this$0.kingDrawView.getPaletteId())) {
                            FormatValue formatValue = this.this$0.viewInitFormatValue;
                            if (this.this$0.fileFormatValue != null) {
                                formatValue = this.this$0.fileFormatValue;
                            }
                            if (this.this$0.paletteConfigModel == null) {
                                this.this$0.paletteConfigModel = new PaletteConfigModel();
                            }
                            this.this$0.kingDrawView.createPalette(formatValue, this.this$0.paletteConfigModel);
                        }
                        setFileContent(this.this$0.fileJson, this.this$0.fileFormat, this.this$0.kingDrawView.getPaletteId());
                    }
                }
            });
        }
        
        public Builder withFileFormatValue(final FormatValue fileFormatValue) {
            this.fileFormatValue = fileFormatValue;
            return this;
        }
        
        public Builder withPaletteConfig(final PaletteConfigModel paletteConfigModel) {
            this.paletteConfigModel = paletteConfigModel;
            return this;
        }
        
        public Builder withView(final KingDrawView kingDrawView) {
            this.kingDrawView = kingDrawView;
            return this;
        }
        
        public Builder withView(final KingDrawView kingDrawView, final FormatValue viewInitFormatValue) {
            this.kingDrawView = kingDrawView;
            this.viewInitFormatValue = viewInitFormatValue;
            return this;
        }
    }
}
