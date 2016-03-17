package com.xxn.constants;

public class BikeConstants {

	/**
	 * sign key
	 */
	public static final String SIGN_KEY = "dfeb3d35bc3543rdc234";
	
	/**
	 * 云通讯
	 */
	public static final String YUN_TEMPLATEID = "72742";
	public static final String YUN_ACCOUNT_SID = "8a48b55152f73add0152ff0804121100";
	public static final String YUN_AUTH_TOKEN = "1cda62228f8040d4a0b0929eaf00b96b";
	public static final String YUN_REST_URL = "https://app.cloopen.com:8883";
	public static final String YUN_APP_ID = "aaf98f895350b688015372c58ad23634";
	
	/**
	 * 微信支付
	 */
	public static final String WX_PAY_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String WX_KEY = "13190819011daxiangdanche12345678";
	public static final String WX_APP_ID = "wx4a480f3f5a6c4c6c";
	public static final String WX_MCH_ID = "1319081901";
	public static final String WX_CERTPASSWORD = "1319081901";
	
	
	/**
	 * 图片存储地址文件夹
	 */
	public static final String IMAGR_URL = "bikeimage";
	public static final String WEB_IMAGR_URL = "WebImage/images/";
	/**
	 * 状态标签
	 */
	public static final String STATUS = "status";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String MESSAGE = "message";
	
	/**
	 * 临时变量
	 */
	public static final String VERIFY_CODE = "123456";
	public static final String UNLOCK_CODE = "1234";
	public static final String RETURN_CODE = "456789";
	public static final String RESTORE_CODE = "456789";
	
	/**
	 * 自行车分段 周期
	 */
	public static final int STAGEONE = 1;
	public static final int STAGETWO = 2;
	public static final int STAGETHREE = 3;
	
	/**
	 * 丢车费用 200
	 */
	public static final int MISSFEE = 200;
	/**
	 * 余额明细列表请求条数
	 */
	public static final int BAL_COUNT = 10;
	/**
	 * 用户状态 -1:冻结  0:普通用户  1:认证用户
	 */
	public static final String FROZEN = "-1";
	public static final String NORMAL = "0";
	public static final String CENTIFY= "1";
}
