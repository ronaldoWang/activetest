package com.ronaldo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ronaldo on 2017/8/6.
 */
public class Order implements Serializable {
    private Integer id;
    private String orderNo;
    private Date orderDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
