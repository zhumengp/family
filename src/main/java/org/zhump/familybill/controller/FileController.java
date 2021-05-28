package org.zhump.familybill.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhmp
 * @description 说明一下这个类是干嘛的
 * @date 2021-02-16$ 13:12$
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Value("${system.filePath}")
    private String filePath;

    @Value("${system.url}")
    private String fileUrl;



    /**
     * 图片上传
     *
     * @param file
     * @author zhump
     * @return java.lang.String
     * @date 2021/4/10 20:53
     * @throws
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file){
        if (file.isEmpty()) {
            System.out.println("文件为空空");
            return "文件为空";
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 上传后的路径
        //String filePath = "D://temp-rainy//";
        // 新文件名
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath +File.separator+"image"+File.separator+ fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = fileUrl+"/image/" + fileName;
        return filename;
    }



}
