package android.anna.firstapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements SensorEventListener {

    TextView aX;
    TextView aY;
    TextView aZ;
    TextView mX;
    TextView mY;
    TextView mZ;
    TextView proximity;
    TextView light;

    SensorManager sensorManager;
    Sensor aSensor;
    Sensor pSensor;
    Sensor mSensor;
    Sensor lSensor;

    String TAG = "SECOND ACTIVITY";

    boolean created = false;
    boolean accuracyOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        aX = (TextView) findViewById(R.id.acX);
        aY = (TextView) findViewById(R.id.acY);
        aZ = (TextView) findViewById(R.id.acZ);
        mX = (TextView) findViewById(R.id.mX);
        mY = (TextView) findViewById(R.id.mY);
        mZ = (TextView) findViewById(R.id.mZ);
        proximity = (TextView) findViewById(R.id.closer);
        light = (TextView) findViewById(R.id.light);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        created = true;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged");
        if (created) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                aX.setText(Float.toString(event.values[0]));
                aY.setText(Float.toString(event.values[1]));
                aZ.setText(Float.toString(event.values[2]));
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mX.setText(Float.toString(event.values[0]));
                mY.setText(Float.toString(event.values[1]));
                mZ.setText(Float.toString(event.values[2]));
            }
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                proximity.setText(Float.toString(event.values[0]));
            }
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                light.setText(Float.toString(event.values[0]));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        Log.d(TAG, "onAccuracyChanged");
        switch (accuracy) {
            case 0:
                Log.d(TAG,"Unreliable");
                accuracyOK = false;
                break;
            case 1:
                Log.d(TAG,"Low Accuracy");
                accuracyOK = false;
                break;
            case 2:
                Log.d(TAG,"Medium Accuracy");
                accuracyOK = false;
                break;
            case 3:
                Log.d(TAG,"High Accuracy");
                accuracyOK = true;
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        sensorManager.registerListener(this, aSensor,
                sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, mSensor,
                sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, pSensor,
                sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, lSensor,
                sensorManager.SENSOR_DELAY_FASTEST);
        Log.d(TAG, "sensors are registered");
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, aSensor);
        sensorManager.unregisterListener(this, mSensor);
        sensorManager.unregisterListener(this, pSensor);
        sensorManager.unregisterListener(this, lSensor);
        Log.d(TAG, "sensors are unregistered");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d(TAG, "onPointerCaptureChanged");
    }

    public void sqlitheButton(View view) {
        Log.d(TAG, this.getClass().toString() + ": In sqlitheButton()");
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
    }
}
