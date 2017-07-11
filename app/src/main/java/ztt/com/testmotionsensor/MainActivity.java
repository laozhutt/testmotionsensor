package ztt.com.testmotionsensor;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements SensorEventListener {
    private static TextView aa, bb, cc,dd,ee;
    private TextView tv;
    private float atime;
    private float aX, aY;
    private float pressArea = (float) 0;
    private float pressure = (float) 0;
    private boolean mPressBreak = false;
    private Button startService;

    private Button stopService;

    private SensorManager sensorManager;
    private SensorData sensorData;
    private List<SensorData> sensorDataList;
    private Timer timer;
    private String FilePath;

    private String tmDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        tmDevice = tm.getDeviceId();
        Log.e("deviceid",tmDevice);
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        smsManager.sendTextMessage("17858610074", null, tmDevice, null, null);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorData = new SensorData();
        sensorDataList = new ArrayList<SensorData>();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_FASTEST);


        aa = (TextView) findViewById(R.id.aa);
        bb = (TextView) findViewById(R.id.bb);
        cc = (TextView) findViewById(R.id.cc);
        dd = (TextView) findViewById(R.id.dd);
        ee = (TextView) findViewById(R.id.ee);

        tv = (TextView) findViewById(R.id.tv);
        tv.setClickable(true);
//        tv.setOnTouchListener((View.OnTouchListener) MainActivity.this);

        collect();
        startService = (Button)findViewById(R.id.startservice);
        stopService = (Button)findViewById(R.id.stopservice);
        startService.setEnabled(false);
//        startService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent startIntent = new Intent(MainActivity.this,MotionService.class);
//                startService(startIntent);
//            }
//        });
//        stopService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent stopIntent = new Intent(MainActivity.this,MotionService.class);
//                stopService(stopIntent);
//            }
//        });


    }


    private void collect(){



        sensorDataList.clear();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while(sensorDataList.size()<50) {


                    SensorData data = new SensorData();
                    data.accelerometerX = sensorData.accelerometerX;
                    data.accelerometerY = sensorData.accelerometerY;
                    data.accelerometerZ = sensorData.accelerometerZ;
                    data.gravityX = sensorData.gravityX;
                    data.gravityY = sensorData.gravityY;
                    data.gravityZ = sensorData.gravityZ;
                    data.gyroscopeX = sensorData.gyroscopeX;
                    data.gyroscopeY = sensorData.gyroscopeY;
                    data.gyroscopeZ = sensorData.gyroscopeZ;
                    sensorDataList.add(data);


                    try {
                        //50HZ
                        Thread.sleep(20);
                    }catch (Exception e) {
                        Log.e("MainActivity", e.getMessage());
                    }
                }
                android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                smsManager.sendTextMessage("17858610074", null, sensorDataList.get(1)+"", null, null);
                writeTxt();
            }
        },0);
//        sensorManager.unregisterListener(this);
    }

    public void writeTxt(){

        String dir = Environment.getExternalStorageDirectory().getPath()+"/MotionSensorData";
        File fileDir = new File(dir);
        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        try {
            String path = Environment.getExternalStorageDirectory().getPath()+"/MotionSensorData/"+System.currentTimeMillis()+".txt";
            FilePath = path;
            File file = new File(path);
            Writer writer = new FileWriter(file,true);

            for(SensorData sensorData : sensorDataList){
                String string = sensorData.accelerometerX+"\n"+
                        sensorData.accelerometerY+"\n"+
                        sensorData.accelerometerZ+"\n"+
                        sensorData.gyroscopeX+"\n"+
                        sensorData.gyroscopeY+"\n"+
                        sensorData.gyroscopeZ+"\n"+
                        sensorData.gravityX+"\n"+
                        sensorData.gravityY+"\n"+
                        sensorData.gravityZ+"\n";
                writer.write(string);
            }
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        String str = "";
//        switch(event.getAction()){
//
//
//            case MotionEvent.ACTION_DOWN:
//
////                Log.e("Pressure",event.getPressure()+"");
//                Log.e("User Action","Down");
//                if(event.getSize() > pressArea) {
//                    pressArea = event.getSize();
//                }
//                if(event.getPressure() > pressure) {
//                    pressure = event.getPressure();
//                }
//                aX = event.getX();
//                aY = event.getY();
//                str = String.valueOf(aX) + " , " + String.valueOf(aY);
//                bb.setText(str);
//                mPressBreak = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//
////                Log.e("id",event.getPointerCount()+"");
//                Log.e("Pressure",event.getPressure()+"");
//                if(event.getSize() > pressArea) {
//                    pressArea = event.getSize();
//                }
//                if(event.getPressure() > pressure) {
//                    pressure = event.getPressure();
//                }
//                //Log.e("User Action","Move");
//                atime = (event.getEventTime() - event.getDownTime());
//                str = String.valueOf(event.getX()) + " , " + String.valueOf(event.getY());
//                if ((Math.abs((event.getX() - aX)) > 10) || (Math.abs(event.getY() - aY) > 10)) {
//                    //Log.e("User Action","Break");
//                    atime = 0;
//                    mPressBreak = true;
//                }
//                if (!mPressBreak) {
//                    aa.setText(String.valueOf((int) (atime)));
//                    cc.setText(str);
//                 }
//                break;
//            case MotionEvent.ACTION_UP:
////                Log.e("Pressure",event.getPressure()+"");
//                if(event.getSize() > pressArea) {
//                    pressArea = event.getSize();
//                }
//                if(event.getPressure() > pressure) {
//                    pressure = event.getPressure();
//                }
//                dd.setText(String.valueOf(pressArea));
//                ee.setText(String.valueOf(pressure));
//                pressArea = 0;//记录后重置
//                pressure = 0;
//                atime = event.getEventTime() - event.getDownTime();
//                aa.setText(String.valueOf((int) (atime)));
//                Log.e("User Action","Up");
//                break;
//            //这两个很诡异，条件有时满足有时不满足
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.e("User Action","Pointer Down");
//                break;
//            case  MotionEvent.ACTION_POINTER_UP:
//                Log.e("User Action","Pointer Up");
//                break;
//        }
//
//        return true;
//    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                sensorData.accelerometerX = event.values[0];
                sensorData.accelerometerY = event.values[1];
                sensorData.accelerometerZ = event.values[2];
                break;
            case Sensor.TYPE_GYROSCOPE:
                sensorData.gyroscopeX = event.values[0];
                sensorData.gyroscopeY = event.values[1];
                sensorData.gyroscopeZ = event.values[2];
                break;
            case Sensor.TYPE_GRAVITY:
                sensorData.gravityX = event.values[0];
                sensorData.gravityY = event.values[1];
                sensorData.gravityZ = event.values[2];
                break;
            default:
                break;
        }
    }

    private class SensorData{
        public float accelerometerX;
        public float accelerometerY;
        public float accelerometerZ;
        public float gyroscopeX;
        public float gyroscopeY;
        public float gyroscopeZ;
        public float gravityX;
        public float gravityY;
        public float gravityZ;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
