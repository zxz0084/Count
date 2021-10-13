package com.swufestu.count;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class cacu extends AppCompatActivity {
    private static final String TAG="cacu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacu);
        //Intent first=new Intent(this,RateActivity.class);
        Intent intent=getIntent();
        String title = intent.getStringExtra("title");
        String detail = intent.getStringExtra("detail");
        TextView tit=findViewById(R.id.textget);
        TextView det=findViewById(R.id.textrate);
        EditText et=findViewById(R.id.editTextTextPersonName5);
        double x=Double.parseDouble(detail);
        double rate=x/100;
        tit.setText(title);
        //et.setText(detail);
        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG,"before"+charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s=charSequence.toString();
                double Rmb=Double.parseDouble(s);
                Log.i(TAG,"on"+detail+"i");
                det.setText(String.valueOf(Rmb*rate));
            }

            @Override
            public void afterTextChanged(Editable editable) {
              Log.i(TAG," "+editable);

            }
        };
        et.addTextChangedListener(textWatcher);
    }
}