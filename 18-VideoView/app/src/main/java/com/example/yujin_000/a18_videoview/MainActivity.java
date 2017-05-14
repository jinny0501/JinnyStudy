package com.example.yujin_000.a18_videoview;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    /*VideoView 컴포넌트*/
    VideoView videoView;

    /* 재생,중지버튼,재생위치 슬라이더를 포함하는 객체 */
    MediaController mediaController;

    /** 상태바와 옵션값을 저장할 변수 */
    View decorView;
    int	uiOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /** 타이틀바 제거 - setContentView 전에 수행해야 한다. */
        // 4.x 이하 버전용
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_ACTION_BAR);

        // 5.x 이상용
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 롤리팝 이상 버전(5.x)에서는 상태바의 색상 변경도 가능함.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#ff1493"));
        }

        setContentView(R.layout.activity_main);

        /** fullScreen 모드 - setContentView 이후에 처리해야 한다 */
        decorView = getWindow().getDecorView();
        uiOption = decorView.getSystemUiVisibility();

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ) {
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        /** VideoView 처리 */
        Uri videoUri = Uri.parse("http://itpaper.co.kr/demo/app/BigBuck.mp4");

        mediaController = new MediaController(MainActivity.this);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri); // 온라인상의 동영상에 대한 스트리밍
        videoView.start();
    }
    /** 창에 포커스가 간 경우 Fullscreen 모드에 대한 On/Off 처리를 수행한다 */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if( hasFocus ) {
            decorView.setSystemUiVisibility( uiOption );
        }
    }
}
