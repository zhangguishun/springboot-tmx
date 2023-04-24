package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Software;
import com.example.uniappspringboot.Service.SoftwareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "软件市场")
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/software")
@ResponseBody
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;
    @PostMapping("/addSoft")
    @ApiOperation("新增")
    public R addSoftPost(Software software){return softwareService.addSoft(software);}//添加

    @PostMapping("/delSoft")
    @ApiOperation("删除")
    public R delSoftPost(Software software){return softwareService.delSoft(software);}//删除

    @PostMapping("/setSoft")
    @ApiOperation("修改")
    public R setSoftPost(Software software){return softwareService.setSoft(software);}//删除

    @PostMapping("/selSoft")
    @ApiOperation("查询（单个）")
    public R selSoftPost(Software software){return softwareService.selSoft(software);}//删除

    @GetMapping("/selSoftGet")
    @ApiOperation("查询（全部）")
    public R selSoftGets(){return softwareService.selSoftsGet();}//删除
}
