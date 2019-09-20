package com.example.erpqpp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.ui.SelectShActivity;
import com.example.erpqpp.ui.SelectXsDjActivity;
import com.example.erpqpp.ui.SetLxActivity;
import com.example.erpqpp.ui.SetXsCountActivity;
import com.example.erpqpp.ui.SetzKvActivity;
import com.example.erpqpp.ui.XsAddproductActivity;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class XsAddproductAdapter extends RecyclerView.Adapter<XsAddproductAdapter.XsAddproductHodler> {
    private Context context;
    private XsAddproductActivity xsAddproductActivity;
    private List<XsproductListMode.DataBean.ListBean> list;

    public List<XsproductListMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<XsproductListMode.DataBean.ListBean> list) {
        this.list = list;
    }

    public XsAddproductAdapter(Context context,XsAddproductActivity xsAddproductActivity) {
        this.context = context;
        this.xsAddproductActivity = xsAddproductActivity;
    }

    @NonNull
    @Override
    public XsAddproductHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_xsaddproduct, viewGroup, false);
        XsAddproductHodler xsAddproductHodler=new XsAddproductHodler(inflate);
        return xsAddproductHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull XsAddproductHodler xsAddproductHodler, final int i) {
      xsAddproductHodler.tv.setText(list.get(i).getPro_name());
      xsAddproductHodler.caizhi.setText(list.get(i).getWood_name());
      xsAddproductHodler.chengbenjia.setText(list.get(i).getCost_price()+"");
      xsAddproductHodler.biaojia.setText(list.get(i).getPrice()+"");
      xsAddproductHodler.xilie.setText(list.get(i).getSeries());
      xsAddproductHodler.danjian.setText(list.get(i).getDanjian());
      xsAddproductHodler.danjiancount.setText(list.get(i).getCount());
      xsAddproductHodler.danjia.setText(list.get(i).getDanjia());
      xsAddproductHodler.jine.setText(list.get(i).getJine());
      xsAddproductHodler.zhekoulv.setText(list.get(i).getZhekoulv());
      xsAddproductHodler.leixing.setText(list.get(i).getLeixing());
      xsAddproductHodler.sehao.setText(list.get(i).getColor());


    /*    //选择单件
     xsAddproductHodler.danjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(context,"xspro_id",list.get(i).getPro_id()+"");
                Intent intent = new Intent(context, SelectXsDjActivity.class);
                intent.putExtra("select",i+"");
                xsAddproductActivity.startActivityForResult(intent,1015);
            }
        });*/

     //设置数量
        xsAddproductHodler.danjiancount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetXsCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("type","数量");
                xsAddproductActivity.startActivityForResult(intent,1011);
            }
        });

        /*//设置单价
        xsAddproductHodler.danjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SetXsCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("type","单价");
                xsAddproductActivity.startActivityForResult(intent,1012);
            }
        });*/

        //设置折扣率
        xsAddproductHodler.zhekoulv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetzKvActivity.class);
                intent.putExtra("select",i+"");
                xsAddproductActivity.startActivityForResult(intent,1012);
            }
        });

        //设置类型
        xsAddproductHodler.leixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetLxActivity.class);
                intent.putExtra("select",i+"");
                xsAddproductActivity.startActivityForResult(intent,1013);
            }
        });
        //设置色号
        xsAddproductHodler.sehao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SelectShActivity.class);
                intent.putExtra("select",i+"");

                xsAddproductActivity.startActivityForResult(intent,1014);
            }
        });


      xsAddproductHodler.delect.setOnClickListener(new View.OnClickListener() {
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
                                      list.remove(i);
                                      notifyDataSetChanged();
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

    class XsAddproductHodler extends RecyclerView.ViewHolder{
         public TextView tv;
         public TextView delect;
         public TextView xilie;
         public TextView danjian;
         public TextView caizhi;
         public TextView danjiancount;
         public TextView sehao;
         public TextView leixing;
         public TextView chengbenjia;
         public TextView biaojia;
         public TextView zhekoulv;
         public TextView danjia;
         public TextView jine;
        public XsAddproductHodler(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            delect=itemView.findViewById(R.id.delect);
            danjian=itemView.findViewById(R.id.danjian);
            caizhi=itemView.findViewById(R.id.caizhi);
            danjiancount=itemView.findViewById(R.id.danjiancount);
            sehao=itemView.findViewById(R.id.sehao);
            leixing=itemView.findViewById(R.id.leixing);
            chengbenjia=itemView.findViewById(R.id.chengbenjia);
            biaojia=itemView.findViewById(R.id.biaojia);
            zhekoulv=itemView.findViewById(R.id.zhekoulv);
            jine=itemView.findViewById(R.id.jine);
            danjia=itemView.findViewById(R.id.danjia);
            xilie=itemView.findViewById(R.id.xilie);
        }
    }
}
