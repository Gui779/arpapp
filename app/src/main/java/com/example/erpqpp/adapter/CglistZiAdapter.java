package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.mvp.mode.DiscountMode;
import com.example.erpqpp.utils.StatusUtils;

import java.util.List;

public class CglistZiAdapter extends RecyclerView.Adapter<CglistZiAdapter.DiscountHodler> {
    private Context context;
    private  List<CgStayGoMode.DataBean.ListBean.ProListBean>  pro_list;

    public  List<CgStayGoMode.DataBean.ListBean.ProListBean>  getPro_list() {
        return pro_list;
    }

    public void setPro_list( List<CgStayGoMode.DataBean.ListBean.ProListBean>  pro_list) {
        this.pro_list = pro_list;
    }

    public CglistZiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cglistzi, viewGroup, false);
        DiscountHodler discountHodler=new DiscountHodler(inflate);
        return discountHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountHodler discountHodler, int i) {
     discountHodler.chanpin_name.setText(pro_list.get(i).getPro_name());
     discountHodler.dprice.setText(pro_list.get(i).getSale_price());
     discountHodler.sehao.setText(pro_list.get(i).getColor_name());
     discountHodler.caizhi.setText(pro_list.get(i).getWood_name());
     discountHodler.shuliang.setText(pro_list.get(i).getSale_num()+"");
     discountHodler.jine.setText(pro_list.get(i).getSale_price()+"");

    }

    @Override
    public int getItemCount() {
        return pro_list==null?0:pro_list.size();
    }

    class DiscountHodler extends RecyclerView.ViewHolder{
        public TextView chanpin_name;
        public TextView dprice;
        public TextView sehao;
        public TextView caizhi;
        public TextView jine;
        public TextView shuliang;
        public DiscountHodler(@NonNull View itemView) {
            super(itemView);
            chanpin_name=itemView.findViewById(R.id.chanpin_name);
            dprice=itemView.findViewById(R.id.dprice);
            sehao=itemView.findViewById(R.id.sehao);
            caizhi=itemView.findViewById(R.id.caizhi);
            jine=itemView.findViewById(R.id.jine);
            shuliang=itemView.findViewById(R.id.shuliang);
        }
    }
}
