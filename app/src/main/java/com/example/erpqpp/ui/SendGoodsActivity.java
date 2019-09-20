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
import com.example.erpqpp.fragment.AlreadDeliverFragment;
import com.example.erpqpp.fragment.AlreadyreceivingFragment;
import com.example.erpqpp.fragment.CgalreadyGoFragment;
import com.example.erpqpp.fragment.StayDeliverFragment;
import com.example.erpqpp.fragment.StayreceivingFragment;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发货
 */
public class SendGoodsActivity extends BaseActivity implements TitleBarView.BtnClickListener{


    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.pawn_tab)
    TabLayout pawnTab;
    @BindView(R.id.pawn_page)
    ViewPager pawnPage;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_goods);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        if (name.equals("收货")){
            tbvTitlebar.setMainTitle("收货");
            tbvTitlebar.setTitleBarListener(this);

            mFragments.add(new StayreceivingFragment());
            mFragments.add(new AlreadDeliverFragment());
            titles.add("待收货");
            titles.add("已收货");


        }else if (name.equals("发货")){

            tbvTitlebar.setMainTitle("发货");
            tbvTitlebar.setTitleBarListener(this);

            titles.add("待发货");
            titles.add("已发货");

            mFragments.add(new StayDeliverFragment());
            mFragments.add(new AlreadyreceivingFragment());


        }

        pawnTab.setupWithViewPager(pawnPage);
        pawnPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragments.clear();
        titles.clear();
    }
}
