package com.clkj.common.file.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * date文件名和目录（有并发问题）
 *
 * @author Created by YangLiu on 2021-12-28
 */
public class FileNameDate implements FileName {
    @Override
    public String genFileName(String ext) {
        StringBuilder sb = new StringBuilder();
        //存储仓库号
        sb.append(0)
                //时间格式：年月日时分秒
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                //随机数
                .append(Math.abs(new Random().nextInt()) % 10000)
                .append(".")
                //扩展名
                .append(ext);
        return sb.toString();
    }

    @Override
    public String computeFolder(String fileName) {
        if (null != fileName && !"".equals(fileName)) {
            String yearStr = fileName.substring(1, 5);
            String monthStr = fileName.substring(5, 7);
            String dayStr = fileName.substring(7, 9);
            return SEPARATOR + yearStr + SEPARATOR + monthStr + SEPARATOR + dayStr;
        } else {
            return "";
        }
    }
}
