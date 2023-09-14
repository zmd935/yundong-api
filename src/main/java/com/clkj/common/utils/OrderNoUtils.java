package com.clkj.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.clkj.common.enums.OrderPreEnum;
import org.springframework.stereotype.Component;

/**
 * 订单号生成
 *
 * @author Created by YangLiu on 2022-12-12
 */
@Component
public class OrderNoUtils {

    /**
     * 生成交易唯一ID
     * 集群部署workerId和datacenterId请配置到yml
     *
     * @return string
     */
    public String gen(OrderPreEnum pre) {
        Snowflake snowflake = IdUtil.getSnowflake(0, 0);
        return pre.getValue() + snowflake.nextIdStr();
    }
}
