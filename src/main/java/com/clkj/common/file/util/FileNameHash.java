package com.clkj.common.file.util;

import cn.hutool.core.util.IdUtil;

/**
 * hash文件名和目录
 *
 * @author Created by YangLiu on 2021-12-28
 */
public class FileNameHash implements FileName {
    @Override
    public String genFileName(String ext) {
        return IdUtil.fastSimpleUUID() + "." + ext;
    }

    @Override
    public String computeFolder(String fileName) {
        int index = fileName.lastIndexOf("\\.");
        String uuid;
        if (index != -1) {
            uuid = fileName.substring(0, index);
        } else {
            uuid = fileName;
        }
        // 计算文件夹名
        int hashCode = uuid.hashCode();
        //1级目录
        String folder1 = Integer.toHexString(hashCode & 0xf);
        //2级目录
        String folder2 = Integer.toHexString((hashCode >> 8) & 0xf);
        return SEPARATOR + folder1 + SEPARATOR + folder2;
    }
}
