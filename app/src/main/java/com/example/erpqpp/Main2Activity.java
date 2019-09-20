package com.example.erpqpp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.erpqpp.mvp.mode.Mymode;
import com.example.erpqpp.mvp.presenter.MyPresenter;
import com.example.erpqpp.mvp.view.Myview;

import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

public class Main2Activity extends MvpActivity<MyPresenter> implements Myview {

  /*   @BindView(R.id.rc)
     RecyclerView rc;*/
     @BindView(R.id.refreshLayout)
     SmartRefreshLayout mRefreshLayout;

    private StatusLayoutManager statusLayoutManager;
    private int page=1;
    Map<String,String> map=new HashMap<>();
   // private Myadapter myadapter;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //getSupportFragmentManager().beginTransaction().add(R.id.fl,new Myfragment()).commit();


        ButterKnife.bind(this);

        statusLayoutManager = setLayout(mRefreshLayout);
        statusLayoutManager.showLoadingLayout();

        addListener();
        LinLog.init(true,"哈哈");

         SPUtils.put(this,"name","6666");
         Log.d("哈哈", (String) SPUtils.get(this,"name",""));
    }


    /**
     * 刷新加载
     * */
    private void addListener() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Map<String,String> map=new HashMap<>();
                page=1;
                map.put("startNum",page+"");
                mvpPresenter.loadDataByRetrofitRxjava(map);
                refreshlayout.finishRefresh();
            }
        });


        //加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                Map<String,String> map=new HashMap<>();
                page++;
                map.put("startNum",page+"");
                mvpPresenter.loadDataByRetrofitRxjava(map);
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    protected MyPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initretry() {

    }





    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(Mymode model) {
       /* statusLayoutManager.showSuccessLayout();
        List<Mymode.DataBean> data = model.getData();
        if (model.getData().size()>0) {
            if (page == 1) {
                myadapter = new Myadapter(R.layout.rc, data);
                rc.setAdapter(myadapter);
                rc.setLayoutManager(new LinearLayoutManager(this));
            } else {
                myadapter.getData().addAll(model.getData());
                myadapter.notifyDataSetChanged();
            }
        }else {
            if (page==1&&model.getData().size()==0){
                statusLayoutManager.showEmptyLayout();
            }else {
                page--;
                toastShow("没有更多数据了");
            }
        }*/
    }

    @Override
    public void getDataFail(String msg) {
        //加载失败
        statusLayoutManager.showErrorLayout();

    }
}
