package com.kdanwoo.ormjpademo.service.impl;

import com.kdanwoo.ormjpademo.entity.Address;
import com.kdanwoo.ormjpademo.entity.Member;
import com.kdanwoo.ormjpademo.entity.Order;
import com.kdanwoo.ormjpademo.entity.item.Book;
import com.kdanwoo.ormjpademo.entity.item.Item;
import com.kdanwoo.ormjpademo.enums.OrderStatus;
import com.kdanwoo.ormjpademo.exception.NotEnoughStockException;
import com.kdanwoo.ormjpademo.repository.OrderRepository;
import com.kdanwoo.ormjpademo.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //Spring에서는 @Transactional 어노테이션이 테스트 케이스에 있으면 테스트 마치고 rollback 해버림.ㄴ
public class OrderServiceImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void order() throws Exception{ //상품주문
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getOrderStatus());
        Assert.assertEquals("주문한 상품 종류수가 정확해야한다.", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄오야한다,", 8, item.getStockQuantity());


    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

    @Test(expected = NotEnoughStockException.class)
    public void cancel() throws Exception{
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
//When
        orderService.cancelOrder(orderId);
        //Then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문 취소시 상태는 CANCEL 이다.",OrderStatus.CANCEL, getOrder.getOrderStatus());
        Assert.assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void stockCheck() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고

        int orderCount =11;

        //when
        orderService.order(member.getId(),item.getId(),orderCount);
        //then

        //fail("재고수량부족 예외가 발생해야한다.");
    }

}