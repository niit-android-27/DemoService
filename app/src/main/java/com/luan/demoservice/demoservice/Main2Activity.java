package com.luan.demoservice.demoservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button btnBindService,
            btnUnBindService,
            btnStartPlay,
            btnPause,
            btnResume;
    MyBoundService audioPlayerService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnBindService=(Button)findViewById(R.id.btnBindService);
        btnUnBindService=(Button)findViewById(R.id.btnUnbindService);
        btnStartPlay=(Button)findViewById(R.id.btnStart);
        btnPause=(Button)findViewById(R.id.btnPause);
        btnResume=(Button)findViewById(R.id.btnResume);

        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this,MyBoundService.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        btnStartPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioPlayerService!=null){
                    audioPlayerService.startPlaying();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioPlayerService!=null){
                    audioPlayerService.pause();
                }
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioPlayerService!=null){
                    audioPlayerService.resume();
                }
            }
        });

        btnUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audioPlayerService!=null) {
                    unbindService(serviceConnection);
                }
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder =(MyBoundService.MyBinder)iBinder;
            audioPlayerService= (MyBoundService)myBinder.getService();
            Log.i("Main2Activity","service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("Main2Activity","service disconnected");

        }
    };


}
