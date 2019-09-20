package com.example.erpqpp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.fragment.AlreadyreceivingFragment;
import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.ui.SendGoodsDetailsActivity;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class AlreadyreceivingAdapter extends RecyclerView.Adapter<AlreadyreceivingAdapter.StayreceivingHodler>{
    private Context context;
    private Itemclick item;
    private String name;
    private boolean isfh=false;
    private AlreadyreceivingFragment alreadyreceivingFragment;


    public boolean isIsfh() {
        return isfh;
    }

    public void setIsfh(boolean isfh) {
        this.isfh = isfh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlreadyreceivingAdapter(Context context,AlreadyreceivingFragment alreadyreceivingFragment) {
        this.context = context;
        this.alreadyreceivingFragment = alreadyreceivingFragment;
    }
   private List<StayDeliverMode.DataBean.ListBean> list;

    public List<StayDeliverMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<StayDeliverMode.DataBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StayreceivingHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_stayreceiving, viewGroup, false);
        StayreceivingHodler stayreceivingHodler=new StayreceivingHodler(inflate);
        return stayreceivingHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final StayreceivingHodler stayreceivingHodler, final int i) {

        stayreceivingHodler.kehu_name.setText("客户名称:"+list.get(i).getCustomer_name());
        stayreceivingHodler.jiaoyi_date.setText("交易日期: "+list.get(i).getAdd_time());
        stayreceivingHodler.price.setText(list.get(i).getTotal()+"");

        List<StayDeliverMode.DataBean.ListBean.ProListBean> pro_list = list.get(i).getPro_list();
        StayreceivingZiAdaprer stayreceivingZiAdaprer=new StayreceivingZiAdaprer(context);
        stayreceivingZiAdaprer.setPro_list(pro_list);
        stayreceivingHodler.rc.setAdapter(stayreceivingZiAdaprer);
        stayreceivingHodler.rc.setLayoutManager(new LinearLayoutManager(context));
        if (isfh){
            stayreceivingHodler.fahuo.setVisibility(View.VISIBLE);
        }else {
            stayreceivingHodler.fahuo.setVisibility(View.GONE);
        }

        stayreceivingZiAdaprer.huidiao(new StayreceivingZiAdaprer.Itemclick() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void itemclick(View view, int position) {
                Intent intent=new Intent(context,SendGoodsDetailsActivity.class);
                intent.putExtra("name",name);
                SPUtils.put(context,"fh",list.get(i).getOrder_id()+"");
                SPUtils.put(context,"name",name);
                context.startActivity(intent);
            }
        });

        if (item!=null){
            //holder为Myhodler holder的参数
            stayreceivingHodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //调用接口的方法
                    item.itemclick(stayreceivingHodler.itemView,i);
                }
            });
        }

        stayreceivingHodler.fahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CBDialogBuilder(context)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("确定出货吗")
                        .setMessage("")
                        .setConfirmButtonText("确定")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        int order_id = list.get(i).getOrder_id();
                                        alreadyreceivingFragment.agree(order_id+"");

                                        break;
                                    case BUTTON_CANCEL:
                                        // Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class StayreceivingHodler extends RecyclerView.ViewHolder{
        public TextView kehu_name;
        public RecyclerView rc;
        public TextView jiaoyi_date;
        public TextView price;
        public TextView fahuo;
        public StayreceivingHodler(@NonNull View itemView) {
            super(itemView);
            this.kehu_name=itemView.findViewById(R.id.kehu_name);
            this.rc=itemView.findViewById(R.id.rc);
            this.jiaoyi_date=itemView.findViewById(R.id.jiaoyi_date);
            this.price=itemView.findViewById(R.id.price);
            this.fahuo=itemView.findViewById(R.id.fahuo);
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
