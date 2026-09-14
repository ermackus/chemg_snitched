package com.goodsrc.ui.library.widget;

import android.app.Activity;
import java.util.Stack;

public class AppManager
{
    private static Stack<Activity> activityStack;
    private static AppManager appManager;
    
    private AppManager() {
    }
    
    public static AppManager getInstance() {
        if (AppManager.appManager == null) {
            AppManager.appManager = new AppManager();
        }
        return AppManager.appManager;
    }
    
    public void addActivity(final Activity activity) {
        if (AppManager.activityStack == null) {
            AppManager.activityStack = (Stack<Activity>)new Stack();
        }
        AppManager.activityStack.add((Object)activity);
    }
    
    public Activity getLastActivity() {
        final Stack<Activity> activityStack = AppManager.activityStack;
        if (activityStack != null && !activityStack.empty()) {
            return (Activity)AppManager.activityStack.lastElement();
        }
        return null;
    }
    
    public void removeActivity(final Activity activity) {
        final Stack<Activity> activityStack = AppManager.activityStack;
        if (activityStack != null) {
            activityStack.remove((Object)activity);
        }
    }
}
