package org.xutils.http.body;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class FileBody extends InputStreamBody
{
    private String contentType;
    private File file;
    
    public FileBody(final File file) throws IOException {
        this(file, null);
    }
    
    public FileBody(final File file, final String contentType) throws IOException {
        super((InputStream)new FileInputStream(file));
        this.file = file;
        this.contentType = contentType;
    }
    
    public static String getFileContentType(final File file) {
        final String guessContentTypeFromName = HttpURLConnection.guessContentTypeFromName(file.getName());
        String replaceFirst;
        if (TextUtils.isEmpty((CharSequence)guessContentTypeFromName)) {
            replaceFirst = "application/octet-stream";
        }
        else {
            replaceFirst = guessContentTypeFromName.replaceFirst("\\/jpg$", "/jpeg");
        }
        return replaceFirst;
    }
    
    @Override
    public String getContentType() {
        if (TextUtils.isEmpty((CharSequence)this.contentType)) {
            this.contentType = getFileContentType(this.file);
        }
        return this.contentType;
    }
    
    @Override
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
}
