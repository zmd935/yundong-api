package com.clkj.common.utils;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version V1.0
 * @ClassName:CheckCaptchaUtls
 * @Description:
 * @author:Lizy
 * @date 2019/12/05 14:05
 */
@Component
public class CheckCaptchaUtils {

    public static boolean isVerify(List<Integer> datas) {
        int sum = 0;
        for (Integer data : datas) {
            sum += data;
        }
        double avg = sum * 1.0 / datas.size();

        double sum2 = 0.0;
        for (Integer data : datas) {
            sum2 += Math.pow(data - avg, 2);
        }

        double stddev = sum2 / datas.size();
        return stddev != 0;
    }
}
