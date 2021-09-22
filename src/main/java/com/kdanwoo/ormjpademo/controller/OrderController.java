package com.kdanwoo.ormjpademo.controller;

import com.kdanwoo.ormjpademo.entity.Member;
import com.kdanwoo.ormjpademo.entity.item.Item;
import com.kdanwoo.ormjpademo.service.ItemService;
import com.kdanwoo.ormjpademo.service.MemberService;
import com.kdanwoo.ormjpademo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }
}
