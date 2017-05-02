package com.example.yujin_000.a10_spinner;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textView2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        textView2 = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //(1)
                //Spinner의 선택 위치를 가져온다 ->> 0부터 시작됨 (index이므로)
                int selectedIndex = spinner.getSelectedItemPosition();

                //res 폴더에 접근할수 있는 객체 (res폴더가 array.xml이 있는 위치의 가장 상위이므로)
                Resources r = getResources();

                //XML에 준비한 배열을 읽어온다.(array.xml)
                String[] seasons = r.getStringArray(R.array.seasons);

                //읽어온 배열에 대해서 사용자가 선택한 위치에 대응되는 값이 사용자의 선택값이다.
                String userChoice = seasons[selectedIndex];

                //선택된 값을 토스트 메세지로 띄움.
                Toast.makeText(MainActivity.this, userChoice, Toast.LENGTH_SHORT).show();
            }
        }); //end OnClickListener

        /** (2) Spinner의 선택 이벤트 */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Spinner의 선택 상태가 변경될 경우 호출된다. position, id가 선택 위치
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //(1)의 코드들을 전부 다 복사해온다. 단, 여기서는 위의 코드와 다르게 위치를 받아온다.
                Resources r = getResources();
                String[] seasons = r.getStringArray(R.array.seasons);
                String userChoice = seasons[position];
                textView2.setText(userChoice); //스피너 아래의 텍스트뷰에 선택한 아이템을 출력.
            }

            //선택이 취소될 때 호출된다.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        }); //end OnItemSelectedListener
    }
}
