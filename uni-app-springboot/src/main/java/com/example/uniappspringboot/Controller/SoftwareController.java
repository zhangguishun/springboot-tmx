package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Software;
import com.example.uniappspringboot.Service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/software")
@ResponseBody
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;
    @PostMapping("/addSoft")
    public R addSoftPost(Software software){return softwareService.addSoft(software);}//添加

    @PostMapping("/delSoft")
    public R delSoftPost(Software software){return softwareService.delSoft(software);}//删除

    @PostMapping("/setSoft")
    public R setSoftPost(Software software){return softwareService.setSoft(software);}//删除

    @PostMapping("/selSoft")
    public R selSoftPost(Software software){return softwareService.selSoft(software);}//删除

    @GetMapping("/selSoftGet")
    public R selSoftGets(){return softwareService.selSoftsGet();}//删除
}
