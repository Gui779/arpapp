package com.example.erpqpp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.ManagementMode;
import com.example.erpqpp.ui.ManagementActivity;
import com.example.erpqpp.ui.ManagementDetailsActivity;
import com.lbb.mvplibrary.app.AppManager;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;


public class ManagementAdapter extends RecyclerView.Adapter<ManagementAdapter.ManagementHodler>{
    private Context context;
    private List<ManagementMode.DataBean.ListBean> list;
    private ManagementActivity managementActivity;


    public List<ManagementMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<ManagementMode.DataBean.ListBean> list) {
        this.list = list;
    }

    public ManagementAdapter(Context context,ManagementActivity managementActivity) {
        this.context = context;
        this.managementActivity = managementActivity;
    }


    @NonNull
    @Override
    public ManagementHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_management, viewGroup, false);
        ManagementHodler managementHodler=new ManagementHodler(inflate);
        return managementHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final ManagementHodler managementHodler, final int i) {

        managementHodler.Management_name.setText(list.get(i).getUser_name());
        managementHodler.Management_tel.setText("联系电话: "+list.get(i).getTel());
        managementHodler.Management_addres.setText("联系地址: "+list.get(i).getAddress());

        managementHodler.ll_add_suite_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManagementDetailsActivity.class);
                intent.putExtra("type",list.get(i).getUser_id()+"");
                context.startActivity(intent);
            }
        });

        managementHodler.delete.setOnClickListener(new View.OnClickListener() {
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
                                        managementActivity.Companydelete(list.get(i).getUser_id()+"");
                                       list.remove(i);
                                       notifyDataSetChanged();
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



    class ManagementHodler extends RecyclerView.ViewHolder{
        public TextView Management_name;
        public TextView Management_tel;
        public TextView Management_addres;
        public Button delete;
        public LinearLayout ll_add_suite_container;
        public ManagementHodler(@NonNull View itemView) {
            super(itemView);
            Management_name=itemView.findViewById(R.id.Management_name);
            Management_tel=itemView.findViewById(R.id.Management_tel);
            Management_addres=itemView.findViewById(R.id.Management_addres);
            delete=itemView.findViewById(R.id.delete);
            ll_add_suite_container=itemView.findViewById(R.id.ll_add_suite_container);
        }
    }



    
}
