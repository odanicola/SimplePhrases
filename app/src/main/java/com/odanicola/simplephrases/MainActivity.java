package com.odanicola.simplephrases;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    Field[] fields=R.raw.class.getFields();
    Map<Integer,Uri> mp3 = new HashMap<>();

    public void doPlay(View view){
        for (Map.Entry<Integer, Uri> entry : mp3.entrySet())
        {
            if(view.getId() == entry.getKey()){
                mPlayer = MediaPlayer.create(this, entry.getValue());

                if(mPlayer.isPlaying()){
                    mPlayer.stop();
                }

                mPlayer.start();

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int count=0; count < fields.length; count++){
            int id = getResources().getIdentifier("btn"+count, "id", getPackageName());
            Uri video = Uri.parse("android.resource://"+getPackageName()+"/raw/"+fields[count].getName());
            mp3.put(id,video);
        }
    }
}
