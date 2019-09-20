package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;

import java.util.List;

public class CpCzAdapter extends RecyclerView.Adapter<CpCzAdapter.SingletonHodler> {
    private Context context;
    private  List<SelectCpCzMode.DataBean>  list;
    private Itemclick item;

    public  List<SelectCpCzMode.DataBean>  getList() {
        return list;
    }

    public void setList( List<SelectCpCzMode.DataBean>  list) {
        this.list = list;
    }

    public CpCzAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SingletonHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_singleton, viewGroup, false);
        SingletonHodler singletonHodler=new SingletonHodler(inflate);
        return singletonHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final SingletonHodler singletonHodler, final int i) {
        singletonHodler.name.setText(list.get(i).getWood_name());

        if (item!=null){
            //holder为Myhodler holder的参数
            singletonHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(singletonHodler.itemView,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();

    }

    class SingletonHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public SingletonHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
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
