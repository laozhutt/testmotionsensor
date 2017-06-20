package ztt.com.testmotionsensor;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private static TextView aa, bb, cc,dd,ee;
    private float atime;
    private float aX, aY;
    private float pressArea = (float) 0;
    private float pressure = (float) 0;
    private boolean mPressBreak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aa = (TextView) findViewById(R.id.aa);
        bb = (TextView) findViewById(R.id.bb);
        cc = (TextView) findViewById(R.id.cc);
        dd = (TextView) findViewById(R.id.dd);
        ee = (TextView) findViewById(R.id.ee);
    }

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

}
