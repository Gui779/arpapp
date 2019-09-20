package com.example.erpqpp.myview;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erpqpp.R;

import java.util.Arrays;
import java.util.List;

public class DialogUtils {


    private setOnclick listener;

    public  void setdialog(Activity activity, final List<String> list){
        final Dialog bottomDialog = new Dialog(activity, R.style.BottomDialog);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.wheel_view, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = activity.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(false);

        final WheelView wva = contentView.findViewById(R.id.wheel_view_wv);
        TextView quxiao = contentView.findViewById(R.id.quxiao);
        TextView queding = contentView.findViewById(R.id.queding);
        wva.setOffset(1);
        wva.setItems(list);


        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });


        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.getSeletedItem(wva.getSeletedItem());
                    listener.getSeletedndex(wva.getSeletedIndex());
                }
                bottomDialog.dismiss();
            }
        });

        bottomDialog.show();
    }

    public  void  setonclickListener(setOnclick listener){
        this.listener=listener;
    }

    public interface setOnclick{
        void  getSeletedItem(String selectname);
        void  getSeletedndex(int selectindex);
    }
}
