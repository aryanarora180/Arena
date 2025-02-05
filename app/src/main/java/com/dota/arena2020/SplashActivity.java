package com.dota.arena2020;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dota.arena2020.MainActivity;


public class SplashActivity extends Activity implements Runnable {


    Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mThread = new Thread(this);

        mThread.start();
    }
    @Override
    public void run(){
        try {
            Thread.sleep(4550);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

}