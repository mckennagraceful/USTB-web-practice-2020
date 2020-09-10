package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//表示这个实体类会映射成一张表
@Data//自动生成getter和setter toString hashCode equals
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    //所有属性一律都是私有的
    @Id//表示它是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//使用默认的主键生成策略auto_increment
    @TableId(value = "sid",type = IdType.AUTO)
    private int sid;//编号
    private int price;
    private String addr;
}
