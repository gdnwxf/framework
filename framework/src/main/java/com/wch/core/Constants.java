package com.wch.core;

import java.math.BigDecimal;


/**
 * 项目名称：retail-core
 * 类名称：Constants
 * 类描述： 通用常量定义
 * 创建时间：2014年9月2日 上午9:07:08
 * 
 * @author guanhuangbai
 * @version 1.0
 */
public final class Constants {

    public static final String SUCCESS = "success"; // 返回成功消息标志

    public static final String FAIL = "fail"; // 返回失败消息标志

    public static final String DOING = "doing"; // 返回正在处理消息标志

    // 临时文件地址
    public static final String TMP_DIR = "tmp.dir";
    
    public static final int NUMBER_PER_PAGE = 20; // 手机每页显示数量(默认)
    
    public static final int NUMBER_PER_PAGE_TABLET = 24; //收银台每页显示数量

    public static final String FIRSTADD = "firstadd"; // 第一次添加操作

    public static final String ADD = "add"; // 添加操作

    public static final String EDIT = "edit"; // 编辑操作
    
    public static final String DEL = "del"; // 删除操作

    public static final String REAPPLY = "reapply"; // 重新申请

    public static final String SUBMIT = "submit"; // 提交操作
    
    public static final String CONFIG = "config"; // 确认操作
    
    public static final String REFUSE = "refuse"; // 拒绝操作

    public static final String CANCEL = "cancel"; // 取消操作

    public static final String CONFIRM = "confirm"; // 确认操作

    public static final String CONFIRM_TAKE = "confirm_take"; // 确认上门自提

    public static final String RECEIPT = "receipt"; // 确认收货

    public static final String SAVE = "save"; // 保存操作
    
    public static final String ACTIVATION = "activation"; // 激活操作

    public static final String LOSS = "loss"; // 挂失操作

    public static final short VALID = 1; // 有效的数据

    public static final Byte PROPERTY_REPAIR_STATUS_REPORT = 1; // 物业保修状态：申报
    
    public static final Byte PROPERTY_REPAIR_STATUS_CONFIRM = 2; // 物业保修状态：确认申报
    
    public static final String DEFAULT_VALUE = "0";//系统处理数据，统一设置用户ID

    public static final short INVALID = 0; // 无效的数据

    public static final Long PREMIERE_VER = 1l; // 第一个版本
    
    public static final String EMPTY_STRING = ""; // 空字符串
    
    public static final String COMMA_STRING = ","; // 空字符串
    
    public static final String NULL = "null"; //值为null的字符串
    
    public static final BigDecimal MAX_VALUE = new BigDecimal("99999999999999.98"); //系统规定的最大数值

    public static final BigDecimal THOUSAND_RATE = new BigDecimal("0.001"); //千分比

    public static final BigDecimal HUNDRED_RATE = new BigDecimal("0.01"); //百分比
    
    // 常量整数0
    public static final int ZERO = 0;
    // 常量字符0
    public static final String ZERO_STR = "0";
    // cookie名
    public static final String COOKIE_TOKEN = "MYSHOP.2DFIRE.COM.TOKEN";

    // 系统参数，开关  1 开启
    public static final String NEW_SWITCH_ON = "1";
    // 系统参数，开关  2 关闭
    public static final String NEW_SWITCH_OFF = "2";

    
    // session的key名
    public static final String SESSION_KEY_PLATFORM = "PLATFORM";
    // cookie保存时间，缓存保存时间，单位：秒
    public static final int COOKIE_INDATE = 43200;
    public static final String ADMIN = "ADMIN";
    /* 字典类别 */
    public static final String DIC_CARD_STATUS = "DIC_CARD_STATUS"; // 会员卡状态
    //public static final String DIC_PAY_MODEL = "DIC_PAY_MODEL"; // 支付方式
    public static final String DIC_DISTRIBUTION_STATUS = "DIC_CHAIN_RECEIPT_STATUS"; // 配送单状态
    public static final String DIC_SHOP_TYPE = "DIC_SHOP_TYPE"; // 店铺类型
    public static final String DIC_IDENTITY_TYPE = "DIC_IDENTITY_TYPE"; // 证件类型
    public static final String DIC_SEX_TYPE = "DIC_SEX_TYPE"; // 性别
    public static final String DIC_BILLNUM_MODEL = "DIC_BILLNUM_MODEL"; // 单号区分
    public static final String DIC_BILL_TYPE = "DIC_BILL_TYPE"; // 物流记录单类型
    public static final String DIC_OPERAT_TYPE_MODEL = "DIC_OPERAT_TYPE_MODEL"; // 会员卡操作类型
    public static final String DIC_MONEY_TYPE_MODEL = "DIC_MONEYFLOW_MODEL"; // 会员卡金额变动类型
    public static final String DIC_SALE_STATUS = "DIC_SALE_STATUS"; // 营销活动状态
    public static final String DIC_OPERATE_TYPE_MODEL = "DIC_OPERATE_TYPE_MODEL"; // 会员卡操作类型
    public static final String DIC_STOCK_ADJUST_TYPE = "DIC_STOCK_ADJUST_TYPE"; // 库存调整状态
    public static final String DIC_BILL_STATUS = "DIC_BILL_STATUS"; // 物流管理订单状态
    public static final String DIC_DEGREE_TYPE_MODEL = "DIC_DEGREE_TYPE_MODEL"; // 会员卡积分变动类型
    public static final String DIC_ACTION_DATA_TYPE = "DIC_ACTION_DATA_TYPE"; // 数据权限类型
    public static final String DIC_ACTION_TYPE = "DIC_ACTION_TYPE"; // 权限类型
    public static final String DIC_ORDERGOODS_STATUS = "DIC_CALL_ORDER_STATUS"; // 订货状态
    public static final String DIC_RETURN_STATUS = "DIC_RETURN_STATUS"; // 退货状态
    public static final String DIC_RETURN_RESON = "DIC_RETURN_RESON"; // 退货原因
//    public static final String DIC_ALLOCATE_STATUS = "DIC_ALLOCATE_STATUS"; // 调拨状态
    public static final String DIC_SUPPLY_TYPE="DIC_SUPPLY_TYPE";	//供应商类别
    public static final String DIC_REFUSE_RESON = "DIC_REFUSE_RESON"; // 拒绝配送原因
    public static final String DIC_SALES_DETAIL_INQUIRY = "DIC_SALES_DETAIL_INQUIRY";//销售明细查询区分


    public static final String DIC_RETURN_CATEGORY = "DIC_RETURN_CATEGORY"; // 退货类别

    /*客单备注*/
    public static final String DIC_ORDER_MEMO = "DIC_ORDER_MEMO"; //客单备注
    /**
     * 新增物流单状态 系统，字典系统设置
     */
    /*物流库存单状态*/
    public static final String DIC_CALL_ORDER_STATUS = "DIC_CALL_ORDER_STATUS";//采购叫货单
    public static final String DIC_CHAIN_RECEIPT_STATUS = "DIC_CHAIN_RECEIPT_STATUS";//收货单状态
    public static final String DIC_CHAIN_RETURN_STATUS = "DIC_CHAIN_RETURN_STATUS";//退货单状态
    public static final String DIC_CUSTOMER_CALL_ORDER_STATUS = "DIC_CUSTOMER_CALL_ORDER_STATUS";//客户采购单
    public static final String DIC_CUSTOMER_RETURN_STATUS = "DIC_CUSTOMER_RETURN_STATUS";//客户退货单
    public static final String DIC_ALLOCATE_STATUS = "DIC_ALLOCATE_STATUS";//调拨单状态
    public static final String DIC_PACK_BOX_STATUS = "DIC_PACK_BOX_STATUS";//装箱单状态


    /* 系统参数说明 */
    public static final String CONFIG_ENTITY_MODEL = "CONFIG_ENTITY_MODEL"; // 经营模式
    public static final String CONFIG_NEGATIVESTORE_STATUS = "CONFIG_NEGATIVESTORE_STATUS"; // 支持负库存
    public static final String CONFIG_REMNANT_MODEL = "CONFIG_REMNANT_MODEL"; // 零头处理
    public static final String CONFIG_GOODS_REMNANT_MODEL = "CONFIG_GOODS_REMNANT_MODEL"; //每项金额处理
    public static final String CONFIG_RECEIPTWIDTH_MODEL = "CONFIG_RECEIPTWIDTH_MODEL"; // 小票规格
    public static final String CONFIG_STOCKCHECK_STATUS = "CONFIG_STOCKCHECK_STATUS"; // 盘点状态
    public static final String CONFIG_IMPORT_GOODS_STATUS = "CONFIG_IMPORT_GOODS_STATUS"; // 商品导入状态
    public static final String CONFIG_MEMBER_PRICE_MODEL = "CONFIG_MEMBER_PRICE_MODEL"; // 会员价格设置模式
    public static final String CONFIG_WHOLE_DISCOUNT = "CONFIG_WHOLE_DISCOUNT"; // 整单折扣设置
    public static final String CONFIG_PRODUCT_DISCOUNT = "CONFIG_PRODUCT_DISCOUNT"; //单品折扣
    public static final String CONFIG_PAY_MODEL = "CONFIG_PAY_MODEL"; // 支付类型
    public static final String CONFIG_PAY_MENT = "CONFIG_PAY_MENT"; // 支付类型
    public static final String CONFIG_SCALE_TYPE = "CONFIG_SCALE_TYPE"; //电子秤类型
    public static final String CONFIG_SCALE_BARCODE_MARK = "CONFIG_SCALE_BARCODE_MARK"; //电子秤条码标识
    public static final String CONFIG_SCALE_CODE_TYPE = "CONFIG_SCALE_CODE_TYPE"; //电子秤条码输出格式
    public static final String CONFIG_SCALE_WEIGHT_PRECISION = "CONFIG_SCALE_WEIGHT_PRECISION"; //电子秤重量精度
    public static final String CONFIG_SEND_OPEN_CARD_SMS = "CONFIG_SEND_OPEN_CARD_SMS"; //发送开卡短信
    public static final String CONFIG_SEND_CHARGE_SMS = "CONFIG_SEND_CHARGE_SMS"; //发送充值短信
    public static final String CONFIG_SEND_CANCEL_CARD_SMS = "CONFIG_SEND_CANCEL_CARD_SMS"; //发送退卡短信
    public static final String CONFIG_SEND_DEAL_SMS = "CONFIG_SEND_DEAL_SMS"; //发送交易短信 
    public static final String CONFIG_SHOP_SYNC_STATUS = "CONFIG_SHOP_SYNC_STATUS"; //门店之间同步状态
    public static final String CONFIG_STOCK_IMPORT_STATUS = "CONFIG_STOCK_IMPORT_STATUS"; //库存导入状态
    public static final String CONFIG_SHOP_CLEAR_STATUS = "CONFIG_SHOP_CLEAR_STATUS"; //门店数据清理状态
    public static final String CONFIG_SHOP_INIT_STATUS = "CONFIG_SHOP_INIT_STATUS"; //门店数据初始化状态
    public static final String CONFIG_PRICE_IMPORT_STATUS = "CONFIG_PRICE_IMPORT_STATUS"; //价格导入状态
    public static final String CONFIG_MEMBER_IMPORT_STATUS = "CONFIG_MEMBER_IMPORT_STATUS"; //会员导入状态
    public static final String CONFIG_CATEGORY_IMPORT_STATUS = "CONFIG_CATEGORY_IMPORT_STATUS"; //分类导入状态
    public static final String CONFIG_SKU_IMPORT_STATUS = "CONFIG_SKU_IMPORT_STATUS"; //sku属性导入状态
    public static final String CONFIG_VIRTUAL_STORE_IMPORT_STATUS = "CONFIG_VIRTUAL_STORE_IMPORT_STATUS"; //虚拟库存导入状态
    public static final String CONFIG_SELL_RETURN_TYPE = "CONFIG_SELL_RETURN_TYPE";
    public static final String CONFIG_UNITRECHARGE_IMPORT_STATUS = "CONFIG_UNITRECHARGE_IMPORT_STATUS";//公司充值导入状态
    public static final String CONFIG_PARKINGCARD_IMPORT_STATUS = "CONFIG_PARKINGCARD_IMPORT_STATUS";//停车卡类型导入状态
    public static final String CONFIG_PARKINGCHARGE_IMPORT_STATUS = "CONFIG_PARKINGCHARGE_IMPORT_STATUS";//停车卡充值导入状态
    
    public static final String CONFIG_REPORT_CYCLE = "CONFIG_REPORT_CYCLE"; // 会计期设置
    public static final String CONFIG_SHOP_STOCK_STATUS = "CONFIG_SHOPSTOCK_STATUS"; // 查看同级店铺库存
    public static final String CONFIG_DEFAULT_MEMBER_TYPE = "CONFIG_DEFAULT_MEMBER_TYPE"; //默认会员类型

    public static final String DIC_SELL_RETURN_TYPE = "DIC_SELL_RETURN_TYPE"; //退货类型
    public static final String DIC_SELL_RETURN_WAY = "DIC_SELL_RETURN_WAY";//退货方式
    
    public static final String  CONFIG_MALL_DEFAULT_CARD_TYPE="CONFIG_MALL_DEFAULT_CARD_TYPE";  //默认会员卡类型
    public static final String CONFIG_MALL_EXPIRE_TIP="CONFIG_MALL_EXPIRE_TIP";  //商家授权到期提醒
    public static final String CONFIG_MALL_COMPLAINT_TITLE="CONFIG_MALL_COMPLAINT_TITLE";  //投诉建议主题设置
    
    public static final String CONFIG_OPEN_PACKAGE_STATUS = "CONFIG_OPEN_PACKAGE_STATUS"; // 启用装箱单
    public static final String CONFIG_RETURN_GUIDE_STATUS = "CONFIG_RETURN_GUIDE_STATUS"; // 启用退货指导

    public static final String CONFIG_WEIXIN_FEN = "CONFIG_WEIXIN_FEN";//是否启用微分销




    //物流库存系统设置
    public static final String CONFIG_POINTS_GOODS_STOCK = "CONFIG_POINTS_GOODS_STOCK"; // 积分商品库存
    public static final String CONFIG_SEE_BROTHER_STORE = "CONFIG_SEE_BROTHER_STORE"; //查看同级店铺库存 1:关闭 2:开启（默认）

    /** 客户需求新增系统参数设置  **/
    //1、	增加“收银时导购员必选”开关。
    public static final String CONFIG_SHOPPING_GUIDE_CHOICE = "CONFIG_SHOPPING_GUIDE_CHOICE";
    //2、	增加允许拒绝配送中的收货单和允许对配送中的收货单进行修改的开关。
    public static final String CONFIG_ALLOW_SENDING_REFUSE = "CONFIG_ALLOW_SENDING_REFUSE";
    //3、允许对配送中的收货单进行修改
    public static final String CONFIG_ALLOW_SENDING_UPDATE = "CONFIG_ALLOW_SENDING_UPDATE";

    /*单号生成规则*/
    public static final String NO_CREATE_SALEORDER = "1"; // 销售流水单号	单号区分+门店编码+日期时间（140810122820193）+2位随机数		
    public static final String NO_CREATE_SALERETURN = "2"; // 销售退货单号	单号区分+门店编码+日期时间（14081012282010）
    public static final String NO_CREATE_ORDER = "3"; // 采购单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_PURCHASE = "4"; // 收货单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_RETURN = "5"; // 退货单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_ADJUST = "6"; // 库存调整单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_ALLOCATE = "7"; // 门店调拨单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_GIFT = "8"; // 兑换单	单号区分+门店编码+日期时间（14081012282010）		
    public static final String NO_CREATE_DISTRIBUTION = "9"; // 配送单	单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    public static final String NO_CREATE_ONLINECHARGE_RRW = "10"; //充值单号    RRW+日期（140810）+6位当日连番号  R:零售 R:充值订单Recharge W:微信 S:实体
    public static final String NO_CREATE_SUPPLY_GOODS = "12";   // 供货单 单号区分（12）+日期（151223）+7位当日连番号
    public static final String NO_CREATE_PACK = "11"; // 装箱单 单号区分+日期（140810）+6位当日连番号（以entity为单位采番）
    /* 表名 */
    public static final String TABLE_NAME_GOODS = "GOODS"; // 商品表

    public static final String TABLE_NAME_CUSTOMER = "CUSTOMER"; //客户表
    
    public static final String TABLE_NAME_USERINFO = "USERINFO"; //用户表
    
    public static final String TABLE_NAME_SHOP = "SHOP"; //用户表
    
    public static final String CONFIG_NAME_HESSIAN_SERVICE_URL = "hessian_service_url";
    
    /**设备版本号*/
    public static final String RCP = "RCP";
    public static final String RMM = "RMM";
    public static final String RMB = "RMB";
    /* 中国ID */
    public static final String CHINA_ID = "001";
    /* 中国code */
    public static final String CHINA_CODE = "CN";
    public static final String PASSWORD_CHARS= "******";

    public static final String TABLE_NAME_UNIT = "UNIT"; //企业表
    
    public static final String TABLE_NAME_ACTIVITY = "ACTIVITY"; //活动表
    
    public static final String TABLE_NAME_NOTICE = "NOTICE"; //公告表

    public static final String TABLE_NAME_PROPERTYREPAIR = "PROPERTYREPAIR"; //物业报修表
    public static final String TABLE_NAME_SETTLEDMALL = "SETTLEDMALL"; //商圈入驻表
    
    /* 同步时候的临时文件 */
    public static final String SYNC_TEMP_FILE= "sync_data/";
    /* 同步上传时的临时文件 */
    public static final String SYNC_UPLOADS_TEMP_FILE= "uploads/";
    /* 导出时的临时文件 */
    public static final String EXPORT_TEMP_FILE= "export/";

    /** 订单服务化分割时间 */

//    public static final Long ORDER_SERVICE_TIME = 1430409600000L;
    //1436425200000L
    public static final Long ORDER_SERVICE_TIME =1438358400000L;// 1435507200000L;//1425139200000,1435680000000
    
    /** 微店最大商品销售数量 */
    public static final int MICRO_GOODS_NUMBER = 1000;

    /** 基础属性名*/
    public static final String ATTRIBUTE_SERIES = "ATTRIBUTE_SERIES";
    public static final String ATTRIBUTE_SEASON = "ATTRIBUTE_SEASON";
    public static final String ATTRIBUTE_FABRIC = "ATTRIBUTE_FABRIC";
    public static final String ATTRIBUTE_LINING = "ATTRIBUTE_LINING";
    public static final String ATTRIBUTE_TYPE = "ATTRIBUTE_TYPE";
    public static final String ATTRIBUTE_SIZE = "ATTRIBUTE_SIZE";
    public static final String ATTRIBUTE_PROTOTYPE = "ATTRIBUTE_PROTOTYPE";
    public static final String ATTRIBUTE_AUXILIARY = "ATTRIBUTE_AUXILIARY";
    public static final String ATTRIBUTE_COLOR = "ATTRIBUTE_COLOR";
    public static final String ATTRIBUTE_TYPE_BASE = "1";
    public static final String ATTRIBUTE_TYPE_DYNAMIC = "2";
    public static final String COLLECTION_TYPE_SKU = "1";
    public static final String COLLECTION_TYPE_GOODS = "2";

    /** 日志分类 */
    public static final String ERROR_LOGGER = "ERROR";
    public static final String BIZ_ERROR_LOGGER = "BIZ_ERROR";
    public static final String LONG_TIME_LOGGER = "LONGTIME";
    public static final String BIZ_LOGGER = "BIZ";

    /** 营销相关 */
    public static final Integer LASTVER_INIT = 1;
    public static final Long LASTVER = new Long(1);
    public static final BigDecimal BIG_DECIMAL_INIT = new BigDecimal(0.00);
    public static final Integer DIVISION = 1;
    /** 活动类别 */
    public static final Short SALES_TYPE_NP = 1; //第N件打折
    public static final Short SALES_TYPE_MINUS = 2; //满减
    public static final Short SALES_TYPE_SEND = 3; //满送
    public static final Short SALES_TYPE_BIND = 4; //捆绑打折
    public static final Short SALES_TYPE_COUPON = 5; //优惠劵
    public static final Short SALES_TYPE_MEM = 6; // 会员卡充值促销
    /** 折扣类别 */
    public static final Short DISCOUNT_TYPE_NP = 1;//第N件打折
    public static final Short DISCOUNT_TYPE_SEND = 2 ;//满送
    public static final Short DISCOUNT_TYPE_COUPONOUT = 3;//优惠券出券
    public static final Short DISCOUNT_TYPE_COUPONUSE = 4;//优惠券用券
    /** 购买组合方式 */
    public static final Short GROUP_TYPE_RANDOM = 1;//任意购买
    public static final Short GROUP_TYPE_SAME = 2;//必须购买同款
    public static final Short GROUP_TYPE_NOT_SAME = 3;//必须购买不同款
    /** 价格类别 */
    public static final Short STYLE_PRICE = 2;//款式价格
    public static final Short GOOD_PRICE = 1;//商品价格
    /** 价格方案 */
    public static final Short SALE_SCHEME_RATE = 1;//按折扣率
    public static final Short SALE_SCHEME_PRICE = 2;//按特价
    /** 选择门店标志 */
    public static final Short SHOP_FLAG_ALL = 1;//全部
    public static final Short SHOP_FLAG_PART = 2;//部分
    /** 赠送商品类别 */
    public static final Byte GIVE_GOOD_ID = 1;//商品id
    public static final Byte GIVE_STYLE_ID = 2;//款式id
    /** 单店或机构 */
    public static final Short SHOP_FLAG = 1;//商品id
    public static final Short ORGAN_FLAG = 2;//款式id
    /** 是否适合门店 */
    public static final Short IS_SHOP_YES = 1;//适合门店
    public static final Short IS_SHOP_NO = 0;//不适合门店
    /** 开关值 */
    public static final Short SWITCH_ON = 1;//开关打开
    public static final Short SWITCH_OFF = 0;//开关关闭

    /** 选择条件添加相关 */
    public static final int MAX_ADD_STYLE_NUM_BY_CONDITION = 1000;// 选择条件添加款式时，一次最多添加的条数

    /** 营业相关 */
    public static final String  DIC_THIRD_PAY= "DIC_THIRD_PAY" ;//第三方支付
    public static final String  PAY_MENT_CODE= "DIC_PAY_MODEL" ;//支付方式CODE
    public static final String SHOP_TYPE = "DIC_SHOP_TYPE";//店铺类型
    public static final String CONFIG_ALLOW_SUPPLY = "CONFIG_ALLOW_SUPPLY";//机构允许供货开关 1 启用 2 关闭
    public static final String CONFIG_APPOINT_COMPANY = "CONFIG_APPOINT_COMPANY";//向指定公司采购开关 1 启用 2 关闭

    /** 员工相关 */
    public static final Short  SHOW_TYPE_MAX = 9 ;//显示区分的最大值
    public static final Short  SHOW_TYPE_PROFIT = 1;//收益
    public static final Short  SHOW_TYPE_SALE = 3; // 销售金额
    public static final Short  SHOW_TYPE_CUSTOMER_NUM = 5;//客单数
    public static final Short  SHOW_TYPE_CUSTOMER_PRICE = 7;//客单价

    public static final Byte  SHOW_TYPE_IS = 1;//显示
    public static final Byte SHOW_TYPE_NOT = 0;//不显示
    public static final String API_PARAM_LOGGER = "API_PARAM";

    /** 开关 **/
    //关
    public static final Short STATUS_LIGHT_OFF = 0;
    //开
    public static final Short STATUS_LIGHT_OPEN = 1;


    public static final long UPLOAD_FILE_MAX_SIZE = 512*1024;
    public static final long UPLOAD_IMAGE_FILE_MAX_SIZE = 10*1024*1024;

    /** 门店/仓库区分 */
    public static final int WEIXIN_SHOP = 1;
    public static final int WEIXIN_WAREHOUSE = 2;

    /** 微店基本设置 */
    public static final String START_DISTRIBUTE = "CONFIG_START_DISTRIBUTE"; //启用微分销
    public static final String START_VIRTURL_STOCK = "CONFIG_START_VIRTURL_STOCK";//启用微店库存
    public static final String AUTO_CONFIRM_GOOD = "CONFIG_AUTO_CONFIRM_GOOD";//自动确认收货
    public static final String ALLOW_RETURN_GOOD = "CONFIG_ALLOW_RETURN_GOOD";//允许退货
    public static final String ALLOW_RETURN_GOOD_TERM = "CONFIG_ALLOW_RETURN_GOOD_TERM";//允许退货期限
    public static final String START_MEMBER_CARD_PAY = "CONFIG_START_MEMBER_CARD_PAY";//启用会员卡支付
    public static final String START_INTEGRAL_CONVERT = "CONFIG_START_INTEGRAL_CONVERT";//启用积分兑换
    public static final String START_THEMEPACKAGE = "CONFIG_START_THEMEPACKAGE";//启用主题销售包
    public static final String START_CASH_ON_THING = "CONFIG_START_CASH_ON_THING";//启用货到付款
    public static final String OPERATING_FREE = "CONFIG_OPERATING_FREE";//营业手续费
    public static final String CONFIG_EXCHANGE_WAREHOUSE = "CONFIG_EXCHANGE_WAREHOUSE";//兑换仓库
    public static final String CONFIG_WX_POINTS_GOODS_STOCK = "CONFIG_WX_POINTS_GOODS_STOCK";//微店积分库存开关

    /** 微分销基本设置 */
    public static final String START_KIN_SHOP = "CONFIG_KIN_SHOP"; //启用血缘关系
    public static final String MEMBER_REGIS_INTEGRAL = "CONFIG_MEMBER_REGIS_INTEGRAL";//会员注册送积分
    public static final String MEMBER_UPGRADE_JUNIOR = "CONFIG_MEMBER_UPGRADE_JUNIOR";//会员自动升级到小伙伴
    public static final String CONSUME_AMOUNT = "CONFIG_CONSUME_AMOUNT"; //消费金额
    public static final String PROFIT_BELONG_BIG_PARTNER = "CONFIG_PROFIT_BELONG_BIG_PARTNER";//分销余利归属大伙伴
    public static final String OPERATE_FEE = "CONFIG_OPERATING_FREE";//营业手续费
    public static final String TEMP_BALANCE_POSITIVE = "CONFIG_TEMP_BALANCE_POSITIVE";//临时余额转正
    
    /** openapi */
    public static final Short OPEN_API_SHOP_TYPE_UNIT = 0; // 公司
    public static final Short OPEN_API_SHOP_TYPE_DEPT = 1; // 部门
    public static final Short OPEN_API_SHOP_TYPE_STORE = 2; // 门店
    public static final Short OPEN_API_SHOP_TYPE_WAREHOUSE = 3; // 仓库
    
    public static final String BIG_PARTNER_EXAM = "CONFIG_BIG_PARTNER_EXAM";//大伙伴审核提现

    /** 退款方式 **/
    public static final Short SELL_RETURN_WAY_BANK = 1; //1：银行卡
    public static final Short SELL_RETURN_WAY_WX = 2; //2：微信
    public static final Short SELL_RETURN_WAY_ZFB = 3; //3：支付宝

    /** 分单状态 **/
    public static final Byte ORDER_DIVIDE_STATUS_PENDING = 1; //1：待处理
    public static final Byte ORDER_DIVIDE_STATUS_CONFIRM = 2; //2：已确认
    public static final Byte ORDER_DIVIDE_STATUS_REFUSE = 3; //3：已拒绝

    /** 评价类型*/
    public static final Byte COMMENT_REPORT_DETAIL_TYPE_ATTITUDE = 1; //1：服务态度
    public static final Byte COMMENT_REPORT_DETAIL_TYPE_SHOPPING = 2; //2：购物环境
    public static final Byte COMMENT_REPORT_DETAIL_TYPE_SERVICE = 3; //3：售后服务
    public static final Byte COMMENT_REPORT_DETAIL_TYPE_DESCRIPTION = 4; //4：描述相符
    public static final Byte COMMENT_REPORT_DETAIL_TYPE_SHIPPING = 5; // 5：物流服务
   
    /** 商家金额变动流水操作 **/
    public static final Byte SHOP_MONEY_FLOW_ACTION_WITHDRAW = 1; //余额提现
    public static final Byte SHOP_MONEY_FLOW_ACTION_SUPPLY = 2; //供货
    public static final Byte SHOP_MONEY_FLOW_ACTION_SELL_RETURN = 3; //退货扣款
    public static final Byte SHOP_MONEY_FLOW_ACTION_SURPLUS_PROFIT = 4; //余利

    /** 伙伴金额变动流水操作**/
    public static final Byte COMPANION_MONEY_FLOW_ACTION_WITHDRAW = 1; //余额提现
    public static final Byte COMPANION_MONEY_FLOW_ACTION_CHANGE_2_BALANCE = 2; //积分余额
    public static final Byte COMPANION_MONEY_FLOW_ACTION_REBATE = 3; //返利
    public static final Byte COMPANION_MONEY_FLOW_ACTION_SELL_RETURN = 4; //退货扣款
    public static final Byte COMPANION_MONEY_FLOW_ACTION_SURPLUS_PROFIT = 5; //余利

    /** 伙伴状态 **/
    public static final Short COMPANION_STATUS_ACTIVATION = 1; //激活
    public static final Short COMPANION_STATUS_FROZEN = 2; //冻结


    /** 余额提现 **/
    public static final Byte WITHDRAW_CHECK_BALANCE = 1; //1：余额提现
    public static final Byte WITHDRAW_CHECK_INTEGRAL = 2; //2：余额转积分
    public static final Byte WITHDRAW_CHECK_REBATE = 3; //3：返利
    public static final Byte WITHDRAW_CHECK_RETURN_CHARGE = 4; //4：退货扣款

    /** 余额提现状态 **/
    public static final Byte WITHDRAW_CHECK_NOT_START = 1; //1：未审核
    public static final Byte WITHDRAW_CHECK_NOT_PASS = 2; //2：审核不通过
    public static final Byte WITHDRAW_CHECK_PASS = 3; //3：审核通过
    public static final Byte WITHDRAW_CHECKCANCEL = 4; //3：取消


    /** ONS订单消费者Tag限制 **/
    public static final String RETAIL_ORDER_TAG_DIVIDE = "retail_order_tag_divide";

    /** 订单类型 **/
    public static final int ORDER_TYPE_SELL = 1;    //1：销售订单
    public static final int ORDER_TYPE_DIVIDE = 2;  //2：供货订单
    /** 订单来源 **/
    public static final String ORDER_OUT_TYPE_ENTITY = "entity"; // 实体订单
    public static final String ORDER_OUT_TYPE_WEIXIN = "weixin"; // 微信订单
    public static final String ORDER_OUT_TYPE_WEIPLATFORM = "weiPlatform"; // 微平台订单

    /** 第三方平台类型 **/
    public static final Byte THIRD_PARTY_TYPE_WECHAT = 1; //微信平台

    /** 虚拟库存状态 */
    public static final Byte VIRTUAL_STORE_STATUS_1 = 1; //无虚拟库存
    public static final Byte VIRTUAL_STORE_STATUS_2 = 2; //虚拟库存正常
    public static final Byte VIRTUAL_STORE_STATUS_3 = 3; //虚拟库存异常

    /** 会员卡状态*/
    public static final Short CUSTOMER_CARD_STATUS_NORMAL = 1;//正常
    public static final Short CUSTOMER_CARD_STATUS_LOSS = 2;//挂失
    public static final Short CUSTOMER_CARD_STATUS_CANCEL = 3;//注销
    public static final Short CUSTOMER_CARD_STATUS_UNNORMAL = 4;//异常


    /** 主页显示权限 */
    public static final String HOME_SHOW_ACTION_CODE = "ACTION_INCOME_SEARCH";

    /** h5微店搜索类型 */
    public static final Short MICRO_WEB_SEARCH_TYPE_1 = 1; // 综合 （优先值升序+更新时间降序）
    public static final Short MICRO_WEB_SEARCH_TYPE_2 = 2; // 优惠商品 （特价商品）
    public static final Short MICRO_WEB_SEARCH_TYPE_3 = 3; // 最近购买 （会员最近购买商品，时间降序）（最近购买在微信端直接请求订单服务化）
    public static final Short MICRO_WEB_SEARCH_TYPE_4 = 4; // 人气优先 （销量排序）

    /** 微店购物车 商品营销标志 */
    public static final Byte MICRO_SHOP_GOODS_SPECIAL_PRICE = 1;//特价标志
    public static final Byte MICRO_SHOP_GOODS_SALESN_PRICE = 2;//N件打折
    public static final Byte MICRO_SHOP_GOODS_BINDING_SELL = 3;//捆绑
    public static final Byte MICRO_SHOP_GOODS_FULL_CUT = 4;//满减
    public static final Byte MICRO_SHOP_GOODS_FULL_SEND = 5;//满送
    public static final Byte MICRO_SHOP_GOODS_EXCHANGE_BUY = 6;//换购

    /** 入驻商圈申请状态  **/
    public static final Byte SETTLEDMALL_STATUS_INVITE = 0; //0：邀请中
    public static final Byte SETTLEDMALL_STATUS_PENDING = 1; //1：已申请
    public static final Byte SETTLEDMALL_STATUS_CONFIRM = 2; //2：已入驻
    public static final Byte SETTLEDMALL_STATUS_REFUSE = 3; //3：拒绝
    public static final Byte SETTLEDMALL_STATUS_RELIEVE = 4; //4：解除
    
    /** 支付方式  固定编码不可变更**/
    public static final int PAY_MODE_MALL = 13;//园区卡，固定编码不可变更

    /** 订单服务化扩展字段常量 */
    public static final String RECEIVING_TIME = "receiving_time";//确认收货时间
    public static final String SKU = "sku";//sku属性值
    public static final String CONSUME_POINTS = "consume_points";//消费积分
    public static final String HANG_TAG_PRICE = "hang_tag_price";//吊牌价
    
    public static final String IS_TOTAL_RETURN = "is_total_return";//是否整单折扣

    /** 数据删除一次性数量 */
    public static final Integer DELETE_DATA_COUNT = 1000;//一次性删除数据量

    public static final BigDecimal MALL_WEIXIN_RATE = new BigDecimal(0.006); //园区微信结算率：千分之六

    /** 微店配送管理 */
    public static final String DIC_DISTRIBUTION_STRATEGY = "DIC_DISTRIBUTION_STRATEGY"; // 配送策略
    public static final String DIC_WX_LOGISTICS_TYPE = "DIC_WX_LOGISTICS_TYPE"; // 配送类型
    public static final String DIC_LOGISTICS_DISTRIBUTION = "DIC_LOGISTICS_DISTRIBUTION"; // 第三方物流配送(默认物流公司)
    public static final String DIC_IS_FREE_SHIPPING = "DIC_IS_FREE_SHIPPING"; // 是否包邮
    public static final String DIC_FREE_SHIPPING_CONDITION = "DIC_FREE_SHIPPING_CONDITION"; // 包邮条件
    
    public class DynamicAction {
        
        public static final String WEI_DISTRIBUTION = "WEI_DISTRIBUTION";
        public static final String ONLINE_RECEIPT = "ONLINE_RECEIPT";
        
    }
    
    public class MoudleCode {
    	// 微分销
    	public static final String MODULE_WEI_DISTRIBUTION = "MODULE_WEI_DISTRIBUTION";
    	// 微信
        public static final String MODULE_WEIXIN = "MODULE_WEIXIN";
    	
    }
    
}