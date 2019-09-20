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
import com.example.erpqpp.mvp.mode.WmMode;
import com.example.erpqpp.ui.SetCountActivity;
import com.example.erpqpp.ui.UpdataPriceActivity;
import com.example.erpqpp.ui.WmWarehouseActivity;
import com.example.erpqpp.utils.StatusUtils;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class WmWarehouseAdapter extends RecyclerView.Adapter<WmWarehouseAdapter.WmWarehouseHodler>{
    private Context context;
    private List<WmMode.DataBean> list;
    private boolean ischeck=false;
    private WmWarehouseActivity wmWarehouseActivity;
    public WmWarehouseAdapter(Context context,WmWarehouseActivity wmWarehouseActivity) {
        this.context = context;
        this.wmWarehouseActivity = wmWarehouseActivity;
    }

    public List<WmMode.DataBean> getList() {
        return list;
    }

    public void setList(List<WmMode.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WmWarehouseHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_wmwarehouse, viewGroup, false);
        WmWarehouseHodler wmWarehouseHodler=new WmWarehouseHodler(inflate);
        return wmWarehouseHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull WmWarehouseHodler wmWarehouseHodler, final int i) {
        wmWarehouseHodler.cb.setChecked(list.get(i).isIscheck());
        wmWarehouseHodler.cb.setChecked(list.get(i).isIscheck());
        wmWarehouseHodler.name.setText("产品名称: "+list.get(i).getPro_name());
        wmWarehouseHodler.order_id.setText("订单编号: "+list.get(i).getPro_num());
        wmWarehouseHodler.chanpin_name.setText(list.get(i).getStore_name());
        wmWarehouseHodler.caizhi.setText(list.get(i).getWood_name());
        wmWarehouseHodler.danwen.setText(list.get(i).getUnit_name());
        wmWarehouseHodler.mgdate.setText(list.get(i).getAdd_time());
        wmWarehouseHodler.shthcount.setText(list.get(i).getUnit_num()+"");
        wmWarehouseHodler.ckcount.setText(list.get(i).getUnit_num()+"");
        wmWarehouseHodler.zhuangtai.setText(StatusUtils.getstatus(list.get(i).getStatus()));
        wmWarehouseHodler.laiyuan.setText(StatusUtils.getsource(list.get(i).getSource()));
        wmWarehouseHodler.order_stat.setText(list.get(i).getId()+"");
        wmWarehouseHodler.tvcount.setText("数量");
        wmWarehouseHodler.cbprice.setText(list.get(i).getCost()+"");

        wmWarehouseHodler.cb.setOnClickListener(new View.OnClickListener() {
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
                wmWarehouseActivity.setcheckbox(flog);
                notifyDataSetChanged();
            }
        });

        wmWarehouseHodler.delect.setOnClickListener(new View.OnClickListener() {
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
                                            String admin_id = (String) SPUtils.get(context, "admin_id", "");
                                            wmWarehouseActivity.delectproduct(list.get(i).getStore_id()+"",admin_id,list.get(i).getId()+"");
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

        wmWarehouseHodler.shthcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("zdcount",list.get(i).getUnit_num()+"");
                wmWarehouseActivity.startActivityForResult(intent,1001);

            }
        });

        wmWarehouseHodler.cbprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdataPriceActivity.class);
                intent.putExtra("store_id",list.get(i).getStore_id()+"");
                intent.putExtra("id",list.get(i).getId()+"");
                intent.putExtra("name","未磨仓库");
                wmWarehouseActivity.startActivityForResult(intent,666);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WmWarehouseHodler extends RecyclerView.ViewHolder{
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
        public WmWarehouseHodler(@NonNull View itemView) {
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
        }
    }
}
