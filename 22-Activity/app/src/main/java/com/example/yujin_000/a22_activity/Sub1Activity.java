package com.example.yujin_000.a22_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sub1Activity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        //이전 화면에서 넘어온 Intent를 받는다
        Intent fromIntent = getIntent();
        String input = fromIntent.getStringExtra("INPUT");
        //int a = fromIntent.getIntExtra("aaaa",0);

        //이전 화면에서 넘어온 데이터를 출력하기
        textView.setText(input);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 화면 종료
                finish();

            }
        }); //end OnClickListener
    }
}
