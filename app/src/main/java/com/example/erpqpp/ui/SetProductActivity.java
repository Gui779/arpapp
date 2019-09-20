package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.TestAdd;
import com.example.erpqpp.mvp.mode.CpdwMode;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置产品
 */
public class SetProductActivity extends BaseActivity implements TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    @BindView(R.id.add_count)
    Item addCount;
    private String subscript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_product);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        subscript = intent.getStringExtra("subscript");
        CpdwMode.DataBean dataBean  = (CpdwMode.DataBean) intent.getSerializableExtra("databean");
        if (dataBean!=null){
            if (!dataBean.getUnit_name().equals("单件")) {
                addName.setText(dataBean.getUnit_name());
            }
            addCount.setText(dataBean.getRate()+"");
        }
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("产品材质");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

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
        if (addName.getText().trim().isEmpty()){
            toastShow("请输入单件");
            return;
        }
        if (!checkNameChese(addName.getText().trim())){
            toastShow("单件只能输入汉字");
            return;
        }


        if (addCount.getText().trim().isEmpty()){
            toastShow("请输入比例");
            return;
        }
        TestAdd testAdd=new TestAdd(addName.getText().trim(), addCount.getText().trim());
        Intent intent=new Intent();
        intent.putExtra("subscript",subscript);
        intent.putExtra("testadd",testAdd);
        setResult(2002,intent);
        finish();

    }


    /**
     * 判定输入汉字
     * @param c
     * @return
     */
    public  boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 检测String是否全是中文
     * @param name
     * @return
     */
    public  boolean checkNameChese(String name)
    {
        boolean res=true;
        char [] cTemp = name.toCharArray();
        for(int i=0;i<name.length();i++)
        {
            if(!isChinese(cTemp[i]))
            {
                res=false;
                break;
            }
        }
        return res;
    }



}
