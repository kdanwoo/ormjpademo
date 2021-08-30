package com.kdanwoo.ormjpademo.entity;

import com.kdanwoo.ormjpademo.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    private OrderStatus orderStatus; //주문상태 ORDER, CANCEL
}
