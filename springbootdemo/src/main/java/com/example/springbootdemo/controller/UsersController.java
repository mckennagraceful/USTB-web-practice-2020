package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Users;
import com.example.springbootdemo.service.UsersService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {
    @Resource
    private UsersService usersService;
    @GetMapping("auth")
    public Map<String,Object> login(String identify,String password){
        Map<String,Object> result=new HashMap<String,Object>();
        Users loginUser=usersService.login(identify,password);
        if(loginUser!=null){
            result.put("code",200);
            result.put("data",loginUser);
            result.put("msg","loginSuccess");
        }
        else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","loginFailure");
        }
        return result;
    }
    //处理post请求，请求参数必须用RequestBody修饰
    @PostMapping("/")
    public Map<String,Object> reg(@RequestBody Users user){
        Users regUser=usersService.reg(user);
        Map<String,Object> result=new HashMap<String, Object>();
        if(regUser!=null){
            result.put("code",200);
            result.put("data",regUser);
            result.put("msg","regSuccess");
        }
        else {
            result.put("code",400);
            result.put("data",null);
            result.put("msg","regFailure");
        }
        return result;
    }
    @GetMapping("/{uid}")
    public Map<String,Object> queryUserById(@PathVariable("uid") int uid){
        Map<String,Object> result=new HashMap<String, Object>();
        Users user=usersService.queryUserById(uid);
        if(user!=null){
            result.put("code",200);
            result.put("data",user);
            result.put("msg","querySuccess");
        }
        else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","queryFailure");
        }
        return result;
    }
    @PutMapping("/")
    public Map<String,Object> updateUser(@RequestBody Users user){
        Map<String,Object> result=new HashMap<String, Object>();
        user=usersService.updateUser(user);
        result.put("code",200);
        result.put("data",user);
        result.put("msg","updateSuccess");
        return result;
    }
    @DeleteMapping("/{uid}")
    public Map<String,Object> deleteUser(@PathVariable("uid") int uid){
        Map<String,Object> result=new HashMap<String, Object>();
        if(usersService.deleteUser(uid)){
            result.put("code",200);
            result.put("data",null);
            result.put("msg","deleteSuccess");
        }
        else {
            result.put("code",400);
            result.put("data",null);
            result.put("msg","deleteFailure");
        }
        return result;
    }
    @GetMapping("/")
    public Map<String,Object> queryAllUsers() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Users> usersList = usersService.queryAllUsers();
        if (usersList != null) {
            result.put("code", 200);
            result.put("data", usersList);
            result.put("msg", "queryFailure");
        } else {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryFailure");
        }
        return result;
    }
}
