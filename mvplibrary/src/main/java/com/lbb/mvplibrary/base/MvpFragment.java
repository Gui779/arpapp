package com.lbb.mvplibrary.base;


import android.os.Bundle;

import android.os.Handler;
import android.view.View;


import com.lbb.mvplibrary.R;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;


/**
 * Created by WuXiaolong on 2016/3/30.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements View.OnClickListener {
    protected P mvpPresenter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
        initretry();
    }

    protected abstract void initretry();





    protected abstract P createPresenter();

    protected StatusLayoutManager setLayout(View view){
            final StatusLayoutManager statusLayoutManager = new StatusLayoutManager.Builder(view)
                    .setDefaultLoadingText("拼命加载中...")
                    .setEmptyLayout(R.layout.layout_emptyorder)
                    .setLoadingLayout(R.layout.layout_progressshow)
                    .setErrorLayout(R.layout.activity_error)
                    .setErrorClickViewID(R.id.tv_base_again).
                            setOnStatusChildClickListener(new OnStatusChildClickListener() {
                                @Override
                                public void onEmptyChildClick(View view) {
                                }

                                @Override
                                public void onErrorChildClick(View view) {
                                    initretry();

                                }

                                @Override
                                public void onCustomerChildClick(View view) {

                                }
                            }).build();
            return statusLayoutManager;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
