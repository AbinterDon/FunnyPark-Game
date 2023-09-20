package com.example.funnypark_game;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //錄音權限
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得button
        final Button game01 =  (Button)findViewById(R.id.btnGame01);//定義GAME01
        final Button game02 =  (Button)findViewById(R.id.btnGame02);//定義GAME02
        final Button game03 =  (Button)findViewById(R.id.btnGame03);//定義GAME03
        final Button game04 =  (Button)findViewById(R.id.btnGame04);//定義GAME04
        final Button game05 =  (Button)findViewById(R.id.btnGame05);//定義GAME05

        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);

        }
        else
        {
            //暫時關閉按鈕
            game02.setEnabled(false);
            //game05.setEnabled(false);

            final Intent jump = new Intent();//跳畫面

            game01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String GAME_id = "GAME01";
                    jump.setClass(MainActivity.this, GameInfo.class);
                    jump.putExtra("GAME_CLASS", GAME_id);//帶過去的變數名稱與資料變數名稱
                    startActivity(jump);
                }
            });

            game02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String GAME_id = "GAME02";
                    jump.setClass(MainActivity.this, GameInfo.class);
                    jump.putExtra("GAME_CLASS", GAME_id);//帶過去的變數名稱與資料變數名稱
                    startActivity(jump);
                }
            });

            game03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String GAME_id = "GAME03";
                    jump.setClass(MainActivity.this, GameInfo.class);
                    jump.putExtra("GAME_CLASS", GAME_id);//帶過去的變數名稱與資料變數名稱
                    startActivity(jump);
                }
            });
            game04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String GAME_id = "GAME04";
                    jump.setClass(MainActivity.this, GameInfo.class);
                    jump.putExtra("GAME_CLASS", GAME_id);//帶過去的變數名稱與資料變數名稱
                    startActivity(jump);
                }
            });
            game05.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String GAME_id = "GAME05";
                    jump.setClass(MainActivity.this, GameInfo.class);
                    jump.putExtra("GAME_CLASS", GAME_id);//帶過去的變數名稱與資料變數名稱
                    startActivity(jump);
                }
            });
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("TAG","程式已關閉...");
        System.exit(0);
    }


}
