package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class forthActivity extends AppCompatActivity {
    private static final String TAG="forth";
    Handler handler=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        ListView mylist=findViewById(R.id.mylist);
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==9){
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(forthActivity.this, android.R.layout.simple_list_item_1,rlist);
                    mylist.setAdapter(adapter);
                }else if(msg.what==6){
                    Bundle bundle=(Bundle) msg.obj;
                    double dollar_rate=bundle.getDouble("dollar_rate");
                    Log.i(TAG,"rate"+String.valueOf(dollar_rate));
                }
            }
        };
        MyTask task=new MyTask();
        //FirActivity fir=new FirActivity();
        task.setHandler(handler);
        Thread thread=new Thread(task);
        thread.start();
    }
}