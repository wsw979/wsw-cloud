package io.cloud.sms.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @program: wsw-cloud-servce
 * @description: 阿里短信
 * @author: wsw
 * @create: 2020-07-01 17:33
 **/
public class SmsUtil {

    //产品名称（无需修改）
    private static final String PRODUCU = "Dysmsapi";
    //产品域名（无需修改）
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    //连接超时
    private static String CONNECT_TIME_OUT = "10000";
    //读取超时
    private static String READ_TIME_OUT = "10000";

    /**
     * 发送验证码
     * @param phone 手机号/验证码
     * @param sms   短信签名，提前在阿里云申请/模板代码，提前在阿里云申请
     * @return
     */
    public SendSmsResponse sendSms(Phone phone, Sms sms) {
        //初始化阿里云短信配置
        IAcsClient acsClient = initIAcsClient(sms);
        //使用验证码配置模板
        String templateParam = "{\"code\":\"" + phone.getSmsCode() + "\"}";
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号  例如 "18629501095"
        request.setPhoneNumbers(phone.getSmsPhone());
        //必填:短信签名-可在短信控制台中找到  例如 "debug"
        request.setSignName(sms.getSignName());
        //必填:短信模板-可在短信控制台中找到， 例如 "SMS_151178564"
        request.setTemplateCode(sms.getTemplateCode());
        //可选:模板中的变量替换JSON串
        request.setTemplateParam(templateParam);
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {}

        return sendSmsResponse;
    }

    /**
     * 初始化配置
     * @return
     */
    private static IAcsClient initIAcsClient(Sms sms){
        IAcsClient acsClient;
        System.setProperty("sun.net.client.defaultConnectTimeout", CONNECT_TIME_OUT);
        System.setProperty("sun.net.client.defaultReadTimeout", READ_TIME_OUT);
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", sms.getAccessKey(), sms.getAccessSecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCU, DOMAIN);
            acsClient = new DefaultAcsClient(profile);
            return acsClient;
        } catch (ClientException e) {

        }
        return null;
    }

}
