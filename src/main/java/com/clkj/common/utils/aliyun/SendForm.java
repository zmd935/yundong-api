package com.clkj.common.utils.aliyun;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author Created by YangLiu on 2020-09-21
 */
@Data
public class SendForm {
    private String phone;
    private JSONObject params;
    private String templateCode;
}
