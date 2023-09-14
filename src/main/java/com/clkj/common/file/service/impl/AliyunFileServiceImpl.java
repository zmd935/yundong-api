package com.clkj.common.file.service.impl;

import com.clkj.common.file.form.FileUploadForm;
import com.clkj.common.file.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * todo 待开发
 * 阿里云 图片上传下载
 *
 * @author Created by YangLiu on 2020-10-10
 */
public class AliyunFileServiceImpl implements FileService {

    /**
     * 保存单个文件
     *
     * @param file 文件
     * @return
     */
    @Override
    public FileUploadForm storeFile(MultipartFile file) {
        return null;
    }

    /**
     * 保存多个文件
     *
     * @param files 多个文件
     * @return 对象
     */
    @Override
    public List<FileUploadForm> storeFiles(List<MultipartFile> files) {
        List<FileUploadForm> list = new ArrayList<>();
        files.forEach(file -> {

        });
        return list;
    }

    @Override
    public File getStoreFile(String name) {
        return null;
    }
}
