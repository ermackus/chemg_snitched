package com.github.lzyzsd.jsbridge;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class Message
{
    private static final String CALLBACK_ID_STR = "callbackId";
    private static final String DATA_STR = "data";
    private static final String HANDLER_NAME_STR = "handlerName";
    private static final String RESPONSE_DATA_STR = "responseData";
    private static final String RESPONSE_ID_STR = "responseId";
    private String callbackId;
    private String data;
    private String handlerName;
    private String responseData;
    private String responseId;
    
    public static List<Message> toArrayList(String data) {
        final ArrayList list = new ArrayList();
        try {
            final JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); ++i) {
                final Message message = new Message();
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final boolean has = jsonObject.has("handlerName");
                final String s = null;
                if (has) {
                    data = jsonObject.getString("handlerName");
                }
                else {
                    data = null;
                }
                message.setHandlerName(data);
                if (jsonObject.has("callbackId")) {
                    data = jsonObject.getString("callbackId");
                }
                else {
                    data = null;
                }
                message.setCallbackId(data);
                if (jsonObject.has("responseData")) {
                    data = jsonObject.getString("responseData");
                }
                else {
                    data = null;
                }
                message.setResponseData(data);
                if (jsonObject.has("responseId")) {
                    data = jsonObject.getString("responseId");
                }
                else {
                    data = null;
                }
                message.setResponseId(data);
                data = s;
                if (jsonObject.has("data")) {
                    data = jsonObject.getString("data");
                }
                message.setData(data);
                ((List)list).add((Object)message);
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return (List<Message>)list;
    }
    
    public static Message toObject(String data) {
        final Message message = new Message();
        try {
            final JSONObject jsonObject = new JSONObject(data);
            final boolean has = jsonObject.has("handlerName");
            final String s = null;
            if (has) {
                data = jsonObject.getString("handlerName");
            }
            else {
                data = null;
            }
            message.setHandlerName(data);
            if (jsonObject.has("callbackId")) {
                data = jsonObject.getString("callbackId");
            }
            else {
                data = null;
            }
            message.setCallbackId(data);
            if (jsonObject.has("responseData")) {
                data = jsonObject.getString("responseData");
            }
            else {
                data = null;
            }
            message.setResponseData(data);
            if (jsonObject.has("responseId")) {
                data = jsonObject.getString("responseId");
            }
            else {
                data = null;
            }
            message.setResponseId(data);
            data = s;
            if (jsonObject.has("data")) {
                data = jsonObject.getString("data");
            }
            message.setData(data);
            return message;
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
            return message;
        }
    }
    
    public String getCallbackId() {
        return this.callbackId;
    }
    
    public String getData() {
        return this.data;
    }
    
    public String getHandlerName() {
        return this.handlerName;
    }
    
    public String getResponseData() {
        return this.responseData;
    }
    
    public String getResponseId() {
        return this.responseId;
    }
    
    public void setCallbackId(final String callbackId) {
        this.callbackId = callbackId;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public void setHandlerName(final String handlerName) {
        this.handlerName = handlerName;
    }
    
    public void setResponseData(final String responseData) {
        this.responseData = responseData;
    }
    
    public void setResponseId(final String responseId) {
        this.responseId = responseId;
    }
    
    public String toJson() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("callbackId", (Object)this.getCallbackId());
            jsonObject.put("data", (Object)this.getData());
            jsonObject.put("handlerName", (Object)this.getHandlerName());
            jsonObject.put("responseData", (Object)this.getResponseData());
            jsonObject.put("responseId", (Object)this.getResponseId());
            return jsonObject.toString();
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
