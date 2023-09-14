package com.clkj.modules.sys.form;

import lombok.Data;


/**
 * @author Created by YangLiu on 2022-03-29
 */
@Data
public class SysUserTokenForm {

    private String token;

    private int expire;
}
