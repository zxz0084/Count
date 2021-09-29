package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        TextView score=findViewById(R.id.textView);
        String sum=score.getText().toString();
        if(view.getId()==R.id.button){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+1;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }else if(view.getId()==R.id.button2){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+2;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }else if(view.getId()==R.id.button3){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+3;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }

    }

    public void click1(View view) {
        TextView score=findViewById(R.id.textView4);
        String sum=score.getText().toString();
        if(view.getId()==R.id.button5){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+1;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }else if(view.getId()==R.id.button6){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+2;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }else if(view.getId()==R.id.button7){
            int sum1=Integer.parseInt(sum);
            sum1=sum1+3;
            DecimalFormat df=new DecimalFormat();
            String BM=df.format(sum1);
            score.setText(BM);
        }
    }

    public void click2(View view) {
        TextView score=findViewById(R.id.textView4);
        TextView score1=findViewById(R.id.textView);
        score.setText("0");
        score1.setText("0");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView score=findViewById(R.id.textView4);
        TextView score1=findViewById(R.id.textView);
        int score2=Integer.parseInt(score.getText().toString());
        int score3=Integer.parseInt(score1.getText().toString());
        outState.putInt("score2",score2);
        outState.putInt("score3",score3);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int score2=savedInstanceState.getInt("score2");
        int score3=savedInstanceState.getInt("score3");
        TextView score=findViewById(R.id.textView4);
        TextView score1=findViewById(R.id.textView);
        score.setText(String.valueOf(score2));
        score1.setText(String.valueOf(score3));
    }

}