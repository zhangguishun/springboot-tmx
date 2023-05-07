package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.ListCate;
import com.example.uniappspringboot.Domain.ListItem;
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

    @PostMapping("/addItem")
    @ApiOperation("新增站点数据")
    public R PostAddItem(ListItem listItem){return listService.addList(listItem);}

    @PostMapping("/delItem")
    @ApiOperation("删除站点数据")
    public R PostDelItem(ListItem listItem){return listService.delList(listItem);}

    @PostMapping("/setItem")
    @ApiOperation("修改站点数据")
    public R PostSetItem(ListItem listItem){return listService.setList(listItem);}


    @PostMapping("/addCate")
    @ApiOperation("新增站点分类")
    public R PostAddCate(ListCate listCate){return listService.addCate(listCate);}

    @PostMapping("/setCate")
    @ApiOperation("修改站点分类")
    public R PostSetCate(ListCate listCate){return listService.setCate(listCate);}

}
