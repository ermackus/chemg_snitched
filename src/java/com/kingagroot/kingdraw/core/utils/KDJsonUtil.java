package com.kingagroot.kingdraw.core.utils;

import org.json.JSONException;
import com.kingagroot.kingdraw.core.model.ModelUtils;
import android.text.TextUtils;
import org.json.JSONObject;
import com.kingagroot.kingdraw.core.model.FormatValue;

public class KDJsonUtil
{
    public static FormatValue getFormatValue(String string) {
        try {
            final JSONObject jsonObject = new JSONObject(string);
            if (jsonObject.has("format")) {
                string = jsonObject.getString("format");
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    return ModelUtils.formJson(string, FormatValue.class);
                }
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
