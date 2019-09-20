package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;
import com.example.erpqpp.ui.SelectXsDjActivity;
import com.example.erpqpp.ui.SetXsCountActivity;

import java.util.List;

public class SelectXsDjAdapter extends RecyclerView.Adapter<SelectXsDjAdapter.SelectXsDjHodler> {
    private Context context;
    private List<SelectXsDjMode.DataBean.ProListBean> list;
    private SelectXsDjActivity selectXsDjActivity;

    public List<SelectXsDjMode.DataBean.ProListBean> getList() {
        return list;
    }

    public void setList(List<SelectXsDjMode.DataBean.ProListBean> list) {
        this.list = list;
    }

    public SelectXsDjAdapter(Context context,SelectXsDjActivity selectXsDjActivity) {
        this.context = context;
        this.selectXsDjActivity = selectXsDjActivity;
    }

    @NonNull
    @Override
    public SelectXsDjHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_addcgproduct, viewGroup, false);
        SelectXsDjHodler selectXsDjHodler=new SelectXsDjHodler(inflate);
        return selectXsDjHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectXsDjHodler selectXsDjHodler, final int i) {
        selectXsDjHodler.name.setText(list.get(i).getUnit_name());
        selectXsDjHodler.count.setText(list.get(i).getRate()+"");
        selectXsDjHodler.ll_add_suite_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetXsCountActivity.class);
                intent.putExtra("select",i+"");
                intent.putExtra("type","数量");
                selectXsDjActivity.startActivityForResult(intent,1012);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class SelectXsDjHodler extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView count;
        public LinearLayout ll_add_suite_container;
        public SelectXsDjHodler(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            count=itemView.findViewById(R.id.count);
            ll_add_suite_container=itemView.findViewById(R.id.ll_add_suite_container);
        }
    }
}
