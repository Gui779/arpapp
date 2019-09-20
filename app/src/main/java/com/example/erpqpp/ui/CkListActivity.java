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
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.presenter.CkListPresenter;
import com.example.erpqpp.mvp.view.CkListView;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 仓库列表
 */
public class CkListActivity extends MvpActivity<CkListPresenter> implements  TitleBarView.BtnClickListener, CkListView {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.teachplan_rc)
    RecyclerView teachplanRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String order_id;
    private StatusLayoutManager statusLayoutManager;
    private CkListAdapter ckListAdapter;
    private int page=1;
    private List<CkListMode.DataBean> datalist;
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
        tbvTitlebar.setRightTitleText("出货");
        tbvTitlebar.setRightTitleColor(Color.WHITE);

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        ckListAdapter = new CkListAdapter(this,this);
        teachplanRc.setAdapter(ckListAdapter);
        teachplanRc.setLayoutManager(new LinearLayoutManager(this));

        addListener();
        mvpPresenter.ckList(order_id,page+"");

    }

    @Override
    protected CkListPresenter createPresenter() {
        return new CkListPresenter(this);
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
        List<String> idlist=new ArrayList<>();
        List<String> outCountlist=new ArrayList<>();
        List<String> proIdlist=new ArrayList<>();
         int selectcount=0;

         if (ckListAdapter.getList()!=null) {
             for (int i = 0; i < ckListAdapter.getList().size(); i++) {
                 List<CkListMode.DataBean.ProductUnitListBean> product_unit_list = ckListAdapter.getList().get(i).getProduct_unit_list();
                 if (product_unit_list != null) {
                     List<CkListMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
                     if (depot.size() == 0) {
                         toastShow("没有库存");
                         return;
                     }
                     int depotCount = 0;
                     for (int j = 0; j < depot.size(); j++) {
                         if (depot.get(j).isIsflag()) {
                             depotCount++;
                             selectcount++;
                         }
                     }
                     if (depotCount == 0) {
                         toastShow(ckListAdapter.getList().get(i).getProName() + "请至少选择一个");
                         return;
                     }
                 }

             }
         }else {
             toastShow("没有库存");
         }

        if (ckListAdapter.getList()!=null) {
            for (int i = 0; i < ckListAdapter.getList().size(); i++) {
                List<CkListMode.DataBean.ProductUnitListBean> product_unit_list = ckListAdapter.getList().get(i).getProduct_unit_list();
                if (product_unit_list!=null) {
                    List<CkListMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
                    int unitId = product_unit_list.get(0).getUnitId();
                    if (depot.size() > 0) {
                        for (int j = 0; j < depot.size(); j++) {
                            depot.get(j).setUnitId(unitId);
                            boolean isflag = depot.get(j).isIsflag();
                            if (isflag) {
                                if (depot.get(j).getCount().equals("数量")) {
                                    toastShow("请输入数量");
                                    return;
                                }
                                String count = depot.get(j).getCount();
                                int depotCount = depot.get(j).getDepotCount();
                                if (count != null) {
                                    if (Integer.parseInt(count) > depotCount) {
                                        toastShow("库存数量不足");
                                        return;
                                    }
                                }
                                idlist.add(depot.get(j).getId() + "");
                                outCountlist.add(depot.get(j).getCount());
                                proIdlist.add(depot.get(j).getUnitId() + "");

                            }
                        }

                    }

                }else {
                    toastShow("没有库存");

                }
            }
        }

        String id = MyListToString.setbanner(idlist);
        String outCount = MyListToString.setbanner(outCountlist);
        String proId = MyListToString.setbanner(proIdlist);
        LinLog.lLog(id+"----"+outCount + "------"+proId+"----"+order_id);
        String admin_id = (String) SPUtils.get(this, "admin_id", "");
        mvpPresenter.outProduct(order_id, proId,id,outCount);

    }

    @Override
    public void getDataSuccess(CkListMode ckListMode) {
        statusLayoutManager.showSuccessLayout();
        datalist = ckListMode.getData();
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
    public void outDataSuccess(CzMode czMode) {
        if (czMode.getCode().equals("1")){
            finish();
        }
        toastShow(czMode.getMsg());
    }

    @Override
    public void outDataFail(String msg) {
        LinLog.lLog(msg);
    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            mvpPresenter.ckList(order_id,page+"");
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            mvpPresenter.ckList(order_id,page+"");
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
                List<CkListMode.DataBean.ProductUnitListBean> product_unit_list = ckListAdapter.getList().get(Integer.parseInt(select)).getProduct_unit_list();
                if (select1!=null){
                    List<CkListMode.DataBean.ProductUnitListBean.DepotBean> depot = product_unit_list.get(0).getDepot();
                    depot.get(Integer.parseInt(select1)).setCount(count);

                    ckListAdapter.setList(datalist);
                    ckListAdapter.shuaxin();
                }
            }
        }
    }
}
