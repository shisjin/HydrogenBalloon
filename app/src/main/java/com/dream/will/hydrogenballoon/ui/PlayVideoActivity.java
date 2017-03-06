package com.dream.will.hydrogenballoon.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.dream.will.hydrogenballoon.R;

/**
 */
public class PlayVideoActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    public VideoView videoView;
    private ImageView iv;
    private TextView tv;
    private RelativeLayout relativeLayout1,relativeLayout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playvideo_activity);

        initView();
    }

    private void initView() {

        iv = (ImageView) findViewById(R.id.iv_play);
        tv = (TextView) findViewById(R.id.tv_play);
        relativeLayout1= (RelativeLayout) findViewById(R.id.re_play);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.re_play_agin);
        videoView = (VideoView) findViewById(R.id.video_play);
        videoView.setVideoPath("android.resource://com.dream.will.hydrogenballoon/raw/" + R.raw.media);
        videoView.requestFocus();
        videoView.setOnCompletionListener(this);
    }


    /**
     * 开始播放
     */
    public void play_iv(View view){
        tv.setVisibility(View.VISIBLE);
        iv.setVisibility(View.GONE);
        relativeLayout1.setVisibility(View.GONE);
        videoView.start();
    }
    public void last_tv(View view){
        videoView.stopPlayback();
        skipActivity();
    }
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        relativeLayout2.setVisibility(View.VISIBLE);
        tv.setVisibility(View.GONE);

    }

    public void last_home(View view){
        skipActivity();
    }

    public void agin_play(View view){
        tv.setVisibility(View.VISIBLE);
        relativeLayout2.setVisibility(View.GONE);
        videoView.start();
    }

    public void skipActivity(){
        Intent intent = new Intent(PlayVideoActivity.this,HomeActivity.class);
        startActivity(intent);
        PlayVideoActivity.this.finish();
    }
}
