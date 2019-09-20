package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.ui.MarkettjDetailsActivity;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

public class MarkettjAdapter extends RecyclerView.Adapter<MarkettjAdapter.MarkettjHodler> {
    private Context context;

    public MarkettjAdapter(Context context) {
        this.context = context;
    }
    private List<MarkettjMode.DataBean.ListBean> list;

    public List<MarkettjMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<MarkettjMode.DataBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MarkettjHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_markettj, viewGroup, false);
        MarkettjHodler markettjHodler=new MarkettjHodler(inflate);
        return markettjHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MarkettjHodler markettjHodler, final int i) {

        markettjHodler.markettj_item_date.setText(list.get(i).getAdd_time());
      //  markettjHodler.markettj_item_price.setText("本月销售金额"+list.get(i).getTotal());
        final MarkettjMode.DataBean.ListBean listBean = list.get(i);
        List<MarkettjMode.DataBean.ListBean.ProListBean> pro_list = list.get(i).getPro_list();

        MarkettjItemAdapter markettjItemAdapter=new MarkettjItemAdapter(context,listBean);
        markettjHodler.markettj_item_rc.setLayoutManager(new LinearLayoutManager(context));
        markettjItemAdapter.setPro_list(pro_list);
        markettjHodler.markettj_item_rc.setAdapter(markettjItemAdapter);

        markettjItemAdapter.setOrder_id(list.get(i).getOrder_id()+"");

        markettjItemAdapter.huidiao(new MarkettjItemAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Intent intent=new Intent(context, MarkettjDetailsActivity.class);
                intent.putExtra("order_id",list.get(i).getOrder_id());
                SPUtils.put(context,"order_id",list.get(i).getOrder_id()+"");
                context.startActivity(intent);
            }
        });
        markettjHodler.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MarkettjDetailsActivity.class);
                intent.putExtra("order_id",list.get(i).getOrder_id());
                SPUtils.put(context,"order_id",list.get(i).getOrder_id()+"");
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MarkettjHodler extends RecyclerView.ViewHolder{
        public TextView markettj_item_date;
        public RecyclerView markettj_item_rc;
        public LinearLayout ly;
        public MarkettjHodler(@NonNull View itemView) {
            super(itemView);
            this.markettj_item_date=itemView.findViewById(R.id.markettj_item_date);
            this.markettj_item_rc=itemView.findViewById(R.id.markettj_item_rc);
            this.ly=itemView.findViewById(R.id.ly);
        }
    }
}
