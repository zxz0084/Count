package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        if(s.length()>0&&!s.equals("Input RMB")){
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
        extracted();
    }

    private void extracted() {
        Intent second=new Intent(this,thirdActivity.class);
        //Intent first=new Intent(Intent.ACTION_VIE)
        second.putExtra("dollar_rate",dollar_rate);
        second.putExtra("euro_rate",euro_rate);
        second.putExtra("won_rate",won_rate);
        Log.i(TAG,""+dollar_rate);
        startActivityForResult(second,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==3){
            dollar_rate=data.getDoubleExtra("dollar_rate",0.0);
            euro_rate=data.getDoubleExtra("euro_rate",0.0);
            won_rate=data.getDoubleExtra("won_rate",0.0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.setting){
            extracted();
        }
        return super.onOptionsItemSelected(item);
    }
}