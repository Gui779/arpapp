package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.erpqpp.mvp.mode.CpdwMode;
import com.example.erpqpp.ui.AddCgProductActivity;
import com.example.erpqpp.ui.CompileproductActivity;
import com.example.erpqpp.ui.SetProductActivity;

import java.io.Serializable;
import java.util.List;

public class CompileproductAdapter extends RecyclerView.Adapter<CompileproductAdapter.AddCgProductHodler> {

    private Context context;
    private List<CpdwMode.DataBean>  list;
    private CompileproductActivity addCgProductActivity;

    public List<CpdwMode.DataBean>  getList() {
        return list;
    }

    public void setList(List<CpdwMode.DataBean>  list) {
        this.list = list;
    }

    public CompileproductAdapter(Context context, CompileproductActivity addCgProductActivity) {
        this.context = context;
        this.addCgProductActivity = addCgProductActivity;

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

        addCgProductHodler.name.setText(list.get(i).getUnit_name());
        addCgProductHodler.count.setText(list.get(i).getRate()+"");

        addCgProductHodler.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });

        addCgProductHodler.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SetProductActivity.class);
                intent.putExtra("subscript",i+"");
                CpdwMode.DataBean dataBean = list.get(i);
                intent.putExtra("databean", (Serializable) dataBean);
                addCgProductActivity.startActivityForResult(intent,2001);
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
