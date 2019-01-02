package com.blueline.harvbest.blueline.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.blueline.harvbest.blueline.Login.Login_Blue;
import com.blueline.harvbest.blueline.R;


public class Splash extends AppCompatActivity {
    public static final int segundos= 8;
    public static final int milisegundos= segundos*1000;
    public ProgressBar progreso;
    public static  final int delay = 2;
    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progreso = (ProgressBar) findViewById(R.id.progressBar);
        iv= (ImageView) findViewById(R.id.imageLogo);
        progreso.setMax(maximo_progreso());
        empezaranimacion();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animacion);
        iv.startAnimation(animation);
    }

    public  void empezaranimacion()
    {
        new CountDownTimer(milisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            progreso.setProgress(establecer_progreso(millisUntilFinished));
            }

            @Override
            public void onFinish() {
            Intent nuevo = new Intent(Splash.this, Login_Blue.class);
            startActivity(nuevo);
            finish();
            }
        }.start();
    }

    public int establecer_progreso(long miliseconds){
        return (int)((milisegundos - miliseconds)/1000);
    }

    public int maximo_progreso(){
        return segundos-delay;
    }
}
