package com.example.uniappspringboot.Controller;

import java.io.IOException;
import java.io.InputStream;


import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class ploadController {
	@PostMapping("/head")
	public String upload(MultipartFile file,HttpServletRequest req) throws IOException{
		String fileName= file.getOriginalFilename();
		InputStream is= file.getInputStream();
		String[] split = fileName.split("\\.");
		int length =split.length;
		int finalIndex =length - 1;
		String suffix =split[finalIndex];
		long sss = System.currentTimeMillis();
		fileName = sss+"."+suffix;
		//4、拿到文件保存路径
		String tomcatPath= System.getProperty("catalina.home");
		String uploadPath ="\\webapps\\uploads\\";
		//1/5、拼接完整路径
		String fullPath = tomcatPath+uploadPath+fileName;
		//6、文件写入到指定路径
		//FileUtil.writeFromStream(is, fullPath);

		System.out.print(fullPath);
		//返回文件的访问地址
		//http://localhost:8086/uploads/test.jpg
		String http = req.getScheme();
		String localhost = req.getServerName();
		int serverPort = req.getServerPort();
		String url = http+"://"+localhost+":"+serverPort+"/uploads/"+fileName;
		//响应数据
		return url;
	}

}
