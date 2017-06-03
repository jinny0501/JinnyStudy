package com.example.yujin_000.a22_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        //이전화면에서 전달 된 Intent받기
        Intent fromIntent = getIntent();
        //Intent에 포함된 데이터 객체 추출
        UserInfo userInfo = (UserInfo) fromIntent.getSerializableExtra("USER_INFO");

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        button = (Button) findViewById(R.id.button);

        // 추출한 정보 출력하기
        textView1.setText(userInfo.user_name);
        textView2.setText(userInfo.user_email);
        textView3.setText(userInfo.user_phone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }); // end OnClickListener
    }
}
