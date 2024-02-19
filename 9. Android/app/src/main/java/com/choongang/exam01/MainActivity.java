package com.choongang.exam01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 인플레이션 XML로 정의된 내용을 객체로 변환

        // Button button = findViewById(R.id.button); // <Button android:id="@+id/button" ~ />
        // Log.i("BUTTON", button.toString());
    }

    // 이벤트 발생한 뷰의 객체를 매개변수로 넣어줘야함
    public void onButton1Click(View v){
        Toast.makeText(this, "버튼 클릭!", Toast.LENGTH_SHORT).show();
    }

    public void onButton2Click(View v){
        // 데이터 전달 목적 -> Intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
        startActivity(intent); // 암시적 인텐트
    }

    public void onButton3Click(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(intent);
    }
}