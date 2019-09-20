package com.example.erpqpp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.StayreceivingAdapter;
import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.mvp.presenter.StayDeliverPresenter;
import com.example.erpqpp.mvp.view.StayDeliverView;
import com.example.erpqpp.ui.SendGoodsDetailsActivity;
import com.lbb.mvplibrary.base.MvpFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 已发货
 * */
public class AlreadyDeliverFragment extends MvpFragment<StayDeliverPresenter> implements StayDeliverView {
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.stayreceiving_rc)
    RecyclerView stayreceivingRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_stayreceiving, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();
        return inflate;
    }


    private void initdata() {
        StayreceivingAdapter stayreceivingAdapter=new StayreceivingAdapter(getActivity());
        stayreceivingRc.setAdapter(stayreceivingAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(getActivity()));
        stayreceivingAdapter.setIsfh(true);

        stayreceivingAdapter.huidiao(new StayreceivingAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("type",position+"");
                bundle.putString("name","发货");
                startActivity(SendGoodsDetailsActivity.class,bundle);
            }
        });

        etSearchContent.setOnClickListener(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    protected StayDeliverPresenter createPresenter() {
        return new StayDeliverPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_search_content:
                etSearchContent.setText("");
                break;
        }
    }

    @Override
    public void getDataSuccess(StayDeliverMode stayDeliverMode) {

    }

    @Override
    public void getDataFail(String msg) {

    }
}
