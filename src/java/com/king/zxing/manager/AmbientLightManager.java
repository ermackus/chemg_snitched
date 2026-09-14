package com.king.zxing.manager;

import android.hardware.SensorEvent;
import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;

public class AmbientLightManager implements SensorEventListener
{
    protected static final float BRIGHT_LUX = 100.0f;
    protected static final float DARK_LUX = 45.0f;
    private static final int INTERVAL_TIME = 200;
    private float brightLightLux;
    private float darkLightLux;
    private boolean isLightSensorEnabled;
    private long lastTime;
    private Sensor lightSensor;
    private OnLightSensorEventListener mOnLightSensorEventListener;
    private SensorManager sensorManager;
    
    public AmbientLightManager(final Context context) {
        this.darkLightLux = 45.0f;
        this.brightLightLux = 100.0f;
        final SensorManager sensorManager = (SensorManager)context.getSystemService("sensor");
        this.sensorManager = sensorManager;
        this.lightSensor = sensorManager.getDefaultSensor(5);
        this.isLightSensorEnabled = true;
    }
    
    public boolean isLightSensorEnabled() {
        return this.isLightSensorEnabled;
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent sensorEvent) {
        if (this.isLightSensorEnabled) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastTime < 200L) {
                return;
            }
            this.lastTime = currentTimeMillis;
            if (this.mOnLightSensorEventListener != null) {
                final float n = sensorEvent.values[0];
                this.mOnLightSensorEventListener.onSensorChanged(n);
                if (n <= this.darkLightLux) {
                    this.mOnLightSensorEventListener.onSensorChanged(true, n);
                }
                else if (n >= this.brightLightLux) {
                    this.mOnLightSensorEventListener.onSensorChanged(false, n);
                }
            }
        }
    }
    
    public void register() {
        final SensorManager sensorManager = this.sensorManager;
        if (sensorManager != null) {
            final Sensor lightSensor = this.lightSensor;
            if (lightSensor != null) {
                sensorManager.registerListener((SensorEventListener)this, lightSensor, 3);
            }
        }
    }
    
    public void setBrightLightLux(final float brightLightLux) {
        this.brightLightLux = brightLightLux;
    }
    
    public void setDarkLightLux(final float darkLightLux) {
        this.darkLightLux = darkLightLux;
    }
    
    public void setLightSensorEnabled(final boolean isLightSensorEnabled) {
        this.isLightSensorEnabled = isLightSensorEnabled;
    }
    
    public void setOnLightSensorEventListener(final OnLightSensorEventListener mOnLightSensorEventListener) {
        this.mOnLightSensorEventListener = mOnLightSensorEventListener;
    }
    
    public void unregister() {
        final SensorManager sensorManager = this.sensorManager;
        if (sensorManager != null && this.lightSensor != null) {
            sensorManager.unregisterListener((SensorEventListener)this);
        }
    }
    
    public interface OnLightSensorEventListener
    {
        void onSensorChanged(final float p0);
        
        void onSensorChanged(final boolean p0, final float p1);
    }
}
