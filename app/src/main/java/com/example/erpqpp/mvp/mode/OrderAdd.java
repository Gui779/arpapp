package com.example.erpqpp.mvp.mode;

import java.util.List;

public class OrderAdd {
    private String store_id;
    private PostXsBean order_detail;
    private List<PostProduct> pro_list;

    public OrderAdd(String store_id, PostXsBean order_detail, List<PostProduct> pro_list) {
        this.store_id = store_id;
        this.order_detail = order_detail;
        this.pro_list = pro_list;
    }

    public OrderAdd() {
    }
}
