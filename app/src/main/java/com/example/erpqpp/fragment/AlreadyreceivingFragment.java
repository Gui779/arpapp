package com.example.erpqpp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.AlreadyreceivingAdapter;
import com.example.erpqpp.adapter.StayreceivingAdapter;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.mvp.mode.XsMode;
import com.example.erpqpp.mvp.presenter.AlreadyreceivingPresenter;
import com.example.erpqpp.mvp.presenter.StayreceivingPresenter;
import com.example.erpqpp.mvp.view.StayreceivingView;
import com.example.erpqpp.ui.SendGoodsDetailsActivity;
import com.lbb.mvplibrary.base.MvpFragment;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 已发货
 */
public class AlreadyreceivingFragment extends MvpFragment<AlreadyreceivingPresenter> implements StayreceivingView {
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.stayreceiving_rc)
    RecyclerView stayreceivingRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private AlreadyreceivingAdapter stayreceivingAdapter;
    private int page=1;
    private StatusLayoutManager statusLayoutManager;
    private List<StayDeliverMode.DataBean.ListBean> list;
    private String store_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_stayreceiving, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();
        return inflate;
    }


    private void initdata() {

        etSearchContent.setHint("客户名称搜索");
        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(getActivity(), "store_id", "");



        stayreceivingAdapter = new AlreadyreceivingAdapter(getActivity(),this);
        stayreceivingRc.setAdapter(stayreceivingAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(getActivity()));
        stayreceivingAdapter.setName("已发货");
        stayreceivingAdapter.setIsfh(true);


        stayreceivingAdapter.huidiao(new AlreadyreceivingAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("type",list.get(position).getOrder_id()+"");
                bundle.putString("name","已发货");
                SPUtils.put(getContext(),"fh",list.get(position).getOrder_id()+"");
                SPUtils.put(getContext(),"name","已发货");
                startActivity(SendGoodsDetailsActivity.class,bundle);
            }
        });

        etSearchContent.setOnClickListener(this);

        addListener();

        etSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                page = 1;
                if (editable.toString().trim().equals("")) {
                    mvpPresenter.Stayreceiving(store_id,page+"");
                } else {
                    mvpPresenter.Stayreceivingsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
                }
            }
        });
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.Stayreceiving(store_id,page+"");
    }

    @Override
    protected AlreadyreceivingPresenter createPresenter() {
        return new AlreadyreceivingPresenter(this);
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
        statusLayoutManager.showSuccessLayout();
        list = stayDeliverMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                stayreceivingAdapter.setList(list);
                stayreceivingAdapter.notifyDataSetChanged();
            } else {
                stayreceivingAdapter.getList().addAll(list);
                stayreceivingAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && list.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
            }
        }

    }

    @Override
    public void getDataFail(String msg) {
        //加载失败
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void DataSuccess(MgMode mgMode) {
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.Stayreceiving(store_id,page+"");
            toastShow(mgMode.getMsg());

    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (etSearchContent.toString().trim().equals("")) {
                    mvpPresenter.Stayreceiving(store_id,page+"");
                } else {
                    mvpPresenter.Stayreceivingsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishRefresh();
            }
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (etSearchContent.getText().toString().isEmpty()) {
                    mvpPresenter.Stayreceiving(store_id,page+"");
                } else {
                    mvpPresenter.Stayreceivingsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishLoadmore();
            }
        });

    }
    public void agree(String order_id){
      mvpPresenter.order_id(order_id);
    }


}
