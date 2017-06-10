package com.example.yujin_000.a29_externalfileio;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {


    //읽기, 쓰기 모드를 구분하기 위한 값
    final static int MODE_READ = 0 ;
    final static int MODE_WRITE = 1;

    EditText editText;
    TextView textView;
    Button button1, button2;

    //버튼 클릭시 사용자가 선택한 모드
    int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장버튼에 대한 모드값 설정
                mode = MODE_WRITE;

                // 현재클래스이름PermissionsDispatcher
                //          .[@NeedsPermission이 적용된메서드이름]WithCheck(컨텍스트)
                MainActivityPermissionsDispatcher.needsPermissionWithCheck(MainActivity.this);
            }
        }); // end OnClickListener

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 읽기버튼에 대한 모드값 설정
                mode = MODE_READ;

                MainActivityPermissionsDispatcher.needsPermissionWithCheck(MainActivity.this);
            }
        }); // end OnClickListener
    }
    public void save(String path, String content) {
        OutputStream os = null;

        try {
            os = new FileOutputStream(path);
            byte[] data = content.getBytes("UTF-8");
            os.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String read(String path) {
        String content = null;
        InputStream is = null;

        try {
            is = new FileInputStream(path);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            content = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content;
    }
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needsPermission() {
        // 내부 저장소의 경로 얻기
        File externalDir = Environment.getExternalStorageDirectory();
        String externalPath = externalDir.getAbsolutePath();

        File myDir = new File(externalPath + "/mydir");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        File saveFile = new File(myDir, "hello.txt");
        String path = saveFile.getAbsolutePath();

        if (mode == MODE_WRITE) {
            String content = editText.getText().toString().trim();
            save(path, content);
        } else {
            String content = read(path);
            textView.setText(content);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        showPermissionDialog();
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        showPermissionDialog();
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        showPermissionDialog();
    }

    /**
     * 사용자가 권한을 거부한 상태에서도 지속적으로 해당 기능을 사용하려고 할 경우,
     * 권한을 승인해야 한다는 안내 메시지를 표시하고 설정화면으로 이동시키 위함
     */
    void showPermissionDialog() {
        // 현재 App의 패키지 이름 가져오기
        final String packageName = getApplicationContext().getPackageName();
        // 현재 App의 이름 가져오기
        final String appName = getApplicationInfo().loadLabel(
                getPackageManager()).toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림");
        builder.setMessage("내부저장소에 대한 접근을 거부하셨습니다. " +
                "이 기능을 사용하기 위해서는 " +
                "\"설정 > 애플리케이션 > [" + appName + "] > 권한\"에서 " +
                "해당 기능을 활성화 하셔야 합니다.");
        builder.setPositiveButton("확인", null);
        builder.setNeutralButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }
}

