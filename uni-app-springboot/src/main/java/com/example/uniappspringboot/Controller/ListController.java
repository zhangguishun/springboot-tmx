package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Service.ListService;
import com.example.uniappspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/list")
@ResponseBody
public class ListController {

    @Autowired
    private ListService listService;
    @GetMapping("/selItem")
    public R GetSelList(){return listService.selList();}

}
