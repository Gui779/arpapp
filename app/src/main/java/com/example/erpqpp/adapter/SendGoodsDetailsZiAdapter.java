package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;
import com.example.erpqpp.mvp.mode.SendGoodsDetailsMode;

import java.util.List;

public class SendGoodsDetailsZiAdapter extends RecyclerView.Adapter<SendGoodsDetailsZiAdapter.MarkettjDetailsZiHodler>{
    private Context context;
    List<SendGoodsDetailsMode.DataBean.ProListBean.Unit> unit_list;

    public List<SendGoodsDetailsMode.DataBean.ProListBean.Unit> getUnit_list() {
        return unit_list;
    }

    public void setUnit_list(List<SendGoodsDetailsMode.DataBean.ProListBean.Unit> unit_list) {
        this.unit_list = unit_list;
    }

    public SendGoodsDetailsZiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MarkettjDetailsZiHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rc_zi, viewGroup, false);
        MarkettjDetailsZiHodler markettjDetailsZiHodler=new MarkettjDetailsZiHodler(inflate);
        return markettjDetailsZiHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MarkettjDetailsZiHodler markettjDetailsZiHodler, int i) {
      markettjDetailsZiHodler.dname.setText(unit_list.get(i).getUnit_name());
      markettjDetailsZiHodler.count.setText(unit_list.get(i).getCount()+"");
    }

    @Override
    public int getItemCount() {
        return unit_list==null?0:unit_list.size();
    }

    class MarkettjDetailsZiHodler extends RecyclerView.ViewHolder{
        public TextView dname;
        public TextView count;
        public MarkettjDetailsZiHodler(@NonNull View itemView) {
            super(itemView);
            dname=itemView.findViewById(R.id.dname);
            count=itemView.findViewById(R.id.count);
        }
    }
}
