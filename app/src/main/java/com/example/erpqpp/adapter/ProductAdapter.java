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
import com.example.erpqpp.mvp.mode.ProductMode;
import com.example.erpqpp.ui.CompileproductActivity;
import com.example.erpqpp.ui.ManagementDetailsActivity;
import com.example.erpqpp.ui.ProductActivity;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ManagementHodler>{
    private Context context;
    private List<ProductMode.DataBean> list;
    private ProductActivity productActivity;

    public List<ProductMode.DataBean> getList() {
        return list;
    }

    public void setList(List<ProductMode.DataBean> list) {
        this.list = list;
    }

    public ProductAdapter(Context context,ProductActivity productActivity) {
        this.context = context;
        this.productActivity = productActivity;
    }


    @NonNull
    @Override
    public ManagementHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_product, viewGroup, false);
        ManagementHodler managementHodler=new ManagementHodler(inflate);
        return managementHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final ManagementHodler managementHodler, final int i) {

        managementHodler.product_name.setText(list.get(i).getPro_name());
        managementHodler.product_xilie.setText("系列:  "+list.get(i).getSeries());
        managementHodler.product_caizhi.setText("材质:  "+list.get(i).getWood_name());

        managementHodler.ll_add_suite_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompileproductActivity.class);
                //intent.putExtra("type",list.get(i));
                ProductMode.DataBean dataBean = list.get(i);
                intent.putExtra("dataBean",dataBean);
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
                                        if (list.size()>0){
                                         String   store_id = (String) SPUtils.get(context, "store_id", "");
                                            productActivity.delect(store_id,list.get(i).getPro_id()+"");
                                        }
                                       list.remove(i);
                                       notifyDataSetChanged();
                                        break;
                                    case BUTTON_CANCEL:

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
        public TextView product_name;
        public TextView product_xilie;
        public TextView product_caizhi;
        public Button delete;
        public LinearLayout ll_add_suite_container;
        public ManagementHodler(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_xilie=itemView.findViewById(R.id.product_xilie);
            product_caizhi=itemView.findViewById(R.id.product_caizhi);
            delete=itemView.findViewById(R.id.delete);
            ll_add_suite_container=itemView.findViewById(R.id.ll_add_suite_container);
        }
    }



    
}
