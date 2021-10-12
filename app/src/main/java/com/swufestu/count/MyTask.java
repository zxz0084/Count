package com.swufestu.count;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTask  implements  Runnable{
    private static final String TAG="MyTask";
    List<String> list=new ArrayList<String>();
    private Handler handler;
    public void  setHandler(Handler handler){
        this.handler=handler;
    }
    public void run() {
        Log.i(TAG,"run.........");
        int count=0;
        Bundle bdl=new Bundle();
        try{
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG,doc.title());
            Elements tables = doc.getElementsByTag("table");//id是唯一的，Tag返回一个集合
            Element first = tables.get(1);
            Elements trs = first.getElementsByTag("tr");
            trs.remove(0);
            for (Element tr :trs){
                //Log.i(TAG,"run:tr="+tr);
                Elements tds = tr.getElementsByTag("td");
                Element td1 = tds.get(0);
                Element td2 = tds.get(5);
                Log.i(TAG,"td1="+td1.text()+" \t td2="+td2.text());
                list.add(td1.text()+"==>"+td2.text());
                /*
                if("美元".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("dollar_rate",100/Double.parseDouble(rate1));
                }else if("欧元".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("euro_rate",100/Double.parseDouble(rate1));
                }else if("韩元".equals(td1.text())){
                    String rate1=td2.text();
                    bdl.putDouble("won_rate",100/Double.parseDouble(rate1));
                }*/
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        bdl.putInt("num",count);
        Message msg=handler.obtainMessage();
        msg.what=6;
        msg.obj=list;
        handler.sendMessage(msg);
    }
}
