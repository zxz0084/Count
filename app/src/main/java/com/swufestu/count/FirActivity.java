package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class FirActivity extends AppCompatActivity implements Runnable {
   private static final String TAG="FirActivity";
    double dollar_rate;
    double euro_rate;
    double won_rate;
    Handler handler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fir);
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);
        String dollar_rate1=sharedPreferences.getString("dollar_rate","");
        String euro_rate1=sharedPreferences.getString("euro_rate","");
        String won_rate1=sharedPreferences.getString("won_rate","");
        dollar_rate=Double.parseDouble(dollar_rate1);
        euro_rate=Double.parseDouble(euro_rate1);
        won_rate=Double.parseDouble(won_rate1);
        TextView result=findViewById(R.id.textView5);
        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG,"handleMessage:accept Message");
                if(msg.what==6){
                    //接收消息
                    //Intent intent=getIntent();
                    Bundle bundle=(Bundle) msg.obj;
                    dollar_rate=bundle.getDouble("dollar_rate");
                    euro_rate=bundle.getDouble("euro_rate");
                    won_rate=bundle.getDouble("won_rate");

                    SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("dollar_rate",String.valueOf(dollar_rate));
                    editor.putString("euro_rate",String.valueOf(euro_rate));
                    editor.putString("won_rate",String.valueOf(won_rate));
                    editor.apply();
                    Toast.makeText(FirActivity.this,"汇率已更新",Toast.LENGTH_SHORT).show();
                }

                super.handleMessage(msg);
            }
        };
        Thread thread=new Thread(this);
        thread.start();//this.run();
    }

    public void click3(View view) {
        EditText et=findViewById(R.id.editTextTextPersonName);
        TextView te=findViewById(R.id.textView5);
        String s =et.getText().toString();
        if(s.length()>0&&!s.equals("Input RMB")){
            double v = Double.parseDouble(s);
            if(view.getId()==R.id.button8){
                //SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                //PreferenceManager.getDefaultSharedPreferences(this);
                //String dollar_rate1=sharedPreferences.getString("dollar_rate","");
                //dollar_rate=Double.parseDouble(dollar_rate1);
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
        //second.putExtra("dollar_rate",dollar_rate);
        //second.putExtra("euro_rate",euro_rate);
        //second.putExtra("won_rate",won_rate);
        //Log.i(TAG,""+dollar_rate);
        startActivityForResult(second,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==3){
            SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            //PreferenceManager.getDefaultSharedPreferences(this);
            String dollar_rate1=sharedPreferences.getString("dollar_rate","");
            String euro_rate1=sharedPreferences.getString("euro_rate","");
            String won_rate1=sharedPreferences.getString("won_rate","");
            dollar_rate=Double.parseDouble(dollar_rate1);
            euro_rate=Double.parseDouble(euro_rate1);
            won_rate=Double.parseDouble(won_rate1);
            //dollar_rate=data.getDoubleExtra("dollar_rate",0.0);
            //euro_rate=data.getDoubleExtra("euro_rate",0.0);
            //won_rate=data.getDoubleExtra("won_rate",0.0);
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
        /*try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
       /* URL url=null;
        try {
            Log.i(TAG,"run:访问 url");
            url=new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            InputStream in =http.getInputStream();
            String html=inputStream2String(in);
            Log.i(TAG,"run:html="+html);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }*/
        Bundle bdl=new Bundle();
        try{
            Document doc = Jsoup.connect("https://www.usd-cny.com/").get();
            //Log.i(TAG,doc.title());
            Elements tables = doc.getElementsByTag("table");//id是唯一的，Tag返回一个集合
            Element first = tables.first();
            Elements trs = first.getElementsByTag("tr");
            trs.remove(0);
            for (Element tr :trs){
              //Log.i(TAG,"run:tr="+tr);
                Elements tds = tr.getElementsByTag("td");
                Element td1 = tds.get(0);
                Element td2 = tds.get(4);
                Log.i(TAG,"td1="+td1.text()+" \t td2="+td2.text());
                if("美元".equals(td1.text())){
                     String rate1=td2.text();
                     bdl.putDouble("dollar_rate",100/Double.parseDouble(rate1));
                }else if("欧元".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("euro_rate",100/Double.parseDouble(rate1));
                }else if("韩元".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("won_rate",100/Double.parseDouble(rate1));
                }
            }
            //Log.i(TAG,"run:table:"+first);
         //   Elements tds = first.getElementsByTag("td");
         //t i=0;i<tds.size();i+=5){
          //ement element1= tds.get(i);
            //ement element2 = tds.get(i + 1);
          //Log.i(TAG,"td1="+ element1.text()+" \t td2="+element2.text());
           //ring str1=element1.text();
         //   for(Element td:tds){
           //     Log.i(TAG,"run:table:"+td.text());
            //}
           // Element th2 = ths.get(0);
            //Log.i(TAG,"run:"+th2);
        }catch (IOException e){
            e.printStackTrace();
        }
       // Intent intent=new Intent();
       // intent.putExtras(bdl);
       Message msg=handler.obtainMessage();
        msg.what=6;
        msg.obj=bdl;
        handler.sendMessage(msg);
    }
  /*  private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize=1024;
        final char[]buffer=new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"gb2312");
        while (true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0) break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }    */
}