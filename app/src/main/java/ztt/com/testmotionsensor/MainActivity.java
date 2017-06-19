package ztt.com.testmotionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private SensorManager mSensorManager=null;
    private Sensor mSensor=null;
    Button btnRegister = null;
    Button btnUnregister = null;
    TextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*获取系统服务（SENSOR_SERVICE）返回一个SensorManager对象*/
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        /*通过SensorManager获取相应的（压力传感器）Sensor类型对象*/
        mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        tv = (TextView)findViewById(R.id.textView) ;

        btnRegister = (Button)findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSensorManager.registerListener(mSensorEventListener, mSensor
                        , SensorManager.SENSOR_DELAY_NORMAL);

            }
        });

        btnUnregister = (Button)findViewById(R.id.unregister);
        btnUnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSensorManager.unregisterListener(mSensorEventListener, mSensor);
            }
        });
    }

    /*声明一个SensorEventListener对象用于侦听Sensor事件，并重载onSensorChanged方法*/
    private final SensorEventListener mSensorEventListener = new SensorEventListener() {



        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType()==Sensor.TYPE_PRESSURE){
                /*压力传感器返回当前的压强，单位是百帕斯卡hectopascal（hPa）。*/
                float pressure=event.values[0];
                tv.setText(String.valueOf(pressure)+"hPa");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            

        }
        return false;
    }
}
