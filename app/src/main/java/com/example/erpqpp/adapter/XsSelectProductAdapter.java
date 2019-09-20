package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.ui.SelectProductActivity;
import com.example.erpqpp.ui.XsproductListActivity;

import java.io.Serializable;
import java.util.List;

public class XsSelectProductAdapter extends RecyclerView.Adapter<XsSelectProductAdapter.SelectProductHodler> {
    private Context context;
    private XsproductListActivity xsproductListActivity;

    public List<XsproductListMode.DataBean.ListBean>  getList() {
        return list;
    }

    public void setList(List<XsproductListMode.DataBean.ListBean>  list) {
        this.list = list;
    }

    private List<XsproductListMode.DataBean.ListBean>  list;

    public XsSelectProductAdapter(Context context, XsproductListActivity xsproductListActivity) {
        this.context = context;
        this.xsproductListActivity=xsproductListActivity;
    }

    @NonNull
    @Override
    public SelectProductHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_xsselectproduct, viewGroup, false);
        SelectProductHodler selectProductHodler=new SelectProductHodler(inflate);
        return selectProductHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectProductHodler selectProductHodler, final int i) {

        selectProductHodler.name.setText(list.get(i).getPro_name());
        selectProductHodler.caizhi.setText(list.get(i).getWood_name());
        selectProductHodler.price.setText(list.get(i).getPrice());
        selectProductHodler.xilie.setText(list.get(i).getSeries());
        selectProductHodler.chengben.setText(list.get(i).getCost_price());

        selectProductHodler.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent();
                XsproductListMode.DataBean.ListBean listBean = list.get(i);
                intent.putExtra("list",listBean);
                xsproductListActivity.setResult(1012,intent);
                xsproductListActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class SelectProductHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView add;
        public TextView xilie;
        public TextView caizhi;
        public TextView price;
        public TextView chengben;
        public SelectProductHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            add=itemView.findViewById(R.id.add);
            xilie=itemView.findViewById(R.id.xilie);
            caizhi=itemView.findViewById(R.id.caizhi);
            price=itemView.findViewById(R.id.price);
            chengben=itemView.findViewById(R.id.chengben);
        }
    }
}
