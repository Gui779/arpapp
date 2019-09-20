package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.CkListAdapter;
import com.example.erpqpp.adapter.CkShListAdapter;
import com.example.erpqpp.mvp.mode.CgShMode;
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.mvp.mode.CkResponse;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.presenter.CkListPresenter;
import com.example.erpqpp.mvp.presenter.CkShListPresenter;
import com.example.erpqpp.mvp.view.CkListView;
import com.example.erpqpp.mvp.view.CkShListView;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 仓库列表
 */
public class CkShListActivity extends MvpActivity<CkShListPresenter> implements  TitleBarView.BtnClickListener, CkShListView {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.teachplan_rc)
    RecyclerView teachplanRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String order_id;
    private StatusLayoutManager statusLayoutManager;
    private CkShListAdapter ckListAdapter;
    private int page=1;
    private int postunitId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("仓管列表");
        tbvTitlebar.setMainTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("收货");
        tbvTitlebar.setRightTitleColor(Color.WHITE);

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        ckListAdapter=new CkShListAdapter(this,this);
        teachplanRc.setAdapter(ckListAdapter);
        teachplanRc.setLayoutManager(new LinearLayoutManager(this));

        addListener();
        mvpPresenter.warehous_list(order_id,page+"");

    }

    @Override
    protected CkShListPresenter createPresenter() {
        return new CkShListPresenter(this);
    }


    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        for (int i = 0; i < ckListAdapter.getList().size(); i++) {
            List<CgShMode.DataBean.ProductUnitListBean> product_unit_list = ckListAdapter.getList().get(i).getProduct_unit_list();
            List<CgShMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
            if (depot.size()==0){
                toastShow("没有库存");
                return;
            }

            int depotCount = 0;
            int zonghe=0;
            for (int j = 0; j <depot.size() ; j++) {
                if (depot.size()==0){
                    toastShow("库存数量不足");
                    return;
                }
                if (depot.get(j).isIsflag()){
                    if (depot.get(j).getOutCount().equals("数量")){
                        toastShow("请输入数量");
                        return;
                    }
                    depotCount++;
                    String outCount = depot.get(j).getOutCount();
                    if (outCount!=null){
                        zonghe+=Integer.parseInt(outCount);
                    }
                }

            }
            if (depotCount == 0){
                toastShow(ckListAdapter.getList().get(i).getProName() + "请至少选择一个");
                return;
            }
            if (zonghe>product_unit_list.get(0).getOrderCount()){
                toastShow("输入的数量不能大于订单数量");
                return;
            }

        }
        CkResponse ckResponse = new CkResponse();
        List<CkResponse.DataBean> list = new ArrayList<>();
        for (int i = 0; i < ckListAdapter.getList().size(); i++) {
            CkResponse.DataBean dataBean = new CkResponse.DataBean();
            CgShMode.DataBean oldDataBean = ckListAdapter.getList().get(i);
            dataBean.setProId(oldDataBean.getProId());
            dataBean.setProName(oldDataBean.getProName());
            dataBean.setTypeName(oldDataBean.getTypeName());
            dataBean.setOrder_unit_id(oldDataBean.getOrder_unit_id());
            CkResponse.DataBean.ProductUnitListBean productUnitListBean = new CkResponse.DataBean.ProductUnitListBean();
            List<CgShMode.DataBean.ProductUnitListBean> product_unit_list = oldDataBean.getProduct_unit_list();
            productUnitListBean.setUnit(product_unit_list.get(0).getUnit());
            productUnitListBean.setUnitId(product_unit_list.get(0).getUnitId());
            productUnitListBean.setUnitName(product_unit_list.get(0).getUnitName());
            productUnitListBean.setColorId(product_unit_list.get(0).getColorId());
            productUnitListBean.setColorName(product_unit_list.get(0).getColorName());
            productUnitListBean.setOrderCount(product_unit_list.get(0).getOrderCount());
            int orderCount = product_unit_list.get(0).getOrderCount();
            List<CkResponse.DataBean.ProductUnitListBean.DepotBean> depotBeanList = new ArrayList<>();
            List<CgShMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
            int unitId = product_unit_list.get(0).getUnitId();
            for (int j = 0; j < depot.size(); j++) {
                CgShMode.DataBean.ProductUnitListBean.DepotBean depotBean = depot.get(j);
                boolean isflag = depotBean.isIsflag();
                if (isflag) {
                    if (depot.get(j).getOutCount().equals("数量")){
                        toastShow("请输入数量");
                        return;
                    }
                    CkResponse.DataBean.ProductUnitListBean.DepotBean bean = new CkResponse.DataBean.ProductUnitListBean.DepotBean();
                    bean.setId(depotBean.getId());
                    bean.setStore_id(depotBean.getStore_id());
                    bean.setDepotName(depotBean.getDepotName());
                    bean.setDepotCount(depotBean.getDepotCount());
                    bean.setCount(depotBean.getOutCount());
                    depotBeanList.add(bean);
                }
            }
            productUnitListBean.setDepot(depotBeanList);
            dataBean.setProduct_unit_list(productUnitListBean);
            list.add(dataBean);
        }
        ckResponse.setOrder_id(order_id);
        ckResponse.setData(list);
         mvpPresenter.returnGoods(ckResponse);



    }



    @Override
    public void getDataSuccess(CgShMode cgShMode) {
        statusLayoutManager.showSuccessLayout();
        List<CgShMode.DataBean> datalist = cgShMode.getData();
        ckListAdapter.setList(datalist);
        if (datalist.size() > 0) {
            if (page == 1) {
                ckListAdapter.setList(datalist);
                ckListAdapter.notifyDataSetChanged();
            } else {
                ckListAdapter.getList().addAll(datalist);
                ckListAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && datalist.size() == 0) {
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
    public void ThDataSuccess(CzMode czMode) {
        if (czMode.getCode().equals("1")){
           finish();
        }
        toastShow(czMode.getMsg());
    }

    @Override
    public void ThDataFail(String msg) {
        LinLog.lLog(msg);
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            mvpPresenter.warehous_list(order_id,page+"");
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            mvpPresenter.warehous_list(order_id,page+"");
            refreshlayout.finishLoadmore();
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1111&&resultCode==001){
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            String select1 = data.getStringExtra("select1");
            if (select!=null){
                List<CgShMode.DataBean.ProductUnitListBean> product_unit_list = ckListAdapter.getList().get(Integer.parseInt(select)).getProduct_unit_list();
                if (select1!=null){
                    List<CgShMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
                    depot.get(Integer.parseInt(select1)).setOutCount(count);
                    ckListAdapter.setList(ckListAdapter.getList());
                    ckListAdapter.shuaxin();
                }
            }
        }
    }


   
}
