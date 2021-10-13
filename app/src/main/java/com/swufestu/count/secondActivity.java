package com.swufestu.count;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class secondActivity extends ListActivity {
    private static final String TAG="second";
    Handler handler=null;
    int count;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> list1=new ArrayList<String>();
        for(int i=1;i<100;i++){
            list1.add("item"+i);
        }
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==9){
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(secondActivity.this, android.R.layout.simple_list_item_1,rlist);
                    setListAdapter(adapter);
                }
            }
        };
        MyTask task=new MyTask();
        task.setHandler(handler);
        Thread thread=new Thread(task);
        thread.start();
        //setContentView(R.layout.activity_second);
    }
}

