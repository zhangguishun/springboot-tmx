package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Privileges;
import com.example.uniappspringboot.Service.PrivilegesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "权限模块")
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/Privileges")
@ResponseBody
public class PrivilegesController {

        @Autowired
        private PrivilegesService privilegesService;
        @GetMapping("/sels")//权限查询接口（全部）
        @ApiOperation("权限查询接口（全部）")
        public R GetPrivileges(){return privilegesService.selPrivileges();}


        @PostMapping("/add")//新增加接口（全部）
        @ApiOperation("新增加接口（全部）")
        public R addPrivileges(Privileges privileges){return privilegesService.addPrivileges(privileges);}

}