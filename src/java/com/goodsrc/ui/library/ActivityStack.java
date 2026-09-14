package com.goodsrc.ui.library;

import android.app.Activity;
import java.util.Vector;

public class ActivityStack
{
    private static ActivityStack instance;
    private final Vector<Activity> activityVector;
    
    private ActivityStack() {
        this.activityVector = (Vector<Activity>)new Vector();
    }
    
    public static ActivityStack getInstance() {
        if (ActivityStack.instance == null) {
            ActivityStack.instance = new ActivityStack();
        }
        return ActivityStack.instance;
    }
    
    public void add(final Activity activity) {
        this.activityVector.add((Object)activity);
    }
    
    public int getSize() {
        return this.activityVector.size();
    }
    
    public Activity getTopActivity() {
        if (this.getSize() > 0) {
            return (Activity)this.activityVector.elementAt(this.getSize() - 1);
        }
        return null;
    }
    
    public void remove(final Activity activity) {
        if (this.activityVector.size() > 0 && activity != null) {
            this.activityVector.remove((Object)activity);
        }
    }
}
