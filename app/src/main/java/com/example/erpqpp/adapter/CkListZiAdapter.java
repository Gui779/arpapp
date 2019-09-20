package com.example.erpqpp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.ui.CkListActivity;
import com.example.erpqpp.ui.SetCgThActivity;
import com.lbb.mvplibrary.utils.LinLog;

import java.util.List;


public class CkListZiAdapter extends RecyclerView.Adapter<CkListZiAdapter.CkListZiHodler>{
    private Context context;
    private List<CkListMode.DataBean.ProductUnitListBean> product_unit_list;
    private boolean ischeck=false;
    private String index;
    private CkListActivity ckListActivity;
    private String saleNum;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public CkListZiAdapter(Context context,CkListActivity ckListActivity,String saleNum) {
        this.context = context;
        this.ckListActivity = ckListActivity;
        this.saleNum = saleNum;
    }

    public void setProduct_unit_list(List<CkListMode.DataBean.ProductUnitListBean> product_unit_list) {
        this.product_unit_list = product_unit_list;
    }

    @NonNull
    @Override
    public CkListZiHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cklistzi, viewGroup, false);
        CkListZiHodler ckListZiHodler=new CkListZiHodler(inflate);
        return ckListZiHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull CkListZiHodler ckListZiHodler, final int i) {
        if (product_unit_list.get(0).getDepot()!=null){
            ckListZiHodler.tv_name.setText(product_unit_list.get(0).getDepot().get(i).getDepotName());
            ckListZiHodler.checkBox.setChecked(product_unit_list.get(0).getDepot().get(i).isIsflag());
            ckListZiHodler.count.setText(product_unit_list.get(0).getDepot().get(i).getCount());
        }

        ckListZiHodler.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product_unit_list.get(0).getDepot().get(i).setIsflag(isChecked);
                notifyDataSetChanged();
                if (!isChecked){
                    product_unit_list.get(0).getDepot().get(i).setCount("数量");
                }
            }
        });

        ckListZiHodler.count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int depotCount = product_unit_list.get(0).getDepot().get(i).getDepotCount();
                Intent intent=new Intent(context, SetCgThActivity.class);
                intent.putExtra("select1",i+"");
                intent.putExtra("zdcount",saleNum);
                intent.putExtra("select",index+"");
                ckListActivity.startActivityForResult(intent,1111);

            }
        });
    }

    @Override
    public int getItemCount() {
        return product_unit_list==null?0:product_unit_list.get(0).getDepot().size();
    }

    class CkListZiHodler extends RecyclerView.ViewHolder{
        public CheckBox checkBox;
        public TextView tv_name;
        public TextView count;
        public CkListZiHodler(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.cb);
            tv_name=itemView.findViewById(R.id.tv_name);
            count=itemView.findViewById(R.id.count);
        }
    }
}
