package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.ui.MarkettjDetailsActivity;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

public class MarkettjItemAdapter extends RecyclerView.Adapter<MarkettjItemAdapter.MarkettjItemHodler> {
    private Context context;
    private Itemclick item;
    private  MarkettjMode.DataBean.ListBean listBean;
    private List<MarkettjMode.DataBean.ListBean.ProListBean> pro_list;
    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<MarkettjMode.DataBean.ListBean.ProListBean> getPro_list() {
        return pro_list;
    }

    public void setPro_list(List<MarkettjMode.DataBean.ListBean.ProListBean> pro_list) {
        this.pro_list = pro_list;
    }

    public Context getContext() {
        return context;
    }



    public MarkettjMode.DataBean.ListBean getListBean() {
        return listBean;
    }

    public void setListBean(MarkettjMode.DataBean.ListBean listBean) {
        this.listBean = listBean;
    }

    public MarkettjItemAdapter(Context context, MarkettjMode.DataBean.ListBean listBean) {
        this.context = context;
        this.listBean = listBean;
    }

    @NonNull
    @Override
    public MarkettjItemHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_markettjitem, viewGroup, false);
        MarkettjItemHodler markettjItemHodler=new MarkettjItemHodler(inflate);
        return markettjItemHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final MarkettjItemHodler markettjItemHodler, final int i) {

        markettjItemHodler.kehu_name.setText("客户名称: "+listBean.getCustomer_name());
        markettjItemHodler.date.setText("日期: "+listBean.getAdd_time());

        if (item!=null){
            //holder为Myhodler holder的参数
            markettjItemHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(markettjItemHodler.itemView,i);
                }
            });
        }

        MarkeAdaprer markeAdaprer=new MarkeAdaprer(context);
        markeAdaprer.setList(pro_list);
        markettjItemHodler.marke.setAdapter(markeAdaprer);
        markettjItemHodler.marke.setLayoutManager(new LinearLayoutManager(context));

        markeAdaprer.huidiao(new MarkeAdaprer.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Intent intent=new Intent(context, MarkettjDetailsActivity.class);
                SPUtils.put(context,"order_id",order_id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MarkettjItemHodler extends RecyclerView.ViewHolder{
        public TextView kehu_name;
        public RecyclerView marke;
        public TextView date;
        public TextView xiaoshouyuan;
        public MarkettjItemHodler(@NonNull View itemView) {
            super(itemView);
            this.kehu_name=itemView.findViewById(R.id.kehu_name);
            this.date=itemView.findViewById(R.id.date);
            this.xiaoshouyuan=itemView.findViewById(R.id.xiaoshouyuan);
            this.marke=itemView.findViewById(R.id.marke);
        }
    }



    public interface Itemclick{
        void itemclick(View view,int position);
    }

    //定义方法   参数1为接口  2为接口的变量名
    public void huidiao(Itemclick item){
        this.item=item;
    }

}
