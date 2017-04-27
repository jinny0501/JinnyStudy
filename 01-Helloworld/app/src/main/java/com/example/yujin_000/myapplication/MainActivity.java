package com.example.yujin_000.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// 화면을 구성하는 모든 클래스는 AppCompatActivity를 상속 받아야 한다.
public class MainActivity extends AppCompatActivity {

    // 안드로이드 App의 시작점. -> main()의 역할.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 화면 구성에 필요한 초기화 처리를 호출
        // 호출하지 않을 경우 SuperNotCalledException 발생.
        super.onCreate(savedInstanceState);

        // "/res/layout/activity_main.xml"을
        // 읽어들여서 화면에 Content로 구성한다.
        setContentView(R.layout.activity_main);
    }
}
