package com.example.yujin_000.a02_button;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by yujin_000 on 2017-04-27.
 */

public class OnButton1Click implements View.OnClickListener{
    // 별도로 정의한 클래스에서 Context에 접근하기 위해서는
    // 생성자를 통해서 주입받아야 한다.
    Context context;

    public OnButton1Click(Context context){
        this.context = context;
    }

    /**
     * 이 클래스의 객체가 연결된 버튼이 클릭되었을 때 호출된다
     * @param v     이벤트가 발생한 컴포넌트 자신
     */

    @Override
    public void onClick(View v){
// 상위 클래스 형태로 전달되므로, 원래 형태로 변환 필요함
        Button b =(Button) v;
        b.setText("해당 버튼이 클릭되었습니다.");

        Toast.makeText(this.context, "button1 눌러짐", Toast.LENGTH_SHORT).show();
    }
}
