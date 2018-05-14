package com.github.pires.obd.reader.io;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class AccelerometerManager implements SensorEventListener {
    public float[] values;
    private final SensorManager mSensorManager;
    private final Sensor mAccelerometer;

    public AccelerometerManager(SensorManager sensorManager) {
        super();
        this.mSensorManager = sensorManager;
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void start() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.values = sensorEvent.values;
        Log.d("SENSOR", ""+ values[0] + "," + values[1] + "," + values[2]);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
