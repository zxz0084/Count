package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FirActivity extends AppCompatActivity implements Runnable {
   private static final String TAG="FirActivity";
    double dollar_rate;
    double euro_rate;
    double won_rate;
    Handler handler=null;
    public void  setHandler(Handler handler){
        this.handler=handler;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir);
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //PreferenceManager.getDefaultSharedPreferences(this);
        int flag=sharedPreferences.getInt("flag",0);
        String time=sharedPreferences.getString("time","2021-09-30");
        //LocalDate date=LocalDate.now();
        LocalDateTime dateTime=LocalDateTime.now();
        LocalDate date=LocalDate.now();
        LocalDate date2 = LocalDate.parse(time);
        DateTimeFormatter struct=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formant2=struct.format(dateTime);
        String format=struct.format(date);
        String format1=struct.format(date2);
        Log.i(TAG,"datenow="+format+" \n date2="+time+"\n format2"+formant2);
        //????????????flag???0.????????????????????????????????????flag?????????????????????????????????
        //??????flag!=0????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        if(flag==0){
            //SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            Log.i(TAG,"flag="+flag);
            editor.putInt("flag",1);
            //DateTimeFormatter struct=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //String format=struct.format(date);
            editor.putString("time",format);
            handler=new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    Log.i(TAG,"handleMessage:accept Message");
                    if(msg.what==6){
                        //????????????
                        //Intent intent=getIntent();
                        Bundle bundle=(Bundle) msg.obj;
                        dollar_rate=bundle.getDouble("dollar_rate");
                        euro_rate=bundle.getDouble("euro_rate");
                        won_rate=bundle.getDouble("won_rate");
                        editor.putString("dollar_rate",String.valueOf(dollar_rate));
                        editor.putString("euro_rate",String.valueOf(euro_rate));
                        editor.putString("won_rate",String.valueOf(won_rate));
                        Log.i(TAG,"date="+format+"????????????:"+"\n ?????????"+dollar_rate+"\n ??????"+euro_rate+"\n ??????"+won_rate);
                        Toast.makeText(FirActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                    }
                    editor.apply();
                    super.handleMessage(msg);
                }
            };
            Thread thread=new Thread(this);
            thread.start();//this.run();
        }else{
            if(date.isAfter(date2)){
                //DateTimeFormatter struct=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //String format=struct.format(date);
                //String format1=struct.format(date2);
                Log.i(TAG,"datenow="+date+"date2="+date2);
                editor.putString("time",format);
                handler=new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        Log.i(TAG,"handleMessage:accept Message");
                        if(msg.what==6){
                            //????????????
                            //Intent intent=getIntent();
                            Bundle bundle=(Bundle) msg.obj;
                            dollar_rate=bundle.getDouble("dollar_rate");
                            euro_rate=bundle.getDouble("euro_rate");
                            won_rate=bundle.getDouble("won_rate");
                            editor.putString("dollar_rate",String.valueOf(dollar_rate));
                            editor.putString("euro_rate",String.valueOf(euro_rate));
                            editor.putString("won_rate",String.valueOf(won_rate));
                            Log.i(TAG,"date="+format+"????????????:"+"\n ?????????"+dollar_rate+"\n ??????"+euro_rate+"\n ??????"+won_rate);
                            Toast.makeText(FirActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                        }
                        editor.apply();
                        super.handleMessage(msg);
                    }
                };
                Thread thread=new Thread(this);
                thread.start();//this.run();
            } else {
                String dollar_rate1=sharedPreferences.getString("dollar_rate","");
                String euro_rate1=sharedPreferences.getString("euro_rate","");
                String won_rate1=sharedPreferences.getString("won_rate","");
                dollar_rate=Double.parseDouble(dollar_rate1);
                euro_rate=Double.parseDouble(euro_rate1);
                won_rate=Double.parseDouble(won_rate1);
                Log.i(TAG,"????????????????????????");
                Toast.makeText(FirActivity.this,"??????????????????????????????",Toast.LENGTH_SHORT).show();
            }
        }
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
        startActivityForResult(second,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==3){
            SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            String dollar_rate1=sharedPreferences.getString("dollar_rate","");
            String euro_rate1=sharedPreferences.getString("euro_rate","");
            String won_rate1=sharedPreferences.getString("won_rate","");
            dollar_rate=Double.parseDouble(dollar_rate1);
            euro_rate=Double.parseDouble(euro_rate1);
            won_rate=Double.parseDouble(won_rate1);
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

    @Override
    public void run() {
        Log.i(TAG,"run.........");
        Bundle bdl=new Bundle();
        try{
            Document doc = Jsoup.connect("https://www.usd-cny.com/").get();
            //Log.i(TAG,doc.title());
            Elements tables = doc.getElementsByTag("table");//id???????????????Tag??????????????????
            Element first = tables.first();
            Elements trs = first.getElementsByTag("tr");
            trs.remove(0);
            for (Element tr :trs){
              //Log.i(TAG,"run:tr="+tr);
                Elements tds = tr.getElementsByTag("td");
                Element td1 = tds.get(0);
                Element td2 = tds.get(4);
                Log.i(TAG,"td1="+td1.text()+" \t td2="+td2.text());
                if("??????".equals(td1.text())){
                     String rate1=td2.text();
                     bdl.putDouble("dollar_rate",100/Double.parseDouble(rate1));
                }else if("??????".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("euro_rate",100/Double.parseDouble(rate1));
                }else if("??????".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("won_rate",100/Double.parseDouble(rate1));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
       Message msg=handler.obtainMessage();
        msg.what=6;
        msg.obj=bdl;
        handler.sendMessage(msg);
    }
}