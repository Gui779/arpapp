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
import com.example.erpqpp.fragment.CgStayCollectFragment;
import com.example.erpqpp.fragment.CgStayGoFragment;
import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.utils.StatusUtils;

import java.util.List;

public class CgShlistAdapter extends RecyclerView.Adapter<CgShlistAdapter.CglistHodler>{
    private Context context;
    private String name;
    private List<CgStayGoMode.DataBean.ListBean> list;
    private CgStayCollectFragment cgStayGoFragment;


    public void setCgStayGoFragment(CgStayCollectFragment cgStayGoFragment) {
        this.cgStayGoFragment = cgStayGoFragment;
    }

    public List<CgStayGoMode.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<CgStayGoMode.DataBean.ListBean> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CgShlistAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CglistHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cglist, viewGroup, false);
        CglistHodler cglistHodler=new CglistHodler(inflate);
        return cglistHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull CglistHodler cglistHodler, final int i) {

        cglistHodler.name.setText("客户名称: "+list.get(i).getCustomer_name());
       // cglistHodler.zonge.setText("总额: "+list.get(i).getTotal());
        cglistHodler.order_id.setText("订单编号: "+list.get(i).getOrder_num());
        cglistHodler.order_stat.setText("状态: "+ StatusUtils.getxsstatus(list.get(i).getStatus()));


        List<CgStayGoMode.DataBean.ListBean.ProListBean> pro_list = list.get(i).getPro_list();
        CglistZiAdapter cglistZiAdapter=new CglistZiAdapter(context);
        cglistZiAdapter.setPro_list(pro_list);
        cglistHodler.cglist_rc.setAdapter(cglistZiAdapter);
        cglistHodler.cglist_rc.setLayoutManager(new LinearLayoutManager(context));

        if (name.equals("没有")){
            cglistHodler.shouhuo.setVisibility(View.GONE);
        }else {
            cglistHodler.shouhuo.setText(name);
        }

        cglistHodler.shouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("出货")){

                }else if (name.equals("收货")){
                    cgStayGoFragment.Sh(list.get(i).getOrder_id()+"");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class CglistHodler extends RecyclerView.ViewHolder{
      public TextView name;
      public TextView zonge;
      public TextView order_id;
      public TextView order_stat;
      public TextView shouhuo;
      public RecyclerView cglist_rc;
     public CglistHodler(@NonNull View itemView) {
         super(itemView);
         name=itemView.findViewById(R.id.name);
         zonge=itemView.findViewById(R.id.zonge);
         order_id=itemView.findViewById(R.id.order_id);
         order_stat=itemView.findViewById(R.id.order_stat);
         shouhuo=itemView.findViewById(R.id.shouhuo);
         cglist_rc=itemView.findViewById(R.id.cglist_rc);
     }
 }
}
