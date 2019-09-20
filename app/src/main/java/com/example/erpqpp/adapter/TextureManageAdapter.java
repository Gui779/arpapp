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
import com.example.erpqpp.mvp.mode.TextureManageMode;
import com.example.erpqpp.ui.TextureManageActivity;
import com.example.erpqpp.ui.UpManageActivity;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class TextureManageAdapter extends RecyclerView.Adapter<TextureManageAdapter.TextureManageHodler> {
    private Context context;
    private List<TextureManageMode.DataBean> list;
    private TextureManageActivity textureManageActivity;

    public List<TextureManageMode.DataBean> getList() {
        return list;
    }

    public void setList(List<TextureManageMode.DataBean> list) {
        this.list = list;
    }

    public TextureManageAdapter(Context context,TextureManageActivity textureManageActivity) {
        this.context = context;
        this.textureManageActivity = textureManageActivity;
    }

    @NonNull
    @Override
    public TextureManageHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_texturemanage, viewGroup, false);
        TextureManageHodler textureManageHodler=new TextureManageHodler(inflate);
        return textureManageHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull TextureManageHodler textureManageHodler, int i) {
       textureManageHodler.texture_name.setText(list.get(i).getWood_name());
       textureManageHodler.texture_date.setText(list.get(i).getAdd_time());

        textureManageHodler.ll_add_suite_container.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpManageActivity.class);
            intent.putExtra("title","修改材质");
            intent.putExtra("wood_id",list.get(i).getWood_id()+"");
            intent.putExtra("wood_name",list.get(i).getWood_name());
            context.startActivity(intent);
        });

        textureManageHodler.delete.setOnClickListener(v -> {
            new CBDialogBuilder(context)
                    .setTouchOutSideCancelable(true)
                    .showCancelButton(true)
                    .setTitle("确定删除吗")
                    .setMessage("")
                    .setConfirmButtonText("确定")
                    .setCancelButtonText("取消")
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                    .setButtonClickListener(true, (context, dialog, whichBtn) -> {
                        switch (whichBtn) {
                            case CBDialogBuilder.onDialogbtnClickListener.BUTTON_CONFIRM:
                                if (list.size()>0){
                                    textureManageActivity.delectWood(list.get(i).getWood_id()+"");
                                }
                                list.remove(i);
                                notifyDataSetChanged();
                                break;
                            case CBDialogBuilder.onDialogbtnClickListener.BUTTON_CANCEL:

                                break;
                            default:
                                break;
                        }
                    })
                    .create().show();
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class TextureManageHodler extends RecyclerView.ViewHolder{
        public TextView texture_name;
        public TextView texture_date;
        public LinearLayout ll_add_suite_container;
        public Button delete;
        public TextureManageHodler(@NonNull View itemView) {
            super(itemView);
            texture_name=itemView.findViewById(R.id.texture_name);
            texture_date=itemView.findViewById(R.id.texture_date);
            ll_add_suite_container=itemView.findViewById(R.id.ll_add_suite_container);
            delete=itemView.findViewById(R.id.delete);
        }
    }



}
