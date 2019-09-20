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
import com.example.erpqpp.ui.SelectDmProductActivity;
import com.example.erpqpp.ui.SelectSqProductActivity;

import java.io.Serializable;
import java.util.List;

public class SelectSqProductAdapter extends RecyclerView.Adapter<SelectSqProductAdapter.SelectProductHodler> {
    private Context context;
    private SelectSqProductActivity selectProductActivity;

    public List<SelectMgMode.DataBean.ProductAndUnitListBean> getList() {
        return list;
    }

    public void setList(List<SelectMgMode.DataBean.ProductAndUnitListBean> list) {
        this.list = list;
    }

    private List<SelectMgMode.DataBean.ProductAndUnitListBean> list;

    public SelectSqProductAdapter(Context context, SelectSqProductActivity selectProductActivity) {
        this.context = context;
        this.selectProductActivity=selectProductActivity;
    }

    @NonNull
    @Override
    public SelectProductHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_selectproduct, viewGroup, false);
        SelectProductHodler selectProductHodler=new SelectProductHodler(inflate);
        return selectProductHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectProductHodler selectProductHodler, final int i) {

        selectProductHodler.name.setText(list.get(i).getPro_name());
        selectProductHodler.caizhi.setText(list.get(i).getWood_name());
        selectProductHodler.price.setText(list.get(i).getPrice());
        selectProductHodler.xilie.setText(list.get(i).getSeries());

        selectProductHodler.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent();
                 SelectMgMode.DataBean.ProductAndUnitListBean productAndUnitListBean = list.get(i);
                 intent.putExtra("list", (Serializable) productAndUnitListBean);
                 selectProductActivity.setResult(1002,intent);
                 selectProductActivity.finish();
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
