package com.example.yujin_000.a23_activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // import android.util.Log --> e(error), w(warning), i(info), d(debug), v(verbose)
        //       TAG
        Log.e("MainActivity", "Hello World");
        Log.w("MainActivity", "Hello World");
        Log.i("MainActivity", "Hello World");
        Log.d("MainActivity", "Hello World");
        Log.v("MainActivity", "Hello World");

        Log.d("MainActivity", "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정화면으로의 이동을 위한 Intent
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                // 수정화면에 전달할 데이터를 셋팅.
                intent.putExtra("CONTENT", textView.getText().toString());
                // SubActivity에 100번이라는 식별값을 부여한 상태로,
                // 결과값을 예약하는 화면 호출
                startActivityForResult(intent, 100);
            }
        }); //end OnClickListener

    }

    /**
     * startActivityForResult 에 의해서 화면을 이동한 상태에서 다시 돌아올 경우 호출된다.
     * @param requestCode - startActivityForResult()에서 부여한 식별값
     * @param resultCode - setResult()에 설정한 결과값
     * @param data - setResult()에 설정한 Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 100번 화면에 다녀온 경우
            case 100:
                // 처리 결과가 성공이라면?
                if (resultCode == Activity.RESULT_OK) {
                    // 부가 데이터를 추출하여 화면에 표시
                    String edit = data.getStringExtra("EDIT");
                    textView.setText(edit);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        // Activity Life Cycle 관련 메서드들은 반드시 첫 라인에서 상위 클래스의
        // 원래 메서드를 호출해야 함
        // 호출하지 않을 경우 SuperNotCalledException 발생함.
        super.onStart();
        Log.d("MainActivity", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy()");
    }
}

















