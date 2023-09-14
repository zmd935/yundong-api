package com.clkj.common.file.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件上传响应表单
 *
 * @author Created by YangLiu on 2020-10-10
 */
@ApiModel
@Data
public class FileUploadForm {

    @ApiModelProperty("名称")
    String name;

    @ApiModelProperty("url")
    String url;
}
