package com.kdanwoo.ormjpademo.service;

import com.kdanwoo.ormjpademo.entity.Order;
import com.kdanwoo.ormjpademo.repository.OrderSearch;

import java.util.List;

public interface OrderService {

    //주문
    Long order(Long memberId, Long itemId, int count);

    //주문취소
    void cancelOrder(Long orderId);

    //검색
    List<Order> findOrders(OrderSearch orderSearch);
}
