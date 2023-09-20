package com.example.funnypark_game;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

public class GameInfo extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private String GAME_id;
    private SoundPool sound;
    private int startMusic;
    private AudioManager am;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameinfomation);

        final Intent getData =this.getIntent();//實體化 抓資料
        GAME_id = getData.getStringExtra("GAME_CLASS");

        Log.d("TAG",GAME_id);

        //設置螢幕保持亮起
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        am=(AudioManager)getSystemService(Context.AUDIO_SERVICE); //獲取AUDIO服務
        //取得手機最大音量
        //am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        //調整手機音量
        am.setStreamVolume(AudioManager.STREAM_MUSIC,8,0);

        sound = new SoundPool(10, AudioManager.STREAM_MUSIC,5);
        startMusic=sound.load(this,R.raw.start,1);

        //遊戲說明內容
        if(GAME_id.equals("GAME01"))
        {
            //能源Ｘ
            GameDialog("將手機「上下甩動」收集能源\n收集能源至100%即可破關成功!!\n\np.s.甩動越大力,能源收集越快哦!!\n但不要把手機甩出去囉ＱＱ");
        }
        else if(GAME_id.equals("GAME02"))
        {
            //環環相扣
            GameDialog("用「水平擺動」將兩個圓圈合而為一\n即可破關成功!!");
        }
        else if(GAME_id.equals("GAME03"))
        {
            //黑暗援助
            GameDialog("用手電筒把手機照亮吧!!\n\n將「目前亮度」控制在\n最高與最低亮度範圍內\n進度達100即可破關成功!!\n\np.s.不要太亮或太暗\n不只亮度還要注意距離\n(注意：亮度感測器在手機上方）");
        }
        else if(GAME_id.equals("GAME04"))
        {
            //時鐘沙漏
            GameDialog("將手機向上或向下「水平翻轉」\n調整到正確時間即可破關成功!!\n\n向上:增加時間\n向下:減少時間\n水平:比對時間\n\np.s.不要逞一時之快\n(注意：有耐心才能破關)");
        }
        else if(GAME_id.equals("GAME05"))
        {
            //迷霧解謎
            GameDialog("用手電筒把手機照亮吧!!\n\n將「目前亮度」控制在\n最高與最低亮度範圍內\n進度達100即可破關成功!!\n\np.s.不要太亮或太暗\n不只亮度還要注意距離\n(注意：亮度感測器在手機上方）");


        }
        showMessage(builder);
    }

    //訊息
    private void GameDialog(String Message)
    {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("遊戲說明");
        builder.setMessage(Message);
        builder.setPositiveButton("START", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //播放音效
                playMusic();

                final Intent jump = new Intent();//跳畫面
                if(GAME_id.equals("GAME01"))
                {
                    jump.setClass(GameInfo.this, Game01.class);
                    startActivity(jump);
                }
                else if(GAME_id.equals("GAME02"))
                {
                    jump.setClass(GameInfo.this, Game02.class);
                    startActivity(jump);

                }
                else if(GAME_id.equals("GAME03"))
                {
                    jump.setClass(GameInfo.this, Game03.class);
                    startActivity(jump);

                }
                else if(GAME_id.equals("GAME04"))
                {
                    jump.setClass(GameInfo.this, Game04.class);
                    startActivity(jump);

                }
                else if(GAME_id.equals("GAME05"))
                {
                    jump.setClass(GameInfo.this, Game05.class);
                    startActivity(jump);

                }

            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameInfo.this.finish();
            }
        });
    }

    //播放音效
    private void playMusic()
    {
        sound.play(this.startMusic,1,1,0,0,1);
    }

    //顯示訊息
    private void showMessage(AlertDialog.Builder builder)
    {
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false); //禁止點選Dialog以外的範圍
        dialog.setCancelable(false); //禁止點選back button
        dialog.show(); //顯示遊戲說明訊息
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("TAG","關閉GameInfo...");
        GameInfo.this.finish();
    }

}
