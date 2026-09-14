package com.alibaba.mtl.log.a;

import com.alibaba.mtl.appmonitor.AppMonitorDelegate;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.alibaba.mtl.log.d.i;
import android.content.res.Resources;
import com.alibaba.mtl.log.d.p;
import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.d.j;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;

public class a
{
    public static boolean A = false;
    public static volatile boolean B = false;
    private static boolean C = false;
    private static boolean D = false;
    private static String Q = "";
    private static String R = "";
    public static String S;
    private static String T;
    private static boolean a = false;
    private static long c = 300000L;
    private static long d = 30000L;
    static List<String> f;
    static List<String> g;
    static List<String> h;
    static List<String> i;
    static List<String> j;
    static List<String> k;
    
    static {
        com.alibaba.mtl.log.a.a.f = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.g = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.h = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.i = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.j = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.k = (List<String>)new ArrayList();
        com.alibaba.mtl.log.a.a.T = "net";
        com.alibaba.mtl.log.a.a.g.add((Object)"stm_bcx");
        com.alibaba.mtl.log.a.a.h.add((Object)"stm_x");
        com.alibaba.mtl.log.a.a.i.add((Object)"stm_p");
        com.alibaba.mtl.log.a.a.j.add((Object)"stm_c");
        com.alibaba.mtl.log.a.a.f.add((Object)"stm_d");
        com.alibaba.mtl.log.a.a.k.add((Object)"stm_nc");
    }
    
    public static long a() {
        return com.alibaba.mtl.log.a.a.d;
    }
    
    private static String a(final String s) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(com.alibaba.mtl.log.a.getContext());
        final StringBuilder sb = new StringBuilder();
        sb.append("ut_");
        sb.append(s);
        sb.append("_config");
        return defaultSharedPreferences.getString(sb.toString(), (String)null);
    }
    
    public static List<String> a(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (final Exception ex) {
            int1 = -1;
        }
        if (int1 == -1) {
            return com.alibaba.mtl.log.a.a.f;
        }
        if (int1 == 1 || int1 == 61005 || int1 == 61006 || int1 == 65501 || int1 == 65502 || int1 == 65503 || int1 == 65133) {
            return com.alibaba.mtl.log.a.a.g;
        }
        if ((int1 > 1 && int1 < 100) || (int1 > 15300 && int1 < 15400) || (int1 > 21000 && int1 < 30000) || (int1 > 61000 && int1 < 61200) || (int1 > 65000 && int1 < 65300)) {
            return com.alibaba.mtl.log.a.a.h;
        }
        if (int1 > 1000 && int1 < 2100) {
            return com.alibaba.mtl.log.a.a.i;
        }
        if (int1 > 2100 && int1 < 2200) {
            return com.alibaba.mtl.log.a.a.j;
        }
        if (int1 != 19999 && (int1 <= 4000 || int1 >= 10000) && (int1 <= 64000 || int1 >= 65000) && (int1 <= 65500 || int1 >= 65600) && (int1 <= 66000 || int1 >= 67000)) {
            return com.alibaba.mtl.log.a.a.k;
        }
        return com.alibaba.mtl.log.a.a.f;
    }
    
    public static void a(final Context context) {
        synchronized (a.class) {
            if (!com.alibaba.mtl.log.a.a.a) {
                com.alibaba.mtl.log.a.a.a = true;
                try {
                    p();
                    com.alibaba.mtl.appmonitor.d.j.a().a(context);
                }
                catch (final Exception ex) {
                    ex.printStackTrace();
                }
                b.a().q();
            }
        }
    }
    
    private static int b(final String s) {
        Label_0018: {
            if (TextUtils.isEmpty((CharSequence)s)) {
                break Label_0018;
            }
            try {
                return Integer.valueOf(s);
                intValue = -1;
                return intValue;
            }
            catch (final Exception ex) {
                return -1;
            }
        }
    }
    
    public static long b() {
        return com.alibaba.mtl.log.a.a.c;
    }
    
    public static boolean e() {
        return com.alibaba.mtl.log.a.a.A;
    }
    
    public static void f(final String r) {
        com.alibaba.mtl.log.a.a.R = r;
    }
    
    public static boolean f() {
        return com.alibaba.mtl.log.a.a.B ^ true;
    }
    
    public static String g() {
        if (TextUtils.isEmpty((CharSequence)com.alibaba.mtl.log.a.a.Q)) {
            g("adashbc.ut.taobao.com");
            if (!TextUtils.isEmpty((CharSequence)com.alibaba.mtl.log.a.a.R)) {
                g(com.alibaba.mtl.log.a.a.R);
            }
            final Context context = com.alibaba.mtl.log.a.getContext();
            g(getString(context, "utanalytics_https_host"));
            g(p.getString(context, "utanalytics_https_host"));
        }
        return com.alibaba.mtl.log.a.a.Q;
    }
    
    private static void g(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("https://");
            sb.append(s);
            sb.append("/rest/sur");
            com.alibaba.mtl.log.a.a.Q = sb.toString();
        }
    }
    
    private static String getString(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        try {
            final Resources resources = context.getResources();
            if (resources == null) {
                return null;
            }
            final int identifier = resources.getIdentifier(s, "string", context.getPackageName());
            if (identifier != 0) {
                return context.getString(identifier);
            }
            return null;
        }
        finally {
            return null;
        }
    }
    
    public static String h() {
        if ("l0".equalsIgnoreCase(com.alibaba.mtl.log.a.a.T)) {
            com.alibaba.mtl.log.d.i.a("Config", new Object[] { com.alibaba.mtl.log.a.a.T, "l0 config" });
            return "{\"SYSTEM\":{\"discardap\":\"1\"}}";
        }
        if ("l1".equalsIgnoreCase(com.alibaba.mtl.log.a.a.T)) {
            com.alibaba.mtl.log.d.i.a("Config", new Object[] { com.alibaba.mtl.log.a.a.T, "l1 config" });
            return "{\"SYSTEM\":{\"useut\":\"0\",\"bg_interval\":600000,\"fg_interval\":60000},\"STAT\":{\"sampling\":0,\"metrics\":[{\"module\":\"networkPrefer\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"network\",\"sampling\":10},{\"monitorPoint\":\"NetworkError\",\"sampling\":10000}]},{\"module\":\"mtopsdk\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"mtopStats\",\"sampling\":10},{\"monitorPoint\":\"mtopExceptions\",\"sampling\":10},{\"monitorPoint\":\"uploadStats\",\"sampling\":10000}]},{\"module\":\"ImageLib\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"ImageFlow\",\"sampling\":50},{\"monitorPoint\":\"ImageExceptions\",\"sampling\":50}]},{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"netperformance\",\"sampling\":10}]},{\"module\":\"ACDS\",\"sampling\":1000,\"monitorPoints\":[{\"monitorPoint\":\"initdata\",\"sampling\":20}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"AllPackageApps\",\"sampling\":10000},{\"monitorPoint\":\"Config\",\"sampling\":5000},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000},{\"monitorPoint\":\"H5\",\"sampling\":1},{\"monitorPoint\":\"WebViewVisit\",\"sampling\":5000}]},{\"module\":\"order\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"Page_OrderList\",\"sampling\":500},{\"monitorPoint\":\"Page_OrderDetail\",\"sampling\":1000}]},{\"module\":\"wopc\",\"sampling\":10000},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"load\",\"sampling\":10000}]},{\"module\":\"NetworkSDK\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"TrafficStats\",\"sampling\":20}]}]},\"COUNTER\":{\"sampling\":0,\"cacheCount\":60,\"metrics\":[{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"to_buss\",\"sampling\":1},{\"monitorPoint\":\"resend\",\"sampling\":1}]},{\"module\":\"configCenter\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SyncConfigsFromApi\",\"sampling\":2500},{\"monitorPoint\":\"SynconfigUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"NotifyConfigChanged\",\"sampling\":1},{\"monitorPoint\":\"RegisterToken\",\"sampling\":1},{\"monitorPoint\":\"SyncReferFromCdn\",\"sampling\":2500},{\"monitorPoint\":\"XcmdUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"SyncXcommandFromCdn\",\"sampling\":2500},{\"monitorPoint\":\"SaveConfigError\",\"sampling\":2500},{\"monitorPoint\":\"ParseTokenError\",\"sampling\":2500},{\"monitorPoint\":\"NotifyLoadConfig\",\"sampling\":2500}]},{\"module\":\"ACDS\",\"sampling\":250},{\"module\":\"Page_hotpatch\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hotpatch\",\"sampling\":250}]}]},\"ALARM\":{\"sampling\":0,\"successSampling\":0,\"failSampling\":0,\"metrics\":[{\"module\":\"Page_Home\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"HomeItemLoad\",\"sampling\":2500},{\"monitorPoint\":\"HomeNetwork\",\"sampling\":2500}]},{\"module\":\"Page_Detail\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"LoadDetail\",\"sampling\":2500},{\"monitorPoint\":\"LoadDesc\",\"sampling\":2500}]},{\"module\":\"Page_SearchItemList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchItem\",\"sampling\":2000}]},{\"module\":\"Page_Shop\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadShop\",\"sampling\":2500}]},{\"module\":\"Page_AutoLogin\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"autoLogin\",\"sampling\":2500}]},{\"module\":\"Page_Shop_Search_List\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"searchShopList\",\"sampling\":2500}]},{\"module\":\"Page_ShoppingCart\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"QueryData\",\"sampling\":2500},{\"monitorPoint\":\"ShowCart\",\"sampling\":2500},{\"monitorPoint\":\"UpdateCart\",\"sampling\":2500}]},{\"module\":\"Page_Pay\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"Alipay\",\"sampling\":2500}]},{\"module\":\"Page_ShowOrder\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"ShowOrder\",\"sampling\":2500},{\"monitorPoint\":\"CreateOrder\",\"sampling\":2500}]},{\"module\":\"Page_Attention\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadAttention\",\"sampling\":2500},{\"monitorPoint\":\"refreshAttention\",\"sampling\":2500}]},{\"module\":\"Page_SearchShopList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchShop\",\"sampling\":2500}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"NativeError\",\"sampling\":1250},{\"monitorPoint\":\"AllPackageApps\",\"sampling\":1250},{\"monitorPoint\":\"Config\",\"sampling\":1250},{\"monitorPoint\":\"JavaScriptError\",\"sampling\":1250},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000}]},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"domModule\",\"sampling\":2500},{\"monitorPoint\":\"enviroment\",\"sampling\":2500},{\"monitorPoint\":\"jsBridge\",\"sampling\":2500},{\"monitorPoint\":\"streamModule\",\"sampling\":2500}]},{\"module\":\"ACDS\",\"sampling\":250,\"monitorPoints\":[{\"monitorPoint\":\"huichang\",\"sampling\":0}]},{\"module\":\"Page_HuiChangTab\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcconfig\",\"sampling\":250}]},{\"module\":\"Page_2015MainStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":250},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":250},{\"monitorPoint\":\"hcmaindegrade\",\"sampling\":2500}]},{\"module\":\"Page_2015SubStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":250},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":250},{\"monitorPoint\":\"hcsubdegrade\",\"sampling\":2500}]}]}}";
        }
        if ("l2".equalsIgnoreCase(com.alibaba.mtl.log.a.a.T)) {
            com.alibaba.mtl.log.d.i.a("Config", new Object[] { com.alibaba.mtl.log.a.a.T, "l2 config" });
            return "{\"SYSTEM\":{\"useut\":\"0\",\"bg_interval\":600000,\"fg_interval\":60000},\"STAT\":{\"sampling\":0,\"metrics\":[{\"module\":\"networkPrefer\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"network\",\"sampling\":10},{\"monitorPoint\":\"NetworkError\",\"sampling\":10000}]},{\"module\":\"mtopsdk\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"mtopStats\",\"sampling\":10},{\"monitorPoint\":\"mtopExceptions\",\"sampling\":10},{\"monitorPoint\":\"uploadStats\",\"sampling\":10000}]},{\"module\":\"ImageLib\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"ImageFlow\",\"sampling\":50},{\"monitorPoint\":\"ImageExceptions\",\"sampling\":50}]},{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"netperformance\",\"sampling\":10}]},{\"module\":\"ACDS\",\"sampling\":1000,\"monitorPoints\":[{\"monitorPoint\":\"initdata\",\"sampling\":20}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"AllPackageApps\",\"sampling\":10000},{\"monitorPoint\":\"Config\",\"sampling\":5000},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000},{\"monitorPoint\":\"H5\",\"sampling\":1},{\"monitorPoint\":\"WebViewVisit\",\"sampling\":5000}]},{\"module\":\"order\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"Page_OrderList\",\"sampling\":500},{\"monitorPoint\":\"Page_OrderDetail\",\"sampling\":1000}]},{\"module\":\"wopc\",\"sampling\":10000},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"load\",\"sampling\":10000}]},{\"module\":\"NetworkSDK\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"TrafficStats\",\"sampling\":20}]}]},\"COUNTER\":{\"sampling\":0,\"cacheCount\":60,\"metrics\":[{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"to_buss\",\"sampling\":1},{\"monitorPoint\":\"resend\",\"sampling\":1}]},{\"module\":\"configCenter\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SyncConfigsFromApi\",\"sampling\":5000},{\"monitorPoint\":\"SynconfigUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"NotifyConfigChanged\",\"sampling\":1},{\"monitorPoint\":\"RegisterToken\",\"sampling\":1},{\"monitorPoint\":\"SyncReferFromCdn\",\"sampling\":5000},{\"monitorPoint\":\"XcmdUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"SyncXcommandFromCdn\",\"sampling\":5000},{\"monitorPoint\":\"SaveConfigError\",\"sampling\":5000},{\"monitorPoint\":\"ParseTokenError\",\"sampling\":5000},{\"monitorPoint\":\"NotifyLoadConfig\",\"sampling\":5000}]},{\"module\":\"ACDS\",\"sampling\":500},{\"module\":\"Page_hotpatch\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hotpatch\",\"sampling\":500}]}]},\"ALARM\":{\"sampling\":0,\"successSampling\":0,\"failSampling\":0,\"metrics\":[{\"module\":\"Page_Home\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"HomeItemLoad\",\"sampling\":5000},{\"monitorPoint\":\"HomeNetwork\",\"sampling\":5000}]},{\"module\":\"Page_Detail\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"LoadDetail\",\"sampling\":5000},{\"monitorPoint\":\"LoadDesc\",\"sampling\":5000}]},{\"module\":\"Page_SearchItemList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchItem\",\"sampling\":5000}]},{\"module\":\"Page_Shop\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadShop\",\"sampling\":5000}]},{\"module\":\"Page_AutoLogin\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"autoLogin\",\"sampling\":5000}]},{\"module\":\"Page_Shop_Search_List\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"searchShopList\",\"sampling\":5000}]},{\"module\":\"Page_ShoppingCart\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"QueryData\",\"sampling\":5000},{\"monitorPoint\":\"ShowCart\",\"sampling\":5000},{\"monitorPoint\":\"UpdateCart\",\"sampling\":5000}]},{\"module\":\"Page_Pay\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"Alipay\",\"sampling\":5000}]},{\"module\":\"Page_ShowOrder\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"ShowOrder\",\"sampling\":5000},{\"monitorPoint\":\"CreateOrder\",\"sampling\":5000}]},{\"module\":\"Page_Attention\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadAttention\",\"sampling\":5000},{\"monitorPoint\":\"refreshAttention\",\"sampling\":5000}]},{\"module\":\"Page_SearchShopList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchShop\",\"sampling\":5000}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"NativeError\",\"sampling\":2500},{\"monitorPoint\":\"AllPackageApps\",\"sampling\":2500},{\"monitorPoint\":\"Config\",\"sampling\":2500},{\"monitorPoint\":\"JavaScriptError\",\"sampling\":2500},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000}]},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"domModule\",\"sampling\":5000},{\"monitorPoint\":\"enviroment\",\"sampling\":5000},{\"monitorPoint\":\"jsBridge\",\"sampling\":5000},{\"monitorPoint\":\"streamModule\",\"sampling\":5000}]},{\"module\":\"ACDS\",\"sampling\":500,\"monitorPoints\":[{\"monitorPoint\":\"huichang\",\"sampling\":0}]},{\"module\":\"Page_HuiChangTab\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcconfig\",\"sampling\":500}]},{\"module\":\"Page_2015MainStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":500},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":500},{\"monitorPoint\":\"hcmaindegrade\",\"sampling\":5000}]},{\"module\":\"Page_2015SubStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":500},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":500},{\"monitorPoint\":\"hcsubdegrade\",\"sampling\":5000}]}]}}";
        }
        if ("l3".equalsIgnoreCase(com.alibaba.mtl.log.a.a.T)) {
            com.alibaba.mtl.log.d.i.a("Config", new Object[] { com.alibaba.mtl.log.a.a.T, "l3 config" });
            return "{\"SYSTEM\":{\"useut\":\"0\",\"bg_interval\":600000,\"fg_interval\":60000},\"STAT\":{\"sampling\":0,\"metrics\":[{\"module\":\"networkPrefer\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"network\",\"sampling\":10},{\"monitorPoint\":\"NetworkError\",\"sampling\":10000}]},{\"module\":\"mtopsdk\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"mtopStats\",\"sampling\":10},{\"monitorPoint\":\"mtopExceptions\",\"sampling\":10},{\"monitorPoint\":\"uploadStats\",\"sampling\":10000}]},{\"module\":\"ImageLib\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"ImageFlow\",\"sampling\":50},{\"monitorPoint\":\"ImageExceptions\",\"sampling\":50}]},{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"netperformance\",\"sampling\":10}]},{\"module\":\"ACDS\",\"sampling\":1000,\"monitorPoints\":[{\"monitorPoint\":\"initdata\",\"sampling\":20}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"AllPackageApps\",\"sampling\":10000},{\"monitorPoint\":\"Config\",\"sampling\":5000},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000},{\"monitorPoint\":\"H5\",\"sampling\":1},{\"monitorPoint\":\"WebViewVisit\",\"sampling\":5000}]},{\"module\":\"order\",\"sampling\":10000,\"monitorPoints\":[{\"monitorPoint\":\"Page_OrderList\",\"sampling\":500},{\"monitorPoint\":\"Page_OrderDetail\",\"sampling\":1000}]},{\"module\":\"wopc\",\"sampling\":10000},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"load\",\"sampling\":10000}]},{\"module\":\"NetworkSDK\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"TrafficStats\",\"sampling\":20}]}]},\"COUNTER\":{\"sampling\":0,\"cacheCount\":60,\"metrics\":[{\"module\":\"accs\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"to_buss\",\"sampling\":1},{\"monitorPoint\":\"resend\",\"sampling\":1}]},{\"module\":\"configCenter\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SyncConfigsFromApi\",\"sampling\":10000},{\"monitorPoint\":\"SynconfigUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"NotifyConfigChanged\",\"sampling\":1},{\"monitorPoint\":\"RegisterToken\",\"sampling\":1},{\"monitorPoint\":\"SyncReferFromCdn\",\"sampling\":10000},{\"monitorPoint\":\"XcmdUpdateConfig\",\"sampling\":1},{\"monitorPoint\":\"SyncXcommandFromCdn\",\"sampling\":10000},{\"monitorPoint\":\"SaveConfigError\",\"sampling\":10000},{\"monitorPoint\":\"ParseTokenError\",\"sampling\":10000},{\"monitorPoint\":\"NotifyLoadConfig\",\"sampling\":10000}]},{\"module\":\"ACDS\",\"sampling\":1000},{\"module\":\"Page_hotpatch\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hotpatch\",\"sampling\":1000}]}]},\"ALARM\":{\"sampling\":0,\"successSampling\":0,\"failSampling\":0,\"metrics\":[{\"module\":\"Page_Home\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"HomeItemLoad\",\"sampling\":10000},{\"monitorPoint\":\"HomeNetwork\",\"sampling\":10000}]},{\"module\":\"Page_Detail\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"LoadDetail\",\"sampling\":10000},{\"monitorPoint\":\"LoadDesc\",\"sampling\":10000}]},{\"module\":\"Page_SearchItemList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchItem\",\"sampling\":10000}]},{\"module\":\"Page_Shop\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadShop\",\"sampling\":10000}]},{\"module\":\"Page_AutoLogin\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"autoLogin\",\"sampling\":10000}]},{\"module\":\"Page_Shop_Search_List\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"searchShopList\",\"sampling\":10000}]},{\"module\":\"Page_ShoppingCart\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"QueryData\",\"sampling\":10000},{\"monitorPoint\":\"ShowCart\",\"sampling\":10000},{\"monitorPoint\":\"UpdateCart\",\"sampling\":10000}]},{\"module\":\"Page_Pay\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"Alipay\",\"sampling\":10000}]},{\"module\":\"Page_ShowOrder\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"ShowOrder\",\"sampling\":10000},{\"monitorPoint\":\"CreateOrder\",\"sampling\":10000}]},{\"module\":\"Page_Attention\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"loadAttention\",\"sampling\":10000},{\"monitorPoint\":\"refreshAttention\",\"sampling\":10000}]},{\"module\":\"Page_SearchShopList\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"SearchShop\",\"sampling\":10000}]},{\"module\":\"WindVane\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"NativeError\",\"sampling\":5000},{\"monitorPoint\":\"AllPackageApps\",\"sampling\":5000},{\"monitorPoint\":\"Config\",\"sampling\":5000},{\"monitorPoint\":\"JavaScriptError\",\"sampling\":5000},{\"monitorPoint\":\"PackageApp\",\"sampling\":2000}]},{\"module\":\"WeApp+\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"domModule\",\"sampling\":10000},{\"monitorPoint\":\"enviroment\",\"sampling\":10000},{\"monitorPoint\":\"jsBridge\",\"sampling\":10000},{\"monitorPoint\":\"streamModule\",\"sampling\":10000}]},{\"module\":\"ACDS\",\"sampling\":1000,\"monitorPoints\":[{\"monitorPoint\":\"huichang\",\"sampling\":0}]},{\"module\":\"Page_HuiChangTab\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcconfig\",\"sampling\":1000}]},{\"module\":\"Page_2015MainStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":1000},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":1000},{\"monitorPoint\":\"hcmaindegrade\",\"sampling\":10000}]},{\"module\":\"Page_2015SubStage\",\"sampling\":0,\"monitorPoints\":[{\"monitorPoint\":\"hcjs_cdn\",\"sampling\":1000},{\"monitorPoint\":\"hcjs_mtop\",\"sampling\":1000},{\"monitorPoint\":\"hcsubdegrade\",\"sampling\":10000}]}]}}";
        }
        com.alibaba.mtl.log.d.i.a("Config", new Object[] { com.alibaba.mtl.log.a.a.T, "net config" });
        final String a = a("b01na");
        if (a != null) {
            try {
                final JSONObject jsonObject = new JSONObject(a).getJSONObject("content");
                if (jsonObject != null) {
                    return jsonObject.getString("c_content");
                }
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public static void h(final String s) {
        if (!com.alibaba.mtl.log.a.a.D) {
            final int b = b(s);
            if (b == -1) {
                return;
            }
            com.alibaba.mtl.log.a.a.c = b * 1000;
        }
    }
    
    public static void i(final String s) {
        if (!com.alibaba.mtl.log.a.a.C) {
            final int b = b(s);
            if (b == -1) {
                return;
            }
            com.alibaba.mtl.log.a.a.d = b * 1000;
        }
    }
    
    public static void p() {
        try {
            final String h = h();
            com.alibaba.mtl.appmonitor.d.j.a().b(h);
            e.j(h);
            com.alibaba.mtl.log.a.d.a().b(a("b01n15"));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void turnOnRealTimeDebug(final Map<String, String> map) {
        com.alibaba.mtl.log.d.i.a("Config", new Object[] { "[turnOnRealTimeDebug] params:", map });
        if (map != null) {
            final String q = (String)map.get((Object)"debug_api_url");
            final String s = (String)map.get((Object)"debug_key");
            if (!TextUtils.isEmpty((CharSequence)q) && !TextUtils.isEmpty((CharSequence)s)) {
                com.alibaba.mtl.log.a.a.Q = q;
                com.alibaba.mtl.log.a.a.A = true;
                com.alibaba.mtl.log.a.a.S = s;
            }
            if (map.containsKey((Object)"debug_sampling_option")) {
                AppMonitorDelegate.IS_DEBUG = true;
                com.alibaba.mtl.log.a.m();
            }
        }
    }
}
