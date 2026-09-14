package com.goodsrc.library.http;

import android.webkit.MimeTypeMap;
import java.net.FileNameMap;

public class KDFileNameMap implements FileNameMap
{
    public String getContentTypeFor(String substring) {
        if (substring.endsWith("/")) {
            substring = "html";
        }
        else {
            final int length = substring.length();
            final int n = substring.lastIndexOf(46) + 1;
            if (n > substring.lastIndexOf(47)) {
                substring = substring.substring(n, length);
            }
            else {
                substring = "";
            }
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(substring);
    }
}
