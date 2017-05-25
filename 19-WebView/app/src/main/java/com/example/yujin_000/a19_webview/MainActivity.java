package com.example.yujin_000.a19_webview;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        /**웹 뷰에서 설정 객체를 추출하여 기본 설정 수행하기**/
        WebSettings webSettings = webView.getSettings();
        webSettings.setSaveFormData(true);      //폼 입력값 저장 여부
        webSettings.setSupportZoom(true);       //줌 사용 여부 --> HTML Meta태그 설정이 우선함.
        webSettings.setJavaScriptEnabled(true); //자바스크립트 사용 가능 여부

        //웹 뷰에서 보조 객체를 연결
        webView.setWebViewClient(new MyViewClient());
        webView.setWebChromeClient(new MyChromeClient());
        webView.addJavascriptInterface(new MyInterfaceClass(), "myandroid");

        // 기본 페이지 설정하기
        webView.loadUrl("http://m.naver.com");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://m.naver.com");
            }
        }); // end OnClickListener

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://m.daum.net");
            }
        }); // end OnClickListener

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 프로젝트에 포함된 HTML 열기
                webView.loadUrl("file:///android_asset/portappmain.html");
            }
        }); // end OnClickListener
    }


    /**단말기의 Back-Key가 눌러진 경우 호출된다. **/
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            //상위 클래스의 원래 기능은 현재 Activity 종료
            super.onBackPressed();
        }
    }

    /** 웹뷰의 페이지 로드 시작, 종료, 페이지 이동시의 처리를 담당 */
    class MyViewClient extends WebViewClient {
        // 로딩다이얼로그
        // --> import android.app.ProgressDialog;
        ProgressDialog dlg;

        /** 페이지 로딩이 시작된 경우 호출된다. */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (dlg == null) {
                dlg = new ProgressDialog(MainActivity.this);
            }
            dlg.setMessage("Loading...");
            dlg.show();
        }

        /** 페이지 로딩이 완료되면 호출된다. */
        @Override
        public void onPageFinished(WebView view, String url) {
            if (dlg != null) {
                dlg.dismiss();
                dlg = null;
            }
        }

        /** 사용자가 링크를 클릭한 경우 실행된다. --> 안드로이드 API LEVEL 20(4.4) 이하 */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 웹뷰가 스스로 페이지를 이동하도록 강제 처리
            view.loadUrl(url);
            return true;
        }

        /** 사용자가 링크를 클릭한 경우 실행된다. --> 안드로이드 API LEVEL 21(5.0) 이상 */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // 상위 클래스의 원래 기능은 시스템 내장 브라우저를 호출하므로 제거한다.
            //return super.shouldOverrideUrlLoading(view, request);

            if (Build.VERSION.SDK_INT >= 21) {
                // 웹뷰 스스로 페이지를 이동하도록 URL설정
                view.loadUrl(request.getUrl().toString());
            }

            return true;
        }
    }
    /** Javascript의 alert, confirm, prompt 함수 처리를 담당한다. */
    class MyChromeClient extends WebChromeClient {
        // javascript의 alert함수를 호출했을 때 동작된다.
        // 이 곳에서 Alert 대화상자를 생성한다.
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final JsResult result) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.app_name)
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton("확인", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Javascript에게 리턴할 값
                            result.confirm();
                        }
                    })
                    .create()
                    .show();

            return true;
        }

        // javascript의 confirm함수를 호출했을 때 동작된다.
        // 이 곳에서 Confirm 대화상자를 생성한다.
        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.app_name)
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton("확인", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm(); // javascript에게 true리턴
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel(); // javascript에게 false리턴
                        }
                    })
                    .create()
                    .show();
            return true;
        }
    }

    /** 사용자 정의 클래스 */
    class MyInterfaceClass {
        @JavascriptInterface
        public void showToast(String msg) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String sum(final int x, final int y) {
            String result = String.format("%d + %d = %d", x, y, x+y);
            return result;
        }
    }

}
