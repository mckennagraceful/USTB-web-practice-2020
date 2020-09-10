package com.example.springbootdemo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends BaseMapper<Orders> {
}
