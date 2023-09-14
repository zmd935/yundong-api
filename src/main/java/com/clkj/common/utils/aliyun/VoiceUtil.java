//package com.clkj.common.utils.aliyun;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
//import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.clkj.common.exception.RRException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 阿里云语音服务
// *
// * @author Created by YangLiu on 2020-09-10
// */
//@Slf4j
//@Component
//public class VoiceUtil {
//
//    //产品名称:云通信语音API产品,开发者无需替换
//    private static final String product = "Dyvmsapi";
//    //产品域名,开发者无需替换
//    private static final String domain = "dyvmsapi.aliyuncs.com";
//
//    private static final String regionId = "cn-hangzhou";
//
//    @Value("${spring.profiles.active}")
//    String env;
//
//    public boolean sendVoiceByTts(SendForm form) {
//        if ("dev".equals(env)) {
//            log.info("模拟->语音发送->成功：" + form.getPhone() + ", code:" + form.getParams());
//            return true;
//        }
//
//        //设置访问超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//        //云通信产品-语音API服务产品名称（产品名固定，无需修改）
//
//        boolean flag = false;
//        try {
//            //初始化acsClient 暂时不支持多region
//            IClientProfile profile = DefaultProfile.getProfile(regionId, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
//            DefaultProfile.addEndpoint(regionId, regionId, product, domain);
//            IAcsClient acsClient = new DefaultAcsClient(profile);
//            SingleCallByTtsRequest request = new SingleCallByTtsRequest();
//            //必填-被叫显号,可在语音控制台中找到所购买的显号
//            request.setCalledShowNumber("");
//            //必填-被叫号码
//            request.setCalledNumber(form.getPhone());
//            //必填-Tts模板ID
//            request.setTtsCode(form.getTemplateCode());
//            //可选-当模板中存在变量时需要设置此值
//            request.setTtsParam(form.getParams().toString());
//            //可选-音量 取值范围 0--200
//            request.setVolume(100);
//            //可选-播放次数
//            request.setPlayTimes(3);
//            //可选-外部扩展字段,此ID将在回执消息中带回给调用方
//            //request.setOutId("yourOutId");
//            //hint 此处可能会抛出异常，注意catch
//            SingleCallByTtsResponse response = acsClient.getAcsResponse(request);
//            if ("OK".equals(response.getCode())) {
//                flag = true;
//                log.info("发送语音成功：" + form.getPhone() + ", content:" + response.getMessage());
//            } else {
//                log.info("发送语音失败：" + form.getPhone() + ", content:" + response.getMessage());
//                throw new RRException("发送语音失败");
//            }
//        } catch (ClientException e) {
//            e.printStackTrace();
//            throw new RRException("发送语音失败");
//        }
//
//        return false;
//    }
//}
