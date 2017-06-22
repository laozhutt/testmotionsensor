package ztt.com.testmotionsensor;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private static TextView aa, bb, cc,dd,ee;
    private TextView tv;
    private float atime;
    private float aX, aY;
    private float pressArea = (float) 0;
    private float pressure = (float) 0;
    private boolean mPressBreak = false;
    private Button startService;

    private Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("111133330","1111");
        aa = (TextView) findViewById(R.id.aa);
        bb = (TextView) findViewById(R.id.bb);
        cc = (TextView) findViewById(R.id.cc);
        dd = (TextView) findViewById(R.id.dd);
        ee = (TextView) findViewById(R.id.ee);

        tv = (TextView) findViewById(R.id.tv);
        tv.setClickable(true);
//        tv.setOnTouchListener((View.OnTouchListener) MainActivity.this);



        startService = (Button)findViewById(R.id.startservice);
        stopService = (Button)findViewById(R.id.stopservice);
        startService.setEnabled(false);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this,MotionService.class);
                startService(startIntent);
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this,MotionService.class);
                stopService(stopIntent);
            }
        });

        //允许在其他应用上层显示
        if (Build.VERSION.SDK_INT >= 23) {
            if (! Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        String str = "";
//        switch(event.getAction()){
//
//
//            case MotionEvent.ACTION_DOWN:
//
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
//                Log.e("Pressure",event.getPressure()+"");
//                if(event.getSize() > pressArea) {
//                    pressArea = event.getSize();
//                }
//                if(event.getPressure() > pressure) {
//                    pressure = event.getPressure();
//                }
//                atime = (event.getEventTime() - event.getDownTime());
//                str = String.valueOf(event.getX()) + " , " + String.valueOf(event.getY());
//                if ((Math.abs((event.getX() - aX)) > 10) || (Math.abs(event.getY() - aY) > 10)) {
//                    atime = 0;
//                    mPressBreak = true;
//                }
//                if (!mPressBreak) {
//                    aa.setText(String.valueOf((int) (atime)));
//                    cc.setText(str);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
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
//        return false;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        String str = "";
        switch(event.getAction()){


            case MotionEvent.ACTION_DOWN:

//                Log.e("Pressure",event.getPressure()+"");
                Log.e("User Action","Down");
                if(event.getSize() > pressArea) {
                    pressArea = event.getSize();
                }
                if(event.getPressure() > pressure) {
                    pressure = event.getPressure();
                }
                aX = event.getX();
                aY = event.getY();
                str = String.valueOf(aX) + " , " + String.valueOf(aY);
                bb.setText(str);
                mPressBreak = false;
                break;
            case MotionEvent.ACTION_MOVE:

//                Log.e("id",event.getPointerCount()+"");
                Log.e("Pressure",event.getPressure()+"");
                if(event.getSize() > pressArea) {
                    pressArea = event.getSize();
                }
                if(event.getPressure() > pressure) {
                    pressure = event.getPressure();
                }
                //Log.e("User Action","Move");
                atime = (event.getEventTime() - event.getDownTime());
                str = String.valueOf(event.getX()) + " , " + String.valueOf(event.getY());
                if ((Math.abs((event.getX() - aX)) > 10) || (Math.abs(event.getY() - aY) > 10)) {
                    //Log.e("User Action","Break");
                    atime = 0;
                    mPressBreak = true;
                }
                if (!mPressBreak) {
                    aa.setText(String.valueOf((int) (atime)));
                    cc.setText(str);
                 }
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("Pressure",event.getPressure()+"");
                if(event.getSize() > pressArea) {
                    pressArea = event.getSize();
                }
                if(event.getPressure() > pressure) {
                    pressure = event.getPressure();
                }
                dd.setText(String.valueOf(pressArea));
                ee.setText(String.valueOf(pressure));
                pressArea = 0;//记录后重置
                pressure = 0;
                atime = event.getEventTime() - event.getDownTime();
                aa.setText(String.valueOf((int) (atime)));
                Log.e("User Action","Up");
                break;
            //这两个很诡异，条件有时满足有时不满足
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.e("User Action","Pointer Down");
                break;
            case  MotionEvent.ACTION_POINTER_UP:
                Log.e("User Action","Pointer Up");
                break;
        }

        return true;
    }


    //允许在其他应用上层显示
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    //Toast.makeText(MainActivity.this,"not granted",Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
