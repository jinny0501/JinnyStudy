package com.example.yujin_000.a12_defaultdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
    }

    public void showAlertDialog(){
        //import android.support.v7.app.AlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        /**기본 설정**/
        builder.setTitle("알림");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("안드로이드 다이얼로그 입니다.");
        // 빈 공간이나, 백키를 통해서 창을 닫지 못함.
        //builder.setCancelable(false);

        /**버튼 설정**/
        //긍정의 버튼
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "확인 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });

        /**취소 이벤트**/
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "취소됨", Toast.LENGTH_SHORT).show();

            }
        });

        //다이얼로그 생성
        builder.create();
        builder.show();

    }

    public void showConfirmDialog() {
        // import android.support.v7.app.AlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        /** 기본 설정 */
        builder.setTitle("알림");                               // 타이틀
        builder.setIcon(R.mipmap.ic_launcher);                  // 아이콘
        builder.setMessage("안드로이드 다이얼로그 입니다.");     // 메시지
        // 빈 공간이나, 백키를 통해서 창을 닫지 못함.
        //builder.setCancelable(false);

        /** 버튼 설정 */
        // 긍정의 버튼
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "확인 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });

        // 부정의 버튼
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "취소 눌러짐", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("hello", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        /** 취소 이벤트 */
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "취소됨", Toast.LENGTH_SHORT).show();
            }
        });

        // 다이얼로그 생성
        builder.create();
        builder.show();
    }

    public void showListDialog() {
        // import android.support.v7.app.AlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        /** 기본 설정 */
        builder.setTitle("선택");                               // 타이틀
        builder.setIcon(R.mipmap.ic_launcher);                  // 아이콘

        /** 목록으로 구성할 배열 데이터 */
        final String[] items = {"축구", "농구", "배구"};

        // 배열을 다이얼로그에 추가
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "취소됨", Toast.LENGTH_SHORT).show();
            }
        });

        // 다이얼로그 생성
        builder.create();
        builder.show();
    }
}


























