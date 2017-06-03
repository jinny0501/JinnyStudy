package com.example.yujin_000.a23_activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText editText;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("EditActivity","onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = (EditText) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        //이전화면에서 넘어온 데이터 받기
        Intent fromIntent = getIntent();
        String content = fromIntent.getStringExtra("CONTENT");
        editText.setText(content);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이전화면에 되돌려줄 데이터를 구성
                Intent intent = new Intent();
                intent.putExtra("EDIT", editText.getText().toString());

                // 결과셋팅 (intent는 필요 없는 경우 생략 가능)
                setResult(Activity.RESULT_OK, intent);

                // 현재화면 종료
                finish();
            }
        }); // end OnClickListener

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 결과값을 지정하지 않은 상태로 finish() 호출시
                // RESULT_CANCELED가 기본값.
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }); // end OnClickListener
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("EditActivity", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("EditActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("EditActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("EditActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("EditActivity", "onDestroy()");
    }
}

