package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.Users;
import com.example.hellospringboot.service.UsersService;
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
    public Map<String,Object> login(String identify, String password){
        Map<String,Object> result = new HashMap<String,Object>();
        Users loginUser =  usersService.login(identify,password);
        if(loginUser!=null){
            result.put("code",200);
            result.put("data",loginUser);
            result.put("msg","loginSuccess");
        }else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","loginFailure");
        }
        System.out.println(result);
        return result;
    }

    //如果是处理post请求，请求参数必须使用@RequestBody,这个参数仅仅只有一个。
    //本身接受的就是一个json对象。
    @PostMapping("/")
    public Map<String,Object> reg(@RequestBody Users user){

        System.out.println("要注册的用户资料是：" + user);
        Map<String,Object> result = new HashMap<String,Object>();

        if(usersService.checkMobileIsExist(user.getMobile())){
            result.put("code",400);
            result.put("data",null);
            result.put("msg","该手机号已被注册");
            return result;
        }

        Users regUser =   usersService.reg(user);

        if(regUser!=null){
            result.put("code",200);
            result.put("data",regUser);
            result.put("msg","regSuccess");
        }else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","regFailure");
        }
        return result;
    }
    @GetMapping("/{uid}")
    public Map<String,Object> queryUserByUid(@PathVariable("uid") int uid){
        Map<String, Object> result = new HashMap<String, Object>();
        Users user = usersService.queryUserByUid(uid);
        if (user != null) {
            result.put("code", 200);
            result.put("data", user);
            result.put("msg", "querySuccess");
        } else {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryFailure");
        }
        return result;
    }

    @PutMapping("/")
    public Map<String, Object> updateUser(@RequestBody Users user) {
        Map<String, Object> result = new HashMap<String, Object>();
        user = usersService.updateUser(user);
        result.put("code", 200);
        result.put("data", user);
        result.put("msg", "updateSuccess");
        return result;
    }

    @DeleteMapping("/{uid}")
    public Map<String, Object> deleteUserByUid(@PathVariable("uid") int uid) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (usersService.deleteUserByUid(uid)) {
            result.put("code", 200);
            result.put("data", null);
            result.put("msg", "deleteSuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "deleteFailure");
        }
        return result;
    }

    @GetMapping("/")
    public Map<String, Object> queryAllUsers(){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Users> usersList = usersService.queryAllUsers();
        if(usersList!=null){
            result.put("code", 200);
            result.put("data", usersList);
            result.put("msg", "querySuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryFailure");
        }
        return result;
    }
}
