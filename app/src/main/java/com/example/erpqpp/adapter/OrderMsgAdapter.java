package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.OrderMsgMode;

import java.util.List;

public class OrderMsgAdapter extends RecyclerView.Adapter<OrderMsgAdapter.OrderMsgHodler> {
    private Context context;
    private List<OrderMsgMode.DataBean> list;
    private Itemclick item;

    public List<OrderMsgMode.DataBean> getList() {
        return list;
    }

    public void setList(List<OrderMsgMode.DataBean> list) {
        this.list = list;
    }

    public OrderMsgAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderMsgHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_ordermsg, viewGroup, false);
        OrderMsgHodler orderMsgHodler=new OrderMsgHodler(inflate);
        return orderMsgHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderMsgHodler orderMsgHodler, final int i) {
        orderMsgHodler.msg_date.setText(list.get(i).getAdd_time());


        if (item!=null){
            //holder为Myhodler holder的参数
            orderMsgHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(orderMsgHodler.itemView,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class OrderMsgHodler extends RecyclerView.ViewHolder{
        public TextView msg_date;
        public TextView msg_name;
        public OrderMsgHodler(@NonNull View itemView) {
            super(itemView);
            this.msg_date=itemView.findViewById(R.id.msg_date);
            this.msg_name=itemView.findViewById(R.id.msg_name);
        }
    }


    //定义接口
    public interface Itemclick{
        void itemclick(View view,int position);
    }

    //定义方法   参数1为接口  2为接口的变量名
    public void huidiao(Itemclick item){
        this.item=item;
    }

}
