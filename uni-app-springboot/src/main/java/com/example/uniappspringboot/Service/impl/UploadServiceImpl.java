package com.example.uniappspringboot.Service.impl;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class UploadServiceImpl implements UploadService {

    @Override //文件上传
    public  R fileopload(MultipartFile file){
        R r = new R();
        String Rrl= "https://www.yubin-fuwu.top/Static/";//服务器访问前缀
       // String filePath ="F:\\web\\";//本地存放路径
        String filePath ="/www/wwwroot/www.yubin-fuwu.top/Static/"; //服务器的存放路径
        if (file.isEmpty()){r.setCode(String.valueOf(203));r.setMsg("不通过");
        }else {
            //取出文件
            String OriginalFilename=file.getOriginalFilename();
           //文件重命名
            String fileName =System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
            //拼接文件存放地址
            File dest=new File(filePath+fileName);
            //判断文件路径是否存在  如果不存在就创建文件路径
            if (dest.getParentFile().exists())
                dest.getParentFile().mkdirs();
            try {
                //文件保存
                file.transferTo(dest);
                r.setData( Rrl+fileName);//服务器地址路径 + 文件名字
                //r.setData( fileName);//本地
                r.setCode(String.valueOf(200));
                r.setMsg("上传成功");

                //抛出异常
            } catch (IOException e) {e.printStackTrace();r.setData(e);}
        }
        return r;
    }

    @Override//删除文件
    public R deltimg(String fileName){
        String []filenameindex = fileName.split("https://www.yubin-fuwu.top/Static/");
        String imgname = null;
        for(String listName : filenameindex){imgname=listName;}
        File file = new File("/www/wwwroot/www.yubin-fuwu.top/Static/"+imgname);
        R r = new R();
        if(!file.exists()){
            r.setData("删除文件失败："+fileName+"不存在！");
            r.setCode(String.valueOf(203));
            r.setMsg("删除失败");
        }else{
            //判断这是不是一个文件，ps：有可能是文件夹
            if(file.isFile()){
                file.delete();//删除
                r.setCode(String.valueOf(200));
                r.setMsg("删除成功");
            }
        }

        return  r;
    }
}
