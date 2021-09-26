package com.swufestu.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class thirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void get(View view) {
        EditText et1=findViewById(R.id.editTextTextPersonName2);
        EditText et2=findViewById(R.id.editTextTextPersonName3);
        EditText et3=findViewById(R.id.editTextTextPersonName4);
        Intent intent=getIntent();
        double dollar2=intent.getDoubleExtra("dollar_rate",0.0);
        double euro2=intent.getDoubleExtra("euro_rate",0.0);
        double won2=intent.getDoubleExtra("won_rate",0.0);
        et1.append(String.valueOf(dollar2));
        et2.append(String.valueOf(euro2));
        et3.append(String.valueOf(won2));
    }

    public void back(View view) {
        Intent first=new Intent(this,FirActivity.class);
        startActivity(first);
    }
}