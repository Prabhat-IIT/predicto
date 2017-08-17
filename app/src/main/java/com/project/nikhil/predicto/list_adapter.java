package com.project.nikhil.predicto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nikhil on 18/8/17.
 */

public class list_adapter  extends ArrayAdapter<wheather_data> {

    public list_adapter(Context context, ArrayList<wheather_data> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list_item_view=convertView;
        if(list_item_view==null){
            list_item_view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        wheather_data e=getItem(position);
        TextView temp =(TextView)list_item_view.findViewById(R.id.temp);
        TextView rain =(TextView)list_item_view.findViewById(R.id.rain);
        TextView description=(TextView)list_item_view.findViewById(R.id.description);
        TextView date =(TextView)list_item_view.findViewById(R.id.date);

        temp.setText(e.getTemp());
        rain.setText(e.getRain());
        date.setText(e.getDate());
        description.setText(e.getDescription());
        return list_item_view;
    }
}
