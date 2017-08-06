package com.ronaldo.activetest.listener;

import com.alibaba.fastjson.JSONObject;
import com.ronaldo.entity.Order;

/**
 * Created by ronaldo on 2017/8/6.
 */
public class ConsumerListener {

    public void handleMessage(Order order) {
        System.out.println("接收到的订单编号为:" + order.getOrderNo());
        if (1 == 1) {
            throw new RuntimeException();
        }
    }
}
