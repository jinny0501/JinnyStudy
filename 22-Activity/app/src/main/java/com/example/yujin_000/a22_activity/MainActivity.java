package com.example.yujin_000.a22_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    EditText editText1, editText2, editText3;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면 이동 처리
                Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
                /*
                *Intent에 부가정보 넣기 (String Key, Any Value)형식
                * -->value는 모든 종류의 데이터 타입에 대하여 Overload되어있다.
                * */
                intent.putExtra("INPUT", editText.getText().toString().trim());
                startActivity(intent);
             }
        }); //end OnClickListener

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);

        button1 = (Button)findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = new UserInfo();
                userInfo.user_name = editText1.getText().toString();
                userInfo.user_email = editText2.getText().toString();
                userInfo.user_phone = editText3.getText().toString();

                Intent intent = new Intent(MainActivity.this, Sub2Activity.class);
                intent.putExtra("USER_INFO",userInfo);

                startActivity(intent);

            }
        }); //end OnClickListener
    }
}















