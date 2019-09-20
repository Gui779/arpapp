package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.SelectNameMode;

import java.util.List;

public class SelectNameAdapter extends RecyclerView.Adapter<SelectNameAdapter.SelectNameHodeler> {
     private Context context;
    List<SelectNameMode.DataBean.ListBean> list;
    private Itemclick item;

    public SelectNameAdapter(Context context) {
        this.context = context;
    }

    public List<SelectNameMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<SelectNameMode.DataBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SelectNameHodeler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_selectname, viewGroup, false);
        SelectNameHodeler selectNameHodeler=new SelectNameHodeler(inflate);
        return selectNameHodeler;
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectNameHodeler selectNameHodeler, final int i) {
     selectNameHodeler.nickanme.setText(list.get(i).getUser_name());

        if (item!=null){
            //holder为Myhodler holder的参数
            selectNameHodeler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(selectNameHodeler.itemView,i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class SelectNameHodeler extends RecyclerView.ViewHolder{
          public TextView nickanme;
        public SelectNameHodeler(@NonNull View itemView) {
            super(itemView);
            nickanme=itemView.findViewById(R.id.nickanme);
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
