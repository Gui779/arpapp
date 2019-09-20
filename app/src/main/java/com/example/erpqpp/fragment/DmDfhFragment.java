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
import com.example.erpqpp.adapter.DmDdhlistAdapter;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.Mgbean;
import com.example.erpqpp.mvp.presenter.DmPresenter;
import com.example.erpqpp.mvp.view.DmView;
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
 * 打磨待发货
 */
public class DmDfhFragment extends MvpFragment<DmPresenter> implements DmView {

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
    private DmDdhlistAdapter DmDdhlistAdapter;
    private boolean isqx = false;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private int page=1;
    private List<DmMode.DataBean> dmModeData;

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
        
        DmDdhlistAdapter = new DmDdhlistAdapter(getActivity(),this,this);
          stayreceivingRc.setAdapter(DmDdhlistAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(getActivity()));

        ivSearchDelete.setOnClickListener(this);
        qx.setOnClickListener(this);
        fahuo.setOnClickListener(this);
        chehui.setOnClickListener(this);

        fahuo.setVisibility(View.VISIBLE);
        chehui.setVisibility(View.VISIBLE);
        chehui.setText("撤回");
        shouhuo.setVisibility(View.GONE);


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
                    mvpPresenter.getDmList(store_id, page+"", "2");
                } else {
                    mvpPresenter.getDmListsousuo(store_id, page+"", "2",editable.toString().trim());
                }
            }
        });


    }

    @Override
    protected void initretry() {
        String store_id = (String) SPUtils.get(getActivity(), "store_id", "");
        mvpPresenter.getDmList(store_id, page+"", "2");
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
            case R.id.qx:
                isqx = !isqx;
                myqx(isqx);
                qx.setChecked(isqx);
                break;
            case R.id.fahuo:
                List<Mgbean> listcount = new ArrayList<>();
                List<DmMode.DataBean> list = DmDdhlistAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isIscheck()) {
                        Mgbean mgbean=new Mgbean(list.get(i).getId()+"",list.get(i).getStore_id()+"",list.get(i).getUnit_num()+"");
                        listcount.add(mgbean);
                    }
                }
                if (listcount.size() > 0) {
                    Intent intent=new Intent(getActivity(),HarvestActivity.class);
                    intent.putExtra("title","打磨管理");
                    intent.putExtra("type","打磨发货");
                    intent.putExtra("listcount", (Serializable) listcount);
                    startActivityForResult(intent,666);
                } else {
                    toastShow("请您至少选择一个");
                }

                break;
            case R.id.chehui:
                List<Mgbean> listcount1 = new ArrayList<>();
                List<DmMode.DataBean> list1 = DmDdhlistAdapter.getList();
                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i).isIscheck()) {
                        Mgbean mgbean = new Mgbean(list1.get(i).getId() + "", list1.get(i).getStore_id() + "", list1.get(i).getUnit_num() + "");
                        listcount1.add(mgbean);
                    }
                }

                if (listcount1.size() > 0) {
                    Intent intent=new Intent(getActivity(),HarvestActivity.class);
                    intent.putExtra("title","打磨管理");
                    intent.putExtra("type","打磨待发货撤回");
                    intent.putExtra("listcount", (Serializable) listcount1);
                    startActivityForResult(intent,666);
                } else {
                    toastShow("请您至少选择一个");
                }

                break;
        }
    }

    private void myqx(boolean flag) {
        List<DmMode.DataBean> list = DmDdhlistAdapter.getList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIscheck(flag);
            }
        }
        DmDdhlistAdapter.notifyDataSetChanged();
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
            DmPresenter dmPresenter=new DmPresenter(this);
            if (store_id!=null){
                dmPresenter.getDmList(store_id, page + "", "2");
            }

        } else {
            // 相当于onpause()方法
            if (qx != null) {
                if (qx.isChecked()) {
                    qx.setChecked(false);
                    List<DmMode.DataBean> list = DmDdhlistAdapter.getList();
                    if (list!=null) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setIscheck(false);
                        }
                    }
                    DmDdhlistAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void getDataSuccess(DmMode dmMode) {
        statusLayoutManager.showSuccessLayout();
        dmModeData = dmMode.getData();
        if (dmModeData.size() > 0) {
            fahuo.setEnabled(true);
            chehui.setEnabled(true);
            qx.setEnabled(true);
            if (page == 1) {
                DmDdhlistAdapter.setList(dmModeData);
                DmDdhlistAdapter.notifyDataSetChanged();
            } else {
                DmDdhlistAdapter.getList().addAll(dmModeData);
                DmDdhlistAdapter.notifyDataSetChanged();
            }
        } else {
            fahuo.setEnabled(false);
            chehui.setEnabled(false);
            qx.setEnabled(false);
            if (page == 1 && dmModeData.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
                fahuo.setEnabled(true);
                chehui.setEnabled(true);
                qx.setEnabled(true);
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
                refreshlayout.finishRefresh();
            }
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (etSearchContent.getText().toString().isEmpty()) {

                    mvpPresenter.getDmList(store_id, page + "", "2");
                } else {
                    mvpPresenter.getDmListsousuo(store_id, page + "", "2", etSearchContent.getText().toString().trim());
                }
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&resultCode==001){
            List<DmMode.DataBean> dmModeData = DmDdhlistAdapter.getList();
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            dmModeData.get(Integer.parseInt(select)).setUnit_num(Integer.parseInt(count));
            DmDdhlistAdapter.notifyDataSetChanged();
        }
        if (requestCode==666&&resultCode==888){
            store_id = (String) SPUtils.get(getActivity(), "store_id", "");
            mvpPresenter.getDmList(store_id, page + "", "2");
            qx.setChecked(false);
        }
    }

    public void setcheckbox(boolean setcb){
        qx.setChecked(setcb);
    }
}
