package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.StatisticsMode;
import com.example.erpqpp.mvp.mode.WarehouseStatisticsMode;

import java.util.List;

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.StatisticsHodler> {
    private Context context;
    private  List<WarehouseStatisticsMode.DataBean.ListBeanX.ListBean> list;

    public  List<WarehouseStatisticsMode.DataBean.ListBeanX.ListBean> getList() {
        return list;
    }

    public void setList( List<WarehouseStatisticsMode.DataBean.ListBeanX.ListBean> list) {
        this.list = list;
    }

    public WarehouseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StatisticsHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_statistics, viewGroup, false);
        StatisticsHodler statisticsHodler=new StatisticsHodler(inflate);
        return statisticsHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsHodler statisticsHodler, int i) {
         statisticsHodler.name.setText("产品名称: "+list.get(i).getPro_name());
         statisticsHodler.order_id.setText("产品编号: "+list.get(i).getPro_num());
         statisticsHodler.order_stat.setText("产品单位: "+list.get(i).getUnit_name());
         statisticsHodler.daishouhuo.setText(list.get(i).getSn1_mgdsh()+"");
         statisticsHodler.mushou.setText(list.get(i).getSn2_mgsh()+"");
         statisticsHodler.damo.setText(list.get(i).getSn3_wmck()+"");
         statisticsHodler.damowei.setText(list.get(i).getSn4_dmwsh()+"");
         statisticsHodler.damoshou.setText(list.get(i).getSn5_dmsh()+"");
         statisticsHodler.yimo.setText(list.get(i).getSn6_ymck()+"");
         statisticsHodler.youqidai.setText(list.get(i).getSn7_yqdsh()+"");
         statisticsHodler.youshou.setText(list.get(i).getSn8_yqsh()+"");
         statisticsHodler.yqck.setText(list.get(i).getSn9_yqsh()+"");
         statisticsHodler.chengpin.setText(list.get(i).getSn10_cp()+"");
         statisticsHodler.yxswjh.setText(list.get(i).getSn11_yxswjh()+"");
         statisticsHodler.ythwjh.setText(list.get(i).getSn12_ythwjh()+"");
         statisticsHodler.yl.setText(list.get(i).getSn13_yl()+"");

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class StatisticsHodler extends RecyclerView.ViewHolder{
        public TextView daishouhuo;
        public TextView order_id;
        public TextView order_stat;
        public TextView name;
        public TextView mushou;
        public TextView damo;
        public TextView damowei;
        public TextView damoshou;
        public TextView yimo;
        public TextView youqidai;
        public TextView youshou;
        public TextView yqck;
        public TextView chengpin;
        public TextView yxswjh;
        public TextView ythwjh;
        public TextView yl;
        public StatisticsHodler(@NonNull View itemView) {
            super(itemView);
            daishouhuo=itemView.findViewById(R.id.daishouhuo);
            order_id=itemView.findViewById(R.id.order_id);
            mushou=itemView.findViewById(R.id.mushou);
            damo=itemView.findViewById(R.id.damo);
            damowei=itemView.findViewById(R.id.damowei);
            damoshou=itemView.findViewById(R.id.damoshou);
            yimo=itemView.findViewById(R.id.yimo);
            youqidai=itemView.findViewById(R.id.youqidai);
            youshou=itemView.findViewById(R.id.youshou);
            yqck=itemView.findViewById(R.id.yqck);
            chengpin=itemView.findViewById(R.id.chengpin);
            yxswjh=itemView.findViewById(R.id.yxswjh);
            ythwjh=itemView.findViewById(R.id.ythwjh);
            yl=itemView.findViewById(R.id.yl);
            order_stat=itemView.findViewById(R.id.order_stat);
            name=itemView.findViewById(R.id.name);
        }
    }
}
