package com.example.marblemaze;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorData implements SensorEventListener {
    private SensorManager manager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private Sensor light;
    private Sensor temperature;

    private float[] accelerometerOutput;
    private float[] magnetometerOutput;
    private float lightOutput;
    private float temperatureOutput;
    public int tempColor = Color.MAGENTA;

    private float[] orientation = new float[3];

    public float[] getOrientation() {
        return orientation;
    }

    private float[] startOrientation = null;

    public float[] getStartOrientation() {
        return startOrientation;
    }

    public void newGame() {
        startOrientation = null;
    }

    public SensorData() {
        manager = (SensorManager)Constants.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temperature = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }

    public void register() {
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause() {
        manager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // getting data from accelerometer and magnetometer
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER: // for movement of marble
                accelerometerOutput = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD: // for movement of marble
                magnetometerOutput = event.values.clone();
                break;
            case Sensor.TYPE_LIGHT:
                lightOutput = event.values[0];
                System.out.println("LIGHT LEVEL: " + lightOutput);
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                temperatureOutput = event.values[0]; // room temp in degrees Celsius
            default:
                return;

        }

        // changing background color
        if (lightOutput < 15) { // dark mode
            Constants.BACKGROUND_COLOR = Color.BLACK;
            Constants.OBSTACLE_COLOR = Color.WHITE;
        } else { // light mode
            Constants.BACKGROUND_COLOR = Color.WHITE;
            Constants.OBSTACLE_COLOR = Color.BLACK;
        }

        if (temperatureOutput <= 0) { // very cold, blue marble
            Constants.MARBLE_COLOR = Color.BLUE;
        } else if (temperatureOutput > 0 && temperatureOutput <= 7) { // sort of cold, light blue
            Constants.MARBLE_COLOR = Color.rgb(161, 209, 240);
        } else if (temperatureOutput >7 && temperatureOutput <= 15 ) { // almost warm, purple
            Constants.MARBLE_COLOR = Color.rgb(127, 15, 255);
        } else if (temperatureOutput > 15 && temperatureOutput <= 25 ) { // warm, pink
            Constants.MARBLE_COLOR = Color.rgb(255, 33, 248);
        } else { // very warm, red
            Constants.MARBLE_COLOR = Color.RED;
        }

        // getting rotation matrix
        if (accelerometerOutput != null && magnetometerOutput != null) {
            float[] rotationMatrix = new float[9];
            boolean canRotate = SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerOutput, magnetometerOutput);

            if (canRotate) {
                SensorManager.getOrientation(rotationMatrix, orientation);

                if (startOrientation == null) {
                    startOrientation = new float[orientation.length];
                    System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
                }
            }
        }
    }
}
