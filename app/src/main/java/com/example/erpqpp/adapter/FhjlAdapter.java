package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.FhjlMode;

import java.util.List;

public class FhjlAdapter extends RecyclerView.Adapter<FhjlAdapter.FhjlHodler> {
    private Context context;
    private List<FhjlMode.DataBean> list;

    public FhjlAdapter(Context context) {
        this.context = context;
    }

    public List<FhjlMode.DataBean> getList() {
        return list;
    }

    public void setList(List<FhjlMode.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FhjlHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_fhjl, viewGroup, false);
        FhjlHodler fhjlHodler=new FhjlHodler(inflate);
        return fhjlHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull FhjlHodler fhjlHodler, int i) {
        fhjlHodler.name.setText("产品名称： "+list.get(i).getPro_name());
        fhjlHodler.order_id.setText("产品编号: "+list.get(i).getPro_num());
        fhjlHodler.order_stat.setText("产品单位: "+list.get(i).getUnit_name());
        fhjlHodler.caizhi.setText(list.get(i).getWood_name());
        fhjlHodler.price.setText(list.get(i).getCost());
        fhjlHodler.date.setText(list.get(i).getAdd_time());
        fhjlHodler.shuliang.setText(list.get(i).getUnit_num()+"");
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class FhjlHodler extends RecyclerView.ViewHolder{
         public TextView name;
         public TextView order_id;
         public TextView order_stat;
         public TextView caizhi;
         public TextView price;
         public TextView date;
         public TextView shuliang;
        public FhjlHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            order_id=itemView.findViewById(R.id.order_id);
            order_stat=itemView.findViewById(R.id.order_stat);
            caizhi=itemView.findViewById(R.id.caizhi);
            price=itemView.findViewById(R.id.price);
            date=itemView.findViewById(R.id.date);
            shuliang=itemView.findViewById(R.id.shuliang);
        }
    }
}
