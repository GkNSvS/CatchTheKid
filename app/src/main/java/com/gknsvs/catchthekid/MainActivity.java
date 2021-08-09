package com.gknsvs.catchthekid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView []image;
    TextView txtTime,txtScore;
    int time,score;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        hideImage();
        timeDownStart();
        runGame();

    }




    private void initialize() {
        image=new ImageView[12];
        image[0]=findViewById(R.id.imageView1);
        image[1]=findViewById(R.id.imageView2);
        image[2]=findViewById(R.id.imageView3);
        image[3]=findViewById(R.id.imageView4);
        image[4]=findViewById(R.id.imageView5);
        image[5]=findViewById(R.id.imageView6);
        image[6]=findViewById(R.id.imageView7);
        image[7]=findViewById(R.id.imageView8);
        image[8]=findViewById(R.id.imageView9);
        image[9]=findViewById(R.id.imageView10);
        image[10]=findViewById(R.id.imageView11);
        image[11]=findViewById(R.id.imageView12);
        txtTime=findViewById(R.id.txtTime);
        txtScore=findViewById(R.id.txtScore);
        time=0;
        score=0;
    }
    private void hideImage() {
        for(ImageView i:image)
        {
            i.setVisibility(View.INVISIBLE);
        }
    }


    public void incScore(View view){
        score++;
        txtScore.setText("Score : "+score);
        hideImage();

    }
    private void timeDownStart(){
        new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {
                txtTime.setText("Time : "+ (l/1000));
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                hideImage();
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Game Over");
                alertDialog.setMessage("Are you retry?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alertDialog.show();
            }
        }.start();
    }
    private void runGame(){
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                hideImage();
                Random random=new Random();
                int num=random.nextInt(11);
                image[num].setVisibility(View.VISIBLE);
                handler.postDelayed(this,300);
            }
        };
        handler.post(runnable);

    }
}