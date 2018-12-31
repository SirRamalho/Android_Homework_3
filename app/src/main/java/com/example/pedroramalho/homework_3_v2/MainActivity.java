package com.example.pedroramalho.homework_3_v2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    static public SensorManager mSensorManager;
    //static List<Sensor> SensorList;
    // static final public String SENSOR_TYPE="sensorType";
    Sensor mSensor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.mSensorManager.registerListener(this, mSensor, 5000000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.mSensorManager.unregisterListener(this, mSensor);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        Resources res=getResources();
        String  Answers[] = res.getStringArray(R.array.answers);
        final EditText question =(EditText)findViewById(R.id.Question);
        String check= question.getText().toString();
        int Min=0,Max=8;

        if(check.contains("?") && ((event.values[0]>=1) && (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER))){
            //Min=0;Max=3;
            int random = (Min + (int) (Math.random() * (Max - Min)));
            InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            ((EditText)findViewById(R.id.Question)).setText(null);

            String displayText = Answers[random];
            TextView valueTextV = (TextView)findViewById(R.id.answers);
            valueTextV.setText(displayText);
        }






    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}
