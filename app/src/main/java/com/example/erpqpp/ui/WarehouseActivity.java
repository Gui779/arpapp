package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.fragment.CgStayCollectFragment;
import com.example.erpqpp.fragment.CgStayGoFragment;
import com.example.erpqpp.fragment.CgalreadyCollectFragment;
import com.example.erpqpp.fragment.CgalreadyGoFragment;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仓管列表
 */
public class WarehouseActivity extends BaseActivity implements  TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.pawn_tab)
    TabLayout pawnTab;
    @BindView(R.id.pawn_page)
    ViewPager pawnPage;

    private ArrayList<RxFragment> mFragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {
        tbvTitlebar.setMainTitle("仓管列表");
        tbvTitlebar.setTitleBarListener(this);

        titles.add("待出货");
        titles.add("已出货");
        titles.add("待收货");
        titles.add("已收货");

        mFragments.add(new CgStayGoFragment());
        mFragments.add(new CgalreadyGoFragment());
        mFragments.add(new CgStayCollectFragment());
        mFragments.add(new CgalreadyCollectFragment());


        pawnPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pawnTab.setupWithViewPager(pawnPage);

        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        if (status!=null){
            if (status.equals("2")){
                pawnPage.setCurrentItem(0);
            }else if (status.equals("3")){
                pawnPage.setCurrentItem(1);
            }
        }

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void leftClick() {
     finish();
    }

    @Override
    public void rightClick() {

    }



    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
