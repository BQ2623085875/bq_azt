package com.azhyun.massxj.utils;

import android.database.Observable;

import com.azhyun.massxj.bean.ArticlesCategorys2foResult;
import com.azhyun.massxj.bean.AuditUsersResult;
import com.azhyun.massxj.bean.CategoryResult;
import com.azhyun.massxj.bean.EvaluationsResult;
import com.azhyun.massxj.bean.HomeArticleShowResult;
import com.azhyun.massxj.bean.HomeDataResult;
import com.azhyun.massxj.bean.LandInfoResult;
import com.azhyun.massxj.bean.LandListResult;
import com.azhyun.massxj.bean.LandTypeResult;
import com.azhyun.massxj.bean.ManagerInfoResult;
import com.azhyun.massxj.bean.ManagerListResult;
import com.azhyun.massxj.bean.ManagerResult;
import com.azhyun.massxj.bean.MyApplyInfoResult;
import com.azhyun.massxj.bean.MyManageInfoResult;
import com.azhyun.massxj.bean.MyManageListResult;
import com.azhyun.massxj.bean.MyServiceInfoResult;
import com.azhyun.massxj.bean.MyServiceListResult;
import com.azhyun.massxj.bean.MySupplyInfoResult;
import com.azhyun.massxj.bean.MyinfoListResult;
import com.azhyun.massxj.bean.NoDataResult;
import com.azhyun.massxj.bean.PersonInfoResult;
import com.azhyun.massxj.bean.PostRegiste1rResult;
import com.azhyun.massxj.bean.RegionResult;
import com.azhyun.massxj.bean.RegisterResult;
import com.azhyun.massxj.bean.ServiceCategorysResult;
import com.azhyun.massxj.bean.ServiceInfoResult;
import com.azhyun.massxj.bean.ServiceListResult;
import com.azhyun.massxj.bean.SharefoResult;
import com.azhyun.massxj.bean.SupplyInfoResult;
import com.azhyun.massxj.bean.SupplyListResult;
import com.azhyun.massxj.bean.UserResult;
import com.azhyun.massxj.bean.aizhongtian.CaiBean;
import com.azhyun.massxj.bean.aizhongtian.CaiGouQiYeBean;
import com.azhyun.massxj.bean.aizhongtian.CaiGouXQBean;
import com.azhyun.massxj.bean.aizhongtian.CommodityDetailsBean;
import com.azhyun.massxj.bean.aizhongtian.GongQiuBean;
import com.azhyun.massxj.bean.aizhongtian.GongXuFenLeiBean;
import com.azhyun.massxj.bean.aizhongtian.GyXqBean;
import com.azhyun.massxj.bean.aizhongtian.MADeBean;
import com.azhyun.massxj.bean.aizhongtian.MallLiebiaoBean;
import com.azhyun.massxj.bean.aizhongtian.MallShangPinBean;
import com.azhyun.massxj.bean.aizhongtian.NbLieBiaoBean;
import com.azhyun.massxj.bean.aizhongtian.NbOrderXQBean;
import com.azhyun.massxj.bean.aizhongtian.NewFieldBean;
import com.azhyun.massxj.bean.aizhongtian.NewFieldContentBean;
import com.azhyun.massxj.bean.aizhongtian.NongYuZhiBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserBean;
import com.azhyun.massxj.bean.aizhongtian.OrdserDetailsBean;
import com.azhyun.massxj.bean.aizhongtian.QgBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBean;
import com.azhyun.massxj.bean.aizhongtian.ResultBeans;
import com.azhyun.massxj.bean.aizhongtian.SubmitBean;
import com.azhyun.massxj.bean.aizhongtian.UnpayZfBean;
import com.azhyun.massxj.bean.aizhongtian.WeChatZfBean;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoLieBean;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoFenLeiBean;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoXiaDanYeBean;
import com.azhyun.massxj.bean.aizhongtian.baoxian.BaoYuDingBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by tl on 2018/8/24.
 */

public interface HttpService {


    //首页新闻信息
    @FormUrlEncoded
    @POST("api/article/show")
    Call<HomeArticleShowResult> articleShow(
            @Field("categoryId") int categoryId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("region") String region,
            @Field("token") String token
    );

    //获取审批人
    @FormUrlEncoded
    @POST("api/audit/users")
    Call<AuditUsersResult> auditUsers(
            @Field("region") String region,
            @Field("token") String token,
            @Field("type") int type
    );

    //加盟
    @FormUrlEncoded
    @POST("api/apply/manager")
    Call<ManagerResult> manager(
            @Field("address") String address,
            @Field("applyRole") int applyRole,
            @Field("area") double area,
            @Field("companyName") String companyName,
            @Field("introduce") String introduce,
            @Field("mobilePhone") String mobilePhone,
            @Field("name") String name,
            @Field("region") String region,
            @Field("sex") String sex,
            @Field("token") String token
    );

    //加盟新
    @FormUrlEncoded
    @POST("api/apply/new")
    Call<ManagerResult> managerNew(
            @Field("address") String address,
            @Field("applyRole") int applyRole,
            @Field("area") double area,
            @Field("auditUserId") int auditUserId,
            @Field("companyName") String companyName,
            @Field("introduce") String introduce,
            @Field("mobilePhone") String mobilePhone,
            @Field("name") String name,
            @Field("region") String region,
            @Field("sex") String sex,
            @Field("token") String token
    );

    //获取土地类型
    @POST("api/type/land")
    Call<LandTypeResult> typeLand(
    );

    //土地托管(提交)
    @Multipart
    @POST("api/land/save")
    Call<ManagerResult> lanSave(
            @Part("address") RequestBody address,
            @Part("area") double area,
            @PartMap Map<String, RequestBody> file,
            @Part("introduce") RequestBody introduce,
            @Part("name") RequestBody name,
            @Part("phone") RequestBody phone,
            @Part("region") RequestBody region,
            @Part("title") RequestBody title,
            @Part("token") RequestBody token
    );

    //土地托管新
    @FormUrlEncoded
    @POST("api/land/new")
    Call<ManagerResult> landNew(
            @Field("address") String address,
            @Field("area") double area,
            @Field("auditUserId") int auditUserId,
            @Field("introduce") String introduce,
            @Field("landType") int landType,
            @Field("landWay") int landWay,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("region") String region,
            @Field("remark") String remark,
            @Field("title") String title,
            @Field("token") String token
    );

    //发布供需
    @Multipart
    @POST("api/supply/save")
    Call<ManagerResult> supplySave(
            @Part("categoryId") int categoryId,
            @Part("contacts") RequestBody contacts,
            @Part("description") RequestBody description,
            @PartMap Map<String, RequestBody> file,
            @Part("norms") RequestBody norms,
            @Part("num") RequestBody num,
            @Part("phone") RequestBody phone,
            @Part("price") RequestBody price,
            @Part("region") RequestBody region,
            @Part("title") RequestBody title,
            @Part("token") RequestBody token,
            @Part("type") int type
    );

    //供需分类
    @FormUrlEncoded
    @POST("api/supply/category")
    Call<CategoryResult> getCategory(
            @Field("parentId") int parentId,
            @Field("token") String token);

    //供需列表
    @FormUrlEncoded
    @POST("api/supply/list")
    Call<SupplyListResult> getSupplyList(
            @Field("categoryId") int categoryId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("region") String region,
            @Field("token") String token,
            @Field("type") int type
    );


    //供需详情
    @FormUrlEncoded
    @POST("api/supply/info")
    Call<SupplyInfoResult> getSupplyInfo(
            @Field("id") int id,
            @Field("token") String token
    );

    //服务分类
    @FormUrlEncoded
    @POST("api/service/categorys")
    Call<ServiceCategorysResult> getCategorys(
            @Field("parentId") int parentId,
            @Field("token") String token
    );

    //农事服务列表
    @FormUrlEncoded
    @POST("api/service/list")
    Call<ServiceListResult> getServiceList(
            @Field("categoryId") int categoryId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token);

    //农事服务详情
    @FormUrlEncoded
    @POST("api/service/info")
    Call<ServiceInfoResult> getServiceInfo(
            @Field("id") int id,
            @Field("token") String token
    );

    //提交预约单
    @FormUrlEncoded
    @POST("api/myserv/savenew")
    Call<ManagerResult> myServiceSave(
            @Field("address") String address,
            @Field("consignee") String consignee,
            @Field("mob") String mob,
            @Field("num") String num,
            @Field("region") String region,
            @Field("remark") String remark,
            @Field("serviceId") int serviceId,
            @Field("timestamp") String timestamp,
            @Field("token") String token);

    //个人信息
    @FormUrlEncoded
    @POST("api/person/info")
    Call<PersonInfoResult> getPersonInfo(
            @Field("token") String token
    );

    //更换头像
    @Multipart
    @POST("api/person/img")
    Call<ManagerResult> personImg(
            @PartMap Map<String, RequestBody> file,
            @Part("token") RequestBody token
    );


    //修改手机号
    @FormUrlEncoded
    @POST("api/phone/update")
    Call<ManagerResult> phoneUpdate(
            @Field("mob") String mob,
            @Field("vcode") String vcode
    );

    //预约单列表
    @FormUrlEncoded
    @POST("api/myserv/listnew")
    Call<MyServiceListResult> myServList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("token") String token
    );

    //获取评价
    @FormUrlEncoded
    @POST("api/service/evaluations")
    Call<EvaluationsResult> getEvaluations(
            @Field("id") int id,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token,
            @Field("type") int type
    );


    //预约单详情
    @FormUrlEncoded
    @POST("api/myserv/infonew")
    Call<MyServiceInfoResult> getMyservInfo(
            @Field("id") int id,
            @Field("token") String token
    );

    //操作预约单
    @FormUrlEncoded
    @POST("api/myserv/operatenew")
    Call<MyServiceInfoResult> MyservOperate(
            @Field("id") int id,
            @Field("type") int type
    );

    //评价
    @Multipart
    @POST("api/myserv/evaluation")
    Call<ManagerResult> evaluation(
            @Part("content") RequestBody content,
            @Part("deviceLevel") int deviceLevel,
            @PartMap Map<String, RequestBody> file,
            @Part("level") int level,
            @Part("orderId") int orderId,
            @Part("serviceLevel") int serviceLevel,
            @Part("token") RequestBody token);

    //审核土地托管列表
    @FormUrlEncoded
    @POST("api/land/list")
    Call<LandListResult> getLandList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("token") String token);


    //审核土地托管列表
    @FormUrlEncoded
    @POST("api/land/listnew")
    Call<LandListResult> getListNewList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("token") String token);


    //获取审核_土地托管详情
    @FormUrlEncoded
    @POST("api/land/infonew")
    Call<LandInfoResult> getLandInfo(
            @Field("id") int id,
            @Field("token") String token);

    //土地审核
    @FormUrlEncoded
    @POST("api/land/operate")
    Call<LandInfoResult> landOperate(
            @Field("id") int id,
            @Field("note") String note,
            @Field("type") int type,
            @Field("token") String token
    );

    //上传证件
    @Multipart
    @POST("api/landatta/save")
    Call<ManagerResult> landAttaSave(
            @PartMap Map<String, RequestBody> file,
            @Part("id") int id,
            @Part("type") int type,
            @Part("token") RequestBody token);

    //审核 经纪人 列表
    @FormUrlEncoded
    @POST("api/manager/list")
    Call<ManagerListResult> getManagerList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("status") int status,
            @Field("token") String token);

    //获取审核_经纪人详情
    @FormUrlEncoded
    @POST("api/manager/info")
    Call<ManagerInfoResult> getManagerInfo(
            @Field("userId") int userId,
            @Field("token") String token
    );

    //经纪人审核
    @FormUrlEncoded
    @POST("api/manager/operate")
    Call<LandInfoResult> managerOperate(
            @Field("userId") int userId,
            @Field("note") String note,
            @Field("type") int type,
            @Field("token") String token
    );

    //我的供需列表
    @FormUrlEncoded
    @POST("api/mysupply/list")
    Call<SupplyListResult> getMySupplyList(
            @Field("categoryId") int categoryId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token,
            @Field("type") int type
    );

    //我的供需详情
    @FormUrlEncoded
    @POST("api/mysupply/info")
    Call<MySupplyInfoResult> getMySupplyInfo(
            @Field("id") int id,
            @Field("token") String token
    );

    //下架
    @FormUrlEncoded
    @POST("api/mysupply/operate")
    Call<ManagerResult> MySupplyOperate(
            @Field("id") int id,
            @Field("token") String token,
            @Field("type") int type
    );

    //我的土地托管列表
    @FormUrlEncoded
    @POST("api/myland/list")
    Call<LandListResult> getMyLandList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token,
            @Field("status") int status
    );

    //经纪人申请详情
    @FormUrlEncoded
    @POST("api/myapply/info")
    Call<MyApplyInfoResult> MyApplyInfo(
            @Field("token") String token
    );


    //我的管理列表
    @FormUrlEncoded
    @POST("api/mymanage/list")
    Call<MyManageListResult> getMyManageList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token,
            @Field("region") String region
    );

    //我管理详情
    @FormUrlEncoded
    @POST("api/mymanage/info")
    Call<MyManageInfoResult> MyManageInfo(
            @Field("token") String token,
            @Field("userId") int userId
    );


    //系统消息
    @FormUrlEncoded
    @POST("api/myinfo/list")
    Call<MyinfoListResult> getMyinfoList(
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("token") String token
    );

    //读取消息
    @FormUrlEncoded
    @POST("api/myinfo/read")
    Call<MyManageInfoResult> MyManageRead(
            @Field("token") String token,
            @Field("id") int id
    );

    //清空
    @FormUrlEncoded
    @POST("api/myinfo/delall")
    Call<ManagerResult> MyInfoDelall(
            @Field("token") String token
    );

    //我的界面新闻政策
    @FormUrlEncoded
    @POST("api/article2/categorys")
    Call<ArticlesCategorys2foResult> MyArticleCategorys2(
            @Field("token") String token,
            @Field("parentId") int parentId
    );

    @FormUrlEncoded
    @POST("api/myapply/update")
    Call<ManagerResult> MyapplyUpdate(
            @Field("address") String address,
            @Field("applyRole") int applyRole,
            @Field("area") double area,
            @Field("auditUserId") int auditUserId,
            @Field("introduce") String introduce,
            @Field("mobilePhone") String mobilePhone,
            @Field("name") String name,
            @Field("region") String region,
            @Field("sex") String sex,
            @Field("token") String token
    );

    //登录分享
    @FormUrlEncoded
    @POST("api/article2/share")
    Call<SharefoResult> loginShare(
            @Field("articleId") int articleId,
            @Field("token") String token,
            @Field("types") int types
    );


    /*
     * 爱种田=====================================================================================================================
     * */

    //版本更新
    @FormUrlEncoded
    @POST("api/version")
    Call<PostRegiste1rResult> PostRegiste1r(
            @Field("terminalSn") String terminalSn,
            @Field("type") int type);

    //注册
    @FormUrlEncoded
    @POST("api/register")
    Call<RegisterResult> PostRegister(
            @Field("address") String address,
            @Field("mob") String mob,
            @Field("name") String name,
            @Field("referrerid") String referrerid,
            @Field("region") String region,
            @Field("vcode") String vcode);

    //获取验证码
    @FormUrlEncoded
    @POST("api/vcode")
    Call<NoDataResult> getCode(
            @Field("mob") String mob,
            @Field("type") int type);


    //获取区域地址
    @FormUrlEncoded
    @POST("api/region")
    Call<RegionResult> getRegion(
            @Field("parentId") String parentId);

    //登录
    @FormUrlEncoded
    @POST("api/login")
    Call<UserResult> Login(
            @Field("mob") String mob,
            @Field("vcode") String vcode
    );

    //退出登录
    @FormUrlEncoded
    @POST("api/loginout")
    Call<UserResult> Loginout(
            @Field("token") String token
    );

    //获取首页数据
    @FormUrlEncoded
    @POST("api/index/show")
    Call<HomeDataResult> getHome(
            @Field("token") String token
    );


    //农资商城列表
    @FormUrlEncoded
    @POST("api/item/category/list")
    Call<MallLiebiaoBean> malllb(
            @Field("parentId") int articleId
    );

    //农资商城商品列表
    @FormUrlEncoded
    @POST("api/item/list")
    Call<MallShangPinBean> mallshang(
            @Field("categoryId") int categoryId,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("region") String region
    );


    //农资商城商品详情
    @FormUrlEncoded
    @POST("api/item/show")
    Call<CommodityDetailsBean> commodity(
            @Field("id") int id
    );


    //下单页面
    @Multipart
    @POST("api/order/amount")
    Call<SubmitBean> submit(
            @PartMap HashMap<String, Object> map
    );

    //提交订单
    @Multipart
    @POST("api/order/save")
    Call<ResultBean> tijiaoorder(
            @PartMap HashMap<String, Object> map
    );


    //订单详情
    @FormUrlEncoded
    @POST("api/order/info")
    Call<OrdserDetailsBean> orderxiang(
            @Field("orderId") int orderId
    );

    //取消订单
    @FormUrlEncoded
    @POST("api/order/del")
    Call<ResultBeans> quxiaoorder(
            @Field("orderId") int orderId
    );

    //确认收货
    @FormUrlEncoded
    @POST("api/signById")
    Call<ResultBeans> getYesData(
            @Field("id") int id
    );

    //订单列表
    @FormUrlEncoded
    @POST("api/order/list")
    Call<OrdserBean> orderliebiao(
            @Field("clientId") String clientId,//用户id
//            @Field("sdate") int sdate,//筛选
            @Field("status") int status//分类
    );

    //新品试验田
    @FormUrlEncoded
    @POST("api/article/show")
    Call<NewFieldBean> xinpintian(
            @Field("categoryId") int categoryId,
            @Field("region") String region
    );

    //新品试验田
    @FormUrlEncoded
    @POST("api/article/info")
    Call<NewFieldContentBean> newfieldconent(
            @Field("id") int id
    );

    //修改个人信息
    @FormUrlEncoded
    @POST("api/person/update")
    Call<ManagerResult> personUpdate(
            @Field("mobilePhone") String mobilePhone,
            @Field("name") String name,
            @Field("nickname") String nickname,
            @Field("token") String token
    );

    //我的农誉值
    @FormUrlEncoded
    @POST("api/currency/index")
    Call<NongYuZhiBean> getNongYu(
            @Field("token") String token
    );

    //我的积分
    @FormUrlEncoded
    @POST("api/credit/index")
    Call<NongYuZhiBean> getJiFen(
            @Field("token") String token
    );


    //兑换积分
    @FormUrlEncoded
    @POST("api/credit/buy")
    Call<ResultBeans> getExchange(
            @Field("num") String num
    );

    /**
     * 微信支付-统一下单
     */
    @FormUrlEncoded
    @POST("api/pay/wxpay")
    Call<WeChatZfBean> getWeChat(
            @Field("payNumber") String paynumber
    );

    /**
     * 银联支付-获取流水号
     */
    @FormUrlEncoded
    @POST("api/pay/unpay")
    Call<UnpayZfBean> getUnionpay(
            @Field("payNumber") String paynumber
    );

    /*
     * 本地生活   我的供应   求购
     * */
    @FormUrlEncoded
    @POST("api/supply/list")
    Call<GongQiuBean> getBenDiGQ(
            @Field("categoryId") Object categoryId,
            @Field("keyword") String keyword,
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("region") String region,
            @Field("type") Object type,
            @Field("userId") Object userId
    );


    /*
     * 供应详情
     * */
    @FormUrlEncoded
    @POST("api/supply/supplyInfo")
    Call<GyXqBean> getGyXq(
            @Field("id") Object id
    );

    /*
     * 求购详情
     * */
    @FormUrlEncoded
    @POST("api/supply/demandInfo")
    Call<QgBean> getQgXq(
            @Field("id") Object id
    );

    /*
     * 供需分类
     * */
    @POST("api/supply/category")
    Call<GongXuFenLeiBean> getGongXuFenLei();

    //发布供需
    @Multipart
    @POST("api/supply/save")
    Call<ResultBeans> getFaBuGongXu(
            @PartMap Map<String, RequestBody> banners,
            @Part("categoryId") int categoryId,
            @Part("description") RequestBody description,
            @PartMap Map<String, RequestBody> infoImg,
            @Part("norms") RequestBody norms,
            @Part("num") RequestBody num,
            @Part("price") RequestBody price,
            @Part("region") RequestBody region,
            @Part("title") RequestBody title,
            @Part("type") int type
    );

    //发布求购
    @Multipart
    @POST("api/supply/save")
    Call<ResultBeans> getFaBuQiuGou(
            @PartMap Map<String, RequestBody> banners,
            @Part("categoryId") int categoryId,
            @Part("description") RequestBody description,
            @Part("num") RequestBody num,
            @Part("region") RequestBody region,
            @Part("title") RequestBody title,
            @Part("type") int type
    );

    //下线
    @FormUrlEncoded
    @POST("api/supply/downLine")
    Call<ResultBeans> getXixian(
            @Field("id") int id
    );

    /*
     * ==============================采收服务接口
     * */
    //采购列表
    @FormUrlEncoded
    @POST("api/harvest/item/list")
    Call<CaiBean> getCaiGou(
            @Field("id") int page,
            @Field("id") int pagerow,
            @Field("id") String region
    );

    //采购商品详情
    @FormUrlEncoded
    @POST("api/harvest/item/show")
    Call<CaiGouXQBean> getCaiGouXQ(
            @Field("id") int id,
            @Field("userId") String userId
    );

    //采购订单列表页面
    @FormUrlEncoded
    @POST("api/harvest/order/list")
    Call<OrdserBean> caigouorderliebiao(
            @Field("clientId") String clientId,//用户id
//            @Field("sdate") int sdate,//筛选
            @Field("status") int status//分类

    );

    //采购提交订单
    @Multipart
    @POST("api/harvest/order/save")
    Call<ResultBean> caigoutijiaoorder(
            @PartMap HashMap<String, Object> map
    );

    //====================================================农保中心
    //农保中心列表
    @FormUrlEncoded
    @POST("api/getInsuranceList")
    Call<BaoLieBean> getBaoLie(
            @Field("insuranceId") int clientId,//分类id
            @Field("page") int page,
            @Field("pagerow") int pagerow,
            @Field("region") String region

    );

    //农保中心分类列表
    @GET("api/insuranceList")
    Call<BaoFenLeiBean> getBaoFenLei();

    //农保预定
    @FormUrlEncoded
    @POST("api/saveInsurance")
    Call<BaoYuDingBean> getBaoYuDing(
            @Field("address") String address,
            @Field("clientId") String clientId,
            @Field("idcard") String idcard,
            @Field("insuranceItemId") int insuranceItemId,
            @Field("mu") String mu,
            @Field("name") String name,
            @Field("region") String region
    );

    //农保下单页面
    @Multipart
    @POST("api/insurance/order/amount")
    Call<BaoXiaDanYeBean> getXiaDan(
            @PartMap HashMap<String, Object> map
    );

    //农保提交订单
    @Multipart
    @POST("api/insurance/order/save")
    Call<MADeBean> getBaotijiaoorder(
            @PartMap HashMap<String, Object> map
    );


    //采购企业名录
    @FormUrlEncoded
    @POST("api/harvest/companyLists")
    Call<CaiGouQiYeBean> getCaiCompay(
            @Field("page") int page,
            @Field("pagerow") int pagerow
    );

    //农保订单列表
    @FormUrlEncoded
    @POST("api/insurance/order/list")
    Call<NbLieBiaoBean> nbOrderliebiao(
            @Field("clientId") String clientId,//用户id
            @Field("status") int status//分类
    );

    //农保订单详情
    @FormUrlEncoded
    @POST("api/insurance/order/info")
    Call<NbOrderXQBean> nbOrderXQ(
            @Field("orderId") int orderId//订单id
    );


}
