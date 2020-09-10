package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootdemo.entity.Users;
import com.example.springbootdemo.repository.UsersRepository;
import com.example.springbootdemo.service.UsersService;
import com.example.springbootdemo.util.MyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.validation.Validator;
import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersRepository usersRepository;
    @Override
    public Users reg(Users user) {
        //用户注册的时候必须对明文的密码进行加密，并自动将注册日期写入数据库中
        user.setPassword(MyUtil.encodeStringByMd5(user.getPassword()));
        //保存注册日期
        user.setRegDate(MyUtil.utilDateToChineseString(new Date()));
        //根据手机号码生成一个默认的用户名
        user.setUsername(MyUtil.generateRandomUsername(user.getMobile()));
        int result=usersRepository.insert(user);
        if(result>0){
            return usersRepository.selectById(user.getUid());
        }
        return null;
    }

    @Override
    public Users login(String identify, String password) {
        //需要判断identify是手机，电子邮箱还是用户名
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
        //wrapper.eq("username",username);
        //wrapper.eq("password",password);
        switch(MyUtil.checkIdentifyType(identify)) {
            case MOBILE:
                wrapper.eq("mobile", identify).eq("password", MyUtil.encodeStringByMd5(password));
                break;
            case EMAIL:
                wrapper.eq("email", identify).eq("password", MyUtil.encodeStringByMd5(password));
                break;
            case USERNAME:
                wrapper.eq("username", identify).eq("password", MyUtil.encodeStringByMd5(password));
                break;
        }
        return usersRepository.selectOne(wrapper);
    }

    @Override
    public Users queryUserById(int uid) {
        return usersRepository.selectById(uid);
    }

    @Override
    public Users updateUser(Users user) {
        usersRepository.updateById(user);
        return usersRepository.selectById(user.getUid());
    }

    @Override
    public boolean deleteUser(int uid) {
        int result=usersRepository.deleteById(uid);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Users> queryAllUsers() {
        return usersRepository.selectList(null);
    }
}
