package com.alipay.sdk.m.u;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
    public static JSONObject a(final JSONObject jsonObject, final JSONObject jsonObject2) {
        final JSONObject jsonObject3 = new JSONObject();
        int i = 0;
        while (i < 2) {
            try {
                final JSONObject jsonObject4 = (new JSONObject[] { jsonObject, jsonObject2 })[i];
                if (jsonObject4 != null) {
                    final Iterator keys = jsonObject4.keys();
                    while (keys.hasNext()) {
                        final String s = (String)keys.next();
                        jsonObject3.put(s, jsonObject4.get(s));
                    }
                }
                ++i;
                continue;
            }
            catch (final JSONException ex) {
                e.a((Throwable)ex);
            }
            break;
        }
        return jsonObject3;
    }
}
