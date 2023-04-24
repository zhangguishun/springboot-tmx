package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Service.ListService;
import com.example.uniappspringboot.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "官网首页数据")
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/list")
@ResponseBody
public class ListController {

    @Autowired
    private ListService listService;
    @GetMapping("/selItem")
    @ApiOperation("获取官网数据列表")
    public R GetSelList(){return listService.selList();}

}
