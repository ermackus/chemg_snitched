package com.kingagroot.kingdraw.core.model;

import java.util.Iterator;
import java.io.Writer;
import android.util.JsonWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.Reader;
import android.util.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ChirlityConvertModel
{
    public String ChirlityJson;
    public String Kid;
    public String StructJson;
    
    public static List<ChirlityConvertModel> parseConvertJson(final String s) {
        final ArrayList list = new ArrayList();
        try {
            final JsonReader jsonReader = new JsonReader((Reader)new StringReader(s));
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                final ChirlityConvertModel chirlityConvertModel = new ChirlityConvertModel();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    final String nextName = jsonReader.nextName();
                    if (nextName.equals((Object)"Kid")) {
                        chirlityConvertModel.Kid = jsonReader.nextString();
                    }
                    else if (nextName.equals((Object)"StructJson")) {
                        chirlityConvertModel.StructJson = jsonReader.nextString();
                    }
                    else {
                        if (!nextName.equals((Object)"ChirlityJson")) {
                            continue;
                        }
                        chirlityConvertModel.ChirlityJson = jsonReader.nextString();
                    }
                }
                jsonReader.endObject();
                ((List)list).add((Object)chirlityConvertModel);
            }
            jsonReader.endArray();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return (List<ChirlityConvertModel>)list;
    }
    
    public static String parseConvertModels(final List<ChirlityConvertModel> list) {
        final StringWriter stringWriter = new StringWriter();
        final JsonWriter jsonWriter = new JsonWriter((Writer)stringWriter);
        try {
            jsonWriter.beginArray();
            for (final ChirlityConvertModel chirlityConvertModel : list) {
                jsonWriter.beginObject();
                jsonWriter.name("Kid");
                jsonWriter.value(chirlityConvertModel.Kid);
                jsonWriter.name("StructJson");
                jsonWriter.value(chirlityConvertModel.StructJson);
                jsonWriter.name("ChirlityJson");
                jsonWriter.value(chirlityConvertModel.ChirlityJson);
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return stringWriter.toString();
    }
}
