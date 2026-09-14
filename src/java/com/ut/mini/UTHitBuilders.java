package com.ut.mini;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.Map;

public class UTHitBuilders
{
    public static class UTHitBuilder
    {
        public static final String FIELD_ARG1 = "_field_arg1";
        public static final String FIELD_ARG2 = "_field_arg2";
        public static final String FIELD_ARG3 = "_field_arg3";
        public static final String FIELD_ARGS = "_field_args";
        public static final String FIELD_EVENT_ID = "_field_event_id";
        public static final String FIELD_PAGE = "_field_page";
        private Map<String, String> y;
        
        public UTHitBuilder() {
            final HashMap y = new HashMap();
            this.y = (Map<String, String>)y;
            if (!((Map)y).containsKey((Object)"_field_page")) {
                this.y.put((Object)"_field_page", (Object)"UT");
            }
        }
        
        private static boolean a(final Map<String, String> map) {
            if (map != null) {
                if (map.containsKey((Object)null)) {
                    map.remove((Object)null);
                }
                if (map.containsKey((Object)"")) {
                    map.remove((Object)"");
                }
                if (map.containsKey((Object)LogField.PAGE.toString())) {
                    i.a("checkIlleagleProperty", (Object)"IlleaglePropertyKey(PAGE) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
                if (map.containsKey((Object)LogField.EVENTID.toString())) {
                    i.a("checkIlleagleProperty", (Object)"IlleaglePropertyKey(EVENTID) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
                if (map.containsKey((Object)LogField.ARG1.toString())) {
                    i.a("checkIlleagleProperty", (Object)"IlleaglePropertyKey(ARG1) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
                if (map.containsKey((Object)LogField.ARG2.toString())) {
                    i.a("checkIlleagleProperty", (Object)"IlleaglePropertyKey(ARG2) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
                if (map.containsKey((Object)LogField.ARG3.toString())) {
                    i.a("checkIlleagleProperty", (Object)"IlleaglePropertyKey(ARG3) is setted when you call the method setProperty or setProperties ,please use another key to replace it!");
                    return false;
                }
            }
            return true;
        }
        
        private static void d(final Map<String, String> map) {
            if (map != null) {
                if (map.containsKey((Object)"_field_page")) {
                    final String s = (String)map.get((Object)"_field_page");
                    map.remove((Object)"_field_page");
                    map.put((Object)LogField.PAGE.toString(), (Object)s);
                }
                if (map.containsKey((Object)"_field_arg1")) {
                    final String s2 = (String)map.get((Object)"_field_arg1");
                    map.remove((Object)"_field_arg1");
                    map.put((Object)LogField.ARG1.toString(), (Object)s2);
                }
                if (map.containsKey((Object)"_field_arg2")) {
                    final String s3 = (String)map.get((Object)"_field_arg2");
                    map.remove((Object)"_field_arg2");
                    map.put((Object)LogField.ARG2.toString(), (Object)s3);
                }
                if (map.containsKey((Object)"_field_arg3")) {
                    final String s4 = (String)map.get((Object)"_field_arg3");
                    map.remove((Object)"_field_arg3");
                    map.put((Object)LogField.ARG3.toString(), (Object)s4);
                }
                if (map.containsKey((Object)"_field_args")) {
                    final String s5 = (String)map.get((Object)"_field_args");
                    map.remove((Object)"_field_args");
                    map.put((Object)LogField.ARGS.toString(), (Object)s5);
                }
                if (map.containsKey((Object)"_field_event_id")) {
                    final String s6 = (String)map.get((Object)"_field_event_id");
                    map.remove((Object)"_field_event_id");
                    map.put((Object)LogField.EVENTID.toString(), (Object)s6);
                }
            }
        }
        
        private static void e(final Map<String, String> map) {
            if (map != null) {
                if (map.containsKey((Object)LogField.PAGE.toString())) {
                    map.remove((Object)LogField.PAGE.toString());
                }
                if (map.containsKey((Object)LogField.EVENTID.toString())) {
                    map.remove((Object)LogField.EVENTID.toString());
                }
                if (map.containsKey((Object)LogField.ARG1.toString())) {
                    map.remove((Object)LogField.ARG1.toString());
                }
                if (map.containsKey((Object)LogField.ARG2.toString())) {
                    map.remove((Object)LogField.ARG2.toString());
                }
                if (map.containsKey((Object)LogField.ARG3.toString())) {
                    map.remove((Object)LogField.ARG3.toString());
                }
                if (map.containsKey((Object)LogField.ARGS.toString())) {
                    map.remove((Object)LogField.ARGS.toString());
                }
            }
        }
        
        public Map<String, String> build() {
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).putAll((Map)this.y);
            if (!a((Map<String, String>)hashMap)) {
                return null;
            }
            e((Map<String, String>)hashMap);
            d((Map<String, String>)hashMap);
            if (!((Map)hashMap).containsKey((Object)LogField.EVENTID.toString())) {
                return null;
            }
            return (Map<String, String>)hashMap;
        }
        
        public String getProperty(final String s) {
            if (s != null && this.y.containsKey((Object)s)) {
                return (String)this.y.get((Object)s);
            }
            return null;
        }
        
        public UTHitBuilder setProperties(final Map<String, String> map) {
            if (map != null) {
                this.y.putAll((Map)map);
            }
            return this;
        }
        
        public UTHitBuilder setProperty(final String s, final String s2) {
            if (!TextUtils.isEmpty((CharSequence)s) && s2 != null) {
                if (this.y.containsKey((Object)s)) {
                    this.y.remove((Object)s);
                }
                this.y.put((Object)s, (Object)s2);
            }
            else {
                i.a("setProperty", (Object)"key is null or key is empty or value is null,please check it!");
            }
            return this;
        }
    }
}
