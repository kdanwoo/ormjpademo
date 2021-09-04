package com.kdanwoo.ormjpademo.service;

public interface OrderService {

    //주문
    Long order(Long memberId, Long itemId, int count);

    //주문취소
    void cancelOrder(Long orderId);

    //검색
    //List<Order> findOrders(OrderSearch orderSearch);
}
