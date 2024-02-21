package com.choongang.exam03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;
    private FrameLayout[] contents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[3];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);

        contents = new FrameLayout[3];
        contents[0] = findViewById(R.id.content1);
        contents[1] = findViewById(R.id.content2);
        contents[2] = findViewById(R.id.content3);

        for(int i=0; i<buttons.length; i++){
            int num = i;
            buttons[i].setOnClickListener(v->{
                showContent(num);
            });
        }
    }
    private void showContent(int i){
        for(FrameLayout content : contents){
            content.setVisibility(View.GONE);
        }
        Log.i("BUTTON", String.valueOf(i));
        contents[i].setVisibility(View.VISIBLE);
    }
}