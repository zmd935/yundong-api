package com.clkj.modules.sys.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Created by YangLiu on 2021-07-22
 */
@Data
public class SetLangForm {
    /**
     * 默认语言
     */
    @NotBlank(message = "语言不能为空")
    private String lang;
}
