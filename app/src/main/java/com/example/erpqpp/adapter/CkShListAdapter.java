package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CgShMode;
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.ui.CkListActivity;
import com.example.erpqpp.ui.CkShListActivity;

import java.util.List;

public class CkShListAdapter extends RecyclerView.Adapter<CkShListAdapter.CkListHodler> {
    private Context context;
    private CkShListActivity ckShListActivity;
    private CkShListZiAdapter ckListZiAdapter;
    private List<CgShMode.DataBean> list;

    public List<CgShMode.DataBean> getList() {
        return list;
    }

    public void setList(List<CgShMode.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public CkShListAdapter(Context context, CkShListActivity ckShListActivity) {
        this.context = context;
        this.ckShListActivity = ckShListActivity;
    }

    @NonNull
    @Override
    public CkListHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cklistxq, viewGroup, false);
        CkListHodler ckListHodler=new CkListHodler(inflate);
        return ckListHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull CkListHodler ckListHodler, int i) {
      ckListHodler.tv_name.setText(list.get(i).getProName());
        List<CgShMode.DataBean.ProductUnitListBean> product_unit_list = list.get(i).getProduct_unit_list();
        product_unit_list = list.get(i).getProduct_unit_list();
        ckListZiAdapter = new CkShListZiAdapter(context,ckShListActivity);
        ckListZiAdapter.setIndex(i+"");
        ckListZiAdapter.setProduct_unit_list(product_unit_list);
        ckListHodler.cklistxq_rc.setAdapter(ckListZiAdapter);
        ckListHodler.cklistxq_rc.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class CkListHodler extends RecyclerView.ViewHolder{
        public TextView tv_name;
        public RecyclerView cklistxq_rc;
        public CkListHodler(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            cklistxq_rc=itemView.findViewById(R.id.cklistxq_rc);
        }
    }

    public void shuaxin(){
        ckListZiAdapter.notifyDataSetChanged();
    }
}
