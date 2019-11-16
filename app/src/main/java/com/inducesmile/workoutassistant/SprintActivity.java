package com.inducesmile.workoutassistant;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class SprintActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG="SprintActivity";
    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView xvalue,yvalue,zvalue,totalSprints;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final double SHAKE_THRESHOLD = 2.8;
    private int sprintcount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprint);

        xvalue= findViewById(R.id.xAxis);
        yvalue= findViewById(R.id.yAxis);
        zvalue= findViewById(R.id.zAxis);
        totalSprints=findViewById(R.id.totalSprints);

        Log.d(TAG,"onCreate: Initializing Sensor Services");
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) SprintActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG,"onCreate: Registered Accelerometer Listener");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int i) {}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Log.d(TAG,"onSensorChanged : X:"+sensorEvent.values[0] + "Y: "+sensorEvent.values[1]+"z:"+sensorEvent.values[2] );
        xvalue.setText(""+sensorEvent.values[0]);
        yvalue.setText(""+sensorEvent.values[1]);
        zvalue.setText(""+sensorEvent.values[2]);

        long curTime=System.currentTimeMillis();

        if(curTime-lastUpdate > 100) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            float speed = Math.abs(sensorEvent.values[0] + sensorEvent.values[1] + sensorEvent.values[2] - last_x - last_y - last_z) / diffTime * 10000;

            if(speed > SHAKE_THRESHOLD)
            {
                sprintcount++;
            }
            totalSprints.setText(String.valueOf(sprintcount));


            last_x = sensorEvent.values[0];
            last_y = sensorEvent.values[1];
            last_z = sensorEvent.values[2];
        }
    }

    public void continueButton(View view)
    {
        sensorManager.unregisterListener(this);
    }
}
