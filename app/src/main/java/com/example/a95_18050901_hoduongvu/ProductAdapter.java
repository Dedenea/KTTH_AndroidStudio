package com.example.a95_18050901_hoduongvu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    List<Product> list;
    Context context;
    int layout;
    String url="https://60b6e58117d1dc0017b8882b.mockapi.io/Product";

    public ProductAdapter(List<Product> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView ma,loai,gia,nguon;
        convertView= LayoutInflater.from(context).inflate(layout,parent,false);
        ma=convertView.findViewById(R.id.txtID);
        loai=convertView.findViewById(R.id.txtType);
        gia=convertView.findViewById(R.id.txtPrice);
        nguon=convertView.findViewById(R.id.txtCountry);
        ma.setText(list.get(position).getId());
        loai.setText(list.get(position).getType());
        gia.setText(list.get(position).getPrice());
        nguon.setText(list.get(position).getCountry());
        return convertView;
    }
}
