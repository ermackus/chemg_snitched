package org.ccil.cowan.tagsoup;

import java.io.Reader;
import java.io.InputStream;

public interface AutoDetector
{
    Reader autoDetectingReader(final InputStream p0);
}
