package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;

import java.util.List;

public class MarkettjDetailsAdapter extends RecyclerView.Adapter<MarkettjDetailsAdapter.MarkettjHodler> {
    private Context context;
    private List<MarkettjDetailsMode.DataBean.ProListBean> data;



    public List<MarkettjDetailsMode.DataBean.ProListBean> getData() {
        return data;
    }

    public void setData(List<MarkettjDetailsMode.DataBean.ProListBean> data) {
        this.data = data;
    }

    public MarkettjDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MarkettjHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_markettjdetails, viewGroup, false);
        MarkettjHodler markettjHodler=new MarkettjHodler(inflate);
        return markettjHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MarkettjHodler markettjHodler, int i) {
        markettjHodler.name.setText(data.get(i).getPro_name());
        markettjHodler.caizhi.setText(data.get(i).getWood_name());
        markettjHodler.xilie.setText(data.get(i).getSeries());
        markettjHodler.color.setText(data.get(i).getColor_name());
        markettjHodler.count.setText(data.get(i).getSale_num()+"");
        markettjHodler.dprice.setText(data.get(i).getSale_price()+"");

        List<MarkettjDetailsMode.DataBean.ProListBean.Unit> unit_list = data.get(i).getUnit_list();

        MarkettjDetailZiAdapter markettjDetailZiAdapter=new MarkettjDetailZiAdapter(context);
        markettjDetailZiAdapter.setUnit_list(unit_list);
        markettjHodler.rc.setAdapter(markettjDetailZiAdapter);
        markettjHodler.rc.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class MarkettjHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView danjian;
        public TextView color;
        public TextView price;
        public TextView dprice;
        public TextView xilie;
        public TextView caizhi;
        public TextView date;
        public TextView count;
        public RecyclerView rc;
        public MarkettjHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            danjian=itemView.findViewById(R.id.danjian);
            color=itemView.findViewById(R.id.color);
            price=itemView.findViewById(R.id.price);
            dprice=itemView.findViewById(R.id.dprice);
            caizhi=itemView.findViewById(R.id.caizhi);
            xilie=itemView.findViewById(R.id.xilie);
            count=itemView.findViewById(R.id.count);
            rc=itemView.findViewById(R.id.rc);
        }
    }
}
