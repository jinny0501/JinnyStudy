package com.example.yujin_000.a21_callphone;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String phoneNumber = editText.getText().toString().trim();
                //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                //startActivity(intent);

                /** (1) 사용자에게 권한 승인 여부를 묻는 팝업을 표시한다. */
                // 현재클래스PermissionsDispatcher.정의한메서드이름WithCheck(Context)
                // --> 이 형식의 메서드가 라이브러리에 의해서 자동 생성된다.
                MainActivityPermissionsDispatcher.callPhoneWithCheck(MainActivity.this);
            }
        }); // end OnClickListener
    }

    /**
     * (2) 권한 요청 팝업에서 사용자가 특정 버튼을 누른 경우 호출된다.
     */

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 라이브러리가 생성한 기능에게 사용자의 선택값을 전달한다
        // 라이브러리는 사용자의 선택값을 분석하여
        // 상황에 맞는 메서드를 호출한다.
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this,
                requestCode, grantResults);
    }

    /**
     * (3-1) 사용자가 CALL_PHONE에 대한 권한을 승인 한 경우 호출된다.
     */
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void callPhone() {
        String phoneNumber = editText.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

        try {
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    /**
     * (3-2) 이미 거부되어 있는 상태에서 지속적으로 기능을 호출하는 경우
     */
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void onShowRationale(final PermissionRequest request) {
        showPermissionDialog();
    }

    /**
     * (3-3) 권한 거부시에 호출됨
     */
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void onPermissionDenied() {
        showPermissionDialog();
    }

    /**
     * (3-4) 다시 묻지 않음을 선택해 놓은 경우 호출됨
     */
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
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
        final String appName = getApplicationInfo().loadLabel(getPackageManager()).toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림");
        builder.setMessage("전화걸기 기능 사용을 거부하셨습니다. " +
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
