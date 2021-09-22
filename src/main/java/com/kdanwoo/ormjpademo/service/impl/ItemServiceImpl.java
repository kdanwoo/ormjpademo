package com.kdanwoo.ormjpademo.service.impl;

import com.kdanwoo.ormjpademo.entity.item.Book;
import com.kdanwoo.ormjpademo.entity.item.Item;
import com.kdanwoo.ormjpademo.repository.ItemRepository;
import com.kdanwoo.ormjpademo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional //Merge 방식
    public void updateItem(Long itemId, Book param){
        Book findItem = (Book) itemRepository.findOne(itemId); //영속상태
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
        findItem.setAuthor(param.getAuthor());
        findItem.setIsbn(param.getIsbn());
        //commit이 되서 jpa는 flush를 날림.

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    @Override
    public void updateItem(Long id, String name, int price) {
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
    }

}
