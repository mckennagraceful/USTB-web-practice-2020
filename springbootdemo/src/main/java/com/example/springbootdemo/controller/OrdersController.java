package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Orders;
import com.example.springbootdemo.entity.Products;
import com.example.springbootdemo.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("add")
    public Map<String, Object> addOrder(@RequestBody Products product, int uid) {
        Orders order=ordersService.addOrder(product,uid);
        Map<String,Object> result=new HashMap<String, Object>();
        if(order!=null){
            result.put("code",200);
            result.put("data",order);
            result.put("msg","regSuccess");
        }
        else {
            result.put("code",400);
            result.put("data",null);
            result.put("msg","regFailure");
        }
        return result;
    }
    @DeleteMapping("/{oid}")
    public Map<String,Object> deleteOrder(@PathVariable("oid") int oid){
        Map<String,Object> result=new HashMap<String, Object>();
        if(ordersService.deleteOrder(oid)){
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
    @GetMapping("data")
    public Map<String,Object> queryAllOrders(){
        Map<String,Object> result=new HashMap<String, Object>();
        List<Orders> ordersList=ordersService.queryAllOrder();
        if(ordersList!=null){
            result.put("code",200);
            result.put("data",ordersList);
            result.put("msg","querySuccess");
        }
        else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","queryFailure");
        }
        return result;
    }
    @PutMapping("pay/{oid}")
    public Map<String,Object> payOrder(@PathVariable("oid") int oid){
        Map<String,Object> result=new HashMap<String, Object>();
        Orders order=ordersService.payOrder(oid);
        result.put("code",200);
        result.put("data",order);
        result.put("msg","paySuccess");
        return result;
    }
}
