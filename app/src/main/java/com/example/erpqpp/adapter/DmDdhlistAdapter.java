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
import com.example.erpqpp.fragment.DmDfhFragment;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.ui.SetCountActivity;
import com.example.erpqpp.utils.StatusUtils;

import java.util.List;

public class DmDdhlistAdapter extends RecyclerView.Adapter <DmDdhlistAdapter.MglistHodler>{
    private Context context;
    private Fragment mgmanageActivity;
    private List<DmMode.DataBean> list;
    private boolean ischeck=false;
    private DmDfhFragment dmDfhFragment;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public List<DmMode.DataBean> getList() {
        return list;
    }

    public void setList(List<DmMode.DataBean> list) {
        this.list = list;
    }

    public DmDdhlistAdapter(Context context, Fragment mgmanageActivity,DmDfhFragment dmDfhFragment) {
        this.context = context;
        this.mgmanageActivity = mgmanageActivity;
        this.dmDfhFragment = dmDfhFragment;
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
        mglistHodler.shthcount.setText(list.get(i).getUnit_num()+"");
        mglistHodler.ckcount.setText(list.get(i).getUnit_num()+"");
        mglistHodler.zhuangtai.setText(StatusUtils.getstatus(list.get(i).getStatus()));
        mglistHodler.laiyuan.setText(StatusUtils.getsource(list.get(i).getSource()));
        mglistHodler.order_stat.setText(list.get(i).getId()+"");
        mglistHodler.countname.setText("待发货数量");


        mglistHodler.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischeck=!ischeck;
                list.get(i).setIscheck(ischeck);
                boolean flog=true;
                for (int j = 0; j <list.size() ; j++) {
                    if (!list.get(j).isIscheck()){
                        flog=false;
                    }
                }
                notifyDataSetChanged();
                dmDfhFragment.setcheckbox(flog);
            }
        });

        mglistHodler.shthcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("zdcount",list.get(i).getUnit_num()+"");
                mgmanageActivity.startActivityForResult(intent,1001);

            }
        });



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
        public TextView countname;
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
            countname=itemView.findViewById(R.id.countname);
        }
    }
}
