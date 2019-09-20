package com.example.erpqpp.retrofit;



import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.AddclientMode;
import com.example.erpqpp.mvp.mode.CgShMode;
import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.mode.CkResponse;
import com.example.erpqpp.mvp.mode.ColourManageMode;
import com.example.erpqpp.mvp.mode.CpckMode;
import com.example.erpqpp.mvp.mode.CpdwMode;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.DiscountMode;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.FhjlMode;
import com.example.erpqpp.mvp.mode.LogOutMode;
import com.example.erpqpp.mvp.mode.LoginMode;
import com.example.erpqpp.mvp.mode.ManagementListMode;
import com.example.erpqpp.mvp.mode.ManagementMode;
import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;
import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.mvp.mode.MdMode;
import com.example.erpqpp.mvp.mode.MeMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.MgShMode;
import com.example.erpqpp.mvp.mode.OrderAdd;
import com.example.erpqpp.mvp.mode.OrderMsgMode;
import com.example.erpqpp.mvp.mode.ProductMode;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.mode.SelectNameMode;
import com.example.erpqpp.mvp.mode.SelectShMode;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;
import com.example.erpqpp.mvp.mode.SendGoodsDetailsMode;
import com.example.erpqpp.mvp.mode.SingletonMode;
import com.example.erpqpp.mvp.mode.SqMode;
import com.example.erpqpp.mvp.mode.StatisticsMode;
import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.mvp.mode.TextureManageMode;
import com.example.erpqpp.mvp.mode.WarehouseStatisticsMode;
import com.example.erpqpp.mvp.mode.WmMode;
import com.example.erpqpp.mvp.mode.WorkerMode;
import com.example.erpqpp.mvp.mode.XsListMode;
import com.example.erpqpp.mvp.mode.XsMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.mode.XzCzMode;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WuXiaolong on 2016/3/24.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://erp.bestshowgroup.com/";


   /* @POST(ApiPart.Index)@FormUrlEncoded
    Observable<Mymode> getIndex(@FieldMap Map<String, String> map);*/

 //登录
 @POST(ApiPart.login)
 @FormUrlEncoded
 Observable<LoginMode> Login(@FieldMap Map<String, String> map);

    //登录
    @POST(ApiPart.logout)
    @FormUrlEncoded
    Observable<LogOutMode> Logout(@FieldMap Map<String, String> map);


 //客户管理
    @POST(ApiPart.index)
    @FormUrlEncoded
    Observable<ManagementMode> getindex(@FieldMap Map<String, String> map);


    //客户管理详情
    @POST(ApiPart.userList)
    @FormUrlEncoded
    Observable<ManagementListMode> getuserList(@FieldMap Map<String, String> map);


    //添加客户
    @POST(ApiPart.addUser)
    @FormUrlEncoded
    Observable<AddclientMode> addUser(@FieldMap Map<String, String> map);


    //木工管理收货
    @POST(ApiPart.mgindex)
    @FormUrlEncoded
    Observable<MgShMode> Getmgsh(@FieldMap Map<String, String> map);


    //获取木工人员
    @POST(ApiPart.getworker)
    @FormUrlEncoded
    Observable<WorkerMode> getworker(@FieldMap Map<String, String> map);


    //木工收货
    @POST(ApiPart.mgreceive)
    @FormUrlEncoded
    Observable<MgMode> mgreceiver(@FieldMap Map<String, String> map);


    //木工发货
    @POST(ApiPart.send)
    @FormUrlEncoded
    Observable<MgMode> send(@FieldMap Map<String, String> map);


    //木工收货删除
    @POST(ApiPart.delreceive)
    @FormUrlEncoded
    Observable<MgMode> delreceive(@FieldMap Map<String, String> map);

    //打磨收货删除
    @POST(ApiPart.dmdelreceive)
    @FormUrlEncoded
    Observable<MgMode> Dmdelreceive(@FieldMap Map<String, String> map);


    //木工发货撤回
    @POST(ApiPart.sendrevoke)
    @FormUrlEncoded
    Observable<MgMode> Sendrevoke(@FieldMap Map<String, String> map);

    //木工待发货撤回
    @POST(ApiPart.dsend_revoke)
    @FormUrlEncoded
    Observable<MgMode> Dsend_revoke(@FieldMap Map<String, String> map);


    //选择木工产品
    @POST(ApiPart.add_product)
    @FormUrlEncoded
    Observable<SelectMgMode> Addproduct(@FieldMap Map<String, String> map);


   //选择木工单件
   @POST(ApiPart.getproductunit)
   @FormUrlEncoded
   Observable<SingletonMode> getProductunit(@FieldMap Map<String, String> map);

   //木工产品添加
   @POST(ApiPart.add_productHandle)
   @FormUrlEncoded
   Observable<MgMode> AddproductHandle(@FieldMap Map<String, String> map);


   //打磨产品添加
   @POST(ApiPart.add_dmproductHandle)
   @FormUrlEncoded
   Observable<MgMode> AddDmproductHandle(@FieldMap Map<String, String> map);

   //打磨管理首页
   @POST(ApiPart.dmindex)
   @FormUrlEncoded
   Observable<DmMode> getDmIndex(@FieldMap Map<String, String> map);

   //打磨收货
   @POST(ApiPart.receive)
   @FormUrlEncoded
   Observable<MgMode> getreceive(@FieldMap Map<String, String> map);


   //打磨收货退回
   @POST(ApiPart.receive_revoke)
   @FormUrlEncoded
   Observable<MgMode> getreceive_revoke(@FieldMap Map<String, String> map);


   //打磨发货
   @POST(ApiPart.dmsend)
   @FormUrlEncoded
   Observable<MgMode> DmSend(@FieldMap Map<String, String> map);

   //打磨待发货撤回
   @POST(ApiPart.dmdsend_revoke)
   @FormUrlEncoded
   Observable<MgMode> dmdsend_revoke(@FieldMap Map<String, String> map);


   //打磨发货撤回
   @POST(ApiPart.dmsend_revoke)
   @FormUrlEncoded
   Observable<MgMode> dmSend_revoke(@FieldMap Map<String, String> map);

   //打磨产品列表
   @POST(ApiPart.dmadd_product)
   @FormUrlEncoded
   Observable<SelectMgMode> dmadd_product(@FieldMap Map<String, String> map);

   //上漆产品列表
   @POST(ApiPart.sqindex)
   @FormUrlEncoded
   Observable<SqMode> getSqindex(@FieldMap Map<String, String> map);

   //上漆删除
   @POST(ApiPart.sqdelect)
   @FormUrlEncoded
   Observable<SqMode> Sqdelect(@FieldMap Map<String, String> map);


    //上漆管理收货
    @POST(ApiPart.sqreceive)
    @FormUrlEncoded
    Observable<MgMode> Sqreceive(@FieldMap Map<String, String> map);


    //上漆收货撤回
    @POST(ApiPart.sqreceive_revoke)
    @FormUrlEncoded
    Observable<MgMode> Sqsqreceive_revoke(@FieldMap Map<String, String> map);

    //上漆待发货撤回
    @POST(ApiPart.sqsend_revoke)
    @FormUrlEncoded
    Observable<MgMode> Sqsend_revoke(@FieldMap Map<String, String> map);


    //上漆管理发货
    @POST(ApiPart.sqsend)
    @FormUrlEncoded
    Observable<MgMode> Sqsend(@FieldMap Map<String, String> map);


    //选择颜色
    @POST(ApiPart.add_product)
    @FormUrlEncoded
    Observable<SelectMgMode> SelectColor(@FieldMap Map<String, String> map);


    //上漆发货撤回
    @POST(ApiPart.sqsendrevoke)
    @FormUrlEncoded
    Observable<MgMode> Sqsendrevoke(@FieldMap Map<String, String> map);


    //上漆添加产品展示
    @POST(ApiPart.sqadd_product)
    @FormUrlEncoded
    Observable<SelectMgMode> Sqadd_product(@FieldMap Map<String, String> map);

    //上漆产品添加
    @POST(ApiPart.add_sqproductHandle)
    @FormUrlEncoded
    Observable<MgMode> AddSqproductHandle(@FieldMap Map<String, String> map);



    //已油漆仓库首页
    @POST(ApiPart.yyqindex)
    @FormUrlEncoded
    Observable<WmMode> Yyqindex(@FieldMap Map<String, String> map);

    //已油漆仓删除
    @POST(ApiPart.yqdelete)
    @FormUrlEncoded
    Observable<MgMode> YqDelect(@FieldMap Map<String, String> map);


    //已油漆仓库修改成本价
    @POST(ApiPart.editCost)
    @FormUrlEncoded
    Observable<MgMode> Updataprice(@FieldMap Map<String, String> map);

    //查询仓库分类
    @POST(ApiPart.getWarehousing)
    @FormUrlEncoded
    Observable<CkMode> getWarehousing(@FieldMap Map<String, String> map);


    //已油漆仓库发货
    @POST(ApiPart.yqreceipt)
    @FormUrlEncoded
    Observable<MgMode> yqreceipt(@FieldMap Map<String, String> map);


    //已油漆仓库发货
    @POST(ApiPart.ryqeturnProduct)
    @FormUrlEncoded
    Observable<MgMode> ryqeturnProduct(@FieldMap Map<String, String> map);

    //已油漆仓库新增产品提交
    @POST(ApiPart.yqadd_productHandle)
    @FormUrlEncoded
    Observable<MgMode> yqaddproduct(@FieldMap Map<String, String> map);


    //成品仓库首页
    @POST(ApiPart.cpckindex)
    @FormUrlEncoded
    Observable<CpckMode> cpckIndex(@FieldMap Map<String, String> map);

    //成品仓库删除
    @POST(ApiPart.cpckdelete)
    @FormUrlEncoded
    Observable<MgMode> cpckDelete(@FieldMap Map<String, String> map);

    //成品仓库调货
    @POST(ApiPart.cpcknontransshipment)
    @FormUrlEncoded
    Observable<MgMode> CpDh(@FieldMap Map<String, String> map);


    //成品仓库撤回
    @POST(ApiPart.cpreturnProduct)
    @FormUrlEncoded
    Observable<MgMode> CpCh(@FieldMap Map<String, String> map);

    //成品仓库修改成本价
    @POST(ApiPart.cpeditCost)
    @FormUrlEncoded
    Observable<MgMode> UpdataCpprice(@FieldMap Map<String, String> map);

    //成品仓库新增产品提交
    @POST(ApiPart.cpadd_productHandle)
    @FormUrlEncoded
    Observable<MgMode> Addcpproduct(@FieldMap Map<String, String> map);



    //未磨仓库首页
    @POST(ApiPart.wmindex)
    @FormUrlEncoded
    Observable<WmMode> WmIndex(@FieldMap Map<String, String> map);


    //未磨仓库删除
    @POST(ApiPart.wmdelect)
    @FormUrlEncoded
    Observable<MgMode> WmDelect(@FieldMap Map<String, String> map);

    //未磨仓库修改成本价
    @POST(ApiPart.wmeditCost)
    @FormUrlEncoded
    Observable<MgMode> UpdataWmprice(@FieldMap Map<String, String> map);

    //未磨仓库撤回
    @POST(ApiPart.wmch)
    @FormUrlEncoded
    Observable<MgMode> WmCh(@FieldMap Map<String, String> map);

    //未磨仓库发货
    @POST(ApiPart.wmfh)
    @FormUrlEncoded
    Observable<MgMode> WmFh(@FieldMap Map<String, String> map);

    //未磨仓库发货
    @POST(ApiPart.wmadd)
    @FormUrlEncoded
    Observable<MgMode> Wmadd(@FieldMap Map<String, String> map);

    //已磨仓库首页
    @POST(ApiPart.ymindex)
    @FormUrlEncoded
    Observable<WmMode> YmIndex(@FieldMap Map<String, String> map);

    //已磨仓库删除
    @POST(ApiPart.ymdelect)
    @FormUrlEncoded
    Observable<MgMode> YmDelect(@FieldMap Map<String, String> map);


    //已磨仓库修改成本价
    @POST(ApiPart.ymeditCost)
    @FormUrlEncoded
    Observable<MgMode> UpdataYmprice(@FieldMap Map<String, String> map);


    //已磨仓库发货
    @POST(ApiPart.ymreceipt)
    @FormUrlEncoded
    Observable<MgMode> ymReceipt(@FieldMap Map<String, String> map);


    //已磨仓库撤回
    @POST(ApiPart.ymreturnProduct)
    @FormUrlEncoded
    Observable<MgMode> ymReturnProduct(@FieldMap Map<String, String> map);


    //已磨仓库新增产品提交
    @POST(ApiPart.ymadd)
    @FormUrlEncoded
    Observable<MgMode> Ymadd(@FieldMap Map<String, String> map);

    //仓库统计
    @POST(ApiPart.statistics)
    @FormUrlEncoded
    Observable<StatisticsMode> Statistics(@FieldMap Map<String, String> map);


    //获取材质列表
    @POST(ApiPart.getwood)
    @FormUrlEncoded
    Observable<CzMode> getWood(@FieldMap Map<String, String> map);


    //添加产品菜单
    @POST(ApiPart.addwood)
    @FormUrlEncoded
    Observable<MgMode> AddWood(@FieldMap Map<String, String> map);


    //产品列表
    @POST(ApiPart.productList)
    @FormUrlEncoded
    Observable<ProductMode> ProductList(@FieldMap Map<String, String> map);

    //删除产品
    @POST(ApiPart.del_product)
    @FormUrlEncoded
    Observable<MgMode> Delectproduct(@FieldMap Map<String, String> map);


    //查询材质
    @POST(ApiPart.getProductWood)
    @FormUrlEncoded
    Observable<SelectCpCzMode> Selectcz(@FieldMap Map<String, String> map);


    //修改产品操作
    @POST(ApiPart.updatawood)
    @FormUrlEncoded
    Observable<MgMode> Updatawood(@FieldMap Map<String, String> map);

    //修改产品操作
    @POST(ApiPart.addcpwood)
    @FormUrlEncoded
    Observable<MgMode> Addood(@FieldMap Map<String, String> map);

    //产品单位
    @POST(ApiPart.productUnit)
    @FormUrlEncoded
    Observable<CpdwMode> productUnit(@FieldMap Map<String, String> map);

    //----------------------销售

    //产品单位
    @POST(ApiPart.kctj)
    @FormUrlEncoded
    Observable<WarehouseStatisticsMode> Kctj(@FieldMap Map<String, String> map);

    //销售管理
    @POST(ApiPart.sale)
    @FormUrlEncoded
    Observable<MarkettjMode> sale(@FieldMap Map<String, String> map);

    //销售管理
    @POST(ApiPart.zsSaleList)
    @FormUrlEncoded
    Observable<MarkettjDetailsMode> zsSaleList(@FieldMap Map<String, String> map);


    //销售管理
    @POST(ApiPart.feedback)
    @FormUrlEncoded
    Observable<CzMode> Feedback(@FieldMap Map<String, String> map);

    //发货（待发货）
    @POST(ApiPart.fGoods)
    @FormUrlEncoded
    Observable<StayDeliverMode> fGoods(@FieldMap Map<String, String> map);

    //发货（待发货详情页面）
    @POST(ApiPart.fxGoods)
    @FormUrlEncoded
    Observable<SendGoodsDetailsMode> SendDetailsM(@FieldMap Map<String, String> map);

    //发货（已发货）
    @POST(ApiPart.sGoods)
    @FormUrlEncoded
    Observable<StayDeliverMode> sGoods(@FieldMap Map<String, String> map);

    //发货（已发货详情页面）
    @POST(ApiPart.shGoods)
    @FormUrlEncoded
    Observable<SendGoodsDetailsMode> shGoods(@FieldMap Map<String, String> map);

    //收货（待收货）
    @POST(ApiPart.goodsDsh)
    @FormUrlEncoded
    Observable<StayDeliverMode> goodsDsh(@FieldMap Map<String, String> map);

    //收货（待收货详情）
    @POST(ApiPart.goodsxq)
    @FormUrlEncoded
    Observable<SendGoodsDetailsMode> goodsxq(@FieldMap Map<String, String> map);

    //收货（已收货）
    @POST(ApiPart.goodsysh)
    @FormUrlEncoded
    Observable<StayDeliverMode> goodsysh(@FieldMap Map<String, String> map);


    // 收货（已收货详情）
    @POST(ApiPart.goodsyshxq)
    @FormUrlEncoded
    Observable<SendGoodsDetailsMode> goodsyshxq(@FieldMap Map<String, String> map);

    // 添加订单添加产品
    @POST(ApiPart.productAdd)
    @FormUrlEncoded
    Observable<XsproductListMode> productAdd(@FieldMap Map<String, String> map);

    // 添加订单产品单件
    @POST(ApiPart.singleton)
    @FormUrlEncoded
    Observable<SelectXsDjMode> singleton(@FieldMap Map<String, String> map);

    // 添加订单色号展示
    @POST(ApiPart.colorlist)
    @FormUrlEncoded
    Observable<SelectShMode> colorlist(@FieldMap Map<String, String> map);


    // 添加订单客户搜索
    @POST(ApiPart.user)
    @FormUrlEncoded
    Observable<SelectNameMode> user(@FieldMap Map<String, String> map);

    // 添加订单时销售下拉
    @POST(ApiPart.orderSale)
    @FormUrlEncoded
    Observable<XsListMode> orderSale(@FieldMap Map<String, String> map);

    // 添加订单产品门店
    @POST(ApiPart.store)
    @FormUrlEncoded
    Observable<MdMode> store(@FieldMap Map<String, String> map);

    // 销售添加接口
    @POST(ApiPart.sale_add)
    Observable<AddMode> Sale_add(@Body OrderAdd orderAdd);

    // 折扣列表
    @POST(ApiPart.discount)
    @FormUrlEncoded
    Observable<DiscountMode> Discount(@FieldMap Map<String, String> map);

    // 折扣列表否决按钮
    @POST(ApiPart.discountNo)
    @FormUrlEncoded
    Observable<AddMode> discountNo(@FieldMap Map<String, String> map);

    // 折扣列表同意按钮
    @POST(ApiPart.discountAgree)
    @FormUrlEncoded
    Observable<AddMode> discountAgree(@FieldMap Map<String, String> map);

    // 仓管列表（待出货）
    @POST(ApiPart.warehouseListA)
    @FormUrlEncoded
    Observable<CgStayGoMode> warehouseListA(@FieldMap Map<String, String> map);

    // 仓管列表（已出货）
    @POST(ApiPart.warehouseListB)
    @FormUrlEncoded
    Observable<CgStayGoMode> warehouseListB(@FieldMap Map<String, String> map);

    // 仓管列表（已出货）
    @POST(ApiPart.warehouseListC)
    @FormUrlEncoded
    Observable<CgStayGoMode> warehouseListC(@FieldMap Map<String, String> map);

    // 仓管列表（已出货）
    @POST(ApiPart.warehouseListD)
    @FormUrlEncoded
    Observable<CgStayGoMode> warehouseListD(@FieldMap Map<String, String> map);

    // 仓库收货页面列表
    @POST(ApiPart.warehouse_c)
    @FormUrlEncoded
    Observable<MgMode> warehouse_c(@FieldMap Map<String, String> map);

    // 获取产品仓库列表信息2
    @POST(ApiPart.get_product_list)
    @FormUrlEncoded
    Observable<CkListMode> productList(@FieldMap Map<String, String> map);

    // 出货
    @POST(ApiPart.out_product)
    @FormUrlEncoded
    Observable<CzMode> outProduct(@FieldMap Map<String, String> map);

    // 仓管个人中心
    @POST(ApiPart.cGpersonal)
    @FormUrlEncoded
    Observable<MeMode> cGpersonal(@FieldMap Map<String, String> map);


    // 退货页面列表1
    @POST(ApiPart.returnGoodsPage)
    @FormUrlEncoded
    Observable<MgMode> returnGoodsPage(@FieldMap Map<String, String> map);

    // 退货页面列表1
    @POST(ApiPart.warehous_list)
    @FormUrlEncoded
    Observable<CgShMode> warehous_list(@FieldMap Map<String, String> map);

    //退货
    @POST(ApiPart.returnGoods)
    Observable<CzMode> returnGoods(@Body CkResponse data);

    //销售订单（信息）
    @POST(ApiPart.orderMessage)
    @FormUrlEncoded
    Observable<OrderMsgMode> orderMessage(@FieldMap Map<String, String> map);

    //订单已出货状态 同意
    @POST(ApiPart.agree)
    @FormUrlEncoded
    Observable<MgMode> agree(@FieldMap Map<String, String> map);

    //客户删除
    @POST(ApiPart.company_delete)
    @FormUrlEncoded
    Observable<MgMode> Companydelete(@FieldMap Map<String, String> map);

    //库存统计
    @POST(ApiPart.record)
    @FormUrlEncoded
    Observable<FhjlMode> record(@FieldMap Map<String, String> map);

    //库存统计
    @POST(ApiPart.saleColor)
    @FormUrlEncoded
    Observable<ColourManageMode> saleColor(@FieldMap Map<String, String> map);

    //修改色号
    @POST(ApiPart.colorEdit)
    @FormUrlEncoded
    Observable<XzCzMode> colorEdit(@FieldMap Map<String, String> map);

    //添加色号
    @POST(ApiPart.addColor)
    @FormUrlEncoded
    Observable<XzCzMode> addColor(@FieldMap Map<String, String> map);

    //删除色号
    @POST(ApiPart.deleteColor)
    @FormUrlEncoded
    Observable<XzCzMode> deleteColor(@FieldMap Map<String, String> map);

    //材质列表
    @POST(ApiPart.woodSale)
    @FormUrlEncoded
    Observable<TextureManageMode> woodSale(@FieldMap Map<String, String> map);

    //材质列表
    @POST(ApiPart.editWood)
    @FormUrlEncoded
    Observable<XzCzMode> editWood(@FieldMap Map<String, String> map);

    //添加材质
    @POST(ApiPart.addWood)
    @FormUrlEncoded
    Observable<XzCzMode> addWood(@FieldMap Map<String, String> map);

    //删除材质
    @POST(ApiPart.deleteWood)
    @FormUrlEncoded
    Observable<XzCzMode> deleteWood(@FieldMap Map<String, String> map);
}
