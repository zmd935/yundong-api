package com.clkj.common.file.controller;

import com.clkj.common.file.form.FileUploadForm;
import com.clkj.common.file.service.FileService;
import com.clkj.common.file.util.FileUtil;
import com.clkj.common.utils.R;
import com.clkj.common.utils.StringUtils;
import com.clkj.common.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载
 *
 * @author Created by YangLiu on 2020-10-10
 */
@Api(tags = "文件上传下载")
@RequestMapping("/common/file")
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(@Qualifier("localFileService") FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Kindeditor文件上传
     *
     * @param file 文件
     * @return
     */
    @PostMapping("/uploadKEImg")
    public Object uploadKEImg(MultipartFile file) {
        Assert.notNull(file, "请选择文件");
        //保存文件到本地
        FileUploadForm form = fileService.storeFile(file);

        Map<String, Object> result = new HashMap<>();
        result.put("error", 0);
        result.put("url", form.getUrl());
        result.put("message", form.getName());
        return result;
    }

    /**
     * 单文件上传
     *
     * @param file 文件
     * @return
     */
    @ApiOperation("上传")
    @PostMapping("/upload")
    public R<FileUploadForm> upload(MultipartFile file) {
        Assert.notNull(file, "请选择文件");
        //保存文件到本地
        FileUploadForm form = fileService.storeFile(file);
        return R.data(form);
    }

    /**
     * 多文件上传
     *
     * @param files 多个文件
     * @return
     */
    @PostMapping("/uploads")
    public R<List<FileUploadForm>> uploads(List<MultipartFile> files) {
        Assert.greaterThan(files.size(), 0, "至少上传一个文件");
        //判断最大上传文件数量
        int max = 10;
        Assert.lessThanOrEquals(files.size(), max, MessageFormat.format("超过文件最大上传数量{0}", max));

        //保存文件到本地
        List<FileUploadForm> forms = fileService.storeFiles(files);
        return R.data(forms);
    }

    /**
     * 下载文件
     *
     * @param name 文件名
     * @param res  响应
     */
    @ApiOperation("下载")
    @GetMapping("/download")
    public void download(@RequestParam("name") String name, HttpServletResponse res) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        File file = fileService.getStoreFile(name);
        //如果文件不存在就返回
        if (!file.exists()) {
            return;
        }

        res.setContentType("application/octet-stream;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Content-Disposition", "attachment; filename=" + new String(name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        res.setContentLength((int) file.length());

        try {
            FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载视频文件
     *
     * @throws Exception
     */
    @ApiOperation(value = "下载视频文件")
    @GetMapping("/download-video")
    public void downloadVideo(String name, HttpServletResponse response, HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(name)) {
            return;
        }
        File file = fileService.getStoreFile(name);
        //只读模式
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(file, "r");
            long contentLength = randomFile.length();
            String range = request.getHeader("Range");
            int start = 0, end = 0;
            if (range != null && range.startsWith("bytes=")) {
                String[] values = range.split("=")[1].split("-");
                start = Integer.parseInt(values[0]);
                if (values.length > 1) {
                    end = Integer.parseInt(values[1]);
                }
            }
            int requestSize = 0;
            if (end != 0 && end > start) {
                requestSize = end - start + 1;
            } else {
                requestSize = Integer.MAX_VALUE;
            }

            response.setContentType("video/mp4");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("ETag", name);
            response.setHeader("Last-Modified", new Date().toString());
            //第一次请求只返回content length来让客户端请求多次实际数据
            if (range == null) {
                response.setHeader("Content-length", contentLength + "");
            } else {
                //以后的多次以断点续传的方式来返回视频数据 //206
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                long requestStart = 0, requestEnd = 0;
                String[] ranges = range.split("=");
                if (ranges.length > 1) {
                    String[] rangeDatas = ranges[1].split("-");
                    requestStart = Integer.parseInt(rangeDatas[0]);
                    if (rangeDatas.length > 1) {
                        requestEnd = Integer.parseInt(rangeDatas[1]);
                    }
                }
                long length = 0;
                if (requestEnd > 0) {
                    length = requestEnd - requestStart + 1;
                    response.setHeader("Content-length", "" + length);
                    response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
                } else {
                    length = contentLength - requestStart;
                    response.setHeader("Content-length", "" + length);
                    response.setHeader("Content-Range", "bytes " + requestStart + "-" + (contentLength - 1) + "/" + contentLength);
                }
            }
            ServletOutputStream out = response.getOutputStream();
            int needSize = requestSize;
            randomFile.seek(start);
            while (needSize > 0) {
                byte[] buffer = new byte[4096];
                int len = randomFile.read(buffer);
                if (needSize < buffer.length) {
                    out.write(buffer, 0, needSize);
                } else {
                    out.write(buffer, 0, len);
                    if (len < buffer.length) {
                        break;
                    }
                }
                needSize -= buffer.length;
            }
            randomFile.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
