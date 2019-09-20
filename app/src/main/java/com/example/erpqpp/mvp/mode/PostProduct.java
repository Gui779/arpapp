package com.example.erpqpp.mvp.mode;

public class PostProduct {
    //产品id
    private String pro_id;
    //产品名称
    private String pro_name;
    //色号id
    private String color_id;
    //色号名称
    private String color_name;
    //系列
    private String series;
    //产品材质名称
    private String wood_name;
    //类型
    private String unit;
    //销售金额
    private String sale_amount;
    //销售数量
    private String sale_num;
    //成本价
    private String cost_price;
    //标价
    private String price;
    //折扣率
    private String per_rate;


    public PostProduct(String pro_id, String pro_name, String color_id, String color_name, String series, String wood_name, String unit, String sale_amount, String sale_num, String cost_price, String price, String per_rate) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.color_id = color_id;
        this.color_name = color_name;
        this.series = series;
        this.wood_name = wood_name;
        this.unit = unit;
        this.sale_amount = sale_amount;
        this.sale_num = sale_num;
        this.cost_price = cost_price;
        this.price = price;
        this.per_rate = per_rate;
    }
}
