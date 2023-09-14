package com.clkj.common.file.util;

import com.clkj.common.utils.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Created by YangLiu on 2020-10-12
 */
public class FileUtil {
    private static final String SEPARATOR = File.separator;

    private static final FileName fileNameUtil = new FileNameHash();

    /**
     * 文件保存
     *
     * @param root 根目录
     * @param file 文件
     * @return 文件名
     */
    public static String storeFile(String root, MultipartFile file) {
        //获得扩展名
        String extName = FileUtil.genExtName(file.getOriginalFilename());
        //根据扩展名，生成一个新的文件名
        String fileName = fileNameUtil.genFileName(extName);
        //目录不存在，就创建
        File storeFile = createFileDirs(root, fileName);
        try {
            file.transferTo(storeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }


    /**
     * 获得已保存的文件File
     *
     * @param fileName 文件命名
     * @return File
     */
    public static File getStoreFile(String root, String fileName) {
        return new File(getFullFileFolder(root, fileName) + SEPARATOR + fileName);
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
     * 生成文件全路径，如果不存在就创建
     *
     * @param fileName 文件命名
     * @return File
     */
    private static File createFileDirs(String root, String fileName) {
        String fullFileFolder = getFullFileFolder(root, fileName);
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
     * @return String
     */
    private static String getFullFileFolder(String root, String fileName) {
        return root + fileNameUtil.computeFolder(fileName);
    }
}
