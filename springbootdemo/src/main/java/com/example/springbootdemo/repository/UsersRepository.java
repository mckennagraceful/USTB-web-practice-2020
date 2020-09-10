package com.example.springbootdemo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends BaseMapper<Users> {

}
