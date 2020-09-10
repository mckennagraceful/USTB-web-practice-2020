package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Users;

import java.util.List;

public interface UsersService {
    //用户注册
    public Users reg(Users user);
    //用户登录,可选择三种方式
    public Users login(String identify,String password);
    //根据用户编号查询单个用户
    public Users queryUserById(int uid);
    //更新用户信息
    public Users updateUser(Users user);
    //根据用户ID删除用户信息
    public boolean deleteUser(int uid);
    //查询所有用户
    public List<Users> queryAllUsers();

}
