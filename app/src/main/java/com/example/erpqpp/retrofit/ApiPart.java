package com.example.erpqpp.retrofit;

/**
 * 接口
 */
public interface ApiPart {

    //
    //String Index = "data.do?channelId=0";

    //登录
    String login="login/dologin";

    //登出
    String logout="login/logout";

    //木工管理首页
    String mgindex="workmg/index";


    //获取木工人员
    String getworker="workmg/getworker";


    //木工收货
    String mgreceive="workmg/receive";


    //木工发货
    String send="workmg/send";


    //木工收货删除
    String delreceive="workmg/delreceive";

    //打磨收货删除
    String dmdelreceive="workdm/delreceive";


    //木工发货撤回
    String sendrevoke="workmg/send_revoke";

    //木工待发货撤回
    String dsend_revoke="workmg/dsend_revoke";


    //选择木工产品
    String add_product="worksq/add_product";

    //选择木工单件
    String getproductunit="workmg/getproductunit";


    //木工产品添加
    String add_productHandle="workmg/add_productHandle";

    //打磨产品添加
    String add_dmproductHandle="workdm/add_productHandle";


    //打磨管理首页
    String dmindex="workdm/index";

    //打磨收货
    String receive="workdm/receive";

    //打磨收货退回
    String receive_revoke="workdm/receive_revoke";

    //打磨发货
    String dmsend="workdm/send";

    //打磨待发货撤回
    String dmdsend_revoke="workdm/dsend_revoke";

    //打磨发货撤回
    String dmsend_revoke="workdm/send_revoke";


    //打磨产品列表
    String dmadd_product="workdm/add_product";


    //上漆首页
    String sqindex="worksq/index";

    //上漆删除
    String sqdelect="worksq/delreceive";

    //上漆收货
    String sqreceive="worksq/receive";

    //上漆收货撤回
    String sqreceive_revoke="worksq/receive_revoke";

    //上漆待收货撤回
    String sqsend_revoke="worksq/dsend_revoke";

    //上漆发货
    String sqsend="worksq/send";

    //上漆发货撤回
    String sqsendrevoke="worksq/send_revoke";

    //上漆添加产品展示
    String sqadd_product="worksq/add_product";

   //上漆产品添加
    String add_sqproductHandle = "worksq/add_productHandle";



    //已油漆仓库
    String yyqindex="ckyyq/index";

    //已油漆删除
    String yqdelete="ckyyq/delete";

    //已油漆仓库修改成本价
    String editCost="ckyyq/editCost";

    //查询仓库分类
    String getWarehousing="ckwm/getWarehousing";

    //已油漆仓库发货
    String yqreceipt="ckyyq/receipt";

    //已油漆仓库撤回
    String ryqeturnProduct="ckyyq/returnProduct";

    //已油漆仓库新增产品提交
    String yqadd_productHandle="ckyyq/add_productHandle";

    //成品仓库首页
    String cpckindex="ckcp/index";

    //成品仓库删除
    String cpckdelete="ckcp/delete";

    //成品仓库调货
    String cpcknontransshipment="ckyyq/nontransshipment";

    //成品仓库撤回
    String cpreturnProduct="ckcp/returnProduct";

    //成品仓库修改成本价
    String cpeditCost="ckcp/editCost";

    //成品仓库新增产品提交
    String cpadd_productHandle="ckcp/add_productHandle";

    //未磨仓库首页
    String wmindex = "ckwm/index";

    //未磨仓库删除
    String wmdelect = "ckwm/delete";

    //未磨仓库修改成本价
    String wmeditCost = "ckwm/editCost";

    //未磨仓库撤回
    String wmch = "ckwm/returnProduct";

    //未磨仓库发货
    String wmfh = "ckwm/receipt";

    //未磨仓库新增产品提交
    String wmadd = "ckwm/add_productHandle";

    //已磨仓库首页
    String ymindex="ckym/index";

    //已磨仓库删除
    String ymdelect="ckym/delete";

   // 已磨仓库修改成本价
    String ymeditCost ="ckym/editCost" ;

   // 已磨仓库发货
    String ymreceipt ="ckym/receipt" ;

   // 已磨仓库撤回
    String ymreturnProduct ="ckym/returnProduct" ;

    // 已磨仓库新增产品提交
    String ymadd ="ckym/add_productHandle" ;

    // 库存统计
    String statistics ="ckcp/WarehouseProductStatistics" ;

    //获取材质列表
    String getwood="workmg/getProductWood";

    //添加产品菜单
    String addwood="workmg/add_productMenu";

    //产品列表
    String productList="product/productList";

    //删除产品
    String del_product="product/del_product";


    //查询材质
    String getProductWood="product/getProductWood";

    //修改产品操作
    String updatawood="product/edit_productHandle";

    //修改产品操作
    String addcpwood="product/add_productHandle";

    //产品单位
    String productUnit="product/productUnit";

    //------------------------销售


    //客户管理
    String index="api/Sale/index";

    //客户管理详情
    String userList="api/Sale/userList";

    //添加客户
    String addUser="api/Sale/addUser";

    //库存统计
    String kctj="api/Sale/WarehouseProductStatistics";

    //销售管理
    String sale="api/Sale/sale";

    //销售管理详情
    String zsSaleList="api/Sale/zsSaleList";

    //个人中心-意见反馈
    String feedback="api/Boss/feedback";

    //发货（待发货）
    String fGoods="api/Sale/fGoods";

    //发货（待发货详情页面）
    String fxGoods="api/Sale/fxGoods";

    //发货（已发货）
    String sGoods="api/Sale/sGoods";

    //发货（已发货详情页面）
    String shGoods="/api/Sale/shGoods";

    //收货（待收货）
    String goodsDsh="api/Sale/goodsDsh";

    //收货（待收货详情）
    String goodsxq="api/Sale/goodsxq";

    //收货（已收货）
    String goodsysh="api/Sale/goodsysh";

    //收货（已收货详情）
    String goodsyshxq="api/Sale/goodsyshxq";

    //添加订单添加产品
    String productAdd="api/Sale/productAdd";

    //添加订单产品单件
    String singleton="api/Sale/singleton";

    //添加订单色号展示
    String colorlist="api/Sale/colorlist";

    //添加订单客户搜索
    String user="api/Sale/user";

    //添加订单时销售下拉
    String orderSale="api/Sale/orderSale";

    //添加订单产品门店
    String store="api/Sale/store";

    //销售添加接口
    String sale_add="api/Sale/sale_add";

    //折扣列表
    String discount="api/Sale/discount";

    //折扣列表否决按钮
    String discountNo="api/Sale/discountNo";

    //折扣列表同意按钮
    String discountAgree="api/Sale/discountAgree";

    //仓管列表（待出货）
    String warehouseListA="api/Sale/warehouseListA";

    //仓管列表（已出货）
    String warehouseListB="api/Sale/warehouseListB";

    //仓管列表（待收货）
    String warehouseListC="api/Sale/warehouseListC";

    //仓管列表（已收货）
    String warehouseListD="api/Sale/warehouseListD";

    //仓库收货页面列表1
    String warehouse_c="api/Sale/warehouse_c";

    //获取产品仓库列表信息2
    String get_product_list="api/Sale/get_product_list";

    //出货
    String out_product="api/Sale/out_product";

    //仓管个人中心
    String cGpersonal="api/Sale/cGpersonal";

    //退货页面列表1
    String returnGoodsPage="api/Sale/returnGoodsPage";

    //退货页面列表1
    String warehous_list="api/Sale/get_warehous_list";

    //退货3
    String returnGoods="api/Sale/returnGoods";

    //销售订单（信息）
    String orderMessage="api/Sale/orderMessage";

    //订单已出货状态 同意
    String agree="api/Sale/agree";

    //客户删除
    String company_delete="api/Sale/company_delete";

    //发货记录
    String record="ckwm/record";

    //色号展示列表
    String saleColor="api/Sale/color";

    //修改色号
    String colorEdit="api/Sale/color_edit";

    //添加色号
    String addColor="api/Sale/add_color";

    //删除色号
    String deleteColor="api/Sale/color_delete";

    //材质列表
    String woodSale="api/Sale/wood";

    //修改材质
    String editWood="api/Sale/wood_edit";

    //材质添加
    String addWood="api/Sale/add_wood";

    //材质删除
    String deleteWood="api/Sale/wood_delete";

}
