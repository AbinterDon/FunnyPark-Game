package com.example.funnypark_game;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Game05 extends AppCompatActivity implements RecordThread.ChangeState {

    private TextView tRnd;
    private TextView tAlert;
    private EditText tNum;
    private Button btnSend;
    public static boolean isRunning = true;
    private int light=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game05);

        //設置螢幕保持亮起
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //初始手機當前畫面亮度為0
        setScreenBrightness(0);

        //verifyAudioPermissions(this);


        //get object
        tRnd=(TextView)findViewById(R.id.txtRnd);
        tNum=(EditText)findViewById(R.id.inNum);
        tAlert=(TextView)findViewById(R.id.txtAlert);


        btnSend=(Button)findViewById(R.id.btnSend);


        int cRnd =(int)(Math.random()*100000+999999);

        tRnd.setText(""+ cRnd );


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //取得玩家輸入數字
                String aNum = tNum.getText().toString();

                //判斷數字
                if (tRnd.getText().toString().equals(aNum))
                {
                    tAlert.setText("破關成功！！");
                }
                else
                {
                    tAlert.setText("輸入錯誤囉,再輸入一次");
                }
            }
        });


        // TODO Auto-generated method stub
        //isRunning = !isRunning;

        new RecordThread(this).start();

    }

    //設置手機螢幕亮度
    public void setScreenBrightness(int level) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = level / 255.0f;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void change() {
        // TODO Auto-generated method stub
        if(tAlert != null) {
            String text = tAlert.getText().toString();
            text = text + "\r\n吹粗来了!";
            tAlert.setText(text);
            setScreenBrightness(light);
            light+=10;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("TAG","關閉Game05...");
        Game05.this.finish();
    }

}
