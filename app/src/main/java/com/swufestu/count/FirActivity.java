package com.swufestu.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class FirActivity extends AppCompatActivity {
   private static final String TAG="FirActivity";
   double dollar_rate=0.35;
   double euro_rate=0.28;
   double won_rate=0.1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir);
    }

    public void click3(View view) {
        EditText et=findViewById(R.id.editTextTextPersonName);
        TextView te=findViewById(R.id.textView5);
        String s =et.getText().toString();
        if(s.length()>0){
            double v = Double.parseDouble(s);
            if(view.getId()==R.id.button8){
                v=v*dollar_rate;
            }else if(view.getId()==R.id.button9){
                v=v*euro_rate;
            }else if(view.getId()==R.id.button9){
                v=v*won_rate;
            }
            DecimalFormat df=new DecimalFormat("#.00");
            String V=df.format(v);
            te.setText(V);
        }else{
            Toast.makeText(this,"please input RMB",Toast.LENGTH_SHORT).show();
        }
    }

    public void open(View view) {
        Intent second=new Intent(this,thirdActivity.class);
        //Intent first=new Intent(Intent.ACTION_VIE)
        second.putExtra("dollar_rate",dollar_rate);
        second.putExtra("euro_rate",euro_rate);
        second.putExtra("won_rate",won_rate);
        startActivity(second);
    }
}