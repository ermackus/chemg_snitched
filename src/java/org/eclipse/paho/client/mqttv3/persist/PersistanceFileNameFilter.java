package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FilenameFilter;

public class PersistanceFileNameFilter implements FilenameFilter
{
    private final String fileExtension;
    
    public PersistanceFileNameFilter(final String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    public boolean accept(final File file, final String s) {
        return s.endsWith(this.fileExtension);
    }
}
