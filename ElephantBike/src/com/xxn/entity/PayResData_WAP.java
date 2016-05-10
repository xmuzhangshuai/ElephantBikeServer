package com.xxn.entity;


/**
 * User: rizenguo
 * Date: 2014/10/22
 * Time: 21:29
 */

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求被扫支付API需要提交的数据
 */
public class PayResData_WAP {

    //每个字段具体的意思请查看API文档
    private String appId = "";
    private String timeStamp = "";
    private String nonceStr = "";
    private String packa = "";
    private String signType="";
    private String 	paySign = "";
    /**
     * @param authCode 这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @param body 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @param attach 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
     * @param outTradeNo 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
     * @param totalFee 订单总金额，单位为“分”，只能整数
     * @param deviceInfo 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
     * @param spBillCreateIP 订单生成的机器IP
     * @param timeStart 订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     * @param timeExpire 订单失效时间，格式同上
     * @param goodsTag 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
     */
    public PayResData_WAP(String prepayid){

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppId(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）

        //这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
        //调试的时候可以在微信上打开“钱包”里面的“刷卡”，将扫码页面里的那一串14位的数字输入到这里来，进行提交验证
        //记住out_trade_no这个订单号可以将这一笔支付进行退款

        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setPacka("prepay_id="+prepayid);
        //随机字符串，不长于32 位
        setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
        setTimestamp(new Date().getTime()/1000+"");
        setSignType("MD5");
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setPaySign(sign);//把签名数据设置到Sign这个属性中
    }


	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPacka() {
		return packa;
	}

	public void setPacka(String packa) {
		this.packa = packa;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimestamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getPaySign() {
		return paySign;
	}


	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                	if(field.getName().equals("packa")){
                		map.put("package", obj);
                	}
                	else map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
