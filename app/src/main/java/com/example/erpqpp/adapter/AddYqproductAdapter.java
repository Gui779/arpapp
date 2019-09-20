package com.example.erpqpp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.ui.AddYqproductActivity;
import com.example.erpqpp.ui.AddproductActivity;
import com.example.erpqpp.ui.SelectCkActivity;
import com.example.erpqpp.ui.SelectColorActivity;
import com.example.erpqpp.ui.SingletonActivity;
import com.example.erpqpp.ui.SingletonCountActivity;
import com.example.erpqpp.ui.TjselectActivity;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class AddYqproductAdapter extends RecyclerView.Adapter<AddYqproductAdapter.AddproductHodler> {

    private Context context;
    private List<SelectMgMode.DataBean.ProductAndUnitListBean> list;
    private DialogUtils dialogUtils;
    private AddYqproductActivity addproductActivity;
    private boolean iscolor;

    public boolean isIscolor() {
        return iscolor;
    }

    public void setIscolor(boolean iscolor) {
        this.iscolor = iscolor;
    }

    public List<SelectMgMode.DataBean.ProductAndUnitListBean> getList() {
        return list;
    }

    public void setList(List<SelectMgMode.DataBean.ProductAndUnitListBean> list) {
        this.list = list;
    }

    public AddYqproductAdapter(Context context, AddYqproductActivity addproductActivity) {
        this.context = context;
        this.addproductActivity = addproductActivity;
        dialogUtils = new DialogUtils();
    }

    @NonNull
    @Override
    public AddproductHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_addyqproduct, viewGroup, false);
        AddproductHodler addproductHodler=new AddproductHodler(inflate);
        return addproductHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final AddproductHodler addproductHodler, final int i) {
        //禁止复用
        addproductHodler.setIsRecyclable(false);
        if (iscolor){
            addproductHodler.tr.setVisibility(View.VISIBLE);
        }else {
            addproductHodler.tr.setVisibility(View.GONE);
        }

        addproductHodler.danjiancount.setText(list.get(i).getCount());
        addproductHodler.xilie.setText(list.get(i).getSeries());
        addproductHodler.tv.setText(list.get(i).getPro_name());
        addproductHodler.caizhi.setText(list.get(i).getWood_name());
        addproductHodler.danjian.setText(list.get(i).getUnit_name());
        addproductHodler.selectcolor.setText(list.get(i).getColorname());
        addproductHodler.shck.setText(list.get(i).getCangku());
        addproductHodler.taojian.setText(list.get(i).getTjname()==null?"套件":list.get(i).getTjname());

        addproductHodler.delect.setOnClickListener(new View.OnClickListener() {
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


        addproductHodler.danjiancount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SingletonCountActivity.class);
                intent.putExtra("select",i+"");
                addproductActivity.startActivityForResult(intent,123);
            }
        });

        addproductHodler.danjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isIsselect()){
                    Intent intent=new Intent(context, SingletonActivity.class);
                    intent.putExtra("select",i+"");
                    intent.putExtra("pro_id",list.get(i).getPro_id()+"");
                    addproductActivity.startActivityForResult(intent,123);
                }

            }
        });

        addproductHodler.selectcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String store_id = (String) SPUtils.get(context, "store_id", "");
                Intent intent=new Intent(context, SelectColorActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("store_id",store_id);
                addproductActivity.startActivityForResult(intent,123);
            }
        });

        addproductHodler.shck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SelectCkActivity.class);
                intent.putExtra("select",i+"");
                addproductActivity.startActivityForResult(intent,123);
            }
        });

        addproductHodler.taojian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TjselectActivity.class);
                intent.putExtra("select",i+"");
                addproductActivity.startActivityForResult(intent,123);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class AddproductHodler extends RecyclerView.ViewHolder{
        public TextView tv;
        public TextView delect;
        public TextView xilie;
        public TextView danjian;
        public TextView caizhi;
        public TextView danjiancount;
        public TextView selectcolor;
        public TextView shck;
        public TextView taojian;
        public TableRow tr;
        public AddproductHodler(@NonNull View itemView) {
            super(itemView);
            this.tv=itemView.findViewById(R.id.tv);
            this.delect=itemView.findViewById(R.id.delect);
            this.xilie=itemView.findViewById(R.id.xilie);
            this.danjian=itemView.findViewById(R.id.danjian);
            this.danjiancount=itemView.findViewById(R.id.danjiancount);
            this.caizhi=itemView.findViewById(R.id.caizhi);
            this.selectcolor=itemView.findViewById(R.id.selectcolor);
            this.shck=itemView.findViewById(R.id.shck);
            this.taojian=itemView.findViewById(R.id.taojian);
            this.tr=itemView.findViewById(R.id.tr);

        }
    }
}
