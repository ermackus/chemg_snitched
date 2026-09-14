package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ParcelableMqttMessage extends MqttMessage implements Parcelable
{
    public static final Parcelable$Creator<ParcelableMqttMessage> CREATOR;
    String messageId;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ParcelableMqttMessage>() {
            public ParcelableMqttMessage createFromParcel(final Parcel parcel) {
                return new ParcelableMqttMessage(parcel);
            }
            
            public ParcelableMqttMessage[] newArray(final int n) {
                return new ParcelableMqttMessage[n];
            }
        };
    }
    
    ParcelableMqttMessage(final Parcel parcel) {
        super(parcel.createByteArray());
        this.messageId = null;
        this.setQos(parcel.readInt());
        final boolean[] booleanArray = parcel.createBooleanArray();
        this.setRetained(booleanArray[0]);
        this.setDuplicate(booleanArray[1]);
        this.messageId = parcel.readString();
    }
    
    ParcelableMqttMessage(final MqttMessage mqttMessage) {
        super(mqttMessage.getPayload());
        this.messageId = null;
        this.setQos(mqttMessage.getQos());
        this.setRetained(mqttMessage.isRetained());
        this.setDuplicate(mqttMessage.isDuplicate());
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getMessageId() {
        return this.messageId;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeByteArray(this.getPayload());
        parcel.writeInt(this.getQos());
        parcel.writeBooleanArray(new boolean[] { this.isRetained(), this.isDuplicate() });
        parcel.writeString(this.messageId);
    }
}
