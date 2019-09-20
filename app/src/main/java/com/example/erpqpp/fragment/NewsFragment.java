package com.example.erpqpp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.ui.OrderMsgActivity;
import com.lbb.mvplibrary.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 消息
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.ordet_rl)
    RelativeLayout ordetRl;
    @BindView(R.id.jidi_rl)
    RelativeLayout jidiRl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();
        return inflate;
    }

    private void initdata() {
        tbvTitlebar.hideleft();
        tbvTitlebar.setMainTitle("消息中心");
        tbvTitlebar.setMainTitleColor(Color.WHITE);

        ordetRl.setOnClickListener(this);
        jidiRl.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         //订单消息
         case R.id.ordet_rl:
             Bundle bundle=new Bundle();
             bundle.putString("type","0");
            startActivity(OrderMsgActivity.class,bundle);
             break;
         //生产基地
         case R.id.jidi_rl:
             Bundle bundle1=new Bundle();
             bundle1.putString("type","1");
             startActivity(OrderMsgActivity.class,bundle1);
             break;
     }
    }
}
