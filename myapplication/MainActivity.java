package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_x, txt_y, txt_z, textView;
    Button button;
    Button button2;
    ImageView imageUp, imageDown, imageLeft, imageRight;
    private  SensorManager sensorManager;
    private  Sensor sensor;

    
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = new BlankFragment();
            ft.replace(R.id.mainactivityLayout,f);

          int x = Math.round(sensorEvent.values[0]);
          int y = Math.round(sensorEvent.values[1]);
          int z = Math.round(sensorEvent.values[2]);

           txt_x.setText("x = " + x);
           txt_y.setText("y = " + y);
           txt_z.setText("z = " + z);

            if(y == 10){
                imageUp.setImageResource(R.drawable.smiley);
                imageDown.setImageResource(R.drawable.white);
                imageRight.setImageResource(R.drawable.white);
                imageLeft.setImageResource(R.drawable.white);
                ft.commit();


           }
            if(y == -10 ){
                imageDown.setImageResource(R.drawable.smiley);
                imageUp.setImageResource(R.drawable.white);
                imageRight.setImageResource(R.drawable.white);
                imageLeft.setImageResource(R.drawable.white);
                ft.hide(f);
                ft.commit();


            }
            if(x == 10){
                imageRight.setImageResource(R.drawable.smiley);
                imageLeft.setImageResource(R.drawable.white);
                imageUp.setImageResource(R.drawable.white);
                imageDown.setImageResource(R.drawable.white);
                ft.hide(f);
                ft.commit();
            }
            if(x == -10){
                imageLeft.setImageResource(R.drawable.smiley);
                imageUp.setImageResource(R.drawable.white);
                imageRight.setImageResource(R.drawable.white);
                imageDown.setImageResource(R.drawable.white);
                ft.hide(f);
                ft.commit();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          button = findViewById(R.id.button);
          button.setOnClickListener(v -> {
            onPause();

        });

        button = findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            onResume();
            
        });

        imageUp =  findViewById(R.id.imageUp);
        imageDown =  findViewById(R.id.imageDown);
        imageLeft =  findViewById(R.id.imageLeft);
        imageRight =  findViewById(R.id.imageRight);
        txt_y = findViewById(R.id.txt_y);
        txt_x = findViewById(R.id.txt_x);
        txt_z = findViewById(R.id.txt_z);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }
        protected void onResume() {
          super.onResume();
          sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
        protected void onPause()  {
          super.onPause();
          sensorManager.unregisterListener(sensorEventListener);
    }
}