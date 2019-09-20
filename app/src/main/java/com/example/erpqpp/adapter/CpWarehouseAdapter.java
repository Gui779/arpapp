package com.example.erpqpp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CpckMode;
import com.example.erpqpp.ui.CpWarehouseActivity;
import com.example.erpqpp.ui.SetCountActivity;
import com.example.erpqpp.ui.UpdataPriceActivity;
import com.example.erpqpp.utils.StatusUtils;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class CpWarehouseAdapter extends RecyclerView.Adapter<CpWarehouseAdapter.CpWarehouseHodler> {
    private Context context;
    private  List<CpckMode.DataBean> list;
    private boolean ischeck=false;
    private CpWarehouseActivity cpWarehouseActivity;

    public  List<CpckMode.DataBean> getList() {
        return list;
    }

    public void setList( List<CpckMode.DataBean> list) {
        this.list = list;
    }

    public CpWarehouseAdapter(Context context,CpWarehouseActivity cpWarehouseActivity) {
        this.context = context;
        this.cpWarehouseActivity = cpWarehouseActivity;
    }

    @NonNull
    @Override
    public CpWarehouseHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cpwarehouse, viewGroup, false);
        CpWarehouseHodler cpWarehouseHodler=new CpWarehouseHodler(inflate);
        return cpWarehouseHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull CpWarehouseHodler cpWarehouseHodler, final int i) {
        cpWarehouseHodler.cb.setChecked(list.get(i).isIscheck());
        cpWarehouseHodler.cb.setChecked(list.get(i).isIscheck());
        cpWarehouseHodler.name.setText("产品名称: "+list.get(i).getPro_name());
        cpWarehouseHodler.order_id.setText("订单编号: "+list.get(i).getPro_num());
        cpWarehouseHodler.chanpin_name.setText(list.get(i).getStore_name());
        cpWarehouseHodler.caizhi.setText(list.get(i).getWood_name());
        cpWarehouseHodler.danwen.setText(list.get(i).getUnit_name());
        cpWarehouseHodler.mgdate.setText(list.get(i).getAdd_time());
        cpWarehouseHodler.shthcount.setText(list.get(i).getUnit_num()+"");
        cpWarehouseHodler.ckcount.setText(list.get(i).getUnit_num()+"");
        cpWarehouseHodler.zhuangtai.setText(StatusUtils.getstatus(list.get(i).getStatus()));
        cpWarehouseHodler.laiyuan.setText(StatusUtils.getsource(list.get(i).getSource()));
        cpWarehouseHodler.order_stat.setText(list.get(i).getId()+"");
        cpWarehouseHodler.tvcount.setText("数量");
        cpWarehouseHodler.cbprice.setText(list.get(i).getCost()+"");
        cpWarehouseHodler.toajianname.setText(list.get(i).getPro_name());
        cpWarehouseHodler.tjcount.setText(list.get(i).getUnit_num()+"");

        cpWarehouseHodler.cb.setOnClickListener(new View.OnClickListener() {
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
                cpWarehouseActivity.setcheckbox(flog);
            }
        });

        cpWarehouseHodler.delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(context)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("确定删除吗")
                        .setMessage("")
                        .setConfirmButtonText("确定")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        if (list.size()>0){
                                            int store_id = list.get(i).getStore_id();
                                            int id = list.get(i).getId();
                                            String admin_id = (String) SPUtils.get(context, "admin_id", "");
                                            cpWarehouseActivity.delectproduct(store_id+"",admin_id,id+"");
                                        }
                                        list.remove(i);
                                        notifyDataSetChanged();
                                        break;
                                }
                            }
                        })
                        .create().show();

            }
        });

        cpWarehouseHodler.shthcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("zdcount",list.get(i).getUnit_num()+"");
                cpWarehouseActivity.startActivityForResult(intent,1001);

            }
        });


        cpWarehouseHodler.cbprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdataPriceActivity.class);
                intent.putExtra("store_id",list.get(i).getStore_id()+"");
                intent.putExtra("id",list.get(i).getId()+"");
                intent.putExtra("name","成品仓库");
                cpWarehouseActivity.startActivityForResult(intent,666);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CpWarehouseHodler extends RecyclerView.ViewHolder{
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
        public TextView delect;
        public TextView tvcount;
        public TextView cbprice;
        public TextView toajianname;
        public TextView tjcount;
        public TextView cpck;
        public CpWarehouseHodler(@NonNull View itemView) {
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
            delect=itemView.findViewById(R.id.delect);
            tvcount=itemView.findViewById(R.id.tvcount);
            cbprice=itemView.findViewById(R.id.cbprice);
            tjcount=itemView.findViewById(R.id.tjcount);
            toajianname=itemView.findViewById(R.id.toajianname);
            cpck=itemView.findViewById(R.id.cpck);

        }
    }
}
