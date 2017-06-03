package com.example.yujin_000.a26_actionbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by yujin_000 on 2017-06-02.
 */

public class BaseActivity extends AppCompatActivity {

    ImageButton btHome, btMy, btSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** 액션바 관련 작업은 setContentView 호출 이전에 수행되어야 한다.*/
        // 액션바 객체를 획득.
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);    // 커스텀화를 허용
        actionBar.setDisplayHomeAsUpEnabled(false);     // 기본 홈 버튼 숨김
        actionBar.setDisplayShowTitleEnabled(false);    // 기본 제목 타이틀 숨김
        //actionBar.setElevation(0);                      //  ActionBar의 그림자 숨김

        // 액션바의 배경색상은 흰색으로 설정
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));

        // 커스텀 액션바를 위한 XML로드
        View actionBarView = getLayoutInflater().inflate(R.layout.actionbar, null);

        // 커스텀 XML을 액션바에 추가하기 위한 width,height 설정
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        // 액션바에 XML객체를 붙임
        actionBar.setCustomView(actionBarView, params);

        // 커스텀 XML의 상위요소 --> 툴바를 가져옴
        Toolbar parent = (Toolbar) actionBarView.getParent();
        // 기본 여백 제거
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);

        /** 액션바에 추가한 버튼에 대한 객체 할당 및 이벤트 구현 */
        btHome = (ImageButton) actionBarView.findViewById(R.id.bt_home);
        btMy = (ImageButton) actionBarView.findViewById(R.id.bt_my);
        btSetup = (ImageButton) actionBarView.findViewById(R.id.bt_setup);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });

        btMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "My", Toast.LENGTH_SHORT).show();
            }
        });

        btSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseActivity.this, "Setup", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
