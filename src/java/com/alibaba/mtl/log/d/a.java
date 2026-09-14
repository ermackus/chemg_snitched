package com.alibaba.mtl.log.d;

import org.json.JSONException;
import android.text.TextUtils;
import org.json.JSONObject;

public class a
{
    public static a a(String string) {
        final a a = new a();
        try {
            final JSONObject jsonObject = new JSONObject(string);
            if (jsonObject.has("success")) {
                string = jsonObject.getString("success");
                if (!TextUtils.isEmpty((CharSequence)string) && string.equals((Object)"success")) {
                    a.I = true;
                }
            }
            if (jsonObject.has("ret")) {
                a.ah = jsonObject.getString("ret");
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    
    public static class a
    {
        public static a a;
        public boolean I;
        public String ah;
        
        static {
            com.alibaba.mtl.log.d.a.a.a = new a();
        }
        
        public a() {
            this.I = false;
            this.ah = null;
        }
        
        public boolean g() {
            return "E0102".equalsIgnoreCase(this.ah);
        }
        
        public boolean h() {
            return "E0111".equalsIgnoreCase(this.ah) || "E0112".equalsIgnoreCase(this.ah);
        }
    }
}
