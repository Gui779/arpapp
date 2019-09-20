package com.example.erpqpp.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.erpqpp.R;

import java.util.List;

public class UpdataversionAdapter extends RecyclerView.Adapter<UpdataversionAdapter.Myhodler> {

    private Context context;
   private List<String> list;

    public UpdataversionAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_updataversion, parent, false);
        Myhodler myhodler=new Myhodler(inflate);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(Myhodler holder, int position) {
       holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class Myhodler extends RecyclerView.ViewHolder{
        public TextView tv;
        public Myhodler(View itemView) {
            super(itemView);
            this.tv=itemView.findViewById(R.id.tv);
        }
    }
}
