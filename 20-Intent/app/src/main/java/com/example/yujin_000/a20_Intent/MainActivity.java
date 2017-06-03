package com.example.yujin_000.a20_Intent;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // 전화 다이얼러 호출
                        // --> import android.content.Intent;
                        // --> import android.net.Uri;
                        Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
                        startActivity(intent1);
                        break;

                    case 1:
                        // 문자 발송 화면 호출
                        Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01012345678"));
                        // key-value 형식으로 추가 데이터 설정.
                        // --> value값이 모든 데이터 타입에 대해서 Overload되어 있음.
                        intent2.putExtra("sms_body", "The SMS text");
                        startActivity(intent2);
                        break;

                    case 2:
                        // 메일발송 화면 호출 (에뮬레이터에서 실행 안됨)
                        Intent intent3 = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:android@gmail.com"));
                        intent3.putExtra(Intent.EXTRA_SUBJECT, "메일테스트");
                        intent3.putExtra(Intent.EXTRA_TEXT, "안드로이드에서 메일 발송 테스트 입니다.");
                        startActivity(intent3);
                        break;

                    case 3:
                        // 웹 브라우저 호출
                        Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.itpaper.co.kr"));
                        startActivity(intent4);
                        break;

                    case 4:
                        // 구글 웹 검색
                        // --> import android.app.SearchManager;
                        Intent intent5 = new Intent(Intent.ACTION_WEB_SEARCH);
                        // 검색어 설정
                        intent5.putExtra(SearchManager.QUERY, "Android");
                        startActivity(intent5);
                        break;

                    case 5:
                        // 특정 App에 대한 구글 플레이 설치 화면 호출 (에뮬레이터에서 실행 안됨)
                        // --> market://details?id=패키지이름
                        Intent intent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.android.chrome"));
                        startActivity(intent6);
                        break;

                    case 6:
                        // 온라인 동영상 재생
                        Intent intent7 = new Intent(Intent.ACTION_VIEW);
                        intent7.setDataAndType(
                                Uri.parse("http://itpaper.co.kr/demo/app/BigBuck.mp4"), "video/*");
                        startActivity(intent7);
                        break;

                    case 7:
                        // 온라인 음악 재생
                        Intent intent8 = new Intent(Intent.ACTION_VIEW);
                        intent8.setDataAndType(Uri.parse("http://itpaper.co.kr/demo/app/music.mp3"), "audio/*");
                        startActivity(intent8);
                        break;

                    case 8:
                        // 환경설정 화면 호출 --> import android.provider.Settings;
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                        break;

                    case 9:
                        // GPS 설정 화면 호출
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        break;

                    case 10:
                        // WIFI 설정 화면 호출 (에뮬레이터에서 확인 안됨)
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        break;

                    case 11:
                        // 현재 App에 대한 설정 정보 보기
                        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:study.android.a20_intent")));
                        break;
                }
            }
        }); // end OnItemClickListener
    }
}
