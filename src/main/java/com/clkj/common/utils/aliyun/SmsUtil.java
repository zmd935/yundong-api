package com.clkj.common.utils.aliyun;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.clkj.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 阿里云短信
 */
@Slf4j
@Component
public class SmsUtil {
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String regionId = "cn-hangzhou";

    @Value("${spring.profiles.active}")
    String env;

    /**
     * 发送验证码
     *
     * @param phone        手机号
     * @param code         验证码
     * @param templateCode 模板
     */
    public void sendSms(String phone, String code, String templateCode) {
        if ("dev".equals(env)) {
            log.info("模拟发送短信成功：" + phone + ", code:" + code);
            return;
        }

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(regionId, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
        try {
            DefaultProfile.addEndpoint(regionId, regionId, product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(AliyunConfig.signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            JSONObject tmp = new JSONObject();
            tmp.put("code", code);
            request.setTemplateParam(tmp.toString());

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse response = acsClient.getAcsResponse(request);

            if ("OK".equals(response.getCode())) {
                log.info("发送短信成功：" + phone + ", content:" + response.getMessage());
            } else {
                log.info("发送短信失败：" + phone + ", content:" + response.getMessage());
                throw new RRException("发送短信失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RRException("发送短信失败");
        }

    }

    /**
     * 根据模板发送短信
     *
     * @param form 参数
     */
    public boolean send(SendForm form) {
        if ("dev".equals(env)) {
            log.info("模拟->短信发送->成功：" + form.getPhone() + ", code:" + form.getParams());
            return true;
        }

        boolean flag = false;
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(regionId, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
        try {
            DefaultProfile.addEndpoint(regionId, regionId, product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(form.getPhone());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(AliyunConfig.signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(form.getTemplateCode());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(form.getParams().toString());

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse response = acsClient.getAcsResponse(request);

            if ("OK".equals(response.getCode())) {
                flag = true;
                log.info("发送短信成功：" + form.getPhone() + ", content:" + response.getMessage());
            } else {
                log.info("发送短信失败：" + form.getPhone() + ", content:" + response.getMessage());
            }
        } catch (ClientException e) {
            e.printStackTrace();
            log.info("发送短信失败");
        }

        return flag;
    }

    /**
     * 根据模板批量发送短信
     *
     * @param forms 参数
     */
    public boolean sendBatch(List<SendForm> forms, String templateCode) {
        if (forms == null || forms.size() == 0) {
            return false;
        }
        JSONArray phones = new JSONArray();
        JSONArray signNames = new JSONArray();
        JSONArray params = new JSONArray();

        for (SendForm item : forms) {
            phones.add(item.getPhone());
            signNames.add(AliyunConfig.signName);
            params.add(item.getParams());
        }
        if ("dev".equals(env)) {
            log.info("模拟->短信发送->成功：" + phones);
            return true;
        }


        boolean flag = false;
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(regionId, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
        try {
            DefaultProfile.addEndpoint(regionId, regionId, product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendBatchSmsRequest request = new SendBatchSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumberJson(phones.toString());
            //必填:短信签名-可在短信控制台中找到
            request.setSignNameJson(signNames.toString());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParamJson(params.toString());

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            SendBatchSmsResponse response = acsClient.getAcsResponse(request);

            if ("OK".equals(response.getCode())) {
                flag = true;
                log.info("发送短信成功：" + phones + ", content:" + response.getMessage());
            } else {
                log.info("发送短信失败：" + phones + ", content:" + response.getMessage());
            }
        } catch (ClientException e) {
            e.printStackTrace();
            log.info("发送短信失败");
        }

        return flag;
    }

}
