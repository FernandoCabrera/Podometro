package com.example.podometro;
/*
Autor:Fernando

 */
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor countsensor;
    private TextView steptinit;
    boolean isRunning=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        steptinit =findViewById(R.id.cont_steps);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        isRunning=true;

        countsensor= mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countsensor!=null){
            //Case sensor is found
            //We can select sensor delay in function to ours requirements.
            mSensorManager.registerListener(this, countsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this,"Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning=false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(isRunning){
            //Update step counter
            steptinit.setText(String.valueOf(sensorEvent.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}