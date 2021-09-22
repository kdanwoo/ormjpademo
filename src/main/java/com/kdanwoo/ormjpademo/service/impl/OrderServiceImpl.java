package com.kdanwoo.ormjpademo.service.impl;

import com.kdanwoo.ormjpademo.entity.Delivery;
import com.kdanwoo.ormjpademo.entity.Member;
import com.kdanwoo.ormjpademo.entity.Order;
import com.kdanwoo.ormjpademo.entity.OrderItem;
import com.kdanwoo.ormjpademo.entity.item.Item;
import com.kdanwoo.ormjpademo.enums.DeliveryStatus;
import com.kdanwoo.ormjpademo.repository.ItemRepository;
import com.kdanwoo.ormjpademo.repository.MemberRepository;
import com.kdanwoo.ormjpademo.repository.OrderRepository;
import com.kdanwoo.ormjpademo.repository.OrderSearch;
import com.kdanwoo.ormjpademo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();

    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }

    @Override
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
