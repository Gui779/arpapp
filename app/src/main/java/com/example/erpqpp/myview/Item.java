package com.example.erpqpp.myview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;


public class Item extends LinearLayout {

    private int arrowTextColor= R.color.text_black;
    private String name;
    private boolean arrowShow=true;
    private boolean inputShow=false;
    private String inputHint;
    private String inputType;
    private boolean contentShow=false;
    private String contentInput;
    private int contentColor=R.color.text_black;
    private String arrowText;
    private setOnclick listener;
    private TextView tv_item_arrow;
    private TextView et_item_input;
    private TextView tv_item_content;
    private TextView tv_item_name;
    private LinearLayout ll_item_container;


    public Item(Context context) {
        this(context,null);
    }

    public Item(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Item(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);

        initView(context);
    }

    @SuppressLint("ResourceAsColor")
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemLayout);
         arrowTextColor = typedArray.getColor(R.styleable.ItemLayout_arrow_text_color, R.color.text_black);
        name = typedArray.getString(R.styleable.ItemLayout_name);
        arrowText = typedArray.getString(R.styleable.ItemLayout_arrow_text);
        arrowShow = typedArray.getBoolean(R.styleable.ItemLayout_arrow_show, true);
        inputShow = typedArray.getBoolean(R.styleable.ItemLayout_input_show, false);
        inputHint = typedArray.getString(R.styleable.ItemLayout_input_hint);
        inputType = typedArray.getString(R.styleable.ItemLayout_input_type);
        contentShow = typedArray.getBoolean(R.styleable.ItemLayout_content_show, false);
        contentInput = typedArray.getString(R.styleable.ItemLayout_content_input);
        contentColor = typedArray.getColor(R.styleable.ItemLayout_content_color, R.color.text_black);
        typedArray.recycle();

    }

    @SuppressLint("ResourceAsColor")
    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, this, true);
        tv_item_arrow = inflate.findViewById(R.id.tv_item_arrow);
        et_item_input = inflate.findViewById(R.id.et_item_input);
        tv_item_content = inflate.findViewById(R.id.tv_item_content);
        tv_item_name = inflate.findViewById(R.id.tv_item_name);
        ll_item_container = inflate.findViewById(R.id.ll_item_container);
        if (arrowShow) {
            tv_item_arrow.setVisibility(View.VISIBLE);
        } else {
            tv_item_arrow.setVisibility(GONE);
        }

        if (inputShow) {
            et_item_input.setVisibility(View.VISIBLE);
        } else {
            et_item_input.setVisibility(GONE);
        }

        if (contentShow) {
            tv_item_content.setVisibility(View.VISIBLE);
        } else {
            tv_item_content.setVisibility(GONE);
        }

        tv_item_name.setText(name);
        tv_item_arrow.setText(arrowText);
        tv_item_arrow.setTextColor(arrowTextColor);
        et_item_input.setHint(inputHint);

        if (inputType != null) {
            if (inputType.equals("number")) {
                et_item_input.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (inputType.equals("text")) {
                et_item_input.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                et_item_input.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        }

        tv_item_content.setText(contentInput);
        tv_item_content.setTextColor(contentColor);


            ll_item_container.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null) {
                        listener.onclick(v);
                    }
                }
            });

    }


    public  void  setonclickListener(setOnclick listener){
        this.listener=listener;
    }

     public interface setOnclick{
        void  onclick(View view);
     }

     public void setArrowColor(int arrowColor){
         tv_item_arrow.setTextColor(arrowColor);
     }

    public void setArrowText(String text){
        tv_item_arrow.setText(text);
    }

    public void setInputHint(String text){
        et_item_input.setHint(text);
    }
    public void setInputtype(){
        et_item_input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public String getArrowText(){
        return tv_item_arrow.getText().toString();
    }


    public void setText(String text){
        et_item_input.setText(text);
    }


    public String getText(){
        return et_item_input.getText().toString();
    }

    public void setContentText(String text){
        tv_item_content.setText(text);
    }


    public String getContentText(){
        return tv_item_content.getText().toString();
    }


    public void setName(String text){
        tv_item_name.setText(text);
    }


}
