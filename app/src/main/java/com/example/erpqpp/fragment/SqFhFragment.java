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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SqFhlistAdapter;
import com.example.erpqpp.mvp.mode.Mgbean;
import com.example.erpqpp.mvp.mode.SqMode;
import com.example.erpqpp.mvp.presenter.SqPresenter;
import com.example.erpqpp.mvp.view.SqView;
import com.example.erpqpp.ui.HarvestActivity;
import com.lbb.mvplibrary.base.MvpFragment;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 上漆发货
 */
public class SqFhFragment extends MvpFragment<SqPresenter> implements SqView {

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
    private SqFhlistAdapter SqFhlistAdapter;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private int page=1;
    private boolean isqx=false;
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

        SqFhlistAdapter = new SqFhlistAdapter(getActivity(),this,this);
         stayreceivingRc.setAdapter(SqFhlistAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(getActivity()));

        ivSearchDelete.setOnClickListener(this);
        qx.setOnClickListener(this);
        chehui.setOnClickListener(this);

        shouhuo.setVisibility(View.GONE);
        fahuo.setVisibility(View.GONE);
        chehui.setVisibility(View.VISIBLE);



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
                    mvpPresenter.getSqindex(store_id, page+"", "3");
                } else {
                    mvpPresenter.getSqindexsousuo(store_id, page+"", "3",etSearchContent.getText().toString().trim());
                }
            }
        });
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.getSqindex(store_id, page+"", "3");
    }

    @Override
    protected SqPresenter createPresenter() {
        return new SqPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;
            case R.id.qx:
                isqx = !isqx;
                myqx(isqx);
                qx.setChecked(isqx);
                break;
            case R.id.chehui:
                List<Mgbean> listcount = new ArrayList<>();
                List<SqMode.DataBean> list = SqFhlistAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isIscheck()) {
                        Mgbean mgbean=new Mgbean(list.get(i).getId()+"",list.get(i).getStore_id()+"",list.get(i).getUnit_num()+"");
                        listcount.add(mgbean);
                    }
                }
                if (listcount.size() > 0) {
                    Intent intent=new Intent(getActivity(),HarvestActivity.class);
                    intent.putExtra("title","上漆管理");
                    intent.putExtra("type","上漆发货撤回");
                    intent.putExtra("listcount", (Serializable) listcount);
                    startActivityForResult(intent,666);
                } else {
                    toastShow("请您至少选择一个");
                }
                break;
        }
    }

    private void myqx(boolean flag) {
        List<SqMode.DataBean> list = SqFhlistAdapter.getList();
        if (list!=null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIscheck(flag);
            }
        }
        SqFhlistAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            SqPresenter sqPresenter=new SqPresenter(this);
            if (store_id!=null){
                sqPresenter.getSqindex(store_id, page + "", "3");
            }
        } else {
            // 相当于onpause()方法
            if (qx != null) {
                if (qx.isChecked()) {
                    qx.setChecked(false);
                    List<SqMode.DataBean> list = SqFhlistAdapter.getList();
                    if (list!=null) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setIscheck(false);
                        }
                    }
                    SqFhlistAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void getDataSuccess(SqMode sqMode) {
        statusLayoutManager.showSuccessLayout();
        List<SqMode.DataBean> SqModeData = sqMode.getData();
        if (SqModeData.size() > 0) {
            chehui.setEnabled(true);
            qx.setEnabled(true);
            if (page == 1) {
                SqFhlistAdapter.setList(SqModeData);
                SqFhlistAdapter.notifyDataSetChanged();
            } else {
                SqFhlistAdapter.getList().addAll(SqModeData);
                SqFhlistAdapter.notifyDataSetChanged();
            }
        } else {
            chehui.setEnabled(false);
            qx.setEnabled(false);
            if (page == 1 && SqModeData.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
                chehui.setEnabled(true);
                qx.setEnabled(true);
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {

    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
            }
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (etSearchContent.getText().toString().isEmpty()) {

                    mvpPresenter.getSqindex(store_id, page + "", "3");
                } else {
                    mvpPresenter.getSqindexsousuo(store_id, page + "", "3", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&resultCode==001){
            List<SqMode.DataBean> SqModeData = SqFhlistAdapter.getList();
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            SqModeData.get(Integer.parseInt(select)).setUnit_num(Integer.parseInt(count));
            SqFhlistAdapter.notifyDataSetChanged();
        }
        if (requestCode==666&&resultCode==888){
            store_id = (String) SPUtils.get(getActivity(), "store_id", "");
            mvpPresenter.getSqindex(store_id, page + "", "3");
            qx.setChecked(false);
        }

    }

    public void setcheckbox(boolean setcb){
        qx.setChecked(setcb);
    }
}
