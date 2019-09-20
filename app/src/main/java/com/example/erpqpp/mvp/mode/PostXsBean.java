package com.example.erpqpp.mvp.mode;

public class PostXsBean {
    //工厂id
    private String store_id;
    //客户id
    private String customer_id;
    //添加时间
    private String add_time;
    //预付款
    private String  due;
    //-1为全额付款
    private String beforepay;
    //销售人id
    private String sales_person;
    //门店id
    private String store;
    //交货日期
    private String delivery;
    //订单编号
    private String order_num;
    //收货人地址
    private String address;
    //手机
    private String  tel;
    //配件
    private String parts;
    //做法
    private String practice;
    //备注
    private String remarks;
    private String total;

    public PostXsBean(String store_id, String customer_id, String add_time, String due, String sales_person, String store, String delivery, String order_num, String address, String tel, String parts, String practice, String remarks,String total) {
        this.store_id = store_id;
        this.customer_id = customer_id;
        this.add_time = add_time;
        this.due = due;
        this.sales_person = sales_person;
        this.store = store;
        this.delivery = delivery;
        this.order_num = order_num;
        this.address = address;
        this.tel = tel;
        this.parts = parts;
        this.practice = practice;
        this.remarks = remarks;
        this.total = total;
    }

    public PostXsBean(String store_id, String customer_id, String add_time, String due, String beforepay, String sales_person, String store, String delivery, String order_num, String address, String tel, String parts, String practice, String remarks,String total) {
        this.store_id = store_id;
        this.customer_id = customer_id;
        this.add_time = add_time;
        this.due = due;
        this.beforepay = beforepay;
        this.sales_person = sales_person;
        this.store = store;
        this.delivery = delivery;
        this.order_num = order_num;
        this.address = address;
        this.tel = tel;
        this.parts = parts;
        this.practice = practice;
        this.remarks = remarks;
        this.total = total;
    }

    @Override
    public String toString() {
        return "PostXsBean{" +
                "store_id='" + store_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", add_time='" + add_time + '\'' +
                ", due='" + due + '\'' +
                ", beforepay='" + beforepay + '\'' +
                ", sales_person='" + sales_person + '\'' +
                ", store='" + store + '\'' +
                ", delivery='" + delivery + '\'' +
                ", order_num='" + order_num + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", parts='" + parts + '\'' +
                ", practice='" + practice + '\'' +
                ", remarks='" + remarks + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
