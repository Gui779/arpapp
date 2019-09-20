package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.fragment.SqFhFragment;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.SqMode;
import com.example.erpqpp.ui.SetCountActivity;
import com.example.erpqpp.utils.StatusUtils;

import java.util.List;

public class SqlistAdapter extends RecyclerView.Adapter <SqlistAdapter.MglistHodler>{
    private Context context;
    private Fragment mgmanageActivity;
    private List<SqMode.DataBean> list;
    private boolean ischeck=false;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public List<SqMode.DataBean> getList() {
        return list;
    }

    public void setList(List<SqMode.DataBean> list) {
        this.list = list;
    }

    public SqlistAdapter(Context context, Fragment mgmanageActivity) {
        this.context = context;
        this.mgmanageActivity = mgmanageActivity;
    }

    @NonNull
    @Override
    public MglistHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_mglist, viewGroup, false);
        MglistHodler mglistHodler=new MglistHodler(inflate);
        return mglistHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final MglistHodler mglistHodler, final int i) {

       mglistHodler.cb.setChecked(list.get(i).isIscheck());

        mglistHodler.name.setText("产品名称: "+list.get(i).getPro_name());
        mglistHodler.order_id.setText("订单编号: "+list.get(i).getPro_num());
        mglistHodler.chanpin_name.setText(list.get(i).getStore_name());
        mglistHodler.caizhi.setText(list.get(i).getWood_name());
        mglistHodler.danwen.setText(list.get(i).getUnit_name());
        mglistHodler.mgdate.setText(list.get(i).getAdd_time());
        mglistHodler.ckcount.setText(list.get(i).getUnit_num()+"");
        mglistHodler.zhuangtai.setText(StatusUtils.getstatus(list.get(i).getStatus()));
        mglistHodler.laiyuan.setText(StatusUtils.getsource(list.get(i).getSource()));
        mglistHodler.order_stat.setText(list.get(i).getId()+"");

        mglistHodler.cb.setVisibility(View.GONE);

        mglistHodler.shthcount.setText(list.get(i).getUnit_num()+"/"+list.get(i).getUnit_num_s());

     /*   mglistHodler.shthcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("zdcount",list.get(i).getUnit_num()+"");
                mgmanageActivity.startActivityForResult(intent,1001);

            }
        });*/



    }

    @Override
    public int getItemCount() {

       return list==null?0:list.size();

    }

    class MglistHodler extends RecyclerView.ViewHolder{
        private CheckBox cb;
        public TextView name;
        public TextView order_id;
        public TextView order_stat;
        public TextView chanpin_name;
        public TextView danwen;
        public TextView caizhi;
        public TextView zhuangtai;
        public TextView laiyuan;
        public TextView ckcount;
        public TextView shthcount;
        public TextView mgdate;
        public MglistHodler(@NonNull View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.cb);
            name=itemView.findViewById(R.id.name);
            order_id=itemView.findViewById(R.id.order_id);
            danwen=itemView.findViewById(R.id.danwen);
            order_stat=itemView.findViewById(R.id.order_stat);
            chanpin_name=itemView.findViewById(R.id.chanpin_name);
            caizhi=itemView.findViewById(R.id.caizhi);
            zhuangtai=itemView.findViewById(R.id.zhuangtai);
            laiyuan=itemView.findViewById(R.id.laiyuan);
            ckcount=itemView.findViewById(R.id.ckcount);
            shthcount=itemView.findViewById(R.id.shthcount);
            mgdate=itemView.findViewById(R.id.mgdate);
        }
    }
}
