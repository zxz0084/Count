package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RateActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    Handler handler=null;
    private static final String TAG="Rate";
    private Myadapter myadapter;
    ListView mylist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        mylist2=findViewById(R.id.mylist2);
        mylist2.setOnItemClickListener(this);
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==9){
                    ArrayList<HashMap<String,String>> listItems=new ArrayList<HashMap<String,String>>();
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    for(int i=0;i<rlist.size();i++){
                        String s=rlist.get(i);
                        String []pre=s.split(" ");
                        Log.i(TAG,pre[0]+pre[1]);
                        HashMap<String,String> map=new HashMap<String,String>();
                        map.put("itemtitle",pre[0]);
                        map.put("itemdetail",pre[1]);
                        listItems.add(map);
                    }

                   // SimpleAdapter simpleAdapter=new SimpleAdapter(RateActivity.this,listItems,
                     //       R.layout.list_layout,new String[]{"itemtitle","itemdetail"},new int[]{R.id.itemtitle,R.id.itemdetail});
                    Myadapter myadapter=new Myadapter(RateActivity.this,R.layout.list_layout,listItems);

                    mylist2.setAdapter(myadapter);
                    mylist2.setVisibility(View.VISIBLE);
                }
            }
        };
        MyTask task=new MyTask();
        task.setHandler(handler);
        Thread thread=new Thread(task);
        thread.start();

    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemPosition=mylist2.getItemAtPosition(i);
        HashMap<String,String> map=(HashMap<String, String>) itemPosition;
        String titleStr=map.get("itemtitle");
        String detailStr=map.get("itemdetail");
        Log.i(TAG,"onItemClick:titleStr="+titleStr);
        Log.i(TAG,"onItemClick:detailStr="+detailStr);

        TextView title=(TextView) view.findViewById(R.id.itemtitle);
        TextView detail=(TextView) view.findViewById(R.id.itemdetail);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());
        Log.i(TAG,"onItemClick:title2="+title2);
        Log.i(TAG,"onItemClick:detail2="+detail2);

        Intent second=new Intent(this,cacu.class);
        second.putExtra("title",title2);
        second.putExtra("detail",detail2);
        startActivityForResult(second,1);

}

}