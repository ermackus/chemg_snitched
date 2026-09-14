package org.eclipse.paho.android.service;

import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.UUID;
import android.content.ContentValues;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Iterator;
import android.database.SQLException;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class DatabaseMessageStore implements MessageStore
{
    private static final String ARRIVED_MESSAGE_TABLE_NAME = "MqttArrivedMessageTable";
    private static final String MTIMESTAMP = "mtimestamp";
    private static final String TAG = "DatabaseMessageStore";
    private SQLiteDatabase db;
    private MQTTDatabaseHelper mqttDb;
    private MqttTraceHandler traceHandler;
    
    public DatabaseMessageStore(final MqttService traceHandler, final Context context) {
        this.db = null;
        this.mqttDb = null;
        this.traceHandler = null;
        this.traceHandler = traceHandler;
        this.mqttDb = new MQTTDatabaseHelper(this.traceHandler, context);
        this.traceHandler.traceDebug("DatabaseMessageStore", "DatabaseMessageStore<init> complete");
    }
    
    private int getArrivedRowCount(final String s) {
        int int1 = 0;
        final Cursor query = this.db.query("MqttArrivedMessageTable", new String[] { "messageId" }, "clientHandle=?", new String[] { s }, (String)null, (String)null, (String)null);
        if (query.moveToFirst()) {
            int1 = query.getInt(0);
        }
        query.close();
        return int1;
    }
    
    @Override
    public void clearArrivedMessages(final String s) {
        this.db = this.mqttDb.getWritableDatabase();
        int n;
        if (s == null) {
            this.traceHandler.traceDebug("DatabaseMessageStore", "clearArrivedMessages: clearing the table");
            n = this.db.delete("MqttArrivedMessageTable", (String)null, (String[])null);
        }
        else {
            final MqttTraceHandler traceHandler = this.traceHandler;
            final StringBuilder sb = new StringBuilder();
            sb.append("clearArrivedMessages: clearing the table of ");
            sb.append(s);
            sb.append(" messages");
            traceHandler.traceDebug("DatabaseMessageStore", sb.toString());
            n = this.db.delete("MqttArrivedMessageTable", "clientHandle=?", new String[] { s });
        }
        final MqttTraceHandler traceHandler2 = this.traceHandler;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("clearArrivedMessages: rows affected = ");
        sb2.append(n);
        traceHandler2.traceDebug("DatabaseMessageStore", sb2.toString());
    }
    
    @Override
    public void close() {
        final SQLiteDatabase db = this.db;
        if (db != null) {
            db.close();
        }
    }
    
    @Override
    public boolean discardArrived(final String s, final String s2) {
        this.db = this.mqttDb.getWritableDatabase();
        final MqttTraceHandler traceHandler = this.traceHandler;
        final StringBuilder sb = new StringBuilder();
        sb.append("discardArrived{");
        sb.append(s);
        sb.append("}, {");
        sb.append(s2);
        sb.append("}");
        traceHandler.traceDebug("DatabaseMessageStore", sb.toString());
        try {
            final int delete = this.db.delete("MqttArrivedMessageTable", "messageId=? AND clientHandle=?", new String[] { s2, s });
            if (delete != 1) {
                final MqttTraceHandler traceHandler2 = this.traceHandler;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("discardArrived - Error deleting message {");
                sb2.append(s2);
                sb2.append("} from database: Rows affected = ");
                sb2.append(delete);
                traceHandler2.traceError("DatabaseMessageStore", sb2.toString());
                return false;
            }
            final int arrivedRowCount = this.getArrivedRowCount(s);
            final MqttTraceHandler traceHandler3 = this.traceHandler;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("discardArrived - Message deleted successfully. - messages in db for this clientHandle ");
            sb3.append(arrivedRowCount);
            traceHandler3.traceDebug("DatabaseMessageStore", sb3.toString());
            return true;
        }
        catch (final SQLException ex) {
            this.traceHandler.traceException("DatabaseMessageStore", "discardArrived", (Exception)ex);
            throw ex;
        }
    }
    
    @Override
    public Iterator<StoredMessage> getAllArrivedMessages(final String s) {
        return (Iterator<StoredMessage>)new Iterator<StoredMessage>(this, s) {
            private Cursor c;
            private boolean hasNext;
            private final String[] selectionArgs = { this.val$clientHandle };
            final DatabaseMessageStore this$0;
            final String val$clientHandle;
            
            {
                this$0 = this.this$0;
                this$0.db = this$0.mqttDb.getWritableDatabase();
                if (this.val$clientHandle == null) {
                    this.c = this.this$0.db.query("MqttArrivedMessageTable", (String[])null, (String)null, (String[])null, (String)null, (String)null, "mtimestamp ASC");
                }
                else {
                    this.c = this.this$0.db.query("MqttArrivedMessageTable", (String[])null, "clientHandle=?", this.selectionArgs, (String)null, (String)null, "mtimestamp ASC");
                }
                this.hasNext = this.c.moveToFirst();
            }
            
            @Override
            protected void finalize() throws Throwable {
                this.c.close();
                super.finalize();
            }
            
            public boolean hasNext() {
                if (!this.hasNext) {
                    this.c.close();
                }
                return this.hasNext;
            }
            
            public StoredMessage next() {
                final Cursor c = this.c;
                final String string = c.getString(c.getColumnIndex("messageId"));
                final Cursor c2 = this.c;
                final String string2 = c2.getString(c2.getColumnIndex("clientHandle"));
                final Cursor c3 = this.c;
                final String string3 = c3.getString(c3.getColumnIndex("destinationName"));
                final Cursor c4 = this.c;
                final byte[] blob = c4.getBlob(c4.getColumnIndex("payload"));
                final Cursor c5 = this.c;
                final int int1 = c5.getInt(c5.getColumnIndex("qos"));
                final Cursor c6 = this.c;
                final boolean boolean1 = Boolean.parseBoolean(c6.getString(c6.getColumnIndex("retained")));
                final Cursor c7 = this.c;
                final boolean boolean2 = Boolean.parseBoolean(c7.getString(c7.getColumnIndex("duplicate")));
                final MqttMessageHack mqttMessageHack = this.this$0.new MqttMessageHack(blob);
                mqttMessageHack.setQos(int1);
                mqttMessageHack.setRetained(boolean1);
                mqttMessageHack.setDuplicate(boolean2);
                this.hasNext = this.c.moveToNext();
                return this.this$0.new DbStoredData(string, string2, string3, mqttMessageHack);
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public String storeArrived(final String s, final String s2, final MqttMessage mqttMessage) {
        this.db = this.mqttDb.getWritableDatabase();
        final MqttTraceHandler traceHandler = this.traceHandler;
        final StringBuilder sb = new StringBuilder();
        sb.append("storeArrived{");
        sb.append(s);
        sb.append("}, {");
        sb.append(mqttMessage.toString());
        sb.append("}");
        traceHandler.traceDebug("DatabaseMessageStore", sb.toString());
        final byte[] payload = mqttMessage.getPayload();
        final int qos = mqttMessage.getQos();
        final boolean retained = mqttMessage.isRetained();
        final boolean duplicate = mqttMessage.isDuplicate();
        final ContentValues contentValues = new ContentValues();
        final String string = UUID.randomUUID().toString();
        contentValues.put("messageId", string);
        contentValues.put("clientHandle", s);
        contentValues.put("destinationName", s2);
        contentValues.put("payload", payload);
        contentValues.put("qos", Integer.valueOf(qos));
        contentValues.put("retained", Boolean.valueOf(retained));
        contentValues.put("duplicate", Boolean.valueOf(duplicate));
        contentValues.put("mtimestamp", Long.valueOf(System.currentTimeMillis()));
        try {
            this.db.insertOrThrow("MqttArrivedMessageTable", (String)null, contentValues);
            final int arrivedRowCount = this.getArrivedRowCount(s);
            final MqttTraceHandler traceHandler2 = this.traceHandler;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("storeArrived: inserted message with id of {");
            sb2.append(string);
            sb2.append("} - Number of messages in database for this clientHandle = ");
            sb2.append(arrivedRowCount);
            traceHandler2.traceDebug("DatabaseMessageStore", sb2.toString());
            return string;
        }
        catch (final SQLException ex) {
            this.traceHandler.traceException("DatabaseMessageStore", "onUpgrade", (Exception)ex);
            throw ex;
        }
    }
    
    private class DbStoredData implements StoredMessage
    {
        private String clientHandle;
        private MqttMessage message;
        private String messageId;
        final DatabaseMessageStore this$0;
        private String topic;
        
        DbStoredData(final DatabaseMessageStore this$0, final String messageId, final String s, final String topic, final MqttMessage message) {
            this.this$0 = this$0;
            this.messageId = messageId;
            this.topic = topic;
            this.message = message;
        }
        
        @Override
        public String getClientHandle() {
            return this.clientHandle;
        }
        
        @Override
        public MqttMessage getMessage() {
            return this.message;
        }
        
        @Override
        public String getMessageId() {
            return this.messageId;
        }
        
        @Override
        public String getTopic() {
            return this.topic;
        }
    }
    
    private static class MQTTDatabaseHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "mqttAndroidService.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TAG = "MQTTDatabaseHelper";
        private MqttTraceHandler traceHandler;
        
        public MQTTDatabaseHelper(final MqttTraceHandler traceHandler, final Context context) {
            super(context, "mqttAndroidService.db", (SQLiteDatabase$CursorFactory)null, 1);
            this.traceHandler = null;
            this.traceHandler = traceHandler;
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            final MqttTraceHandler traceHandler = this.traceHandler;
            final StringBuilder sb = new StringBuilder();
            sb.append("onCreate {");
            sb.append("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
            sb.append("}");
            traceHandler.traceDebug("MQTTDatabaseHelper", sb.toString());
            try {
                sqLiteDatabase.execSQL("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
                this.traceHandler.traceDebug("MQTTDatabaseHelper", "created the table");
            }
            catch (final SQLException ex) {
                this.traceHandler.traceException("MQTTDatabaseHelper", "onCreate", (Exception)ex);
                throw ex;
            }
        }
        
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
            this.traceHandler.traceDebug("MQTTDatabaseHelper", "onUpgrade");
            try {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MqttArrivedMessageTable");
                this.onCreate(sqLiteDatabase);
                this.traceHandler.traceDebug("MQTTDatabaseHelper", "onUpgrade complete");
            }
            catch (final SQLException ex) {
                this.traceHandler.traceException("MQTTDatabaseHelper", "onUpgrade", (Exception)ex);
                throw ex;
            }
        }
    }
    
    private class MqttMessageHack extends MqttMessage
    {
        final DatabaseMessageStore this$0;
        
        public MqttMessageHack(final DatabaseMessageStore this$0, final byte[] array) {
            this.this$0 = this$0;
            super(array);
        }
        
        @Override
        protected void setDuplicate(final boolean duplicate) {
            super.setDuplicate(duplicate);
        }
    }
}
