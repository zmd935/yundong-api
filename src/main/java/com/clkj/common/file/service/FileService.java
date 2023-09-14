package com.clkj.common.file.service;

import com.clkj.common.file.form.FileUploadForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author Created by YangLiu on 2020-10-10
 */
public interface FileService {
    /**
     * 保存单个文件
     *
     * @param file 文件
     * @return FileUploadForm
     */
    FileUploadForm storeFile(MultipartFile file);

    /**
     * 保存多个文件
     *
     * @param files 多个文件
     * @return List
     */
    List<FileUploadForm> storeFiles(List<MultipartFile> files);

    /**
     * 获得保存的文件
     *
     * @param name name
     * @return File
     */
    File getStoreFile(String name);
}
