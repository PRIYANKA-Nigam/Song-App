package com.example.song;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button b1,b2,b3;private SeekBar seekBar;private MediaPlayer mediaPlayer;
    private Handler handler;private Runnable runnable;@Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.bt);b2=(Button)findViewById(R.id.btf);b3=(Button)findViewById(R.id.btb);
        seekBar=(SeekBar)findViewById(R.id.seekBar);b1.setOnClickListener( this);b2.setOnClickListener(this);
        b3.setOnClickListener(this);handler=new Handler();
        mediaPlayer=MediaPlayer.create(this,R.raw.song);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {@Override
            public void onPrepared(MediaPlayer mp) {
            seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();changeSeekbar(); }});
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {@Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){ mediaPlayer.seekTo(progress); } }@Override
            public void onStartTrackingTouch(SeekBar seekBar) { }@Override
            public void onStopTrackingTouch(SeekBar seekBar) { }});
    } private void changeSeekbar() { seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {@Override
                public void run() {
                    changeSeekbar();
                }};
            handler.postDelayed(runnable, 1000); } }@Override
    public void onClick(View v) { switch (v.getId()){
            case R.id.bt:  if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();b1.setText("<>");
            }else { mediaPlayer.start();
                    b1.setText("||");changeSeekbar(); } break;
            case R.id.btf: mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);break;
            case R.id.btb: mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);break; } }
}