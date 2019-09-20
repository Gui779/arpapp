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
import com.example.erpqpp.mvp.mode.DiscountMode;
import com.example.erpqpp.ui.DiscountActivity;
import com.example.erpqpp.utils.StatusUtils;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountHodler> {
    private Context context;
    private List<DiscountMode.DataBean.ListBean> list;
    private DiscountActivity discountActivity;

    public List<DiscountMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<DiscountMode.DataBean.ListBean> list) {
        this.list = list;
    }

    public DiscountAdapter(Context context, DiscountActivity discountActivity) {
        this.context = context;
        this.discountActivity = discountActivity;
    }

    @NonNull
    @Override
    public DiscountHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_discount, viewGroup, false);
        DiscountHodler discountHodler=new DiscountHodler(inflate);
        return discountHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountHodler discountHodler, final int i) {
        discountHodler.name.setText("客户名称: "+list.get(i).getCustomer_name());
        discountHodler.order_id.setText("订单编号："+list.get(i).getOrder_num()+"");
        discountHodler.zonge.setText("总额: "+list.get(i).getTotal());
        discountHodler.order_stat.setText("状态: "+ StatusUtils.getxsstatus(list.get(i).getStatus()));

        List<DiscountMode.DataBean.ListBean.ProListBean> pro_list = list.get(i).getPro_list();
        DiscountZiAdapter discountZiAdapter=new DiscountZiAdapter(context);
        discountZiAdapter.setPro_list(pro_list);
        discountHodler.discount_rc.setAdapter(discountZiAdapter);
        discountHodler.discount_rc.setLayoutManager(new LinearLayoutManager(context));

        discountHodler.jujie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountActivity.discountNo(list.get(i).getOrder_id()+"");
            }
        });

        discountHodler.tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountActivity.discountAgree(list.get(i).getOrder_id()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class DiscountHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView zonge;
        public TextView order_id;
        public TextView order_stat;
        public RecyclerView discount_rc;
        public TextView jujie;
        public TextView tongyi;
        public DiscountHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            zonge=itemView.findViewById(R.id.zonge);
            order_id=itemView.findViewById(R.id.order_id);
            order_stat=itemView.findViewById(R.id.order_stat);
            discount_rc=itemView.findViewById(R.id.discount_rc);
            jujie=itemView.findViewById(R.id.jujie);
            tongyi=itemView.findViewById(R.id.tongyi);

        }
    }
}
