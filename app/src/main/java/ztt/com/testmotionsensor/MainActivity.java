package ztt.com.testmotionsensor;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private static TextView aa, bb, cc;
    private float atime;
    private float aX, aY;
    private boolean mPressBreak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aa = (TextView) findViewById(R.id.aa);
        bb = (TextView) findViewById(R.id.bb);
        cc = (TextView) findViewById(R.id.cc);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        String str = "";
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("User Action","Down");
                aX = event.getX();
                aY = event.getY();
                str = String.valueOf(aX) + " , " + String.valueOf(aY);
                bb.setText(str);
                mPressBreak = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("User Action","Move");
                atime = (event.getEventTime() - event.getDownTime());
                str = String.valueOf(event.getX()) + " , " + String.valueOf(event.getY());
                if ((Math.abs((event.getX() - aX)) > 10) || (Math.abs(event.getY() - aY) > 10)) {
                    Log.e("User Action","Break");
                    atime = 0;
                    mPressBreak = true;
                }
                if (!mPressBreak) {
                    aa.setText(String.valueOf((int) (atime)));
                    cc.setText(str);
                 }
                break;
            case MotionEvent.ACTION_UP:
                atime = event.getEventTime() - event.getDownTime();
                aa.setText(String.valueOf((int) (atime)));
                Log.e("User Action","Up");
                break;
        }

        return true;
    }

}
