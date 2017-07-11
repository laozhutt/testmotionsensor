//package ztt.com.testmotionsensor;
//
//import android.app.Service;
//import android.content.Intent;
//import android.graphics.PixelFormat;
//import android.os.Build;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
///**
// * Created by laoztt on 2017/6/21.
// */
//
//public class MotionService extends Service implements View.OnTouchListener {
//    private static String TAG = "Motion Service";
//
//    private static TextView aa, bb, cc,dd,ee;
//    private float atime;
//    private float aX, aY;
//    private float pressArea = (float) 0;
//    private float pressure = (float) 0;
//    private boolean mPressBreak = false;
//    private Button mButton;
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.e(TAG,"start");
//
//        mButton = new Button(this);
//        mButton.setAlpha(0);//透明
//        mButton.setText("Overlay button");
//        mButton.setOnTouchListener(this);
//
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//
//
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
//                        | WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER
//                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//                        | WindowManager.LayoutParams.FLAG_FULLSCREEN
//                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,//覆盖全部屏幕
//                PixelFormat.TRANSLUCENT);
//        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
//        params.setTitle("Load Average");
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        wm.addView(mButton, params);
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.e(TAG,"destroy");
//        if(mButton != null)
//        {
//            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mButton);
//            mButton = null;
//        }
//    }
//
//
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
////        Toast.makeText(this,"Overlay button event", Toast.LENGTH_SHORT).show();
//
//
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG,"Down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e(TAG,"OHIO");
//                Log.e(TAG,"X="+String.valueOf(event.getX())+"  Y="+String.valueOf(event.getY()));
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e(TAG,"Up");
//                break;
//        }
//        return false;
//    }
//
//
//
//
//
//}
