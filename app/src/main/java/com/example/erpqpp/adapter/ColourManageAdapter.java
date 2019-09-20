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
import com.example.erpqpp.mvp.mode.ColourManageMode;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.ui.ColourManageActivity;
import com.example.erpqpp.ui.UpdataManageActivity;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class ColourManageAdapter extends RecyclerView.Adapter<ColourManageAdapter.ColourManageHodler> {
    private Context context;
    private List<ColourManageMode.DataBean> list;
    private ColourManageActivity colourManageActivity;

    public List<ColourManageMode.DataBean> getList() {
        return list;
    }

    public void setList(List<ColourManageMode.DataBean> list) {
        this.list = list;
    }

    public ColourManageAdapter(Context context, ColourManageActivity colourManageActivity) {
        this.context = context;
        this.colourManageActivity = colourManageActivity;
    }

    @NonNull
    @Override
    public ColourManageHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_colourmanage, viewGroup, false);
        ColourManageHodler colourManageHodler=new ColourManageHodler(inflate);
        return colourManageHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ColourManageHodler colourManageHodler, int i) {

        colourManageHodler.name.setText(list.get(i).getColor_name());
        colourManageHodler.bianhao.setText(list.get(i).getColor_num()+"");

         colourManageHodler.ll_add_suite_container.setOnClickListener(v -> {
             Intent intent = new Intent(context, UpdataManageActivity.class);
             intent.putExtra("title","修改色号");
             intent.putExtra("color_id",list.get(i).getColor_id()+"");
             intent.putExtra("name",list.get(i).getColor_name()+"");
             intent.putExtra("num",list.get(i).getColor_num()+"");
             context.startActivity(intent);
         });

        //删除
        colourManageHodler.delete.setOnClickListener(v -> {
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
                                        colourManageActivity.deleteColor(list.get(i).getColor_id()+"");
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

        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ColourManageHodler extends RecyclerView.ViewHolder{
        public TextView bianhao;
        public TextView name;
        public Button delete;
        public LinearLayout ll_add_suite_container;
        public ColourManageHodler(@NonNull View itemView) {
            super(itemView);
            bianhao=itemView.findViewById(R.id.bianhao);
            name=itemView.findViewById(R.id.name);
            delete=itemView.findViewById(R.id.delete);
            ll_add_suite_container=itemView.findViewById(R.id.ll_add_suite_container);
        }
    }




}
