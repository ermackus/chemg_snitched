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

public class IUPACConvertModel
{
    public String json;
    public String kid;
    
    public static List<IUPACConvertModel> parseConvertJson(String nextName) {
        final ArrayList list = new ArrayList();
        try {
            final JsonReader jsonReader = new JsonReader((Reader)new StringReader(nextName));
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                final IUPACConvertModel iupacConvertModel = new IUPACConvertModel();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    nextName = jsonReader.nextName();
                    if (nextName.equals((Object)"kid")) {
                        iupacConvertModel.kid = jsonReader.nextString();
                    }
                    else {
                        if (!nextName.equals((Object)"json")) {
                            continue;
                        }
                        iupacConvertModel.json = jsonReader.nextString();
                    }
                }
                jsonReader.endObject();
                ((List)list).add((Object)iupacConvertModel);
            }
            jsonReader.endArray();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return (List<IUPACConvertModel>)list;
    }
    
    public static String parseConvertModels(final List<IUPACConvertModel> list) {
        final StringWriter stringWriter = new StringWriter();
        final JsonWriter jsonWriter = new JsonWriter((Writer)stringWriter);
        try {
            jsonWriter.beginArray();
            for (final IUPACConvertModel iupacConvertModel : list) {
                jsonWriter.beginObject();
                jsonWriter.name("kid");
                jsonWriter.value(iupacConvertModel.kid);
                jsonWriter.name("json");
                jsonWriter.value(iupacConvertModel.json);
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
