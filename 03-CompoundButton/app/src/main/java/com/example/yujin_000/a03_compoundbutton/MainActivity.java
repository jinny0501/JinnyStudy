package com.example.yujin_000.a03_compoundbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText의 입력값 추출하기
                String name = editText1.getText().toString();
                String password = editText2.getText().toString();
                String email = editText3.getText().toString();
                String phone = editText4.getText().toString();

                System.out.println("-----------------------");
                // 첫 번째 파라미터 -> TAG
                // 두 번째 파라미터는 로그 내용.
                Log.v("EditText", "[사용자 입력 내용]"); // 5수준
                Log.d("EditText", name);                // 4수준
                Log.i("EditText", password);            // 3수준
                Log.w("EditText", email);               // 2수준
                Log.e("EditText", phone);               // 1수준
                System.out.println("-----------------------");
            }
        }); //end OnClickListener
    }
}
