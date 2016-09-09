package com.example.moon7.threadtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private TextView Thr_text;
    private NumThread NumThr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thr_text=(TextView)findViewById(R.id.text);
        handler=new Handler();
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Btn_End:
                NumThr.stopThread();
                break;
            case R.id.Btn_Start:
                NumThr=new NumThread(true);
                NumThr.start();
                break;
            case R.id.Btn_test:
                Toast.makeText(MainActivity.this,"test ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class NumThread extends Thread{
        private int i=0;
        private boolean isPlay=false;

        public NumThread(boolean isPlay){
            this.isPlay=isPlay;
        }
        public void stopThread(){
            isPlay=!isPlay;
        }
        @Override
        public void run(){
            super.run();
            while (isPlay){
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Thr_text.setText(""+i++);
                    }
                });
            }
        }
    }
}
