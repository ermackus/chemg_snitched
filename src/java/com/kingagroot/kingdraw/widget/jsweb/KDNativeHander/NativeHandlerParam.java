package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import java.util.List;
import java.io.Serializable;

public class NativeHandlerParam implements Serializable
{
    public boolean autoSave;
    public List<String> dataScope;
    public String fileName;
    public String filePath;
    public long saveInterval;
    public String taskId;
    
    public NativeHandlerParam() {
        this.autoSave = false;
        this.saveInterval = 0L;
    }
}
