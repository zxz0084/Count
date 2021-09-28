package com.swufestu.count;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);
        String dollar_rate1=sharedPreferences.getString("dollar_rate","");
        String euro_rate1=sharedPreferences.getString("euro_rate","");
        String won_rate1=sharedPreferences.getString("won_rate","");
        double dollar_rate=Double.parseDouble(dollar_rate1);
        double euro_rate=Double.parseDouble(euro_rate1);
        double won_rate=Double.parseDouble(won_rate1);
        //Intent intent=getIntent();
        //dollar2=intent.getDoubleExtra("dollar_rate",0.0);
        //euro2=intent.getDoubleExtra("euro_rate",0.0);
        //won2=intent.getDoubleExtra("won_rate",0.0);
        et1.append(String.valueOf(dollar_rate));
        et2.append(String.valueOf(euro_rate));
        et3.append(String.valueOf(won_rate));
    }

    public void back(View view) {
        EditText et1=findViewById(R.id.editTextTextPersonName2);
        EditText et2=findViewById(R.id.editTextTextPersonName3);
        EditText et3=findViewById(R.id.editTextTextPersonName4);
        Intent first=new Intent(this,FirActivity.class);
        double newdo=Double.parseDouble(et1.getText().toString());
        double neweu=Double.parseDouble(et2.getText().toString());
        double newwo=Double.parseDouble(et3.getText().toString());
        //first.putExtra("dollar_rate",newdo);
        //first.putExtra("euro_rate",neweu);
        //first.putExtra("won_rate",newwo);
        SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("dollar_rate",String.valueOf(newdo));
        editor.putString("euro_rate",String.valueOf(neweu));
        editor.putString("won_rate",String.valueOf(newwo));
        editor.apply();
        setResult(3,first);
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
}