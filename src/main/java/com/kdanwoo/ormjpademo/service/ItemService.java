package com.kdanwoo.ormjpademo.service;

import com.kdanwoo.ormjpademo.entity.item.Item;

import java.util.List;

public interface ItemService {
    void saveItem(Item item);
    List<Item> findItems();
    Item findOne(Long itemId);

    void updateItem(Long id, String name, int price, int stockQuantity);
}
