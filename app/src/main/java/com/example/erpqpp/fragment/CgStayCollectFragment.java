package com.example.erpqpp.fragment;

import android.content.Intent;
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
import com.example.erpqpp.adapter.CgShlistAdapter;
import com.example.erpqpp.adapter.CglistAdapter;
import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.XsMode;
import com.example.erpqpp.mvp.presenter.CglistPresenter;
import com.example.erpqpp.mvp.view.CglistView;
import com.example.erpqpp.ui.CkListActivity;
import com.example.erpqpp.ui.CkShListActivity;
import com.lbb.mvplibrary.base.BaseFragment;
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
 * 仓管待收货
 * */
public class CgStayCollectFragment extends MvpFragment<CglistPresenter> implements CglistView {
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.cglist_rc)
    RecyclerView cglistRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int page = 1;
    private CgShlistAdapter cglistAdapter;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private String order_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cglist, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();
        return inflate;
    }

    private void initdata() {
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        etSearchContent.setHint("客户名称搜索");
        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();


        cglistAdapter= new CgShlistAdapter(getActivity());
        cglistAdapter.setName("收货");
        cglistRc.setAdapter(cglistAdapter);
        cglistRc.setLayoutManager(new LinearLayoutManager(getActivity()));
        cglistAdapter.setCgStayGoFragment(this);
        ivSearchDelete.setOnClickListener(this);


        //刷新加载
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
                    mvpPresenter.CgDslist(store_id,page+"");
                } else {
                    mvpPresenter.CgDslistsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected CglistPresenter createPresenter() {
        return new CglistPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.CgDslist(store_id,page+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;
        }
    }

    @Override
    public void getDataSuccess(CgStayGoMode cgStayGoMode) {
        statusLayoutManager.showSuccessLayout();
        List<CgStayGoMode.DataBean.ListBean> list = cgStayGoMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                cglistAdapter.setList(list);
                cglistAdapter.notifyDataSetChanged();
            } else {
                cglistAdapter.getList().addAll(list);
                cglistAdapter.notifyDataSetChanged();
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
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }



    @Override
    public void getYzDataSuccess(MgMode mgMode) {
      if (mgMode.getCode().equals("1")){
          Intent intent=new Intent(getActivity(), CkShListActivity.class);
          intent.putExtra("order_id",order_id);
          startActivity(intent);
      }else {
          toastShow(mgMode.getMsg());
      }
    }

    @Override
    public void getYzDataFail(String msg) {
        LinLog.lLog(msg);
    }




    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if (etSearchContent.getText().toString().isEmpty()) {
                    mvpPresenter.CgDslist(store_id, page + "");
                } else {
                    mvpPresenter.CgDslistsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
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
                    mvpPresenter.CgDslist(store_id, page + "");
                } else {
                    mvpPresenter.CgDslistsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            CglistPresenter cglistPresenter = new CglistPresenter(this);
            if (store_id!=null) {
                cglistPresenter.CgDslist(store_id, page + "");
            }
        }else {
            page=1;
        }
    }

    public void Sh(String order_id1){
        this.order_id=order_id1;
        mvpPresenter.returnGoodsPage(order_id1);
    }


    @Override
    public void onResume() {
        super.onResume();
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.CgDslist(store_id,page+"");
    }
}
