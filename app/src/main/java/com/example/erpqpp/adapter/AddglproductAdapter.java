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
import com.example.erpqpp.TestAdd;
import com.example.erpqpp.ui.AddglproductActivity;
import com.example.erpqpp.ui.CompileproductActivity;
import com.example.erpqpp.ui.SetProductActivity;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.List;

public class AddglproductAdapter extends RecyclerView.Adapter<AddglproductAdapter.AddCgProductHodler> {

    private Context context;
    private List<TestAdd> list;
    private AddglproductActivity addglproductActivity;

    public List<TestAdd> getList() {
        return list;
    }

    public void setList(List<TestAdd> list) {
        this.list = list;
    }

    public AddglproductAdapter(Context context, AddglproductActivity addglproductActivity) {
        this.context = context;
        this.addglproductActivity = addglproductActivity;

    }

    @NonNull
    @Override
    public AddCgProductHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_addcgproduct, viewGroup, false);
        AddCgProductHodler AddCgProductHodler=new AddCgProductHodler(inflate);
        return AddCgProductHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final AddCgProductHodler addCgProductHodler, final int i) {
        //禁止复用
        addCgProductHodler.setIsRecyclable(false);

        addCgProductHodler.name.setText(list.get(i).getName());
        addCgProductHodler.count.setText(list.get(i).getCount());

        addCgProductHodler.delete.setOnClickListener(new View.OnClickListener() {
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
                                }
                            }
                        })
                        .create().show();

            }
        });

        addCgProductHodler.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetProductActivity.class);
                intent.putExtra("subscript",i+"");
                addglproductActivity.startActivityForResult(intent,2001);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class AddCgProductHodler extends RecyclerView.ViewHolder{
         public Button delete;
         private TextView name;
         private TextView count;
         private LinearLayout ly;
        public AddCgProductHodler(@NonNull View itemView) {
            super(itemView);
            delete=itemView.findViewById(R.id.delete);
            name=itemView.findViewById(R.id.name);
            count=itemView.findViewById(R.id.count);
            ly=itemView.findViewById(R.id.ll_add_suite_container);

        }
    }
}
