package com.example.yujin_000.a27_prefex;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ConfigActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

    }
    /**  이 화면이 보일때 자동으로 호출된다. */
    // 외부에서 데이터를 로드할 경우 구현
    // 환경설정 데이터를 로드해서 EditText와 RadioGroup에 설정한다.
    @Override
    protected void onResume() {
        super.onResume();

        // 설정정보 객체
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // 객체에서 정보 추출(key, 기본값)
        String name = preferences.getString("name", "");
        String email = preferences.getString("email", "");
        int age = preferences.getInt("age", 0);
        boolean gender = preferences.getBoolean("gender", true);

        // 출력
        editText1.setText(name);
        editText2.setText(email);

        if (age > 0) {
            editText3.setText(String.valueOf(age));
        }

        radioGroup.check(gender ? R.id.radioButton1 : R.id.radioButton2);
    }

    /** 이 화면에서 벗어나는 경우 자동으로 호출된다. */
    // 사용자의 입력값을 저장한다.
    @Override
    protected void onPause() {
        // LifeCycle 메서드들은 가장 첫 줄에 부모의 기능을 호출할 것을 요구함.
        super.onPause();

        // 입력값을 모은다.
        String name     = editText1.getText().toString().trim();
        String email    = editText2.getText().toString().trim();
        int age         = Integer.parseInt(editText3.getText().toString().trim());
        boolean gender  = radioGroup.getCheckedRadioButtonId() == R.id.radioButton1;

        // 설정 정보를 관리하는 객체 -> 파라미터: Context
        SharedPreferences preferences
                = PreferenceManager.getDefaultSharedPreferences(ConfigActivity.this);

        // 저장기능을 갖는 객체 생성
        SharedPreferences.Editor editor = preferences.edit();

        // 데이터 추가(혹은 변경)하기 --> 기본 자료형만 가능함.
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putInt("age", age);
        editor.putBoolean("gender", gender);

        // editor.remove("name");   // --> 특정 데이터 삭제하기
        // editor.clear();          // --> 전체 삭제하기

        // 변경사항 저장
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }
}
