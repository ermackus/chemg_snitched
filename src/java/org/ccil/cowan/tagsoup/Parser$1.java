package org.ccil.cowan.tagsoup;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;

class Parser$1 implements AutoDetector
{
    private final Parser this$0;
    
    Parser$1(final Parser this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Reader autoDetectingReader(final InputStream inputStream) {
        return (Reader)new InputStreamReader(inputStream);
    }
}
