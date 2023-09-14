/**
 * Copyright (c) 2015 CHENGLING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * CHENGLING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with CHENGLING.
 */
package com.clkj.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统参数
 *
 * @author zhangyl
 * @version $Revision$ $Date$
 */

@Component
public class SystemParams {
    /**
     * 文件服务器
     */
    public static String fileServerUrl;
    /**
     * 上传文件存储位置
     */
    public static String fileStoreDir;
    /**
     *
     */
    public static String webUrl;

    /**
     * 文件服务器下载文件Action
     */
    public final static String fileServerDownloadAction = "common/file/download";

    /**
     * 存储文件名的变量名
     */
    public final static String fileServerStoreFNameVName = "name";

    public SystemParams() {
    }

    public static StringBuilder getFileShowUrl() {

        return new StringBuilder().append(SystemParams.fileServerUrl).append("/")
                .append(fileServerDownloadAction)
                .append("?").append(fileServerStoreFNameVName).append("=");
    }

    @Value("${common.file.fileServerUrl}")
    private void setFileServerUrl(String fileServerUrl) {
        SystemParams.fileServerUrl = fileServerUrl;
    }

    @Value("${common.file.fileStoreDir}")
    private void setFileStoreDir(String fileStoreDir) {
        SystemParams.fileStoreDir = fileStoreDir;
    }

    @Value("${common.file.webUrl}")
    private void setWebUrl(String webUrl) {
        SystemParams.webUrl = webUrl;
    }

}
