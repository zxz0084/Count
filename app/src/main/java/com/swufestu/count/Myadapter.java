package com.swufestu.count;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Myadapter extends ArrayAdapter {
    public Myadapter(Context context, int resource, ArrayList<HashMap<String,String>> list){
        super(context,resource,list);
    }
public View getView(int position, View convertView, ViewGroup parent){
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_layout,parent,false);
        }
    Map<String,String> map=(Map<String, String>) getItem(position);
    TextView title=(TextView) view.findViewById(R.id.itemtitle);
    TextView detail=(TextView) view.findViewById(R.id.itemdetail);
    title.setText(map.get("itemtitle"));
    detail.setText(map.get("itemdetail"));
    return view;
}
}
