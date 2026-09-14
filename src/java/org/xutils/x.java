package org.xutils;

import org.xutils.common.task.TaskControllerImpl;
import org.xutils.view.ViewInjectorImpl;
import org.xutils.common.TaskController;
import org.xutils.image.ImageManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.db.DbManagerImpl;
import android.content.Context;
import android.app.Application;

public final class x
{
    private x() {
    }
    
    public static Application app() {
        if (Ext.app == null) {
            try {
                Ext.app = new MockApplication((Context)Class.forName("com.android.layoutlib.bridge.impl.RenderAction").getDeclaredMethod("getCurrentContext", (Class<?>[])new Class[0]).invoke((Object)null, new Object[0]));
            }
            finally {
                throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate() and register your Application in manifest.");
            }
        }
        return Ext.app;
    }
    
    public static DbManager getDb(final DbManager.DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }
    
    public static HttpManager http() {
        if (Ext.httpManager == null) {
            HttpManagerImpl.registerInstance();
        }
        return Ext.httpManager;
    }
    
    public static ImageManager image() {
        if (Ext.imageManager == null) {
            ImageManagerImpl.registerInstance();
        }
        return Ext.imageManager;
    }
    
    public static boolean isDebug() {
        return Ext.debug;
    }
    
    public static TaskController task() {
        return Ext.taskController;
    }
    
    public static ViewInjector view() {
        if (Ext.viewInjector == null) {
            ViewInjectorImpl.registerInstance();
        }
        return Ext.viewInjector;
    }
    
    public static class Ext
    {
        private static Application app;
        private static boolean debug;
        private static HttpManager httpManager;
        private static ImageManager imageManager;
        private static TaskController taskController;
        private static ViewInjector viewInjector;
        
        private Ext() {
        }
        
        public static void init(final Application app) {
            TaskControllerImpl.registerInstance();
            if (Ext.app == null) {
                Ext.app = app;
            }
        }
        
        public static void setDebug(final boolean debug) {
            Ext.debug = debug;
        }
        
        public static void setHttpManager(final HttpManager httpManager) {
            Ext.httpManager = httpManager;
        }
        
        public static void setImageManager(final ImageManager imageManager) {
            Ext.imageManager = imageManager;
        }
        
        public static void setTaskController(final TaskController taskController) {
            if (Ext.taskController == null) {
                Ext.taskController = taskController;
            }
        }
        
        public static void setViewInjector(final ViewInjector viewInjector) {
            Ext.viewInjector = viewInjector;
        }
    }
    
    private static class MockApplication extends Application
    {
        public MockApplication(final Context context) {
            this.attachBaseContext(context);
        }
    }
}
