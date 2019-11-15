package com.yu.v1center.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yu.commons.pojo.ResultBean;
import com.yu.v1center.pojo.WangEditorResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yu
 * @date 2019/11/1 0001
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FastFileStorageClient client;
    @Value("${image.server}")
    private String imageServer;

    @RequestMapping("upload")
    public ResultBean<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            StorePath storePath = client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extName, null);
            StringBuilder builder = new StringBuilder(imageServer).append(storePath.getFullPath());
            return new ResultBean<>("200", builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean<>("500", "上传文件错误");
        }
    }

    @RequestMapping("batchUpload")
    public WangEditorResultBean batchUpload(MultipartFile[] files) {

        String[] data=new String[files.length];
        try {
            for (int i=0;i<files.length;i++) {
                String originalFilename = files[i].getOriginalFilename();
                String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), extName, null);
                StringBuilder builder=new StringBuilder(imageServer).append(storePath.getFullPath());
                data[i]=builder.toString();
            }
            return new WangEditorResultBean("0",data);
        } catch (IOException e) {
            e.printStackTrace();
            return new WangEditorResultBean("1",null);
        }
    }
}
