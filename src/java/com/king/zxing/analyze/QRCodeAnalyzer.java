package com.king.zxing.analyze;

import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.Reader;
import com.google.zxing.DecodeHintType;
import java.util.Map;
import com.king.zxing.DecodeConfig;

public class QRCodeAnalyzer extends BarcodeFormatAnalyzer
{
    public QRCodeAnalyzer() {
        this((DecodeConfig)null);
    }
    
    public QRCodeAnalyzer(final DecodeConfig decodeConfig) {
        super(decodeConfig);
    }
    
    public QRCodeAnalyzer(final Map<DecodeHintType, Object> hints) {
        this(new DecodeConfig().setHints((Map)hints));
    }
    
    @Override
    public Reader createReader() {
        return (Reader)new QRCodeReader();
    }
}
