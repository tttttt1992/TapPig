package ru.ITschool.tappig;

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
    }

    public void tap(View view) {
        nTaps++;
        TextBottom.setText("Вы потапали "+nTaps+end());
    }

    String end(){
        if(nTaps%10 >1 && nTaps%10<5 && nTaps/10%10!=1){
            return " раза";
        }
        return " раз";

    }
}