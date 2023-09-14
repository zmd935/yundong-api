package com.clkj.common.utils;

import com.clkj.common.config.SystemParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Created by YangLiu on 2020-10-12
 */
public class PrivateFileUtil {
    private static String SEPARATOR = File.separator;

    public static String storeFile(MultipartFile file) {
        //获得扩展名
        String extName = genExtName(file.getOriginalFilename());

        //根据扩展名，生成一个新的文件名
        String fileName = genFileName(extName);
        //目录不存在，就创建
        File storeFile = createFileDirs(fileName);
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(storeFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }


    /**
     * 获得已保存的文件File
     *
     * @param fileName 文件命名
     * @return
     */
    public static File getStoreFile(String fileName) {
        return new File(getFullFileFolder(fileName) + SEPARATOR + fileName);
    }

    /**
     * 验证文件名是否正确
     *
     * @param fileName 文件名称（包括扩展名）
     * @return
     */
    public static boolean validateFileName(String fileName) {

        if (StringUtils.isBlank(fileName)) {
            return false;
        }

        String name = StringUtils.substringBeforeLast(fileName, ".");
        if (name.length() != 19) {
            return false;
        }

        return StringUtils.isNumeric(name);

    }

    /**
     * 获得文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    private static String genExtName(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }


    /**
     * 生成文件名
     *
     * @param ext 扩展名
     * @return 新的文件名
     */
    private static String genFileName(String ext) {

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

    /**
     * 生成文件全路径，如果不存在就创建
     *
     * @param fileName 文件命名
     * @return
     */
    private static File createFileDirs(String fileName) {
        String fullFileFolder = getFullFileFolder(fileName);
        //如果文件夹不存在，就创建
        File file = new File((fullFileFolder));
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(fullFileFolder + SEPARATOR + fileName);
    }

    /**
     * 获得文件全文件夹路径，不包括名字
     *
     * @param fileName 文件名
     * @return
     */
    private static String getFullFileFolder(String fileName) {

//        return SystemParams.privateFileStoreDir + computeDateFolder(fileName);
        return "";
    }

    /**
     * 计算目录 根据日期
     *
     * @param fileName 文件名
     * @return
     */
    private static String computeDateFolder(String fileName) {
        if (null != fileName && !"".equals(fileName)) {
            String yearStr = fileName.substring(1, 5);
            String monthStr = fileName.substring(5, 7);
            String dayStr = fileName.substring(7, 9);
            return SEPARATOR + yearStr + SEPARATOR + monthStr + SEPARATOR + dayStr;
        } else {
            return "";
        }
    }

    /**
     * 计算1级目录和2级目录（根据hash值）
     *
     * @param fileName 文件名
     * @return 拼接1级目录和2级目录
     */
    private static String computeHashFolder(String fileName) {
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
