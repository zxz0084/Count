package com.swufestu.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class thirdActivity extends AppCompatActivity {
    double dollar2,euro2,won2;
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
        dollar2=intent.getDoubleExtra("dollar_rate",0.0);
        euro2=intent.getDoubleExtra("euro_rate",0.0);
        won2=intent.getDoubleExtra("won_rate",0.0);
        et1.append(String.valueOf(dollar2));
        et2.append(String.valueOf(euro2));
        et3.append(String.valueOf(won2));
    }

    public void back(View view) {
        EditText et1=findViewById(R.id.editTextTextPersonName2);
        EditText et2=findViewById(R.id.editTextTextPersonName3);
        EditText et3=findViewById(R.id.editTextTextPersonName4);
        Intent first=new Intent(this,FirActivity.class);
        double newdo=Double.parseDouble(et1.getText().toString());
        double neweu=Double.parseDouble(et2.getText().toString());
        double newwo=Double.parseDouble(et3.getText().toString());
        first.putExtra("dollar_rate",newdo);
        first.putExtra("euro_rate",neweu);
        first.putExtra("won_rate",newwo);
        setResult(3,first);
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
}