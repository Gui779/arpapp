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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.DmSfhlistAdapter;
import com.example.erpqpp.adapter.MglistAdapter;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.presenter.DmPresenter;
import com.example.erpqpp.mvp.view.DmView;
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
 * 打磨收发货记录
 */
public class DmSfjlFragment extends MvpFragment<DmPresenter> implements DmView {

    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.stayreceiving_rc)
    RecyclerView stayreceivingRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.qx)
    CheckBox qx;
    @BindView(R.id.rl)
    RelativeLayout rl;
    Unbinder unbinder;
    @BindView(R.id.shouhuo)
    TextView shouhuo;
    @BindView(R.id.fahuo)
    TextView fahuo;
    @BindView(R.id.chehui)
    TextView chehui;
    private MglistAdapter mglistAdapter;
    private boolean isqx = false;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private int page=1;
    private List<DmMode.DataBean> dmModeData;
    private DmSfhlistAdapter DmSfhlistAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_dmmanagemt, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();

        return inflate;
    }

    private void initdata() {
        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(getActivity(), "store_id", "");

        DmSfhlistAdapter = new DmSfhlistAdapter(getActivity(), this);
        stayreceivingRc.setAdapter(DmSfhlistAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(getActivity()));

        ivSearchDelete.setOnClickListener(this);
        qx.setOnClickListener(this);
        shouhuo.setOnClickListener(this);

       rl.setVisibility(View.GONE);

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
                    mvpPresenter.getDmList(store_id, page+"", "4");
                } else {
                    mvpPresenter.getDmListsousuo(store_id, page+"", "4",etSearchContent.getText().toString().trim());
                }
            }
        });


    }

    @Override
    protected void initretry() {
        String store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.getDmList(store_id, page+"", "4");
    }

    @Override
    protected DmPresenter createPresenter() {
        return new DmPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;

        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getDataSuccess(DmMode dmMode) {
        statusLayoutManager.showSuccessLayout();
        dmModeData = dmMode.getData();
        if (dmModeData.size() > 0) {
            fahuo.setEnabled(true);
            chehui.setEnabled(true);
            if (page == 1) {
                DmSfhlistAdapter.setList(dmModeData);
                DmSfhlistAdapter.notifyDataSetChanged();
            } else {
                DmSfhlistAdapter.getList().addAll(dmModeData);
                DmSfhlistAdapter.notifyDataSetChanged();
            }
        } else {
            fahuo.setEnabled(false);
            chehui.setEnabled(false);
            if (page == 1 && dmModeData.size() == 0) {
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
    public void delectDataSuccess(MgMode mgMode) {

    }

    @Override
    public void delectDataFail(String msg) {

    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                mvpPresenter.getDmList(store_id, page + "", "4");
                refreshlayout.finishRefresh();
            }
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (etSearchContent.getText().toString().isEmpty()) {

                    mvpPresenter.getDmList(store_id, page + "", "4");
                } else {
                    mvpPresenter.getDmListsousuo(store_id, page + "", "4", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishLoadmore();
            }
        });

    }


}
