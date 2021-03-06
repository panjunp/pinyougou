package com.pinyougou.shop.controller;


import com.pinyougou.common.util.FastDFSClient;
import com.pinyougou.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@RequestMapping("/upload")
@RestController
public class UploadController {
    @PostMapping("/upload")
    /*public Result upload(@RequestParam("file") MultipartFile file){*/
    public Result upload(MultipartFile file){
        Result result = Result.fail("上传图片失败");
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastdfs/tracker.conf");

            //获取文件拓展名
            String file_ext_name = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            //上传图片
            String url = fastDFSClient.uploadFile(file.getBytes(), file_ext_name);

            return Result.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
