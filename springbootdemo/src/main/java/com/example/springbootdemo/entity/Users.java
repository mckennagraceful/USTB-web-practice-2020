package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//表示这个实体类会映射成一张表
@Data//自动生成getter和setter toString hashCode equals
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    //所有属性一律都是私有的
    @Id//表示它是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//使用默认的主键生成策略auto_increment
    @TableId(value = "uid",type = IdType.AUTO)
    private int uid;
    private String mobile;
    private String email;
    private String username;
    private String password;
    private String birthday;
    @Column(length = 2)
    private String gender;
    private String regDate;
    private int status;
}
