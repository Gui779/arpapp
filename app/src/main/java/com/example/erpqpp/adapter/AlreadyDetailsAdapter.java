package com.example.erpqpp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;

public class AlreadyDetailsAdapter extends RecyclerView.Adapter<AlreadyDetailsAdapter.AlreadyDetailshodler> {
    private Context context;

    public AlreadyDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AlreadyDetailshodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_alreadydetails, viewGroup, false);
        AlreadyDetailshodler alreadyDetailshodler=new AlreadyDetailshodler(inflate);
        return alreadyDetailshodler;
    }

    @Override
    public void onBindViewHolder(@NonNull AlreadyDetailshodler alreadyDetailshodler, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class AlreadyDetailshodler extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView xilie;
        public TextView zhekoulv;
        public TextView price;
        public TextView color;
        public TextView caizhi;
        public TextView leixing;
        public TextView count;
        public AlreadyDetailshodler(@NonNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.name);
            this.xilie=itemView.findViewById(R.id.xilie);
            this.zhekoulv=itemView.findViewById(R.id.zhekoulv);
            this.price=itemView.findViewById(R.id.price);
            this.caizhi=itemView.findViewById(R.id.caizhi);
            this.leixing=itemView.findViewById(R.id.leixing);
            this.count=itemView.findViewById(R.id.count);

        }
    }
}
