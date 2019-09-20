package com.example.erpqpp.ui;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.fragment.DmDfhFragment;
import com.example.erpqpp.fragment.DmFhFragment;
import com.example.erpqpp.fragment.DmSfjlFragment;
import com.example.erpqpp.fragment.DmShFragment;
import com.example.erpqpp.fragment.SqDfhFragment;
import com.example.erpqpp.fragment.SqFhFragment;
import com.example.erpqpp.fragment.SqSfjlFragment;
import com.example.erpqpp.fragment.SqShFragment;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 上漆管理
 * */
public class SqmanageActivity extends BaseActivity implements  TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.pawn_tab)
    TabLayout pawnTab;
    @BindView(R.id.pawn_page)
    ViewPager pawnPage;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgmanage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("上漆管理");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("新增");

        titles.add("收货");
        titles.add("待发货");
        titles.add("发货");
        titles.add("收发货记录");

        mFragments.add(new SqShFragment());
        mFragments.add(new SqDfhFragment());
        mFragments.add(new SqFhFragment());
        mFragments.add(new SqSfjlFragment());

        pawnPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pawnTab.setupWithViewPager(pawnPage);


        pawnTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        tbvTitlebar.setRightTitleText("新增");
                        tbvTitlebar.setTitleBarListener(SqmanageActivity.this);
                        break;
                    case 1:
                        tbvTitlebar.setRightTitleText("");
                        break;
                    case 2:
                        tbvTitlebar.setRightTitleText("");
                        break;
                    case 3:
                        tbvTitlebar.setRightTitleText("");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
        Bundle bundle=new Bundle();
        bundle.putString("title","上漆管理");
        startActivity(AddActivity.class,bundle);
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
