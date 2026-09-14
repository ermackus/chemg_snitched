package com.kingagroot.kingdraw.dialog;

import android.app.Activity;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.HashMap;
import java.util.Map;

public class DialogManager
{
    private static DialogManager dialogManager;
    Map<String, DialogBaseQueue> dialogQueueMap;
    
    private DialogManager() {
        this.dialogQueueMap = (Map<String, DialogBaseQueue>)new HashMap();
    }
    
    public static DialogManager getInstance() {
        if (DialogManager.dialogManager == null) {
            DialogManager.dialogManager = new DialogManager();
        }
        return DialogManager.dialogManager;
    }
    
    public void clear() {
        final Iterator iterator = this.dialogQueueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            ((DialogBaseQueue)((Map$Entry)iterator.next()).getValue()).onDestory();
        }
        this.dialogQueueMap.clear();
    }
    
    public DialogBaseQueue getDialogQueue(final Activity activity) {
        final StringBuilder sb = new StringBuilder();
        sb.append(activity.getPackageName());
        sb.append(".");
        sb.append(activity.getLocalClassName());
        final String string = sb.toString();
        if (!this.dialogQueueMap.containsKey((Object)string)) {
            final DialogQueue dialogQueue = new DialogQueue();
            this.dialogQueueMap.put((Object)string, (Object)dialogQueue);
            return (DialogBaseQueue)dialogQueue;
        }
        return (DialogBaseQueue)this.dialogQueueMap.get((Object)string);
    }
    
    public void onDestory(final Activity activity) {
        final String localClassName = activity.getLocalClassName();
        final DialogBaseQueue dialogBaseQueue = (DialogBaseQueue)this.dialogQueueMap.get((Object)localClassName);
        if (dialogBaseQueue != null) {
            dialogBaseQueue.onDestory();
        }
        this.dialogQueueMap.remove((Object)localClassName);
    }
}
