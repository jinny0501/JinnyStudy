package com.example.yujin_000.a16_loadingdialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomLoadingDialog();
            }
        });
    }

    void showLoadingDialog(){
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("잠시만 기다려주세요");

        // 일반적인 경우는 잘 사용 안함.
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(MainActivity.this, "로딩 취소됨", Toast.LENGTH_SHORT).show();
            }
        });

        // 취소키로 창 닫기 방지 (일반적으로 많이 사용함)
        // progressDialog.setCancelable(false);
        progressDialog.show();

        // 시간이 오래 걸리는 작업(ex:네트워크 처리)등이 종료된 후, 로딩창을 강제로 닫는다.
        //progressDialog.dismiss();
    }
    void showCustomLoadingDialog() {
        // 스타일 리소스를 적용한 다이얼로그 객체
        // --> import android.app.Dialog;
        Dialog dialog = new Dialog(MainActivity.this, R.style.loading_dialog_style);

        // 프로그레스바 컴포넌트를 레이아웃 XML없이 직접 생성
        // --> 파라미터는 Context객체를 요구하므로, Activity를 전달한다.
        // --> import android.widget.ProgressBar;
        ProgressBar pb = new ProgressBar(MainActivity.this);

        // 다이얼로그에 프로그레스바 추가
        // --> 파라미터: 프로그레스바 컴포넌트, 가로/세로 사이즈 정보를 갖는 객체
        // --> import android.widget.LinearLayout.LayoutParams;
        dialog.addContentView(pb,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(MainActivity.this, "로딩 취소됨", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}

