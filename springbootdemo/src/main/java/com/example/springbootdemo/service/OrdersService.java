package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Orders;
import com.example.springbootdemo.entity.Products;

import java.util.List;

public interface OrdersService {
    //添加订单
    public Orders addOrder(Products product,int uid);
    //删除订单
    public boolean deleteOrder(int oid);
    //获取所有订单的信息
    public List<Orders> queryAllOrder();
    //支付订单
    public Orders payOrder(int oid);
}
