package com.example.yujin_000.a27_prefex;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //환경설정 화면으로 이동하기
                Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(intent);

            }
        }); // end OnClickListener
    }
    // 이 화면이 보일때마다 호출됨 --> 외부에서 데이터를 로드할 경우 구현
    // App이 최초에 실행될 경우와 설정화면에 다녀온 경우를 모두 충족하는 시점.
    @Override
    protected void onResume(){
        super.onResume();

        //설정정보 객체
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        //객체에서 정보 추출(key, 기본값)

        String name = preferences.getString("name","이름없음");
        String email = preferences.getString("email", "이메일없음");
        int age = preferences.getInt("age", 0);
        boolean gender = preferences.getBoolean("gender", true);

        //출력
        textView1.setText(name);
        textView2.setText(email);
        textView3.setText(String.valueOf(age));
        textView4.setText(gender ? "여자" : "남자");

    }

}











