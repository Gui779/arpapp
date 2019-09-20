package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.SelectMgMode;

import java.util.List;

public class SelectColorAdapter extends RecyclerView.Adapter<SelectColorAdapter.SelectColorHodler> {

    private Context context;
    private Itemclick item;
    private List<SelectMgMode.DataBean.ProductColorBean> list;

    public List<SelectMgMode.DataBean.ProductColorBean> getList() {
        return list;
    }

    public void setList(List<SelectMgMode.DataBean.ProductColorBean> list) {
        this.list = list;
    }

    public SelectColorAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SelectColorHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_singleton, viewGroup, false);
        SelectColorHodler selectColorHodler=new SelectColorHodler(inflate);
        return selectColorHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectColorHodler selectColorHodler, final int i) {
        selectColorHodler.name.setText(list.get(i).getColor_name());
        if (item!=null){
            //holder为Myhodler holder的参数
            selectColorHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(selectColorHodler.itemView,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class SelectColorHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public SelectColorHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
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
