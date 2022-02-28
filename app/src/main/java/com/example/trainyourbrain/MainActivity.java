package com.example.trainyourbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_go, btn_1,btn_2, btn_3, btn_4, btn_playAgain;
    TextView tv_timer, tv_task,tv_score, tv_result;
    ConstraintLayout cl_game;
    GridLayout gridLayout;
    int locationOfCorrectAnswer;
    int score, numberOfQuestions = 0;

    ArrayList<Integer> answer = new ArrayList<Integer>();


    public void playAgain(View view){
        score = 0;
        numberOfQuestions =0;
        tv_timer.setText("30s");
        btn_1.setEnabled(true);
        btn_2.setEnabled(true);
        btn_3.setEnabled(true);
        btn_4.setEnabled(true);
        newQuestion();
        btn_playAgain.setVisibility(View.INVISIBLE);
        tv_result.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                tv_timer.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tv_result.setText("Fertig!");
                tv_timer.setText("0s");
                btn_playAgain.setVisibility(View.VISIBLE);

                btn_1.setEnabled(false);
                btn_2.setEnabled(false);
                btn_3.setEnabled(false);
                btn_4.setEnabled(false);
            }
        }.start();
    }


    public void setBtn_go(View view){
        btn_go.setVisibility(View.INVISIBLE);
        cl_game.setVisibility(View.VISIBLE);
        btn_playAgain.setVisibility(View.INVISIBLE);
        tv_result.setText("");
        playAgain(findViewById(R.id.tv_timer));

    }


    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            tv_result.setText("Korrekt!");
            score++;
        }else{
            tv_result.setText("Falsch!");
        }
        numberOfQuestions++;
        newQuestion();
    }

    public void newQuestion(){
        Random rnd = new Random();

        int a = rnd.nextInt(49);
        int b = rnd.nextInt(49);
        tv_task.setText(a + " + " + b);

        locationOfCorrectAnswer = rnd.nextInt(4);
        answer.clear();
        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                int wrongAnswer = rnd.nextInt(96);
                while (wrongAnswer == a+b){
                    wrongAnswer = rnd.nextInt(96);
                }
                answer.add(wrongAnswer);
            }
        }
        btn_1.setText(Integer.toString(answer.get(0)));
        btn_2.setText(Integer.toString(answer.get(1)));
        btn_3.setText(Integer.toString(answer.get(2)));
        btn_4.setText(Integer.toString(answer.get(3)));

        tv_score.setText(score + "/" + numberOfQuestions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl_game = findViewById(R.id.cl_game);
        gridLayout= findViewById(R.id.gridLayout);

        btn_go = findViewById(R.id.btn_go);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_playAgain = findViewById(R.id.btn_playAgain);

        tv_timer = findViewById(R.id.tv_timer);
        tv_task = findViewById(R.id.tv_task);
        tv_score = findViewById(R.id.tv_score);
        tv_result = findViewById(R.id.tv_result);

        cl_game.setVisibility(View.INVISIBLE);

        newQuestion();
    }
}