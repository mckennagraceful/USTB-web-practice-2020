package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.Orders;
import com.example.springbootdemo.entity.Products;
import com.example.springbootdemo.repository.OrdersRepository;
import com.example.springbootdemo.service.OrdersService;
import com.example.springbootdemo.util.MyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersRepository ordersRepository;

    @Override
    public Orders addOrder(Products product,int uid) {
        Orders order=new Orders();
        order.setOrderDate(MyUtil.utilDateToChineseString(new Date()));
        order.setSid(product.getSid());
        order.setUid(uid);
        order.setAddr(product.getAddr());
        order.setPrice(product.getPrice());
        order.setIsPaid(0);
        int result=ordersRepository.insert(order);
        if(result>0){
            return ordersRepository.selectById(order.getOid());
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int oid) {
        int result=ordersRepository.deleteById(oid);
        if(result>0) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Orders> queryAllOrder() {
        return ordersRepository.selectList(null);
    }

    @Override
    public Orders payOrder(int oid) {
        Orders tmp_order=ordersRepository.selectById(oid);
        tmp_order.setIsPaid(1);
        ordersRepository.updateById(tmp_order);
        return ordersRepository.selectById(tmp_order.getOid());
    }

    @Override
    public int settle() {
        int total=0;
        Orders tmp=new Orders();
        List<Orders> allOrders=ordersRepository.selectList(null);
        for(int i=0;i<allOrders.size();i++){
            tmp= allOrders.get(i);
            if(tmp.getIsPaid()==0){
                total+=tmp.getPrice();
            }
        }
        return total;
    }

    @Override
    public void allSettle() {
        Orders tmp=new Orders();
        List<Orders> allOrders=ordersRepository.selectList(null);
        for(int i=0;i<allOrders.size();i++){
            tmp= allOrders.get(i);
            if(tmp.getIsPaid()==0){
                payOrder(tmp.getOid());
            }
        }
    }
}
