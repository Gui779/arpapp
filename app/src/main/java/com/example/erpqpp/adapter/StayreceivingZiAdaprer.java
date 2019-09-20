package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.mvp.mode.StayDeliverMode;

import java.util.List;

public class StayreceivingZiAdaprer extends RecyclerView.Adapter<StayreceivingZiAdaprer.MarkeHolder>{

    private Context context;
    private Itemclick item;

    public StayreceivingZiAdaprer(Context context) {
        this.context = context;
    }

    List<StayDeliverMode.DataBean.ListBean.ProListBean> list;

    public List<StayDeliverMode.DataBean.ListBean.ProListBean> getPro_list() {
        return list;
    }

    public void setPro_list(List<StayDeliverMode.DataBean.ListBean.ProListBean> pro_list) {
        this.list = pro_list;
    }

    @NonNull
    @Override
    public MarkeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_marke, viewGroup, false);
        MarkeHolder markeHolder=new MarkeHolder(inflate);
        return markeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MarkeHolder markeHolder, final int i) {
      markeHolder.chanpin_name.setText("产品名称: "+list.get(i).getPro_name()+" X "+list.get(i).getSale_num());
      markeHolder.price.setText(list.get(i).getSale_price());
        if (item!=null){
            //holder为Myhodler holder的参数
            markeHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(markeHolder.itemView,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MarkeHolder extends RecyclerView.ViewHolder{
         public TextView chanpin_name;
         public TextView price;
        public MarkeHolder(@NonNull View itemView) {
            super(itemView);
            chanpin_name= itemView.findViewById(R.id.chanpin_name);
            price= itemView.findViewById(R.id.price);
        }
    }

    //定义接口
    public interface Itemclick{
        void itemclick(View view, int position);
    }

    //定义方法   参数1为接口  2为接口的变量名
    public void huidiao(Itemclick item){
        this.item=item;
    }

}
