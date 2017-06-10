package com.example.yujin_000.a28_defaultfileio;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.textView);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString().trim(); //입력한 내용
                //저장될 파일의 이름과 저장할 내용을 지정
                save("helloworld.text",content);
            }
        }); //endOnClickListener
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = read("helloworld.txt");
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                textView.setText(content);
            }
        }); // end OnClickListener
   }

    void save(String path, String content){

        OutputStream os =null;      //파일 저장 객체

        try {
            // MODE_PRIVATE	 - 덮어쓰기 모드 (디폴트)
            // MODE_APPEND	 - 추가 모드
            os = openFileOutput(path, Context.MODE_PRIVATE);
            byte[] data = content.getBytes("UTF-8");
            os.write(data);
            Toast.makeText(MainActivity.this, "저장성공", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (os != null) {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String read(String path) {
        String read = null;             // 읽어들인 내용이 저장될 문자열
        InputStream is = null;     // 파일 읽기 객체

        try {
            is = openFileInput(path);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            read = new String(buffer, "UTF-8");
            Toast.makeText(MainActivity.this, "읽기성공", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return read;
    }
}





















