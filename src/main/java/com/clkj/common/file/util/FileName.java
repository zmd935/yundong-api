package com.clkj.common.file.util;

import java.io.File;

/**
 * @author Created by YangLiu on 2021-12-28
 */
public interface FileName {
    /**
     * 文件分隔符
     */
    String SEPARATOR = File.separator;

    /**
     * 生成文件名
     *
     * @param ext 扩展名
     * @return 文件名
     */
    String genFileName(String ext);

    /**
     * 生成文件存储目录
     *
     * @param fileName 文件名
     * @return 目录后缀
     */
    String computeFolder(String fileName);
}
