package com.example.exotest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://www.moreplex.com/trailer/CAREERWOMAN1562801819.webm
        mp = MediaPlayer.create(this, Uri.parse("http://newslistener.com/Mukesh/1.mp4"));
        SurfaceView sv = (SurfaceView) findViewById(R.id.surface);
        //mp.addTimedTextSource(this, "", MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
/*
        try
        {
            sv.setVideoURI(Uri.parse("http://www.MY_DOMAIN_NAME.com/videos/video1.mp4"));
            myVideoView.setMediaController(new MediaController(this));
            myVideoView.requestFocus();
            myVideoView.start();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "No Media found", Toast.LENGTH_LONG).show();
        }*/

        SurfaceHolder holder = sv.getHolder();
        sv.setSecure(true);

        holder.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mp.setDisplay(holder);
                mp.start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();

        if(null != mp) mp.release();
        mp = null;
    }
}