package com.example.yujin_000.a02_button;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 사용자가 컴포넌트에 대해서 행하는 행위 --> 이벤트.
 * 이벤트가 발생한 경우 그에 대한 동작을 구현한 클래스 --> EventListener 혹은 EventHandler
 *
 * 이벤트 리스너의 이름 규칙 >> On~~~~Listener
 *
 * 모든 이벤트리스너는 Interface 형태로 존재.
 *      >> 기능 구현을 위해서는 이벤트리스너를 상속받는 클래스가 필요!!!
 *
 * 컴포넌트에는 setOn~~~Listener(...) 형식의 메서드가 존재.
 *      >> 이 메서드에게 Interface의 구현체에 대한 객체를 전달해야 한다.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** (2) 선언된 버튼 객체참조변수에 id값에 의해서 참조시킴 */
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        /** (3) 버튼 이벤트 연결하기 */
        //별도의 클래스로 존재하는 경우 --> 해당 클래스의 객체를 생성한다.
        button1.setOnClickListener(new OnButton1Click(MainActivity.this));

        // 현재 클래스가 직접 인터페이스를 상속받는 경우
        button2.setOnClickListener(this);

        // 익명클래스 방식으로 이벤트 처리하기
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Button) v).setText("해당 버튼이 클릭되었습니다.");
                // Context에 대한 접근이 필요한 경우 현재 "Activity이름.this"
                Toast.makeText(MainActivity.this, "button2 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**OnClickListener를 상속받음으로 인해서 재정의된 메서드**/
    @Override
    public void onClick(View v){
        // 모든 컴포넌트가 이 방식으로 처리될 경우 하나의 메서드에서
        // 모든 이벤트를 처리해야 하므로 어떤 버튼이 눌러졌는가에 대한 판단이 필수.

        switch(v.getId()){
            case R.id.button2:
                ((Button)v).setText("해당 버튼이 클릭되었습니다.");

                // 토스트 메시지 표시
                // --> Context객체, 표시할 내용, 표시시간
                Toast.makeText(MainActivity.this, "button2 눌러짐", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}





















