package com.wanxi.springboot.team.manage.system.controller;


import com.wanxi.springboot.team.manage.system.api.CommonResult;
import com.wanxi.springboot.team.manage.system.model.User;
import com.wanxi.springboot.team.manage.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制类")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${jwt.tokenHead}")
    String tokenHead;

    @GetMapping("/getUserByName")
    @ApiOperation("根据用户编号查找用户")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "String",dataType = "json")
    public CommonResult getUserByName(@RequestParam String username) {
        return userService.getUserByName(username);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "user",value = "User实体类",required = true,paramType = "User",dataType = "json")
    public CommonResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "user",value = "User实体类",required = true,paramType = "User",dataType = "json")
    public CommonResult register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/getUsers")
    @ApiOperation("条件查询获取用户列表")
    @ApiImplicitParam(name = "map",value = "带有page,limit,param,入学时间范围等条件的map集合",required = true,paramType = "Map",dataType = "json")
    public CommonResult getUsers(@RequestParam Map map) {
        return userService.getUsers(map);
    }

    @GetMapping("/deleteUser")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id",value = "用户主键",required = true,paramType = "int",dataType = "json")
    public CommonResult deleteUser(@RequestParam Integer id){
        return userService.deleteUser(id);
    }

    @GetMapping("/updateUser")
    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "user",value = "用户实体类",required = true,paramType = "User",dataType = "json")
    public CommonResult updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/getCodeList")
    @ApiOperation("获取码表")
    public CommonResult getCodeList(){
        return userService.getCodeList();
    }
}
