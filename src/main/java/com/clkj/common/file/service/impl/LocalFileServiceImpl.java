package com.clkj.common.file.service.impl;

import com.clkj.common.config.SystemParams;
import com.clkj.common.file.form.FileUploadForm;
import com.clkj.common.file.service.FileService;
import com.clkj.common.file.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地图片，上传下载
 *
 * @author Created by YangLiu on 2020-10-10
 */
@Service("localFileService")
public class LocalFileServiceImpl implements FileService {

    /**
     * 上传文件存储位置
     */
    @Value("${common.file.fileStoreDir}")
    private String fileStoreDir;

    @Value("${common.file.fileShowUrl}")
    private String fileShowUrl;

    /**
     * 保存多个文件
     *
     * @param file
     * @return
     */
    @Override
    public FileUploadForm storeFile(MultipartFile file) {

        String fileName = FileUtil.storeFile(fileStoreDir, file);
        //封装返回对象
        FileUploadForm item = new FileUploadForm();
        item.setName(fileName);
        item.setUrl(this.fileShowUrl + fileName);
        return item;
    }

    /**
     * 保存多个文件
     *
     * @param files
     * @return
     */
    @Override
    public List<FileUploadForm> storeFiles(List<MultipartFile> files) {
        List<FileUploadForm> forms = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadForm item = this.storeFile(file);
            forms.add(item);

        }
        return forms;
    }

    @Override
    public File getStoreFile(String name) {
        return FileUtil.getStoreFile(fileStoreDir, name);
    }
}
