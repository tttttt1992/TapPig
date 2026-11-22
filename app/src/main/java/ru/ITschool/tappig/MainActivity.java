package ru.ITschool.tappig;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
int nTaps;
TextView TextTop;
TextView TextBottom;
ImageView ImgPig;
SoundPool soundPool;
int pigSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextTop = findViewById(R.id.TextTop);
        TextBottom = findViewById(R.id.TextBottom);
        ImgPig = findViewById(R.id.ImagePig);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                        .setAudioAttributes(audioAttributes)
                            .build();
        pigSoundId = soundPool.load(this, R.raw.pig_sound, 1);
        loadPig();
        updateTaxtBottom();

    }

    @Override
    protected void onStop() {
        super.onStop();
        savePig();
    }

    public void tap(View view) {
        nTaps++;
        updateTaxtBottom();
        soundPool.play(pigSoundId, 1f, 1f, 1, 0, 1f);
    }
    protected  void  onDestroy(){
        super.onDestroy();
        if(soundPool != null){
            soundPool.release();
            soundPool = null;
        }
    }
    void updateTaxtBottom(){
        TextBottom.setText("Вы потапали "+nTaps+end());
    }

    String end(){
        if(nTaps%10 >1 && nTaps%10<5 && nTaps/10%10!=1){
            return " раза";
        }
        return " раз";
    }

    public  void  savePig(){
        SharedPreferences prefs = getSharedPreferences("PigsPrefs", Context.MODE_PRIVATE);
        prefs.edit().putInt("num taps", nTaps).apply();
    }

    public void loadPig(){
        SharedPreferences prefs = getSharedPreferences("PigsPrefs", Context.MODE_PRIVATE);
        nTaps = prefs.getInt("num taps", 0);
    }
}